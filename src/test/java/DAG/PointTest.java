package DAG;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    static private Point point1;
    static private Point point2;
    static private Point point3;

    @BeforeEach
    void setUp() throws DAGConstraintException {
        Space space = new Space(2, 2);
        Origin origin1 = new Origin(-1, 2);
        point1 = new Point(3, 3);
        point2 = new Point(1, 2);
        point3 = new Point(3, 3);

        space.setChildren(point1);
        space.setChildren(point2);
        origin1.setChildren(point1);
        origin1.setChildren(point2);
    }

    @Test
    void getBounds() {
        String actual = point2.getBounds().toString();
        String expected = "[0.0 0.0; 0.0 0.0]";
        assertEquals(expected, actual);
    }

    @Test
    void getPosition() {
        // point2 was initialized with coordinates [1, 2].
        double actualX = point2.getPosition().getX();
        double actualY = point2.getPosition().getY();
        double expectedX = 1;
        double expectedY = 2;

        assertEquals(expectedX, actualX);
        assertEquals(expectedY, actualY);
    }


    @Test
    void setPosition() {
        // Changing point1's coordinates to the coordinates of point2.
        point1.setPosition(1, 2);

        assertEquals(point1, point2);
    }

    @Test
    void testEquals() {
        // Both point1 and point2 have same coordinates.
        assertEquals(point1, point3);
    }
}