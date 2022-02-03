package DAG;

final public class BoundBox {

    private double Ax;
    private double Ay;
    private double Bx;
    private double By;

    public BoundBox(double ax, double ay, double bx,double by) {
        Ax = ax;
        Ay = ay;
        Bx = bx;
        By = by;
    }


    /**
     * Calculates bounds of a graph node
     * @param point point/origin/space to check bounds
     * @return BoundBox object with top left and bottom right diagonal boundaries
     */
    static public BoundBox calculateBounds(Point point) {

        // If null is passed.
        if (point == null) {
            throw new IllegalArgumentException(
                    "Null added as an origin point for bounds calculation.");
        }

        // Starting condition of BoundBox.
        BoundBox bounds = new BoundBox(0, 0, 0, 0);

        shiftBoundaries(bounds, point, 0, 0);
        return bounds;
    }

    /**
     * @param bounds BoundBox object that is being changed through recursive calls
     * @param point node of the graph to iterate through a children list
     * @param xCoord x coordinate of the node relative to the initial node
     * @param yCoord y coordinate of the node relative to the initial node
     */
    static private void shiftBoundaries(BoundBox bounds, Point point, double xCoord, double yCoord) {

        // If point is a leaf.
        if(!(point instanceof Origin)) {
            return;
        }
        for(Point child : ((Origin) point).getChildren()) {
            xCoord += child.getPosition().getX();
            yCoord += child.getPosition().getY();
            shiftBoundaries(bounds, child, xCoord, yCoord);

            // BoundBox covers leaves only.
            if(!(child instanceof Origin)) {
                bounds.Ax = Math.min(bounds.Ax, xCoord);
                bounds.Ay = Math.max(bounds.Ay, yCoord);
                bounds.Bx = Math.max(bounds.Bx, xCoord);
                bounds.By = Math.min(bounds.By, yCoord);
            }

            xCoord -= child.getPosition().getX();
            yCoord -= child.getPosition().getY();
        }
    }

    /**
     * Overrides toString method.
     * @return formatted BoundBox
     */
    public String toString() {
        return String.format("[%s %s; %s %s]", Ax, Ay, Bx, By);
    }
}