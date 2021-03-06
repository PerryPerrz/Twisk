package twisk.vues;

import javafx.geometry.Insets;
import javafx.scene.control.Tooltip;
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
            paneLibre = 10;
        flowPane = new FlowPane();

        TailleComposants tc = TailleComposants.getInstance();
        CouleurComposants cc = CouleurComposants.getInstance();

        this.label.setStyle("-fx-text-fill: " + cc.getCouleurLabelGuichet());
        flowPane.setStyle("-fx-border-color: " + cc.getCouleurBorderGuichet() + "; -fx-background-insets: 0 0 -1 0, 0, 1, 2; -fx-background-radius: 3px, 3px, 2px, 1px; -fx-border-width: 3px; -fx-background-color:" + cc.getCouleurBackgroundGuichet() + " ;");

        this.setMinSize(tc.getLargGuichet(), tc.getHautGuichet()); //Taille min du guichet
        this.setMaxSize(tc.getLargGuichet(), tc.getHautGuichet() * 4); //Taille max du guichet
        flowPane.setMinSize(TailleComposants.getInstance().getLargGuichet() - tc.getMargeSelection(), TailleComposants.getInstance().getHautGuichet() - tc.getHautLabelEtape() - tc.getMargeSelection());
        flowPane.setMaxSize(TailleComposants.getInstance().getLargGuichet() - tc.getMargeSelection(), (TailleComposants.getInstance().getHautGuichet() - tc.getHautLabelEtape()) * 4 - tc.getMargeSelection());

        flowPane.setPadding(new Insets(tc.getEcartHV()));
        flowPane.setHgap(tc.getEcartHV());
        flowPane.setVgap(tc.getEcartHV());

        this.getChildren().add(flowPane);
        this.setOnMouseClicked(actionEvent -> monde.ajouterEtapeSelectionnee(this.etape));
        label.setTooltip(new Tooltip("Nombre de tickets : " + etape.siEstUnGuichetGetNbJetons()));
    }

    @Override
    public void ajouterVueClient(VueClient viewC) {
        if (getNbVueClients() % 11 == 0) {
            for (int i = 0; i < 11; i++) {
                StackPane sp = new StackPane();
                sp.setStyle("-fx-border-color: " + CouleurComposants.getInstance().getCouleurBorderGuichet() + "; -fx-border-width: 3px; -fx-background-color:" + CouleurComposants.getInstance().getCouleurBackgroundGuichet() + ";");
                sp.setPrefSize(TailleComposants.getInstance().getRadClient() * 3, TailleComposants.getInstance().getRadClient() * 6);
                flowPane.getChildren().add(sp);
            }
        }
        //Utilisation d'un StackPane pour pouvoir afficher les clients au centre du rectangle.
        StackPane sp = (StackPane) flowPane.getChildren().get(paneLibre);
        sp.getChildren().add(viewC);
        if (etape.siEstUnGuichetGetVersLaDroite() == null || etape.siEstUnGuichetGetVersLaDroite())
            paneLibre++;
        else {
            if (paneLibre % 11 == 0)
                paneLibre += 21;
            else
                paneLibre--;
        }
    }

    /**
     * Fonction qui retourne le nombre de vue clients contenues dans cette activit??s
     *
     * @return le nombre de vue client.
     */
    public int getNbVueClients() {
        int res = 0;
        for (int i = 0; i < flowPane.getChildren().size(); i++) {
            StackPane sp = (StackPane) flowPane.getChildren().get(i);
            if (sp.getChildren().size() != 0)
                res++;
        }
        return res;
    }

    @Override
    public void reagir() {
    }
}
