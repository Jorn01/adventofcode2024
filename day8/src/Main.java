package day8.src;

import src.Utils;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> meow = List.of(Utils.readFile("day8\\src\\input.txt").split("\n"));
        String allText = Utils.readFile("day8\\src\\input.txt");
        Set<String> a = Arrays.stream(allText.split("")).collect(Collectors.toSet());
        a.remove(",");
        a.remove(" ");
        a.remove("\n");
        a.remove(".");
        Matrix matrix = new Matrix(meow);

        Set<Node> nodes = new HashSet<>();
        List<Node> antiNodes = new ArrayList<>();
        for (String s : a) {
            nodes.addAll(matrix.findAll(s));
            antiNodes.addAll(matrix.findAllAntiNodesForChar(s));
        }

        System.out.println(nodes.size());
        System.out.println(antiNodes);

        antiNodes.removeAll(nodes);


        antiNodes.removeAll(antiNodes.stream().map(N -> {
            if (N.getX() < 0 || N.getY() < 0 || N.getX() >= matrix.getWidth() || N.getY() >= matrix.getHeight()) {
                System.out.println(N);
                return N;
            } else {
                return null;
            }
        }).toList());

        System.out.println("Nodes: " + antiNodes.size());
    }
}