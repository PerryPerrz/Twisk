package twisk.monde;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * The type Gestionnaire etapes.
 */
public class GestionnaireEtapes implements Iterable<Etape> {
    private final ArrayList<Etape> etapes;

    /**
     * Instantiates a new Gestionnaire etapes.
     */
    public GestionnaireEtapes() {
        etapes = new ArrayList<>(5);  //On mets 5 en capacité initiale pour l'instant (à ajuster par la suite)
    }

    /**
     * Ajouter.
     *
     * @param etapes the etapes
     */
    public void ajouter(Etape... etapes) {
        this.etapes.addAll(Arrays.asList(etapes));
    }

    /**
     * Nb etapes int.
     *
     * @return the int
     */
    public int nbEtapes() {
        return etapes.size();
    }

    public Iterator<Etape> iterator() {
        return etapes.iterator();
    }

    @Override
    public String toString() {
        if (this.nbEtapes() == 0)
            return "pas d'étapes";
        StringBuilder str = new StringBuilder(50 * this.nbEtapes());
        str.append("Etapes : \n");
        for (Etape e : this) {
            str.append("  ").append(e).append("\n");
        }
        return str.toString();
    }
}
