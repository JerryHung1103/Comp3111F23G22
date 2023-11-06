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

import static Algorithm.PathFinder.findShortestPath;
import static java.lang.Math.abs;

public class MovingObject extends JPanel implements KeyListener {
    public int TomX;
    public int TomY;
    private int JerryX;
    private int JerryY;
    Image tomImage;
    Image jerryImage;


    private int[][] barriers = TransposeArray.T(Maze.map);

    private List<int[]> path;
    private int pathIndex;

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
            tomImage = ImageIO.read(new File("src/main/java/Game_Component/tom.jpg"));
            jerryImage = ImageIO.read(new File("src/main/java/Game_Component/Jerry.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(tomImage, TomX * 30, TomY * 30, this);
        g.drawImage(jerryImage, JerryX * 30, JerryY * 30, this);

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
        }
    }

    private void MoveTom(int dx, int dy) {
        int newX = TomX + dx;
        int newY = TomY + dy;
        if (isValidMove(newX, newY)) {
            TomX = newX;
            TomY = newY;
            repaint();
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
        System.out.println("move!!!!!");
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
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void startTimer() {
        int delay = 500; // Delay between each step (500 milliseconds)
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                followPath();
            }
        };
        Timer timer = new Timer(delay, actionListener);
        timer.start();
    }


}

