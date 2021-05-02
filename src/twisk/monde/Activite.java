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
        StringBuilder stB = new StringBuilder(200);
        if (nbSuccesseurs() == 1) {
            stB.append("delai(").append(getTemps()).append(", ").append(getEcartTemps()).append(");\n");
            stB.append("transfert(").append(getNum()).append(", ").append(getSucc().getNum()).append(");\n");
            stB.append(getSucc().toC());
        } else {
            stB.append("int nb").append(getNum()).append(" = (int) ((rand() / (float) RAND_MAX)*").append(nbSuccesseurs()).append(");\n");
            stB.append("switch(nb)\n");
            stB.append("{\n");
            for (int i = 0; i < nbSuccesseurs(); i++) {
                stB.append("case ").append(i).append(" :\n");
                stB.append("delai(").append(getTemps()).append(", ").append(getEcartTemps()).append(");\n");
                stB.append("transfert(").append(getNum()).append(", ").append(getSuccI(i).getNum()).append(");\n");
                stB.append(getSuccI(i).toC());
                stB.append("break;\n");
            }
            stB.append("}\n");
        }
        return stB.toString();
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
