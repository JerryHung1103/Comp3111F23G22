package Game_Component;//my package
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import Event_Handler.*;

import static Algorithm.PathFinder.city_block_distance;

public class Maze extends JFrame {
    public static JPanel gridPanel = new JPanel();
    public static final int ROWS = 30;
    public static final int COLS = 30;
    public static final int SQUARE_SIZE = 30;
    public static int[][] map = new int[ROWS][COLS];

    public static JPanel tempPanel = new JPanel();

    public static Map<JPanel, VertexLocation> mazeMap;

    public Maze() {
        mazeMap = new HashMap<>();
        setTitle("Grid Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton autoGenerateButton = new JButton("Auto-generate maze");
        Auto_generate_map_Button listener = new Auto_generate_map_Button();
        autoGenerateButton.addMouseListener(listener);


        JButton confirmButton = new JButton("Confirm");
        Confirm_Button confirmListener = new Confirm_Button();
        confirmButton.addMouseListener(confirmListener);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(autoGenerateButton);
        buttonPanel.add(confirmButton);

        gridPanel.setLayout(new GridLayout(ROWS, COLS));

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                JPanel squarePanel = new JPanel();
                PX_Square_Button listener_ = new PX_Square_Button();

              //  squarePanel.addMouseListener(listener_);
                squarePanel.addMouseMotionListener(new PanelDragListener());
                squarePanel. addMouseListener(new PanelDragListener. PanelMouseListener());

                squarePanel.setPreferredSize(new Dimension(SQUARE_SIZE, SQUARE_SIZE));
                squarePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                mazeMap.put(squarePanel, new ClearVertex(squarePanel, i, j));
                gridPanel.add(squarePanel);
            }
        }





        tempPanel.setBackground(Color.RED);
        tempPanel.setBounds(0, 0, 30, 30);
        tempPanel.addMouseMotionListener(new PanelDragListener());
        tempPanel.addMouseListener(new PanelDragListener. PanelMouseListener());

        tempPanel.setVisible(false);
        getContentPane().add(tempPanel);



        getContentPane().add(gridPanel);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void load_maze() {//load the grid as boolean map
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (map[i][j] == 1) {
                    if (!(mazeMap.get(get_panel(i, j)) instanceof Barrier)) {
                        mazeMap.put(get_panel(i, j), new Barrier(get_panel(i, j), i, j));
                         get_panel(i, j).repaint();
                    }
                } else if (map[i][j] == 0) {
                    if (!(mazeMap.get(get_panel(i, j)) instanceof ClearVertex)) {
                        mazeMap.put(get_panel(i, j), new ClearVertex(get_panel(i, j), i, j));
                        get_panel(i, j).repaint();
                    }
                }
            }
        }
    }
    public static JPanel get_panel(int row, int col) {
        for (JPanel jPanel : mazeMap.keySet()) {
            if (mazeMap.get(jPanel).x == row && mazeMap.get(jPanel).y == col) {
                return jPanel;
            }
        }
        return null;
    }
    public VertexLocation get_vertex(int row, int col) {
        for (VertexLocation vertexLocation : mazeMap.values()) {
            if (vertexLocation.x == row && vertexLocation.y == col) {
                return vertexLocation;
            }
        }
        return null;
    }
    public static void auto_generate_maze() {//function for Task A
        for (JPanel jPanel : mazeMap.keySet()) {
            int x = mazeMap.get(jPanel).x;
            int y = mazeMap.get(jPanel).y;
            Random random = new Random();
            double randomNumber = random.nextDouble();
            if (randomNumber >0.75)
                Maze.mazeMap.put(jPanel, new Barrier(jPanel, x, y));
            else
                Maze.mazeMap.put(jPanel, new ClearVertex(jPanel, x, y));
        }
    }

    public static void save_map() throws IOException {//save map to csv and update the boolean map
        FileWriter writer = new FileWriter("maze.csv");
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                for (JPanel jPanel : mazeMap.keySet()) {
                    int x = mazeMap.get(jPanel).x;
                    int y = mazeMap.get(jPanel).y;
                    if (x == i && y == j) {
                        map[x][y] = (mazeMap.get(jPanel) instanceof ClearVertex) ? 0 : 1;
                        writer.append(map[i][j] == 1 ? "1" : "0");
                        if (j < COLS - 1) {
                            writer.append(",");
                        } else {
                            writer.append("\n");
                        }
                    }
                }
            }
        }
        writer.close();
    }




    public static void show_path( List<int[]> path){
        if(!Path_Exist(path)) return;
        for(int[] coordinate : path){
            JPanel panel= get_panel(coordinate[0]   , coordinate[1]);
            panel.setBackground(Color.GREEN);
            panel.repaint();

        }
    }


    public static boolean Path_Exist( List<int[]> path){
        return ! (path.size()<city_block_distance(path.get(0)[0],
                                                path.get(0)[1],
                                                path.get(path.size()-1)[0],
                                                path.get(path.size()-1)[1]));
    }


}

