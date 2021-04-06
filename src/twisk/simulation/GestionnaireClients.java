package twisk.simulation;

import twisk.monde.Etape;

import java.util.Arrays;
import java.util.Iterator;

public class GestionnaireClients implements Iterable<Client> {
    private final Client[] clients;

    public GestionnaireClients(int nbClients) {
        clients = new Client[nbClients];
    }

    public void setClients(int... tabClients) {
        for (int i = 0; i < tabClients.length; i++)
            clients[i] = new Client(tabClients[i]);
    }

    public void allerA(int numeroClient, Etape etape, int rang) {
        for (Client client : clients)
            if (client.getNumeroClient() == numeroClient)
                client.allerA(etape, rang);
    }

    public void reset() {
        Arrays.fill(clients, null); //Mets toutes les cases du tableau à la valeur null
    }

    @Override
    public Iterator<Client> iterator() {
        return Arrays.stream(clients).iterator();
    }

    public Client getClientI(int i) {
        return clients[i];
    }
}
