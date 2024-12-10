package day4.src;

import day6.src.Facing;

public class Node {
    private final int x;
    private final int y;
    private final Facing facing;

    public Node(int x, int y, Facing facing) {
        this.x = x;
        this.y = y;
        this.facing = facing;
    }

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.facing = Facing.NORTH;
    }

    public Facing getFacing() {
        return facing;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Node{" +
                "x=" + x +
                ", y=" + y +
                ", facing=" + facing +
                '}';
    }
}
