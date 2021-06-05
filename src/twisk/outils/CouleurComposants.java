package twisk.outils;

/**
 * La classe CouleurComposants.
 */
public class CouleurComposants {
    private static final CouleurComposants instance = new CouleurComposants();
    private final String couleurLabelActivite;
    private final String couleurActivite;
    private final String couleurBackgroudnActivite;
    private final String couleurLigneStroke;
    private final String couleurTriangleStroke;
    private final String couleurTriangleFill;
    private final String couleurLigneStrokeIsSelected;
    private final String couleurTriangleStrokeIsSelected;
    private final String couleurTriangleFillIsSelected;
    private final String couleurClient;
    private final String couleurBackgroundEtapeIsSelected;
    private final String couleurBorderEtapeIsSelected;
    private final String couleurLabelGuichet;
    private final String couleurBorderGuichet;
    private final String couleurBackgroundGuichet;
    private final String couleurBackgroundJourMonde;
    private final String couleurBackgroundNuitMonde;
    private final String couleurBackgroundResetMonde;
    private final String couleurPointDeControle;


    /**
     * Constructeur de la classe CouleurComposants, il permet d'initialiser toutes les couleurs utilisées dans l'application twiskIG.
     */
    private CouleurComposants() {
        this.couleurLabelActivite = "#138D75";
        this.couleurActivite = "#8F00FF";
        this.couleurBackgroudnActivite = "#a2d5f2";
        this.couleurLigneStroke = "#6284FF";
        this.couleurTriangleStroke = "#6284FF";
        this.couleurTriangleFill = "#6284FF";
        this.couleurLigneStrokeIsSelected = "#f4abc4";
        this.couleurTriangleFillIsSelected = "#f4abc4";
        this.couleurTriangleStrokeIsSelected = "#f4abc4";
        this.couleurClient = "f4abc4";
        this.couleurBackgroundEtapeIsSelected = "#f4abc4";
        this.couleurBorderEtapeIsSelected = "#f4abc4";
        this.couleurLabelGuichet = "#138D75";
        this.couleurBorderGuichet = "#8F00FF";
        this.couleurBackgroundGuichet = "#ABEBC6";
        this.couleurBackgroundJourMonde = "#ffe268";
        this.couleurBackgroundNuitMonde = "#151515";
        this.couleurBackgroundResetMonde = "#FFFFFF";
        this.couleurPointDeControle = "#6284FF";
    }

    /**
     * Fonction permettant de réaliser une instance du singleton CouleurComposants.
     *
     * @return instance instance
     */
    public static CouleurComposants getInstance() {
        return instance;
    }

    /**
     * Fonction qui retourne la couleur d'une activité
     *
     * @return la couleur de l'activité
     */
    public String getCouleurActivite() {
        return couleurActivite;
    }

    /**
     * Fonction qui retourne la couleur du label d'une activité
     *
     * @return la couleur du label de l'activité
     */
    public String getCouleurLabelActivite() {
        return couleurLabelActivite;
    }

    /**
     * Fonction qui retourne la couleur du background d'une activité
     *
     * @return la couleur du background de l'activité
     */
    public String getCouleurBackgroudnActivite() {
        return couleurBackgroudnActivite;
    }

    public String getCouleurLigneStroke() {
        return couleurLigneStroke;
    }

    public String getCouleurTriangleStroke() {
        return couleurTriangleStroke;
    }

    public String getCouleurTriangleFill() {
        return couleurTriangleFill;
    }

    public String getCouleurLigneStrokeIsSelected() {
        return couleurLigneStrokeIsSelected;
    }

    public String getCouleurTriangleStrokeIsSelected() {
        return couleurTriangleStrokeIsSelected;
    }

    public String getCouleurTriangleFillIsSelected() {
        return couleurTriangleFillIsSelected;
    }

    public String getCouleurClient() {
        return couleurClient;
    }

    public String getCouleurBackgroundEtapeIsSelected() {
        return couleurBackgroundEtapeIsSelected;
    }

    public String getCouleurBorderEtapeIsSelected() {
        return couleurBorderEtapeIsSelected;
    }

    public String getCouleurLabelGuichet() {
        return couleurLabelGuichet;
    }

    public String getCouleurBorderGuichet() {
        return couleurBorderGuichet;
    }

    public String getCouleurBackgroundGuichet() {
        return couleurBackgroundGuichet;
    }

    public String getCouleurBackgroundJourMonde() {
        return couleurBackgroundJourMonde;
    }

    public String getCouleurBackgroundNuitMonde() {
        return couleurBackgroundNuitMonde;
    }

    public String getCouleurBackgroundResetMonde() {
        return couleurBackgroundResetMonde;
    }

    public String getCouleurPointDeControle() {
        return couleurPointDeControle;
    }
}
