package Event_Handler;

import Game_Component.Maze;
import Main.Main_PlayGame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class Confirm_Button extends MouseAdapter{
    @Override
    public void mouseClicked(MouseEvent e) {
        if (Maze.Entry_Exist() && Maze.Exit_Exist()) {
            Maze.Save_Map();
            Main_PlayGame.GAME_STATE++;
            Main_PlayGame.main(new String[0]);
        } else {
            System.out.println("你未input出入口啊7頭");
            System.out.println(Main_PlayGame.GAME_STATE);
        }
   }
}