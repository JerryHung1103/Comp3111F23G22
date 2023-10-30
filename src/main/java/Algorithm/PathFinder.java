package Algorithm;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import static java.lang.Math.abs;

public class PathFinder {
    public static int city_block_distance(int x1, int y1, int x2, int y2) {
        return abs(x1 - x2) + abs(y1 - y2);
    }

    public static List<int[]> findShortestPath(int[][] map, int startRow, int startCol, int endRow, int endCol) {
        int rows = map.length;
        int cols = map[0].length;
        boolean[][] closed = new boolean[rows][cols];
        int[][] distance = new int[rows][cols];
        int[][] previous = new int[rows][cols];
//System.out.println("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
        // Initialize distance array with maximum values
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                distance[i][j] = Integer.MAX_VALUE;
            }
        }

        // Initialize priority queue for A* algorithm
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> distance[a[0]][a[1]] - distance[b[0]][b[1]]);

        // Add the start position to the queue
        queue.offer(new int[]{startRow, startCol});
        distance[startRow][startCol] = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];

            // Check if we reached the destination
            if (row == endRow && col == endCol) {
                break;
            }

            // Skip if the current position is already closed


            closed[row][col] = true;

            // Explore neighboring cells
            int[][] neighbors = {{row + 1, col}, {row - 1, col}, {row, col + 1}, {row, col - 1}};
            for (int[] neighbor : neighbors) {
                int newRow = neighbor[0];
                int newCol = neighbor[1];

                // Check if the neighbor is within bounds and not blocked
                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && map[newRow][newCol] != 1) {
                    int newDistance = distance[row][col] + 1;

                    // Update the distance and previous arrays if a shorter path is found
                    if (newDistance < distance[newRow][newCol]) {
                        distance[newRow][newCol] = newDistance;
                        previous[newRow][newCol] = row * cols + col;

                        int priority = newDistance + city_block_distance(newRow, newCol, endRow, endCol);
                        queue.offer(new int[]{newRow, newCol, priority});
                    }
                }
            }
        }

        // Check if there is a valid path from start to end
        if (distance[endRow][endCol] == Integer.MAX_VALUE) {
            return null; // No path found
        }

        // Reconstruct the shortest path from the previous array
        List<int[]> shortestPath = new ArrayList<>();
        int currentRow = endRow;
        int currentCol = endCol;
        while (currentRow != startRow || currentCol != startCol) {
            shortestPath.add(0, new int[]{currentRow, currentCol});
            int prev = previous[currentRow][currentCol];
            currentRow = prev / cols;
            currentCol = prev % cols;
        }
        shortestPath.add(0, new int[]{startRow, startCol});

        return shortestPath;
    }
}