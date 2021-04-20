package twisk.vues;

import javafx.animation.PauseTransition;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import twisk.designPattern.Observateur;
import twisk.exceptions.ArcAlreadyCreateException;
import twisk.exceptions.CreateArcWithEndPdcException;
import twisk.exceptions.SameActivityException;
import twisk.exceptions.TwiskException;
import twisk.mondeIG.MondeIG;
import twisk.mondeIG.PointDeControleIG;
import twisk.outils.TailleComposants;

/**
 * La classe VuePointDeControleIG.
 */
public class VuePointDeControleIG extends Circle implements Observateur {
    private final MondeIG monde;
    private final PointDeControleIG pdc;

    /**
     * Constructeur de la classe VuePointDeControleIG.
     *
     * @param monde le monde
     * @param pdc   le pdc
     */
    public VuePointDeControleIG(MondeIG monde, PointDeControleIG pdc) {
        TailleComposants tc = TailleComposants.getInstance();
        this.monde = monde;
        monde.ajouterObservateur(this);
        this.pdc = pdc;
        this.setFill(Color.SLATEBLUE);
        this.setRadius(tc.getRad());
        this.setOnMouseClicked(ActionEvent -> {
            try {
                monde.creationArc(pdc);
            } catch (ArcAlreadyCreateException aace) {
                Alert dialog = new Alert(Alert.AlertType.ERROR);
                dialog.setTitle("ArcAlreadyCreateException");
                dialog.setHeaderText("Impossible de créer cet arc!");
                dialog.setContentText("Erreur : On ne peut pas créer un arc déjà créer!\n" +
                        "Veuillez ré-essayer");
                Image image = new Image(getClass().getResourceAsStream("/twisk/ressources/images/warning.png"), tc.getTailleIcons(), tc.getTailleIcons(), true, true);
                ImageView icon = new ImageView(image);
                dialog.setGraphic(icon);
                dialog.show();
                //Le chronomètre
                PauseTransition pt = new PauseTransition(Duration.seconds(5));
                pt.setOnFinished(Event -> dialog.close());
                pt.play();
            } catch (CreateArcWithEndPdcException cawepe) {
                Alert dialog = new Alert(Alert.AlertType.ERROR);
                dialog.setTitle("CreateArcWithEndPdcException");
                dialog.setHeaderText("Impossible de créer cet arc!");
                dialog.setContentText("Erreur : Un arc ne peut pas partir du point d'arrivé d'un autre arc!\n Veuillez ré-essayer");
                Image image = new Image(getClass().getResourceAsStream("/twisk/ressources/images/cone.png"), tc.getTailleIcons(), tc.getTailleIcons(), true, true);
                ImageView icon = new ImageView(image);
                dialog.setGraphic(icon);
                dialog.show();
                //Le chronomètre
                PauseTransition pt = new PauseTransition(Duration.seconds(5));
                pt.setOnFinished(Event -> dialog.close());
                pt.play();
            } catch (SameActivityException sae) {
                Alert dialog = new Alert(Alert.AlertType.ERROR);
                dialog.setTitle("SameActivityException");
                dialog.setHeaderText("Impossible de créer cet arc!");
                dialog.setContentText("Erreur : Vous ne pouvez pas créer d'arcs entre 2 points de controle identiques!\nVeuillez ré-essayer");
                Image image = new Image(getClass().getResourceAsStream("/twisk/ressources/images/siren.png"), tc.getTailleIcons(), tc.getTailleIcons(), true, true);
                ImageView icon = new ImageView(image);
                dialog.setGraphic(icon);
                dialog.show();
                //Le chronomètre
                PauseTransition pt = new PauseTransition(Duration.seconds(5));
                pt.setOnFinished(Event -> dialog.close());
                pt.play();
            } catch (TwiskException e) {
                e.printStackTrace();
            }
        });
        monde.ajouterObservateur(this);
    }

    @Override
    public void reagir() {
        //On change les coordonnées du cercle à la nouvelle place du pdc
        this.setCenterX(this.pdc.getCentreX());
        this.setCenterY(this.pdc.getCentreY());
    }
}
