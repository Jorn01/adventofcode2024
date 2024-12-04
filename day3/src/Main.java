package day3.src;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> meow = List.of(Utils.readFile("day3\\src\\input.txt").split("\n"));
        Pattern pattern = Pattern.compile("mul\\([0-9]{1,3},[0-9]{1,3}\\)", Pattern.CASE_INSENSITIVE);

        Integer totalSolution = 0;
        String s = String.valueOf(meow);
        List<String> dos = recursive(s, new ArrayList<>());

        for (String doMatch : dos) {
            Matcher matcher = pattern.matcher(doMatch);
            while (matcher.find()) {
                String function = doMatch.substring(matcher.start(), matcher.end());
                function = function.replace("mul(", "");
                function = function.replace(")", "");

                String[] parts = function.split(",", 2);

                int solution = Integer.parseInt(parts[0]) * Integer.parseInt(parts[1]);
                totalSolution += solution;
            }
        }

        System.out.println(totalSolution);
    }

    public static List<String> recursive(String s, List<String> Dos) {
        String[] list = s.split("don't\\(\\)", 2);
        if (list.length == 1) {
            Dos.add(list[0]);
            return Dos;
        }
        String Do = list[0];
        String dontDo = list[1];

        Dos.add(Do);

        return recursiveDo(dontDo, Dos);
    }

    public static List<String> recursiveDo(String s, List<String> Dos) {
        String[] list = s.split("do\\(\\)", 2);
        if (list.length == 1) {
            return Dos;
        }
        String dontDo = list[1];

        return recursive(dontDo, Dos);
    }

}