package day5.src;

import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> meow = List.of(Utils.readFile("day5\\src\\input.txt").split("\n"));
        List<String> updates = List.of(Utils.readFile("day5\\src\\input2.txt").split("\n"));


        LinkedList linkedList = new LinkedList();

        for (String rule : meow) {
            linkedList.addWeirdRule(rule);
        }

        System.out.println(linkedList);
        List<String> validUpdates = new ArrayList<>();
        for (String update : updates) {
            String[] dateup = update.split(",");
            boolean valid = true;
            for (int i = 0; i < dateup.length - 1; i++) {
                System.out.println("Result of isBefore: " + linkedList.isBefore(dateup[i], dateup[i + 1]));
                if (!linkedList.isBefore(dateup[i], dateup[i + 1])) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                validUpdates.add(update);
            }
        }

        System.out.println(validUpdates);

        int total = 0;
        for (String validUpdate : validUpdates) {
            String[] arr = validUpdate.split(",");
            System.out.println(arr.length);
            int middle = (int) (double) (arr.length / 2);
            total += Integer.parseInt(arr[middle]);
        }

        System.out.println(total);
    }
}