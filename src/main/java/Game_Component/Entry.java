package Game_Component;

import javax.swing.*;
import java.awt.*;
/**
 * This class is for storing the information of a Entry
 * @author Jerry Hung
 */
public class Entry extends ClearVertex{
    /**
     * The color of Barriers
     */
    public static Color color=Color.YELLOW;

    /**
     * This method is for constructing the Entry instances.
     * @param panel It indicates the target panel
     * @param row It indicates the target row
     * @param col It indicates the target column
     */
    public Entry(JPanel panel, int row, int col){
        super(panel,row,col);
        panel.setBackground(color);

    }
}
