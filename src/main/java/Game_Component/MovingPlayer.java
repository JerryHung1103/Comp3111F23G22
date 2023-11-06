package Game_Component;

import javax.swing.*;

public class MovingPlayer extends VertexLocation {
    public MovingPlayer(JPanel panel, int row , int col) {
        super(panel, row, col);
    }

    public void movePlayerUp() {
        if (super.x > 0 && !(Maze.mazeMap.get(Maze.get_panel(super.x - 1, super.y)) instanceof Barrier)) {
            Maze.mazeMap.put(Maze.get_panel(super.x, super.y), new ClearVertex(Maze.get_panel(super.x, super.y), super.x, super.y));
            super.x--;
            Maze.mazeMap.put(Maze.get_panel(super.x, super.y), new ClearVertex(Maze.get_panel(super.x, super.y), super.x, super.y));
            Maze.gridPanel.repaint();
        }
    }

    public void movePlayerDown() {
        if (super.x > 0 && !(Maze.mazeMap.get(Maze.get_panel(super.x + 1, super.y)) instanceof Barrier)) {
            Maze.mazeMap.put(Maze.get_panel(super.x, super.y), new ClearVertex(Maze.get_panel(super.x, super.y), super.x, super.y));
            super.x++;
            Maze.mazeMap.put(Maze.get_panel(super.x, super.y), new ClearVertex(Maze.get_panel(super.x, super.y), super.x, super.y));
            Maze.gridPanel.repaint();
        }
    }

    public void movePlayerLeft() {
        if (super.x > 0 && !(Maze.mazeMap.get(Maze.get_panel(super.x, super.y - 1)) instanceof Barrier)) {
            Maze.mazeMap.put(Maze.get_panel(super.x, super.y), new ClearVertex(Maze.get_panel(super.x, super.y), super.x, super.y));
            super.y--;
            Maze.mazeMap.put(Maze.get_panel(super.x, super.y), new ClearVertex(Maze.get_panel(super.x, super.y), super.x, super.y));
            Maze.gridPanel.repaint();
        }
    }
    public void movePlayerRight() {
        if (super.x > 0 && !(Maze.mazeMap.get(Maze.get_panel(super.x, super.y + 1)) instanceof Barrier)) {
            Maze.mazeMap.put(Maze.get_panel(super.x, super.y), new ClearVertex(Maze.get_panel(super.x, super.y), super.x, super.y));
            super.y++;
            Maze.mazeMap.put(Maze.get_panel(super.x, super.y), new ClearVertex(Maze.get_panel(super.x, super.y), super.x, super.y));
            Maze.gridPanel.repaint();
        }
    }
}
