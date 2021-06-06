package twisk.vues;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import twisk.designPattern.Observateur;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;
import twisk.outils.CouleurComposants;
import twisk.outils.TailleComposants;
import twisk.simulation.Client;

/**
 * La classe VueClient
 */
public class VueClient extends Circle implements Observateur {
    private final Client client;
    private final MondeIG monde;
    private EtapeIG e;

    /**
     * Constructeur de la classe VueClient
     *
     * @param monde  le monde
     * @param client le client
     */
    public VueClient(MondeIG monde, Client client) {
        this.monde = monde;
        this.client = client;
        CouleurComposants cc = CouleurComposants.getInstance();
        //On récupère le dernier chiffre du pid des client
        int nb = this.client.getNumeroClient() % 10;
        switch (nb) {
            case 0:
                this.setFill(Paint.valueOf("#E74C3C"));
                break;
            case 1:
                this.setFill(Paint.valueOf("#9B59B6"));
                break;
            case 2:
                this.setFill(Paint.valueOf("#2980B9"));
                break;
            case 3:
                this.setFill(Paint.valueOf("#16A085"));
                break;
            case 4:
                this.setFill(Paint.valueOf("#27AE60"));
                break;
            case 5:
                this.setFill(Paint.valueOf("#F39C12"));
                break;
            case 6:
                this.setFill(Paint.valueOf("#E67E22"));
                break;
            case 7:
                this.setFill(Paint.valueOf("#BDC3C7"));
                break;
            case 8:
                this.setFill(Paint.valueOf("#34495E"));
                break;
            case 9:
                this.setFill(Paint.valueOf("#2C3E50"));
                break;
        }
        if (this.client.getEtape().estUnSasEntree() || this.client.getEtape().estUnSasSortie())
            this.setVisible(false);
        else {
            e = monde.getCorE().get(this.client.getEtape());
            this.setRadius(TailleComposants.getInstance().getRadClient());
        }
    }

    /**
     * Gets e.
     *
     * @return l'étape du client
     */
    public EtapeIG getE() {
        return e;
    }

    @Override
    public void reagir() {

    }
}
