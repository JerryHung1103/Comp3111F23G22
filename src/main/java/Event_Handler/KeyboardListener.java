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
