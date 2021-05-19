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
import twisk.outils.TailleComposants;
import twisk.simulation.Client;

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
        TailleComposants tC = TailleComposants.getInstance();
        for (Iterator<EtapeIG> iter = monde.iterator(); iter.hasNext(); ) {
            EtapeIG etape = iter.next();
            //On met à jour le modèle avant de mettre à jour la vue.
            etape.randomPositions();
            VueActiviteIG viewA = new VueActiviteIG(this.monde, etape);
            viewA.setMinSize(tC.getLargAct(), tC.getHautAct());
            this.getChildren().add(viewA);
            viewA.reagir();
            for (PointDeControleIG pdc : etape) {
                VuePointDeControleIG viewPdc = new VuePointDeControleIG(this.monde, pdc);
                this.getChildren().add(viewPdc);
                viewPdc.reagir();
            }
        }
        this.setOnDragOver(dragEvent -> {
            if (dragEvent.getDragboard().hasString()) { //Si le dragDropped renvoie bien un string, je peux bouger
                dragEvent.acceptTransferModes(TransferMode.MOVE);
            }
            dragEvent.consume();
        });

        this.setOnDragDropped(dragEvent -> {
            Dragboard db = dragEvent.getDragboard();
            // Get item id here, which was stored when the drag started.
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
            dragEvent.setDropCompleted(success);
            dragEvent.consume();
        });
    }

    @Override
    public void reagir() {
        Runnable command = () -> {
            getChildren().clear();
            TailleComposants tC = TailleComposants.getInstance();
            //Il demande un iterator sur les arcs au monde puis parcours les arcs avec un for
            for (Iterator<ArcIG> it = monde.iteratorArcs(); it.hasNext(); ) {
                ArcIG a = it.next();
                VueArcIG viewArk = new VueArcIG(monde, a);
                getChildren().add(viewArk);
            }
            for (Iterator<EtapeIG> iter = monde.iterator(); iter.hasNext(); ) {
                EtapeIG etape = iter.next();
                if (etape.estUneActivite()) {
                    VueActiviteIG viewA = new VueActiviteIG(monde, etape);
                    viewA.setMinSize(tC.getLargAct(), tC.getHautAct());
                    getChildren().add(viewA);
                } else {
                    VueGuichetIG viewG = new VueGuichetIG(monde, etape);
                    viewG.setMinSize(tC.getLargAct(), tC.getHautAct());
                    getChildren().add(viewG);
                }
                for (PointDeControleIG pdc : etape) {
                    VuePointDeControleIG viewPdc = new VuePointDeControleIG(monde, pdc);
                    getChildren().add(viewPdc);
                }
            }
            if (monde.simulationACommencee()) {
                for (Client cl : monde.getGestionnaireClientDeSimulation()) {
                    VueClient viewC = new VueClient(monde, cl);
                    getChildren().add(viewC);
                    System.out.println(viewC.getCenterX());
                    System.out.println(viewC.getCenterY());
                }
            }
            switch (monde.getStyle()) {
                case 0:
                    getParent().setStyle("-fx-background-color : #ffe268");
                    break;
                case 1:
                    getParent().setStyle("-fx-background-color : #151515");
                    break;
                case 2:
                    getParent().setStyle("-fx-background-color : #FFFFFF");
            }
        };
        if (Platform.isFxApplicationThread()) {
            command.run();
        } else {
            Platform.runLater(command);
        }
    }
}
