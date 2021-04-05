package twisk.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.monde.Activite;
import twisk.monde.Etape;
import twisk.monde.GestionnaireSuccesseurs;
import twisk.monde.Guichet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The type Gestionnaire successeurs test.
 */
class GestionnaireSuccesseursTest {
    /**
     * The Act 1.
     */
    Activite act1, /**
     * The Act 2.
     */
    act2, /**
     * The Act 3.
     */
    act3;
    /**
     * The Gui 1.
     */
    Guichet gui1, /**
     * The Gui 2.
     */
    gui2, /**
     * The Gui 3.
     */
    gui3;
    /**
     * The Gest 1.
     */
    GestionnaireSuccesseurs gest1, /**
     * The Gest 2.
     */
    gest2, /**
     * The Gest 3.
     */
    gest3;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        act1 = new Activite("act1");
        act2 = new Activite("act2", 10, 2);
        act3 = new Activite("act3");
        gui1 = new Guichet("gui1");
        gui2 = new Guichet("gui2", 10);
        gui3 = new Guichet("gui3");
        gest1 = new GestionnaireSuccesseurs();
        gest2 = new GestionnaireSuccesseurs();
        gest3 = new GestionnaireSuccesseurs();
    }

    /**
     * Nb etapes.
     */
    @Test
    void nbEtapes() {
        //On ajoute des activités
        assertEquals(gest1.nbEtapes(), 0);
        gest1.ajouter(act1);
        assertEquals(gest1.nbEtapes(), 1);
        gest1.ajouter(act2, act3);
        assertEquals(gest1.nbEtapes(), 3);

        //on ajoute des guichets
        assertEquals(gest2.nbEtapes(), 0);
        gest2.ajouter(gui1);
        assertEquals(gest2.nbEtapes(), 1);
        gest2.ajouter(gui2, gui3);
        assertEquals(gest2.nbEtapes(), 3);

        //On ajoute des activités et des guichets
        assertEquals(gest3.nbEtapes(), 0);
        gest3.ajouter(gui1, act1);
        assertEquals(gest3.nbEtapes(), 2);
        gest3.ajouter(gui2, act2, act3, gui3);
        assertEquals(gest3.nbEtapes(), 6);
    }

    /**
     * Iterator.
     */
    @Test
    void iterator() {
        //On teste si l'itérateur parcours bien tous les successeurs d'une étape et si les étapes parcourues sont les bonnes

        gest1.ajouter(act1, act2, act3);
        int count = 0;
        for (Etape etape : gest1) {
            assertTrue(etape.estUneActivite());
            count++;
        }
        assertEquals(count, gest1.nbEtapes());

        count = 0;
        gest2.ajouter(gui1, gui2, gui3);
        for (Etape etape : gest2) {
            assertTrue(etape.estUnGuichet());
            count++;
        }
        assertEquals(count, gest2.nbEtapes());
    }
}