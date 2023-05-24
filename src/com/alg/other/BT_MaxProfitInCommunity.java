package com.alg.other;

import java.util.Arrays;

/*

no same business in the adjacent rows ( location)
at each location , you can open at most 1 type of business

    res   book  store
l1   10    5     13
l2   4     10    8
l3   7     5     3



 */
public class BT_MaxProfitInCommunity {
    public int getMaxProfit(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] dp = new int[m][n];

        // dp[0][0] = max of matrix[0][1]... matrix[0][n-1]
        // dp[i][j], the max profit if we open j business at i location
        //dp[i][j] = matrix[i][j] + max of dp[i-1][j= !j]

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    dp[i][j] = matrix[i][j];// max of row matrix[i] without jth element
                } else {
                    int temp = 0;
                    for (int k = 0; k < n; k++) {
                        if (k != j) {
                            temp = Math.max(temp, dp[i - 1][k]);
                        }
                    }
                    dp[i][j] = matrix[i][j] + temp;
                }
            }
        }
        System.out.println(Arrays.deepToString(dp));
        int res = dp[m-1][0];
        for (int j = 0; j < n; j++) {
            res = Math.max(dp[m-1][j], res);
        }
        return res;

    }
    private int getMaxWithoutSelf(int[] row, int k) {
        int size = row.length;
        int max = 0;
        for (int i = 0; i < size; i++) {
            if (i != k) {
                max = Math.max(max, row[i]);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {10,5,13},
                {4,10, 8},
                {7, 5 ,3}
        };

        BT_MaxProfitInCommunity bb = new BT_MaxProfitInCommunity();
        System.out.println(bb.getMaxProfit(matrix));

    }

//    public int getMaxRevenue(int[][] matrix) {
//        int m = matrix.length;
//        int n = matrix[0].length;
//        int[] dp = new int[n];
//        for (int j = 0; j < n; j++) {
//            for (int i = 0; i < m; i++) {
//                if (i == 0) {
//                    dp[j] = getMaxWithoutSelf(matrix[i], j);
//                } else {
//                    int temp = 0;
//                    for (int k = 0; k < n && k!= j; k++ ) {
//                        temp = Math.max(temp, dp[k]);
//                    }
//                    dp[j] = matrix[i][j] + temp;
//                }
//            }
//        }
//
//    }
}
