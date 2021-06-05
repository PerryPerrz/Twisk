package twisk.simulation;

import twisk.monde.Etape;

import java.util.Arrays;
import java.util.Iterator;

/**
 * La classe GestionnaireClients.
 */
public class GestionnaireClients implements Iterable<Client> {
    private final Client[] clients;
    private final int nbClients;

    /**
     * Instancie un nouveau Gestionnaire clients.
     *
     * @param nbClients le nombre de clients
     */
    public GestionnaireClients(int nbClients) {
        clients = new Client[nbClients];
        this.nbClients = nbClients;
    }

    /**
     * Définit les clients.
     *
     * @param tabClients le tableau contenant les numéros des clients
     */
    public void setClients(int... tabClients) {
        for (int i = 0; i < tabClients.length; i++)
            clients[i] = new Client(tabClients[i]);
    }

    /**
     * Définit l'étape dans laquelle se trouve un certain client ainsi que son rang.
     *
     * @param numeroClient le numero du client
     * @param etape        l'etape
     * @param rang         le rang
     */
    public void allerA(int numeroClient, Etape etape, int rang) {
        for (Client client : clients)
            if (client.getNumeroClient() == numeroClient)
                client.allerA(etape, rang);
    }

    /**
     * Nettoie les clients.
     */
    public void reset() {
        Arrays.fill(clients, null); //Mets toutes les cases du tableau à la valeur null
    }

    @Override
    public Iterator<Client> iterator() {
        return Arrays.stream(clients).iterator();
    }

    /**
     * Retourne le client à l'indice i.
     * Nécessaire pour les tests.
     *
     * @param i l'indice
     * @return le client
     */
    public Client getClientI(int i) {
        return clients[i];
    }

    /**
     * Fonction qui retourne le nombre de clients de la simulation actuelle.
     *
     * @return le nombre de clients
     */
    public int getNbClients() {
        return nbClients;
    }
}
