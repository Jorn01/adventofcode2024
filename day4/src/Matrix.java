package day4.src;

import java.util.List;

public class Matrix {
    private String[][] data;
    private int width;
    private int height;

    /**
     * Constructs a Matrix by converting a List of Strings to a 2D String array.
     * Each String in the list represents a row in the matrix.
     * The matrix is indexed as data[x][y], where x is the column and y is the row.
     *
     * @param input the List of Strings to be converted into a matrix.
     */
    public Matrix(List<String> input) {
        if (input == null || input.isEmpty()) {
            data = new String[0][0];
            width = 0;
            height = 0;
            return;
        }

        height = input.size();
        // Determine the maximum row length to define the width
        width = input.stream().mapToInt(String::length).max().orElse(0);
        data = new String[width][height];

        for (int y = 0; y < height; y++) {
            String row = input.get(y);
            if (row != null) {
                for (int x = 0; x < width; x++) {
                    if (x < row.length()) {
                        // Split the row into individual characters
                        data[x][y] = String.valueOf(row.charAt(x));
                    } else {
                        // Pad with a default value if the row is shorter
                        data[x][y] = " ";
                    }
                }
            } else {
                // If the row is null, fill the entire column for this y with default values
                for (int x = 0; x < width; x++) {
                    data[x][y] = " ";
                }
            }
        }
    }

    /**
     * Returns the underlying 2D String array representing the matrix.
     * Indexed as data[x][y].
     *
     * @return the 2D String array of the matrix.
     */
    public String[][] getData() {
        return data;
    }

    /**
     * Returns the width (number of columns) of the matrix.
     *
     * @return the width of the matrix.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height (number of rows) of the matrix.
     *
     * @return the height of the matrix.
     */
    public int getHeight() {
        return height;
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
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                sb.append(data[x][y]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // Additional methods can be added here (e.g., getters, setters, matrix operations)
}
