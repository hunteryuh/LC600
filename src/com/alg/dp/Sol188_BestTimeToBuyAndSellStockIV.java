package com.alg.dp;
/*
You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.

Find the maximum profit you can achieve. You may complete at most k transactions.

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).



Example 1:

Input: k = 2, prices = [2,4,1]
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
Example 2:

Input: k = 2, prices = [3,2,6,5,0,3]
Output: 7
Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 */
public class Sol188_BestTimeToBuyAndSellStockIV {
    public int maxProfit(int k, int[] prices) {
        int length = prices.length;
        // k buy and k sell
        // 2k + 1
        if (length == 0) return 0;
        // [天数][股票状态]
        // 股票状态: 奇数表示第 k 次交易持有/买入, 偶数表示第 k 次交易不持有/卖出, 0 表示没有操作
        int[][] dp = new int[length][2*k +1];

        dp[0][0] = 0;

        for(int j = 1; j<= 2* k ; j+=2) {
            dp[0][j] = -prices[0];
        }
        // 0 without stock
        // 1 with stock
        for (int i = 1; i < length; i ++) {
            for (int j = 0; j <= 2*k; j++) {
                dp[i][j+1] = Math.max(dp[i-1][j+1], dp[i-1][j] - prices[i]);    // buy
                dp[i][j+2] = Math.max(dp[i-1][j+2], dp[i-1][j+1] + prices[i]); // sell
            }
        }
        return dp[length-1][2* k];
    }

    //3d dp
    // https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0188.%E4%B9%B0%E5%8D%96%E8%82%A1%E7%A5%A8%E7%9A%84%E6%9C%80%E4%BD%B3%E6%97%B6%E6%9C%BAIV.md
    public int maxProfit3d(int k, int[] prices) {
        if (prices.length == 0) return 0;

        // dp[i][j][k]，第i天，第j次买卖，k表示买还是卖的状态，
        // dp[i][used_k][ishold] = balance
        // ishold: 0 nothold, 1 hold
        // [天数][交易次数][是否持有股票]
        int len = prices.length;
        int[][][] dp = new int[len][k + 1][2];

        // dp数组初始化
        // 初始化所有的交易次数是为确保 最后结果是最多 k 次买卖的最大利润
        for (int i = 0; i <= k; i++) {
            dp[0][i][1] = -prices[0];
        }

        for (int i = 1; i < len; i++) {
            for (int j = 1; j <= k; j++) {
                // dp方程, 0表示不持有/卖出, 1表示持有/买入
                // we can either remain in sell state by doing nothing on day i (remain idle)
                // but for that we'll need to be in sell state on day i-1, with same no. of transactions

                // or we can transition into sell state by making a sell
                // if we make a sell on ith day and finish jth transaction, then we must
                // get profit from purchase of jth transaction on (i-1)th day
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                // we can either remain in buy state by doing nothing on day i (remain idle)
                // but for that we'll need to be in buy state on day i-1, with same no. of transactions

                // or we can transition into buy state by making a purchase
                // if we make a purchase on ith day and initiate jth transaction, then we must
                // get profit from completion of (j-1)th transaction on (i-1)th day
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }
        return dp[len - 1][k][0];
    }
}

