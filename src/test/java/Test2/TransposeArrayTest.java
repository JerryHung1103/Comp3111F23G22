package Test2;

import Algorithm.TransposeArray;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransposeArrayTest {
    @Test
    void Test_TransposeArray(){
        int[][] input = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[][] actual = TransposeArray.T(input);
        int[][]expected={
                {1 ,4 ,7},
                {2,5,8},
                {3, 6 ,9}
        };
       for(int i=0;i<3;i++){
           assertArrayEquals(actual[i],expected[i]);
       }
    }
}