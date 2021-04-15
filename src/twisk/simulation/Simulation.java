package twisk.simulation;

import twisk.monde.Monde;
import twisk.outils.KitC;

/**
 * La classe Simulation.
 */
public class Simulation {
    private int nbClients;
    private GestionnaireClients gestCli;

    /**
     * Constructeur de la classe Simulation.
     */
    public Simulation() {
    }

    /**
     * Lance la simulation.
     *
     * @param monde le monde utilisé pour la simulation
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
        this.gestCli = new GestionnaireClients(nbClients);
        gestCli.setClients(numProc);

        //On affiche les clients dans les étapes
        int[] tabClientsEtapes = ou_sont_les_clients(monde.nbEtapes(), nbClients);
        while (nbClients != tabClientsEtapes[monde.getNumSasSortie() * nbClients + monde.getNumSasSortie()]) {
            tabClientsEtapes = ou_sont_les_clients(monde.nbEtapes(), nbClients);
            for (int i = 0; i < monde.nbEtapes(); i++) {
                int temp = tabClientsEtapes[(i * nbClients) + i];  //Variable qui permet de réduire la charge visuelle et les accès au tableau (contient les clients dans l'étape actuelle)
                System.out.print("" + monde.getNomEtapeI(i) + ", " + temp + " clients : ");
                for (int j = 0; j < temp; j++) {//On parcourt les clients qu'il y a dans l'étape.
                    System.out.print(tabClientsEtapes[(i * nbClients) + i + j + 1] + ", ");
                    gestCli.allerA(tabClientsEtapes[(i * nbClients) + i + j + 1], monde.getEtapeI(i), i);
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
     * Fonction définie dans le fichier ressources/codeC/def.h, fonction qui démarre la simulation au niveau des clients.
     *
     * @param nbEtapes          le nombre d'étapes
     * @param nbServices        le nombre de services
     * @param nbClients         le nombre de clients
     * @param tabJetonsServices le tableau contenant le nombre de jetons des services
     * @return Un tableau d'entiers contenant les pids des clients
     */
    public native int[] start_simulation(int nbEtapes, int nbServices, int nbClients, int[] tabJetonsServices);

    /**
     * Fonction définie dans le fichier ressources/codeC/def.h, fonction qui renvoie la position des clients à l'instant actuel.
     *
     * @param nbEtapes  le nombre d'étapes
     * @param nbClients le nombre de clients
     * @return Un tableau d'entiers contenant les positions des clients
     */
    public native int[] ou_sont_les_clients(int nbEtapes, int nbClients);

    /**
     * Nettoyage des clients.
     */
    public native void nettoyage();

    /**
     * Définit le nombre de clients.
     *
     * @param nbClients le nombre de clients
     */
    public void setNbClients(int nbClients) {
        this.nbClients = nbClients;
    }
}
