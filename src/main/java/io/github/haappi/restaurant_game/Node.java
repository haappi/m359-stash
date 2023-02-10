package io.github.haappi.restaurant_game;

public class Node {
    // The node's x and y position
    private int x;
    private int y;
    // The node's parent (the node it can most efficiently be reached from)
    // Used to trace the path
    private Node parent;
    // The cost of getting from the start node to this node
    private double gCost;
    // The heuristic cost of getting from this node to the target node (estimated)
    private double hCost;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double getFCost() {
        return this.gCost + this.hCost;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public double getGCost() {
        return gCost;
    }

    public void setGCost(double gCost) {
        this.gCost = gCost;
    }

    public double getHCost() {
        return hCost;
    }

    public void setHCost(double hCost) {
        this.hCost = hCost;
    }

    @Override
    public String toString() {
        return "Node{" + "x=" + x + ", y=" + y + '}';
    }

    @Override
    public boolean equals(Object obj) {
        // I have to override this because I don't want to compare the memory address, but the
        // actual x and y values
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Node other = (Node) obj;
        if (this.x != other.x) {
            return false;
        }
        return this.y == other.y;
    }
}
