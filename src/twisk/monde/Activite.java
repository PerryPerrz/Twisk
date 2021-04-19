package twisk.monde;

/**
 * La classe Activité.
 */
public class Activite extends Etape {
    private int temps;
    private int ecartTemps;

    /**
     * Constructeur de la classe Activite.
     *
     * @param nom le nom
     */
    public Activite(String nom) {
        super(nom);
        temps = 4;
        ecartTemps = 2;
    }

    /**
     * Constructeur de la classe Activite.
     *
     * @param nom le nom
     * @param t   le temps
     * @param e   l'écart temps
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
     * Retourne le temps.
     *
     * @return the temps
     */
    public int getTemps() {
        return temps;
    }

    /**
     * Définit le temps
     *
     * @param temps le temps
     */
    public void setTemps(int temps) {
        this.temps = temps;
    }

    /**
     * Retourne l'écart temps.
     *
     * @return l'écart temps
     */
    public int getEcartTemps() {
        return ecartTemps;
    }

    /**
     * Définit l'écart temps.
     *
     * @param ecartTemps l'écart temps
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

    @Override
    public int getNumSem() {
        return 0; //Une activité ne contient pas de numéro de sémaphore
    }
}
