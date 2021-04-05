package twisk.monde;

import twisk.outils.FabriqueNumero;

/**
 * La classe Guichet.
 */
public class Guichet extends Etape {
    private int nbJetons;
    private int numSemaphore;

    /**
     * Contructeur de la classe Guichet.
     *
     * @param nom le nom
     */
    public Guichet(String nom) {
        super(nom);
        nbJetons = 2;
        FabriqueNumero fabNum = FabriqueNumero.getInstance();
        numSemaphore = fabNum.getNumeroSemaphore();
    }

    /**
     * Contructeur de la classe Guichet.
     *
     * @param nom le nom
     * @param nb  le nombre
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
     * Fonction qui retourne le nombre de jetons d'un guichet.
     *
     * @return le nombre de jetons
     */
    public int getNbJetons() {
        return nbJetons;
    }

    /**
     * Fonction qui définit le nombre de jetons d'un guichet.
     *
     * @param nbJetons le nombre de jetons
     */
    public void setNbJetons(int nbJetons) {
        this.nbJetons = nbJetons;
    }

    /**
     * Fonction qui retourne le numéro de sémaphore d'un guichet.
     *
     * @return le numéro de sémaphore
     */
    public int getNumSemaphore() {
        return numSemaphore;
    }

    /**
     * Fonction qui définit le numéro de sémaphore d'un guichet.
     *
     * @param numSemaphore le numéro de sémaphore
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
