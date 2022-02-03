package DAG;

public class DAGUtils {
    static String serializeResult = "";

    /**
     * @param node Space object to serialize.
     * @return object serialized as string.
     */
    static public String exportAsString(Space node) {
        if (node == null) {
            throw new IllegalArgumentException(
                    "Null is passed.");
        }

        Serialize(node);
        return serializeResult;
    }

    /**
     * Recursively add all graph's info to string.
     * @param node current node to serialize.
     */
    static void Serialize(Point node) {
        serializeResult += StringNode(node);
        if(!(node instanceof Origin)) {

            // "-" means go back to the parent.
            serializeResult += "-";
            return;
        }
        for (Point child : ((Origin)node).getChildren()) {

            // "+" means go deeper to the child.
            serializeResult += "+";
            Serialize(child);
        }
        serializeResult += "-";
    }

    /**
     * @param node to add to the result string
     * @return node representation in form (x;y) - Point with x y position
     * [x;y] - Origin with x y position; {x;y} - Space with x y position.
     */
    static String StringNode(Point node) {
        if(!(node instanceof Origin)) {
            return String.format("(%s;%s)",
                    node.getPosition().getX(), node.getPosition().getY());
        }
        if(!(node instanceof Space)) {
            return String.format("[%s;%s]",
                    node.getPosition().getX(), node.getPosition().getY());
        }
        return String.format("{%s;%s}",
                node.getPosition().getX(), node.getPosition().getY());
    }


    /*
    static public Space ImportFromString(String s) {
        return new Space(0 ,0);
    }
    */
}

