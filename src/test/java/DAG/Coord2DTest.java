package DAG;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Coord2DTest {

    static private Coord2D coord;
    @BeforeAll
    static void setUp() {
        coord = new Coord2D(3, 5);
    }

    @Test
    void getX() {
        assertEquals(3, coord.getX());
    }

    @Test
    void getY() {
        assertEquals(5, coord.getY());
    }
}