package Test0;
import Game_Component.Maze;
import Main.Main_PlayGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static Main.Main_PlayGame.maze;
import static org.junit.jupiter.api.Assertions.*;
class Main_PlayGameTest {
    String Actual;
    private Main_PlayGame mainPlayGame;

    @Test
    void Test_main() {
        mainPlayGame =new Main_PlayGame();
        mainPlayGame.GAME_STATE=-1;
        mainPlayGame.stage_init =()-> Maze.createUI(false);
        mainPlayGame.main(new String[0]);
        assertNull(mainPlayGame.maze);
        //The maze is not initialized yet


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

    @Test
    public void test_stage1(){

        mainPlayGame =new Main_PlayGame();
        mainPlayGame.GAME_STATE=1;
        mainPlayGame.maze=new Maze(false);
        mainPlayGame. maze.Auto_Generate_Maze(1);
        mainPlayGame. maze.Save_Map();
        mainPlayGame.stage2=()->Actual="Play_Game";
        mainPlayGame.main(new String[0]);
        assertNotNull( Main_PlayGame.shortestPath);
        mainPlayGame.maze=null;
        //stage1 generates the path, so it is expected as not null

    }
}