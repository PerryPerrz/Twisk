package twisk.monde;

import twisk.outils.FabriqueNumero;

import java.util.Iterator;

/**
 * The type Etape.
 */
public abstract class Etape implements Iterable<Etape> {
    /**
     * The Gestsucc.
     */
    protected GestionnaireSuccesseurs gestsucc;
    /**
     * The Nom.
     */
    protected String nom;
    /**
     * The Num.
     */
    protected int num;

    /**
     * Instantiates a new Etape.
     *
     * @param nom the nom
     */
    public Etape(String nom) {
        FabriqueNumero fabNum = FabriqueNumero.getInstance();
        this.nom = nom;
        gestsucc = new GestionnaireSuccesseurs();
        num = fabNum.getNumeroEtape();
    }

    /**
     * Ajouter successeur.
     *
     * @param e the e
     */
    public void ajouterSuccesseur(Etape... e) {
        gestsucc.ajouter(e);
    }

    /**
     * Nb successeurs int.
     *
     * @return the int
     */
    public int nbSuccesseurs() {
        return gestsucc.nbEtapes();
    }

    /**
     * Est une activite boolean.
     *
     * @return the boolean
     */
    public boolean estUneActivite() {
        return false;
    }

    /**
     * Est un guichet boolean.
     *
     * @return the boolean
     */
    public boolean estUnGuichet() {
        return false;
    }

    public Iterator<Etape> iterator() {
        return gestsucc.iterator();
    }

    /**
     * Gets nom.
     *
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Gets num.
     *
     * @return the num
     */
    public int getNum() {
        return num;
    }

    /**
     * To c string.
     *
     * @return the string
     */
    public abstract String toC();

    /**
     * Gets gestsucc.
     *
     * @return the gestsucc
     */
    public GestionnaireSuccesseurs getGestsucc() {
        return gestsucc;
    }

    /**
     * Gets succ.
     *
     * @return the succ
     */
    public Etape getSucc() {
        return getGestsucc().getSucc();
    }

    /**
     * Gets nb ticket si guichet.
     *
     * @return the nb ticket si guichet
     */
    public abstract int getNbTicketSiGuichet();
}
