package twisk.monde;

import java.util.Iterator;

public class Monde {
    private final SasEntree sasE;
    private final SasSortie sasS;
    private final GestionnaireEtapes ge;

    public Monde() {
        sasE = new SasEntree();
        sasS = new SasSortie();
        ge = new GestionnaireEtapes();
    }

    public void aCommeEntree(Etape... etapes) {
        sasE.ajouterSuccesseur(etapes); //Les étapes notées comme "entrées" sont mises comme successeurs du sas d'entrée
    }

    public void aCommeSortie(Etape... etapes) {
        for (Etape e : etapes) {
            e.ajouterSuccesseur(sasS);  //Les étapes notées comme "sorties" sont mises comme prédécesseur du sas de sortie
        }
    }

    public void ajouter(Etape... etapes) {
        ge.ajouter(etapes);
    }

    public int nbEtapes() {
        return ge.nbEtapes();
    }

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
        return "Monde {\n" +
                "sas d'entrée : " + sasE +
                "\nsas de sortie : " + sasS +
                "\n" + ge +
                '}';
    }

    public String toC() {
        return sasE.toC();
    }

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
}
