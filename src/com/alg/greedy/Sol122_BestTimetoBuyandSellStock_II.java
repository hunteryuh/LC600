package com.alg.greedy;

/**
 * Created by HAU on 5/24/2017.
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
    public int maxProfit3(int[] prices) {
        // [天数][是否持有股票]
        // dp[i][0]第i天持有的最多现金
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


    public static void main(String[] args) {
        int[] prices1 = {7,1,5,3,6,5,4};
        int[] prices2={6,4,3,2,1};
        //int sol = 0;
        System.out.println(maxProfit(prices1));
        System.out.println(maxProfit(prices2));
        System.out.println(maxP2(prices1));
        //System.out.println(maxP2(prices1));
        //System.out.println(maxP2(prices2));


    }
}
