package twisk.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.monde.Activite;
import twisk.monde.Etape;
import twisk.monde.Guichet;

import static org.junit.jupiter.api.Assertions.*;

class EtapeTest {

    Activite act1,act2;
    Guichet gui1,gui2;

    @BeforeEach
    void setUp() {
        act1 = new Activite("act1");
        act2 = new Activite("act2",10,2);
        gui1 = new Guichet("gui1");
        gui2 = new Guichet("gui2", 10);
    }

    @Test
    void ajouterSuccesseur() {

    }

    @Test
    void estUneActivite() {
        assertTrue(act1.estUneActivite());
        assertTrue(act2.estUneActivite());
        assertFalse(gui1.estUneActivite());
        assertFalse(gui2.estUneActivite());
    }

    @Test
    void estUnGuichet() {
        assertFalse(act1.estUnGuichet());
        assertFalse(act2.estUnGuichet());
        assertTrue(gui1.estUnGuichet());
        assertTrue(gui2.estUnGuichet());
    }
}