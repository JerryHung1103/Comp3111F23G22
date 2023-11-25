package Event_Handler;
import Game_Component.Barrier;
import Game_Component.ClearVertex;
import Game_Component.Maze;
import Game_Component.VertexLocation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This class is for allowing user to input the maze by dragging the barriers to another location
 * @author Jerry Hung
 */
public class PanelDragListener extends MouseAdapter {
    /**
     * The offset of x direction
     */
    private int xOffset=0;
    /**
     * The offset of y direction
     */
    private int yOffset=0;

    /**
     * This method is for taking the action that the user drags the panel
     * @param e It indicates the mouse event
     */
    @Override
    public void mouseDragged(MouseEvent e) {
            JPanel jPanel = (JPanel) e.getSource();
            if (Maze.mazeMap.get(jPanel) instanceof Barrier) {
                jPanel.setBackground(Color.WHITE);//dangerous
                Maze.tempPanel.setVisible(true);
                JPanel panel = (JPanel) e.getSource();
                int x = panel.getX() + e.getX() - xOffset;
                int y = panel.getY() + e.getY() - yOffset;
                Maze.tempPanel.setLocation(x, y);
            }
    }
    /**
     * The inner class of handling different situation of user's actions
     */
    public static class PanelMouseListener extends MouseAdapter {
        /**
         * This method is for taking the action that the user release the mouse after dragging some vertexes
         * @param e It indicates the mouse event
         */
        @Override
        public void mouseReleased(MouseEvent e) {
            try {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    JPanel original = (JPanel) e.getSource();
                    Maze.tempPanel.setLocation(-1000, -1000);
                    JPanel releasedComponent = (JPanel) Maze.gridPanel.getComponentAt(Maze.gridPanel.getMousePosition());
                    if (Maze.mazeMap.get(original) instanceof Barrier) {

                        if (original.equals(releasedComponent)) {
                            VertexLocation vertex = new ClearVertex(original, Maze.mazeMap.get(original).x, Maze.mazeMap.get(original).y);
                            Maze.mazeMap.put(original, vertex);
                        } else if (Maze.mazeMap.get(releasedComponent) instanceof Barrier) {
                            VertexLocation vertex = new Barrier(original, Maze.mazeMap.get(original).x, Maze.mazeMap.get(original).y);
                            Maze.mazeMap.put(original, vertex);
                            original.setVisible(true);
                            Maze.tempPanel.setVisible(true);
                        } else {
                            VertexLocation vertex = new ClearVertex(original, Maze.mazeMap.get(original).x, Maze.mazeMap.get(original).y);
                            Maze.mazeMap.put(original, vertex);
                            original.setVisible(true);
                            Maze.tempPanel.setVisible(true);

                            Maze.mazeMap.put(releasedComponent, new Barrier(releasedComponent, Maze.mazeMap.get(releasedComponent).x, Maze.mazeMap.get(releasedComponent).y));
                        }
                    } else {
                        Maze.mazeMap.put(original, new Barrier(original, Maze.mazeMap.get(original).x, Maze.mazeMap.get(original).y));
                    }
                }
            }catch (Exception ee){}
        }
    }
}