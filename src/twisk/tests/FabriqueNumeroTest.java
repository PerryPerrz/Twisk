package twisk.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.outils.FabriqueNumero;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The type Fabrique numero test.
 */
class FabriqueNumeroTest {
    /**
     * The Fabrique numero.
     */
    FabriqueNumero fabriqueNumero;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        fabriqueNumero = FabriqueNumero.getInstance();
    }

    /**
     * Gets numero etape.
     */
    @Test
    void getNumeroEtape() {
        int i = fabriqueNumero.getNumeroEtape();
        assertEquals(i, 0);
        fabriqueNumero.getNumeroEtape();
        int j = fabriqueNumero.getNumeroEtape();
        i = fabriqueNumero.getNumeroEtape();
        assertEquals(i, 3);
        assertEquals(j, 2);
    }

    /**
     * Gets numero semaphore.
     */
    @Test
    void getNumeroSemaphore() {
        int i = fabriqueNumero.getNumeroSemaphore();
        assertEquals(i, 1);
        fabriqueNumero.getNumeroSemaphore();
        int j = fabriqueNumero.getNumeroSemaphore();
        i = fabriqueNumero.getNumeroSemaphore();
        assertEquals(i, 4);
        assertEquals(j, 3);
    }

    /**
     * Reset.
     */
    @Test
    void reset() {
        fabriqueNumero.getNumeroEtape();
        int i = fabriqueNumero.getNumeroEtape();
        assertEquals(i, 5); //La valeur est 5 car, comme il n'existe qu'une insance de la fabrique, alors le nombre est affecté par les tests efféctués auparavant
        for (int j = 0; j < 20; j++)
            i = fabriqueNumero.getNumeroSemaphore();
        assertEquals(i, 24); //Idem
        fabriqueNumero.reset();
        i = fabriqueNumero.getNumeroEtape();
        assertEquals(i, 0);
        i = fabriqueNumero.getNumeroSemaphore();
        assertEquals(i, 1);
    }
}