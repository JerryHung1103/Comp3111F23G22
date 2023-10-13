package Game_Component;//my package

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;
import javax.swing.*;
import Event_Handler.*;
public class Maze extends JFrame {
     static final int ROWS = 30;
     static final int COLS = 30;
    private static final int SQUARE_SIZE = 30;
    static int [][] map=new int[ROWS][COLS];
    public static JPanel gridPanel = new JPanel();
//    public static VertexLocation[][] mazeMap=new VertexLocation[ROWS][COLS];
    public static Map<JPanel, VertexLocation> mazeMap;

    public Maze(){
        mazeMap=new HashMap<JPanel, VertexLocation>() ;

        setTitle("Grid Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton autoGenerateButton = new JButton("Auto-generate maze");
        Auto_generate_map_listener listener = new Auto_generate_map_listener();
        autoGenerateButton.addMouseListener(listener);


        JButton confirmButton  = new JButton("Confirm");
        Confirm_Listener confirmListener=new Confirm_Listener();
        confirmButton.addMouseListener(confirmListener);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(autoGenerateButton);
        buttonPanel.add(confirmButton);

        gridPanel.setLayout(new GridLayout(ROWS, COLS));

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {

                JPanel squarePanel=new JPanel();
                squarePanel.setPreferredSize(new Dimension(SQUARE_SIZE, SQUARE_SIZE));
                squarePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                mazeMap.put(squarePanel,new ClearVertex(squarePanel,i,j));
                gridPanel.add(squarePanel);

            }

        }


        getContentPane().add(gridPanel);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }


    public void User_input(){
        SquareMouseListener listener = new SquareMouseListener();
        for(JPanel jPanel: mazeMap.keySet()){
            jPanel.addMouseListener(listener);
        }

    }

    public static void auto_generate_maze() {

        for (JPanel jPanel : mazeMap.keySet()) {
            int x = mazeMap.get(jPanel).x;
            int y = mazeMap.get(jPanel).y;
            Random random = new Random();
            int randomInt = random.nextInt(2);
            if (randomInt == 1)
                Maze.mazeMap.put(jPanel, new Barrier(jPanel, x, y));
            else
                Maze.mazeMap.put(jPanel, new ClearVertex(jPanel, x, y));
        }

    }

    public static  void save_map()throws IOException {
        FileWriter writer = new FileWriter("maze.csv");
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++){
                for(JPanel jPanel:mazeMap.keySet()){
                    int x = mazeMap.get(jPanel).x;
                    int y = mazeMap.get(jPanel).y;
                    if(x==i && y==j){
                        map[x][y]=(mazeMap.get(jPanel) instanceof ClearVertex)?0:1;
                        writer.append(map[i][j]==1? "1" : "0");
                        if (j < COLS - 1) {
                            writer.append(",");
                        } else  {
                            writer.append("\n");
                        }
                    }
                }



            }
        }

        writer.close();

    }












}
