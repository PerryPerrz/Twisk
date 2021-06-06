package twisk.mondeIG;

import twisk.outils.FabriqueIdentifiant;

import java.io.Serializable;

/**
 * The type Guichet ig.
 */
public class GuichetIG extends EtapeIG implements Serializable {

    private static final long serialVersionUID = 1L;

    private int nbJetons;
    private final int numSemaphore;
    private Boolean versLaDroite;
    private FabriqueIdentifiant fab;

    /**
     * Constructeur de la classe EtapeIG.
     *
     * @param nom le nom
     * @param idf l'idf
     */
    public GuichetIG(String nom, String idf) {
        super(nom, idf);
        fab = FabriqueIdentifiant.getInstance();
        this.numSemaphore = fab.getNumSem();
        this.nbJetons = 2;
        this.versLaDroite = null;
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
        this.numSemaphore = fab.getNumSem();
        this.nbJetons = nbJetons;
        this.versLaDroite = null;
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
    public void siEstUnGuichetSetVersLaDroite(Boolean versLaDroite) {
        setVersLaDroite(versLaDroite);
    }

    @Override
    public boolean setActiviteRestreinte(boolean res) {
        return false;
    }

    @Override
    public Boolean siEstUnGuichetGetVersLaDroite() {
        return versLaDroite;
    }

    public Boolean isVersLaDroite() {
        return versLaDroite;
    }

    public void setVersLaDroite(Boolean versLaDroite) {
        this.versLaDroite = versLaDroite;
    }
}
