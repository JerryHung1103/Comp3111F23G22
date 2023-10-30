package Event_Handler;
import Game_Component.Maze;
import org.junit.jupiter.api.Test;
import java.awt.event.MouseEvent;
import static org.junit.jupiter.api.Assertions.*;
class Auto_generate_map_ButtonTest {
    private Maze maze=new Maze(false);
    @Test
    void mouseClicked() {
        Auto_generate_map_Button autoGenerateMapButton =new Auto_generate_map_Button();
        MouseEvent e = new MouseEvent(Maze.confirmButton, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 0, 0, 0, false);
        autoGenerateMapButton.mouseClicked(e);
        assertTrue(Maze.Entry_Exist());
    }
}