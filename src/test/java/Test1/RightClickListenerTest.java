package Test1;
import Event_Handler.RightClickListener;
import Game_Component.*;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.event.MouseEvent;
import static org.junit.jupiter.api.Assertions.*;
class RightClickListenerTest {
private static Maze maze=new Maze(true);
    @Before
    public void setUp() {
        RightClickListener.popupMenu= new JPopupMenu();
        maze.Reset();
    }
    @Test
    public void testShowPopupMenu_EntryItemActionPerformed() {
            JPanel panel = Maze.get_panel(0, 0);
            assertNotNull(panel);
            MouseEvent mouseEvent = new MouseEvent(panel, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, panel.getX(), panel.getY(), 1, false);
            RightClickListener.showPopupMenu(mouseEvent, panel);
            assertNotNull(RightClickListener.popupMenu);
            assertEquals(2, RightClickListener.popupMenu.getComponentCount());

            JMenuItem entryItem = (JMenuItem) RightClickListener.popupMenu.getComponent(0);
            assertNotNull(entryItem);

            entryItem.doClick();
            assertEquals("Entry", entryItem.getText());
            assertTrue(Maze.Entry_Exist());
            entryItem.doClick();
            assertTrue(Maze.mazeMap.get(panel) instanceof Entry);

            JMenuItem exitItem = (JMenuItem) RightClickListener.popupMenu.getComponent(1);
            assertNotNull(exitItem);
            assertEquals("Exit", exitItem.getText());
            exitItem.doClick();
            assertTrue(Maze.Exit_Exist());
            exitItem.doClick();
            assertTrue(Maze.mazeMap.get(panel) instanceof Exit);
    }
    @Test
    void mousePressed() {

        JPanel panel1 =  maze.get_panel(0,0);
        RightClickListener rightClickListener=new RightClickListener();
        rightClickListener.mousePressed(new MouseEvent(panel1, MouseEvent.BUTTON3, System.currentTimeMillis(), 0, 10, 10, 1, true));
        assertNotNull(RightClickListener.popupMenu);
    }
    @Test
    void mouseReleased() {
        JPanel panel1 =  maze.get_panel(0,0);
        RightClickListener rightClickListener=new RightClickListener();
        rightClickListener.mouseReleased(new MouseEvent(panel1, MouseEvent.MOUSE_RELEASED, System.currentTimeMillis(), 0, 10, 10, 1, true));
        assertNotNull(RightClickListener.popupMenu);
    }
}