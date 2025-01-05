package com.alg.other;

import java.util.Arrays;

/*

Assume you are opening different types of businesses in a community, with a list of locations labeled as l followed by an id,
l0, l1, l2,
where some of them are adjacent if their id difference is 1. You can not open same business in the adjacent rows ( location) and
at each location , you can open at most 1 type of business. Given the following array with the profit of opening at each location for each
business, what is your strategy to open businesses in the community?


    restaurant   book   store
l1   10          5      13
l2   4           10     8
l3   7            5     3

Write a function in java to return the max profit with the optimal strategy.

bytedance, tiktok

 */
public class BT_MaxProfitInCommunity {
    public int getMaxProfit(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        // Create a 2D array to store the maximum profit at each location and for each business

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
        System.out.println(bb.getMaxProfit2(matrix));

    }

    //overall time complexity is O(N * M^2). N: number of locations. M: number of businesses
    // The space complexity is O(N * M)
    public static int getMaxProfit2(int[][] profits) {
        int numRows = profits.length;
        int numCols = profits[0].length;

        // Create a 2D array to store the maximum profit at each location and for each business
        int[][] dp = new int[numRows][numCols];

        // Initialize the first row with the profits of opening each business at the first location
        for (int j = 0; j < numCols; j++) {
            dp[0][j] = profits[0][j];
        }

        // Iterate through the rest of the locations
        for (int i = 1; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                // Find the maximum profit for the current location and business
                int maxProfit = 0;
                for (int k = 0; k < numCols; k++) {
                    if (j != k) {
                        // If the businesses are not adjacent, consider the maximum profit
                        maxProfit = Math.max(maxProfit, dp[i - 1][k]);
                    }
                }
                // Update the dp array with the maximum profit
                dp[i][j] = maxProfit + profits[i][j];
            }
        }

        // Find the maximum profit in the last row
        int maxProfit = 0;
        for (int j = 0; j < numCols; j++) {
            maxProfit = Math.max(maxProfit, dp[numRows - 1][j]);
        }
        System.out.println(Arrays.deepToString(dp));

        return maxProfit;
    }
    /*

If the locations form a circle, meaning that the first and last elements are considered adjacent,
you can modify the dynamic programming approach to account for this circular structure.
The key modification is to consider the cases where you can skip adjacent businesses,
including the case where the last and first elements are adjacent.
     */

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
