package twisk.vues;

import javafx.scene.layout.HBox;
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

    /**
     * Constructeur de la classe VueGuichetIG
     *
     * @param monde le monde
     * @param etape l'etape
     */
    public VueGuichetIG(MondeIG monde, EtapeIG etape) {
        super(monde, etape);
        box = new HBox();

        CouleurComposants cc = CouleurComposants.getInstance();

        this.label.setStyle("-fx-text-fill: " + cc.getCouleurLabelGuichet());
        box.setStyle("-fx-border-color: " + cc.getCouleurBorderGuichet() + "; -fx-background-insets: 0 0 -1 0, 0, 1, 2; -fx-background-radius: 3px, 3px, 2px, 1px; -fx-border-width: 3px; -fx-background-color:" + cc.getCouleurBackgroundGuichet() + " ;");
        box.setPrefSize(TailleComposants.getInstance().getHautAct(), TailleComposants.getInstance().getLargAct());
        this.getChildren().add(box);
        this.setOnMouseClicked(actionEvent -> monde.ajouterEtapeSelectionnee(this.etape));
    }

    @Override
    public void ajouterVueClient(VueClient viewC) {
        box.getChildren().add(viewC);
    }

    @Override
    public void reagir() {
    }
}
