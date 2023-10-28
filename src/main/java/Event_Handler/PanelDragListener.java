package Event_Handler;


import Game_Component.Barrier;
import Game_Component.ClearVertex;
import Game_Component.Maze;
import Game_Component.VertexLocation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class PanelDragListener extends MouseAdapter {

    private int xOffset;
    private int yOffset;





    @Override
    public void mouseDragged(MouseEvent e) {
        JPanel jPanel=(JPanel)e.getSource();
       if(Maze.mazeMap.get(jPanel) instanceof Barrier){
           jPanel.setVisible(false);
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

            JPanel jPanel=(JPanel)e.getSource();



if(Maze.mazeMap.get(jPanel) instanceof Barrier){
    VertexLocation vertex =new ClearVertex(jPanel,Maze.mazeMap.get(jPanel).x,Maze.mazeMap.get(jPanel).y);
    Maze.mazeMap.put(jPanel, vertex);
    jPanel.setVisible(true);
    Maze.tempPanel.setVisible(false);
}
else{
    Maze.mazeMap.put(jPanel, new Barrier(jPanel, Maze.mazeMap.get(jPanel).x, Maze.mazeMap.get(jPanel).y));
}

//
//if(Maze.mazeMap.get(releasedComponent) instanceof ClearVertex){
//    Maze.mazeMap.put(releasedComponent, new Barrier(releasedComponent, Maze.mazeMap.get(releasedComponent).x, Maze.mazeMap.get(releasedComponent).y));
//}

       }
    }

}