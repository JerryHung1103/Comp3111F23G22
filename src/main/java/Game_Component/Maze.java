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
/**
 * This class is for manipulating maze of the game
 * @author Jerry Hung
 */
public class Maze extends JFrame {
    /**
     * The grid Panel
     */
    public static JPanel gridPanel = new JPanel();
    /**
     * The total number of rows
     */
    public static int ROWS = 30;
    /**
     * The total number of columns
     */
    public static int COLS = 30;
    /**
     * The vertex size
     */
    public static final int SQUARE_SIZE = 30;

    /**
     * The 30*30 map, which contains only 0s and 1s
     */
    public static int[][] map = new int[ROWS][COLS];


    /**
     * The confirm Button
     */
    public static JButton confirmButton = new JButton("Confirm");

    /**
     * The temporary panel which only appear when user drag the barriers
     */
    public static JPanel tempPanel = new JPanel();
    /**
     * The entry point
     */
    public static int[] entry = new int[2];
    /**
     * The exit point
     */
    public static int[] exit = new int[2];
    /**
     * The main frame
     */
    public static JFrame jFrame = new JFrame();

    /**
     * The hash map, that takes panel as keys, vertex as values
     */

    public static Map<JPanel, VertexLocation> mazeMap;
    /**
     * The moving object
     */
    public static MovingObject movingObject=null;

    /**
     * The panel that shows the instruction
     */
    public JPanel instructionsPanel;

    /**
     * The panel that shows the map
     */
    public JPanel mapPanel;

    /**
     * The instruction button
     */
    public JButton instructionButton;

    /**
     * The layout manager for a container, which allows user switches between instruction page and main game page
     */
    public CardLayout cardLayout;

    /**
     * The instruction panel
     */
    public static JPanel cardContainer;

    /**
     * The go back button
     */
    public JButton goBackButton; // Add the "Go Back" button

    /**
     * The action of user click the go back button
     */
    public ActionListener go_back=e->cardLayout.show(cardContainer, "Map");

    /**
     * The action of user click the instruction button
     */
    public ActionListener instruction=e->cardLayout.show(cardContainer, "Instructions");

    /**
     * The action of user start the game
     */
    public static Do_Something start=()-> {jFrame.dispose();jFrame=null;Main_PlayGame.GAME_STATE++;Main_PlayGame.main(new String[0]);};

    /**
     * The directory of the background image
     */
    public static String bg_image="src/main/java/Game_Component/background.jpg";

    /**
     * This method is for creating the user interface at the beginning of the game
     * @param Visible It indicates the visibility of the interface
     */
    public static void createUI(Boolean Visible) {
        jFrame.setTitle("Group 22 - Game Project");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //  jFrame.setSize(900, 900);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
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

        startButton.addActionListener(e->start.do_Something());

        buttonPanel.setOpaque(false);
        buttonPanel.add(startButton);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        jFrame.getContentPane().add(mainPanel);
        jFrame.setVisible(Visible);

    }

    /**
     * This method is for constructing the maze at the beginning of the game
     * @param Visible It indicates the visibility of the maze
     */
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
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        gridPanel.setPreferredSize(screenSize);

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
        tempPanel.setBounds(-1000, -1000, SQUARE_SIZE+15, SQUARE_SIZE);
        tempPanel.addMouseMotionListener(new PanelDragListener());
        tempPanel.addMouseListener(new PanelDragListener.PanelMouseListener());
        tempPanel.setVisible(false);
        //for honor student code
        //Since I choose the requirement I) of function A :
        //Write a program of Maze-Editor that allows user to build a maze manually.
        //This tempPanel is for user drags a barrier and rearrange to another position
        //It is visible only when user drag his/her mouse, it "acts" like the panel that user is dragging
        //This is one of my own ways to enhance the program higher than standard.
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
        jFrame.setVisible(Visible);

    }


    /**
     * This method is for resting the maze to the original stage
     */
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

    /**
     * This method is for getting the corresponding panel given row and column information.
     * @param row It indicates the row number of the maze
     * @param col It indicates the column number of the maze
     * @return The reference of corresponding panel
     */
    public static JPanel get_panel(int row, int col) {
        for (JPanel jPanel : mazeMap.keySet()) {
            if (mazeMap.get(jPanel).x == row && mazeMap.get(jPanel).y == col) {
                return jPanel;
            }
        }
        return null;
    }

    /**
     * This method is for randomly generating a 30*30 2D integer array
     * @param ratio It indicates the ratio of number 1s and 0s of the array
     * @return The reference of randomly generated  30*30 2D integer array
     */
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
            boolean anotherpathexist=anotherpath_exist(map,path,entry,exit);
            if (anotherpathexist)
                break;}
        return map;
    }
    //for honor student code
    //Since I choose the requirement I) of function A :
    // Write a program of Maze-Editor that allows user to build a maze manually.
    //This Auto_Generate_Map is for user to auto generate a maze
    //Sometime users may want to play the game only, so he/she may not want to spend time to design the maze
    //This is one of my own ways to enhance the program higher than standard.

    /**
     * This method is for checking if there exist another possible path of the given map.
     * @param map It indicates map that we want to check.
     * @param path It indicates path that it already exist on the map.
     * @param entry It indicates the entry point of the path.
     * @param exit It indicates the exit point of the path.
     * @return True if there exist another path, otherwise false.
     */
    public static boolean  anotherpath_exist(int[][] map, List<int[]> path, int[] entry, int[] exit){
        if(path==null)return false;

        for(int i=1;i<path.size()-1;i++){
           map[path.get(i)[0]] [path.get(i)[1]]=1;
            List<int[]> p = findShortestPath(map, entry[0], entry[1], exit[0], exit[1]);
            map[path.get(i)[0]] [path.get(i)[1]]=0;
            if(p!=null) return true;

        }

        return false;
    }
    /**
     * This method is for randomly generating a pair of entry and exist at the boundary of the given map.
     * @param map It indicates map that we want to randomly generate a pair of entry and exist at the boundary.
     * @return a pair of non-overlapped entry and exit point at the boundary of the given map.
     */
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
    //for honor student code
    //Since I choose the requirement I) of function A :
    // Write a program of Maze-Editor that allows user to build a maze manually.
    //This getRandomBoundaryPoint is for auto generating a pair of Random points of the maze
    //It is used in Auto_Generate_Map
    //This is one of my own ways to enhance the program higher than standard.

    /**
     * This method is for randomly generating a 30*30 maze .
     * @param ratio It indicates the ratio of number barriers and clean vertex of the maze.
     */
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
    //for honor student code
    //Since I choose the requirement I) of function A :
    //Write a program of Maze-Editor that allows user to build a maze manually.
    //This Auto_Generate_Maze is make use of Auto_Generate_Map, and visualize it to the user
    //This is one of my own ways to enhance the program higher than standard.

    /**
     * This method is for mapping the maze to an integer 2D array with only 0s and 1s, then output it to a csv file.
     */
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

    /**
     * This method is for showing the given path on the screen.
     * @param path It indicates the path that we want to show.
     */
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

    /**
     * This method is for outputting the path to a csv file.
     * @param filename It indicates the file name that we want to write
     * @param path It indicates the path that we want to output
     */
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

    /**
     * This method is for checking if the give path is valid.
     * @param path It indicates the path that we want to check
     * @return True if the given path is valid, otherwise false
     */
    public static boolean Path_Exist(List<int[]> path) {
        if (path == null) return false;
        if (path.size() == 1) return false;
        int last = path.size() - 1;
        int sedlast = path.size() - 2;
        if (city_block_distance(path.get(last)[0], path.get(last)[1], path.get(sedlast)[0], path.get(sedlast)[1]) > 1)
            return false;
        return true;
    }

    /**
     * This method is for checking if there exist at least one possible path on the given map.
     * @return True if there exist at least one possible path on the given map, otherwise false
     */
    public static boolean Path_Exist() {
        Save_Map();
        if(!Entry_Exist() || !Exit_Exist())return false;
        int startRow=Maze.mazeMap.get(Maze.Get_Entry()).x;
        int startCol=Maze.mazeMap.get(Maze.Get_Entry()).y;
        int endRow=Maze.mazeMap.get(Maze.Get_Exit()).x;
        int endCol=Maze.mazeMap.get(Maze.Get_Exit()).y;

        List<int[]> shortestPath = findShortestPath(Maze.map, startRow, startCol, endRow, endCol);
      return anotherpath_exist(map,shortestPath, new int[]{startRow,startCol},new int[]{endRow,endCol});

    }
    /**
     * This method is for checking if the entry point is already been specified on the  map.
     * @return True if there exist entry point, otherwise false
     */
    public static boolean Entry_Exist() {
        for (JPanel jPanel : mazeMap.keySet()) {
            if (mazeMap.get(jPanel) instanceof Entry) return true;
        }
        return false;
    }

    /**
     * This method is for getting the entry point of the  map.
     * @return The reference of the entry if there exist entry point, otherwise null pointer
     */
    public static JPanel Get_Entry() {
        for (JPanel jPanel : mazeMap.keySet()) {
            if (mazeMap.get(jPanel) instanceof Entry) return jPanel;
        }
        return null;
    }

    /**
     * This method is for checking if the exit point is already been specified on the  map.
     * @return True if there exist exit point, otherwise false
     */
    public static boolean Exit_Exist() {
        for (JPanel jPanel : mazeMap.keySet()) {
            if (mazeMap.get(jPanel) instanceof Exit) return true;
        }
        return false;
    }

    /**
     * This method is for getting the exit point of the  map.
     * @return The reference of the exit if there exist exit point, otherwise null pointer
     */
    public static JPanel Get_Exit() {
        for (JPanel jPanel : mazeMap.keySet()) {
            if (mazeMap.get(jPanel) instanceof Exit) return jPanel;
        }
        return null;
    }


    /**
     * This method is for showing the user interface and constructing the moving object when the game is valid and ready to be played.
     * @param visible It indicates the visibility of the frame
     */
    public  void   play_game(boolean visible){
        List<int[]> path = new ArrayList<>();
        JFrame newFrame=new JFrame("Moving Object");
        movingObject = new MovingObject(path);
        newFrame.add(movingObject);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setVisible(visible);
        newFrame.setResizable(true);
        newFrame.pack();

        // Calculate the dimensions for half of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width / 2;
        int height = screenSize.height;
        int x = screenSize.width - width;
        // Set the frame bounds to occupy half of the screen on the left side
        newFrame.setBounds(0, 0, width, height);
        movingObject.startTimer();
        this.jFrame.setBounds(x, 0, width, height);
    }
}

