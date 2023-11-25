package Main;
import Game_Component.*;//my package
import My_Functional_Interface.Do_Something;

import java.util.ArrayList;
import java.util.List;
import static Algorithm.PathFinder.*;
/**
 * This class is the starting point of running the program
 * @author Jerry Hung
 */
public class Main_PlayGame {
    /**
     * The game stage
     */
    static public int GAME_STATE =-1;
    /**
     * The maze reference
     */
    public static Maze maze;

    /**
     * The action that takes on stage 0 of the game
     */
    public static Do_Something stage0 = ()-> maze=new Maze(true);

    /**
     * The action that takes on stage 2 of the game
     */
    public static Do_Something stage2= ()-> maze.play_game(true);

    /**
     * The action that initializes the game
     */
    public static Do_Something stage_init= ()->  Maze.createUI(true);

    /**
     * The shortest path of the game
     */
    public static List<int[]> shortestPath=new ArrayList<>();

    /**
     * The action that takes on stage 1 of the game
     */
    public static void stage1(){
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
        Maze.OutPut_Path_To_CSV("path.csv",shortestPath);

        System.out.println("================================================================================================================================================================");
        Maze.Show_Path(shortestPath);

    }

    /**
     * This method is a runnable main method of the game
     * @param args redundant parameter(we didn't use this parameter)
     */

    public static void main(String [] args){
        if(GAME_STATE==-1){
            stage_init.do_Something();
        }


        if(GAME_STATE==0){
            stage0.do_Something();
        }

         if(GAME_STATE==1){
            stage1();
            GAME_STATE=2;
            main(new String[2]);
        }

         if(GAME_STATE==2){
            System.out.println("play");
         if(maze!=null)
             stage2.do_Something();
        }
    }
}
