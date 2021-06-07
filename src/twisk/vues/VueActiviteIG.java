package twisk.vues;

import javafx.geometry.Insets;
import javafx.scene.layout.FlowPane;
import twisk.designPattern.Observateur;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;
import twisk.outils.CouleurComposants;
import twisk.outils.TailleComposants;

/**
 * La classe VueActiviteIG.
 */
public class VueActiviteIG extends VueEtapeIG implements Observateur {
    private final FlowPane flowPane;

    /**
     * Constructeur de la classe VueActiviteIG
     *
     * @param monde le monde
     * @param etape l'etape
     */
    public VueActiviteIG(MondeIG monde, EtapeIG etape) {
        super(monde, etape);
        flowPane = new FlowPane();
        TailleComposants tc = TailleComposants.getInstance();
        CouleurComposants cc = CouleurComposants.getInstance();

        this.setMinSize(tc.getLargAct(), tc.getHautAct()); //Taille min de l'activité
        this.setMaxSize(tc.getLargAct(), tc.getHautAct() * 2); //Taille max de l'activité
        //box.setPrefSize(TailleComposants.getInstance().getLargAct(), TailleComposants.getInstance().getHautAct());
        flowPane.setMinSize(tc.getLargAct() - tc.getMargeSelection(), tc.getHautAct() - tc.getHautLabelEtape() - tc.getMargeSelection());
        flowPane.setMaxSize(tc.getLargAct() - tc.getMargeSelection(), (tc.getHautAct() - tc.getHautLabelEtape()) * 2 - tc.getMargeSelection());
        flowPane.setPadding(new Insets(tc.getEcartHV()));
        flowPane.setHgap(tc.getEcartHV());
        flowPane.setVgap(tc.getEcartHV());

        this.label.setStyle("-fx-text-fill: " + cc.getCouleurLabelActivite());
        flowPane.setStyle("-fx-border-color: " + cc.getCouleurActivite() + "; -fx-background-insets: 0 0 -1 0, 0, 1, 2; -fx-background-radius: 3px, 3px, 2px, 1px; -fx-border-width: 3px; -fx-background-color: " + cc.getCouleurBackgroudnActivite() + ";");

        this.getChildren().add(flowPane);
        this.setOnMouseClicked(actionEvent -> monde.ajouterEtapeSelectionnee(this.etape));
    }

    @Override
    public void ajouterVueClient(VueClient viewC) {
        flowPane.getChildren().add(viewC);
    }

    @Override
    public void reagir() {
    }
}
