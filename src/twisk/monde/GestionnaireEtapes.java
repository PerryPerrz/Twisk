package twisk.monde;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * La classe Gestionnaire Etapes.
 */
public class GestionnaireEtapes implements Iterable<Etape> {
    private final ArrayList<Etape> etapes;

    /**
     * Constructeur de la classe gestionnaire d'étapes.
     */
    public GestionnaireEtapes() {
        etapes = new ArrayList<>(5);  //On mets 5 en capacité initiale pour l'instant (à ajuster par la suite)
    }

    /**
     * Procédure qui ajoute une étape au gestionnaire d'étape.
     *
     * @param etapes les étapes
     */
    public void ajouter(Etape... etapes) {
        this.etapes.addAll(Arrays.asList(etapes));
    }

    /**
     * Fonction qui retourne le nombre d'étapes dans le gestionnaire d'étapes.
     *
     * @return une entier
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
