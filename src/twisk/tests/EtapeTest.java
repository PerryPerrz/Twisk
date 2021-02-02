package twisk.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.monde.Activite;
import twisk.monde.Etape;
import twisk.monde.Guichet;

import static org.junit.jupiter.api.Assertions.*;

class EtapeTest {

    Activite act1,act2,act3;
    Guichet gui1,gui2,gui3;

    @BeforeEach
    void setUp() {
        act1 = new Activite("act1");
        act2 = new Activite("act2",10,2);
        act3 = new Activite("act3");
        gui1 = new Guichet("gui1");
        gui2 = new Guichet("gui2", 10);
        gui3 = new Guichet("gui3");
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
    }

    @Test
    void estUneActivite() {
        assertFalse(gui1.estUneActivite());
        assertFalse(gui2.estUneActivite());
    }

    @Test
    void estUnGuichet() {
        assertFalse(act1.estUnGuichet());
        assertFalse(act2.estUnGuichet());
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
        act2.ajouterSuccesseur(gui1, gui2, gui3);
        for (Etape etape : act2) {
            assertTrue(etape.estUnGuichet());
            count++;
        }
        assertEquals(count, act2.nbSuccesseurs());
    }
}