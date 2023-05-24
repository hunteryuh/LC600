package com.alg.dp;
/*
You are given an array prices where prices[i] is the price of a given stock on the ith day.

Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:

After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).



Example 1:

Input: prices = [1,2,3,0,2]
Output: 3
Explanation: transactions = [buy, sell, cooldown, buy, sell]
Example 2:

Input: prices = [1]
Output: 0
 */
public class Sol309_BestTimeToBuyAndSellStocksWithCooldown {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n <= 1) {
            return 0;
        }
        int[][] dp = new int[n][4];
        // 0  sell today
        // 1 cooldown
        // 2  sold at 2 days ago,  past cool down, do nothing, status at sell
        // 3 /buy
        dp[0][0] = 0;
        dp[0][1] = 0;
        dp[0][2] = 0;
        dp[0][3] = -prices[0];
        for (int i = 1; i < n ; i++) {
            dp[i][0] = dp[i-1][3] + prices[i];  //sell on this day
            dp[i][1] = dp[i-1][0]; // cool day
            dp[i][2] = Math.max(dp[i-1][1], dp[i-1][2]); //  keep at sell status, tricky
            dp[i][3]= Math.max(dp[i-1][3], Math.max(dp[i-1][1] -prices[i], dp[i-1][2] - prices[i]));  // buy
        }
        return Math.max(Math.max(dp[n-1][0], dp[n-1][1]), dp[n-1][2]);
    }

    //dp[i][j]，第i天状态为j，所剩的最多现金为dp[i][j]。
    //
    //状态一：买入股票状态（今天买入股票，或者是之前就买入了股票然后没有操作）
    //卖出股票状态，这里就有两种卖出股票状态
    //状态二：两天前就卖出了股票，度过了冷冻期，一直没操作，今天保持卖出股票状态
    //状态三：今天卖出了股票
    //状态四：今天为冷冻期状态，但冷冻期状态不可持续，只有一天！
    //j的状态为：
    //
    //0：状态一
    //1：状态二
    //2：状态三
    //3：状态四
    public int maxProfitDp1d(int[] prices) {
        int[] dp=new int[4];

        dp[0] = -prices[0];
        dp[1] = 0;
        for(int i = 1; i <= prices.length; i++){
            // 使用临时变量来保存dp[0], dp[2]
            // 因为马上dp[0]和dp[2]的数据都会变
            int temp = dp[0];
            int temp1 = dp[2];
            dp[0] = Math.max(dp[0], Math.max(dp[3], dp[1]) - prices[i-1]);
            dp[1] = Math.max(dp[1], dp[3]);
            dp[2] = temp + prices[i-1];
            dp[3] = temp1;
        }
        return Math.max(dp[3],Math.max(dp[1],dp[2]));
    }
}
