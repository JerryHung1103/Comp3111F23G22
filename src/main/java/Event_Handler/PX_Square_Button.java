package Event_Handler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import Game_Component.*;//my package
public class PX_Square_Button extends MouseAdapter {
    @Override
    public void mousePressed(MouseEvent e) {
        JPanel squarePanel = (JPanel) e.getSource();
        if (Maze.mazeMap.get(squarePanel) instanceof ClearVertex) {
            Maze.mazeMap.put(squarePanel, new Barrier(squarePanel, Maze.mazeMap.get(squarePanel).x, Maze.mazeMap.get(squarePanel).y));
            System.out.println("row is " + Maze.mazeMap.get(squarePanel).x);
        }
        else {
            Maze.mazeMap.put(squarePanel, new ClearVertex(squarePanel, Maze.mazeMap.get(squarePanel).x, Maze.mazeMap.get(squarePanel).y));
        }

    }

//    @Override
//    public void mouseClicked(MouseEvent e) {
//        super.mouseClicked(e);
//    }
}




