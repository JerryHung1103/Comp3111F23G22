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
        //for honor student code
        //Since I choose the requirement I) of function A :
        // Write a program of Maze-Editor that allows user to build a maze manually.
        //This Auto_generate_map_Button is for user to auto generate a maze
        //Sometime users may want to play the game only, so he/she may not want to spend time to design the maze
        //This is one of my own ways to enhance the program higher than standard.
    }
}
