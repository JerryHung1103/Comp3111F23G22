package Main;
import Game_Component.Maze;
import org.junit.jupiter.api.Test;
import static Main.Main_PlayGame.maze;
import static org.junit.jupiter.api.Assertions.*;
class Main_PlayGameTest {
    String Actual;
    private Main_PlayGame mainPlayGame=new Main_PlayGame();
    @Test
    void main() {
        mainPlayGame.GAME_STATE=0;
        mainPlayGame.stage0 =()-> Main_PlayGame.maze=new Maze(false);
        mainPlayGame.main(new String[0]);
        assertNotNull(maze);
        //stage0 initializes the map, so it is expected as not null

        mainPlayGame.GAME_STATE++;
        mainPlayGame. maze.Auto_Generate_Maze(1);
        mainPlayGame. maze.Save_Map();
        mainPlayGame.stage2=()->Actual="Play_Game";
        mainPlayGame.main(new String[0]);
        assertNotNull( Main_PlayGame.shortestPath);
        //stage1 generates the path, so it is expected as not null

        String Expected="Play_Game";
        assertEquals(Expected,Actual);
        //stage2 plays the game, if the functional interface modifies Actual. then means it successfully enter
        //game-playing stage
    }
}