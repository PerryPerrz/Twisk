package twisk.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GuichetTest extends EtapeTest {
    @Override
    @BeforeEach
    void setUp() {
        super.setUp();
    }

    @Test
    void toC() { //Pour ce test, on travaille sur des mondes non-cohérents donc on doit rajouter des successeurs aux guichets que l'on teste
        gui1.ajouterSuccesseur(actRes1, sasS1);
        actRes1.ajouterSuccesseur(sasS1);
        assertEquals(gui1.toC(), "P(ids, 1);\ntransfert(3, 6);\ndelai(10, 10);\nV(ids, 1);\ntransfert(6, 12);\n");
        gui2.ajouterSuccesseur(actRes2, act1, sasS2);
        actRes2.ajouterSuccesseur(act1, sasS2);
        act1.ajouterSuccesseur(sasS2);
        assertEquals(gui2.toC(), "P(ids, 2);\ntransfert(4, 7);\ndelai(10, 2);\nV(ids, 2);\ntransfert(7, 0);\ndelai(10, 10);\ntransfert(0, 13);\n");
    }
}