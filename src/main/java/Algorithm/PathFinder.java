package Algorithm;
import java.util.ArrayList;
import java.util.List;
import Game_Component.Maze;
public class PathFinder {
    public static List<List<int[]>> findAllPaths(int[][] map, int startRow, int startCol, int endRow, int endCol) {
        int rows = map.length;
        int cols = map[0].length;
        boolean[][] visited = new boolean[rows][cols];
        List<List<int[]>> paths = new ArrayList<>();
        List<int[]> currentPath = new ArrayList<>();
        dfs(startRow, startCol,map,  visited,endRow,endCol,   currentPath, paths);
        return paths;
//gg
    }
    private static void dfs(int row, int col, int[][] map, boolean[][] visited, int endRow, int endCol, List<int[]> currentPath, List<List<int[]>> paths) {
        int rows = Maze.ROWS;
        int cols =Maze.COLS;

        // Check if the current position is valid
        if (row < 0 || row >= rows || col < 0 || col >= cols || map[row][col] == 1 || visited[row][col]) {
            return;
        }

        // Mark the current position as visited
        visited[row][col] = true;
        currentPath.add(new int[]{row, col});

        // If we reached the destination, add the current path to the list of paths
        if (row == endRow && col == endCol) {
            paths.add(new ArrayList<>(currentPath));
        }

        dfs(row + 1, col, map, visited, endRow, endCol, currentPath, paths);  // Down
        dfs(row - 1, col, map, visited, endRow, endCol, currentPath, paths);  // Up
        dfs(row, col + 1, map, visited, endRow, endCol, currentPath, paths);  // Right
        dfs(row, col - 1, map, visited, endRow, endCol, currentPath, paths);  // Left

        // Backtrack: mark the current position as unvisited and remove it from the current path
        visited[row][col] = false;
        currentPath.remove(currentPath.size() - 1);
    }
}