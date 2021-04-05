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
        assertEquals(gui1.toC(), "P(ids, 13);\ntransfert(63, 66);\ndelai(5, 2);\nV(ids, 13);\ntransfert(66, 72);\n");
        gui2.ajouterSuccesseur(actRes2, act1, sasS2);
        actRes2.ajouterSuccesseur(act1, sasS2);
        act1.ajouterSuccesseur(sasS2);
        assertEquals(gui2.toC(), "P(ids, 14);\ntransfert(64, 67);\ndelai(10, 2);\nV(ids, 14);\ntransfert(67, 60);\ndelai(5, 2);\ntransfert(60, 73);\n");
    }
}