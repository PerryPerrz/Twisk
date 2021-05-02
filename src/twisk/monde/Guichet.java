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
        StringBuilder stB = new StringBuilder(200);
        if (getSucc().nbSuccesseurs() == 1) {
            stB.append("P(ids, ").append(getNumSemaphore()).append(");\n");
            stB.append("transfert(").append(getNum()).append(", ").append(getSucc().getNum()).append(");\n");
            stB.append(getSucc().toC()).append("V(ids, ").append(getNumSemaphore()).append(");\n");
            stB.append("transfert(").append(getSucc().getNum()).append(", ").append(getSucc().getSucc().getNum()).append(");\n");
            stB.append(getSucc().getSucc().toC());
        } else {
            stB.append("int nb").append(getNum()).append(" = (int) ((rand() / (float) RAND_MAX)*").append(nbSuccesseurs()).append(");\n");
            stB.append("switch(nb)\n");
            stB.append("{\n");
            for (int i = 0; i < getSucc().nbSuccesseurs(); i++) {
                stB.append("case ").append(i).append(" :\n");
                stB.append("P(ids, ").append(getNumSemaphore()).append(");\n");
                stB.append("transfert(").append(getNum()).append(", ").append(getSucc().getNum()).append(");\n");
                stB.append(getSucc().toC()).append("V(ids, ").append(getNumSemaphore()).append(");\n");
                stB.append("transfert(").append(getSucc().getNum()).append(", ").append(getSucc().getSuccI(i).getNum()).append(");\n");
                stB.append(getSucc().getSuccI(i).toC());
                stB.append("break;\n");
            }
            stB.append("}\n");
        }
        return stB.toString();
    }

    @Override
    public int getNbTicketSiGuichet() {
        return nbJetons;
    }

    @Override
    public int getNumSem() {
        return this.numSemaphore;
    }

}
