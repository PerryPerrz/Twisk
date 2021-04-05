package twisk.monde;

import twisk.outils.FabriqueNumero;

/**
 * The type Guichet.
 */
public class Guichet extends Etape {
    private int nbJetons;
    private int numSemaphore;

    /**
     * Instantiates a new Guichet.
     *
     * @param nom the nom
     */
    public Guichet(String nom) {
        super(nom);
        nbJetons = 2;
        FabriqueNumero fabNum = FabriqueNumero.getInstance();
        numSemaphore = fabNum.getNumeroSemaphore();
    }

    /**
     * Instantiates a new Guichet.
     *
     * @param nom the nom
     * @param nb  the nb
     */
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

    /**
     * Gets nb jetons.
     *
     * @return the nb jetons
     */
    public int getNbJetons() {
        return nbJetons;
    }

    /**
     * Sets nb jetons.
     *
     * @param nbJetons the nb jetons
     */
    public void setNbJetons(int nbJetons) {
        this.nbJetons = nbJetons;
    }

    /**
     * Gets num semaphore.
     *
     * @return the num semaphore
     */
    public int getNumSemaphore() {
        return numSemaphore;
    }

    /**
     * Sets num semaphore.
     *
     * @param numSemaphore the num semaphore
     */
    public void setNumSemaphore(int numSemaphore) {
        this.numSemaphore = numSemaphore;
    }

    @Override
    public String toString() {
        return "Guichet { " +
                "nom = '" + nom + '\'' +
                ", numéro = " + num +
                ", " + gestsucc +
                ", nbJetons = " + nbJetons +
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
