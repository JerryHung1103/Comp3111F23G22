package Main;
import Game_Component.*;//my package

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static Algorithm.PathFinder.*;

public class Main_PlayGame {
     static public int GAME_STATE =0;
    static List<int[]> shortestPath=new ArrayList<>();
     static Maze maze;
    public static void main(String [] args){
        if(GAME_STATE==0){
            maze=new Maze(true);
        }

        else if(GAME_STATE==1){
            System.out.println("Start main");
            int startRow=Maze.mazeMap.get(Maze.Get_Entry()).x;
            int startCol=Maze.mazeMap.get(Maze.Get_Entry()).y;
            int endRow=Maze.mazeMap.get(Maze.Get_Exit()).x;
            int endCol=Maze.mazeMap.get(Maze.Get_Exit()).y;

                shortestPath = findShortestPath(Maze.map, startRow, startCol, endRow, endCol);

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
            main(new String[2]);
        }

        else if(GAME_STATE==2){
            System.out.println("play");
            maze.play_game();
        }

        else if (GAME_STATE == 3) {
            System.out.println("end");
            // maze.end_game();
            // JOptionPane.showMessageDialog(null, "end", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
