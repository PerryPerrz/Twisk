package twisk.mondeIG;

/**
 * La classe PointDeControleIG.
 */
public class PointDeControleIG {
    private int centreX;
    private int centreY;
    private final String id;
    private final EtapeIG etapeRattache;
    //Booléen qui informe sur quel Pdc l'utilisateur à cliqué
    private boolean clicked;

    /**
     * Constructeur de la classe PointDeControleIG.
     *
     * @param id    l'id
     * @param etape l'etape
     */
    public PointDeControleIG(String id, EtapeIG etape) {
        this.id = id;
        this.etapeRattache = etape;
        this.clicked = false;
    }

    /**
     * Procédure qui permet de set le centre d'un point de contrôle.
     *
     * @param centreX the centre x
     * @param centreY the centre y
     */
    public void setCentre(int centreX, int centreY) {
        this.centreX = centreX;
        this.centreY = centreY;
    }

    /**
     * Fonction qui retourne la coordonnée X du centre d'un point de contrôle.
     *
     * @return le centre x
     */
    public int getCentreX() {
        return centreX;
    }

    /**
     * Fonction qui retourne la coordonnée Y du centre d'un point de contrôle.
     *
     * @return le centre y
     */
    public int getCentreY() {
        return centreY;
    }

    /**
     * Procédure qui change la valeur du booléen "clicked".
     */
    public void setClicked() {
        this.clicked = !this.clicked;
    }

    /**
     * Fonction qui retourne vrai si l'utilisateur à cliqué sur le point de contrôle.
     *
     * @return le boolean
     */
    public boolean isClicked() {
        return this.clicked;
    }

    /**
     * Fonction qui retourne l'id du point de contrôle.
     *
     * @return l'id
     */
    public String getId() {
        return id;
    }

    /**
     * Fonction qui retourne l'étape rattachée à un point de contrôle.
     *
     * @return l'etape rattache
     */
    public EtapeIG getEtapeRattache() {
        return etapeRattache;
    }
}
