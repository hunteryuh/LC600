package com.alg;

/**
 * Created by HAU on 9/20/2017.
 */
/*A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?*/
public class Sol62_UniquePaths {
    public static int uniquePaths(int m, int n){
        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n ;j ++){
                if(i == 0 || j ==0){
                    matrix[i][j] = 1;
                }else{
                    matrix[i][j] = matrix[i-1][j] + matrix[i][j-1];
                }
            }
        }
        return matrix[m-1][n-1];
    }

    public static void main(String[] args) {
        System.out.println(uniquePaths(4,3)); // combination C-2_5
        //System.out.println(up2(4,3));
    }

}
