package twisk.monde;

import twisk.outils.FabriqueNumero;

import java.util.Iterator;
import java.util.Locale;

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
     * Fonction qui retourne le num d'une étape.
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
     * Fonction qui retourne le gestionnaire de successeur.
     *
     * @return le gestionnaire de successeur
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
     * Fonction qui retourne le successeur n°i.
     *
     * @param i l'indice du successeur
     * @return le successeur
     */
    public Etape getSuccI(int i) {
        return getGestsucc().getSuccI(i);
    }

    /**
     * Fonction qui retourne le nombre de ticket d'un guichet.
     *
     * @return le nombre de ticket
     */
    public abstract int getNbTicketSiGuichet();

    /**
     * Fonction qui retourne le numéro de sémaphore d'un guichet.
     *
     * @return le numéro de sémaphore
     */
    public abstract int getNumSem();

    /**
     * Fonction qui retourne le nom de l'étape en majuscules (utiles pour les defines du code c) (utile pour clarifier le code)
     *
     * @return le nom en majuscules
     */
    public String getNomMaj() {
        return getNom().toUpperCase(Locale.ROOT).replace(" ", "_");
    }

    /**
     * Fonction qui retourne vrai si l'étape est le sas entrée
     *
     * @return un booléen
     */
    public boolean estUnSasEntree() {
        return false;
    }

    /**
     * Fonction qui retourne vrai si l'étape est le sas sortie
     *
     * @return un booléen
     */
    public boolean estUnSasSortie() {
        return false;
    }
}
