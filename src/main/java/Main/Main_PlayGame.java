package Main;
import Game_Component.*;//my package
import My_Functional_Interface.Stage_0;
import My_Functional_Interface.Stage_2;

import java.util.ArrayList;
import java.util.List;
import static Algorithm.PathFinder.*;

public class Main_PlayGame {
    static public int GAME_STATE =0;
    static Maze maze;;
    public static Stage_0 stage0 = ()-> maze=new Maze(true);
    public static Stage_2 stage2= ()-> maze.play_game(true);

    static List<int[]> shortestPath=new ArrayList<>();

    public static void main(String [] args){
        if(GAME_STATE==0){
            stage0.newmap();
        }

         if(GAME_STATE==1){

            System.out.println("Start main");
            int startRow=Maze.mazeMap.get(Maze.Get_Entry()).x;
            int startCol=Maze.mazeMap.get(Maze.Get_Entry()).y;
            int endRow=Maze.mazeMap.get(Maze.Get_Exit()).x;
            int endCol=Maze.mazeMap.get(Maze.Get_Exit()).y;

            shortestPath = findShortestPath(Maze.map, startRow, startCol, endRow, endCol);
            System.out.println("Shortest path:");
                for (int[] position : shortestPath) {
                    System.out.print("[" + position[0] + ", " + position[1] + "] ");
                }
                System.out.println();

            System.out.println("B=================================================================================================");
            Maze.Show_Path(shortestPath);
            GAME_STATE++;
            main(new String[2]);
        }

        else if(GAME_STATE==2){
            System.out.println("play");
         if(maze!=null)
             stage2.play();
        }
    }
}
