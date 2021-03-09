package twisk.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ActiviteRestreinteTest extends ActiviteTest {
    @BeforeEach
    void setUp() {
        super.setUp();
    }

    @Test
    void toC() {
        assertEquals(actRes1.toC(), "delai(10, 10);\n");
        assertEquals(actRes2.toC(), "delai(10, 2);\n");
        assertEquals(actRes3.toC(), "delai(10, 10);\n");
    }
}