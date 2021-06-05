package twisk.mondeIG;

import twisk.outils.FabriqueIdentifiant;

/**
 * The type Guichet ig.
 */
public class GuichetIG extends EtapeIG {
    private int nbJetons;
    private final int numSemaphore;

    /**
     * Constructeur de la classe EtapeIG.
     *
     * @param nom le nom
     * @param idf l'idf
     */
    public GuichetIG(String nom, String idf) {
        super(nom, idf);
        FabriqueIdentifiant fab = FabriqueIdentifiant.getInstance();
        this.numSemaphore = fab.getNumSem();
        this.nbJetons = 2;
    }

    /**
     * Constructeur de la classe EtapeIG.
     *
     * @param nom      le nom
     * @param idf      l'idf
     * @param nbJetons le nombre de jetons
     */
    public GuichetIG(String nom, String idf, int nbJetons) {
        super(nom, idf);
        FabriqueIdentifiant fab = FabriqueIdentifiant.getInstance();
        this.numSemaphore = fab.getNumSem();
        this.nbJetons = nbJetons;
    }

    @Override
    public boolean estUnGuichet() {
        return true;
    }

    @Override
    public int siEstUnGuichetGetNbJetons() {
        return this.nbJetons;
    }

    @Override
    public void siEstUnGuichetSetNbJetons(int nbJetons) {
        this.nbJetons = nbJetons;
    }

    @Override
    public boolean setActiviteRestreinte(boolean res) {
        return false;
    }
}
