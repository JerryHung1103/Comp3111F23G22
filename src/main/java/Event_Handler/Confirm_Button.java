package Event_Handler;

import Game_Component.Maze;
import My_Functional_Interface.OptionPane;
import Main.Main_PlayGame;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static Game_Component.Maze.Path_Exist;

public class Confirm_Button extends MouseAdapter {
    private OptionPane optionPane =( parentComponent,  message,  title,  optionType,  messageType)->JOptionPane.showConfirmDialog(parentComponent,message,title,optionType,messageType);
    public void setOptionPane(OptionPane optionPane) {
        this.optionPane = optionPane;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (Maze.Entry_Exist() && Maze.Exit_Exist()&& Path_Exist()) {
            Maze.Save_Map();
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
            if (!Path_Exist()) {
                message += "No path exists between the entry and exit points.\n";
            }

            int choice = optionPane.showConfirmDialog(null,message,"Error",JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE );
            if(choice==JOptionPane.DEFAULT_OPTION){
                //keep asking user input!
            }
        }
   }
}