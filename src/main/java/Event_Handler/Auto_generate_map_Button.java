package Event_Handler;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Game_Component.*;//my package
public class Auto_generate_map_Button extends MouseAdapter{


    @Override
    public void mouseClicked(MouseEvent e) {
        Maze.auto_generate_maze();
    }
}
