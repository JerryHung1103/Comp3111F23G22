import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SquareMouseListener extends MouseAdapter {
    int x,y;
    @Override
    public void mousePressed(MouseEvent e) {

                JPanel squarePanel = (JPanel) e.getSource();

        for (int i = 0; i < Maze.ROWS; i++) {
            for (int j = 0; j < Maze.COLS; j++) {
                if(Maze.mazeMap[i][j].get_x(squarePanel)!=-1)
                 x=Maze.mazeMap[i][j].get_x(squarePanel);

                if(Maze.mazeMap[i][j].get_y(squarePanel)!=-1)
                 y= Maze.mazeMap[i][j].get_y(squarePanel);
            }
        }

            if(x!=-1&&y!=-1){
                if(Maze.mazeMap[x][y].is_clean==0){
                    Maze.mazeMap[x][y].Set_color(Color.DARK_GRAY);
                }
                else{
                    Maze.mazeMap[x][y].Set_color(Color.WHITE);
                }

            }
               }}


