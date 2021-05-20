package twisk.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.monde.Monde;
import twisk.mondeIG.MondeIG;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MondeIGTest {
    MondeIG mondeIG1, mondeIG2, mondeIG3;

    @BeforeEach
    void setUp() {
        mondeIG1 = new MondeIG();
        mondeIG2 = new MondeIG();
        mondeIG3 = new MondeIG();
    }

    @Test
    void creerMonde() {
        Monde monde1, monde2, monde3;
        monde1 = mondeIG1.creerMonde();
        assertTrue(monde1.getEtapeI(0).estUnSasEntree());
        assertTrue(monde1.getEtapeI(1).estUnSasSortie());

        mondeIG2.ajouter("Guichet");
        assertDoesNotThrow(() -> mondeIG2.ajouter(mondeIG2.getEtapeIndice("0").getPdcIndex(0), mondeIG2.getEtapeIndice("1").getPdcIndex(0)));
        monde2 = mondeIG2.creerMonde();
        assertTrue(monde2.getEtapeI(0).estUnSasEntree());
        assertTrue(monde2.getEtapeI(1).estUnSasSortie());
        assertTrue(monde2.getEtapeI(2).estUnGuichet());

        mondeIG2.ajouter("Activite");
        monde3 = mondeIG3.creerMonde();
        assertTrue(monde3.getEtapeI(0).estUnSasEntree());
        assertTrue(monde3.getEtapeI(1).estUnSasSortie());
        assertTrue(monde3.getEtapeI(2).estUneActivite());

    }
}