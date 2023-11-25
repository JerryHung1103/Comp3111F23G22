package Game_Component;//my package
import javax.swing.*;
import java.awt.*;
/**
 * This class is for storing the information of a barrier
 * @author Jerry Hung
 */
public class Barrier extends VertexLocation{
    /**
     * The color of Barriers
     */
    public static Color color=Color.DARK_GRAY;

    /**
     * This method is for constructing the Barrier instances.
     * @param panel It indicates the target panel
     * @param row It indicates the target row
     * @param col It indicates the target column
     */
    public Barrier(JPanel panel, int row, int col){
        super(panel,row,col);
        panel.setBackground(color);
    }
}
