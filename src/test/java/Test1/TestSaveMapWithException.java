package Test1;
import Game_Component.Maze;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class TestSaveMapWithException {
    private Maze maze=new Maze(false);
    @Test
    public void TestSaveMap_with_exception() {
       maze.ROWS=-1;
       maze.Save_Map();
       assertNotNull(maze.map);
        //it catches the exception, so will not set it to null
    }
}
