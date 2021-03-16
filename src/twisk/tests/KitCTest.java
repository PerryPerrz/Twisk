package twisk.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.outils.KitC;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KitCTest {
    KitC kitC;

    @BeforeEach
    void setUp() {
        kitC = new KitC();
        kitC.creerEnvironnement();
    }

    @Test
    void creerEnvironnement() {
        assertTrue(Files.exists(Paths.get("/tmp/twisk/programmeC.o")));
        assertTrue(Files.exists(Paths.get("/tmp/twisk/def.h")));
    }

    @Test
    void creerFichier() {
        kitC.creerFichier("DE RENARD\nQUEUE");
        assertTrue(Files.exists(Paths.get("/tmp/twisk/client.c")));
        try {
            Files.readString(Paths.get("/tmp/twisk/client.c"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}