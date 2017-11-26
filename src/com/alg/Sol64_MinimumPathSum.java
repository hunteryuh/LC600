package com.alg;

/**
 * Created by HAU on 11/26/2017.
 */
/*Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.*/
public class Sol64_MinimumPathSum {
    public static int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for(int i = 0; i < m; i++){
            for (int j = 0; j < n ; j++){
                if (i == 0 & j != 0){
                    grid[i][j] += grid[i][j-1];
                }else if(i != 0 && j ==0){
                    grid[i][j] += grid[i-1][j];
                }else if( i!= 0 && j != 0){
                    grid[i][j] +=Math.min(grid[i-1][j], grid[i][j-1]);
                }
            }
        }
        return grid[m-1][n-1];

    }

    public static void main(String[] args) {
        int[][] g = {
                {1,2,1},
                {2,5,1},
                {4,2,1}
        };
        System.out.println(minPathSum(g));
    }
}
