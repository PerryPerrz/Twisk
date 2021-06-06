package twisk.monde;

/**
 * La classe SasEntree.
 */
public class SasEntree extends Activite {
    private String loi;

    /**
     * Constructeur de la classe SasEntree.
     */
    public SasEntree() {
        super("SasEntree");
        loi = "Uni";
        this.setTemps(10);
        this.setEcartTemps(4);
    }

    @Override
    public String toC() { //On assume que le monde est correct donc SasEntree à forcément un successeur et on assume qu'il n'en a qu'un seul également
        StringBuilder stB = new StringBuilder(200);
        if (nbSuccesseurs() == 1) {
            stB.append("entrer(").append(getNomMaj()).append(");\n");
            switch (loi) {
                case "Uni":
                    stB.append("delaiUniforme(").append(this.getTemps()).append(", ").append(this.getEcartTemps()).append(");\n");
                    break;
                case "Gauss":
                    stB.append("delaiGauss(").append(this.getTemps()).append(".0, ").append(this.getEcartTemps()).append(".0);\n");
                    break;
                case "Expo":
                    stB.append("delaiExponentiel(1.0/").append(this.getTemps()).append(".0);\n");
                    break;
            }
            stB.append("transfert(").append(getNomMaj()).append(", ").append(getSucc().getNomMaj()).append(");\n");
            stB.append(getSucc().toC());
        } else {
            stB.append("int nb").append(getNum()).append(" = (int) ((rand() / (float) RAND_MAX)*").append(nbSuccesseurs()).append(");\n");
            stB.append("switch(nb").append(getNum()).append(")\n");
            stB.append("{\n");
            for (int i = 0; i < nbSuccesseurs(); i++) {
                stB.append("case ").append(i).append(" :\n");
                stB.append("entrer(").append(getNomMaj()).append(");\n");
                stB.append("delai(").append(getTemps()).append(", ").append(getEcartTemps()).append(");\n");
                stB.append("transfert(").append(getNomMaj()).append(", ").append(getSuccI(i).getNomMaj()).append(");\n");
                stB.append(getSuccI(i).toC());
                stB.append("break;\n");
            }
            stB.append("}\n");
        }
        return stB.toString();
    }

    @Override
    public boolean estUnSasEntree() {
        return true;
    }

    /**
     * Procédure qui change la loi de probabilités utilisée pour déterminer le délai d'entrée dans la première étape.
     *
     * @param loi la loi utilisée (Uni, Gauss, Expo)
     */
    public void setLoi(String loi) {
        this.loi = loi;
    }
}
