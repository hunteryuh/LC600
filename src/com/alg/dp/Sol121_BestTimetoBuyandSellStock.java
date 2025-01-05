package com.alg.dp;

/*
You are given an array prices where prices[i] is the price of a given stock on the ith day.

You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.

Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.



Example 1:

Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
Example 2:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transactions are done and the max profit = 0.


Constraints:

1 <= prices.length <= 105
0 <= prices[i] <= 104
 */
public class Sol121_BestTimetoBuyandSellStock {
    public static int maxProfit(int[] prices){
        int max=0;
        int n = prices.length;
        for (int i = 0; i< n - 1; i++)
            for (int j = i + 1; j < n; j++) {
                max = Math.max(prices[j] - prices[i], max);  // double loop  O (n^2)
            }
        return max;
    }
    public static int maxP2(int[] prices){
        if (prices.length==0) return 0;
        int max = 0;
        int n = prices.length;
        int min = prices[0];

        for (int i =1; i<n;i++){   //one loop, O(n)
            if (prices[i] < min)  min = prices[i];  // update min
            else if (prices[i]-min>max)
                max = prices[i]-min;   // update maxProfit

        }
        return max;
    }



    public static void main(String[] args) {
        int[] prices1 = {7,1,5,3,6,5,4};
        int[] prices2={6,4,3,2,1};
        //int sol = 0;
        //System.out.println(maxProfit(prices1));
        //System.out.println(maxProfit(prices2));
        System.out.println(maxP2(prices1));
        System.out.println(maxP2(prices2));
        int[] prices3 = {1,2};

        System.out.println(maxProfit(prices3));
    }

    public int maxProfit2(int[] prices) {
        //  greedy
        int res = 0;
        int min = prices[0];
        for (int i = 0; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            res = Math.max(res, prices[i] - min);
        }
        return res;
    }

    // dp https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0121.%E4%B9%B0%E5%8D%96%E8%82%A1%E7%A5%A8%E7%9A%84%E6%9C%80%E4%BD%B3%E6%97%B6%E6%9C%BA.md
    public int maxProfit3(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int length = prices.length;
        // dp[i][0]代表第i天持有股票的最大收益
        // dp[i][1]代表第i天不持有股票的最大收益
        int[][] dp = new int[length][2];
        int result = 0;
        dp[0][0] = -prices[0]; // 股市刚开场，自己就买入被套牢， 钱在股市不在你手上目前无法套现
        dp[0][1] = 0; // 股市刚开场，自己手里0 为空钱为0
        for (int i = 1; i < length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);// 昨天没股票的状态就是初始状态，一定为0 （最多一次交易）第i天买入股票，所得现金就是买入今天的股票后所得现金即：-prices[i]
            dp[i][1] = Math.max(dp[i - 1][0] + prices[i], dp[i - 1][1]); // 昨天没股票在手，或者昨天有股票今天卖了
        }
        return dp[length - 1][1];
    }
}
