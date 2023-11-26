package Test4;

import Game_Component.Maze;
import Game_Component.MovingObject;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
public class TestEndGame {
    private Maze maze = new Maze(false);
    JFrame newFrame;
    MovingObject movingObject;

    String actual;
    @BeforeEach
    public void f(){
        maze.Auto_Generate_Maze(1);
        maze.Save_Map();
        List<int[]> path = new ArrayList<>();
        movingObject = new MovingObject(path);

    }
    @Test
    public void Test_End_Game_condition() {

        movingObject.optionPane=( parentComponent,  message,  title,  optionType,  messageType)->JOptionPane.YES_OPTION;
        movingObject.TomX=movingObject.JerryX;
        movingObject.TomY=movingObject.JerryY;
        movingObject.MoveTom(0,0);


        movingObject.lose();
        assertEquals(movingObject.gameEnded,false);
        assertEquals(movingObject.JerryX,Maze.mazeMap.get(Maze.Get_Entry()).y);
        assertEquals(movingObject.JerryY,Maze.mazeMap.get(Maze.Get_Entry()).x);
        assertEquals(movingObject.TomX,Maze.mazeMap.get(Maze.Get_Exit()).y);
        assertEquals(movingObject.TomY,Maze.mazeMap.get(Maze.Get_Exit()).x);

        movingObject.optionPane=( parentComponent,  message,  title,  optionType,  messageType)->JOptionPane.NO_OPTION;
        String expectrd="gg";
        movingObject.exitProgram=()->actual="gg";
        movingObject.lose();
        assertEquals(expectrd,actual);

    }

    @Test
    public void Test_End_Game_condition2() {
        movingObject.optionPane=( parentComponent,  message,  title,  optionType,  messageType)->JOptionPane.YES_OPTION;
        movingObject.JerryX=Maze.mazeMap.get(Maze.Get_Exit()).y;
        movingObject.JerryY=Maze.mazeMap.get(Maze.Get_Exit()).x;
        movingObject.MoveJerry(0,0);

        movingObject.win();

        assertEquals(movingObject.gameEnded,false);
        assertEquals(movingObject.JerryX,Maze.mazeMap.get(Maze.Get_Entry()).y);
        assertEquals(movingObject.JerryY,Maze.mazeMap.get(Maze.Get_Entry()).x);
        assertEquals(movingObject.TomX,Maze.mazeMap.get(Maze.Get_Exit()).y);
        assertEquals(movingObject.TomY,Maze.mazeMap.get(Maze.Get_Exit()).x);

        movingObject.optionPane=( parentComponent,  message,  title,  optionType,  messageType)->JOptionPane.NO_OPTION;
        String expectrd="finish";
        movingObject.exitProgram=()->actual="finish";
        movingObject.win();
        assertEquals(expectrd,actual);
    }


}
