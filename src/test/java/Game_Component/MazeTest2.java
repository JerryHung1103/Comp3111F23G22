package Game_Component;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static Algorithm.PathFinder.findShortestPath;
import static org.junit.jupiter.api.Assertions.*;

public class MazeTest2 {


    @Test
    void testget_panel(){

        Maze  maze = new Maze();
        assertEquals( maze.get_panel(787, 878),null );
        assertTrue(maze.mazeMap.keySet().contains(maze.get_panel(29, 29)));
    }
    @Test
    public void testShowPath() {
        Maze maze = new Maze();
        List<int[]> path = new ArrayList<>();
        path.add(new int[]{0, 0});
        path.add(new int[]{0, 1});
        path.add(new int[]{0, 2});
        maze.Show_Path(path);
        for (int[] coordinate : path) {
            JPanel panel = maze.get_panel(coordinate[0], coordinate[1]);
            Color backgroundColor = panel.getBackground();
            assertEquals(Color.GREEN, backgroundColor);
        }
    }
    @Test
    public void testSaveMap() {
        assertDoesNotThrow(() -> Maze.Save_Map());
    }
    @Test
    public void Test_Get_Exit() {
        Maze maze = new Maze();
        assertNull(maze.Get_Exit());
    }
    @Test
    public void Test_Get_Entry() {
        Maze maze = new Maze();
        assertNull(maze.Get_Entry());
    }
    @Test
    public void testAutoGenerateMaze() {
        Maze  maze = new Maze();
        maze.Auto_Generate_Maze();
        List<int[]> path = findShortestPath(maze.map,maze.entry[0],maze.entry[1], maze.exit[0], maze.exit[1]);
        assertTrue(Maze.Path_Exist(path));

    }

}
