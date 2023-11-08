package Game_Component;

import Algorithm.TransposeArray;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.List;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.util.TimerTask;
import java.util.Timer;

import static Algorithm.PathFinder.findShortestPath;
import static java.lang.Math.abs;

public class MovingObject extends JPanel implements KeyListener {
    public int TomX;
    public int TomY;
    private int JerryX;
    private int JerryY;
    Image tomImage;
    Image jerryImage;
    Image exitImege;
    Image entryImage;


    private int[][] barriers = TransposeArray.T(Maze.map);

    private List<int[]> path;
    private int pathIndex;

    private javax.swing.Timer timer;

    private java.util.Timer moveTimer;
    private TimerTask moveTask;
    private long lastMoveTime = 0L;
    private static final long MOVE_DELAY = 80;

    public MovingObject(List<int[]> path) {
        for(int i: barriers[0]){
            System.out.println(i);
        }

        TomX = Maze.mazeMap.get(Maze.Get_Exit()).y;
        TomY = Maze.mazeMap.get(Maze.Get_Exit()).x;

        JerryX =  Maze.mazeMap.get(Maze.Get_Entry()).y;
        JerryY =  Maze.mazeMap.get(Maze.Get_Entry()).x;
        this.path = path;
        pathIndex = 0;
        addKeyListener(this);
        setFocusable(true);
        setPreferredSize(new Dimension(900, 900));

        try {
            tomImage = ImageIO.read(new File("src/main/java/Game_Component/tom.png"));
            jerryImage = ImageIO.read(new File("src/main/java/Game_Component/Jerry.png"));
            exitImege = ImageIO.read(new File("src/main/java/Game_Component/exit.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Define desired width and height for the images
        int imgWidth = 30; // replace with desired width
        int imgHeight = 30; // replace with desired height

        //tom and jerry x&y need *30 for some reason...
        g.drawImage(exitImege, Maze.mazeMap.get(Maze.Get_Exit()).y*30, Maze.mazeMap.get(Maze.Get_Exit()).x*30, imgWidth, imgHeight, this);
        g.drawImage(tomImage, TomX*30, TomY*30, imgWidth, imgHeight, this);
        g.drawImage(jerryImage, JerryX*30, JerryY*30, imgWidth, imgHeight, this);

        // Draw barriers
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                if (barriers[i][j] == 1) {
                    g.fillRect(i * 30, j* 30, 30, 30);
                }
            }
        }
    }

    private void MoveJerry(int dx, int dy) {
        int newX = JerryX + dx;
        int newY = JerryY + dy;
        if (isValidMove(newX, newY)) {
            JerryX = newX;
            JerryY = newY;
            repaint();
            checkCollision();
        }
    }

    private void MoveTom(int dx, int dy) {
        int newX = TomX + dx;
        int newY = TomY + dy;
        if (isValidMove(newX, newY)) {
            TomX = newX;
            TomY = newY;
            repaint();
            checkCollision();
            System.out.println("Tom moved");
        }
    }

    private boolean isValidMove(int x, int y) {
        // Check if the position is within the frame boundaries and no barrier is present
        return x >= 0 && x < 30 && y >= 0 && y < 30 && barriers[x][y] != 1;
    }

    private void updatePath() {
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
        int dx = 0, dy = 0;

        switch (keyCode) {
            case KeyEvent.VK_UP:
                dy = -1;
                break;
            case KeyEvent.VK_DOWN:
                dy = 1;
                break;
            case KeyEvent.VK_LEFT:
                dx = -1;
                break;
            case KeyEvent.VK_RIGHT:
                dx = 1;
                break;
        }

        final int finalDx = dx;
        final int finalDy = dy;

        if (moveTimer != null) {
            moveTimer.cancel();
        }

        moveTimer = new Timer();
        moveTask = new TimerTask() {
            @Override
            public void run() {
                // Check if enough time has passed since the last movement
                if (System.currentTimeMillis() - lastMoveTime >= MOVE_DELAY) {
                    MoveJerry(finalDx, finalDy);
                    followPath();
                    updatePath(); // Update the path whenever Jerry moves

                    // Update the last move time
                    lastMoveTime = System.currentTimeMillis();
                }
            }
        };

        moveTimer.schedule(moveTask, 0, 100); // jerry move speed
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (moveTimer != null) {
            moveTimer.cancel();
        }
    }

    public void startTimer() {
        int delay = 150; // Tom move speed
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                followPath();
            }
        };
        //Timer timer = new Timer(delay, actionListener);
        timer = new javax.swing.Timer(delay, actionListener);
        timer.start();
    }

    private void checkCollision() {
        if (TomX == JerryX && TomY == JerryY) {
            // Stop the timer
            if(timer != null) {
                // Stop the timer
                timer.stop();
            }

            // Show a message dialog
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    JOptionPane.showMessageDialog(MovingObject.this, "Game Over!");
                    System.exit(0);
                }
            });
        }

        // Jerry reaches the exit
        if (JerryX == Maze.mazeMap.get(Maze.Get_Exit()).y && JerryY == Maze.mazeMap.get(Maze.Get_Exit()).x) {
            // Stop the timer
            if(timer != null) {
                // Stop the timer
                timer.stop();
            }

            // Show a message dialog
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    JOptionPane.showMessageDialog(MovingObject.this, "Jerry escaped!");
                    System.exit(0);
                }
            });
        }
    }

}

