package twisk.mondeIG;

import twisk.outils.FabriqueIdentifiant;
import twisk.outils.TailleComposants;

import java.util.Iterator;
import java.util.Random;

/**
 * La classe EtapeIG.
 */
public abstract class EtapeIG implements Iterable<PointDeControleIG> {
    /**
     * Attribut correspondant au nom d'une étape.
     */
    protected String nom;
    /**
     * Attribut correspondant à l'identifiant d'une étape.
     */
    protected String identifiant;
    /**
     * Attribut correspondant à la position X d'une étape.
     */
    protected int posX;
    /**
     * Attribut correspondant à la position Y d'une étape.
     */
    protected int posY;
    /**
     * Attribut correspondant aux 4 points de contrôles que possède une activité, ceux-ci stockés dans un tableau.
     */
    protected PointDeControleIG[] pdc;
    /**
     * Attribut qui est mis à vrai si l'activité regardée est une entrée.
     */
    protected boolean entree;
    /**
     * Attribut qui est mis à vrai si l'activité regardée est une sortie.
     */
    protected boolean sortie;
    /**
     * Attribut correspondant au délai d'une étape.
     */
    protected int delai;
    /**
     * Attribut correspondant au écart d'une étape.
     */
    protected int ecart;

    /**
     * Constructeur de la classe EtapeIG.
     *
     * @param nom le nom
     * @param idf l'idf
     */
    public EtapeIG(String nom, String idf) {
        this.nom = nom;
        this.identifiant = idf;
        pdc = new PointDeControleIG[4];
        this.entree = false;
        this.sortie = false;
        this.delai = 8;
        this.ecart = 4;
        FabriqueIdentifiant fabrik = FabriqueIdentifiant.getInstance();

        for (int i = 0; i < this.pdc.length; ++i) {
            pdc[i] = new PointDeControleIG(fabrik.getIdentifiantPdc(), this);
        }
        randomPositions();
    }

    /**
     * Procédure qui permet de donner un nombre aléatoire à la position X et Y d'une activité.
     */
    public void randomPositions() {
        Random random = new Random();
        TailleComposants tc = TailleComposants.getInstance();
        //On fait en sorte que les activités ne se créent pas sur le menu ni sur le boutton "ajouter"
        this.posX = random.nextInt(tc.getWindowX() - tc.getLargAct() - tc.getRad() * 2) + tc.getRad() * 2;
        this.posY = random.nextInt(tc.getWindowY() - tc.getHautAct() - tc.getTailleBouton() - tc.getTailleIcons2() * 2 - tc.getRad()) + tc.getTailleIcons2() * 2 + tc.getRad();
        this.raffraichissementPdc();
    }

    /**
     * Procédure qui permet de raffraichir les coordonnées des points de contrôles.
     */
    public void raffraichissementPdc() {
        TailleComposants tc = TailleComposants.getInstance();
        pdc[0].setCentre(this.posX + tc.getLargAct() / 2, this.posY);
        pdc[1].setCentre(this.posX + tc.getLargAct() / 2, this.posY + tc.getHautAct());
        pdc[2].setCentre(this.posX, this.posY + tc.getHautAct() / 2);
        pdc[3].setCentre(this.posX + tc.getLargAct(), this.posY + tc.getHautAct() / 2);
    }

    /**
     * Fonction qui retourne l'identifiant d'une étape.
     *
     * @return l'identifiant
     */
    public String getIdentifiant() {
        return identifiant;
    }

    /**
     * Fonction qui retourne la position X d'une étape.
     *
     * @return la pos x
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Fonction qui retourne la position Y d'une étape.
     *
     * @return la pos y
     */
    public int getPosY() {
        return posY;
    }

    @Override
    public Iterator<PointDeControleIG> iterator() {
        return new Iterator<>() {

            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < pdc.length && pdc[i] != null;
            }

            @Override
            public PointDeControleIG next() {
                return pdc[i++];
            }
        };
    }

    /**
     * Fonction qui retourne un point de contrôle grâce à un indice.
     *
     * @param indice l'indice
     * @return le pdc index
     */
    public PointDeControleIG getPdcIndex(int indice) {
        return this.pdc[indice];
    }

    /**
     * Fonction qui retourne le nom d'une étape.
     *
     * @return le nom
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Procédure qui set le nom d'une étape.
     *
     * @param nom le nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Procédure qui set la position X et la position Y d'une étape.
     *
     * @param posX le pos x
     * @param posY le pos y
     */
    public void setPosXPosY(int posX, int posY) {
        TailleComposants tc = TailleComposants.getInstance();
        this.posX = posX;
        this.posY = posY;

        //On raffraichit les points de contrôles lors du drag and drop
        pdc[0].setCentre(this.posX + tc.getLargAct() / 2, this.posY);
        pdc[1].setCentre(this.posX + tc.getLargAct() / 2, this.posY + tc.getHautAct());
        pdc[2].setCentre(this.posX, this.posY + tc.getHautAct() / 2);
        pdc[3].setCentre(this.posX + tc.getLargAct(), this.posY + tc.getHautAct() / 2);
    }

    /**
     * Procédure qui inverse le booléen entrée.
     */
    public void invEntree() {
        this.entree = !this.entree;
    }

    /**
     * Procédure qui inverse le booléen sortie.
     */
    public void invSortie() {
        this.sortie = !this.sortie;
    }

    /**
     * Fonction qui retourne le délai d'une étape.
     *
     * @return le delai
     */
    public int getDelai() {
        return delai;
    }

    /**
     * Fonction qui retourne l'écart d'une étape.
     *
     * @return l'ecart
     */
    public int getEcart() {
        return ecart;
    }

    /**
     * Procédure qui set le délai d'une étape.
     *
     * @param delai le delai
     */
    public void setDelai(int delai) {
        this.delai = delai;
    }

    /**
     * Procédure qui set l'écart d'une étape.
     *
     * @param ecart l'ecart
     */
    public void setEcart(int ecart) {
        this.ecart = ecart;
    }

    /**
     * Fonction qui retourne vrai si l'étape est une entrée.
     *
     * @return le boolean
     */
    public boolean estUneEntree() {
        return entree;
    }

    /**
     * Fonction qui retourne vrai si l'étape est une sortie.
     *
     * @return le boolean
     */
    public boolean estUneSortie() {
        return sortie;
    }
}
