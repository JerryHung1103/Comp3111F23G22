package Test7;
import Game_Component.*;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class Constructor_test {
    @Test
    public void test_vertex_ctor(){
        JPanel jPanel=new JPanel();
        int row=0;
        int col=5;
        VertexLocation vertexLocation=new VertexLocation(jPanel,row,col);
        assertEquals(row,vertexLocation.x);
        assertEquals(col,vertexLocation.y);
        assertEquals(jPanel,vertexLocation.squarePanel);
    }

    @Test
    public void test_clearvertex_ctor(){
        JPanel jPanel=new JPanel();
        int row=0;
        int col=5;
        ClearVertex clearVertex=new ClearVertex(jPanel,row,col);
        assertEquals(row,clearVertex.x);
        assertEquals(col,clearVertex.y);
        assertEquals(jPanel,clearVertex.squarePanel);
        assertEquals(Color.WHITE,ClearVertex.color);
    }

    @Test
    public void test_barrier_ctor(){
        JPanel jPanel=new JPanel();
        int row=0;
        int col=5;
        Barrier barrier=new Barrier(jPanel,row,col);
        assertEquals(row,barrier.x);
        assertEquals(col,barrier.y);
        assertEquals(jPanel,barrier.squarePanel);
        assertEquals(Color.DARK_GRAY,Barrier.color);
    }

    @Test
    public void test_Entry_ctor(){
        JPanel jPanel=new JPanel();
        int row=0;
        int col=5;
        Entry entry=new Entry(jPanel,row,col);
        assertEquals(row,entry.x);
        assertEquals(col,entry.y);
        assertEquals(jPanel,entry.squarePanel);
        assertEquals(Color.YELLOW,Entry.color);
    }

    @Test
    public void test_Exit_ctor(){
        JPanel jPanel=new JPanel();
        int row=0;
        int col=5;
        Exit exit=new Exit(jPanel,row,col);
        assertEquals(row,exit.x);
        assertEquals(col,exit.y);
        assertEquals(jPanel,exit.squarePanel);
        assertEquals(Color.BLUE,Exit.color);
    }
}
