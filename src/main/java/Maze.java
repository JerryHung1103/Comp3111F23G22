import java.awt.*;
import javax.swing.*;
public class Maze extends JFrame {
     static final int ROWS = 30;
     static final int COLS = 30;
    private static final int SQUARE_SIZE = 30;

    public static Vertex[][] mazeMap=new Vertex[ROWS][COLS];

    public Maze(){
        setTitle("Grid Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton autoGenerateButton = new JButton("Auto-generate maze");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(autoGenerateButton);

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(ROWS, COLS));

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {

                JPanel squarePanel=new JPanel();
                squarePanel.setPreferredSize(new Dimension(SQUARE_SIZE, SQUARE_SIZE));
                squarePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                Vertex v=new Vertex(squarePanel,i,j);
                mazeMap[i][j]=v;
               v.Add_to_map(gridPanel);

            }

        }


        getContentPane().add(gridPanel);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    void User_input(){
        SquareMouseListener listener = new SquareMouseListener();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                mazeMap[i][j].squarePanel.addMouseListener(listener);
            }
            }

    }





}
