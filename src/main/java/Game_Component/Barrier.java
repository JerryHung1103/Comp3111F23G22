package Game_Component;//my package

import javax.swing.*;
import java.awt.*;

public class Barrier extends VertexLocation{
    static Color color=Color.BLACK;
    public Barrier(JPanel panel, int row, int col){
        super(panel,row,col);
        panel.setBackground(color);
    }
}
