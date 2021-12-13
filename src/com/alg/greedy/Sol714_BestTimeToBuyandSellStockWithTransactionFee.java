package com.alg.greedy;
/*
You are given an array prices where prices[i] is the price of a given stock on the ith day, and an integer fee representing a transaction fee.

Find the maximum profit you can achieve. You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).



Example 1:

Input: prices = [1,3,2,8,4,9], fee = 2
Output: 8
Explanation: The maximum profit can be achieved by:
- Buying at prices[0] = 1
- Selling at prices[3] = 8
- Buying at prices[4] = 4
- Selling at prices[5] = 9
The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.

Example 2:

Input: prices = [1,3,7,5,10,3], fee = 3
Output: 6

 */
public class Sol714_BestTimeToBuyandSellStockWithTransactionFee {

    //cash = profit, should always be positive
    //hold = balance, can be negative or positive.

    //My understanding: Hold is the max amount of money you would have when you either buy or do nothing on that day.
    // Cash is the max amount of money you would have when you either sell or do nothing on that day.
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/solution/
    public int maxProfit(int[] prices, int fee) {
        int cash = 0;
        int hold = -prices[0];  //why? i == 0 passed
        // cash is the maximum when I did not have a share yesterday, hold is maximum when I had a share //yesterday
        for (int i = 1; i < prices.length; i++) {
            cash = Math.max(cash, hold + prices[i] - fee); //ith day I do not own a share today (either did not have or sold today)
            hold = Math.max(hold, cash - prices[i]); //ith day I own a share today (either already had or purchased today)
        }
        return cash;
    }

    public int maxProfit2(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];

        dp[0][0] = 0; // cash
        dp[0][1] = - prices[0];

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]);
        }
        return dp[n-1][0];
    }
}
