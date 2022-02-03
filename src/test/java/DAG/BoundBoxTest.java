package DAG;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BoundBoxTest {

    static private Space space;

    @BeforeAll
    static void setUp() throws DAGConstraintException {
        space = new Space(2, 2);
        Origin origin1 = new Origin(-1, 2);
        Origin origin2 = new Origin(3, 0);
        Point point1 = new Point(3, 3);
        Point point2 = new Point(-1, 2);

        space.setChildren(origin1);
        space.setChildren(origin2);
        origin1.setChildren(point1);
        origin2.setChildren(point2);
    }

    @Test
    void testToString() {
        String actual = space.getBounds().toString();
        String expected = "[0.0 5.0; 2.0 0.0]";

        assertEquals(expected, actual);
    }

    @Test
    void calculateBounds() {
        BoundBox bounds = BoundBox.calculateBounds(space);
        String expected = "[0.0 5.0; 2.0 0.0]";

        assertEquals(expected, bounds.toString());
    }
}