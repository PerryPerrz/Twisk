package twisk.monde;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * La classe Gestionnaire successeurs.
 */
public class GestionnaireSuccesseurs implements Iterable<Etape> {
    private final ArrayList<Etape> succ;

    /**
     * Constructeur de la classe Gestionnaire successeurs.
     */
    public GestionnaireSuccesseurs() {
        succ = new ArrayList<>(5);  //On mets 5 en capacité initiale pour l'instant (à ajuster par la suite)
    }

    /**
     * Procédure qui ajoute des étapes au gestionnaire de successeurs.
     *
     * @param etapes les étapes
     */
    public void ajouter(Etape... etapes) {
        succ.addAll(Arrays.asList(etapes));
    }

    /**
     * Fonction qui retourne le nombre d'étapes du gestionnaire de sucesseurs.
     *
     * @return un entier
     */
    public int nbEtapes() {
        return succ.size();
    }

    public Iterator<Etape> iterator() {
        return succ.iterator();
    }

    /**
     * Fonction qui retourne le successeur du gestionnnaire de successeurs.
     *
     * @return le successeur
     */
    public Etape getSucc() {
        if (nbEtapes() != 0)
            return succ.get(0);
        else
            return null;
    }

    @Override
    public String toString() {
        if (this.nbEtapes() == 0)
            return "pas de successeurs";
        StringBuilder str = new StringBuilder(50 * this.nbEtapes());
        str.append("Successeurs : ");
        for (Etape e : this) {
            str.append(e.getNom()).append(" - ");
        }
        str.deleteCharAt(str.toString().length() - 1); //On enlève les 3 derniers caractères pour ne pas avoir le " - " en plus
        str.deleteCharAt(str.toString().length() - 1);
        str.deleteCharAt(str.toString().length() - 1);
        return str.toString();
    }
}
