package twisk.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.monde.Activite;
import twisk.simulation.Client;
import twisk.simulation.GestionnaireClients;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GestionnaireClientsTest {
    private GestionnaireClients gest1;
    private GestionnaireClients gest2;
    private int[] tab1;
    private int[] tab2;

    @BeforeEach
    void setUp() {
        gest1 = new GestionnaireClients(5);
        gest2 = new GestionnaireClients(10);
        tab1 = new int[5];
        tab2 = new int[10];
    }

    @Test
    void setClients() {
        Arrays.fill(tab1, 2);
        gest1.setClients(tab1);
        for (Client c : gest1)
            assertEquals(c.getNumeroClient(), 2);

        Arrays.fill(tab2, 1);
        gest2.setClients(tab2);
        for (Client c : gest2)
            assertEquals(c.getNumeroClient(), 1);
    }

    @Test
    void allerA() {
        Arrays.fill(tab1, 2);
        tab1[3] = 7;
        gest1.setClients(tab1);
        Activite test = new Activite("test");
        gest1.allerA(7, test, 4);
        for (Client c : gest1) {
            if (c.getNumeroClient() == 2) {
                assertNull(c.getEtape());
                assertNotEquals(c.getRang(), 4);
            } else {
                assertEquals(c.getNumeroClient(), 7);
                assertEquals(c.getEtape(), test);
                assertEquals(c.getRang(), 4);
            }
        }

        Arrays.fill(tab2, 3);
        tab2[9] = 5;
        gest2.setClients(tab2);
        Activite test2 = new Activite("test2");
        gest2.allerA(5, test2, 7);
        for (Client c : gest2) {
            if (c.getNumeroClient() == 3) {
                assertNull(c.getEtape());
                assertNotEquals(c.getRang(), 7);
            } else {
                assertEquals(c.getNumeroClient(), 5);
                assertEquals(c.getEtape(), test2);
                assertEquals(c.getRang(), 7);
            }
        }
    }

    @Test
    void reset() {
        Arrays.fill(tab1, 2);
        gest1.setClients(tab1);
        for (Client c : gest1)
            assertEquals(c.getNumeroClient(), 2);
        gest1.reset();
        for (Client c : gest1)
            assertNull(c);

        Arrays.fill(tab2, 1);
        gest2.setClients(tab2);
        for (Client c : gest2)
            assertEquals(c.getNumeroClient(), 1);
        gest2.reset();
        for (Client c : gest2)
            assertNull(c);
    }
}