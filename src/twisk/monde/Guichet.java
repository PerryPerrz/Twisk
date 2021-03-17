package twisk.monde;

import twisk.outils.FabriqueNumero;

public class Guichet extends Etape {
    private int nbJetons;
    private int numSemaphore;

    public Guichet(String nom) {
        super(nom);
        nbJetons = 10;
        FabriqueNumero fabNum = FabriqueNumero.getInstance();
        numSemaphore = fabNum.getNumeroSemaphore();
    }

    public Guichet(String nom, int nb) {
        super(nom);
        this.nbJetons = nb;
        FabriqueNumero fabNum = FabriqueNumero.getInstance();
        numSemaphore = fabNum.getNumeroSemaphore();
    }

    @Override
    public boolean estUnGuichet() {
        return true;
    }

    public int getNbJetons() {
        return nbJetons;
    }

    public void setNbJetons(int nbJetons) {
        this.nbJetons = nbJetons;
    }

    public int getNumSemaphore() {
        return numSemaphore;
    }

    public void setNumSemaphore(int numSemaphore) {
        this.numSemaphore = numSemaphore;
    }

    @Override
    public String toString() {
        return "Guichet { " +
                "nbJetons = " + nbJetons +
                ", " + gestsucc +
                ", nom = '" + nom + '\'' +
                ", numéro = " + num +
                '}';
    }

    @Override
    public String toC() { //On assume que le monde est correct et donc que l'étape suivant un guichet est bien une activité restreinte
        return "P(ids, " + getNumSemaphore() + ");\ntransfert(" + getNum() + ", " + getSucc().getNum() + ");\n" + getSucc().toC() + "V(ids, " + getNumSemaphore() + ");\ntransfert(" + getSucc().getNum() + ", " + getSucc().getSucc().getNum() + ");\n" + getSucc().getSucc().toC();
    }

    @Override
    public int getNbTicketSiGuichet() {
        return nbJetons;
    }

}
