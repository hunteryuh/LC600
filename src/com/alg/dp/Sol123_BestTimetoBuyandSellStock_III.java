package com.alg.dp;

/**
 * Created by HAU on 5/24/2017.
 */
/*Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

*/
public class Sol123_BestTimetoBuyandSellStock_III {
    public static int maxProfit2times(int[] prices){
        if (prices.length ==0) return 0;
        int n = prices.length;
        int profit1 = 0;
        int profit2 ;
        int min1= prices[0];
        int min2;
        int sum = 0;
        for(int i=1;i<n;i++){
            if(prices[i]<min1){
                min1 = prices[i];
            }else if(prices[i]-min1>profit1)
                profit1 = prices[i]-min1;
            min2 = prices[i];
            profit2 =0;
            for (int j=i+1;j<n;j++){
                if(prices[j]< min2) min2=prices[j];
                else if (prices[j]-min2> profit2)
                    profit2= prices[j]-min2;
            }
            if (sum < profit1 + profit2)
                sum = profit1+ profit2;

        }
        return sum;  // O(n^2)  time limit exceeded...


    }
    public static int maxProfit2times_2(int[] prices) {
        if (prices.length ==0) return 0;
        int n = prices.length;

        int profit1 = 0;
        int profit2 = 0;
        int minP = prices[0];
        int maxP = prices[n-1];

        int[] left = new int[n];
        int[] right = new int[n];
        for (int i=1;i<n;i++){
            if (prices[i]<minP) minP = prices[i];
            else if (prices[i]-minP>profit1)
                profit1 = prices[i]-minP;
            left[i] = profit1;
        }
        for (int i=n-1;i>=0;i--){
            if (prices[i]>maxP) maxP = prices[i];
            else if (maxP-prices[i]>profit2)
                profit2 = maxP - prices[i];
            right[i] = profit2;
        }

        int result = 0;
        for (int i=0;i<n;i++){
            if(left[i]+right[i]> result)
                result = left[i]+right[i];
        }
        return result;
    }


    public static void main(String[] args){
        int[] nums = {7,1,5,3,6,5,4};
        //int k = 2;
        System.out.println(maxProfit2times(nums));
        System.out.println(maxProfit2times_2(nums));
    }

    public int maxProfit(int[] prices) {
        int b1 = Integer.MIN_VALUE;
        int b2 = Integer.MIN_VALUE;
        int s1 = 0;
        int s2  = 0;
        for (int i = 0; i < prices.length; i++) {
            int cur = prices[i];
            b1 = Math.max(b1, -cur);
            s1 = Math.max(s1, b1 + cur);
            b2 = Math.max(b2, s1 -cur);
            s2 = Math.max(s2, b2 + cur);
        }
        return s2;

    }

    public int maxProfitDP0(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][5];  //4 is also ok
        //dp[i][j]中 i表示第i天，j为 [0 - 4] 五个状态，dp[i][j]表示第i天状态j所剩最大现金。
        /*
         * 定义 5 种状态:
         * 0: 没有操作, 1: 第一次买入, 2: 第一次卖出, 3: 第二次买入, 4: 第二次卖出
         */
        // 1: profit after first buy
        // 2: after first sell
        // 3: after second buy
        // 4: after second sell
        dp[0][1] = - prices[0];
        dp[0][2] = 0;
        dp[0][3] = -prices[0];
        dp[0][4] = 0;
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i-1][0];
            dp[i][1] = Math.max(dp[i-1][1], dp[i - 1][0]-prices[i]);
            dp[i][2] = Math.max(dp[i-1][2], dp[i-1][1] + prices[i]);
            dp[i][3] = Math.max(dp[i-1][3], dp[i-1][2] - prices[i]);
            dp[i][4] = Math.max(dp[i-1][4], dp[i-1][3] + prices[i]);
        }
        return dp[n-1][4];
    }

    public int maxProfitdp1d(int[] prices) {
        int b1 = Integer.MIN_VALUE;
        int b2 = Integer.MIN_VALUE;
        int s1 = 0;
        int s2  = 0;
        for (int i = 0; i < prices.length; i++) {
            int cur = prices[i];
            b1 = Math.max(b1, -cur);
            s1 = Math.max(s1, b1 + cur);
            b2 = Math.max(b2, s1 -cur);
            s2 = Math.max(s2, b2 + cur);
        }
        return s2;
    }

    // 207 / 214 test cases passed. time limit exceeded :(
    public int maxProfit2wayDP(int[] prices) {
        // loop for all dividing points and get the maximum
        int n = prices.length;
        int res = 0;

        for (int i = 0; i <n ; i++) {
            int left = getMaxProfitWith1b1s(prices, 0, i);
            int right = getMaxProfitWith1b1s(prices, i + 1, n -1);
            res = Math.max(res, left + right);
        }
        return res;
    }
    private int getMaxProfitWith1b1s(int[] prices, int start, int end) {
        if (end <= start) return 0;
        int min = prices[start];
        int max= 0;
        for (int i = start + 1; i <= end; i++) {
            min = Math.min(min, prices[i]);
            max = Math.max(max, prices[i] - min);
        }
        return max;
    }

    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/solution/
    public int maxProfitTwoWayDP(int[] prices) {
        int length = prices.length;
        if (length <= 1) return 0;

        int leftMin = prices[0];
        int rightMax = prices[length - 1];

        int[] leftProfits = new int[length];
        // pad the right DP array with an additional zero for convenience.
        int[] rightProfits = new int[length + 1];

        // construct the bidirectional DP array
        for (int l = 1; l < length; ++l) {
            leftProfits[l] = Math.max(leftProfits[l - 1], prices[l] - leftMin);
            leftMin = Math.min(leftMin, prices[l]);

            int r = length - 1 - l;
            rightProfits[r] = Math.max(rightProfits[r + 1], rightMax - prices[r]);
            rightMax = Math.max(rightMax, prices[r]);
        }

        int maxProfit = 0;
        for (int i = 0; i < length; ++i) {
            maxProfit = Math.max(maxProfit, leftProfits[i] + rightProfits[i + 1]);
        }
        return maxProfit;
    }
}