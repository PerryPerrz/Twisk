package twisk.vues;

import animatefx.animation.Pulse;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import twisk.designPattern.Observateur;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;
import twisk.outils.CouleurComposants;
import twisk.outils.TailleComposants;

/**
 * La classe VueGuichetIG
 */
public class VueGuichetIG extends VueEtapeIG implements Observateur {
    private final HBox box;
    private int paneLibre;

    /**
     * Constructeur de la classe VueGuichetIG
     *
     * @param monde le monde
     * @param etape l'etape
     */
    public VueGuichetIG(MondeIG monde, EtapeIG etape) {
        super(monde, etape);
        if (etape.siEstUnGuichetGetVersLaDroite() == null || etape.siEstUnGuichetGetVersLaDroite())
            paneLibre = 0;
        else
            paneLibre = monde.getNbClients() - etape.siEstUnGuichetGetNbJetons() - 1;
        box = new HBox();

        TailleComposants tc = TailleComposants.getInstance();
        CouleurComposants cc = CouleurComposants.getInstance();

        this.label.setStyle("-fx-text-fill: " + cc.getCouleurLabelGuichet());
        box.setStyle("-fx-border-color: " + cc.getCouleurBorderGuichet() + "; -fx-background-insets: 0 0 -1 0, 0, 1, 2; -fx-background-radius: 3px, 3px, 2px, 1px; -fx-border-width: 3px; -fx-background-color:" + cc.getCouleurBackgroundGuichet() + " ;");

        this.setMinSize(tc.getLargGuichet(), tc.getHautGuichet()); //Taille min du guichet
        this.setMaxSize(tc.getLargGuichet(), tc.getHautGuichet()); //Taille max du guichet
        box.setMinSize(TailleComposants.getInstance().getLargGuichet() - tc.getMargeSelection(), TailleComposants.getInstance().getHautGuichet() - tc.getHautLabelEtape() - tc.getMargeSelection());
        box.setMaxSize(TailleComposants.getInstance().getLargGuichet() - tc.getMargeSelection(), TailleComposants.getInstance().getHautGuichet() - tc.getHautLabelEtape() - tc.getMargeSelection());

        box.setPadding(new Insets(5));
        box.setSpacing(5);

        if (!monde.simulationACommencee()) {
            //Animation
            new Pulse(box).play();
        }

        ajouterPane();
        this.getChildren().add(box);
        this.setOnMouseClicked(actionEvent -> monde.ajouterEtapeSelectionnee(this.etape));
    }

    @Override
    public void ajouterVueClient(VueClient viewC) {
        //Utilisation d'un StackPane pour pouvoir afficher les clients au centre du rectangle.
        StackPane sp = (StackPane) box.getChildren().get(paneLibre);
        sp.getChildren().add(viewC);
        if (etape.siEstUnGuichetGetVersLaDroite() == null || etape.siEstUnGuichetGetVersLaDroite())
            paneLibre++;
        else
            paneLibre--;
    }

    private void ajouterPane() {
        for (int i = 0; i < monde.getNbClients() - etape.siEstUnGuichetGetNbJetons(); i++) {
            StackPane sp = new StackPane();
            sp.setStyle("-fx-border-color: " + CouleurComposants.getInstance().getCouleurBorderGuichet() + "; -fx-border-width: 3px; -fx-background-color:" + CouleurComposants.getInstance().getCouleurBackgroundGuichet() + ";");
            sp.setPrefSize(TailleComposants.getInstance().getHautAct(), TailleComposants.getInstance().getRad() * 2);
            box.getChildren().add(sp);
        }
    }

    @Override
    public void reagir() {
    }
}
