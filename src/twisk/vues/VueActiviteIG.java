package twisk.vues;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import twisk.designPattern.Observateur;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;

/**
 * La classe VueActiviteIG.
 */
public class VueActiviteIG extends VueEtapeIG implements Observateur {
    private final HBox box;

    /**
     * Constructeur de la classe VueActiviteIG
     *
     * @param monde le monde
     * @param etape l'etape
     */
    public VueActiviteIG(MondeIG monde, EtapeIG etape) {
        super(monde, etape);
        box = new HBox();
        box.setStyle("-fx-border-color: #8F00FF; -fx-background-insets: 0 0 -1 0, 0, 1, 2; -fx-background-radius: 3px, 3px, 2px, 1px; -fx-border-width: 3px; -fx-background-color: #a2d5f2 ;");
        box.getChildren().add(new Label("D : " + etape.getDelai() + " | É : " + etape.getEcart()));
        this.getChildren().add(box);
        this.setOnMouseClicked(actionEvent -> monde.ajouterEtapeSelectionnee(this.etape));
    }

    @Override
    public void reagir() {
        this.relocate(etape.getPosX(), etape.getPosY());
        if (monde.isSelectionned(etape)) {
            this.setStyle("-fx-border-color: #f4abc4;-fx-border-width: 2px;-fx-background-color: #f4abc4;");
        }
    }
}
