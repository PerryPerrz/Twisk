package twisk.simulation;

import javafx.concurrent.Task;
import twisk.designPattern.SujetObserve;
import twisk.monde.Monde;
import twisk.outils.CreationLogs;
import twisk.outils.FabriqueNumero;
import twisk.outils.GestionnaireThreads;
import twisk.outils.KitC;

/**
 * La classe Simulation.
 */
public class Simulation extends SujetObserve {
    private int nbClients;
    private GestionnaireClients gestCli;
    private boolean enCoursDeSimulation;

    /**
     * Constructeur de la classe Simulation.
     */
    public Simulation() {
        super();
        this.enCoursDeSimulation = false;
    }

    /**
     * Lance la simulation.
     *
     * @param monde le monde utilisé pour la simulation
     */
    public void simuler(Monde monde) {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
                KitC kitC = null;
                try {
                    enCoursDeSimulation = true;
                    FabriqueNumero fab = FabriqueNumero.getInstance();
                    CreationLogs creaLogs = CreationLogs.getInstance();
                    int numeroLog = fab.getNumeroLog();
                    creaLogs.ecrireContenuDansLog(numeroLog, monde.toString() + "\n\n");
                    kitC = new KitC();
                    kitC.creerEnvironnement();
                    kitC.creerFichier(monde.toC());
                    kitC.compiler();
                    kitC.construireLaLibrairie();
                    //On charge la libraire C pour utiliser les fonctions natives définies ci-dessous
                    System.load("/tmp/twisk/libTwisk" + fab.consulterNumeroLibraire() + ".so");
                    int[] tabJetonsGuichet = new int[monde.nbGuichets()];
                    for (int i = 0; i < monde.nbGuichets(); i++)
                        tabJetonsGuichet[i] = monde.getNbTicketsGuichetI(i + 1); //num de Sémaphore commence à 1
                    int[] numProc = start_simulation(monde.nbEtapes(), monde.nbGuichets(), nbClients, tabJetonsGuichet);
                    creaLogs.ecrireContenuDansLog(numeroLog, "PID des clients : ");
                    for (int i = 0; i < nbClients; i++) {
                        creaLogs.ecrireContenuDansLog(numeroLog, numProc[i] + ", ");
                    }
                    creaLogs.ecrireContenuDansLog(numeroLog, "\n\n");
                    gestCli = new GestionnaireClients(nbClients);
                    gestCli.setClients(numProc);

                    //On affiche les clients dans les étapes
                    int[] tabClientsEtapes = ou_sont_les_clients(monde.nbEtapes(), nbClients);
                    while (nbClients != tabClientsEtapes[monde.getNumSasSortie() * nbClients + monde.getNumSasSortie()]) {
                        tabClientsEtapes = ou_sont_les_clients(monde.nbEtapes(), nbClients);
                        for (int i = 0; i < monde.nbEtapes(); i++) {
                            int temp = tabClientsEtapes[(i * nbClients) + i];  //Variable qui permet de réduire la charge visuelle et les accès au tableau (contient les clients dans l'étape actuelle)
                            creaLogs.ecrireContenuDansLog(numeroLog, "" + monde.getNomEtapeI(i) + ", " + temp + " clients : ");
                            for (int j = 0; j < temp; j++) {//On parcourt les clients qu'il y a dans l'étape.
                                creaLogs.ecrireContenuDansLog(numeroLog, tabClientsEtapes[(i * nbClients) + i + j + 1] + ", ");
                                gestCli.allerA(tabClientsEtapes[(i * nbClients) + i + j + 1], monde.getEtapeI(i), j);
                            }
                            creaLogs.ecrireContenuDansLog(numeroLog, "\n");
                        }
                        notifierObservateurs();
                        creaLogs.ecrireContenuDansLog(numeroLog, "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                        Thread.sleep(500);
                    }
                    creaLogs.ecrireContenuDansLog(numeroLog, "Simulation terminee, tous les clients sont dans le sas de sortie !\n");
                    kitC.tuerLesProcessusC(gestCli);
                    gestCli.reset();
                    nettoyage();
                    enCoursDeSimulation = false;
                    notifierObservateurs();
                } catch (InterruptedException e) {
                    kitC.tuerLesProcessusC(gestCli);
                    gestCli.reset();
                    nettoyage();
                    enCoursDeSimulation = false;
                    notifierObservateurs();
                }
                return null;
            }
        };
        //On lance la fonction dans un Thread différent du Thread graphique car il y a trop de fonctions s'exécutant en même temps
        GestionnaireThreads.getInstance().lancer(task);
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

    /**
     * Fonction qui retourne le gestionnaire de clients de la simulation
     *
     * @return le gestionnaire de client
     */
    public GestionnaireClients getGestCli() {
        return gestCli;
    }

    /**
     * Fonction qui retourne vrai si la simulation est en cours.
     *
     * @return un booléen
     */
    public boolean isEnCoursDeSimulation() {
        return enCoursDeSimulation;
    }

}
