package twisk.vues;

import javafx.application.Platform;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import twisk.designPattern.Observateur;
import twisk.mondeIG.ArcIG;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;
import twisk.mondeIG.PointDeControleIG;
import twisk.outils.CouleurComposants;
import twisk.outils.TailleComposants;
import twisk.simulation.Client;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * La classe VueMondeIG.
 */
public class VueMondeIG extends Pane implements Observateur {
    private final MondeIG monde;

    /**
     * Constructeur de la classe VueMondeIG.
     *
     * @param monde le monde
     */
    public VueMondeIG(MondeIG monde) {
        this.monde = monde;
        monde.ajouterObservateur(this);
        TailleComposants tc = TailleComposants.getInstance();
        for (Iterator<EtapeIG> iter = monde.iterator(); iter.hasNext(); ) {
            EtapeIG etape = iter.next();
            //On met à jour le modèle avant de mettre à jour la vue.
            VueActiviteIG viewA = new VueActiviteIG(this.monde, etape);
            viewA.setMinSize(tc.getLargAct(), tc.getHautAct());
            viewA.setPoBVueMondeIG(this.pickOnBoundsProperty());
            this.getChildren().add(viewA);
            viewA.reagir();
            for (PointDeControleIG pdc : etape) {
                VuePointDeControleIG viewPdc = new VuePointDeControleIG(this.monde, pdc);
                this.getChildren().add(viewPdc);
                viewPdc.reagir();
            }
        }
        this.setOnDragOver(dragEvent -> {
            if (!monde.simulationACommencee()) {
                if (dragEvent.getDragboard().hasString()) { //Si le dragDropped renvoie bien un string, je peux bouger
                    //On fait réapparaitre la hitbox de VueMondeIG par dessus la hitbox de VueOutils pour drag'n'drop donc on ne peut pas appuyer sur les boutons pendant le drag'n'drop
                    this.setPickOnBounds(true);
                    dragEvent.acceptTransferModes(TransferMode.MOVE);
                }
            }
            dragEvent.consume();
        });

        this.setOnDragDropped(dragEvent -> {
            if (!monde.simulationACommencee()) {
                Dragboard db = dragEvent.getDragboard();
                boolean success = false;
                if (db.hasString()) {
                    String indice = db.getString();
                    VueEtapeIG image = (VueEtapeIG) this.lookup("#" + indice); //On cherche dans la VueMondeIG, la VueEtapeIG qui s'est fait drag and drop
                    if (image != null) { //Si l'objet drag n drop existe et si c'est bien une VueEtapeIG
                        //On change l'emplacement de l'étape
                        monde.changerEmplacementEtape(indice, (int) dragEvent.getX(), (int) dragEvent.getY());
                        success = true;
                    }
                }
                //On autorise plus le drag'n'drop pour empêcher les conflits de hitbox avec les boutons de vueOutils.
                this.setPickOnBounds(false);
                dragEvent.setDropCompleted(success);
            }
            dragEvent.consume();
        });
        //On passe la hitbox de VueMondeIG en dessous ou pas au même endroit que celle de VueOutils pour permettre de cliquer sur les boutons lorsqu'une activité les recouvrent ou est à côté mais on ne peut plus drag'n'drop
        this.setPickOnBounds(false);
    }

    @Override
    public void reagir() {
        CouleurComposants cc = CouleurComposants.getInstance();
        //La fonction réagir de VueMondeIG est la première à être appelée et elle réinstancie toutes les autres vues qu'elle contient.
        //De ce fait, toutes les autres vues n'ont pas besoin de réagir car elle sont supprimées à chaque fois que l'on est sur le point de les faire réagir.
        Runnable command = () -> {
            getChildren().clear();
            TailleComposants tC = TailleComposants.getInstance();
            //Il demande un iterator sur les arcs au monde puis parcours les arcs avec un for
            for (Iterator<ArcIG> it = monde.iteratorArcs(); it.hasNext(); ) {
                ArcIG a = it.next();
                VueArcIG viewArk = new VueArcIG(monde, a);
                getChildren().add(viewArk);
            }
            ArrayList<VueEtapeIG> vueEtapeIGS = new ArrayList<>(10); //Stockage des vues pour permettre aux clients de se poser dedans
            for (Iterator<EtapeIG> iter = monde.iterator(); iter.hasNext(); ) {
                EtapeIG etape = iter.next();
                if (etape.estUneActivite()) {
                    VueActiviteIG viewA = new VueActiviteIG(monde, etape);
                    //viewA.setMinSize(tC.getLargAct(), tC.getHautAct());
                    viewA.setPoBVueMondeIG(this.pickOnBoundsProperty());
                    getChildren().add(viewA);
                    vueEtapeIGS.add(viewA);
                    for (PointDeControleIG pdc : etape) {
                        VuePointDeControleIG viewPdc = new VuePointDeControleIG(monde, pdc);
                        getChildren().add(viewPdc);
                    }
                } else {
                    VueGuichetIG viewG = new VueGuichetIG(monde, etape);
                    viewG.setPoBVueMondeIG(this.pickOnBoundsProperty());
                    //viewG.setMinSize(tC.getLargAct(), tC.getHautAct());
                    getChildren().add(viewG);
                    vueEtapeIGS.add(viewG);
                    VuePointDeControleIG viewPdc = new VuePointDeControleIG(monde, etape.getPdcIndex(2));
                    VuePointDeControleIG viewPdc2 = new VuePointDeControleIG(monde, etape.getPdcIndex(3));
                    getChildren().addAll(viewPdc, viewPdc2);
                }
            }
            if (monde.simulationACommencee()) {
                for (Client cl : monde.getGestionnaireClientDeSimulation()) {
                    VueClient viewC = new VueClient(monde, cl);
                    getChildren().add(viewC);
                    for (VueEtapeIG vueE : vueEtapeIGS) {
                        if (vueE.getEtape().equals(viewC.getE()))
                            vueE.ajouterVueClient(viewC);
                    }
                }
            }
            switch (monde.getStyle()) {
                case 0:
                    getParent().setStyle("-fx-background-color : " + cc.getCouleurBackgroundJourMonde());
                    break;
                case 1:
                    getParent().setStyle("-fx-background-color : " + cc.getCouleurBackgroundNuitMonde());
                    break;
                case 2:
                    getParent().setStyle("-fx-background-color : " + cc.getCouleurBackgroundResetMonde());
            }
        };
        if (Platform.isFxApplicationThread()) {
            command.run();
        } else {
            Platform.runLater(command);
        }
    }
}
