package Game_Component;

import javax.swing.*;
import java.awt.*;

public class JerryLocation extends VertexLocation {
    static final Color color = Color.ORANGE;
    public JerryLocation(JPanel panel, int row, int col){
        super(panel,row,col);
        panel.setBackground(color);
    }
}
