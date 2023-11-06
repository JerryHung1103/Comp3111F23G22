package Main;
import Game_Component.*;//my package
import java.util.List;
import static Algorithm.PathFinder.*;

public class Main_PlayGame {
     static public int GAME_STATE =0;
    public static void main(String [] args){
        if(GAME_STATE==0){
           Maze maze=new Maze(true);
        }

        else if(GAME_STATE==1){
            System.out.println("Start main");
            int startRow=Maze.mazeMap.get(Maze.Get_Entry()).x;
            int startCol=Maze.mazeMap.get(Maze.Get_Entry()).y;
            int endRow=Maze.mazeMap.get(Maze.Get_Exit()).x;
            int endCol=Maze.mazeMap.get(Maze.Get_Exit()).y;

            List<int[]> shortestPath = findShortestPath(Maze.map, startRow, startCol, endRow, endCol);

            if (!Maze.Path_Exist(shortestPath)) {
                System.out.println("No path found.");
                System.out.println(Maze.Path_Exist(shortestPath));
            } else {
                System.out.println("Shortest path:");
                for (int[] position : shortestPath) {
                    System.out.print("[" + position[0] + ", " + position[1] + "] ");
                }
                System.out.println();
            }
            System.out.println("B=================================================================================================");
            Maze.Show_Path(shortestPath);
            GAME_STATE++;
            Main_PlayGame.main(new String[0]);
        }
        else if(GAME_STATE==2){
            System.out.println("Lets Play!");
            Maze.init_Jerry();
        }
    }
}
