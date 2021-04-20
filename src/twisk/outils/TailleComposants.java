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

    /**
     * Constructeur de la classe TailleComposants, il permet d'initialiser toutes les tailles utilisées dans l'application twiskIG.
     */
    private TailleComposants() {
        this.largAct = 120;
        this.hautAct = 44;
        this.tailleBouton = 30;
        this.windowX = 700;
        this.windowY = 600;
        this.rad = 6;
        this.largLigne = 2;
        this.longTri = 18;
        this.largTri = 6;
        this.tailleIcons = 50;
        this.tailleIcons2 = 25;
        this.tailleIcons3 = 15;
    }

    /**
     * Fonction permettant de réaliser une instance du singleton TailleComposants.
     *
     * @return instance
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
     * @return rad
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
}
