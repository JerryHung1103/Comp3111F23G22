package Game_Component;//my package
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import Event_Handler.*;
import Main.Main_PlayGame;
import My_Functional_Interface.Do_Something;

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

    public static JFrame jFrame = new JFrame();

    public static Map<JPanel, VertexLocation> mazeMap;
    public static MovingObject movingObject=null;
    public JPanel instructionsPanel;
    public JPanel mapPanel;
    public JButton instructionButton;

    public CardLayout cardLayout;
    public static JPanel cardContainer;
    public JButton goBackButton; // Add the "Go Back" button

    public ActionListener go_back=e->cardLayout.show(cardContainer, "Map");

    public ActionListener instruction=e->cardLayout.show(cardContainer, "Instructions");

    public static Do_Something start=()-> {jFrame.dispose();jFrame=null;Main_PlayGame.GAME_STATE++;Main_PlayGame.main(new String[0]);};

    public static String bg_image="src/main/java/Game_Component/background.jpg";

    public static void createUI(Boolean Visible) {
        jFrame.setTitle("Group 22 - Game Project");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(900, 900);
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                BufferedImage backgroundImage = null;
                try {
                    backgroundImage = ImageIO.read(new File(bg_image));
                } catch (IOException e) {}
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("<html><center>Welcome! Get ready to play the game!</center></html>");
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeLabel.setFont(welcomeLabel.getFont().deriveFont(Font.BOLD, 24));
        mainPanel.add(welcomeLabel, BorderLayout.NORTH);
        JButton startButton;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        startButton = new JButton("Start");
        startButton.setFont(startButton.getFont().deriveFont(Font.BOLD, 18)); // Increased button text size
//        startButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
////                jFrame.setVisible(false);
//                jFrame.dispose();
//                jFrame=null;
//                Main_PlayGame.GAME_STATE++;
//                Main_PlayGame.main(new String[0]);
//            }
//        });
        startButton.addActionListener(e->start.do_Something());

        buttonPanel.setOpaque(false);
        buttonPanel.add(startButton);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        jFrame.getContentPane().add(mainPanel);
        jFrame.setVisible(Visible);
    }

    public Maze(Boolean Visible) {
        mazeMap = new HashMap<>();
        jFrame = new JFrame();
        jFrame.setTitle("Have Fun!!!");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton autoGenerateButton = new JButton("Auto-generate maze");
        autoGenerateButton.addMouseListener(new Auto_generate_map_Button());

        confirmButton.addMouseListener( new Confirm_Button());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(autoGenerateButton);
        buttonPanel.add(confirmButton);

        gridPanel.setLayout(new GridLayout(ROWS, COLS));

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                JPanel squarePanel = new JPanel();
                squarePanel.addMouseMotionListener(new PanelDragListener());
                squarePanel.addMouseListener(new PanelDragListener.PanelMouseListener());
                squarePanel.addMouseListener(new RightClickListener());
                squarePanel.setPreferredSize(new Dimension(SQUARE_SIZE, SQUARE_SIZE));
                squarePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                mazeMap.put(squarePanel, new ClearVertex(squarePanel, i, j));
                gridPanel.add(squarePanel);
            }
        }

        tempPanel.setBackground(Color.BLACK);
        tempPanel.setBounds(-1000, -1000, SQUARE_SIZE, SQUARE_SIZE);
        tempPanel.addMouseMotionListener(new PanelDragListener());
        tempPanel.addMouseListener(new PanelDragListener.PanelMouseListener());
        tempPanel.setVisible(false);
        jFrame.getContentPane().add(tempPanel);

        instructionsPanel = new JPanel();
        instructionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        instructionsPanel.add(new JLabel("<html><span style='font-family: Arial; font-size: 20px; color: #FF0000;'><b>Instructions:</b><br>"
                +"<p>=======================================================</p>"
                + "You can left-click to draw or clean walls on the grid.<br>"
                + "You can drag the walls to another position on the grid.<br>"
                + "You can left-click the clean vertex to input the entry and exit on the grid.<br>"
                + "You can click 'Auto-generate maze' to generate a maze automatically.<br>"
                + "You can click 'Confirm' to save the maze and play the game.<br>"
                +"<p>=======================================================</p>"
                + "<span style='font-family: Arial; font-size: 20px; color: #FF0000;'> Have fun !</html>"));
        goBackButton = new JButton("Go Back"); // Create the "Go Back" button
        goBackButton.addActionListener(go_back);

        JPanel instructionsButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        instructionsButtonPanel.add(goBackButton); // Add the "Go Back"

        instructionsPanel.add(instructionsButtonPanel, BorderLayout.SOUTH); // Add the button panel

        mapPanel = new JPanel();
        mapPanel.setLayout(new BorderLayout());
        mapPanel.add(gridPanel, BorderLayout.CENTER);


        instructionButton = new JButton("Instructions");
        instructionButton.addActionListener(instruction);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(mapPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        cardLayout = new CardLayout();
        cardContainer = new JPanel(cardLayout);
        cardContainer.add(mainPanel, "Map");
        cardContainer.add(instructionsPanel, "Instructions");

        jFrame.getContentPane().setLayout(new BorderLayout());
        jFrame.getContentPane().add(cardContainer, BorderLayout.CENTER);
        jFrame.getContentPane().add(instructionButton, BorderLayout.NORTH);

        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(Visible);
        jFrame.setResizable(true);
    }



    public static void Reset() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                JPanel squarePanel = new JPanel();
                squarePanel.addMouseMotionListener(new PanelDragListener());
                squarePanel.addMouseListener(new PanelDragListener.PanelMouseListener());
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

    public static int[][] Auto_Generate_Map(double ratio) {
        int[][] map = new int[ROWS][COLS];
        while (true) {
            Random rand = new Random();
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    if (rand.nextDouble() < ratio) {
                        map[i][j] = 0;
                    } else {
                        map[i][j] = 1;
                    }
                }
            }
            entry = getRandomBoundaryPoint(map);
            exit = getRandomBoundaryPoint(map);
            List<int[]> path = findShortestPath(map, entry[0], entry[1], exit[0], exit[1]);
            if (Maze.Path_Exist(path))
                break;}
        return map;
    }

    public static int[] getRandomBoundaryPoint(int[][] map) {
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

    public static void Auto_Generate_Maze(double ratio) {
        map = Auto_Generate_Map(ratio);
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
        mazeMap.put(get_panel(entry[0], entry[1]), new Entry(get_panel(entry[0], entry[1]), entry[0], entry[1]));
        mazeMap.put(get_panel(exit[0], exit[1]), new Exit(get_panel(exit[0], exit[1]), exit[0], exit[1]));
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
        }catch ( IOException | RuntimeException e){}
    }

    public static void Show_Path(List<int[]> path) {
        if (!Path_Exist(path)) return;
        for (int[] coordinate : path) {
            JPanel panel = get_panel(coordinate[0], coordinate[1]);
            if (!(mazeMap.get(panel) instanceof Exit || mazeMap.get(panel) instanceof Entry)) {
                panel.setBackground(Color.GREEN);
                panel.repaint();
            }
        }
    }

    public static void OutPut_Path_To_CSV(String filename, List<int[]> path) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("PathType,PathNo,Index,Row_X,Row_Y\n");
            for (int i = 0; i < path.size(); i++) {
                int[] point = path.get(i);
                writer.write("SP,1," + i + "," + point[0] + "," + point[1] + ";\n");
            }
            System.out.println("CSV file exported successfully.");
        } catch (IOException | NullPointerException e) {
            System.out.println("CSV file exported fail.");
        }
    }

    public static boolean Path_Exist(List<int[]> path) {
        if (path == null) return false;
        if (path.size() == 1) return false;
        int last = path.size() - 1;
        int sedlast = path.size() - 2;
        if (city_block_distance(path.get(last)[0], path.get(last)[1], path.get(sedlast)[0], path.get(sedlast)[1]) > 1)
            return false;
        return true;
    }

    public static boolean Path_Exist() {
        Save_Map();
        if(!Entry_Exist() || !Exit_Exist())return false;
        int startRow=Maze.mazeMap.get(Maze.Get_Entry()).x;
        int startCol=Maze.mazeMap.get(Maze.Get_Entry()).y;
        int endRow=Maze.mazeMap.get(Maze.Get_Exit()).x;
        int endCol=Maze.mazeMap.get(Maze.Get_Exit()).y;

        List<int[]> shortestPath = findShortestPath(Maze.map, startRow, startCol, endRow, endCol);
        return shortestPath != null;
    }

    public static boolean Entry_Exist() {
        for (JPanel jPanel : mazeMap.keySet()) {
            if (mazeMap.get(jPanel) instanceof Entry) return true;
        }
        return false;
    }

    public static JPanel Get_Entry() {
        for (JPanel jPanel : mazeMap.keySet()) {
            if (mazeMap.get(jPanel) instanceof Entry) return jPanel;
        }
        return null;
    }

    public static boolean Exit_Exist() {
        for (JPanel jPanel : mazeMap.keySet()) {
            if (mazeMap.get(jPanel) instanceof Exit) return true;
        }
        return false;
    }

    public static JPanel Get_Exit() {
        for (JPanel jPanel : mazeMap.keySet()) {
            if (mazeMap.get(jPanel) instanceof Exit) return jPanel;
        }
        return null;
    }



    public  void   play_game(boolean visible){
        List<int[]> path = new ArrayList<>();
        JFrame newFrame=new JFrame("Moving Object");
        movingObject = new MovingObject(path);
        newFrame.add(movingObject);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setVisible(visible);
        newFrame.setResizable(true);
        newFrame.pack();
        movingObject.startTimer();}}

