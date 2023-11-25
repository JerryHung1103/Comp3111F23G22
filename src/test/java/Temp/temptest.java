package Temp;
import Game_Component.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class temptest {
    private Maze maze = new Maze(false);
    String timer;
    String exception;
    MovingObject movingObject;
    @BeforeEach
    public void setUp(){
        maze.Auto_Generate_Maze(1);
        maze.Save_Map();
        List<int[]> path = new ArrayList<>();
        movingObject = new MovingObject(path);
        movingObject.optionPane=( parentComponent,  message,  title,  optionType,  messageType)->JOptionPane.YES_OPTION;
        movingObject.JerryX=10;
        movingObject.JerryY=10;
        movingObject.TomX=20;
        movingObject.TomY=20;
    }
    @Test
    public void test_set_image_with_exception(){
        movingObject.exception_handler =()->exception="Exception!";
        String expected="Exception!";
        String s="a";
        movingObject.setImage(s, s, s );
        assertNotNull(movingObject.tomImage);
        assertNotNull(movingObject.jerryImage);
        assertNotNull(movingObject.exitImage);
        assertEquals(expected,exception);
        //It catches the exception so will not set the image to null
    }
}
