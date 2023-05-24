package com.alg;

/**
 * Created by HAU on 1/8/2018.
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
public class Sol0_amz_searchTarget2DMatrix {
    // Start searching from Upper Right Corner
// if equal, we found it!
// if less than target, go to the row below
// if larger than target, go to the column on left
    public static boolean searchMatrix(int[][] matrix, int target) {
        // check corner case
        if (matrix == null || matrix.length == 0 || matrix[0] == null ||
                matrix[0].length == 0)
            return false;
        int m = matrix.length;
        int n = matrix[0].length;
        int i = 0;
        int j = n - 1;
        while (i < m && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] < target) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }
    /*Solution:
If we found target at matrix[row][col], then we know:
matrix[row][col - 1] must be less than target.
matrix[row + 1][col] must be larger than target;
Thus, we go directly to matrix[row + 1][col - 1].
Which is equal to count++, row++, col--;*/

}
