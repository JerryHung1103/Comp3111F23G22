package Algorithm;

/**
 * This class is for finding the transpose of a 2D array
 * @author Tom
 */

public class TransposeArray {

    /**
     * This method is for finding the transpose of the given 2D array
     * @param originalArray It indicates the input array
     * @return The transpose of the input array
     */
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

