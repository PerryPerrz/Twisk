package twisk.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * La classe GuichetTest.
 */
class GuichetTest extends EtapeTest {

    @Override
    @BeforeEach
    void setUp() {
        super.setUp();
    }

    /**
     * Test de la fonction toC.
     */
    @Test
    void toC() { //Pour ce test, on travaille sur des mondes non-cohérents donc on doit rajouter des successeurs aux guichets que l'on teste
        gui1.ajouterSuccesseur(actRes1, sasS1);
        actRes1.ajouterSuccesseur(sasS1);
        assertEquals(gui1.toC(), "P(ids, SEM_GUI1);\ntransfert(GUI1, ACTRES1);\ndelai(4, 2);\nV(ids, SEM_GUI1);\ntransfert(ACTRES1, SASSORTIE);\n");
        gui2.ajouterSuccesseur(actRes2, act1, sasS2);
        actRes2.ajouterSuccesseur(act1, sasS2);
        act1.ajouterSuccesseur(sasS2);
        assertEquals(gui2.toC(), "int nb64 = (int) ((rand() / (float) RAND_MAX)*3);\nswitch(nb64)\n{\ncase 0 :\nP(ids, SEM_GUI2);\ntransfert(GUI2, ACTRES2);\ndelai(10, 2);\nV(ids, SEM_GUI2);\ntransfert(ACTRES2, ACT1);\ndelai(4, 2);\ntransfert(ACT1, SASSORTIE);\nbreak;\ncase 1 :\nP(ids, SEM_GUI2);\ntransfert(GUI2, ACTRES2);\ndelai(10, 2);\nV(ids, SEM_GUI2);\ntransfert(ACTRES2, SASSORTIE);\nbreak;\n}\n");
    }
}