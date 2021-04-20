package twisk.vues;

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
import twisk.designPattern.Observateur;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;
import twisk.outils.TailleComposants;

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
        HBox hBox = new HBox();
        monde.ajouterObservateur(this);
        this.setId(etape.getIdentifiant()); //L'id de VueEtape = l'id de Etape
        label = new Label(this.etape.getNom());
        this.setOnDragDetected(this::setMouse);

        TailleComposants tc = TailleComposants.getInstance();

        hBox.getChildren().add(label);
        if (this.etape.estUneEntree()) {
            Image image = new Image(getClass().getResourceAsStream("/twisk/ressources/images/entree.png"), tc.getTailleIcons3(), tc.getTailleIcons3(), true, true);
            ImageView icon = new ImageView(image);
            hBox.getChildren().add(icon);
        }
        if (this.etape.estUneSortie()) {
            Image image = new Image(getClass().getResourceAsStream("/twisk/ressources/images/sortie.png"), tc.getTailleIcons3(), tc.getTailleIcons3(), true, true);
            ImageView icon = new ImageView(image);
            hBox.getChildren().add(icon);
        }
        this.getChildren().add(hBox);

        this.setMinSize(tc.getLargAct(), tc.getHautAct()); //Taille min de l'activité
        this.setMaxSize(tc.getLargAct(), tc.getHautAct()); //Taille max de l'activité
    }

    /**
     * Sets mouse.
     *
     * @param mouseEvent the mouse event
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
}
