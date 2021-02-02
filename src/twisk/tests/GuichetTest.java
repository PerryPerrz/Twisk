package twisk.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.monde.Guichet;

import static org.junit.jupiter.api.Assertions.*;

class GuichetTest {

    Guichet guichet1, guichet2;

    @BeforeEach
    void setUp() {
        guichet1 = new Guichet("gui");
        guichet2 = new Guichet("gui2",2);
    }

    @Test
    void estUnGuichet() {
        assertTrue(guichet1.estUnGuichet());
        assertTrue(guichet2.estUnGuichet());
    }
}