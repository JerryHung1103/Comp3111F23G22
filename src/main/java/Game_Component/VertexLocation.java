package Game_Component;//my package
import javax.swing.*;

/**
 * This class is for storing the information of a general vertex
 * @author Jerry Hung
 */
public class VertexLocation {

    /**
     * The panel reference
     */
    public JPanel squarePanel;

    /**
     * The row number of the vertex
     */
    public int x;

    /**
     * The column number of the vertex
     */
    public int y;

    /**
     * This method is for constructing the general vertex instances.
     * @param panel It indicates the target panel
     * @param row It indicates the target row
     * @param col It indicates the target column
     */
    public VertexLocation(JPanel panel, int row, int col){
        squarePanel=panel;
        x=row;
        y=col;
    }
}
