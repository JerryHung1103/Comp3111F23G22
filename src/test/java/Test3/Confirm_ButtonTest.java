package Test3;
import Event_Handler.Confirm_Button;
import Game_Component.Maze;
import Main.Main_PlayGame;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.event.MouseEvent;
import static org.junit.jupiter.api.Assertions.*;

class Confirm_ButtonTest {
    private Maze maze=new Maze(false);
    @Before
    public void setUp() {
        maze.Reset();
    }
    @Test
    void Test1() {
        Confirm_Button confirmButton =new Confirm_Button();
        MouseEvent e = new MouseEvent(maze.confirmButton, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 0, 0, 0, false);
        maze.Auto_Generate_Maze(0.75);
        confirmButton.mouseClicked(e);
        assertEquals(2, Main_PlayGame.GAME_STATE);
    }
    @Test
    void Test2() {
        Confirm_Button confirmButton =new Confirm_Button();
        confirmButton. optionPane=( parentComponent,  message,  title,  optionType,  messageType)-> JOptionPane.DEFAULT_OPTION;
        MouseEvent e = new MouseEvent(maze.confirmButton, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 0, 0, 0, false);
        confirmButton.mouseClicked(e);

    }
}