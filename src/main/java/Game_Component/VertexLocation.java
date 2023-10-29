package Game_Component;//my package

import javax.swing.*;

public class VertexLocation {
    JPanel squarePanel;
    public int x;
    public int y;
    VertexLocation(JPanel panel, int row, int col){
        squarePanel=panel;
        x=row;y=col;
    }
   }
