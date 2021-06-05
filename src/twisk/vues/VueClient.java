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

    public VueClient(MondeIG monde, Client client) {
        this.monde = monde;
        this.client = client;
        CouleurComposants cc = CouleurComposants.getInstance();
        this.setFill(Paint.valueOf(cc.getCouleurClient()));
        if (this.client.getEtape().estUnSasEntree() || this.client.getEtape().estUnSasSortie())
            this.setVisible(false);
        else {
            e = monde.getCorE().get(this.client.getEtape());
            this.setRadius(TailleComposants.getInstance().getRad());
        }
    }

    public EtapeIG getE() {
        return e;
    }

    @Override
    public void reagir() {

    }
}
