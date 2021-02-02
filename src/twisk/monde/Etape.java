package twisk.monde;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public abstract class Etape implements Iterable<Etape> {
    protected ArrayList<Etape> succ;
    protected String nom;

    public Etape(String nom) {
        this.nom = nom;
        succ = new ArrayList<>(5);  //On mets 5 en capacité initiale pour l'instant (à ajuster par la suite)
    }

    public void ajouterSuccesseur(Etape... e) {
        succ.addAll(Arrays.asList(e));
    }

    public int nbSuccesseurs() {
        return succ.size();
    }

    public boolean estUneActivite() {
        return false;
    }

    public boolean estUnGuichet() {
        return false;
    }

    public Iterator<Etape> iterator() {
        return succ.iterator();
    }
}
