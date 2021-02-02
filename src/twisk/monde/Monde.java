package twisk.monde;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

public class Monde {
    private ArrayList<SasEntree> sasE;
    private ArrayList<SasSortie> sasS;
    private GestionnaireEtapes ge;

    public Monde(){
        sasE = new ArrayList<>(5);
        sasS = new ArrayList<>(5);
        ge = new GestionnaireEtapes();
    }

    public void aCommeEntree(Etape...etapes){
        //sasE.addAll(Arrays.asList(etapes));
    }

    public void aCommeSortie(Etape...etapes){
        //sasS.addAll(Arrays.asList(etapes));
    }

    public void ajouter(Etape...etapes){
        ge.ajouter(etapes);
    }

    public int nbEtapes(){
        return ge.nbEtapes();
    }

    public int nbGuichets(){
        int cpt = 0;
        for(Etape etape : ge){
            if(etape.estUnGuichet()){
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
        return "Monde{" +
                "sasE=" + sasE +
                ", sasS=" + sasS +
                ", ge=" + ge +
                '}';
    }
}
