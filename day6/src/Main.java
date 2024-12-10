package day6.src;

import day4.src.Matrix;
import day4.src.Node;
import src.Utils;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.*;

import static day6.src.Facing.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, InterruptedException, ExecutionException {
        String input = Utils.readFile("day6\\src\\input.txt");

        Facing facing = Facing.NORTH;
        final Node start = findStart(Arrays.asList(input.split("\n")));
        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(cores);

        // Prepare tasks for parallel execution
        List<Future<Boolean>> results = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            final int index = i; // Capture loop index
            Callable<Boolean> task = () -> {
                String updatedInput = replaceCharacter(input, index, '#');
                Matrix matrix = new Matrix(Arrays.asList(updatedInput.split("\n")));
                return hasLoop(start, matrix, facing);
            };
            results.add(executorService.submit(task));
        }

        // Gather results
        int loops = 0;
        for (Future<Boolean> result : results) {
            if (result.get()) {
                loops++;
            }
        }

        executorService.shutdown();
        System.out.println("Loops: " + loops);
    }

    public static boolean hasLoop(Node currentPosition, Matrix matrix, Facing facing) {
        List<Node> visited = new ArrayList<>();
        while (true) {
            if (currentPosition == null) {
                System.err.println("da fak");
                System.exit(0);
            }

            if (isOutSideOfMap(currentPosition, matrix, facing)) {
                break;
            }
            if (isObject(currentPosition, matrix, facing)) {
                facing = turn(facing);
            }
            if (contains(currentPosition, visited)) {
                return true;
            } else {
                currentPosition = new Node(currentPosition.getX(), currentPosition.getY(), facing);
                visited.add(currentPosition);
            }
            currentPosition = move(currentPosition, facing);
        }
        return false;
    }

    public static String replaceCharacter(String str, int index, char newChar) {
        // Ensure the index is within bounds
        if (index < 0 || index >= str.length()) {
            throw new IllegalArgumentException("Index is out of range");
        }

        // Use StringBuilder for modification
        StringBuilder sb = new StringBuilder(str);
        if (sb.charAt(index) == '\n'  || sb.charAt(index) == '^') {
            return sb.toString();
        }
        sb.setCharAt(index, newChar);
        return sb.toString();
    }

    private static boolean contains(Node node, List<Node> list) {
        for (Node n : list) {
            if (n.getX() == node.getX() && n.getY() == node.getY() && n.getFacing() == node.getFacing()) {
                return true;
            }
        }
        return false;
    }

    private static Node move(Node currentPosition, Facing facing) {
        switch (facing) {
            case NORTH -> {
                return new Node(currentPosition.getX(), currentPosition.getY() - 1, facing);
            }
            case EAST -> {
                return new Node(currentPosition.getX() + 1, currentPosition.getY(), facing);
            }
            case SOUTH -> {
                return new Node(currentPosition.getX(), currentPosition.getY() + 1, facing);
            }
            case WEST -> {
                return new Node(currentPosition.getX() - 1, currentPosition.getY(), facing);
            }
        }
        return null;
    }

    private static boolean isObject(Node currentPosition, Matrix matrix, Facing facing) {
        switch (facing) {
            case NORTH -> {
                if (matrix.getData()[currentPosition.getX()][currentPosition.getY() - 1].equals("#")) {
                    return true;
                }
            }
            case EAST -> {
                if (matrix.getData()[currentPosition.getX() + 1][currentPosition.getY()].equals("#")) {
                    return true;
                }
            }
            case SOUTH -> {
                if (matrix.getData()[currentPosition.getX()][currentPosition.getY() + 1].equals("#")) {
                    return true;
                }
            }
            case WEST -> {
                if (matrix.getData()[currentPosition.getX() - 1][currentPosition.getY()].equals("#")) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isOutSideOfMap(Node currentPosition, Matrix matrix, Facing facing) {
        switch (facing) {
            case NORTH -> {
                if (matrix.getHeight() <= currentPosition.getY() - 1 || currentPosition.getY() - 1 < 0) {
                    return true;
                }
            }
            case EAST -> {
                if (matrix.getWidth() <= currentPosition.getX() + 1 || currentPosition.getX() + 1 < 0) {
                    return true;
                }
            }
            case SOUTH -> {
                if (matrix.getHeight() <= currentPosition.getY() + 1 || currentPosition.getY() + 1 < 0) {
                    return true;
                }
            }
            case WEST -> {
                if (matrix.getWidth() <= currentPosition.getX() - 1 || currentPosition.getX() - 1 < 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private static Facing turn(Facing facing) {
        if (facing == Facing.NORTH) {
            return EAST;
        }
        if (facing == EAST) {
            return SOUTH;
        }

        if (facing == SOUTH) {
            return WEST;
        }
        if (facing == WEST) {
            return Facing.NORTH;
        }
        return Facing.NORTH;
    }

    public static Node findStart(List<String> input) {
        for (int i = 1; i < input.size() - 1; i++) {
            String line = input.get(i);

            String[] chars = line.split("");
            for (int j = 1; j < chars.length - 1; j++) {
                if (Objects.equals(chars[j], "^")) {
                    // this is already in base 0
                    return new Node(j, i);
                }
            }
        }
        return null;
    }

}