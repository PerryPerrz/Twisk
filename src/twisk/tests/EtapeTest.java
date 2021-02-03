package twisk.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.monde.*;

import static org.junit.jupiter.api.Assertions.*;

class EtapeTest {

    Activite act1,act2,act3;
    Guichet gui1,gui2,gui3;
    ActiviteRestreinte actRes1, actRes2, actRes3;
    SasEntree sasE1, sasE2, sasE3;
    SasSortie sasS1, sasS2, sasS3;


    @BeforeEach
    void setUp() {
        act1 = new Activite("act1");
        act2 = new Activite("act2",10,2);
        act3 = new Activite("act3");
        gui1 = new Guichet("gui1");
        gui2 = new Guichet("gui2", 10);
        gui3 = new Guichet("gui3");
        actRes1 = new ActiviteRestreinte("actRes1");
        actRes2 = new ActiviteRestreinte("actRes2", 10, 2);
        actRes3 = new ActiviteRestreinte("actRes3");
        sasE1 = new SasEntree();
        sasE2 = new SasEntree();
        sasE3 = new SasEntree();
        sasS1 = new SasSortie();
        sasS2 = new SasSortie();
        sasS3 = new SasSortie();
    }

    @Test
    void nbSuccesseur() {
        //On ajoute des activités à des activités
        assertEquals(act1.nbSuccesseurs(),0);
        act1.ajouterSuccesseur(act2);
        assertEquals(act1.nbSuccesseurs(),1);
        act1.ajouterSuccesseur(act3);
        assertEquals(act1.nbSuccesseurs(),2);

        //on ajoute des guichets à des guichets
        assertEquals(gui1.nbSuccesseurs(),0);
        gui1.ajouterSuccesseur(gui2);
        assertEquals(gui1.nbSuccesseurs(),1);
        gui1.ajouterSuccesseur(gui3);
        assertEquals(gui1.nbSuccesseurs(),2);

        //On ajoute des guichets à des activités
        assertEquals(act1.nbSuccesseurs(),2);
        act1.ajouterSuccesseur(gui1);
        assertEquals(act1.nbSuccesseurs(),3);
        act1.ajouterSuccesseur(gui3);
        assertEquals(act1.nbSuccesseurs(),4);

        //on ajoute des activités à des guichets
        assertEquals(gui1.nbSuccesseurs(),2);
        gui1.ajouterSuccesseur(act1);
        assertEquals(gui1.nbSuccesseurs(),3);
        gui1.ajouterSuccesseur(act3);
        assertEquals(gui1.nbSuccesseurs(),4);

        //on ajoute des activités restreintes à des activités
        assertEquals(act1.nbSuccesseurs(),4);
        act1.ajouterSuccesseur(actRes1);
        assertEquals(act1.nbSuccesseurs(),5);
        act1.ajouterSuccesseur(actRes3);
        assertEquals(act1.nbSuccesseurs(),6);

        //on ajoute des activités à des activités restreintes
        assertEquals(actRes1.nbSuccesseurs(),0);
        actRes1.ajouterSuccesseur(act1);
        assertEquals(actRes1.nbSuccesseurs(),1);
        actRes1.ajouterSuccesseur(act3);
        assertEquals(actRes1.nbSuccesseurs(),2);

        //on ajoute des activités restreintes à des guichets
        assertEquals(gui1.nbSuccesseurs(),4);
        gui1.ajouterSuccesseur(actRes1);
        assertEquals(gui1.nbSuccesseurs(),5);
        gui1.ajouterSuccesseur(actRes3);
        assertEquals(gui1.nbSuccesseurs(),6);

        //on ajoute des guichets à des activités restreintes
        assertEquals(actRes1.nbSuccesseurs(),2);
        actRes1.ajouterSuccesseur(gui1);
        assertEquals(actRes1.nbSuccesseurs(),3);
        actRes1.ajouterSuccesseur(gui3);
        assertEquals(actRes1.nbSuccesseurs(),4);

        //on ajoute des sas d'entrées à des guichets
        assertEquals(gui1.nbSuccesseurs(),6);
        gui1.ajouterSuccesseur(sasE1);
        assertEquals(gui1.nbSuccesseurs(),7);
        gui1.ajouterSuccesseur(sasE2);
        assertEquals(gui1.nbSuccesseurs(),8);

        //on ajoute des guichets à des sas d'entrées
        assertEquals(sasE1.nbSuccesseurs(),0);
        sasE1.ajouterSuccesseur(gui1);
        assertEquals(sasE1.nbSuccesseurs(),1);
        sasE1.ajouterSuccesseur(gui3);
        assertEquals(sasE1.nbSuccesseurs(),2);

        //on ajoute des sas de sorties à des guichets
        assertEquals(gui1.nbSuccesseurs(),8);
        gui1.ajouterSuccesseur(sasS1);
        assertEquals(gui1.nbSuccesseurs(),9);
        gui1.ajouterSuccesseur(sasS2);
        assertEquals(gui1.nbSuccesseurs(),10);

        //on ajoute des guichets à des sas de sorties
        assertEquals(sasS1.nbSuccesseurs(),0);
        sasS1.ajouterSuccesseur(gui1);
        assertEquals(sasS1.nbSuccesseurs(),1);
        sasS1.ajouterSuccesseur(gui3);
        assertEquals(sasS1.nbSuccesseurs(),2);

        //on ajoute des sas d'entrées à des activités
        assertEquals(act1.nbSuccesseurs(),6);
        act1.ajouterSuccesseur(sasE1);
        assertEquals(act1.nbSuccesseurs(),7);
        act1.ajouterSuccesseur(sasE2);
        assertEquals(act1.nbSuccesseurs(),8);

        //on ajoute des activités à des sas d'entrées
        assertEquals(sasE1.nbSuccesseurs(),2);
        sasE1.ajouterSuccesseur(act1);
        assertEquals(sasE1.nbSuccesseurs(),3);
        sasE1.ajouterSuccesseur(act3);
        assertEquals(sasE1.nbSuccesseurs(),4);

        //on ajoute des sas de sorties à des activités
        assertEquals(act1.nbSuccesseurs(),8);
        act1.ajouterSuccesseur(sasS1);
        assertEquals(act1.nbSuccesseurs(),9);
        act1.ajouterSuccesseur(sasS2);
        assertEquals(act1.nbSuccesseurs(),10);

        //on ajoute des activités à des sas de sorties
        assertEquals(sasS1.nbSuccesseurs(),2);
        sasS1.ajouterSuccesseur(act1);
        assertEquals(sasS1.nbSuccesseurs(),3);
        sasS1.ajouterSuccesseur(act3);
        assertEquals(sasS1.nbSuccesseurs(),4);

        //on ajoute des sas d'entrées à des sas d'entrées
        assertEquals(sasE1.nbSuccesseurs(),4);
        sasE1.ajouterSuccesseur(sasE2);
        assertEquals(sasE1.nbSuccesseurs(),5);
        sasE1.ajouterSuccesseur(sasE3);
        assertEquals(sasE1.nbSuccesseurs(),6);

        //on ajoute des sas de sorties à des sas d'entrées
        assertEquals(sasE1.nbSuccesseurs(),6);
        sasE1.ajouterSuccesseur(sasS2);
        assertEquals(sasE1.nbSuccesseurs(),7);
        sasE1.ajouterSuccesseur(sasS3);
        assertEquals(sasE1.nbSuccesseurs(),8);

        //on ajoute des sas de sorties à des sas de sorties
        assertEquals(sasS1.nbSuccesseurs(),4);
        sasS1.ajouterSuccesseur(sasS2);
        assertEquals(sasS1.nbSuccesseurs(),5);
        sasS1.ajouterSuccesseur(sasS3);
        assertEquals(sasS1.nbSuccesseurs(),6);

        //on ajoute des sas d'entrées à des sas de sorties
        assertEquals(sasS1.nbSuccesseurs(),6);
        sasS1.ajouterSuccesseur(sasE2);
        assertEquals(sasS1.nbSuccesseurs(),7);
        sasS1.ajouterSuccesseur(sasE3);
        assertEquals(sasS1.nbSuccesseurs(),8);

        //on ajoute des activités restreintes à des activités restreintes
        assertEquals(actRes1.nbSuccesseurs(),4);
        actRes1.ajouterSuccesseur(actRes2);
        assertEquals(actRes1.nbSuccesseurs(),5);
        actRes1.ajouterSuccesseur(actRes3);
        assertEquals(actRes1.nbSuccesseurs(),6);

        //on ajoute des sas d'entrées à des activités restreintes
        assertEquals(actRes1.nbSuccesseurs(),6);
        actRes1.ajouterSuccesseur(sasE1);
        assertEquals(actRes1.nbSuccesseurs(),7);
        actRes1.ajouterSuccesseur(sasE3);
        assertEquals(actRes1.nbSuccesseurs(),8);

        //on ajoute des activités restreintes à des sas d'entrées
        assertEquals(sasE1.nbSuccesseurs(),8);
        sasE1.ajouterSuccesseur(actRes1);
        assertEquals(sasE1.nbSuccesseurs(),9);
        sasE1.ajouterSuccesseur(actRes3);
        assertEquals(sasE1.nbSuccesseurs(),10);

        //on ajoute des sas de sorties à des activités restreintes
        assertEquals(actRes1.nbSuccesseurs(),8);
        actRes1.ajouterSuccesseur(sasE1);
        assertEquals(actRes1.nbSuccesseurs(),9);
        actRes1.ajouterSuccesseur(sasE3);
        assertEquals(actRes1.nbSuccesseurs(),10);

        //on ajoute des activités restreintes à des sas de sorties
        assertEquals(sasS1.nbSuccesseurs(),8);
        sasS1.ajouterSuccesseur(actRes1);
        assertEquals(sasS1.nbSuccesseurs(),9);
        sasS1.ajouterSuccesseur(actRes3);
        assertEquals(sasS1.nbSuccesseurs(),10);
    }

    @Test
    void estUneActivite() {
        assertFalse(gui1.estUneActivite());
        assertFalse(gui2.estUneActivite());
        assertTrue(act1.estUneActivite());
        assertTrue(act2.estUneActivite());
        assertTrue(actRes1.estUneActivite());
        assertTrue(actRes2.estUneActivite());
        assertTrue(sasE1.estUneActivite());
        assertTrue(sasE2.estUneActivite());
        assertTrue(sasS1.estUneActivite());
        assertTrue(sasS2.estUneActivite());
    }

    @Test
    void estUnGuichet() {
        assertFalse(act1.estUnGuichet());
        assertFalse(act2.estUnGuichet());
        assertTrue(gui1.estUnGuichet());
        assertTrue(gui2.estUnGuichet());
        assertFalse(actRes1.estUnGuichet());
        assertFalse(actRes2.estUnGuichet());
        assertFalse(sasE1.estUnGuichet());
        assertFalse(sasE2.estUnGuichet());
        assertFalse(sasS1.estUnGuichet());
        assertFalse(sasS2.estUnGuichet());
    }

    @Test
    void iterator() {
        //On teste si l'itérateur parcours bien tous les successeurs d'une étape et si les étapes parcourues sont les bonnes

        act1.ajouterSuccesseur(act2, act3);
        int count = 0;
        for (Etape etape : act1) {
            assertTrue(etape.estUneActivite());
            count++;
        }
        assertEquals(count, act1.nbSuccesseurs());

        count = 0;
        gui1.ajouterSuccesseur(gui2, gui3);
        for (Etape etape : gui1) {
            assertTrue(etape.estUnGuichet());
            count++;
        }
        assertEquals(count, gui1.nbSuccesseurs());

        actRes1.ajouterSuccesseur(actRes2, actRes3);
        count = 0;
        for (Etape etape : actRes1) {
            assertTrue(etape.estUneActivite());
            count++;
        }
        assertEquals(count, actRes1.nbSuccesseurs());

        sasE1.ajouterSuccesseur(sasE2, sasE3);
        count = 0;
        for (Etape etape : sasE1) {
            assertTrue(etape.estUneActivite());
            count++;
        }
        assertEquals(count, sasE1.nbSuccesseurs());

        sasS1.ajouterSuccesseur(sasS2, sasS3);
        count = 0;
        for (Etape etape : sasS1) {
            assertTrue(etape.estUneActivite());
            count++;
        }
        assertEquals(count, sasS1.nbSuccesseurs());
    }
}