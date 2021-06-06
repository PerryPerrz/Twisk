package twisk.vues;

import javafx.scene.layout.HBox;
import twisk.designPattern.Observateur;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;
import twisk.outils.CouleurComposants;
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
        TailleComposants tc = TailleComposants.getInstance();
        CouleurComposants cc = CouleurComposants.getInstance();

        this.setMinSize(tc.getLargAct(), tc.getHautAct()); //Taille min de l'activité
        this.setMaxSize(tc.getLargAct(), tc.getHautAct()); //Taille max de l'activité
        //box.setPrefSize(TailleComposants.getInstance().getLargAct(), TailleComposants.getInstance().getHautAct());
        box.setMinSize(tc.getLargAct() - tc.getMargeSelection(), tc.getHautAct() - tc.getHautLabelEtape() - tc.getMargeSelection());
        box.setMaxSize(tc.getLargAct() - tc.getMargeSelection(), tc.getHautAct() - tc.getHautLabelEtape() - tc.getMargeSelection());

        this.label.setStyle("-fx-text-fill: " + cc.getCouleurLabelActivite());
        box.setStyle("-fx-border-color: " + cc.getCouleurActivite() + "; -fx-background-insets: 0 0 -1 0, 0, 1, 2; -fx-background-radius: 3px, 3px, 2px, 1px; -fx-border-width: 3px; -fx-background-color: " + cc.getCouleurBackgroudnActivite() + ";");

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
