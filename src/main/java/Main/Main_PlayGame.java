package Main;
import Game_Component.*;//my package
import java.util.List;
import static Algorithm.PathFinder.*;

public class Main_PlayGame {
     static public int GAME_STATE =0;
    public static void main(String [] args){
        if(GAME_STATE==0){
           new Maze();
        }

        else if(GAME_STATE==1){
            System.out.println("Start main");
//            for (int i = 0; i < Maze.ROWS; i++) {
//                for (int j = 0; j < Maze.COLS; j++) {
//                    System.out.print(Maze.map[i][j]);
//                }
//                System.out.println();
//            }

// For debugging

            int startRow=0;int startCol=0;int endRow=4;int endCol=5;

            List<int[]> shortestPath = findShortestPath(Maze.map, startRow, startCol, endRow, endCol);
            if (shortestPath.size()<city_block_distance(startRow,startCol,endRow,endCol)) {
                System.out.println("No path found.");
                System.out.println(Maze.Path_Exist(shortestPath));

            } else {
                System.out.println("Shortest path:");
                for (int[] position : shortestPath) {
                    System.out.print("[" + position[0] + ", " + position[1] + "] ");
                }
            }

            Maze.show_path(shortestPath);


        }

    }
}
