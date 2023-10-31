package Test2;
import Event_Handler.Auto_generate_map_Button;
import Game_Component.Maze;
import org.junit.jupiter.api.Test;
import java.awt.event.MouseEvent;
import static org.junit.jupiter.api.Assertions.*;
 class Auto_generate_map_ButtonTest {

    @Test
    protected void mouseClicked() {
        Maze maze=new Maze(false);
        Auto_generate_map_Button autoGenerateMapButton =new Auto_generate_map_Button();
        MouseEvent e = new MouseEvent(maze.confirmButton, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 0, 0, 0, false);
        autoGenerateMapButton.mouseClicked(e);
        assertTrue(maze.Entry_Exist());
    }
}