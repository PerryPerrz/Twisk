package twisk.monde;

public class ActiviteRestreinte extends Activite {

    public ActiviteRestreinte(String nom) {
        super(nom);
    }

    public ActiviteRestreinte(String nom, int t, int e) {
        super(nom, t, e);
    }

    @Override
    public String toC() {
        System.out.println(this.getNom() + ", " + this.getNum());
        return "delai(" + getTemps() + ", " + getEcartTemps() + ");\n";
    }
}
