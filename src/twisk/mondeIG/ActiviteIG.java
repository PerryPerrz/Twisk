package twisk.mondeIG;

/**
 * La classe ActiviteIG.
 */
public class ActiviteIG extends EtapeIG {
    /**
     * Constructeur de la classe ActiviteIG.
     *
     * @param nom le nom
     * @param idf l'idf
     */
    public ActiviteIG(String nom, String idf) {
        super(nom, idf);
    }

    @Override
    public boolean estUneActivite() {
        return true;
    }

    @Override
    public int siEstUnGuichetGetNbJetons() {
        return -1;
    }

    @Override
    public void siEstUnGuichetSetNbJetons(int nbJetons) {
        System.out.println("Attention, une activité ne possède pas de jeton(s) !");
    }
}
