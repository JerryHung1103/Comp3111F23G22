package Test6;

import Event_Handler.Confirm_Button;
import Game_Component.Maze;
import Game_Component.MovingObject;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
public class Test_print_Object {
    private Maze maze = new Maze(false);
    JFrame newFrame;
    MovingObject movingObject;


    @Test
    public void Test_print() {
        maze.Auto_Generate_Maze(0.95);
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
    }


}
