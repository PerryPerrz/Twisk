package twisk.monde;

/**
 * The type Activite.
 */
public class Activite extends Etape {
    private int temps;
    private int ecartTemps;

    /**
     * Instantiates a new Activite.
     *
     * @param nom the nom
     */
    public Activite(String nom) {
        super(nom);
        temps = 4;
        ecartTemps = 2;
    }

    /**
     * Instantiates a new Activite.
     *
     * @param nom the nom
     * @param t   the t
     * @param e   the e
     */
    public Activite(String nom, int t, int e) {
        super(nom);
        this.temps = t;
        this.ecartTemps = e;

    }

    @Override
    public boolean estUneActivite() {
        return true;
    }

    /**
     * Gets temps.
     *
     * @return the temps
     */
    public int getTemps() {
        return temps;
    }

    /**
     * Sets temps.
     *
     * @param temps the temps
     */
    public void setTemps(int temps) {
        this.temps = temps;
    }

    /**
     * Gets ecart temps.
     *
     * @return the ecart temps
     */
    public int getEcartTemps() {
        return ecartTemps;
    }

    /**
     * Sets ecart temps.
     *
     * @param ecartTemps the ecart temps
     */
    public void setEcartTemps(int ecartTemps) {
        this.ecartTemps = ecartTemps;
    }

    @Override
    public String toString() {
        return "Activite { " +
                "nom = '" + nom + '\'' +
                ", numéro = " + num +
                ", " + gestsucc +
                ", temps = " + temps +
                ", ecartTemps = " + ecartTemps +
                '}';
    }

    @Override
    public String toC() {
        return "delai(" + getTemps() + ", " + getEcartTemps() + ");\ntransfert(" + getNum() + ", " + getSucc().getNum() + ");\n" + getSucc().toC();//On assume que le monde est correct et que l'activité n'a qu'un seul successeur
    }

    @Override
    public int getNbTicketSiGuichet() {
        return 0; //Une activité ne contient pas de tickets
    }
}
