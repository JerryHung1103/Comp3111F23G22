package Event_Handler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import Game_Component.*;
public class KeyboardListener extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        int keyCode = e.getKeyCode();
        System.out.println("keyPress: " + keyCode);
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                if (Maze.Jerry_exist()) {
                    Maze.jerry.movePlayerLeft();
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (Maze.Jerry_exist()) {
                    Maze.jerry.movePlayerRight();
                }
                break;
            case KeyEvent.VK_UP:
                if (Maze.Jerry_exist()) {
                    Maze.jerry.movePlayerUp();
                }
                break;
            case KeyEvent.VK_DOWN:
                if (Maze.Jerry_exist()) {
                    Maze.jerry.movePlayerDown();
                }
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);
        int keyCode = e.getKeyCode();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        super.keyTyped(e);
        char keyChar = e.getKeyChar();
    }
}
