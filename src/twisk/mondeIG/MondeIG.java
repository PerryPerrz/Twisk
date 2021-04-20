package twisk.mondeIG;

import twisk.designPattern.SujetObserve;
import twisk.exceptions.*;
import twisk.outils.FabriqueIdentifiant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * La classe MondeIG.
 */
public class MondeIG extends SujetObserve {
    private final HashMap<String, EtapeIG> etapes;
    private final ArrayList<EtapeIG> etapesSelectionnees;
    private final ArrayList<ArcIG> arcs;
    private int style;

    /**
     * Constructeur de la classe MondeIG.
     */
    public MondeIG() {
        super();
        FabriqueIdentifiant fabID = FabriqueIdentifiant.getInstance();
        etapes = new HashMap<>(10);
        etapesSelectionnees = new ArrayList<>(10);
        arcs = new ArrayList<>(10);
        String id = fabID.getIdentifiantEtape();
        ActiviteIG activite = new ActiviteIG("Activité n°" + id, id);
        this.etapes.put(id, activite);
        this.style = 2;
    }

    /**
     * Procédure qui ajoute le type d'activité à une activité du monde.
     *
     * @param type le type
     */
    public void ajouter(String type) {
        switch (type) {
            case "Activite":
                FabriqueIdentifiant fabID = FabriqueIdentifiant.getInstance();
                String id = fabID.getIdentifiantEtape();
                ActiviteIG activite = new ActiviteIG("Activité n°" + id, id);
                this.etapes.put(id, activite);
                this.notifierObservateurs();
        }
    }

    /**
     * Itérateur d'étape. On peut donc itérer sur les étapes contenues dans un monde.
     *
     * @return l'iterator
     */
    public Iterator<EtapeIG> iterator() {
        return etapes.values().iterator(); //On itère sur les valeurs de la HashMap.
    }

    /**
     * Fonction qui retourne le nombre d'étapes contenue dans le monde.
     *
     * @return l'int
     */
//Fonctions nécessaires aux tests de MondeIG (fonction "ajouter", "iterator")
    public int nbEtapes() {
        int cpt = 0;
        for (int i = 0; i < this.etapes.size(); ++i) {
            cpt += 1;
        }
        return cpt;
    }

    /**
     * Procédure qui ajoute 2 points de contrôles au monde.
     *
     * @param pdc1 le pdc 1
     * @param pdc2 le pdc 2
     * @throws TwiskException le twisk exception
     */
    public void ajouter(PointDeControleIG pdc1, PointDeControleIG pdc2) throws TwiskException {
        for (Iterator<ArcIG> it = this.iteratorArcs(); it.hasNext(); ) {
            ArcIG arc = it.next();
            //Exactement le même arc où exactement l'opposé de cet arc
            if ((arc.getPdcDepart().getId().equals(pdc1.getId()) && arc.getPdcArrive().getId().equals(pdc2.getId()))) {
                throw new ArcAlreadyCreateException("On ne peut pas créer un arc déjà créer!");
            }
            if (arc.getPdcArrive().getId().equals(pdc1.getId()) || arc.getPdcDepart().getId().equals(pdc2.getId())) {
                throw new CreateArcWithEndPdcException("Un arc ne peut pas partir du point d'arrivé d'un autre arc!");
            }
        }
        if (pdc1.getEtapeRattache() == pdc2.getEtapeRattache()) {
            throw new SameActivityException("Vous ne pouvez pas, créer d'arcs entre 2 points de controle identiques! où créer un arc entre 2 points d'une même étape!");
        }
        ArcIG ark = new ArcIG(pdc1, pdc2);
        this.arcs.add(ark);
    }

    /**
     * Itérateur d'arcs. On peut donc itérer sur les arcs contenues dans un monde.
     *
     * @return l'iterator
     */
    public Iterator<ArcIG> iteratorArcs() {
        return arcs.iterator();
    }

    /**
     * Procédure qui créer les arcs d'un monde.
     *
     * @param pdc le pdc
     * @throws TwiskException le twisk exception
     */
    public void creationArc(PointDeControleIG pdc) throws TwiskException {
        boolean isCreated = false;
        for (Iterator<EtapeIG> iter = iterator(); iter.hasNext(); ) {
            EtapeIG etape = iter.next();
            for (PointDeControleIG pdcIG : etape) {
                //Je cherche dans tous les pdc si il y en a un qui est true, le pdc en paramètre est le 2éme pdc qu'on à cliqué
                //Si j'en trouve un, cela signifie que celui en paramètre est le second
                if (pdcIG.isClicked()) {
                    pdcIG.setClicked();
                    isCreated = true;
                    ajouter(pdcIG, pdc);
                    this.notifierObservateurs();
                }
                //Si j'en trouve pas, le pdc en param est le premier.
            }
        }
        if (!isCreated) {
            pdc.setClicked();
        }
    }

    /**
     * Fonction qui retourne une étape du monde à l'aide d'un indice.
     *
     * @param indice l'indice
     * @return l'etape indice
     */

    public EtapeIG getEtapeIndice(String indice) {
        return this.etapes.get(indice);
    }

    /**
     * Fonction qui retourne le nombre d'arcs d'un monde.
     *
     * @return le nb arcs
     */
    public int getNbArcs() {
        return this.arcs.size();
    }

    /**
     * Procédure qui ajoute les étapes sélectionnées. (Cela permet de les sauvegarder dans une collection)
     *
     * @param etape l'etape
     */
    public void ajouterEtapeSelectionnee(EtapeIG etape) {
        if (isSelectionned(etape)) {
            etapesSelectionnees.remove(etape);
        } else {
            etapesSelectionnees.add(etape);
        }
        this.notifierObservateurs();
    }

    /**
     * Fonction qui retourne vrai si l'étape donnée en paramètre est selectionnée.
     *
     * @param etape l'étape
     * @return le boolean
     */
    public boolean isSelectionned(EtapeIG etape) {
        return etapesSelectionnees.contains(etape);
    }

    /**
     * Procédure qui supprime la sélection d'arcs dans le monde.
     */
    public void supprimerLaSelection() {
        for (Iterator<EtapeIG> iter = iterator(); iter.hasNext(); ) {
            supprimer(iter);
        }
        for (Iterator<ArcIG> iterA = iteratorArcs(); iterA.hasNext(); ) {
            ArcIG arc = iterA.next();
            if (arc.isSelected()) {
                arc.setSelect(false);
                iterA.remove();
                this.arcs.remove(arc);
            }
        }
        this.notifierObservateurs();
    }

    /**
     * Procédure qui supprime les arcs liés à une étape lorsqu'ils sont sélectionnées.
     *
     * @param iterE l'itérateur d'étape
     */
    public void supprimer(Iterator<EtapeIG> iterE) {
        ArcIG arc;
        EtapeIG e = iterE.next();
        if (isSelectionned(e)) {
            for (Iterator<ArcIG> iter = this.iteratorArcs(); iter.hasNext(); ) {
                arc = iter.next();
                if (arc.isLinkedToStage(e)) {
                    iter.remove();
                    arcs.remove(arc);
                }
            }
            iterE.remove();
            etapesSelectionnees.remove(e);
            etapes.remove(e.getIdentifiant());
        }
    }

    /**
     * Procédure qui permet de renommer une activité.
     *
     * @param newName le nouveau nom
     */
    public void renommerLaSelection(String newName) {
        for (Iterator<EtapeIG> iter = iterator(); iter.hasNext(); ) {
            EtapeIG e = iter.next();
            if (this.isSelectionned(e)) {
                e.setNom(newName);
            }
        }
        this.effacerLaSelection();
    }

    /**
     * Fonction qui retourne le nombre d'étape(s) sélectionnée(s) dans le monde.
     *
     * @return int le nombre d'étape sélectionnées
     */
    public int nbEtapesSelectionnees() {
        int cpt = 0;
        for (Iterator<EtapeIG> iter = iterator(); iter.hasNext(); ) {
            EtapeIG e = iter.next();
            if (this.isSelectionned(e)) {
                cpt++;
            }
        }
        return cpt;
    }

    /**
     * Procédure qui permet de changer l'emplacement d'une étape dans le monde.
     *
     * @param indice l'indice
     * @param x      Coordonnée X de l'emplacement de l'étape
     * @param y      Coordonnée Y de l'emplacement de l'étape
     */
    public void changerEmplacementEtape(String indice, int x, int y) {
        this.getEtapeIndice(indice).setPosXPosY(x, y);
        this.notifierObservateurs();
    }

    /**
     * Procédure qui permet de sélectionner un arc dans le monde.
     *
     * @param arc l'arc
     */
    public void selectionArc(ArcIG arc) {
        arc.setSelect(!arc.isSelected());
        notifierObservateurs();

    }

    /**
     * Fonction qui retourne vrai lorsque un arc est sélectionné.
     *
     * @param arc l'arc
     * @return le boolean
     */
    public boolean isSelectionned(ArcIG arc) {
        return arc.isSelected();
    }

    /**
     * Procédure qui permet d'effacer la sélection d'un arc.
     */
    public void effacerLaSelection() {
        etapesSelectionnees.clear();
        for (Iterator<ArcIG> iterA = iteratorArcs(); iterA.hasNext(); ) {
            ArcIG arc = iterA.next();
            if (arc.isSelected()) {
                arc.setSelect(false);
            }
        }
        notifierObservateurs();
    }

    /**
     * Procédure qui permet de rendre entrée une activité du monde.
     */
    public void setEntree() {
        for (Iterator<EtapeIG> iter = iterator(); iter.hasNext(); ) {
            EtapeIG e = iter.next();
            if (this.isSelectionned(e)) {
                e.invEntree();
            }
        }
        notifierObservateurs();
        this.effacerLaSelection();
    }

    /**
     * Procédure qui permet de renre sortie une activité du monde.
     */
    public void setSortie() {
        for (Iterator<EtapeIG> iter = iterator(); iter.hasNext(); ) {
            EtapeIG e = iter.next();
            if (this.isSelectionned(e)) {
                e.invSortie();
            }
        }
        notifierObservateurs();
        this.effacerLaSelection();
    }

    /**
     * Procédure qui permet de set le délai d'une activité du monde.
     *
     * @param d La valeur du délai
     * @throws UncorrectSettingsException la uncorrect settings exception
     */
    public void setDelai(String d) throws UncorrectSettingsException {
        try {
            int dBis = Integer.parseInt(d);
            if (dBis < 0) {
                throw new UncorrectSettingsException("Attention, un délai ne peut pas être négatif!");
            }
            for (Iterator<EtapeIG> iter = iterator(); iter.hasNext(); ) {
                EtapeIG eta = iter.next();
                if (this.isSelectionned(eta)) {
                    if (dBis < eta.getEcart()) {
                        throw new UncorrectSettingsException("Attention, un délai ne peut pas être inférieur à un écart!");
                    }
                    eta.setDelai(dBis);
                }
            }
        } catch (NumberFormatException nFE) {
            throw new UncorrectSettingsException("Les paramètres saisis pour le délai sont erronés!");
        }
        notifierObservateurs();
        this.effacerLaSelection();
    }

    /**
     * Procédure qui permet de définir l'écart d'une activité du monde.
     *
     * @param e La valeur de l'écart
     * @throws UncorrectSettingsException la uncorrect settings exception
     */
    public void setEcart(String e) throws UncorrectSettingsException {
        try {
            int eBis = Integer.parseInt(e);
            if (eBis < 0) {
                throw new UncorrectSettingsException("Attention, un écart ne peut pas être négatif!");
            }
            for (Iterator<EtapeIG> iter = iterator(); iter.hasNext(); ) {
                EtapeIG eta = iter.next();
                if (this.isSelectionned(eta)) {
                    if (eBis > eta.getDelai()) {
                        throw new UncorrectSettingsException("Attention, un écart ne peut pas être supérieur à un délai!");
                    }
                    eta.setEcart(eBis);
                }
            }
        } catch (NumberFormatException nFE) {
            throw new UncorrectSettingsException("Les paramètres saisis pour l'écart sont erronés!");
        }
        notifierObservateurs();
        this.effacerLaSelection();
    }

    /**
     * Procédure qui permet de set le style de l'application.
     *
     * @param i la valeur du style
     */
    public void setStyle(int i) {
        this.style = i;
        notifierObservateurs();
    }

    /**
     * Fonction qui retourne le style de l'application.
     *
     * @return le style
     */
    public int getStyle() {
        return this.style;
    }
}
