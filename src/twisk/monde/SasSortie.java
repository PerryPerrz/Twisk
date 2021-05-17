package twisk.monde;

/**
 * La classe SasSortie.
 */
public class SasSortie extends Activite {

    /**
     * Constructeur de la classe SasSortie.
     */
    public SasSortie() {
        super("SasSortie");
    }

    @Override
    public String toC() {
        return "";
    }

    @Override
    public boolean estUnSasSortie() {
        return true;
    }
}
