package Game_Component;

import javax.swing.*;
import java.awt.*;
/**
 * This class is for storing the information of a Exit
 * @author Jerry Hung
 */
public class Exit extends ClearVertex{
    /**
     * The color of Exit
     */
    public static Color color=Color.BLUE;
    /**
     * This method is for constructing the Exit instances.
     * @param panel It indicates the target panel
     * @param row It indicates the target row
     * @param col It indicates the target column
     */
    public Exit(JPanel panel, int row, int col){
        super(panel,row,col);
        panel.setBackground(color);
    }
}