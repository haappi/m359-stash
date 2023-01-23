package io.github.haappi.template;

public class Utils {

    public static int getRandomNumber(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }

    /**
     * Returns the distance between two points using Manhattan distance (if bool false) or Pythagorean distance (if bool true)
     * Calculates the difference in X and Y separately and adds them together.
     *
     * @param x           The x coordinate of the first point
     * @param y           The y coordinate of the first point
     * @param goalX       The x coordinate of the second point
     * @param goalY       The y coordinate of the second point
     * @param pythagorean if true, uses Pythagorean distance instead
     * @return The distance between the two points
     */
    public static int heuristic(int x, int y, int goalX, int goalY, boolean pythagorean) {
        if (pythagorean) {
            return (int) Math.sqrt((x - goalX) * (x - goalX) + (y - goalY) * (y - goalY));
        } else {
            return Math.abs(goalX - x) + Math.abs(goalY - y);
        }
    }

    /**
     * Returns the distance between two points using Pythagorean distance.
     * Calculates the difference in X and Y separately and adds them together.
     *
     * @param x     The x coordinate of the first point
     * @param y     The y coordinate of the first point
     * @param goalX The x coordinate of the second point
     * @param goalY The y coordinate of the second point
     * @return The distance between the two points
     */
    public static int heuristic(int x, int y, int goalX, int goalY) {
        return heuristic(x, y, goalX, goalY, true);
    }

    /**
     * Returns the distance between two points using Pythagorean distance.
     * Calculates the difference in X and Y separately and adds them together.
     *
     * @param startNode The first point
     * @param goalX     The x coordinate of the second point
     * @param goalY     The y coordinate of the second point
     */
    public static int heuristic(Node startNode, int goalX, int goalY) {
        return heuristic(startNode.getX(), startNode.getY(), goalX, goalY, true);
    }
}
