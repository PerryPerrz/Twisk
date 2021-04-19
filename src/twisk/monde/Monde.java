package twisk.monde;

import twisk.outils.FabriqueNumero;

import java.util.Iterator;

/**
 * La classe Monde.
 */
public class Monde implements Iterable<Etape> {
    private final SasEntree sasE;
    private final SasSortie sasS;
    private final GestionnaireEtapes ge;

    /**
     * Constructeur de la classe Monde.
     */
    public Monde() {
        FabriqueNumero fab = FabriqueNumero.getInstance();
        fab.reset();
        sasE = new SasEntree();
        sasS = new SasSortie();
        ge = new GestionnaireEtapes();
        ge.ajouter(sasE, sasS);
    }

    /**
     * Procédure qui retourne l'entrée d'un monde.
     *
     * @param etapes les étapes
     */
    public void aCommeEntree(Etape... etapes) {
        sasE.ajouterSuccesseur(etapes); //Les étapes notées comme "entrées" sont mises comme successeurs du sas d'entrée
    }

    /**
     * Procédure qui retourne la sortie d'un monde.
     *
     * @param etapes les étapes
     */
    public void aCommeSortie(Etape... etapes) {
        for (Etape e : etapes) {
            e.ajouterSuccesseur(sasS);  //Les étapes notées comme "sorties" sont mises comme prédécesseur du sas de sortie
        }
    }

    /**
     * Procédure qui ajoute les étapes dans un monde.
     *
     * @param etapes les étapes.
     */
    public void ajouter(Etape... etapes) {
        ge.ajouter(etapes);
    }

    /**
     * Fonction qui retourne le nombre d'étapes que possède un monde.
     *
     * @return un entier
     */
    public int nbEtapes() {
        return ge.nbEtapes();
    }

    /**
     * Fonction qui retourne le nombre de guichet que possède un monde.
     *
     * @return un entier
     */
    public int nbGuichets() {
        int cpt = 0;
        for (Etape etape : ge) {
            if (etape.estUnGuichet()) {
                cpt++;
            }
        }
        return cpt;
    }

    public Iterator<Etape> iterator() {
        return ge.iterator();
    }

    @Override
    public String toString() {
        return "Monde {\n" + ge + '}';
    }

    /**
     * Fonction qui écrit le code C nécessaire à un client pour passer dans le monde.
     *
     * @return une chaine de caractère.
     */
    public String toC() {
        return "#include \"def.h\"\nvoid simulation(int ids) {\n" + sasE.toC() + "}";
    }

    /**
     * Fonction qui retourne le nombre de ticket d'un guichet d'indice i dans le monde
     *
     * @param i l'indice
     * @return le nombre de ticket du guichet i
     */
    public int getNbTicketsGuichetI(int i) {
        int cpt = 0;
        for (Etape e : ge) {
            if (e.estUnGuichet()) {
                if (cpt == i)
                    return e.getNbTicketSiGuichet();
                cpt++;
            }
        }
        return 0; //Le guichet n'existe pas
    }

    /**
     * Fonction qui retourne le nom de l'étape d'indice i
     *
     * @param index l'indice
     * @return le nom de l'étape à l'indice i
     */
    public String getNomEtapeI(int index) {
        for (Etape e : ge)
            if (index == e.getNum())
                return e.getNom();
        return null;
    }

    /**
     * Fonction qui retourne le numéro du sas de sortie.
     *
     * @return le numéro du sas de sortie.
     */
    public int getNumSasSortie() {
        return sasS.getNum();
    }

    /**
     * Fonction qui retourne l'étape d'indice i du monde.
     *
     * @param i l'indice
     * @return Etape l'étape d'indice i
     */
    public Etape getEtapeI(int i) {
        for (Etape e : ge)
            if (i == e.getNum())
                return e;
        return null;
    }
}
