package Test4;
import Event_Handler.Confirm_Button;
import Game_Component.Maze;
import Game_Component.MovingObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import static Algorithm.PathFinder.findShortestPath;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class TestMovingObject {
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
    public void Test_constructor(){
        assertNotNull(movingObject.optionPane);
        assertNotNull(movingObject.exitProgram);
        assertNotNull(movingObject.TomX);
        assertNotNull(movingObject.TomY);
        assertNotNull(movingObject.JerryX);
        assertNotNull(movingObject.JerryY);
        assertNotNull(movingObject.tomImage);
        assertNotNull(movingObject.jerryImage);
        assertNotNull(movingObject.exitImage);
        assertNotNull(movingObject.gameEnded);
        assertNotNull(movingObject.barriers);
        assertNotNull(movingObject.pathIndex);
        assertNotNull(movingObject.path);

    }
@Test
public void TestValidMove(){
    assertTrue(movingObject.isValidMove(movingObject.JerryX+1, movingObject.JerryY)&&
            movingObject.isValidMove(movingObject.JerryX-1, movingObject.JerryY)&&
            movingObject.isValidMove(movingObject.JerryX, movingObject.JerryY+1)&&
            movingObject.isValidMove(movingObject.JerryX, movingObject.JerryY-1)
     );
    assertFalse(movingObject.isValidMove(-1, -1));
}
    @Test
    public void TestJerry_move(){
            int old_Y_location=movingObject.JerryY;
            movingObject.MoveJerry(1, 0);
            assertEquals(11,movingObject.JerryX);
            assertEquals(old_Y_location, movingObject.JerryY);
            int old_Y_location_=movingObject.JerryY;
            movingObject.MoveJerry(-1, 0);
            assertEquals(10,movingObject.JerryX);
            assertEquals(old_Y_location_, movingObject.JerryY);
            int old_X_location__=movingObject.JerryX;
            movingObject.MoveJerry(0, 1);
            assertEquals(11,movingObject.JerryY);
            assertEquals(old_X_location__, movingObject.JerryX);
            int old_X_location___=movingObject.JerryX;
            movingObject.MoveJerry(0, -1);
            assertEquals(10,movingObject.JerryY);
            assertEquals(old_X_location___, movingObject.JerryX);
    }
    @Test
    public void TestTom_move(){
            int old_Y_location=movingObject.TomY;
            movingObject.MoveTom(1, 0);
            assertEquals(21,movingObject.TomX);
            assertEquals(old_Y_location, movingObject.TomY);

            int old_Y_location_=movingObject.TomY;
            movingObject.MoveTom(-1, 0);
            assertEquals(20,movingObject.TomX);
            assertEquals(old_Y_location_, movingObject.TomY);

            int old_X_location__=movingObject.TomX;
            movingObject.MoveTom(0, 1);
            assertEquals(21,movingObject.TomY);
            assertEquals(old_X_location__, movingObject.TomX);

            int old_X_location___=movingObject.TomX;
            movingObject.MoveTom(0, -1);
            assertEquals(20,movingObject.TomY);
            assertEquals(old_X_location___, movingObject.TomX);
    }


    @Test
    public void Test_Update_Path(){
        List<int[]> newPath = findShortestPath(movingObject.barriers, movingObject.TomX, movingObject.TomY, movingObject.JerryX, movingObject.JerryY);
        movingObject.updatePath();//List<int[]> newPath =findShortestPath(barriers, TomX, TomY, JerryX, JerryY);
        for(int i = 0; i<newPath.size();i++)
            assertArrayEquals(newPath.get(i),movingObject.path.get(i));
    }

    @Test
    public void Test_follow_Path(){
        movingObject.pathIndex = 0;
        movingObject.path =new ArrayList<int[]>();

            movingObject.path.add(new int[]{21, 20});
            movingObject.followPath();
            assertEquals(21, movingObject.TomX);

            movingObject.path.add(new int[]{20, 20});
            movingObject.followPath();
            assertEquals(20, movingObject.TomX);

            movingObject.path.add(new int[]{20, 21});
            movingObject.followPath();
            assertEquals(21, movingObject.TomY);

            movingObject.path.add(new int[]{20, 20});
            movingObject.followPath();
            assertEquals(20, movingObject.TomY);
    }

    @Test
    public void Test_keyPressed() {
            int old_Y_location = movingObject.JerryY;
            //movingObject.MoveTom(1, 0);
            movingObject.keyPressed(new KeyEvent(movingObject, 0, 0, 0, KeyEvent.VK_RIGHT));
            assertEquals(11, movingObject.JerryX);
            assertEquals(old_Y_location, movingObject.JerryY);

            int old_Y_location_ = movingObject.JerryY;
           // movingObject.MoveTom(-1, 0);
            movingObject.keyPressed(new KeyEvent(movingObject, 0, 0, 0, KeyEvent.VK_LEFT));
            assertEquals(10, movingObject.JerryX);
            assertEquals(old_Y_location_, movingObject.JerryY);

            int old_X_location__ = movingObject.JerryX;
            // movingObject.MoveTom(0, 1);
            movingObject.keyPressed(new KeyEvent(movingObject, 0, 0, 0, KeyEvent.VK_DOWN));
            assertEquals(11, movingObject.JerryY);
            assertEquals(old_X_location__, movingObject.JerryX);

            int old_X_location___ = movingObject.JerryX;
            movingObject.keyPressed(new KeyEvent(movingObject, 0, 0, 0, KeyEvent.VK_UP));
            assertEquals(10, movingObject.JerryY);
            assertEquals(old_X_location___, movingObject.JerryX);
        movingObject.keyTyped(new KeyEvent(movingObject, 0, 0, 0, KeyEvent.VK_UP));
        movingObject.keyReleased(new KeyEvent(movingObject, 0, 0, 0, KeyEvent.VK_UP));
    }
    @Test
    public void Test_set_image_with_exception(){
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
    @Test
    public void Test_image_without_exception(){
        String tom="src/main/java/Game_Component/tom.png";
        String jerry="src/main/java/Game_Component/Jerry.png";
        String exit="src/main/java/Game_Component/exit.jpg";
        movingObject.setImage(tom,jerry,exit);
        assertNotNull(movingObject.tomImage);
        assertNotNull(movingObject.jerryImage);
        assertNotNull(movingObject.exitImage);



    }


    @Test
    public void Test_Playgame(){
        maze.Auto_Generate_Maze(1);
        Confirm_Button confirmButton = new Confirm_Button();
        MouseEvent ee = new MouseEvent(maze.confirmButton, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 0, 0, 0, false);
        confirmButton.mouseClicked(ee);
        this.maze.play_game(false);
        assertNotNull(maze.movingObject);
    }

    @Test
    public void Test_timer(){
        String expected="timer start!";
       movingObject.timer_start=()->timer="timer start!";
       movingObject.startTimer();
       assertEquals(expected,timer);
    }
}