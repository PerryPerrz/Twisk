package twisk.monde;

public class Guichet extends Etape{
    private int nbJetons;

    public Guichet(String nom) {
        super(nom);
        nbJetons = 10;
    }

    public Guichet(String nom, int nb) {
        super(nom);
        this.nbJetons = nb;
    }

    @Override
    public boolean estUnGuichet(){
        return true;
    }

    public int getNbJetons() {
        return nbJetons;
    }

    public void setNbJetons(int nbJetons) {
        this.nbJetons = nbJetons;
    }
}
