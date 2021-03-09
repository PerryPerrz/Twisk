package twisk.simulation;

import twisk.monde.Monde;
import twisk.outils.KitC;

public class Simulation {

    public Simulation() {
    }

    public void simuler(Monde monde) {
        KitC kitC = new KitC();
        System.out.println(monde);
        monde.toC();
        kitC.creerEnvironnement();
    }
}
