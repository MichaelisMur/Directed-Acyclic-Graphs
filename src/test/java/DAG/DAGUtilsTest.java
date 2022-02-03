package DAG;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DAGUtilsTest {

    @Test
    void exportAsString() throws DAGConstraintException {
        Space space = new Space(2, 2);
        Origin origin1 = new Origin(-1, 2);
        Origin origin2 = new Origin(3, 3);
        Point point1 = new Point(1, 2);

        space.setChildren(origin1);
        origin1.setChildren(origin2);
        origin2.setChildren(point1);

        String result = DAGUtils.exportAsString(space);

         /*
         node representation in form:
         (x;y) - Point with x y position
         [x;y] - Origin with x y position
         {x;y} - Space with x y position.
         "-" means go back to the parent.
         "+" means go deeper to the child.
         */
        assertEquals("{2.0;2.0}+[-1.0;2.0]+[3.0;3.0]+(1.0;2.0)----",
                result);
    }
}