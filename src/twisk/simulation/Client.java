package twisk.simulation;

import twisk.monde.Etape;

public class Client {
    private Etape etape;
    private final int numeroClient;
    private int rang;

    public Client(int numero) {
        this.numeroClient = numero;
    }

    public void allerA(Etape etape, int rang) {
        this.etape = etape;
        this.rang = rang;
    }

    public Etape getEtape() {
        return etape;
    }

    public int getNumeroClient() {
        return numeroClient;
    }

    public int getRang() {
        return rang;
    }


}
