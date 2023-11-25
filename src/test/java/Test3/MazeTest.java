package Test3;
import Game_Component.ClearVertex;
import Game_Component.Maze;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static Algorithm.PathFinder.findShortestPath;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class MazeTest {
    private List<String> readCSVFile(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    private final static Maze maze=new Maze(false);
    @Test
    public void constructor() {

        assertNotNull(maze.mazeMap);
        assertNotNull(maze.gridPanel);
        assertNotNull(maze.confirmButton);
        assertNotNull(maze.tempPanel);
        assertNotNull(maze.exit);
        assertNotNull(maze.entry);
        assertNotNull(maze.jFrame);
        assertNull(maze.movingObject);
        assertNotNull(maze.instructionsPanel);
        assertNotNull(maze.mapPanel);
        assertNotNull(maze.instructionButton);
        assertNotNull(maze.cardLayout);
        assertNotNull(maze.cardContainer);
        assertNotNull(maze.go_back);
        assertNotNull(maze.instruction);

    }
    @Test
    void testEntryExist(){
        assertFalse(maze.Entry_Exist());
        //since we didn't input the Exist, so it is expected to be false
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
        List<int[]> path = List.of(new int[]{0, 0}, new int[]{0, 1}, new int[]{0, 2});
        assertTrue(Maze.Path_Exist(path));
        List<int[]> pathWithGap = List.of(new int[]{0, 0}, new int[]{0, 2});
        assertFalse(Maze.Path_Exist(pathWithGap));
    }
    @Test
    public void testAutoGenerateMap() {
        int[][] generatedMap = Maze.Auto_Generate_Map(0.75);
        assertEquals(Maze.ROWS, generatedMap.length);
        assertEquals(Maze.COLS, generatedMap[0].length);
        for (int i = 0; i < Maze.ROWS; i++) {
            for (int j = 0; j < Maze.COLS; j++) {
                assertTrue(generatedMap[i][j] == 0 || generatedMap[i][j] == 1);
            }
        }
    }
    @Test
    void test_get_panel(){
        assertNull(Maze.get_panel(787, 878));
        // have no such index, so it is expected to be null
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
            assert panel != null;
            Color backgroundColor = panel.getBackground();
            assertEquals(Color.GREEN, backgroundColor);
        }
    }
    @Test
    public void testSaveMap() {assertDoesNotThrow(() -> Maze.Save_Map());}
    @Test
    public void Test_Get_Exit() {assertNull(Maze.Get_Exit());}
    @Test
    public void Test_Get_Entry() {
        assertNull(Maze.Get_Entry());
    }
    @Test
    public void testAutoGenerateMaze() {
        Maze.Auto_Generate_Maze(0.75);
        List<int[]> path = findShortestPath(Maze.map,Maze.entry[0],Maze.entry[1], Maze.exit[0], Maze.exit[1]);
        assertTrue(Maze.Path_Exist(path));
        assertTrue(Maze.Exit_Exist());
        assertTrue(Maze.Entry_Exist());
        assertNotNull(Maze.Get_Exit());
        assertNotNull(Maze.Get_Entry());
    }
    @Test
    public void TestReset() {
        Maze.Reset();
        for(JPanel jPanel: Maze.mazeMap.keySet()){
            assertTrue(Maze.mazeMap.get(jPanel) instanceof ClearVertex);
        }
    }

    @Test
    public void Test_output_path() {
        List<int[]> path = null;
        String outputPath = "test_path.csv";
        Maze.OutPut_Path_To_CSV(outputPath,path);//exception test



        path = new ArrayList<>();
        path.add(new int[]{1, 2});
        path.add(new int[]{3, 4});
        path.add(new int[]{5, 6});

        Maze.OutPut_Path_To_CSV(outputPath,path);
        List<String> csvLines = readCSVFile(outputPath);
        assertEquals("PathType,PathNo,Index,Row_X,Row_Y", csvLines.get(0));
        assertEquals("SP,1,0,1,2;", csvLines.get(1));
        assertEquals("SP,1,1,3,4;", csvLines.get(2));
        assertEquals("SP,1,2,5,6;", csvLines.get(3));


    }

    @Test
    public void testanotherpath(){
        int[][]map={{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
        List<int[]> path= Arrays.asList(new int[]{0,0},new int[]{0,1},new int[]{0,2});


        assertFalse( Maze.anotherpath_exist(map,path,new int[]{0,0},new int[]{0,2}));
    }


}