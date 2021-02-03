package twisk.outils;

public class FabriqueNumero {
    private int cptEtape;

    private FabriqueNumero(){
        cptEtape = 0;
    }

    private static final FabriqueNumero instance = new FabriqueNumero();

    public static FabriqueNumero getInstance(){
        return instance;
    }

    public int getNumeroEtape(){
        cptEtape++; //On passe au numéro de l'étape suivante
        return cptEtape - 1; //On retourne le numéro de l'étape actuel
    }

    public void reset(){
        cptEtape = 0;
    }
}
