package twisk.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.monde.Activite;

import static org.junit.jupiter.api.Assertions.*;

class ActiviteTest {

    Activite activite1, activite2;

    @BeforeEach
    void setUp() {
        activite1 = new Activite("act");
        activite2 = new Activite("act2", 10, 5);
    }

    @Test
    void estUneActivite() {
        assertTrue(activite1.estUneActivite());
        assertTrue(activite2.estUneActivite());
    }
}