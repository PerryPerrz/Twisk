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
        assertEquals(sasE1.toC(), "entrer(69);\ndelai(5, 2);\ntransfert(69, 72);\n");
        sasE2.ajouterSuccesseur(act1);
        act1.ajouterSuccesseur(sasS2);
        assertEquals(sasE2.toC(), "entrer(70);\ndelai(5, 2);\ntransfert(70, 60);\ndelai(5, 2);\ntransfert(60, 73);\n");
    }
}