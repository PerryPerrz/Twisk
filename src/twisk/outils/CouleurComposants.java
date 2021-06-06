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
        this.couleurBackgroundEtapeIsSelected = "#f4abc4";
        this.couleurBorderEtapeIsSelected = "#f4abc4";
        this.couleurLabelGuichet = "#138D75";
        this.couleurBorderGuichet = "#8F00FF";
        this.couleurBackgroundGuichet = "#ABEBC6";
        this.couleurBackgroundJourMonde = "#ffe268";
        this.couleurBackgroundNuitMonde = "#151515";
        this.couleurBackgroundResetMonde = "#FFFFFF";
        this.couleurPointDeControle = "#8F00FF";
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

    /**
     * Fonction qui retourne la couleur du stroke de la ligne de la flèche
     *
     * @return la couleur du stroke de la ligne de la flèche
     */
    public String getCouleurLigneStroke() {
        return couleurLigneStroke;
    }

    /**
     * Fonction qui retourne la couleur du stroke du triangle de la flèche
     *
     * @return la couleur du stroke du triangle de la flèche
     */
    public String getCouleurTriangleStroke() {
        return couleurTriangleStroke;
    }

    /**
     * Fonction qui retourne la couleur du fill du triangle de la flèche
     *
     * @return la couleur du fill du triangle de la flèche
     */
    public String getCouleurTriangleFill() {
        return couleurTriangleFill;
    }

    /**
     * Fonction qui retourne la couleur du stroke de la ligne de la flèche lorsqu'elle est selectionnée
     *
     * @return la couleur du stroke de la ligne de la flèche lorsqu'elle est selectionnée
     */
    public String getCouleurLigneStrokeIsSelected() {
        return couleurLigneStrokeIsSelected;
    }

    /**
     * Fonction qui retourne la couleur du stroke du triangle de la flèche lorsqu'elle est selectionnée
     *
     * @return la couleur du stroke du triangle de la flèche lorsqu'elle est selectionnée
     */
    public String getCouleurTriangleStrokeIsSelected() {
        return couleurTriangleStrokeIsSelected;
    }

    /**
     * Fonction qui retourne la couleur du fill du triangle de la flèche lorsqu'elle est selectionnée
     *
     * @return la couleur du fill du triangle de la flèche lorsqu'elle est selectionnée
     */
    public String getCouleurTriangleFillIsSelected() {
        return couleurTriangleFillIsSelected;
    }

    /**
     * Fonction qui retourne la couleur du background d'une étape lorsqu'elle est selectionnée
     *
     * @return la couleur du background d'une étape lorsqu'elle est selectionnée
     */
    public String getCouleurBackgroundEtapeIsSelected() {
        return couleurBackgroundEtapeIsSelected;
    }

    /**
     * Fonction qui retourne la couleur des borders d'une étape lorsqu'elle est selectionnée
     *
     * @return la couleur des borders d'une étape lorsqu'elle est selectionnée
     */
    public String getCouleurBorderEtapeIsSelected() {
        return couleurBorderEtapeIsSelected;
    }

    /**
     * Fonction qui retourne la couleur du label d'un guichet
     *
     * @return la couleur du label d'un guichet
     */
    public String getCouleurLabelGuichet() {
        return couleurLabelGuichet;
    }

    /**
     * Fonction qui retourne la couleur des borders d'un guichet
     *
     * @return la couleur des borders d'un guichet
     */
    public String getCouleurBorderGuichet() {
        return couleurBorderGuichet;
    }

    /**
     * Fonction qui retourne la couleur du background d'un guichet
     *
     * @return la couleur du background d'un guichet
     */
    public String getCouleurBackgroundGuichet() {
        return couleurBackgroundGuichet;
    }

    /**
     * Fonction qui retourne la couleur du background du monde si le style correspond à celui du "jour"
     *
     * @return la couleur du background du monde si le style correspond à celui du "jour"
     */
    public String getCouleurBackgroundJourMonde() {
        return couleurBackgroundJourMonde;
    }

    /**
     * Fonction qui retourne la couleur du background du monde si le style correspond à celui de la "nuit"
     *
     * @return la couleur du background du monde si le style correspond à celui de la "nuit"
     */
    public String getCouleurBackgroundNuitMonde() {
        return couleurBackgroundNuitMonde;
    }

    /**
     * Fonction qui retourne la couleur du background du monde si le style correspond à celui du "reset"
     *
     * @return la couleur du background du monde si le style correspond à celui du "reset"
     */
    public String getCouleurBackgroundResetMonde() {
        return couleurBackgroundResetMonde;
    }

    /**
     * Fonction qui retourne la couleur du point de contrôle
     *
     * @return la couleur du point de contrôle
     */
    public String getCouleurPointDeControle() {
        return couleurPointDeControle;
    }
}
