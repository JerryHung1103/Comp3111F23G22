package Game_Component;

import javax.swing.*;
import java.awt.*;

public class Exit extends ClearVertex{
    static Color color=Color.BLUE;
    public Exit(JPanel panel, int row, int col){
        super(panel,row,col);
        panel.setBackground(color);
    }
}