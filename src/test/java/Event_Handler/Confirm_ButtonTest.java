package Event_Handler;
import Game_Component.Maze;
import Main.Main_PlayGame;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import java.awt.event.MouseEvent;
import static org.junit.jupiter.api.Assertions.*;

class Confirm_ButtonTest {
    private Maze maze=new Maze(false);
    @Before
    public void setUp() {
        Maze.Reset();
        Main_PlayGame.GAME_STATE=0;
    }
    @Test
    void test1() {
        Confirm_Button confirmButton =new Confirm_Button();
        MouseEvent e = new MouseEvent(Maze.confirmButton, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 0, 0, 0, false);
        Maze.Auto_Generate_Maze();
        confirmButton.mouseClicked(e);
        assertEquals(2, Main_PlayGame.GAME_STATE);
    }
    @Test
    void test2() {
        Confirm_Button confirmButton =new Confirm_Button();
        MouseEvent e = new MouseEvent(Maze.confirmButton, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 0, 0, 0, false);
        confirmButton.mouseClicked(e);
    }
}