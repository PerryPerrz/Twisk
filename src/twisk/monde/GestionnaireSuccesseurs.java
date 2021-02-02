package twisk.monde;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class GestionnaireSuccesseurs implements Iterable<Etape>  {
    private ArrayList<Etape> succ;

    public GestionnaireSuccesseurs() {
        succ = new ArrayList<>(5);  //On mets 5 en capacité initiale pour l'instant (à ajuster par la suite)
    }

    public void ajouter(Etape... etapes) {
        succ.addAll(Arrays.asList(etapes));
    }

    public int nbEtapes() {
        return succ.size();
    }

    public Iterator<Etape> iterator() {
        return succ.iterator();
    }

    @Override
    public String toString() {
        return "GestionnaireSuccesseurs{" +
                "succ=" + succ +
                '}';
    }
}
