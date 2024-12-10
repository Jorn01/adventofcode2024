package day6.src;

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

    // Additional methods can be added here (e.g., getters, setters, matrix
    // operations)
}