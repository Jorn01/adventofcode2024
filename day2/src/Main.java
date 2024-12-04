
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> meow = List.of(Utils.readFile("day2\\src\\input.txt").split("\n"));

        Integer total = 0;
        for (String s : meow) {
            final List<Integer> list = process(s);
            if (isValid(list)) {
                total++;
            } else {
                boolean afterRemovingStillValid = false;
                for (int i = 0; i < list.size(); i++) {
                    List<Integer> copy = new ArrayList<>(list);

                    copy.remove(i);

                    if (isValid(copy)) {
                        afterRemovingStillValid = true;
                        break;
                    } else {
                        System.out.println(copy);
                    }
                }
                if (afterRemovingStillValid) {
                    total++;
                }
            }
        }

        System.out.println(total);

    }

    public static List<Integer> process(String row) {
        String[] columns = row.split(" ");
        List<Integer> results = new ArrayList<>();

        for (String column : columns) {
            results.add(Integer.valueOf(column));
        }

        return results;
    }

    public static boolean isValid(List<Integer> list) {

        boolean isValid = isSorted(list.toArray(new Integer[0]));
        boolean isValidReverse = isSortedReverse(list.toArray(new Integer[0]));

        if (!isValid && !isValidReverse) {
            return false;
        }

        for (int j = 0; j < list.size() - 1; j++) {
            if (Math.abs(list.get(j) - list.get(j + 1)) > 3) {
                return false;
            }

            if (Math.abs(list.get(j) - list.get(j + 1)) == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isSorted(Integer[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) {
                return false;
            }
        }

        return true;
    }

    public static boolean isSortedReverse(Integer[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] < a[i + 1]) {
                return false;
            }
        }

        return true;
    }
}