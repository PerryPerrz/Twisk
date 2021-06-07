package twisk.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.exceptions.PasUnGuichetException;
import twisk.monde.Monde;
import twisk.mondeIG.MondeIG;
import twisk.outils.FabriqueIdentifiant;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * La classe MondeIGTest
 */
class MondeIGTest {
    /**
     * Le MondeIG 1
     */
    MondeIG mondeIG1, /**
     * Le MondeIG 2.
     */
    mondeIG2, /**
     * Le MondeIG 3.
     */
    mondeIG3;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
    }

    /**
     * Creer monde.
     */
    @Test
    void creerMonde() {
        Monde monde1, monde2, monde3;
        try {
            mondeIG1 = new MondeIG();
            monde1 = mondeIG1.creerMonde();
            assertTrue(monde1.getEtapeI(0).estUnSasEntree());
            assertTrue(monde1.getEtapeI(1).estUnSasSortie());
            assertTrue(monde1.getEtapeI(2).estUneActivite());

            FabriqueIdentifiant.getInstance().reset();
            mondeIG2 = new MondeIG();
            mondeIG2.ajouter("Guichet");
            assertDoesNotThrow(() -> mondeIG2.ajouter(mondeIG2.getEtapeIndice("0").getPdcIndex(0), mondeIG2.getEtapeIndice("1").getPdcIndex(3)));
            monde2 = mondeIG2.creerMonde();
            assertTrue(monde2.getEtapeI(0).estUnSasEntree());
            assertTrue(monde2.getEtapeI(1).estUnSasSortie());
            assertTrue(monde2.getEtapeI(2).estUneActivite());
            assertTrue(monde2.getEtapeI(3).estUnGuichet());

            FabriqueIdentifiant.getInstance().reset();
            mondeIG3 = new MondeIG();
            mondeIG3.ajouter("Activite");
            monde3 = mondeIG3.creerMonde();
            assertTrue(monde3.getEtapeI(0).estUnSasEntree());
            assertTrue(monde3.getEtapeI(1).estUnSasSortie());
            assertTrue(monde3.getEtapeI(2).estUneActivite());
            assertTrue(monde3.getEtapeI(3).estUneActivite());
        } catch (PasUnGuichetException e) {
            e.printStackTrace();
        }


    }
}