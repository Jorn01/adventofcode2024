package day8.src;

import java.util.ArrayList;
import java.util.List;

public class Matrix {
    private String[][] data;

    /**
     * Constructs a Matrix by converting a List of Strings to a 2D String array.
     * Each String in the list represents a row in the matrix, with elements
     * separated by spaces.
     *
     * @param input the List of Strings to be converted into a matrix.
     */
    public Matrix(List<String> input) {
        if (input == null || input.isEmpty()) {
            data = new String[0][0];
            return;
        }

        int numRows = input.size();
        data = new String[numRows][];
        for (int i = 0; i < numRows; i++) {
            String row = input.get(i);
            if (row != null) {
                // Split the row string into columns based on whitespace
                data[i] = row.trim().split("");
            } else {
                data[i] = new String[0]; // Handle null rows
            }
        }
    }

    /**
     * Returns the underlying 2D String array representing the matrix.
     *
     * @return the 2D String array of the matrix.
     */
    public String[][] getData() {
        return data;
    }

    /**
     * Overrides the toString method to provide a string representation of the
     * matrix.
     *
     * @return a string representing the matrix.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String[] row : data) {
            sb.append(String.join(" ", row));
            sb.append("\n");
        }
        return sb.toString();
    }

    public Node find(String target) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j].equals(target)) {
                    return new Node(i, j);
                }
            }
        }
        return null;
    }

    public List<Node> findAll(String target) {
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j].equals(target)) {
                    nodes.add(new Node(i, j));
                }
            }
        }
        return nodes;
    }

    public List<Node> findAllAntiNodesForChar(String target) {
        List<Node> nodes = findAll(target);
        List<Node> antiNodes = new ArrayList<>();
        for (Node node : nodes) {
            for (Node otherNode : nodes) {
                if (!node.equals(otherNode)) {
                    antiNodes.addAll(findAntiNodes(node, otherNode));
                }
            }
        }
        return antiNodes;
    }

    public List<Node> findAntiNodes(Node node1, Node node2) {
        // Find the anti-nodes of the two nodes
        List<Node> antiNodes = new ArrayList<>();

        // find the distance between node1 and node2, and node2 and node1
        int distanceX = Math.abs(node2.getX() - node1.getX());
        int distanceY = Math.abs(node2.getY() - node1.getY());

        Node antiNode1 = new Node(node1.getX() + distanceX, node1.getY() + distanceY);
        Node antiNode2 = new Node(node2.getX() - distanceX, node2.getY() - distanceY);

        // Add the anti-nodes to the list
        antiNodes.add(antiNode1);
        antiNodes.add(antiNode2);

        return antiNodes;
    }

    public int getWidth() {
        return data[0].length;
    }

    public int getHeight() {
        return data.length;
    }
}