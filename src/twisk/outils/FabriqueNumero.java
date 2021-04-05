package twisk.outils;

/**
 * The type Fabrique numero.
 */
public class FabriqueNumero {
    private int cptEtape;
    private int cptSemaphore;

    private FabriqueNumero() {
        cptEtape = 0;
        cptSemaphore = 1;
    }

    private static final FabriqueNumero instance = new FabriqueNumero();

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static FabriqueNumero getInstance() {
        return instance;
    }

    /**
     * Gets numero etape.
     *
     * @return the numero etape
     */
    public int getNumeroEtape() {
        cptEtape++; //On passe au numéro de l'étape suivante
        return cptEtape - 1; //On retourne le numéro de l'étape actuel
    }

    /**
     * Gets numero semaphore.
     *
     * @return the numero semaphore
     */
    public int getNumeroSemaphore() {
        cptSemaphore++;
        return cptSemaphore - 1;
    }

    /**
     * Reset.
     */
    public void reset() {
        cptEtape = 0;
        cptSemaphore = 1;
    }
}
