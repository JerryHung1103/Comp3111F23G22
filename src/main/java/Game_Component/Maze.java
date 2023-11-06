package Game_Component;//my package
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import Event_Handler.*;
import org.jetbrains.annotations.NotNull;

import static Algorithm.PathFinder.city_block_distance;
import static Algorithm.PathFinder.findShortestPath;

public class Maze extends JFrame {
    public static JPanel gridPanel = new JPanel();
    public static int ROWS = 30;
    public static int COLS = 30;
    public static final int SQUARE_SIZE = 30;
    public static int[][] map = new int[ROWS][COLS];
    public static JButton confirmButton = new JButton("Confirm");
    public static JPanel tempPanel = new JPanel();
    public static int[] entry = new int[2];
    public static int[] exit = new int[2];

    public static Map<JPanel, VertexLocation> mazeMap;
    public Maze(Boolean Visible) {
        mazeMap = new HashMap<>();
        setTitle("Have Fun!!!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton autoGenerateButton = new JButton("Auto-generate maze");
        Auto_generate_map_Button listener = new Auto_generate_map_Button();
        autoGenerateButton.addMouseListener(listener);

        Confirm_Button confirmListener = new Confirm_Button();
        confirmButton.addMouseListener(confirmListener);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(autoGenerateButton);
        buttonPanel.add(confirmButton);
        gridPanel.setLayout(new GridLayout(ROWS, COLS));

        JLabel label = new JLabel(); //JLabel Creation
        label.setIcon(new ImageIcon("src/main/java/Game_Component/Jerry.png")); //Sets the image to be displayed as an icon
        Dimension size = label.getPreferredSize(); //Gets the size of the image
        label.setBounds(50, 30, size.width, size.height); //Sets the location of the image
        label.setMaximumSize(new Dimension(10, 10));
        label.setMinimumSize(new Dimension(10, 10));
//        label.setPreferredSize(new Dimension(50, 50));

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                JPanel squarePanel = new JPanel();
                squarePanel.addMouseMotionListener(new PanelDragListener());
                squarePanel. addMouseListener(new PanelDragListener. PanelMouseListener());
                squarePanel.addMouseListener(new RightClickListener());
                squarePanel.setPreferredSize(new Dimension(SQUARE_SIZE, SQUARE_SIZE));
                squarePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

//                squarePanel.add(label);

                mazeMap.put(squarePanel, new ClearVertex(squarePanel, i, j));
                gridPanel.add(squarePanel);

            }
        }




        tempPanel.setBackground(Color.BLACK);
        tempPanel.setBounds(-1000, -1000, SQUARE_SIZE, SQUARE_SIZE);
        tempPanel.addMouseMotionListener(new PanelDragListener());
        tempPanel.addMouseListener(new PanelDragListener. PanelMouseListener());
        tempPanel.setVisible(false);
        getContentPane().add(tempPanel);
        getContentPane().add(gridPanel);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
        setVisible(Visible);
    }


    public static void init_Jerry(){
        JPanel entry=Get_Entry();
        mazeMap.put(entry, new Jerry(entry, mazeMap.get(entry).x,  mazeMap.get(entry).y));

    }


public static void Reset(){
    for (int i = 0; i < ROWS; i++) {
        for (int j = 0; j < COLS; j++) {
            JPanel squarePanel = new JPanel();
            squarePanel.addMouseMotionListener(new PanelDragListener());
            squarePanel. addMouseListener(new PanelDragListener. PanelMouseListener());
            squarePanel.addMouseListener(new RightClickListener());
            squarePanel.setPreferredSize(new Dimension(SQUARE_SIZE, SQUARE_SIZE));
            squarePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            mazeMap.put(squarePanel, new ClearVertex(squarePanel, i, j));
            gridPanel.add(squarePanel);
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

    public static int[][] Auto_Generate_Map() {
        int[][] map = new int[ROWS][COLS];
        while(true){
            Random rand = new Random();
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    if (rand.nextDouble() < 0.75) {
                        map[i][j] = 0;
                    } else {
                        map[i][j] = 1;
                    }
                }
            }
            entry=getRandomBoundaryPoint(map);
            exit=getRandomBoundaryPoint(map);
            List<int[]> path = findShortestPath(map, entry[0], entry[1], exit[0], exit[1]);
            if(Maze.Path_Exist(path))
                break;}
        return map;
    }

    public static int[]getRandomBoundaryPoint(int[][] map) {
        Random rand = new Random();
        int[] point = new int[2];
        int side = rand.nextInt(4);  // Randomly select one of the four sides
        switch (side) {
            case 0:  // Top boundary
                point[0] = 0;
                point[1] = rand.nextInt(ROWS);
                break;
            case 1:  // Right boundary
                point[0] = rand.nextInt(ROWS);
                point[1] = 29;
                break;
            case 2:  // Bottom boundary
                point[0] = 29;
                point[1] = rand.nextInt(ROWS);
                break;
            case 3:  // Left boundary
                point[0] = rand.nextInt(ROWS);
                point[1] = 0;
                break;
        }
        // Ensure the selected point has a value of 0
        if (map[point[0]][point[1]] != 0) return getRandomBoundaryPoint(map);

        return point;
    }
    public static void Auto_Generate_Maze(){
        map=Auto_Generate_Map();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (map[i][j] == 1) {
                    if (!(mazeMap.get(get_panel(i, j)) instanceof Barrier)) {
                        mazeMap.put(get_panel(i, j), new Barrier(get_panel(i, j), i, j));
                        get_panel(i, j).repaint();
                    }
                } else if (map[i][j] == 0) {

                        mazeMap.put(get_panel(i, j), new ClearVertex(get_panel(i, j), i, j));
                        get_panel(i, j).repaint();

                }
            }
        }
        mazeMap.put(get_panel(entry[0], entry[1]), new Entry(get_panel( entry[0], entry[1]), entry[0], entry[1]));
        mazeMap.put(get_panel(exit[0], exit[1]), new Exit(get_panel( exit[0], exit[1]), exit[0], exit[1]));
    }

    public static void Save_Map() {//save map to csv and update the boolean map
        try {
            FileWriter writer = new FileWriter("maze.csv");
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    for (JPanel jPanel : mazeMap.keySet()) {
                        int x = mazeMap.get(jPanel).x;
                        int y = mazeMap.get(jPanel).y;
                        if (x == i && y == j) {
                            map[x][y] = (mazeMap.get(jPanel) instanceof ClearVertex) ? 0 : 1;
                            if (j == 0) writer.append("{");
                            writer.append(map[i][j] == 1 ? "1" : "0");
                            if (j < COLS - 1) {
                                writer.append(",");
                            } else if (j == COLS - 1 && i == ROWS - 1) {
                                writer.append("}");
                                writer.append("\n");
                            } else {
                                writer.append("},");
                                writer.append("\n");
                            }
                        }
                    }
                }
            }
            writer.close();
        }catch (Exception e){e.printStackTrace();}
    }

    public static void Show_Path(List<int[]> path){
        if(!Path_Exist(path)) return;
        for(int[] coordinate : path){
            JPanel panel= get_panel(coordinate[0]   , coordinate[1]);
            if(!(mazeMap.get(panel) instanceof Exit ||mazeMap.get(panel) instanceof Entry)){
                panel.setBackground(Color.GREEN);
                panel.repaint();
            }
        }
    }

    public static boolean Path_Exist( List<int[]> path){
        if (path == null) return false;
        if(path.size()==1)return false;
        int last=path.size()-1;
        int sedlast=path.size()-2;
        if(city_block_distance( path.get(last)[0],path.get(last)[1], path.get(sedlast)[0],  path.get(sedlast)[1])>1)
                return false;

        return true;
    }

    public static boolean Entry_Exist(){
        for(JPanel jPanel :mazeMap.keySet()){
            if(mazeMap.get(jPanel) instanceof Entry) return true;
        }
        return false;
    }

    public static JPanel Get_Entry() {
        for(JPanel jPanel :mazeMap.keySet()){
            if(mazeMap.get(jPanel) instanceof Entry) return jPanel;
        }
        return null;
    }

    public static boolean Exit_Exist(){
        for(JPanel jPanel :mazeMap.keySet()){
            if(mazeMap.get(jPanel) instanceof Exit) return true;
        }
        return false;
    }

    public static JPanel Get_Exit() {
        for(JPanel jPanel :mazeMap.keySet()){
            if(mazeMap.get(jPanel) instanceof Exit) return jPanel;
        }
        return null;
    }

}

