package com.alg.dp;

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

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // 获取网格的长宽
        int n = obstacleGrid.length,m = obstacleGrid[0].length;
        if (n == 0 || m == 0) {
            return 0;
        }
        int[][] dp = new int[n][m];
        // 对于左边界，第一个障碍物或边界前的所有边界点皆可到达
        if (obstacleGrid[0][0] == 0) {
            dp[0][0] = 1;
        }
        for (int i = 0;i < n;i++) {
            for (int j = 0;j < m;j++){
                if (i==0 && j==0) {
                    continue;
                }
                // 若遇到障碍物，则跳过
                if (obstacleGrid[i][j] == 1) {
                    continue;
                }
                // 对于上边界，第一个障碍物或边界左边的所有边界点皆可到达
                if (i == 0) {
                    dp[i][j] = dp[i][j-1];
                    continue;
                }
                // 对于左边界，第一个障碍物或边界前的所有边界点皆可到达
                if (j == 0) {
                    dp[i][j] = dp[i-1][j];
                    continue;
                }
                // 到达当前点的路径数等于能到达此点上面的点和左边点的路径数之和
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[n-1][m-1];
    }

    public static void main(String[] args) {
        int[][] g = {{0,0,0},{0,1,0},{0,0,0}};
        System.out.println(uniquePathsWithObs(g));
        System.out.println(uniquePathWithObj(g));
        System.out.println(uniquePathsWithObstacles(g));
        System.out.println(uqPathwithObj(g));  // this function changes the orginal input, so put in the end

    }

    public int uniquePathsWithObj(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 1) break;
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 1) break;
            dp[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 1) continue;
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

}
