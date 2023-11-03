package Event_Handler;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import Game_Component.*;//my package

public class RightClickListener extends MouseAdapter {
    public static JPopupMenu popupMenu = new JPopupMenu();
    public static void showPopupMenu(MouseEvent e, JPanel jPanel) {


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



    public void mousePressed(MouseEvent e) {

            if (e.isPopupTrigger()) {
                JPanel jPanel=(JPanel) e.getSource();
                showPopupMenu(e,jPanel);
            }


    }

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