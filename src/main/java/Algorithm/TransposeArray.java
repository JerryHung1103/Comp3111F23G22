package Algorithm;

public class TransposeArray {
    public static int[][] T(int[][] originalArray) {
        int rows = originalArray.length;
        int cols = originalArray[0].length;
        int[][] transposedArray = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposedArray[j][i] = originalArray[i][j];
            }
        }
        return transposedArray;
    }
}

