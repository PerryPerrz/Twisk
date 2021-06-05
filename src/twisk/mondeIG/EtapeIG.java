package twisk.mondeIG;

import twisk.exceptions.PasUnGuichetException;
import twisk.outils.FabriqueIdentifiant;
import twisk.outils.TailleComposants;

import java.util.ArrayList;
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
     * Attribut correspondant aux successeurs de l'étape.
     */
    protected ArrayList<EtapeIG> succ;
    /**
     * Attribut correspondant au nombre d'étapes qui précèdent cette étape.
     */
    protected int nbPrec;

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
        this.nbPrec = 0;
        this.delai = 8;
        this.ecart = 4;
        this.succ = new ArrayList<>(2);
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
        if (this.estUneActivite()) {
            pdc[0].setCentre(this.posX + tc.getLargAct() / 2, this.posY - tc.getRad()); //Haut
            pdc[1].setCentre(this.posX + tc.getLargAct() / 2, this.posY + tc.getHautAct() + tc.getRad() - tc.getMargeSelection()); //Bas
            pdc[2].setCentre(this.posX - tc.getRad(), this.posY + tc.getHautAct() / 2 + tc.getMargeSelection() / 2); //Gauche
            pdc[3].setCentre(this.posX + tc.getLargAct() + tc.getRad() - tc.getMargeSelection(), this.posY + tc.getHautAct() / 2 + tc.getMargeSelection() / 2); //Droite
        } else {
            pdc[0].setCentre(this.posX + tc.getLargGuichet() / 2, this.posY - tc.getRad());
            pdc[1].setCentre(this.posX + tc.getLargGuichet() / 2, this.posY + tc.getHautGuichet() + tc.getRad() + tc.getMargeSelection());
            pdc[2].setCentre(this.posX - tc.getRad(), this.posY + tc.getHautGuichet() / 2 + tc.getMargeSelection() / 2);
            pdc[3].setCentre(this.posX + tc.getLargGuichet() + tc.getRad() - tc.getMargeSelection(), this.posY + tc.getHautGuichet() / 2 + tc.getMargeSelection() / 2);
        }
    }

    /**
     * Fonction qui retourne l'identifiant d'une étape.
     *
     * @return l 'identifiant
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

        this.raffraichissementPdc();
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
     * @return l 'ecart
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

    /**
     * Fonction qui retourne vrai si l'étape est une activité.
     *
     * @return un booléen
     */
    public boolean estUneActivite() {
        return false;
    }

    /**
     * Fonction qui retourne vrai si l'étape est une activitéRestreinte.
     *
     * @return un booléen
     */
    public boolean estUneActiviteRestreinte() {
        return false;
    }

    /**
     * Fonction qui retourne vrai si l'étape est un guichet.
     *
     * @return un booléen
     */
    public boolean estUnGuichet() {
        return false;
    }

    /**
     * Fonction qui retourne -1 si l'étape est n'est pas un guichet, et le nombre de jeton(s) si l'tétape est un guichet.
     *
     * @return un entier
     */
    public abstract int siEstUnGuichetGetNbJetons();

    /**
     * Procédure qui permet de set le nombre de jetons d'un guichet si l'étape concernée est bien un guichet
     *
     * @param nbJetons le nombre de jetons
     * @throws PasUnGuichetException Exception se déclenchant quand on essaie de changer les paramètres d'un guichet sur une activité
     */
    public abstract void siEstUnGuichetSetNbJetons(int nbJetons) throws PasUnGuichetException;

    /**
     * Procédure qui permet de set le sens d'un guichet si l'étape concernée est bien un guichet
     *
     * @param versLaDroite booléen indiquant si le sens du guichet est vers la droite
     * @throws PasUnGuichetException Exception se déclenchant quand on essaie de changer les paramètres d'un guichet sur une activité
     */
    public abstract void siEstUnGuichetSetVersLaDroite(Boolean versLaDroite) throws PasUnGuichetException;

    /**
     * Procédure qui ajoute une étape en tant que successeur
     *
     * @param e l'étape
     */
    public void ajouterSuccesseur(EtapeIG e) {
        succ.add(e);
    }

    /**
     * Procédure qui supprime un successeur de l'étape
     *
     * @param e l'étape
     */
    public void supprimerSuccesseur(EtapeIG e) {
        succ.remove(e);
    }

    /**
     * Fonction qui retourne vrai si l'étape concernée possède un successeur
     *
     * @return un booléen
     */
    public boolean possedeUnSuccesseur() {
        return succ.size() != 0;
    }

    /**
     * Fonction qui retourne vrai si l'étape concernée est suivie d'une activité
     *
     * @return un booléen
     */
    public boolean estSuivieDUneActivite() {
        boolean res = false;
        for (EtapeIG e : succ)
            if (e.estUneActivite())
                res = true;
        return res;
    }

    /**
     * Procédure qui permet de transformer une activité en activité restreinte
     *
     * @param res un booléen représentant le fait que l'étape soit une activité restreinte ou non.
     * @return un booléen, faux si il y a une erreur (c'est un guichet) et vrai si c'est une activité
     */
    public abstract boolean setActiviteRestreinte(boolean res);

    /**
     * Fonction qui transforme les activités qui succedent une étape en activités restreintes
     *
     * @return un booléen, faux si il y a une erreur, la transformation à échouer et vrai si tout c'est bien passé
     */
    public boolean actSuccIntoActRes() {
        boolean res = true;
        for (EtapeIG e : succ)
            if (e.estUneActivite())
                res = e.setActiviteRestreinte(true);
        return res;
    }

    /**
     * Fonction qui retourne les successeurs de l'étape
     *
     * @return une arrayList contenant les successeurs
     */
    public ArrayList<EtapeIG> getSucc() {
        return succ;
    }

    /**
     * Fonction qui regarde si l'étape est accessible par l'étape donnée en paramêtres.
     *
     * @param etape l'étape donnée
     * @return un booléen indiquant si elle est accessible ou non
     */
    public boolean estAccessibleDepuis(EtapeIG etape) {
        if (etape.equals(this))
            return true;
        if (etape.possedeUnSuccesseur())
            for (EtapeIG e : etape.getSucc())
                if (this.estAccessibleDepuis(e))
                    return true;
        return false;
    }

    /**
     * Fonction qui retourne le nombre de prédécesseurs de l'étape.
     *
     * @return le nombre de prédécesseurs
     */
    public int getNbPrec() {
        return nbPrec;
    }

    /**
     * Procédure qui incrémente le nombre de prédécesseurs de l'étape.
     */
    public void incrementeNbPrec() {
        nbPrec++;
    }

    /**
     * Procédure qui décrémente le nombre de prédécesseurs de l'étape.
     */
    public void decrementeNbPrec() {
        nbPrec--;
    }

    public abstract Boolean siEstUnGuichetGetVersLaDroite();
}