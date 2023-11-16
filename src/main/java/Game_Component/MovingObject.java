package Game_Component;

import Algorithm.TransposeArray;

import My_Functional_Interface.Exit_Program;
import My_Functional_Interface.OptionPane;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static Algorithm.PathFinder.findShortestPath;


public class MovingObject extends JPanel implements KeyListener {
    public OptionPane optionPane = ( parentComponent,  message,  title,  optionType,  messageType)-> JOptionPane.showConfirmDialog(parentComponent,message,title,optionType,messageType);
    public Exit_Program exitProgram = ()->System.exit(0);
    public int TomX;
    public int TomY;
    public int JerryX;
    public int JerryY;

    public Image tomImage;
    public Image jerryImage;
    public Image exitImage;
    public boolean gameEnded = false; // Flag to track game state

    public int[][] barriers = TransposeArray.T(Maze.map);

    public List<int[]> path;
    public int pathIndex;



    public MovingObject(List<int[]> path) {


        TomX = Maze.mazeMap.get(Maze.Get_Exit()).y;
        TomY = Maze.mazeMap.get(Maze.Get_Exit()).x;

        JerryX =  Maze.mazeMap.get(Maze.Get_Entry()).y;
        JerryY =  Maze.mazeMap.get(Maze.Get_Entry()).x;



        this.path = path;
        pathIndex = 0;
        addKeyListener(this);
        setFocusable(true);

        setPreferredSize(new Dimension(900, 900));
        String tom="src/main/java/Game_Component/tom.png";
        String jerry="src/main/java/Game_Component/Jerry.png";
        String exit="src/main/java/Game_Component/exit.jpg";
        setImage(tom,jerry,exit);

    }
    public void setImage(String Tom, String Jerry, String Exit ){
        try {
            tomImage = ImageIO.read(new File(Tom));
            jerryImage = ImageIO.read(new File(Jerry));
            exitImage = ImageIO.read(new File(Exit));
        }catch (IOException | NullPointerException e){}

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        int imgWidth = panelWidth / 30; // Calculate the width of the images based on panel size
        int imgHeight = panelHeight / 30; // Calculate the height of the images based on panel size
        // Draw exit
        g.drawImage(exitImage, Maze.mazeMap.get(Maze.Get_Exit()).y * imgWidth, Maze.mazeMap.get(Maze.Get_Exit()).x * imgHeight, imgWidth, imgHeight, this);
        // Draw Tom
        g.drawImage(tomImage, TomX * imgWidth, TomY * imgHeight, imgWidth, imgHeight, this);
        // Draw Jerry
        g.drawImage(jerryImage, JerryX * imgWidth, JerryY * imgHeight, imgWidth, imgHeight, this);
        g.setColor(Color.BLACK);
        // Draw barriers
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                if (barriers[i][j] == 1) {
                    g.fillRect(i * imgWidth, j * imgHeight, imgWidth, imgHeight);
                }
            }
        }
    }

    public void MoveJerry(int dx, int dy) {
        if (gameEnded) { // Check game state
            return;
        }
        int newX = JerryX + dx;
        int newY = JerryY + dy;
        if (isValidMove(newX, newY)) {
            JerryX = newX;
            JerryY = newY;
            repaint();
            if (JerryX == Maze.mazeMap.get(Maze.Get_Exit()).y && JerryY == Maze.mazeMap.get(Maze.Get_Exit()).x) {
                gameEnded=true;
                win();
            }
        }
    }

    public void MoveTom(int dx, int dy) {
        if (gameEnded) { // Check  state
            return;
        }
        int newX = TomX + dx;
        int newY = TomY + dy;
        if (isValidMove(newX, newY)) {
            TomX = newX;
            TomY = newY;
            repaint();
            if (TomX == JerryX && TomY == JerryY) {
                gameEnded = true;
                lost();
        }
    }
    }

    public void win(){
        int choice=optionPane.showConfirmDialog(
                this,
                "You have won the game! Do you want to restart or finish?",
                "Game Over",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
              );
        if (choice == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            //System.exit(0);
            exitProgram.exit_the_program();
        }
    }

    public void setOptionPane(OptionPane o) { this.optionPane = o; }
    public void lost(){
        int choice=optionPane.showConfirmDialog(this,
                "You have lost the game! Do you want to restart or finish?",
                "Game Over", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (choice == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
//            System.exit(0);
            exitProgram.exit_the_program();

        }
    }

    public boolean isValidMove(int x, int y) {
        // Check if the position is within the frame boundaries and no barrier is present
        return x >= 0 && x < 30 && y >= 0 && y < 30 && barriers[x][y] != 1;
    }

    public void updatePath() {
        // Recalculate the path using Jerry's current position TomY
        List<int[]> newPath =findShortestPath(barriers, TomX, TomY, JerryX, JerryY);
        if (newPath != null) {
            path = newPath;
            pathIndex = 0;
        }
    }

    public void followPath() {
        if (pathIndex < path.size()) {
            int[] position = path.get(pathIndex);
            int targetX = position[0];
            int targetY = position[1];
            int dx = targetX - TomX;
            int dy = targetY - TomY;
            MoveTom(dx, dy);
            if (TomX == targetX && TomY == targetY) {
                pathIndex++;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        followPath();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                MoveJerry(0, -1);
                break;
            case KeyEvent.VK_DOWN:
                MoveJerry(0, 1);
                break;
            case KeyEvent.VK_LEFT:
                MoveJerry(-1, 0);
                break;
            case KeyEvent.VK_RIGHT:
                MoveJerry(1, 0);
                break;
        }
        updatePath(); // Update the path whenever Jerry moves


    }

    @Override
    public void keyTyped(KeyEvent e) {//Do nothing, but I have to implement the abstract method =.=
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //Do nothing, but I have to implement the abstract method =.=
    }

    public void startTimer() {
        int delay = 250; // Delay between each step (500 milliseconds)
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                followPath();
            }
        };
        Timer timer = new Timer(delay, actionListener);
        timer.start();
    }




    public void resetGame() {
        gameEnded = false;
        JerryX = Maze.mazeMap.get(Maze.Get_Entry()).y;
        JerryY = Maze.mazeMap.get(Maze.Get_Entry()).x;
        TomX = Maze.mazeMap.get(Maze.Get_Exit()).y;
        TomY = Maze.mazeMap.get(Maze.Get_Exit()).x;
        barriers = TransposeArray.T(Maze.map);
        updatePath();
        repaint();
    }
}

