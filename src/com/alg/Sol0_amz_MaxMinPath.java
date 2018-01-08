package com.alg;

/**
 * Created by HAU on 1/7/2018.
 */
/*给一个int[][]的matirx，对于一条从左上到右下的path pi，pi中的最小值是mi，求所有mi中的最大值。只能往下或右.
比如：

[8, 4, 7]
[6, 5, 9]
有3条path：
8-4-7-9 min: 4
8-4-5-9 min: 4
8-6-5-9 min: 5
return: 5*/
public class Sol0_amz_MaxMinPath {
    public static void main(String[] args) {
        int[][] matrix = {{8,4,7}, {6,5,9}};
        //int[] ans = new int[1];
        int res = findMMP_dp(matrix);
        System.out.println("min max dp: " + res);
        int r2 = findMMP_dfs(matrix);
        System.out.println("min max dfs: " + r2);
    }

    private static int findMMP_dp(int[][] matrix) {
        if(matrix ==  null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0){
            return  -1;
        }
        int m = matrix.length;
        int n = matrix[0].length;

        int[] dp = new int[n];
        // build dp for first row
        dp[0] = matrix[0][0];
        for(int i = 1; i < n; i++){
            dp[i] = Math.min(dp[i-1],matrix[0][i]);
        }
        //build dp for other rows
        for(int i = 1; i < m; i++){
            dp[0] = Math.min(dp[0],matrix[i][0]);
            for(int j = 1; j < n; j++){
                // 如果当前值比两条通路的最小值都大,那么选择其中大者 one from left, one from up
                if(matrix[i][j] > dp[j-1] && matrix[i][j] > dp[j]){
                    dp[j] = Math.max(dp[j-1],dp[j]);
                }else{
                    dp[j] = matrix[i][j]; // min of the path updated
                }
            }
        }
        return dp[n-1];
    }
    private static int min, max, row, col;
    private static int findMMP_dfs(int[][] matrix) {
        row = matrix.length;
        col = matrix[0].length;
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        dfshelper(matrix,min,0,0);
        return max;
    }

    private static void dfshelper(int[][] matrix, int min, int i, int j) {
        if ( i >= row || j >= col) return;
        if( i == row -1 && j == col -1){
            min = Math.min(min,matrix[i][j]);
            max = Math.max(min, max);
            return;
        }
        min = Math.min(min, matrix[i][j]);
        dfshelper(matrix,min,i,j+1);
        dfshelper(matrix,min,i+1,j);
    }

}
