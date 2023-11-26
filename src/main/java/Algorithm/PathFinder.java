package Algorithm;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import static java.lang.Math.abs;
/**
 * This class is for finding the shortest path
 * @author Paddy
 */
public class PathFinder {
    /**
     * This method is for calculation the manhattan distance between 2 coordinates
     * @param x1 It indicates the x coordinate of point 1
     * @param y1 It indicates the y coordinate of point 1
     * @param x2 It indicates the x coordinate of point 2
     * @param y2 It indicates the y coordinate of point 2
     * @return the manhattan distance between point 1 and point 2
     */
    public static int city_block_distance(int x1, int y1, int x2, int y2) {
        return abs(x1 - x2) + abs(y1 - y2);
    }

    /**
     * This method is for finding the shortest path, given the map and information of exit and entry
     * @param map It indicates map is used to find the path
     * @param startRow It indicates the row number of starting point
     * @param startCol It indicates the column number of starting point
     * @param endRow It indicates the row number of the destination
     * @param endCol It indicates the column number of the destination
     * @return a list of coordinate that constructs the shortest path
     */
    public static List<int[]> findShortestPath(int[][] map, int startRow, int startCol, int endRow, int endCol) {
        int Rows = map.length;
        int Cols = map[0].length;
        boolean[][] closed = new boolean[Rows][Cols];
        int[][] G_cost = new int[Rows][Cols];
        int[][] previous = new int[Rows][Cols];

        // Initialize distance array with maximum values
        for (int i = 0; i < Rows; i++) {
            for (int j = 0; j < Cols; j++) {
                G_cost[i][j] = Integer.MAX_VALUE;
            }
        }

        // Initialize priority queue for A* algorithm
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> G_cost[a[0]][a[1]] - G_cost[b[0]][b[1]]);

        // Add the start position to the queue
        queue.offer(new int[]{startRow, startCol});
        G_cost[startRow][startCol] = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int Row = current[0];
            int Col = current[1];

            // Check if we reached the destination
            if (Row == endRow && Col == endCol) {
                break;
            }

            // Skip if the current position is already closed
            closed[Row][Col] = true;

            // Explore neighboring cells
            int[][] neighbors = {{Row + 1, Col}, {Row - 1, Col}, {Row, Col + 1}, {Row, Col - 1}};
            for (int[] neighbor : neighbors) {
                int newRow = neighbor[0];
                int newCol = neighbor[1];

                // Check if the neighbor is within bounds and not blocked
                if (newRow >= 0 && newRow < Rows && newCol >= 0 && newCol < Cols && map[newRow][newCol] != 1) {
                    int new_G_cost = G_cost[Row][Col] + 1;

                    // Update the distance and previous arrays if a shorter path is found
                    if (new_G_cost < G_cost[newRow][newCol]) {
                        G_cost[newRow][newCol] = new_G_cost;
                        previous[newRow][newCol] = Row * Cols + Col;

                        int H_cost = city_block_distance(newRow, newCol, endRow, endCol);
                        int F_cost = new_G_cost + H_cost;
                        queue.offer(new int[]{newRow, newCol, F_cost});
                    }
                }
            }
        }

        // Check if there is a valid path from start to end
        if (G_cost[endRow][endCol] == Integer.MAX_VALUE) {
            return null; // No path found
        }

        // Reconstruct the shortest path from the previous array
        List<int[]> shortestPath = new ArrayList<>();
        int currentRow = endRow;
        int currentCol = endCol;
        while (currentRow != startRow || currentCol != startCol) {
            shortestPath.add(0, new int[]{currentRow, currentCol});
            int prev = previous[currentRow][currentCol];
            currentRow = prev / Cols;
            currentCol = prev % Cols;
        }
        shortestPath.add(0, new int[]{startRow, startCol});

        return shortestPath;
    }
}