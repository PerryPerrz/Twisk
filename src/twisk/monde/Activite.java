package twisk.monde;

public class Activite extends Etape {
    private int temps;
    private int ecartTemps;

    public Activite(String nom) {
        super(nom);
        temps = 5;
        ecartTemps = 2;
    }

    public Activite(String nom, int t, int e) {
        super(nom);
        this.temps = t;
        this.ecartTemps = e;

    }

    @Override
    public boolean estUneActivite() {
        return true;
    }

    public int getTemps() {
        return temps;
    }

    public void setTemps(int temps) {
        this.temps = temps;
    }

    public int getEcartTemps() {
        return ecartTemps;
    }

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
