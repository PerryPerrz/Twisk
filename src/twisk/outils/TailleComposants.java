package twisk.outils;

/**
 * La classe TailleComposants.
 */
public class TailleComposants {
    private static final TailleComposants instance = new TailleComposants();
    private final int largAct;
    private final int hautAct;
    private final int tailleBouton;
    private final int windowX;
    private final int windowY;
    private final int rad;
    private final int largLigne;
    private final int longTri;
    private final int largTri;
    private final int tailleIcons;
    private final int tailleIcons2;
    private final int tailleIcons3;
    private final int largGuichet;
    private final int hautGuichet;
    private final int hautLabelEtape;
    private final int margeSelection;
    private final int font;
    private final int radCLient;
    private final int ecartHV;

    /**
     * Constructeur de la classe TailleComposants, il permet d'initialiser toutes les tailles utilisées dans l'application twiskIG.
     */
    private TailleComposants() {
        this.largAct = 150;
        this.hautAct = 118;
        this.tailleBouton = 50;
        this.windowX = 1400;
        this.windowY = 900;
        this.rad = 10;
        this.largLigne = 4;
        this.longTri = 36;
        this.largTri = 12;
        this.tailleIcons = 50;
        this.tailleIcons2 = 50;
        this.tailleIcons3 = 20;
        this.largGuichet = 270;
        this.hautGuichet = 74;
        this.hautLabelEtape = 18;
        this.margeSelection = 4;
        this.font = 18;
        this.radCLient = 6;
        this.ecartHV = 5;
    }

    /**
     * Fonction permettant de réaliser une instance du singleton TailleComposants.
     *
     * @return instance instance
     */
    public static TailleComposants getInstance() {
        return instance;
    }

    /**
     * Fonction qui retourne la largeur d'une activité.
     *
     * @return larg act
     */
    public int getLargAct() {
        return largAct;
    }

    /**
     * Fonction qui retourne la hauteur d'une activité.
     *
     * @return haut act
     */
    public int getHautAct() {
        return hautAct;
    }

    /**
     * Fonction qui retourne la taille du bouton permettant d'ajouter des activités.
     *
     * @return taille bouton
     */
    public int getTailleBouton() {
        return tailleBouton;
    }

    /**
     * Fonction qui retourne la largeur de la fenêtre de l'application.
     *
     * @return window x
     */
    public int getWindowX() {
        return windowX;
    }

    /**
     * Fonction qui retourne la hauteur de la fenêtre de l'application.
     *
     * @return window y
     */
    public int getWindowY() {
        return windowY;
    }

    /**
     * Fonction qui retourne la taille du rayon des points de contrôles.
     *
     * @return rad rad
     */
    public int getRad() {
        return rad;
    }

    /**
     * Fonction qui retourne la largeur de la ligne qui permet de former le corps des arcs.
     *
     * @return larg ligne
     */
    public int getLargLigne() {
        return this.largLigne;
    }

    /**
     * Fonction qui retourne la longueur du triangle qui permet de former la tête des arcs.
     *
     * @return long tri
     */
    public int getLongTri() {
        return longTri;
    }

    /**
     * Fonction qui retourne la largeur du triangle qui permet de former la tête des arcs.
     *
     * @return larg tri
     */
    public int getLargTri() {
        return largTri;
    }

    /**
     * Fonction qui retourne la taille des icones des différentes exceptions qui peuvent se déclancher lors de la création d'arc.
     *
     * @return taille icons
     */
    public int getTailleIcons() {
        return tailleIcons;
    }

    /**
     * Fonction qui retourne la taille des icones utilisés lors de la création du menu.
     *
     * @return taille icons 2
     */
    public int getTailleIcons2() {
        return tailleIcons2;
    }

    /**
     * Fonction qui retourne la taille des icones utilisés lors de la création du menu.
     *
     * @return taille icons 3
     */
    public int getTailleIcons3() {
        return tailleIcons3;
    }

    /**
     * Fonction qui retourne la largeur d'un guichet
     *
     * @return la largeur d'un guichet
     */
    public int getLargGuichet() {
        return largGuichet;
    }

    /**
     * Fonction qui retourne la hauteur d'un guichet
     *
     * @return la hauteur d'un guichet
     */
    public int getHautGuichet() {
        return hautGuichet;
    }

    /**
     * Fonction qui retourne la taille du label d'une étape
     *
     * @return la taille du label d'une étape
     */
    public int getHautLabelEtape() {
        return hautLabelEtape;
    }

    /**
     * Fonction qui retourne la marge d'une étape lors de sa seléction
     *
     * @return la marge d'une étape lors de sa seléction
     */
    public int getMargeSelection() {
        return margeSelection;
    }

    /**
     * Fonction qui retourne la taille de la police utilisée pour les activités et les guichets
     *
     * @return la taille de la police
     */
    public int getFont() {
        return font;
    }

    /**
     * Fonction qui retourne le rayon c'un client
     *
     * @return le rayon d'un client
     */
    public int getRadClient() {
        return radCLient;
    }

    /**
     * Fonction qui retourne l'écart utilisé dans le FlowPane (VueActivité)
     *
     * @return l 'écart horizontal & vertical
     */
    public int getEcartHV() {
        return ecartHV;
    }
}
