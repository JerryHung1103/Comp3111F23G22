package Algorithm;
import java.util.ArrayList;
import java.util.List;
import Game_Component.Maze;
import static java.lang.Math.abs;
public class PathFinder {
    public static int dis(int x1, int y1,int x2,int y2){
        return abs(x1-x2)+abs(y1-y2);
    }
    static int limit=0;
    public static List<List<int[]>> findAllPaths(int[][] map, int startRow, int startCol, int endRow, int endCol) {
        int rows = map.length;
        int cols = map[0].length;
        boolean[][] visited = new boolean[rows][cols];
        List<List<int[]>> paths = new ArrayList<>();
        List<int[]> currentPath = new ArrayList<>();

        dfs(-1,startRow, startCol,map,  visited,endRow,endCol,   currentPath, paths);
        return paths;
    }
    private static void dfs(int flg,int row, int col, int[][] map, boolean[][] visited, int endRow, int endCol, List<int[]> currentPath, List<List<int[]>> paths) {
        int rows = Maze.ROWS;
        int cols =Maze.COLS;
       switch(flg){
           case 0:// Down
               if(dis(row,col,endRow,endCol)>dis(row-1,col,endRow,endCol))return;
               break;
           case 1:// Up
               if(dis(row,col,endRow,endCol)>dis(row+1,col,endRow,endCol))return;
               break;
           case 2:// Right
               if(dis(row,col,endRow,endCol)>dis(row,col-1,endRow,endCol))return;
               break;
           case 3:// Left
               if(dis(row,col,endRow,endCol)>dis(row,col+1,endRow,endCol))return;
               break;
           default:break;
       }
        if(row>endRow){
            row--;
            return;

        }
        if(col>endCol) {
            col--;
            return;
        }


        if(limit==10)return;



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
            limit++;
        }
        dfs(0,row + 1, col, map, visited, endRow, endCol, currentPath, paths);  // Down
        dfs(1,row - 1, col, map, visited, endRow, endCol, currentPath, paths);  // Up
        dfs(2,row, col + 1, map, visited, endRow, endCol, currentPath, paths);  // Right
        dfs(3,row, col - 1, map, visited, endRow, endCol, currentPath, paths);  // Left
        // Backtrack: mark the current position as unvisited and remove it from the current path
        visited[row][col] = false;
        currentPath.remove(currentPath.size() - 1);
    }
}