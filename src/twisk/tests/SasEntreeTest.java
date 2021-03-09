package twisk.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SasEntreeTest extends ActiviteTest {

    @BeforeEach
    void setUp() {
        super.setUp();
    }

    @Test
    void toC() {//Pour ce test, on travaille sur des mondes non-cohérents donc on doit rajouter des sas de sorties en tant que successeurs des sas d'entrées à tester
        sasE1.ajouterSuccesseur(sasS1);
        assertEquals(sasE1.toC(), "entrer(9);\ndelai(10, 10);\ntransfert(9, 12);\n");
        sasE2.ajouterSuccesseur(act1);
        act1.ajouterSuccesseur(sasS2);
        assertEquals(sasE2.toC(), "entrer(10);\ndelai(10, 10);\ntransfert(10, 0);\ndelai(10, 10);\ntransfert(0, 13);\n");
    }
}