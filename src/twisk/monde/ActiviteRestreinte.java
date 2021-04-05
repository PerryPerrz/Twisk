package twisk.monde;

/**
 * La classe Activité restreinte.
 */
public class ActiviteRestreinte extends Activite {

    /**
     * Constructeur de la classe Activité restreinte.
     *
     * @param nom le nom
     */
    public ActiviteRestreinte(String nom) {
        super(nom);
    }

    /**
     * Constructeur de la classe Activité restreinte.
     *
     * @param nom le nom
     * @param t   le temps
     * @param e   l'écart temps
     */
    public ActiviteRestreinte(String nom, int t, int e) {
        super(nom, t, e);
    }

    @Override
    public String toC() {
        return "delai(" + getTemps() + ", " + getEcartTemps() + ");\n";
    }
}
