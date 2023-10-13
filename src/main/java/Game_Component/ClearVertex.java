package Game_Component;//my package

import javax.swing.*;
import java.awt.*;

public class ClearVertex extends VertexLocation{
    static Color color=Color.WHITE;
    public ClearVertex(JPanel panel, int row, int col){
        super(panel,row,col);
        panel.setBackground(color);
    }
}
