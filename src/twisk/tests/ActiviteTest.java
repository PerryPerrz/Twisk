package twisk.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * La classe ActiviteTest.
 */
class ActiviteTest extends EtapeTest {
    @Override
    @BeforeEach
    void setUp() {
        super.setUp();
    }

    /**
     * Test de la fonction toC.
     */
    @Test
    void toC() { //Pour ce test, on travaille sur des mondes non-cohérents donc on doit rajouter des sas de sorties en tant que successeurs des activitées à tester
        act1.ajouterSuccesseur(sasS1);
        assertEquals(act1.toC(), "delai(4, 2);\ntransfert(ACT1, SASSORTIE);\n");
        act2.ajouterSuccesseur(act3);
        act3.ajouterSuccesseur(sasS2);
        assertEquals(act2.toC(), "delai(10, 2);\ntransfert(ACT2, ACT3);\ndelai(4, 2);\ntransfert(ACT3, SASSORTIE);\n");
    }
}