package com.alg;

/**
 * Created by HAU on 1/8/2018.
 */
/*Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
For example,

Consider the following matrix:

[
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
Given target = 3, return true.*/
public class Sol74_SearchA2DMatrix {
    public static boolean searchMatrix(int[][] matrix, int target) {
        if( matrix == null || matrix.length ==0 )
            return false;
        int i = 0;
        int j = matrix[0].length - 1;
        while ( i < matrix.length && j >=0){
            if(matrix[i][j] == target)
                return true;
            else if( matrix[i][j] > target){
                j--;
            }else{
                i++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1,   3,  5,  7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        };
        System.out.println(searchMatrix(matrix,3));
    }
}
