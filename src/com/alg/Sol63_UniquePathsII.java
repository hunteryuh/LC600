package com.alg;

/**
 * Created by HAU on 9/20/2017.
 */
/*Follow up for "Unique Paths":

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

For example,*/
public class Sol63_UniquePathsII {
    public static int uniquePathsWithObs(int[][] obstacleGrid){
        int width = obstacleGrid[0].length;
        int[] dp = new int[width];
        dp[0] = 1;
        for (int[] row : obstacleGrid) {
            for (int j = 0; j < width; j++) {
                if (row[j] == 1) dp[j] = 0;
                else if (j >0) {
                    dp[j] += dp[j-1];  // row by row adding
                }
            }
        }
        return dp[width-1];
    }

    public static int uqPathwithObj(int[][] obsgrid){
        // 2d table for dp
        // no extra space
        int m = obsgrid.length;
        int n = obsgrid[0].length;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++) {
                if (obsgrid[i][j] == 1) {
                    obsgrid[i][j] = 0;
                } else if ( i ==0 && j == 0) {
                    obsgrid[i][j] = 1;
                } else if (i == 0) {
                    obsgrid[i][j] = obsgrid[i][j-1]; // first row, row 0
                } else if (j == 0) {
                    obsgrid[i][j] = obsgrid[i-1][j];
                } else {
                    obsgrid[i][j] = obsgrid[i-1][j]+ obsgrid[i][j-1];
                }
            }
        }
        return obsgrid[m-1][n-1];
    }

    public static int uniquePathWithObj(int[][] obsgrid) {
        // 2d table for dp
        int m = obsgrid.length;
        int n = obsgrid[0].length;
        int[][] f = new int[m][n];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++) {
                if (obsgrid[i][j] == 1) {
                    f[i][j] = 0;
                } else {
                    if (i == 0 && j == 0) {
                        f[i][j] = 1;
                    } else {
                        f[i][j] = 0;
                        if (i >= 1) {
                            f[i][j] += f[i-1][j];
                        }
                        if (j >= 1) {
                            f[i][j] += f[i][j-1];   // so if i > 1 and j > 1 then, f[i][j] = f[i-1][j] + f[i][j-1]
                        }
                    }
                }
            }
        }
        return f[m-1][n-1];
    }

    public static void main(String[] args) {
        int[][] g = {{0,0,0},{0,1,0},{0,0,0}};
        System.out.println(uniquePathsWithObs(g));
        System.out.println(uniquePathWithObj(g));
        System.out.println(uqPathwithObj(g));

    }


}
