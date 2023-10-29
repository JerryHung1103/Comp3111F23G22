package Algorithm;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;

class PathFinderTest {
    @Test
    public void testCityBlockDistance1() {
        int expected = 2;
        int actual = PathFinder.city_block_distance(0, 0, 1, 1);
        assertEquals(expected, actual);
    }
    @Test
    public void testCityBlockDistance2() {
        int expected = 4;
        int actual = PathFinder.city_block_distance(-1, -1, 1, 1);
        assertEquals(expected, actual);
    }

    @Test
    public void testShortestPathAlgo() {
        int[][] map = new int[][] {{0,0,0,0,1,0,0}, {1,1,1,0,1,0,0}, {0,0,1,0,1,0,0}, {1,1,1,0,1,1,1}, {0,0,0,0,0,0,1}, {1,1,1,1,1,1,1}};
        int[][] expected = new int[][] {{0, 0}, {0, 1}, {0, 2}, {0, 3}, {1, 3}, {2, 3}, {3, 3}, {4, 3}, {4, 4}, {4, 5} };
        List<int[]> actual = PathFinder.findShortestPath(map, 0, 0, 4, 5);
        for (int i = 0; i < expected.length || i < actual.size(); i++) {
            assertArrayEquals(expected[i], actual.get(i));
        }
    }

}