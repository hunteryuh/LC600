package com.alg;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HAU on 1/29/2018.
 */
/*A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same element.

Now given an M x N matrix, return True if and only if the matrix is Toeplitz.


Example 1:

Input: matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
Output: True
Explanation:
1234
5123
9512
*/
public class Sol766_ToeplitzMatrix {
    public static boolean isToeplitzMatrix(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        for(int i = 0 ; i < row; i++){
            for(int j = 0; j < col; j++){
                if( i>0 && j>0 && matrix[i-1][j-1] != matrix[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] matrix={
                {3,2},
                {2,3},
                {4,2}
        };
        System.out.println(isToeplitzMatrix(matrix));
    }

    public boolean isToeplitzMatrix2(int[][] matrix) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (!map.containsKey(i - j)) {
                    map.put(i-j, matrix[i][j]);
                } else if (map.get(i-j) != matrix[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // follow up, only has memory to store one row
    public boolean isToeplitzMatrix3(int[][] matrix) {
        if (matrix == null) {
            return false;
        }
        if (matrix.length == 0 || matrix[0].length == 0) {
            return true;
        }
        int[] buffer = new int[matrix[0].length];
        for(int j = 0; j < matrix[0].length; j++) {
            buffer[j] = matrix[0][j];
        }
        for(int i = 1; i < matrix.length; i++) {
            for(int j = matrix[0].length - 1; j >= 1 ; j--) {
                if(buffer[j - 1] != matrix[i][j]) {
                    return false;
                }
                buffer[j] = matrix[i][j];
            }
            buffer[0] = matrix[i][0];
        }

        return true;
    }
    // For the follow-up 2, we can only load a partial row at one time. We could split the whole matrix vertically into several sub-matrices, while each sub-matrix should have one column overlapped.
    // We repeat the same method in follow-up 1 for each sub-matrix.
    // https://leetcode.com/problems/toeplitz-matrix/solutions/271388/java-solution-for-follow-up-1-2/
}
