package com.alg;

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
}
