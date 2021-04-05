package twisk.simulation;

import twisk.monde.Monde;
import twisk.outils.KitC;

/**
 * The type Simulation.
 */
public class Simulation {
    private int nbClients;

    /**
     * Instantiates a new Simulation.
     */
    public Simulation() {
    }

    /**
     * Simuler.
     *
     * @param monde the monde
     */
    public void simuler(Monde monde) {
        System.out.println(monde.toString() + "\n");
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
        System.out.print("PID des clients : ");
        for (int i = 0; i < nbClients; i++) {
            System.out.print(numProc[i] + ", ");
        }
        System.out.println("\n");

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

    /**
     * Start simulation int [ ].
     *
     * @param nbEtapes          the nb etapes
     * @param nbServices        the nb services
     * @param nbClients         the nb clients
     * @param tabJetonsServices the tab jetons services
     * @return the int [ ]
     */
    public native int[] start_simulation(int nbEtapes, int nbServices, int nbClients, int[] tabJetonsServices);

    /**
     * Ou sont les clients int [ ].
     *
     * @param nbEtapes  the nb etapes
     * @param nbClients the nb clients
     * @return the int [ ]
     */
    public native int[] ou_sont_les_clients(int nbEtapes, int nbClients);

    /**
     * Nettoyage.
     */
    public native void nettoyage();

    /**
     * Sets nb clients.
     *
     * @param nbClients the nb clients
     */
    public void setNbClients(int nbClients) {
        this.nbClients = nbClients;
    }
}
