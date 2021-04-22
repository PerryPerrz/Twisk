package twisk.vues;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import twisk.designPattern.Observateur;
import twisk.mondeIG.MondeIG;
import twisk.outils.TailleComposants;


/**
 * La classe VueOutils.
 */
public class VueOutils extends TilePane implements Observateur {
    private final MondeIG monde;
    private final Button boutonActivite;
    private final Button boutonGuichet;

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
        boutonActivite.setOnAction(actionEvent -> monde.ajouter("Activite"));
        boutonGuichet.setOnAction(actionEvent -> monde.ajouter("Guichet"));
        Tooltip tool = new Tooltip("Ajouter une activité !");
        Tooltip tool2 = new Tooltip("Ajouter un guichet !");
        boutonActivite.setTooltip(tool);
        boutonGuichet.setTooltip(tool2);
        TailleComposants tC = TailleComposants.getInstance();
        Image image = new Image(getClass().getResourceAsStream("/twisk/ressources/images/add_activite.png"), tC.getTailleBouton(), tC.getTailleBouton(), true, true); //Donne le chemin à partir de src
        Image image2 = new Image(getClass().getResourceAsStream("/twisk/ressources/images/add_guichet.png"), tC.getTailleBouton(), tC.getTailleBouton(), true, true);
        ImageView icon = new ImageView(image);
        ImageView icon2 = new ImageView(image2);
        boutonActivite.setStyle("-fx-background-color:transparent; -fx-focus-color: transparent;");
        boutonActivite.setGraphic(icon);
        boutonGuichet.setStyle("-fx-background-color:transparent; -fx-focus-color: transparent;");
        boutonGuichet.setGraphic(icon2);
        this.getChildren().addAll(boutonActivite, boutonGuichet);
    }

    @Override
    public void reagir() {

    }
}
