package Game_Component;//my package

import javax.swing.*;
import java.awt.*;
/**
 * This class is for storing the information of a ClearVertex
 * @author Jerry Hung
 */
public class ClearVertex extends VertexLocation{
    /**
     * The color of ClearVertex
     */
    public static final Color color=Color.WHITE;

    /**
     * This method is for constructing the ClearVertex instances.
     * @param panel It indicates the target panel
     * @param row It indicates the target row
     * @param col It indicates the target column
     */
    public ClearVertex(JPanel panel, int row, int col){
        super(panel,row,col);
        panel.setBackground(color);
    }
}
