package Event_Handler;

import Game_Component.Maze;
import My_Functional_Interface.OptionPane;
import Main.Main_PlayGame;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * This class is for bring the game to next stage after checking it is valid
 * @author Jerry Hung
 */
public class Confirm_Button extends MouseAdapter {
    /**
     * The option Pane that shows some error message if the user input it invalid
     */
    public OptionPane optionPane =( parentComponent,  message,  title,  optionType,  messageType)->JOptionPane.showConfirmDialog(parentComponent,message,title,optionType,messageType);


    /**
     * This method is for taking the action that the user click the confirm button
     * @param e It indicates the mouse event
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (Maze.Entry_Exist() && Maze.Exit_Exist()&& Maze.Path_Exist()) {
            Maze.Save_Map();
            Main_PlayGame.stage1();
            Main_PlayGame.GAME_STATE=2;
            Main_PlayGame.main(new String[0]);
        } else {
            String message = "";
            if (!Maze.Entry_Exist()) {
                message += "Entry point is missing.\n";
            }
            if (!Maze.Exit_Exist()) {
                message += "Exit point is missing.\n";
            }
            if (!Maze.Path_Exist()) {
                message += "No at least 2 paths exist between the entry and exit points.\n";
            }

            int choice = optionPane.showConfirmDialog(null,message,"Error",JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE );
            if(choice==JOptionPane.DEFAULT_OPTION){
                //keep asking user input!
            }
        }
   }
}