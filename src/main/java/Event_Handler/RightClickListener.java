package Event_Handler;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import Game_Component.*;//my package

/**
 * This class is for allowing the users to input the entry and exit point of the game by right-clicking the vertex
 * @author Jerry Hung
 */
public class RightClickListener extends MouseAdapter {
    /**
     * The popup Menu that shows the option of entry or exit point that user wants to input.
     */
    public static JPopupMenu popupMenu ;

    /**
     * This method is for taking the action that the user click the option of the popup Menu
     * @param e It indicates the mouse event
     * @param jPanel It indicates the panel of the particular vertex that takes the action of user.
     */
    public static void showPopupMenu(MouseEvent e, JPanel jPanel) {

        popupMenu = new JPopupMenu();
        JMenuItem entryItem = new JMenuItem("Entry");
        entryItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Selected: Entry");
                if(Maze.Entry_Exist()){
                    JPanel old_entry= Maze.Get_Entry();
                    Maze.mazeMap.put(old_entry, new ClearVertex(old_entry, Maze.mazeMap.get(old_entry).x, Maze.mazeMap.get(old_entry).y));
                }
              Maze.mazeMap.put(jPanel,new Entry(jPanel,Maze.mazeMap.get(jPanel).x, Maze.mazeMap.get(jPanel).y));
            }
        });
        popupMenu.add(entryItem);

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Selected: Exit");
                if(Maze.Exit_Exist()){
                    JPanel old_exit= Maze.Get_Exit();
                    Maze.mazeMap.put(old_exit, new ClearVertex(old_exit, Maze.mazeMap.get(old_exit).x, Maze.mazeMap.get(old_exit).y));
                }
                Maze.mazeMap.put(jPanel,new Exit(jPanel,Maze.mazeMap.get(jPanel).x, Maze.mazeMap.get(jPanel).y));

            }
        });
        popupMenu.add(exitItem);

        popupMenu.show(e.getComponent(), e.getX(), e.getY());
    }

    /**
     * This method is for taking the action that the user press the target vertex
     * @param e It indicates the mouse event
     */
    public void mousePressed(MouseEvent e) {

            if (e.isPopupTrigger()) {
                JPanel jPanel=(JPanel) e.getSource();
                showPopupMenu(e,jPanel);
            }


    }
    /**
     * This method is for taking the action that the user releases the mouse
     * @param e It indicates the mouse event
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        JPanel jPanel=(JPanel) e.getSource();
        if (Maze.mazeMap.get(jPanel) instanceof ClearVertex) {
            if (e.isPopupTrigger()) {
                showPopupMenu(e,jPanel);
            }
        }
    }
}