package twisk.vues;

import javafx.geometry.Insets;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import twisk.designPattern.Observateur;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;
import twisk.outils.CouleurComposants;
import twisk.outils.TailleComposants;

/**
 * La classe VueGuichetIG
 */
public class VueGuichetIG extends VueEtapeIG implements Observateur {
    private final FlowPane flowPane;
    private int paneLibre;

    /**
     * Constructeur de la classe VueGuichetIG
     *
     * @param monde le monde
     * @param etape l'etape
     */
    public VueGuichetIG(MondeIG monde, EtapeIG etape) {
        super(monde, etape);
        if (etape.siEstUnGuichetGetVersLaDroite() == null || etape.siEstUnGuichetGetVersLaDroite())
            paneLibre = 0;
        else
            paneLibre = monde.getNbClients() - etape.siEstUnGuichetGetNbJetons() - 1;
        flowPane = new FlowPane();

        TailleComposants tc = TailleComposants.getInstance();
        CouleurComposants cc = CouleurComposants.getInstance();

        this.label.setStyle("-fx-text-fill: " + cc.getCouleurLabelGuichet());
        flowPane.setStyle("-fx-border-color: " + cc.getCouleurBorderGuichet() + "; -fx-background-insets: 0 0 -1 0, 0, 1, 2; -fx-background-radius: 3px, 3px, 2px, 1px; -fx-border-width: 3px; -fx-background-color:" + cc.getCouleurBackgroundGuichet() + " ;");

        this.setMinSize(tc.getLargGuichet(), tc.getHautGuichet()); //Taille min du guichet
        this.setMaxSize(tc.getLargGuichet(), tc.getHautGuichet() * 4); //Taille max du guichet
        flowPane.setMinSize(TailleComposants.getInstance().getLargGuichet() - tc.getMargeSelection(), TailleComposants.getInstance().getHautGuichet() - tc.getHautLabelEtape() - tc.getMargeSelection());
        flowPane.setMaxSize(TailleComposants.getInstance().getLargGuichet() - tc.getMargeSelection(), (TailleComposants.getInstance().getHautGuichet() - tc.getHautLabelEtape()) * 4 - tc.getMargeSelection());

        flowPane.setPadding(new Insets(5));
        flowPane.setHgap(5);
        flowPane.setVgap(5);

        this.getChildren().add(flowPane);
        this.setOnMouseClicked(actionEvent -> monde.ajouterEtapeSelectionnee(this.etape));
    }

    @Override
    public void ajouterVueClient(VueClient viewC) {
        //Utilisation d'un StackPane pour pouvoir afficher les clients au centre du rectangle.
        StackPane sp = new StackPane();
        sp.setStyle("-fx-border-color: " + CouleurComposants.getInstance().getCouleurBorderGuichet() + "; -fx-border-width: 3px; -fx-background-color:" + CouleurComposants.getInstance().getCouleurBackgroundGuichet() + ";");
        sp.setPrefSize(TailleComposants.getInstance().getRadClient() * 3, TailleComposants.getInstance().getRadClient() * 6);
        flowPane.getChildren().add(sp);
        sp.getChildren().add(viewC);
        if (etape.siEstUnGuichetGetVersLaDroite() == null || etape.siEstUnGuichetGetVersLaDroite())
            paneLibre++;
        else
            paneLibre--;
    }

    /**
     * Fonction qui retourne le nombre de vue clients contenues dans cette activités
     *
     * @return le nombre de vue client.
     */
    public int getNbVueClients() {
        return flowPane.getChildren().size();
    }

    @Override
    public void reagir() {
    }
}
