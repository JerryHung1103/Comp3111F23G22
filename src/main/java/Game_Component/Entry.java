package Game_Component;

import javax.swing.*;
import java.awt.*;

public class Entry extends ClearVertex{
    static Color color=Color.YELLOW;
    public Entry(JPanel panel, int row, int col){
        super(panel,row,col);
        panel.setBackground(color);

    }
}
