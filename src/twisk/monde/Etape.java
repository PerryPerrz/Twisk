package twisk.monde;

import java.util.Iterator;

public abstract class Etape implements Iterable<Etape> {

    protected String nom;

    public Etape(String nom) {
        this.nom = nom;
    }

    public void ajouterSuccesseur(Etape... e) {

    }

    public boolean estUneActivite() {
        return false;
    }

    public boolean estUnGuichet() {
        return false;
    }

    public Iterator<Etape> iterator() {
        return null;
    }
}
