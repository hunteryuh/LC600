package com.alg.dp;

/**
 * Created by HAU on 9/20/2017.
 */
/*A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?*/
public class Sol62_UniquePaths {
    public static int uniquePaths(int m, int n){
        //time O(mn), space(mn)
        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n ;j ++) {
                if (i == 0 || j == 0) {
                    matrix[i][j] = 1;  // corner case
                } else {
                    //              up              left
                    matrix[i][j] = matrix[i-1][j] + matrix[i][j-1];
                }
            }
        }
        return matrix[m-1][n-1];
    }

    public static void main(String[] args) {
        System.out.println(uniquePaths(4,3));
        // combination C-2_5  (need 5 steps in total, which are 2 horizontal steps + 3 vertical steps
        System.out.println(uniquePaths2(4,3));
    }

    // improvement improve o(mn), space(n)
    public static int uniquePaths2(int m, int n){
        int[] dp = new int[n];
        //initial
        for (int i = 0; i< n ; i++){
            dp[i] = 1;
        }
        for (int i = 1; i < m; i++){
            for (int j = 1; j < n ; j++){
                dp[j] += dp[j-1];
            }
        }
        return dp[n-1];
    }
    public int uniquePathsCom(int m, int n) {
        // combination, m+n-2 choose m-1 or n-1
        int total = m + n - 2;
        int k = Math.min(m-1, n-1);
        long res = 1;
        for (int i = 1; i <=k; i++) {
            res = res * total/i;
            total--;
        }
        return (int) res;
    }
}
