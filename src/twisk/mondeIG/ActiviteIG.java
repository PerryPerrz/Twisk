package twisk.mondeIG;

import twisk.exceptions.PasUnGuichetException;

/**
 * La classe ActiviteIG.
 */
public class ActiviteIG extends EtapeIG {
    private boolean estUneActiviteRestreinte;

    /**
     * Constructeur de la classe ActiviteIG.
     *
     * @param nom le nom
     * @param idf l'idf
     */
    public ActiviteIG(String nom, String idf) {
        super(nom, idf);
        this.estUneActiviteRestreinte = false;
    }

    @Override
    public boolean estUneActivite() {
        return true;
    }

    @Override
    public boolean estUneActiviteRestreinte() {
        return estUneActiviteRestreinte;
    }

    @Override
    public int siEstUnGuichetGetNbJetons() {
        return -1;
    }

    @Override
    public void siEstUnGuichetSetNbJetons(int nbJetons) throws PasUnGuichetException {
        throw new PasUnGuichetException("Attention, une activité ne possède pas de jeton(s) !");
    }

    @Override
    public void siEstUnGuichetSetVersLaDroite(Boolean versLaDroite) throws PasUnGuichetException {
        throw new PasUnGuichetException("Attention une activité ne possède pas de sens !");
    }

    @Override
    public boolean setActiviteRestreinte(boolean res) {
        this.estUneActiviteRestreinte = res;
        return true;
    }

    @Override
    public Boolean siEstUnGuichetGetVersLaDroite() {
        return null;
    }
}
