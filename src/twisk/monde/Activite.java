package twisk.monde;

public class Activite extends Etape {
    private int temps;
    private int ecartTemps;

    public Activite(String nom){
        super(nom);
    }

    public Activite(String nom,int nb){
        super(nom,nb);
    }

    @Override
    public boolean estUneActivite(){
        return true;
    }


}
