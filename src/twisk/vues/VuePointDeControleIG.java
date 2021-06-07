package twisk.vues;

import animatefx.animation.Pulse;
import javafx.animation.PauseTransition;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import twisk.designPattern.Observateur;
import twisk.exceptions.*;
import twisk.mondeIG.MondeIG;
import twisk.mondeIG.PointDeControleIG;
import twisk.outils.CouleurComposants;
import twisk.outils.TailleComposants;

import java.util.Objects;

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
        CouleurComposants cc = CouleurComposants.getInstance();
        this.monde = monde;
        monde.ajouterObservateur(this);
        this.pdc = pdc;
        this.setFill(Paint.valueOf(cc.getCouleurPointDeControle()));
        this.setRadius(tc.getRad());
        this.setCursor(Cursor.HAND);
        this.setCenterX(this.pdc.getCentreX());
        this.setCenterY(this.pdc.getCentreY());

        if (!monde.simulationACommencee()) {
            //Animation
            new Pulse(this).play();
        }

        this.setOnMouseClicked(ActionEvent -> {
            try {
                monde.creationArc(pdc);
            } catch (ArcAlreadyCreateException aace) {
                Alert dialog = new Alert(Alert.AlertType.ERROR);
                dialog.setTitle("ArcAlreadyCreateException");
                dialog.setHeaderText("Impossible de créer cet arc!");
                dialog.setContentText("Erreur : On ne peut pas créer un arc déjà créer!\n" +
                        "Veuillez ré-essayer");
                Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/warning.png")), tc.getTailleIcons(), tc.getTailleIcons(), true, true);
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
                Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/cone.png")), tc.getTailleIcons(), tc.getTailleIcons(), true, true);
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
                Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/siren.png")), tc.getTailleIcons(), tc.getTailleIcons(), true, true);
                ImageView icon = new ImageView(image);
                dialog.setGraphic(icon);
                dialog.show();
                //Le chronomètre
                PauseTransition pt = new PauseTransition(Duration.seconds(5));
                pt.setOnFinished(Event -> dialog.close());
                pt.play();
            } catch (CreateLoopException cle) {
                Alert dialog = new Alert(Alert.AlertType.ERROR);
                dialog.setTitle("CreateLoopException");
                dialog.setHeaderText("Impossible de créer cet arc!");
                dialog.setContentText("Erreur : Vous ne pouvez pas créer de circuit entre deux étapes !\nVeuillez ré-essayer");
                Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/warning.png")), tc.getTailleIcons(), tc.getTailleIcons(), true, true);
                ImageView icon = new ImageView(image);
                dialog.setGraphic(icon);
                dialog.show();
                //Le chronomètre
                PauseTransition pt = new PauseTransition(Duration.seconds(5));
                pt.setOnFinished(Event -> dialog.close());
                pt.play();
            } catch (PasUnGuichetException e) {
                Alert dia = new Alert(Alert.AlertType.ERROR);
                dia.setTitle("PasUnGuichetException");
                dia.setHeaderText("Impossible de changer le nombre de jetons d'une activité !");
                dia.setContentText("Erreur : L'étape choisie n'est pas un guichet\n" +
                        "Veuillez ré-essayer");
                Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/warning.png")), tc.getTailleIcons(), tc.getTailleIcons(), true, true);
                ImageView icon2 = new ImageView(image2);
                dia.setGraphic(icon2);
                dia.show();
                //Le chronomètre
                PauseTransition pt = new PauseTransition(Duration.seconds(5));
                pt.setOnFinished(Event -> dia.close());
                pt.play();
            } catch (WrongDirectionException e) {
                Alert dia = new Alert(Alert.AlertType.ERROR);
                dia.setTitle("WrongDirectionException");
                dia.setHeaderText("Impossible de créer un arc vers un guichet alors qu'un autre arc va dans le sens opposé !");
                dia.setContentText("Erreur : Mauvais sens de création d'arc \n" +
                        "Veuillez ré-essayer");
                Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/warning.png")), tc.getTailleIcons(), tc.getTailleIcons(), true, true);
                ImageView icon2 = new ImageView(image2);
                dia.setGraphic(icon2);
                dia.show();
                //Le chronomètre
                PauseTransition pt = new PauseTransition(Duration.seconds(5));
                pt.setOnFinished(Event -> dia.close());
                pt.play();
            } catch (TwiskException e) {
                e.printStackTrace();
            }
        });
        monde.ajouterObservateur(this);
    }
    

    @Override
    public void reagir() {
    }
}
