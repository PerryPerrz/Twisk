package twisk.monde;

public class Guichet extends Etape{
    private int nbJetons;

    public Guichet(String nom) {
        super(nom);
    }

    public Guichet(String nom, int nb) {
        super(nom,nb);
    }

    @Override
    public boolean estUnGuichet(){
        return true;
    }
}
