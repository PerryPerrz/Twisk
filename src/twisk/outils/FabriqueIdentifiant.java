package twisk.outils;

/**
 * La classe FabriqueIdentifiant.
 */
public class FabriqueIdentifiant {
    private static final FabriqueIdentifiant instance = new FabriqueIdentifiant();
    private int noEtape;
    private int noPdc;
    private int numSem;

    /**
     * Constructeur de la classe FabriqueIdentifiant permettant d'initialiser les attributs noEtape et noPdc.
     */
    private FabriqueIdentifiant() {
        this.noEtape = 0;
        this.noPdc = 0;
        this.numSem = 1;
    }

    /**
     * Fonction permettant de réaliser une instance du singleton FabriqueIdentifiant.
     *
     * @return instance instance
     */
    public static FabriqueIdentifiant getInstance() {
        return instance;
    }

    /**
     * Fonction permettant de récupérer le numéro d'une étape.
     *
     * @return identifiant etape
     */
    public String getIdentifiantEtape() {
        return "" + this.noEtape++;
    }

    /**
     * Fonction permettant de récupérer le numéro d'un point de contrôle.
     *
     * @return identifiant pdc
     */
    public String getIdentifiantPdc() {
        return "" + this.noPdc++;
    }

    /**
     * Procédure permettant de ré-initialiser le numéro d'étape.
     */
    public void reset() {
        this.noEtape = 0;
    }

    /**
     * Fonction permettant de récupérer le numéro de sémaphore
     *
     * @return numéro semaphore
     */
    public int getNumSem() {
        return this.numSem++;
    }
}
