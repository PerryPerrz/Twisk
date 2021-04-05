package twisk.monde;

/**
 * The type Activite restreinte.
 */
public class ActiviteRestreinte extends Activite {

    /**
     * Instantiates a new Activite restreinte.
     *
     * @param nom the nom
     */
    public ActiviteRestreinte(String nom) {
        super(nom);
    }

    /**
     * Instantiates a new Activite restreinte.
     *
     * @param nom the nom
     * @param t   the t
     * @param e   the e
     */
    public ActiviteRestreinte(String nom, int t, int e) {
        super(nom, t, e);
    }

    @Override
    public String toC() {
        return "delai(" + getTemps() + ", " + getEcartTemps() + ");\n";
    }
}
