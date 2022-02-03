package DAG;

public class Point {

    protected BoundBox bounds;
    private Coord2D position;

    public Point(double x, double y) {
        position = new Coord2D(x, y);
    }

    /**
     * @return zero BoundBox of the Point.
     */
    public BoundBox getBounds() {
        bounds = new BoundBox(0, 0, 0, 0);
        return bounds;
    }

    /**
     * @return Coord2D object with Point's position.
     */
    public Coord2D getPosition() {
        return position;
    }

    /**
     * Sets Point's position.
     * @param x value of position
     * @param y value of position
     */
    public void setPosition(double x, double y) {
        this.position = new Coord2D(x, y);
    }

    /**
     * Overrides equals method.
     * @param o is an object to compare with
     * @return true if both nodes have equal positions
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Point)) return false;
        return position.getX() == ((Point) o).getPosition().getX() &&
                position.getY() == ((Point) o).getPosition().getY();
    }

}
