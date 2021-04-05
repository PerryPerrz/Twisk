package twisk.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.outils.KitC;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The type Kit c test.
 */
class KitCTest {
    /**
     * The Kit c.
     */
    KitC kitC;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        kitC = new KitC();
    }

    /**
     * Creer environnement.
     */
    @Test
    void creerEnvironnement() {
        kitC.creerEnvironnement();
        assertTrue(Files.exists(Paths.get("/tmp/twisk/programmeC.o")));
        assertTrue(Files.exists(Paths.get("/tmp/twisk/def.h")));
        assertTrue(Files.exists(Paths.get("/tmp/twisk/codeNatif.o")));
    }

    /**
     * Creer fichier.
     */
    @Test
    void creerFichier() {
        kitC.creerFichier("#include <stdio.h>\n\nint main(int argc, char* argv[]) {}");
        assertTrue(Files.exists(Paths.get("/tmp/twisk/client.c")));
        try {
            assertEquals(Files.readString(Paths.get("/tmp/twisk/client.c")), "#include <stdio.h>\n\nint main(int argc, char* argv[]) {}\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}