package Game_Component;

import javax.swing.*;
import java.awt.*;

public class Jerry extends MovingPlayer {
    static final Color color = Color.ORANGE;
    public Jerry(JPanel panel, int row, int col){
        super(panel,row,col);
        panel.setBackground(color);
    }
}
