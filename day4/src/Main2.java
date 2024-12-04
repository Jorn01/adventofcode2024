package day4.src;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main2 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> meow = List.of(Utils.readFile("C:\\Users\\JornL\\adventOfCode2023\\src\\input.txt").split("\n"));

        finder(meow);

    }

    public static void finder(List<String> input) {
        Matrix matrix = new Matrix(input);

        List<Node> locationsOfAs = new ArrayList<>();
        for (int i = 1; i < input.size() - 1; i++) {
            String line = input.get(i);

            String[] chars = line.split("");
            for (int j = 1; j < chars.length - 1; j++) {
                if (Objects.equals(chars[j], "A")) {
                    // this is already in base 0
                    Node node = new Node(j, i);
                    locationsOfAs.add(node);
                }
            }
        }

        int total = 0;
        for (int i = 0; i < locationsOfAs.size(); i++) {
            if (isValid(locationsOfAs.get(i), matrix)) {
                total++;
            }
        }
        System.out.println(total);
        System.out.println(locationsOfAs);
    }

    private static boolean isValid(Node node, Matrix matrix) {
        int x = node.getX();
        int y = node.getY();

        String[][] data = matrix.getData();

        // Swap x and y when accessing the data array
        if (data[y - 1][x - 1].equals("M") && data[y + 1][x + 1].equals("S")) {
            if (data[y - 1][x + 1].equals("M") && data[y + 1][x - 1].equals("S")) {
                return true;
            }
            if (data[y - 1][x + 1].equals("S") && data[y + 1][x - 1].equals("M")) {
                return true;
            }
        }
        if (data[y - 1][x - 1].equals("S") && data[y + 1][x + 1].equals("M")) {
            if (data[y - 1][x + 1].equals("S") && data[y + 1][x - 1].equals("M")) {
                return true;
            }
            if (data[y - 1][x + 1].equals("M") && data[y + 1][x - 1].equals("S")) {
                return true;
            }
        }
        System.out.println(node);
        return false;
    }

}