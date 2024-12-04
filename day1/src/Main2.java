package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main2 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> meow = List.of(Utils.readFile("day1\\src\\input.txt").split("\n"));
        List<Integer> list1 = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (String row : meow) {
            String[] columns = row.split(" ");
            list1.add(Integer.valueOf(columns[0]));
            Integer roomID = Integer.valueOf(columns[columns.length - 1]);
            if (!map.containsKey(roomID)) {
                map.put(roomID, 1);
            } else {
                Integer value = map.get(roomID);
                map.put(roomID, (value + 1));
            }
        }

        System.out.println(map);
        Integer total = 0;
        for (Integer i : list1) {
            if (map.containsKey(i)) {
                total += map.get(i) * i;
            }
        }

        System.out.println(total);
    }
}