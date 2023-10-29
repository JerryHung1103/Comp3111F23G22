package Event_Handler;
import Game_Component.Barrier;
import Game_Component.ClearVertex;
import Game_Component.Maze;
import Game_Component.VertexLocation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelDragListener extends MouseAdapter {

    private int xOffset=0;
    private int yOffset=0;

    @Override
    public void mouseDragged(MouseEvent e) {
        JPanel jPanel=(JPanel)e.getSource();
       if(Maze.mazeMap.get(jPanel) instanceof Barrier){
           jPanel.setBackground(Color.WHITE);//dangerous
           Maze.tempPanel.setVisible(true);
           JPanel panel = (JPanel) e.getSource();
           int x = panel.getX() + e.getX() - xOffset;
           int y = panel.getY() + e.getY() - yOffset;
           Maze.tempPanel.setLocation(x, y);
       }



    }

    public static class PanelMouseListener extends MouseAdapter {
        @Override
        public void mouseReleased(MouseEvent e) {

            JPanel original=(JPanel)e.getSource();
            Maze.tempPanel. setLocation(-1000, -1000);
            JPanel releasedComponent = (JPanel)  Maze.gridPanel.getComponentAt(Maze.gridPanel.getMousePosition());
            if(Maze.mazeMap.get(original) instanceof Barrier){

                if(original.equals(releasedComponent)){
                    VertexLocation vertex =new ClearVertex(original,Maze.mazeMap.get(original).x,Maze.mazeMap.get(original).y);
                    Maze.mazeMap.put(original, vertex);
                }

                else if(Maze.mazeMap.get(releasedComponent) instanceof Barrier){
                    VertexLocation vertex =new Barrier(original,Maze.mazeMap.get(original).x,Maze.mazeMap.get(original).y);
                    Maze.mazeMap.put(original, vertex);
                    original.setVisible(true);
                    Maze.tempPanel.setVisible(true);
                }

                else {
                    VertexLocation vertex = new ClearVertex(original, Maze.mazeMap.get(original).x, Maze.mazeMap.get(original).y);
                    Maze.mazeMap.put(original, vertex);
                    original.setVisible(true);
                    Maze.tempPanel.setVisible(true);

                    Maze.mazeMap.put(releasedComponent, new Barrier(releasedComponent, Maze.mazeMap.get(releasedComponent).x, Maze.mazeMap.get(releasedComponent).y));
                }
            }
            else{
                Maze.mazeMap.put(original, new Barrier(original, Maze.mazeMap.get(original).x, Maze.mazeMap.get(original).y));
            }
        }
    }


}