package twisk.simulation;

import twisk.monde.Monde;
import twisk.outils.KitC;

public class Simulation {
    private int nbClients;

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
        int[] tabJetonsGuichet = new int[monde.nbGuichets()];
        for (int i = 0; i < monde.nbGuichets(); i++)
            tabJetonsGuichet[i] = monde.getNbTicketsGuichetI(i);
        int[] numProc = start_simulation(monde.nbEtapes(), monde.nbGuichets(), nbClients, tabJetonsGuichet);
        for (int i = 0; i < nbClients; i++) {
            System.out.print(numProc[i] + ", ");
        }
        System.out.println();

        //On affiche les clients dans les étapes
        int[] tabClientsEtapes = ou_sont_les_clients(monde.nbEtapes(), nbClients);
        while (nbClients != tabClientsEtapes[monde.getNumSasSortie() * nbClients + monde.getNumSasSortie()]) {
            tabClientsEtapes = ou_sont_les_clients(monde.nbEtapes(), nbClients);
            for (int i = 0; i < monde.nbEtapes(); i++) {
                int temp = tabClientsEtapes[(i * nbClients) + i];  //Variable qui permet de réduire la charge visuelle et les accès au tableau (contient les clients dans l'étape actuelle)
                System.out.print("" + monde.getNomEtapeI(i) + ", " + temp + " clients : ");
                for (int j = 0; j < temp; j++) {
                    System.out.print(tabClientsEtapes[(i * nbClients) + i + j + 1] + ", ");
                }
                System.out.println();
            }
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Simulation terminee, tous les clients sont dans le sas de sortie !");
        nettoyage();
    }

    public native int[] start_simulation(int nbEtapes, int nbServices, int nbClients, int[] tabJetonsServices);

    public native int[] ou_sont_les_clients(int nbEtapes, int nbClients);

    public native void nettoyage();

    public void setNbClients(int nbClients) {
        this.nbClients = nbClients;
    }
}
