package twisk.simulation;

import twisk.monde.Etape;

/**
 * La classe Client.
 */
public class Client {
    private Etape etape;
    private final int numeroClient;
    private int rang;

    /**
     * Instancie un nouveau Client.
     *
     * @param numero le numero du client
     */
    public Client(int numero) {
        this.numeroClient = numero;
    }

    /**
     * Définit l'étape dans laquelle se trouve le client et son rang.
     *
     * @param etape l'etape
     * @param rang  le rang
     */
    public void allerA(Etape etape, int rang) {
        this.etape = etape;
        this.rang = rang;
    }

    /**
     * Retourne l'etape dans laquelle se trouve .
     *
     * @return the etape
     */
    public Etape getEtape() {
        return etape;
    }

    /**
     * Retourne le numero du client.
     *
     * @return le numero
     */
    public int getNumeroClient() {
        return numeroClient;
    }

    /**
     * Retourne le rang du client.
     *
     * @return le rang
     */
    public int getRang() {
        return rang;
    }
}
