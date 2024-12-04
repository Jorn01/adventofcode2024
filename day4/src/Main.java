package day4.src;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> meow = List.of(Utils.readFile("day4\\src\\input.txt").split("\n"));
        int totalCount = 0;

        // Horizontal occurrences
        for (String s : meow) {
            totalCount += horizontal(s);
        }

        // Vertical occurrences
        List<String> columns = vertical(meow);
        for (String s : columns) {
            totalCount += horizontal(s);
        }

        // Diagonals from top-left to bottom-right
        List<String> diagonals = linkrechtsSchuin(meow);
        for (String s : diagonals) {
            totalCount += horizontal(s);
        }

        // Flip grid horizontally to get diagonals from top-right to bottom-left
        List<String> flippedHorizontally = flipHorizontally(meow);

        // Diagonals from top-left to bottom-right in flipped grid (originally top-right
        // to bottom-left)
        List<String> diagonalsFlipped = linkrechtsSchuin(flippedHorizontally);
        for (String s : diagonalsFlipped) {
            totalCount += horizontal(s);
        }

        System.out.println("Total XMAS occurrences: " + totalCount);
    }

    public static List<String> flipHorizontally(List<String> input) {
        List<String> flipped = new ArrayList<>();
        for (String row : input) {
            StringBuilder sb = new StringBuilder(row);
            flipped.add(sb.reverse().toString());
        }
        return flipped;
    }

    public static int horizontal(String input) {
        Pattern pattern = Pattern.compile("(?=(XMAS|SAMX))");
        Matcher matcher = pattern.matcher(input);
        int i = 0;
        while (matcher.find()) {
            String match = matcher.group(1);
            i++;
        }
        return i;
    }

    public static List<String> vertical(List<String> input) {
        List<String> meow = new ArrayList<>();

        for (int i = 0; i < input.get(0).length(); i++) {
            StringBuilder sb = new StringBuilder();
            for (String s : input) {
                sb.append(s.charAt(i));
            }
            meow.add(sb.toString());
        }

        return meow;
    }

    public static List<String> linkrechtsSchuin(List<String> input) {
        Matrix matrix = new Matrix(input);
        String[][] data = matrix.getData();
        int rows = data.length;
        int cols = data[0].length;
        List<String> diagonals = new ArrayList<>();

        // Get diagonals starting from each element in the first row
        for (int col = 0; col < cols; col++) {
            StringBuilder sb = new StringBuilder();
            int i = 0, j = col;
            while (i < rows && j < cols) {
                sb.append(data[i][j]);
                i++;
                j++;
            }
            diagonals.add(sb.toString());
        }

        // Get diagonals starting from each element in the first column (excluding the
        // first row)
        for (int row = 1; row < rows; row++) {
            StringBuilder sb = new StringBuilder();
            int i = row, j = 0;
            while (i < rows && j < cols) {
                sb.append(data[i][j]);
                i++;
                j++;
            }
            diagonals.add(sb.toString());
        }
        return diagonals;
    }

    public static List<String> rechtsLinksSchuin(List<String> input) {
        Matrix matrix = new Matrix(input);
        String[][] data = matrix.getData();
        int rows = data.length;
        int cols = data[0].length;
        List<String> diagonals = new ArrayList<>();

        // Get diagonals starting from each element in the first row
        for (int col = 0; col < cols; col++) {
            StringBuilder sb = new StringBuilder();
            int i = 0, j = col;
            while (i < rows && j < cols) {
                sb.append(data[i][j]);
                i++;
                j++;
            }
            diagonals.add(sb.toString());
        }

        // Get diagonals starting from each element in the first column (excluding the
        // first row)
        for (int row = 1; row < rows; row++) {
            StringBuilder sb = new StringBuilder();
            int i = row, j = 0;
            while (i < rows && j < cols) {
                sb.append(data[i][j]);
                i++;
                j++;
            }
            diagonals.add(sb.toString());
        }
        return diagonals;
    }

}