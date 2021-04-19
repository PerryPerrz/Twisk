package twisk.outils;

/**
 * La classe FabriqueNumero.
 */
public class FabriqueNumero {
    private int cptEtape;
    private int cptSemaphore;
    private int cptLibrairie;

    private FabriqueNumero() {
        cptEtape = 0;
        cptSemaphore = 1;
        cptLibrairie = 1;
    }

    private static final FabriqueNumero instance = new FabriqueNumero();

    /**
     * Retourne l'instance de FabriqueNumero.
     *
     * @return l'instance
     */
    public static FabriqueNumero getInstance() {
        return instance;
    }

    /**
     * Retourne un numero d'étape unique.
     *
     * @return le numero d'étape
     */
    public int getNumeroEtape() {
        cptEtape++; //On passe au numéro de l'étape suivante
        return cptEtape - 1; //On retourne le numéro de l'étape actuel
    }

    /**
     * Retourne un numero unique de sémaphore.
     *
     * @return le numero de sémaphore
     */
    public int getNumeroSemaphore() {
        cptSemaphore++;
        return cptSemaphore - 1;
    }

    /**
     * Retourne un numero unique de librairie.
     *
     * @return le numero de librairie
     */
    public int getNumeroLibrairie() {
        cptLibrairie++;
        return cptLibrairie - 1;
    }

    public int consulterNumeroLibraire() {
        return cptLibrairie - 1;
    }

    /**
     * Réinitialise les numéros (sémaphore et étape).
     */
    public void reset() {
        cptEtape = 0;
        cptSemaphore = 1;
    }
}
