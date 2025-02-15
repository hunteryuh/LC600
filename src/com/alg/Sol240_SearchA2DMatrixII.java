package com.alg;

/**
 * Created by HAU on 1/20/2018.
 */
/*Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
For example,

Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
Given target = 5, return true.

Given target = 20, return false.*/
public class Sol240_SearchA2DMatrixII {
    public static boolean searchMatrix(int[][] matrix, int target) {
        // start from bottom left, this would work equally well with a pointer initialized from top right
        // time O(n+m)
        // space O(1)
        int row = matrix.length - 1;
        int col = 0;
        while( row >= 0 && col < matrix[0].length){
            if( matrix[row][col] > target){
                row--;
            }else if( matrix[row][col] < target){
                col++;
            }else{
                return true;
            }
        }
        return false;
    }
}
