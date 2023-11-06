package Game_Component;

import javax.swing.*;
import java.awt.*;

public class Jerry extends VertexLocation {
    static final Color color = Color.ORANGE;
    public Jerry(JPanel panel, int row, int col){
        super(panel,row,col);
        JLabel label = new JLabel(); //JLabel Creation
        label.setIcon(new ImageIcon("src/main/java/Game_Component/Jerry.png")); //Sets the image to be displayed as an icon
        Dimension size = label.getPreferredSize(); //Gets the size of the image
        label.setBounds(50, 30, size.width, size.height); //Sets the location of the image
        label.setMaximumSize(new Dimension(10, 10));
        label.setMinimumSize(new Dimension(10, 10));
        squarePanel.add(label);
        panel.setBackground(color);
        panel.add(label);
    }
}