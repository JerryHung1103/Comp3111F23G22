package Test0;

import Event_Handler.PanelDragListener;
import Game_Component.Barrier;
import Game_Component.ClearVertex;
import Game_Component.Maze;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;

import static org.junit.jupiter.api.Assertions.*;

public class PanelDragListenerTest {

    private static Maze maze=new Maze(true);
    @Before
    public void setUp() {
        maze.Reset();
    }


        @Test
        void Test_() {


            JPanel panel = new JPanel();
            maze.mazeMap.put(panel, new Barrier(panel, 0, 0));
            MouseEvent mouseEvent = new MouseEvent(panel, MouseEvent.MOUSE_DRAGGED, System.currentTimeMillis(), 0, 10, 10, 0, false);
            PanelDragListener panelDragListener = new PanelDragListener();
            panelDragListener.mouseDragged(mouseEvent);
            assertEquals(panel.getBackground(), Color.WHITE);

            JPanel barrier_panel = new JPanel();
            maze.mazeMap.put(barrier_panel, new Barrier(barrier_panel, 0, 0));
            MouseEvent release_event = new MouseEvent(barrier_panel, MouseEvent.MOUSE_RELEASED, System.currentTimeMillis(), 0, 50, 50, 1, false, MouseEvent.BUTTON1);
            PanelDragListener.PanelMouseListener panelMouseListener = new PanelDragListener.PanelMouseListener();

            panelMouseListener.mouseReleased(release_event);
            System.out.println(maze.mazeMap.get(release_event.getSource()).getClass());
            System.out.println(maze.mazeMap.get(maze.get_panel(0, 0)).getClass());

            JPanel barrier_panel2 = new JPanel();
            maze.mazeMap.put(barrier_panel2, new ClearVertex(barrier_panel2, 0, 0));
            System.out.println(maze.mazeMap.get(maze.get_panel(0, 0)).getClass());

            MouseEvent release_event2 = new MouseEvent(barrier_panel2, MouseEvent.MOUSE_RELEASED, System.currentTimeMillis(), 0, 50, 50, 1, false, MouseEvent.BUTTON1);
            PanelDragListener.PanelMouseListener panelMouseListener2 = new PanelDragListener.PanelMouseListener();
            panelMouseListener2.mouseReleased(release_event2);
            System.out.println(maze.mazeMap.get(release_event2.getSource()).getClass());
            System.out.println(maze.mazeMap.get(maze.get_panel(0, 0)).getClass());


            JPanel barrier_panel3 = new JPanel();
            Maze.mazeMap.put(barrier_panel3, new Barrier(barrier_panel3, 0, 0));
            System.out.println(maze.mazeMap.get(maze.get_panel(0, 0)).getClass());

            MouseEvent release_event3 = new MouseEvent(barrier_panel3, MouseEvent.MOUSE_RELEASED, System.currentTimeMillis(), 0, 50, 50, 1, false, MouseEvent.BUTTON1);
            PanelDragListener.PanelMouseListener panelMouseListener3 = new PanelDragListener.PanelMouseListener();
            panelMouseListener3.mouseReleased(release_event3);
            System.out.println(maze.mazeMap.get(release_event3.getSource()).getClass());
            System.out.println(maze.mazeMap.get(maze.get_panel(0, 0)).getClass());


            JPanel barrier_panel4 = new JPanel();
            barrier_panel4.setLocation(0, 0);
            maze.mazeMap.put(barrier_panel4, new Barrier(barrier_panel4, 0, 0));
            System.out.println(maze.mazeMap.get(maze.get_panel(0, 0)).getClass());
            MouseEvent release_event4 = new MouseEvent(barrier_panel4, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 0, 0, 1, false, MouseEvent.BUTTON1);
            PanelDragListener.PanelMouseListener panelMouseListener4 = new PanelDragListener.PanelMouseListener();

            panelMouseListener4.mouseReleased(release_event4);
            System.out.println(maze.mazeMap.get(release_event4.getSource()).getClass());
            System.out.println(maze.mazeMap.get(maze.get_panel(0, 0)).getClass());

        }

}



