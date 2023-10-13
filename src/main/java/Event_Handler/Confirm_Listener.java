package Event_Handler;

import Game_Component.Maze;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Confirm_Listener extends MouseAdapter {


    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            Maze.save_map();

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
