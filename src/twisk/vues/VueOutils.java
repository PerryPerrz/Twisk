package twisk.vues;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import twisk.designPattern.Observateur;
import twisk.exceptions.MondeException;
import twisk.exceptions.PasUnGuichetException;
import twisk.exceptions.TwiskException;
import twisk.mondeIG.MondeIG;
import twisk.outils.TailleComposants;

import java.util.Objects;


/**
 * La classe VueOutils.
 */
public class VueOutils extends TilePane implements Observateur {
    private final MondeIG monde;
    private final Button boutonActivite;
    private final Button boutonGuichet;
    private final Button boutonSimulation;

    /**
     * Constructeur de la classe VueOutils.
     *
     * @param monde le monde
     */
    public VueOutils(MondeIG monde) {
        this.monde = monde;
        monde.ajouterObservateur(this);
        boutonActivite = new Button();
        boutonGuichet = new Button();
        boutonSimulation = new Button();
        boutonActivite.setOnAction(actionEvent -> monde.ajouter("Activite"));
        boutonGuichet.setOnAction(actionEvent -> monde.ajouter("Guichet"));
        boutonSimulation.setOnAction(actionEvent -> {
            try {
                monde.simuler();
            } catch (MondeException | PasUnGuichetException e) {
                lancerFenetreErreurSimu(e);
            }
        });
        Tooltip tool = new Tooltip("Ajouter une activité !");
        Tooltip tool2 = new Tooltip("Ajouter un guichet !");
        Tooltip tool3 = new Tooltip("Réaliser une simulation !");
        boutonActivite.setTooltip(tool);
        boutonGuichet.setTooltip(tool2);
        boutonSimulation.setTooltip(tool3);
        TailleComposants tC = TailleComposants.getInstance();
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/add_activite.png")), tC.getTailleBouton(), tC.getTailleBouton(), true, true); //Donne le chemin à partir de src
        Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/add_guichet.png")), tC.getTailleBouton(), tC.getTailleBouton(), true, true);
        Image image3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/play.png")), tC.getTailleBouton(), tC.getTailleBouton(), true, true);
        ImageView icon = new ImageView(image);
        ImageView icon2 = new ImageView(image2);
        ImageView icon3 = new ImageView(image3);
        boutonActivite.setStyle("-fx-background-color:transparent; -fx-focus-color: transparent;");
        boutonActivite.setGraphic(icon);
        boutonGuichet.setStyle("-fx-background-color:transparent; -fx-focus-color: transparent;");
        boutonGuichet.setGraphic(icon2);
        boutonSimulation.setStyle("-fx-background-color:transparent; -fx-focus-color: transparent;");
        boutonSimulation.setGraphic(icon3);
        this.getChildren().addAll(boutonActivite, boutonGuichet, boutonSimulation);
    }

    @Override
    public void reagir() {
        Runnable command = () -> {
            TailleComposants tC = TailleComposants.getInstance();
            if (monde.simulationACommencee()) {
                Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/arret.png")), tC.getTailleBouton(), tC.getTailleBouton(), true, true);
                ImageView icon = new ImageView(image);
                boutonSimulation.setStyle("-fx-background-color:transparent; -fx-focus-color: transparent;");
                boutonSimulation.setGraphic(icon);
                Tooltip tool = new Tooltip("Stopper une simulation !");
                boutonSimulation.setTooltip(tool);
                this.boutonSimulation.setOnAction(actionEvent -> monde.lavageDesClients());
            } else {
                Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/play.png")), tC.getTailleBouton(), tC.getTailleBouton(), true, true);
                ImageView icon = new ImageView(image);
                boutonSimulation.setStyle("-fx-background-color:transparent; -fx-focus-color: transparent;");
                boutonSimulation.setGraphic(icon);
                Tooltip tool = new Tooltip("Réaliser une simulation !");
                boutonSimulation.setTooltip(tool);
                this.boutonSimulation.setOnAction(actionEvent -> {
                    try {
                        monde.simuler();
                    } catch (MondeException | PasUnGuichetException e) {
                        lancerFenetreErreurSimu(e);
                    }
                });
            }
        };
        if (Platform.isFxApplicationThread()) {
            command.run();
        } else {
            Platform.runLater(command);
        }
    }

    private void lancerFenetreErreurSimu(TwiskException e) {
        TailleComposants tc = TailleComposants.getInstance();
        Alert dialog = new Alert(Alert.AlertType.ERROR);
        dialog.setTitle("MondeException");
        dialog.setHeaderText("Impossible de simuler le monde !");
        dialog.setContentText("Erreur : " + e.getMessage());
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/warning.png")), tc.getTailleIcons(), tc.getTailleIcons(), true, true);
        ImageView icon = new ImageView(image);
        dialog.setGraphic(icon);
        dialog.show();
        //Le chronomètre
        PauseTransition pt = new PauseTransition(Duration.seconds(5));
        pt.setOnFinished(Event -> dialog.close());
        pt.play();
    }
}
