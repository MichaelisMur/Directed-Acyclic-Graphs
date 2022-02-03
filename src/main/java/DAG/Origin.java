package DAG;

import java.util.HashSet;

public class Origin extends Point{

    final private HashSet<Point> children = new HashSet<>();

    public Origin(double x, double y) {
        super(x, y);
    }

    /**
     * Calculates bounds of a graph node.
     * @return BoundBox object with boundaries.
     */
    @Override
    public BoundBox getBounds() {
        bounds = BoundBox.calculateBounds(this);
        return bounds;
    }

    /**
     * @return HashSet of node's children.
     */
    public HashSet<Point> getChildren() {
        HashSet<Point> childrenCopy = new HashSet<>();
        childrenCopy.addAll(children);
        return childrenCopy;
    }

    /**
     * @param child is an object to find to node's children list.
     * @throws DAGConstraintException when acyclic graph's properties are broken.
     */
    public void setChildren(Point child) throws DAGConstraintException {
        if (child == null) {
            throw new IllegalArgumentException("Null added as a child.");
        }

        // If Space object is added to Origin's children list.
        if (!(this instanceof Space) && child instanceof Space) {
            throw new DAGConstraintException(
                    "Space cannot be a child of origin.");
        }
        if(child.equals(this)) {
            throw new DAGConstraintException(
                    "Origin cannot have a child with the same coordinates.");
        }

        for (Point item : children) {
            if (item.equals(child)) {
                throw new DAGConstraintException(
                        "Origin already has a child with the given coordinates.");
            }
        }

        if(child instanceof Origin) {
            if(checkForCycle(((Origin) child).getChildren(),
                    getPosition().getX(), getPosition().getY())) {
                throw new DAGConstraintException(
                        "You can't add this child - it will make our graph acyclic.");
            }
        }

        children.add(child);
    }


    /**
     * Recursively checks whether child is going to brake acyclic property of the graph.
     * @param childrenList contains list of children of the node added.
     * @param originX is an added node's x value.
     * @param originY is an added node's y value.
     * @return true if acyclic property is going to be broken.
     */
    private boolean checkForCycle(HashSet<Point> childrenList, double originX, double originY) {
        for (Point item : childrenList) {
            if (item.getPosition().getX() == originX &&
                    item.getPosition().getY() == originY) {
                return true;
            }

            if (item instanceof Origin) {
                if ((checkForCycle(((Origin) item).children, originX, originY))) {
                    return true;
                }
            }
        }
        return false;
    }
}
