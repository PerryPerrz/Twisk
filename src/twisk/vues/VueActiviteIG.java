package twisk.vues;

import javafx.application.Platform;
import javafx.scene.layout.HBox;
import twisk.designPattern.Observateur;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;
import twisk.outils.TailleComposants;

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
        this.label.setStyle("-fx-text-fill: #138D75");
        box.setStyle("-fx-border-color: #8F00FF; -fx-background-insets: 0 0 -1 0, 0, 1, 2; -fx-background-radius: 3px, 3px, 2px, 1px; -fx-border-width: 3px; -fx-background-color: #a2d5f2 ;");
        box.setPrefHeight(TailleComposants.getInstance().getHautAct());
        this.getChildren().add(box);
        this.setOnMouseClicked(actionEvent -> monde.ajouterEtapeSelectionnee(this.etape));
    }

    @Override
    public void reagir() {
        //On force l'exécution dans le Thread Graphique car c'est une fonction graphique appelée par le modèle
        Runnable command = () -> {
            relocate(etape.getPosX(), etape.getPosY());
            if (monde.isSelectionned(etape)) {
                setStyle("-fx-border-color: #f4abc4;-fx-border-width: 2px;-fx-background-color: #f4abc4;");
            }
        };
        if (Platform.isFxApplicationThread()) {
            command.run();
        } else {
            Platform.runLater(command);
        }
    }
}
