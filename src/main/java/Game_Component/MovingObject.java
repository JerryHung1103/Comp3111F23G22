package Game_Component;
import Algorithm.TransposeArray;
import My_Functional_Interface.Do_Something;
import My_Functional_Interface.OptionPane;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import static Algorithm.PathFinder.findShortestPath;

/**
 * This class is for handling the movement of moving objects(i.e. Tom and Jerry)
 * @author Tom
 */
public class MovingObject extends JPanel implements KeyListener {

    /**
     * The action that starts the timer
     */
    public Do_Something timer_start=()-> new Timer(150, e->followPath()).start();


    /**
     * The option Pane that shows some message if the game is finished
     */
    public OptionPane optionPane = ( parentComponent,  message,  title,  optionType,  messageType)-> JOptionPane.showConfirmDialog(parentComponent,message,title,optionType,messageType);
    /**
     * The action that exit the program when user do not want to play it again when the game is finished.
     */
    public Do_Something exitProgram = ()->System.exit(0);

    /**
     * The action that handles the exception
     */
    public Do_Something exception_handler =()->System.out.println("Exception");

    /**
     * The x coordinate of Tom
     */
    public int TomX;

    /**
     * The y coordinate of Tom
     */
    public int TomY;

    /**
     * The x coordinate of Jerry
     */
    public int JerryX;

    /**
     * The y coordinate of Jerry
     */
    public int JerryY;

    /**
     * The Image of Tom
     */
    public Image tomImage;

    /**
     * The Image of Jerry
     */
    public Image jerryImage;

    /**
     * The Image of exit
     */
    public Image exitImage;

    /**
     * Flag to track game state
     */
    public boolean gameEnded = false;


    /**
     * The map ( translate form row-column based to rectangular coordinate based ), contains only 0s and 1s
     */
    public int[][] barriers = TransposeArray.T(Maze.map);

    /**
     * the path information
     */

    public List<int[]> path;

    /**
     * the index of shortest path
     */
    public int pathIndex;

    /**
     * This method is for constructing the MovingObject instances.
     * @param path It indicates the shortest path form entry to exit
     */

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

    /**
     * This method is for setting the image of Tom, Jerry and Exit
     * @param Tom It indicates the directory of Tom's Image
     * @param Jerry It indicates the directory of Jerry's Image
     * @param Exit It indicates the directory of Exit's Image
     */
    public void setImage(String Tom, String Jerry, String Exit ){
        try {
            tomImage = ImageIO.read(new File(Tom));
            jerryImage = ImageIO.read(new File(Jerry));
            exitImage = ImageIO.read(new File(Exit));
        }catch (IOException | NullPointerException e){
            exception_handler.do_Something();
        }
    }


    /**
     * This method is for printing the gaming interface.
     * @param g It indicates the abstract base class for all graphics contexts that allow an application to draw onto components that are realized.
     */
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


    /**
     * This method is for moving the Jerry
     * @param dx It indicates the movement of x direction
     * @param dy It indicates the movement of y direction
     */
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
    /**
     * This method is for moving the Tom
     * @param dx It indicates the movement of x direction
     * @param dy It indicates the movement of y direction
     */
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
                lose();
        }
    }
    }
    /**
     * This method is for handling the condition that user won the game.
     */
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
            exitProgram.do_Something();
        }
    }
    /**
     * This method is for handling the condition that user lose the game.
     */
    public void lose(){
        int choice=optionPane.showConfirmDialog(this,
                "You have lose the game! Do you want to restart or finish?",
                "Game Over", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (choice == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
//            System.exit(0);
            exitProgram.do_Something();

        }
    }
    /**
     * This method is for checking if the particular movement is valid.
     * @param x It indicates the x coordinate of the destination
     * @param y It indicates the y coordinate of the destination
     * @return true if the movement is valid, otherwise false
     */
    public boolean isValidMove(int x, int y) {
        // Check if the position is within the frame boundaries and no barrier is present
        return x >= 0 && x < 30 && y >= 0 && y < 30 && barriers[x][y] != 1;
    }

    /**
     * This method is for updating the path that between Tom and Jerry.
     */
    public void updatePath() {
        // Recalculate the path using Jerry's current position TomY
        List<int[]> newPath =findShortestPath(barriers, TomX, TomY, JerryX, JerryY);
        if (newPath != null) {
            path = newPath;
            pathIndex = 0;
        }
    }

    /**
     * This method is for Tom to chase the user by following the shortest path between Tom and Jerry.
     */
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
    /**
     * This method is for handling user's keyboard input.
     * @param e It indicates key that user pressed
     */
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
    /**
     * This method is redundant
     */
    @Override
    public void keyTyped(KeyEvent e) {}
    //Do nothing, but I have to implement the abstract method =.=

    /**
     * This method is redundant
     */
    @Override
    public void keyReleased(KeyEvent e) {}
    //Do nothing, but I have to implement the abstract method =.=


    /**
     * This method is for setting the delay of tom between each step,
     * smaller delay means Tom move faster
     */
    public void startTimer() {
        timer_start.do_Something();
    }



    /**
     * This method is for allowing user restart the game after the game is finished.
     */
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

