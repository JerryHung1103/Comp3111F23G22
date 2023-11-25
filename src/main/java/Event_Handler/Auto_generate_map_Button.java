package Event_Handler;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Game_Component.*;//my package

/**
 * This class is for auto randomly generating the maze of the game
 * @author Jerry Hung
 */
public class Auto_generate_map_Button extends MouseAdapter{

    /**
     * This method is for taking the action that the user click the auto generate button
     * @param e It indicates the mouse event
     */
    @Override
    public void mouseClicked(MouseEvent e) {Maze.Auto_Generate_Maze(0.75);
    }
}
