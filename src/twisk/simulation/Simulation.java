package twisk.simulation;

import twisk.monde.Monde;
import twisk.outils.KitC;

public class Simulation {

    public Simulation() {
    }

    public void simuler(Monde monde) {
        KitC kitC = new KitC();
        kitC.creerEnvironnement();
        kitC.creerFichier(monde.toC());
        kitC.compiler();
        kitC.construireLaLibrairie();
        //On charge la libraire C pour utiliser les fonctions natives définies ci-dessous
        System.load("/tmp/twisk/libTwisk.so");
    }

    public native int[] start_simulation(int nbEtapes, int nbServices, int nbClients, int[] tabJetonsServices) ;
    public native int[] ou_sont_les_clients(int nbEtapes, int nbClients) ;
    public native void nettoyage();
}
