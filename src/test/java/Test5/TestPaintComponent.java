package Test5;
import Event_Handler.Confirm_Button;
import Game_Component.Maze;
import Game_Component.MovingObject;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
public class TestPaintComponent {
    String actual;
    @Test
    public void Test_print_maze(){
        Maze.start=()-> actual ="It_can_run_successfully";
        Maze.createUI(true);
        assertNull(actual);//we did click the start button!
        assertNotNull(Maze.jFrame);
        assertEquals("Group 22 - Game Project", Maze.jFrame.getTitle());
        assertEquals(JFrame.EXIT_ON_CLOSE, Maze.jFrame.getDefaultCloseOperation());
    }
    @Test
    public void Test_print_maze_with_exception(){
        Maze.start=()->{};
        Maze.bg_image="";
        Maze.createUI(false);
    }
}
