package twisk.monde;

import java.util.Iterator;

/**
 * The type Monde.
 */
public class Monde implements Iterable<Etape> {
    private final SasEntree sasE;
    private final SasSortie sasS;
    private final GestionnaireEtapes ge;

    /**
     * Instantiates a new Monde.
     */
    public Monde() {
        sasE = new SasEntree();
        sasS = new SasSortie();
        ge = new GestionnaireEtapes();
        ge.ajouter(sasE, sasS);
    }

    /**
     * A comme entree.
     *
     * @param etapes the etapes
     */
    public void aCommeEntree(Etape... etapes) {
        sasE.ajouterSuccesseur(etapes); //Les étapes notées comme "entrées" sont mises comme successeurs du sas d'entrée
    }

    /**
     * A comme sortie.
     *
     * @param etapes the etapes
     */
    public void aCommeSortie(Etape... etapes) {
        for (Etape e : etapes) {
            e.ajouterSuccesseur(sasS);  //Les étapes notées comme "sorties" sont mises comme prédécesseur du sas de sortie
        }
    }

    /**
     * Ajouter.
     *
     * @param etapes the etapes
     */
    public void ajouter(Etape... etapes) {
        ge.ajouter(etapes);
    }

    /**
     * Nb etapes int.
     *
     * @return the int
     */
    public int nbEtapes() {
        return ge.nbEtapes();
    }

    /**
     * Nb guichets int.
     *
     * @return the int
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
     * To c string.
     *
     * @return the string
     */
    public String toC() {
        return "#include \"def.h\"\nvoid simulation(int ids) {\n" + sasE.toC() + "}";
    }

    /**
     * Gets nb tickets guichet i.
     *
     * @param i the
     * @return the nb tickets guichet i
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
     * Gets nom etape i.
     *
     * @param index the index
     * @return the nom etape i
     */
    public String getNomEtapeI(int index) {
        for (Etape e : ge)
            if (index == e.getNum())
                return e.getNom();
        return null;
    }

    /**
     * Gets num sas sortie.
     *
     * @return the num sas sortie
     */
    public int getNumSasSortie() {
        return sasS.getNum();
    }
}
