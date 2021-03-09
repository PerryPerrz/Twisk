package twisk.monde;

public class SasEntree extends Activite {

    public SasEntree() {
        super("SasEntree");
    }

    @Override
    public String toC() {
        return "entrer(" + getNum() + ");\ndelai(" + getTemps() + ", " + getEcartTemps() + ");\ntransfert(" + getNum() + ", " + getSucc().getNum() + ");\n" + getSucc().toC(); //On assume que le monde est correct donc SasEntree à forcément un successeur et on assume qu'il n'en a qu'un seul également
    }
}
