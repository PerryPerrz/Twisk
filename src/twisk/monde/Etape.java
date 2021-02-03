package twisk.monde;

import twisk.outils.FabriqueNumero;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public abstract class Etape implements Iterable<Etape> {
    protected GestionnaireSuccesseurs gestsucc;
    protected String nom;
    protected int num;

    public Etape(String nom) {
        FabriqueNumero fabNum = FabriqueNumero.getInstance();
        this.nom = nom;
        gestsucc = new GestionnaireSuccesseurs();
        num = fabNum.getNumeroEtape();
    }

    public void ajouterSuccesseur(Etape... e) { gestsucc.ajouter(e);
    }

    public int nbSuccesseurs() {
        return gestsucc.nbEtapes();
    }

    public boolean estUneActivite() {
        return false;
    }

    public boolean estUnGuichet() {
        return false;
    }

    public Iterator<Etape> iterator() {
        return gestsucc.iterator();
    }

    public String getNom() {
        return nom;
    }
}
