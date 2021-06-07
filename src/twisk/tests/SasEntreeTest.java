package twisk.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * La classe SasEntreeTest.
 */
class SasEntreeTest extends ActiviteTest {

    @BeforeEach
    void setUp() {
        super.setUp();
    }

    @Test
    void toC() {//Pour ce test, on travaille sur des mondes non-cohérents donc on doit rajouter des sas de sorties en tant que successeurs des sas d'entrées à tester
        sasE1.ajouterSuccesseur(sasS1);
        assertEquals(sasE1.toC(), "entrer(SASENTREE);\ndelaiUniforme(10, 4);\ntransfert(SASENTREE, SASSORTIE);\n");
        sasE2.ajouterSuccesseur(act1);
        act1.ajouterSuccesseur(sasS2);
        assertEquals(sasE2.toC(), "entrer(SASENTREE);\ndelaiUniforme(10, 4);\ntransfert(SASENTREE, ACT1);\ndelai(4, 2);\ntransfert(ACT1, SASSORTIE);\n");

        sasE1.setLoi("Gauss");
        sasE2.setLoi("Gauss");
        assertEquals(sasE1.toC(), "entrer(SASENTREE);\ndelaiGauss(10.0, 4.0);\ntransfert(SASENTREE, SASSORTIE);\n");
        assertEquals(sasE2.toC(), "entrer(SASENTREE);\ndelaiGauss(10.0, 4.0);\ntransfert(SASENTREE, ACT1);\ndelai(4, 2);\ntransfert(ACT1, SASSORTIE);\n");

        sasE1.setLoi("Expo");
        sasE2.setLoi("Expo");
        assertEquals(sasE1.toC(), "entrer(SASENTREE);\ndelaiExponentiel(1.0/10.0);\ntransfert(SASENTREE, SASSORTIE);\n");
        assertEquals(sasE2.toC(), "entrer(SASENTREE);\ndelaiExponentiel(1.0/10.0);\ntransfert(SASENTREE, ACT1);\ndelai(4, 2);\ntransfert(ACT1, SASSORTIE);\n");
    }
}