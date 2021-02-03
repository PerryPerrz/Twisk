package twisk.monde;

public class Activite extends Etape {
    private int temps;
    private int ecartTemps;

    public Activite(String nom){
        super(nom);
        temps = 10;
        ecartTemps = 10;
    }

    public Activite(String nom, int t, int e){
        super(nom);
        this.temps = t;
        this.ecartTemps = e;

    }

    @Override
    public boolean estUneActivite(){
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
                "temps = " + temps +
                ", ecartTemps = " + ecartTemps +
                ", " + gestsucc +
                ", nom = '" + nom + '\'' +
                ", numéro = " + num +
                '}';
    }
}
