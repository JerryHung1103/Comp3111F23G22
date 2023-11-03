package Test0;
import Event_Handler.PanelDragListener;
import Event_Handler.RightClickListener;
import Game_Component.*;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import static org.junit.jupiter.api.Assertions.*;
public class PanelDragListenerTest {
    private static Maze maze=new Maze(true);
    @Before
    public void setUp() {
        RightClickListener.popupMenu= new JPopupMenu();
        maze.Reset();
    }
    @Test
    void Test_() {
        try {
            Robot robot = new Robot();
            robot.mouseMove(500, 500);
            //Test1: mouseDragged function
            JPanel panel = new JPanel();
            maze.mazeMap.put(panel, new Barrier(panel, 0, 0));
            MouseEvent mouseEvent = new MouseEvent(panel, MouseEvent.MOUSE_DRAGGED, System.currentTimeMillis(), 0, 10, 10, 0, false);
            PanelDragListener panelDragListener = new PanelDragListener();
            panelDragListener.mouseDragged(mouseEvent);
            assertEquals(panel.getBackground(), Color.WHITE);//color change for UI
            assertTrue(maze.mazeMap.get(panel) instanceof Barrier);// But the actual class did not change yet

            //Test2: dragging form barrier to clean
            JPanel barrier_panel = new JPanel();
            maze.mazeMap.put(barrier_panel, new Barrier(barrier_panel, 0, 0));
            MouseEvent release_event = new MouseEvent(barrier_panel, MouseEvent.MOUSE_RELEASED, System.currentTimeMillis(), 0, 50, 50, 1, false, MouseEvent.BUTTON1);
            PanelDragListener.PanelMouseListener panelMouseListener = new PanelDragListener.PanelMouseListener();
            panelMouseListener.mouseReleased(release_event);
            assertTrue(maze.mazeMap.get(barrier_panel) instanceof ClearVertex);
            //It is expected that the Barrier was changed to ClearnVertex after dragging

            //Test3: dragging source is Clean
            JPanel CleanPlanel = new JPanel();
            maze.mazeMap.put(CleanPlanel, new ClearVertex(CleanPlanel, 0, 0));
            MouseEvent release_event2 = new MouseEvent(CleanPlanel, MouseEvent.MOUSE_RELEASED, System.currentTimeMillis(), 0, 50, 50, 1, false, MouseEvent.BUTTON1);
            PanelDragListener.PanelMouseListener panelMouseListener2 = new PanelDragListener.PanelMouseListener();
            panelMouseListener2.mouseReleased(release_event2);
            assertTrue(maze.mazeMap.get(CleanPlanel) instanceof Barrier);
            //After dragging a Clean panel, it is expected that the source become Barrier

            //Test4: dragging barrier to barrier
            JPanel barrier_panel3 = new JPanel();
            Maze.mazeMap.put(barrier_panel3, new Barrier(barrier_panel3, 0, 0));
            MouseEvent release_event3 = new MouseEvent(barrier_panel3, MouseEvent.MOUSE_RELEASED, System.currentTimeMillis(), 0, 50, 50, 1, false, MouseEvent.BUTTON1);
            PanelDragListener.PanelMouseListener panelMouseListener3 = new PanelDragListener.PanelMouseListener();
            panelMouseListener3.mouseReleased(release_event3);
            assertTrue(maze.mazeMap.get(barrier_panel3) instanceof Barrier);
            // It tests that when we drag a barrier to a barrier, it remains unchanged

            //Test5: dragging some barriers to itself
           for(JPanel barrier_panel4:maze.mazeMap.keySet()){
               if(barrier_panel4.getMousePosition()!=null){
                   Maze.mazeMap.put(barrier_panel4, new Barrier(barrier_panel4, 0, 0));
                   MouseEvent release_event4 = new MouseEvent(barrier_panel4, MouseEvent.MOUSE_RELEASED, System.currentTimeMillis(), 0, 50, 50, 1, false, MouseEvent.BUTTON1);
                   PanelDragListener.PanelMouseListener panelMouseListener4 = new PanelDragListener.PanelMouseListener();
                   panelMouseListener4.mouseReleased(release_event4);
                   assertTrue(maze.mazeMap.get(barrier_panel4) instanceof ClearVertex);
                   //This tests when we click on the same Barrier panel, it becomes Clean
               }
           }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}