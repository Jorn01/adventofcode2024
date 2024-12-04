package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import src.Utils;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> meow = List.of(Utils.readFile("day1\\src\\input.txt").split("\n"));
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        Integer totalDistance = 0;
        for (String row : meow) {
            String[] columns = row.split(" ");
            list1.add(Integer.valueOf(columns[0]));
            list2.add(Integer.valueOf(columns[columns.length - 1]));
        }

        Collections.sort(list1);
        Collections.sort(list2);

        for (int i = 0; i < list1.size(); i++) {
            Integer distance = Math.abs(list1.get(i) - list2.get(i));
            totalDistance += distance;
        }

        System.out.println(totalDistance);

    }
}