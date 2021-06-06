package twisk.mondeIG;

import javafx.concurrent.Task;
import twisk.ClientTwisk;
import twisk.designPattern.Observateur;
import twisk.designPattern.SujetObserve;
import twisk.exceptions.*;
import twisk.monde.Activite;
import twisk.monde.ActiviteRestreinte;
import twisk.monde.Guichet;
import twisk.monde.Monde;
import twisk.outils.ClassLoaderPerso;
import twisk.outils.CorrespondanceEtapes;
import twisk.outils.FabriqueIdentifiant;
import twisk.outils.GestionnaireThreads;
import twisk.simulation.GestionnaireClients;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * La classe MondeIG.
 */
public class MondeIG extends SujetObserve implements Observateur, Serializable {

    private static final long serialVersionUID = 1L;

    private final HashMap<String, EtapeIG> etapes;
    private final ArrayList<EtapeIG> etapesSelectionnees;
    private final ArrayList<ArcIG> arcs;
    private int style;
    private transient CorrespondanceEtapes corE;
    private transient Object simulation;
    private int nbClients;
    private final FabriqueIdentifiant fabId;
    private String nom;
    private String loi;

    /**
     * Constructeur de la classe MondeIG.
     */
    public MondeIG() {
        super();
        fabId = FabriqueIdentifiant.getInstance();
        etapes = new HashMap<>(10);
        etapesSelectionnees = new ArrayList<>(10);
        arcs = new ArrayList<>(10);
        String id = fabId.getIdentifiantEtape();
        ActiviteIG activite = new ActiviteIG("Activite" + id, id);
        this.etapes.put(id, activite);
        this.style = 2;
        this.nbClients = 5;
        this.nom = "Monde";
        this.loi = "Uni";
    }

    /**
     * Procédure qui ajoute le type d'activité à une activité du monde.
     *
     * @param type le type
     */
    public void ajouter(String type) {
        String id = fabId.getIdentifiantEtape();
        switch (type) {
            case "Activite":
                ActiviteIG activite = new ActiviteIG("Activite" + id, id);
                this.etapes.put(id, activite);
                this.notifierObservateurs();
                break;

            case "Guichet":
                GuichetIG guichet = new GuichetIG("Guichet" + id, id);
                this.etapes.put(id, guichet);
                this.notifierObservateurs();
                break;
        }
    }

    /**
     * Itérateur d'étape. On peut donc itérer sur les étapes contenues dans un monde.
     *
     * @return l 'iterator
     */
    public Iterator<EtapeIG> iterator() {
        return etapes.values().iterator(); //On itère sur les valeurs de la HashMap.
    }

    /**
     * Fonction qui retourne le nombre d'étapes contenue dans le monde.
     *
     * @return l 'int
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
     * Procédure qui ajoute un arc au monde.
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
        if (pdc1.getEtapeRattache() == pdc2.getEtapeRattache())
            throw new SameActivityException("Vous ne pouvez pas, créer d'arcs entre 2 points de controle identiques! où créer un arc entre 2 points d'une même étape!");
        if (pdc1.getEtapeRattache().estAccessibleDepuis(pdc2.getEtapeRattache()))
            throw new CreateLoopException("On peut pas créer un circuit entre deux étapes !");
        if (pdc2.getEtapeRattache().estUnGuichet()) {
            if (pdc2.getEtapeRattache().getNbPrec() == 0)
                pdc2.getEtapeRattache().siEstUnGuichetSetVersLaDroite(pdc2.equals(pdc2.getEtapeRattache().getPdcIndex(3)));
            else {
                if (pdc2.getEtapeRattache().siEstUnGuichetGetVersLaDroite() && !(pdc2.equals(pdc2.getEtapeRattache().getPdcIndex(3))))
                    throw new WrongDirectionException("On ne peut pas créer d'arc vers un guichet dans le sens inverse du guichet !");
                if (!pdc2.getEtapeRattache().siEstUnGuichetGetVersLaDroite() && pdc2.equals(pdc2.getEtapeRattache().getPdcIndex(3)))
                    throw new WrongDirectionException("On ne peut pas créer d'arc vers un guichet dans le sens inverse du guichet !");
            }
        }
        ArcIG ark = new ArcIG(pdc1, pdc2);
        this.arcs.add(ark);
    }

    /**
     * Itérateur d'arcs. On peut donc itérer sur les arcs contenues dans un monde.
     *
     * @return l 'iterator
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
                    etape.ajouterSuccesseur(pdc.getEtapeRattache());
                    pdc.getEtapeRattache().incrementeNbPrec();
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
     * @return l 'etape indice
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
    public void supprimerLaSelection() throws PasUnGuichetException {
        for (Iterator<EtapeIG> iter = iterator(); iter.hasNext(); ) {
            supprimer(iter);
        }
        for (Iterator<ArcIG> iterA = iteratorArcs(); iterA.hasNext(); ) {
            ArcIG arc = iterA.next();
            if (arc.isSelected()) {
                arc.setSelect(false);
                arc.getEtapePdcDepart().supprimerSuccesseur(arc.getEtapePdcArrive());
                arc.getEtapePdcArrive().decrementeNbPrec();
                if (arc.getEtapePdcArrive().estUnGuichet())
                    if (arc.getEtapePdcArrive().getNbPrec() == 0)
                        arc.getEtapePdcArrive().siEstUnGuichetSetVersLaDroite(null);
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

    /**
     * Procédure qui permet de définir le nombre de jeton(s) d'un guichet dans le monde
     *
     * @param nbJetons le nombre de jeton(s)
     * @throws UncorrectSettingsException la uncorrect settings exception
     */
    public void setTokens(int nbJetons) throws UncorrectSettingsException, PasUnGuichetException {
        try {
            if (nbJetons <= 0) {
                throw new UncorrectSettingsException("Attention, un nombre de jeton(s) ne peut pas être nul ou négatif!");
            }
            for (Iterator<EtapeIG> iter = iterator(); iter.hasNext(); ) {
                EtapeIG eta = iter.next();
                if (this.isSelectionned(eta) && eta.estUnGuichet()) {
                    eta.siEstUnGuichetSetNbJetons(nbJetons);
                }
            }
        } catch (NumberFormatException nFE) {
            throw new UncorrectSettingsException("Les paramètres saisis pour le nombre de jetons sont erronés!");
        }
        notifierObservateurs();
        this.effacerLaSelection();
    }

    /**
     * Fonction qui retourne vrai si les étapes séléctionées sont des guichets
     *
     * @return un booléen
     */
    public boolean etapesSelectionneesSontDesGuichets() {
        boolean sontDesGuichets = true;
        for (Iterator<EtapeIG> iter = iterator(); iter.hasNext(); ) {
            EtapeIG e = iter.next();
            if (this.isSelectionned(e) && e.estUneActivite()) {
                sontDesGuichets = false;
            }
        }
        return sontDesGuichets;
    }

    /**
     * Procédure qui verifie si le monde est valide.
     *
     * @throws MondeException Erreur lors de la création du monde.
     */
    private void verifierMondeIG() throws MondeException {
        boolean aUneEntree = false;
        boolean aUneSortie = false;
        boolean aUneEtape = this.nbEtapes() != 0;
        boolean mankUnSucc = false; //Une étape n'a pas de succ
        boolean nePossedePasUneActiviteRestreinte = false;
        boolean transformationEffectuee = true;

        for (Iterator<EtapeIG> iter = iterator(); iter.hasNext(); ) {
            EtapeIG e = iter.next();
            if (e.estUneEntree())
                aUneEntree = true;
            if (e.estUneSortie())
                aUneSortie = true;
            else if (!e.possedeUnSuccesseur())
                mankUnSucc = true;
            if (e.estUnGuichet())
                if (!e.estSuivieDUneActivite())
                    nePossedePasUneActiviteRestreinte = true;
                else
                    transformationEffectuee = e.actSuccIntoActRes();
        }

        if (!aUneEntree || !aUneSortie || !aUneEtape)
            throw new MondeException("Le monde ne possède pas d'entrée, de sortie ou d'étape");
        if (mankUnSucc)
            throw new MondeException("Une étape n'a pas de successeur");
        if (nePossedePasUneActiviteRestreinte)
            throw new MondeException("Un guichet n'est pas suivie par une activité");
        if (!transformationEffectuee)
            throw new MondeException("Transformartion d'une activité échouée");
    }

    /**
     * Fonction qui crée un Monde à partir du mondeIG actuel
     *
     * @return Le monde
     */
    public Monde creerMonde() throws PasUnGuichetException {
        this.corE = new CorrespondanceEtapes();
        Monde monde = new Monde();
        for (Iterator<EtapeIG> iter = iterator(); iter.hasNext(); ) {
            EtapeIG e = iter.next();
            if (e.estUneActiviteRestreinte()) {
                ActiviteRestreinte actR = new ActiviteRestreinte(e.getNom(), e.getDelai(), e.getEcart());
                monde.ajouter(actR);
                corE.ajouter(e, actR);
            } else if (e.estUneActivite()) {
                Activite act = new Activite(e.getNom(), e.getDelai(), e.getEcart());
                monde.ajouter(act);
                corE.ajouter(e, act);
            } else if (e.estUnGuichet()) {
                if (e.estUneEntree() && e.getNbPrec() == 0) {
                    for (Iterator<ArcIG> iterArc = this.iteratorArcs(); iterArc.hasNext(); ) {
                        ArcIG arcIG = iterArc.next();
                        if (arcIG.getEtapePdcDepart().equals(e))
                            e.siEstUnGuichetSetVersLaDroite(arcIG.getPdcDepart().equals(e.getPdcIndex(2)));
                        System.out.println(e.siEstUnGuichetGetVersLaDroite());
                    }
                }
                Guichet gui = new Guichet(e.getNom(), e.siEstUnGuichetGetNbJetons());
                monde.ajouter(gui);
                corE.ajouter(e, gui);
            }
            if (e.estUneEntree())
                monde.aCommeEntree(corE.get(e));
            if (e.estUneSortie())
                monde.aCommeSortie(corE.get(e));
        }
        //On fait une autre boucle pour les successeurs car il faut attendre que toutes les étapes soient implémentées
        for (Iterator<EtapeIG> iter = iterator(); iter.hasNext(); ) {
            EtapeIG e = iter.next();
            if (e.possedeUnSuccesseur()) {
                ArrayList<EtapeIG> succ = e.getSucc();
                for (EtapeIG eIG : succ) {
                    monde.getEtapeI(corE.get(e).getNum()).ajouterSuccesseur(corE.get(eIG));
                }
            }
        }
        monde.getSasE().setLoi(loi);
        return monde;
    }

    /**
     * Procédure qui crée le monde et lance la simulation
     *
     * @throws MondeException the monde exception
     */
    public void simuler() throws MondeException, PasUnGuichetException {
        verifierMondeIG();
        Monde monde = creerMonde();
        ClassLoaderPerso classLoaderPerso = new ClassLoaderPerso(ClientTwisk.class.getClassLoader());
        try {
            //On récupère une instance de Simulation par le biais de notre ClassLoader
            Class<?> clS = classLoaderPerso.loadClass("twisk.simulation.Simulation");
            Class<?> clS1 = classLoaderPerso.loadClass("twisk.simulation.Simulation$1");
            Constructor<?> constructeur = clS.getDeclaredConstructor();
            Object instanceSim = constructeur.newInstance();

            //On stocke l'instance dans la classe et on définit la classe comme observateur de l'instance de Simulation
            this.simulation = instanceSim;
            Method procedureAjouterObs = clS.getMethod("ajouterObservateur", Observateur.class);
            procedureAjouterObs.invoke(this.simulation, this);
            //On lance la simulation
            Method fonctionSetNbClients = clS.getMethod("setNbClients", int.class);
            fonctionSetNbClients.invoke(instanceSim, getNbClients());
            Method fonctionSimuler = clS.getMethod("simuler", Monde.class);
            fonctionSimuler.invoke(instanceSim, monde);
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur lors du chargement de la classe twisk.simulation.Simulation !");
        } catch (NoSuchMethodException e) {
            System.out.println("Erreur, fonction non trouvée dans la classe twisk.simulation.Simulation !");
        } catch (InvocationTargetException e) {
            System.out.println("Erreur lors de l'appel d'une fonction de la classe twisk.simulation.Simulation !");
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            System.out.println("Erreur, appel illegal d'une fonction de la classe twisk.simulation.Simulation !");
        } catch (InstantiationException e) {
            System.out.println("Erreur lors de l'instanciation de la classe twisk.simulation.Simulation !");
        }
    }

    /**
     * Fonction qui retourne la correspondance étape du monde
     *
     * @return la correspondance étape
     */
    public CorrespondanceEtapes getCorE() {
        return corE;
    }

    /**
     * Gets gestionnaire client de simulation.
     *
     * @return the gestionnaire client de simulation
     */
    public GestionnaireClients getGestionnaireClientDeSimulation() {
        GestionnaireClients ge = null;
        if (simulationACommencee()) { //On vérifie si la simulation est déjà commencée
            try {
                Method fonctionGetGestClient = simulation.getClass().getMethod("getGestCli");
                ge = (GestionnaireClients) fonctionGetGestClient.invoke(this.simulation); //On donne l'objet sur lequel on appel la fonction fonctionGetGestClient soit ici this.simulation.
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return ge;
    }

    /**
     * Fonction qui retourne true si la simulation à commencée
     *
     * @return un booléen
     */
    public boolean simulationACommencee() {
        boolean isSimuled = this.simulation != null;
        if (isSimuled) {
            try {
                Method fonctionEstSimulee = simulation.getClass().getMethod("isEnCoursDeSimulation");
                isSimuled = (boolean) fonctionEstSimulee.invoke(this.simulation);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return isSimuled;
    }

    @Override
    public void reagir() {
        notifierObservateurs();
    }

    /**
     * Procédure qui arrête la simulation
     */
    public void lavageDesClients() {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
                GestionnaireThreads.getInstance().detruireTout();
                return null;
            }
        };
        GestionnaireThreads.getInstance().lancer(task);
    }

    /**
     * Fonction qui retourne le nombre de clients utilisés dans la simulation actuelle ou à venir.
     *
     * @return le nombre de clients
     */
    public int getNbClients() {
        return nbClients;
    }

    /**
     * Procédure qui change le nombre de clients utilisés dans la simulation actuelle ou à venir.
     *
     * @param nbClients Le nouveau nombre de clients
     * @throws UncorrectSettingsException exception déclenchée quand le nombre de clients est incorrect.
     */
    public void setNbClients(int nbClients) throws UncorrectSettingsException {
        try {
            if (nbClients <= 0 || nbClients > 49)
                throw new UncorrectSettingsException("Attention, un nombre de jeton(s) ne peut pas être nul, négatif ou supérieur à 49 !");
            this.nbClients = nbClients;
        } catch (NumberFormatException nFE) {
            throw new UncorrectSettingsException("Les paramètres saisis pour le nombre de jetons sont erronés!");
        }
        notifierObservateurs();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setLoi(String loi) {
        this.loi = loi;
        notifierObservateurs();
    }

    public String getLoi() {
        return loi;
    }
}
