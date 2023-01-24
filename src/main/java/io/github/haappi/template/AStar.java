package io.github.haappi.template;

import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.Collections;

import static io.github.haappi.template.Utils.heuristic;

public class AStar {
    private final Button[][] grid;
    private final int goalX, goalY;
    private final ArrayList<Node> openList = new ArrayList<>(); // Nodes that need to be checked (or being)
    private final ArrayList<Node> closedList = new ArrayList<>(); // Nodes that have been checked
    private final ArrayList<Node> path = new ArrayList<>();

    public AStar(Button[][] grid, int goalX, int goalY) {
        this.grid = grid;
        this.goalX = goalX;
        this.goalY = goalY;
    }

    private static Node getFScoreNode(ArrayList<Node> list) { // This gets the lowest f score from the node list provided.
        Node lowestFCostNode = list.get(0);
        for (Node node : list) {
            if (node.getFCost() < lowestFCostNode.getFCost()) {
                lowestFCostNode = node;
            }
        }
        return lowestFCostNode;
    }

    private static boolean isValidSpot(int x, int y, Button[][] grid) {
        return !grid[x][y].getStyle().contains("ff0000");
        //        if (grid[x][y] instanceof Wall) {
        //            return false;
        //        } // todo make a check later to check for walls and what not
    }

    public void calculate(int startX, int startY) {
        Node startNode = new Node(startX, startY);
        startNode.setgCost(0); // No cost because it's the start node
        startNode.sethCost(heuristic(startNode, goalX, goalY)); // use manhattan distance thingy to get distance between the two points

        openList.add(startNode);

        while (openList.size() != 0) {
            Node smallFCost = getFScoreNode(openList);
            if (smallFCost.getX() == goalX && smallFCost.getY() == goalY) { // We found the goal
                Node current = smallFCost;
                while (current.getParent() != null) {
                    path.add(current); // Backtrack the path
                    current = current.getParent();
                }
                return;
            }

            openList.remove(smallFCost);
            closedList.add(smallFCost);

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i == 0 && j == 0) {
                        continue; // No need to check the current node
                    }

                    int newX = smallFCost.getX() + i;
                    int newY = smallFCost.getY() + j;

                    if (newX < 0 || newY < 0 || newX >= grid.length || newY >= grid[0].length) {
                        continue; // Index out of bounds
                    }

                    Node neighbor = new Node(newX, newY);
                    if (closedList.contains(neighbor)) {
                        //                        grid[newX][newY].setStyle("-fx-background-color:
                        // #ff0000");
                        //                        grid[newX][newY].setText("X");
                        continue; // Already checked this node
                    }

                    if (!isValidSpot(newX, newY, grid)) {
                        //                        grid[newX][newY].setStyle("-fx-background-color:
                        // #ff0000");
                        //                        grid[newX][newY].setText("X");
                        continue; // Not a valid spot
                    }

                    double newGCost;

                    if (i != 0 && j != 0) { // Higher values for diagonals
                        newGCost = smallFCost.getgCost() + 1.6; // We should try avoiding diagonals when they just look stupid in the path
                    } else {
                        newGCost = smallFCost.getgCost() + 1; // Prioritize going straight.
                    }

                    if (!openList.contains(neighbor)) {
                        openList.add(neighbor);
                    } else if (newGCost >= neighbor.getgCost()) {
                        continue; // This path is worse than the previous one
                    }

                    neighbor.sethCost(heuristic(neighbor, goalX, goalY));
                    neighbor.setParent(smallFCost);
                    neighbor.setgCost(newGCost);
                }
            }
        }

        System.out.println("No path found");
    }

    public ArrayList<Node> getPath() {
        Collections.reverse(path);
        return path;
    }
}
