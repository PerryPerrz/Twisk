package twisk.vues;

import animatefx.animation.Pulse;
import javafx.scene.Cursor;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import twisk.designPattern.Observateur;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;
import twisk.outils.CouleurComposants;
import twisk.outils.TailleComposants;

import java.util.Objects;

/**
 * La classe de VueEtapeIG.
 */
public abstract class VueEtapeIG extends VBox implements Observateur {
    /**
     * Le Monde.
     */
    protected final MondeIG monde;
    /**
     * L'Etape.
     */
    protected final EtapeIG etape;
    /**
     * Le Label.
     */
    protected final Label label;

    /**
     * Constructeur de la classe VueEtapeIG.
     *
     * @param monde le monde
     * @param etape l'etape
     */
    public VueEtapeIG(MondeIG monde, EtapeIG etape) {
        this.monde = monde;
        this.etape = etape;

        TailleComposants tc = TailleComposants.getInstance();
        CouleurComposants cc = CouleurComposants.getInstance();

        HBox hBox = new HBox();
        monde.ajouterObservateur(this);
        this.setId(etape.getIdentifiant()); //L'id de VueEtape = l'id de Etape
        label = new Label(this.etape.getNom());
        label.setFont(new Font("Serif", tc.getFont()));

        this.label.setMinHeight(tc.getHautLabelEtape());
        this.label.setMaxHeight(tc.getHautLabelEtape());

        this.setCursor(Cursor.HAND);
        if (!monde.simulationACommencee()) {
            //Animation
            new Pulse(this).play();
        }

        this.setOnDragDetected(this::setMouse);
        hBox.getChildren().add(label);
        if (this.etape.estUneEntree()) {
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/entree_visu.png")), tc.getTailleIcons3(), tc.getTailleIcons3(), true, true);
            ImageView icon = new ImageView(image);
            hBox.getChildren().add(icon);
        }
        if (this.etape.estUneSortie()) {
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/twisk/ressources/images/sortie_visu.png")), tc.getTailleIcons3(), tc.getTailleIcons3(), true, true);
            ImageView icon = new ImageView(image);
            hBox.getChildren().add(icon);
        }
        this.getChildren().add(hBox);
        relocate(etape.getPosX(), etape.getPosY());
        if (monde.isSelectionned(etape)) {
            setStyle("-fx-border-color: " + cc.getCouleurBorderEtapeIsSelected() + ";-fx-border-width: 2px;-fx-background-color: " + cc.getCouleurBackgroundEtapeIsSelected());
        }
    }

    /**
     * Sets mouse.
     *
     * @param mouseEvent le mouse event
     */
    public void setMouse(MouseEvent mouseEvent) {
        Dragboard dragboard = this.startDragAndDrop(TransferMode.MOVE); //Presse-papier qui contient les infos du drag'n drop
        WritableImage snapShot = this.snapshot(new SnapshotParameters(), null);
        dragboard.setDragView(snapShot);
        ClipboardContent content = new ClipboardContent(); //On créer un clipboard qui contient l'id de l'étape
        content.putString(etape.getIdentifiant());
        dragboard.setContent(content);
        mouseEvent.consume(); //Cet event est finit.
    }

    /**
     * Gets etape.
     *
     * @return l'étape de la VueEtape
     */
    public EtapeIG getEtape() {
        return etape;
    }

    /**
     * Ajouter vue client.
     *
     * @param viewC la VueCLient que l'on veut ajouter
     */
    public abstract void ajouterVueClient(VueClient viewC);
}
