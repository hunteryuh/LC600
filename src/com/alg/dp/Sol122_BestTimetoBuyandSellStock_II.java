package com.alg.dp;

import java.util.Arrays;

/**
 You are given an integer array prices where prices[i] is the price of a given stock on the ith day.

 On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time. However, you can buy it then immediately sell it on the same day.

 Find and return the maximum profit you can achieve.



 Example 1:

 Input: prices = [7,1,5,3,6,4]
 Output: 7
 Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
 Total profit is 4 + 3 = 7.
 Example 2:

 Input: prices = [1,2,3,4,5]
 Output: 4
 Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 Total profit is 4.
 Example 3:

 Input: prices = [7,6,4,3,1]
 Output: 0
 Explanation: There is no way to make a positive profit, so we never buy the stock to achieve the maximum profit of 0.
 */
//Design an algorithm to find the maximum profit. You may complete
//as many transactions as you like
//(ie, buy one and sell one share of the stock multiple times).

public class Sol122_BestTimetoBuyandSellStock_II {
    //(Simple One Pass) O(n) time O(1) space
    public static int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        int profit = 0;
        int n = prices.length;
        for (int i = 1; i < n; i++) {
            if (prices[i] - prices[i - 1] > 0) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }

    public int maxProfit2(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            res += Math.max(prices[i] - prices[i - 1], 0);
        }
        return res;
    }

    //(Peak Valley Approach)
    public static int maxP2(int[] prices) {
        if (prices.length == 0) return 0;
        int valley = prices[0], peak = prices[0];
        int res = 0, i = 0;
        int n = prices.length;
        while (i < n - 1) {
            while (i < n - 1 && prices[i] >= prices[i + 1]) {
                i++;
            }
            valley = prices[i];
            while (i < n - 1 && prices[i] <= prices[i + 1]) {
                i++;
            }
            peak = prices[i];
            res += peak - valley;
        }
        return res;
    }

    // dp https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0122.%E4%B9%B0%E5%8D%96%E8%82%A1%E7%A5%A8%E7%9A%84%E6%9C%80%E4%BD%B3%E6%97%B6%E6%9C%BAII.md
    // https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0122.%E4%B9%B0%E5%8D%96%E8%82%A1%E7%A5%A8%E7%9A%84%E6%9C%80%E4%BD%B3%E6%97%B6%E6%9C%BAII%EF%BC%88%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92%EF%BC%89.md
    public int maxProfit3(int[] prices) {
        // [天数][是否持有股票]
        // dp[i][0]第i天(不持有股票）持有的最多现金
        // dp[i][1]第i天持有股票后的最多现金
        int[][] dp = new int[prices.length][2];

        // bad case
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            // dp公式
            // 第i天持有最多现金 = max(第i-1天持有的最多现金，第i-1天持有股票的最多现金+第i天卖出股票)
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // 第i天持股票所剩最多现金 = max(第i-1天持股票所剩现金, 第i-1天持现金-买第i天的股票)
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        return dp[prices.length - 1][0];
    }

    // refactor for above dp approach
    public int maxProfit4(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];

        //[][0] balance without holding a stock
        //[][1] balance with holding a stock
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] =  Math.max(dp[i-1][1], dp[i-1][0] - prices[i]);
        }
        System.out.println(Arrays.deepToString(dp));
        return dp[n-1][0];
    }

    // dp with space optimized
    public int maxProfit41(int[] prices) {
        int[] dp = new int[2];
        // 0表示持有，1表示卖出
        dp[0] = -prices[0];
        dp[1] = 0;
        for (int i = 1; i < prices.length; i++){
            // 前一天持有; 既然不限制交易次数，那么再次买股票时，要加上之前的收益
            dp[0] = Math.max(dp[0], dp[1] - prices[i]);
            // 前一天卖出; 或当天卖出，当天卖出，得先持有
            dp[1] = Math.max(dp[1], dp[0] + prices[i]);
        }
        return dp[1];
    }


    public static void main(String[] args) {
        int[] prices1 = {7,1,5,3,6,5,4};
        int[] prices2={6,4,3,2,1};
        //int sol = 0;
        System.out.println(maxProfit(prices1));
        System.out.println(maxProfit(prices2));
        System.out.println(maxP2(prices1));
        //System.out.println(maxP2(prices1));
        //System.out.println(maxP2(prices2));
        Sol122_BestTimetoBuyandSellStock_II ss = new Sol122_BestTimetoBuyandSellStock_II();
        int x = ss.maxProfit4(prices1);
    }
}
