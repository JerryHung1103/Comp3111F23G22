package Game_Component;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import static Algorithm.PathFinder.findShortestPath;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class MazeTest {

    private Maze maze=new Maze(false);
    @Before
    public void setUp() {
        Maze.Reset();
    }


    @Test
    void testEntryExist(){

        assertFalse(maze.Entry_Exist());
    }


    @Test
    void testExitExist(){
        assertFalse(maze.Exit_Exist());
    }

    @Test
    public void testGetRandomBoundaryPoint() {
        boolean[]testcase={false,false,false,false};
        while (!testcase[0]||!testcase[1]||!testcase[2]||!testcase[3]){
            int[] point = Maze.getRandomBoundaryPoint(Maze.map);
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
        List<int[]> path = List.of(
                new int[]{0, 0},
                new int[]{0, 1},
                new int[]{0, 2}
        );
        assertTrue(Maze.Path_Exist(path));

        List<int[]> pathWithGap = List.of(
                new int[]{0, 0},
                new int[]{0, 2}
        );
        assertFalse(Maze.Path_Exist(pathWithGap));
    }

    @Test
    public void testAutoGenerateMap() {
        int[][] generatedMap = Maze.Auto_Generate_Map();
        assertEquals(Maze.ROWS, generatedMap.length);
        assertEquals(Maze.COLS, generatedMap[0].length);
        for (int i = 0; i < Maze.ROWS; i++) {
            for (int j = 0; j < Maze.COLS; j++) {
                assertTrue(generatedMap[i][j] == 0 || generatedMap[i][j] == 1);
            }
        }
    }

    @Test
    void testget_panel(){

        assertEquals( Maze.get_panel(787, 878),null );
        assertTrue(Maze.mazeMap.keySet().contains(Maze.get_panel(29, 29)));
    }
    @Test
    public void testShowPath() {
        List<int[]> path = new ArrayList<>();
        path.add(new int[]{0, 0});
        path.add(new int[]{0, 1});
        path.add(new int[]{0, 2});
        Maze.Show_Path(path);
        for (int[] coordinate : path) {
            JPanel panel = Maze.get_panel(coordinate[0], coordinate[1]);
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
        assertNull(Maze.Get_Exit());
    }
    @Test
    public void Test_Get_Entry() {
        assertNull(Maze.Get_Entry());
    }
    @Test
    public void testAutoGenerateMaze() {

        Maze.Auto_Generate_Maze();
        List<int[]> path = findShortestPath(Maze.map,Maze.entry[0],Maze.entry[1], Maze.exit[0], Maze.exit[1]);
        assertTrue(Maze.Path_Exist(path));

    }

    @Test
    public void TestReset() {
        Maze.Reset();
        for(JPanel jPanel: Maze.mazeMap.keySet()){
            assertTrue(Maze.mazeMap.get(jPanel) instanceof ClearVertex);
        }


    }



}