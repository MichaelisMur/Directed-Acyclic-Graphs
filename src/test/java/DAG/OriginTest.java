package DAG;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OriginTest {

    static private Space space;
    static private Origin origin1;
    static private Origin origin2;
    static private Point point1;
    static private Point point2;

    @BeforeAll
    static void setUp() throws DAGConstraintException {
        space = new Space(2, 2);
        origin1 = new Origin(-1, 2);
        origin2 = new Origin(0, 0);
        point1 = new Point(3, 3);
        point2 = new Point(1, 2);

        space.setChildren(point1);
        space.setChildren(point2);
        origin1.setChildren(point1);
        origin1.setChildren(point2);
    }

    @Test
    void getBounds() {
        BoundBox bounds1 = space.getBounds();
        assertEquals("[0.0 3.0; 3.0 0.0]", bounds1.toString());

        BoundBox bounds2 = origin1.getBounds();
        assertEquals("[0.0 3.0; 3.0 0.0]", bounds2.toString());

        BoundBox bounds3 = point1.getBounds();
        assertEquals("[0.0 0.0; 0.0 0.0]", bounds3.toString());
    }

    @Test
    void getChildren() {
        // Both space and origin1 have same two children - point1 and point2.
        boolean actual = space.getChildren().equals(origin1.getChildren());
        assertTrue(actual);

        // You cannot change children without setChildren method.
        space.getChildren().add(origin2);
        assertEquals(2, space.getChildren().size());
    }

    @Test
    void setChildren() throws DAGConstraintException {
        // Adding to origin2 children of origin1 - point1 and point2.
        origin2.setChildren(point1);
        origin2.setChildren(point2);

        boolean actual = origin2.getChildren().equals(origin1.getChildren());
        assertTrue(actual);
    }
}