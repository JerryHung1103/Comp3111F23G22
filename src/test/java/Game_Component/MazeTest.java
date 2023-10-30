package Game_Component;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import static Algorithm.PathFinder.findShortestPath;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class MazeTest {


    @Test
    void testEntryExist(){
        Maze  maze = new Maze();
        assertFalse(maze.Entry_Exist());
    }


    @Test
    void testExitExist(){
        Maze  maze = new Maze();
        assertFalse(maze.Exit_Exist());
    }

    @Test
    public void testGetRandomBoundaryPoint() {
        Maze  maze = new Maze();
        boolean[]testcase={false,false,false,false};
        while (!testcase[0]||!testcase[1]||!testcase[2]||!testcase[3]){
            int[] point = maze.getRandomBoundaryPoint(maze.map);
            if( point[0] == 0&& !testcase[0]){
                assertTrue(point[1] >= 0 && point[1] < Maze.COLS);
                testcase[0]=true;
            }
            else if( point[1] == 29&& !testcase[1]){
                assertTrue(point[0] >= 0 && point[0] < Maze.ROWS);
                testcase[1]=true;
            }
            else if(point[0] ==29&& !testcase[2]){
                assertTrue(point[1] >= 0 && point[1] < Maze.COLS);
                testcase[2]=true;
            }
            else if(point[1] ==0&& !testcase[3]){
                assertTrue(point[0] >= 0 && point[0] < Maze.ROWS);
                testcase[3]=true;
            }
        }
    }

    @Test
    public void testPathExist() {
        Maze  maze = new Maze();
        List<int[]> path = List.of(
                new int[]{0, 0},
                new int[]{0, 1},
                new int[]{0, 2}
        );
        assertTrue(maze.Path_Exist(path));

        List<int[]> pathWithGap = List.of(
                new int[]{0, 0},
                new int[]{0, 2}
        );
        assertFalse(maze.Path_Exist(pathWithGap));
    }

    @Test
    public void testAutoGenerateMap() {
        Maze  maze = new Maze();
        int[][] generatedMap = maze.Auto_Generate_Map();
        assertEquals(Maze.ROWS, generatedMap.length);
        assertEquals(Maze.COLS, generatedMap[0].length);
        for (int i = 0; i < Maze.ROWS; i++) {
            for (int j = 0; j < Maze.COLS; j++) {
                assertTrue(generatedMap[i][j] == 0 || generatedMap[i][j] == 1);
            }
        }
    }




}