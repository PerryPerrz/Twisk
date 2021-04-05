package twisk.monde;

import twisk.outils.FabriqueNumero;

import java.util.Iterator;

/**
 * La classe Etape.
 */
public abstract class Etape implements Iterable<Etape> {
    /**
     * Attribut correspondant au gestionnaire de successeurs.
     */
    protected GestionnaireSuccesseurs gestsucc;
    /**
     * Le nom d'une étape.
     */
    protected String nom;
    /**
     * Le numéro d'une étape.
     */
    protected int num;

    /**
     * Constructeur de la classe Etape.
     *
     * @param nom le nom
     */
    public Etape(String nom) {
        FabriqueNumero fabNum = FabriqueNumero.getInstance();
        this.nom = nom;
        gestsucc = new GestionnaireSuccesseurs();
        num = fabNum.getNumeroEtape();
    }

    /**
     * Procédure qui ajoute un succésseur de l'étape.
     *
     * @param e l'étape
     */
    public void ajouterSuccesseur(Etape... e) {
        gestsucc.ajouter(e);
    }

    /**
     * Fonction qui retourne le nombre de succésseur d'une étape.
     *
     * @return un entier
     */
    public int nbSuccesseurs() {
        return gestsucc.nbEtapes();
    }

    /**
     * Fonction qui retourne vrai si l'étape est une activité.
     *
     * @return un booléen
     */
    public boolean estUneActivite() {
        return false;
    }

    /**
     * Fonction qui retourne vrai si l'étape est un guichet.
     *
     * @return un booléen
     */
    public boolean estUnGuichet() {
        return false;
    }

    public Iterator<Etape> iterator() {
        return gestsucc.iterator();
    }

    /**
     * Fonction qui retourne le nom d'une étape.
     *
     * @return le nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Fonction qui retourne le nom d'une étape.
     *
     * @return le num
     */
    public int getNum() {
        return num;
    }

    /**
     * Fonction qui écrit le code C nécessaire à un client pour passer dans l'étape.
     *
     * @return un string
     */
    public abstract String toC();

    /**
     * Fonction qui retourne le gestionnaire de succésseur.
     *
     * @return le gestionnaire de succésseur
     */
    public GestionnaireSuccesseurs getGestsucc() {
        return gestsucc;
    }

    /**
     * Fonction qui retourne le succésseur.
     *
     * @return le succésseur
     */
    public Etape getSucc() {
        return getGestsucc().getSucc();
    }

    /**
     * Fonction qui retourne le nombre de ticket d'un guichet.
     *
     * @return le nombre de ticket
     */
    public abstract int getNbTicketSiGuichet();
}
