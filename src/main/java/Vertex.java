import javax.swing.*;
import java.awt.*;

public class Vertex {
    JPanel squarePanel;
    int x;
    int y;
    Color color;
    int is_clean;
    Vertex(JPanel panel,int row, int col){
        squarePanel=panel;
        x=row;y=col;
    }

    public void Add_to_map(JPanel panel){
        panel.add(this.squarePanel);
    }

    void Set_color(Color c){
        if(c.equals(Color.WHITE)) is_clean=0;
        else if(c.equals(Color.DARK_GRAY))is_clean=1;
        color=c;
        squarePanel.setBackground(c);
    }

    int get_x(JPanel panel){
        if(panel == squarePanel){
            return x;
        }
        return -1;
    }

    int get_y(JPanel panel){
        if(panel == squarePanel){
            return y;
        }
        return -1;
    }







}
