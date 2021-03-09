package twisk.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SasSortieTest extends ActiviteTest {
    @BeforeEach
    void setUp() {
        super.setUp();
    }

    @Test
    void toC() {
        assertEquals(sasS1.toC(), "");
        assertEquals(sasS2.toC(), "");
        assertEquals(sasS3.toC(), "");
    }
}