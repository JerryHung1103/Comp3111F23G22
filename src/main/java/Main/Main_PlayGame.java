package Main;
import Game_Component.*;//my package 

import java.util.Arrays;
import java.util.List;

import static Algorithm.PathFinder.findAllPaths;

public class Main_PlayGame {
     static public int GAME_STATE =0;
    public static void main(String [] args){
        if(GAME_STATE==0){
           new Maze();
        }

        else if(GAME_STATE==1){
            System.out.println("Start main");
            List<List<int[]>> paths = findAllPaths(Maze.map, 0, 0, 29, 29);
            for (List<int[]> path : paths) {
                for (int[] point : path) {
                    System.out.print(Arrays.toString(point) + " ");
                }
                System.out.println();
            }
        }



    }
}
