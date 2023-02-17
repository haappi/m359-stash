package io.github.haappi.restaurant_game;

import io.github.haappi.restaurant_game.Tiles.DecorTile;
import io.github.haappi.restaurant_game.Tiles.Tile;

import java.util.ArrayList;
import java.util.Collections;

import static io.github.haappi.restaurant_game.Utils.heuristic;

public class AStar {

    private final Tile[][] grid;
    private final int goalX, goalY;
    private final ArrayList<Node> openList =
            new ArrayList<>(); // Nodes that need to be checked (or being)
    private final ArrayList<Node> closedList = new ArrayList<>(); // Nodes that have been checked
    private final ArrayList<Node> path = new ArrayList<>();

    public AStar(Tile[][] grid, int goalX, int goalY) {
        this.grid = grid;
        this.goalX = goalX;
        this.goalY = goalY;
    }

    private static Node getFScoreNode(
            ArrayList<Node> list) { // This gets the lowest f score from the node list provided.
        Node lowestFCostNode = list.get(0);
        for (Node node : list) {
            if (node.getFCost() < lowestFCostNode.getFCost()) {
                lowestFCostNode = node;
            }
        }
        return lowestFCostNode;
    }

    private static boolean isValidSpot(int x, int y, Tile[][] grid) {
        return !(grid[x][y] instanceof DecorTile);// todo add a color check
//        return !grid[x][y].getStyle().contains("ff0000");
    }

    public void calculate(int startX, int startY) {
        Node startNode = new Node(startX, startY);
        startNode.setGCost(0); // No cost because it's the start node
        startNode.setHCost(
                heuristic(
                        startNode, goalX,
                        goalY)); // use pythagorean thingy to get distance between the two
        // points

        openList.add(startNode); // Add the start node to the open list

        while (openList.size() != 0) { // this is when the algorithm is done
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
                for (int j = -1; j <= 1; j++) { // check all neighbors
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
                        continue; // Already checked this node
                    }

                    if (!isValidSpot(newX, newY, grid)) {
                        continue; // Not a valid spot
                    }

                    double newGCost;

                    if (i != 0 && j != 0) { // Higher values for diagonals
                        newGCost =
                                smallFCost.getGCost()
                                        + 1.6; // We should try avoiding diagonals when they just
                        // look stupid in the path
                    } else {
                        newGCost = smallFCost.getGCost() + 1; // Prioritize going straight.
                    }

                    if (!openList.contains(neighbor)) {
                        openList.add(neighbor);
                    } else if (newGCost >= neighbor.getGCost()) {
                        continue; // This path is worse than the previous one
                        // it could also mean we're straying away from the goal, so we should stop
                        // now and not consume resources
                    }

                    neighbor.setHCost(heuristic(neighbor, goalX, goalY));
                    neighbor.setParent(smallFCost);
                    neighbor.setGCost(newGCost);
                }
            }
        }

        System.out.println("No path found");
    }

    /**
     * Gets the path from the start to the end.
     */
    public ArrayList<Node> getPath() {
        Collections.reverse(
                path); // Reverse the path so it's in the right order (start point to end)
        return path;
    }
}
