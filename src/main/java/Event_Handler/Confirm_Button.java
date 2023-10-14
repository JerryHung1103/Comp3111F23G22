package Event_Handler;

import Game_Component.Maze;
import Main.Main_PlayGame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Confirm_Button extends MouseAdapter {


    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            Maze.save_map();
            Main_PlayGame.GAME_STATE ++;
            Main_PlayGame.main(new String[0]);

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
