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

        //Animation
        if (!monde.simulationACommencee()) {
            new Pulse(this).play();
        }

        this.setOnMouseClicked(ActionEvent -> {
            try {
                monde.creationArc(pdc);
            } catch (ArcAlreadyCreateException aace) {
                this.gestionDesAlertes(aace, "Impossible de créer cet arc!", "Erreur : On ne peut pas créer un arc déjà créé!\n Veuillez ré-essayer", "warning");
            } catch (CreateArcWithEndPdcException cawepe) {
                this.gestionDesAlertes(cawepe, "Impossible de créer cet arc!", "Erreur : Un arc ne peut pas partir du point d'arrivé d'un autre arc!\n Veuillez ré-essayer", "cone");
            } catch (SameActivityException sae) {
                this.gestionDesAlertes(sae, "Impossible de créer cet arc!", "Erreur : Vous ne pouvez pas créer d'arcs entre 2 points de controle identiques!\nVeuillez ré-essayer", "siren");
            } catch (CreateLoopException cle) {
                this.gestionDesAlertes(cle, "Impossible de créer cet arc!", "Erreur : Vous ne pouvez pas créer de circuit entre deux étapes !\nVeuillez ré-essayer", "warning");
            } catch (PasUnGuichetException e) {
                this.gestionDesAlertes(e, "Impossible de changer le nombre de jetons d'une activité !", "Erreur : L'étape choisie n'est pas un guichet\nVeuillez ré-essayer", "warning");
            } catch (WrongDirectionException e) {
                this.gestionDesAlertes(e, "Impossible de créer un arc vers un guichet alors qu'un autre arc va dans le sens opposé !", "Erreur : Mauvais sens de création d'arc \nVeuillez ré-essayer", "warning");
            } catch (TwiskException e) {
                e.printStackTrace();
            }
        });
        monde.ajouterObservateur(this);
    }

    /**
     * Procédure qui permet de gèrer plus facilement et plus proprement les alertes
     *
     * @param e         l'exception
     * @param hText     le headerText de la boite d'alerte
     * @param cText     le contentText de la boite d'alerte
     * @param nameImage le nom de l'image sans son extension
     */
    public void gestionDesAlertes(TwiskException e, String hText, String cText, String nameImage) {
        Alert dia = new Alert(Alert.AlertType.ERROR);
        dia.setTitle(e.getClass().getSimpleName());
        dia.setHeaderText(hText);
        dia.setContentText(cText);
        TailleComposants tc = TailleComposants.getInstance();
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/" + nameImage + ".png")), tc.getTailleIcons(), tc.getTailleIcons(), true, true);
        ImageView icon = new ImageView(image);
        dia.setGraphic(icon);
        dia.show();
        //Le chronomètre
        PauseTransition pt = new PauseTransition(Duration.seconds(5));
        pt.setOnFinished(Event -> dia.close());
        pt.play();
    }

    @Override
    public void reagir() {
    }
}
