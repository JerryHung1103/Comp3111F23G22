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
    private Maze maze = new Maze(false);
    JFrame newFrame;
    MovingObject movingObject;
    String actual;
    @Test
    public void test_print(){
        maze.Auto_Generate_Maze(0.75);
        Confirm_Button confirmButton = new Confirm_Button();
        MouseEvent ee = new MouseEvent(maze.confirmButton, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 0, 0, 0, false);
        confirmButton.mouseClicked(ee);
        List<int[]> path = new ArrayList<>();
        newFrame = new JFrame("Moving Object");
        movingObject = new MovingObject(path);
        newFrame.add(movingObject);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.pack();
        newFrame.setVisible(true);
        movingObject.startTimer();
        assertNotNull(movingObject.tomImage);
        assertNotNull(movingObject.jerryImage);
        assertNotNull(movingObject.exitImage);


//        Maze.start=()-> actual ="It_can_run_successfully";
//        Maze.createUI(true);
//        assertNull(actual);//we did click the start button!
//        assertNotNull(Maze.jFrame);
//        assertEquals("Group 22 - Game Project", Maze.jFrame.getTitle());
//        assertEquals(JFrame.EXIT_ON_CLOSE, Maze.jFrame.getDefaultCloseOperation());
    }

    @Test
    public void test_print_maze(){
        Maze.start=()-> actual ="It_can_run_successfully";
        Maze.createUI(true);
        assertNull(actual);//we did click the start button!
        assertNotNull(Maze.jFrame);
        assertEquals("Group 22 - Game Project", Maze.jFrame.getTitle());
        assertEquals(JFrame.EXIT_ON_CLOSE, Maze.jFrame.getDefaultCloseOperation());
    }
    @Test
    public void test_print_maze_with_exception(){
        Maze.start=()->{};
        Maze.bg_image="";
        Maze.createUI(false);
    }
}
