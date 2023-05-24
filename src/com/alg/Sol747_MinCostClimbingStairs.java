package com.alg;

/**
 * Created by HAU on 12/28/2017.
 */
/*On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).

Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top of the floor,
and you can either start from the step with index 0, or the step with index 1.

Example 1:
Input: cost = [10, 15, 20]
Output: 15
Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
Example 2:
Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
Output: 6
Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].*/
public class Sol747_MinCostClimbingStairs {
    public static int minCostClimbingStairs(int[] cost) {
        int f1 = 0;
        int f2 = 0;
        for (int i = cost.length - 1; i >= 0; i--){
            int f0 = cost[i] + Math.min(f1,f2);
            f2 = f1;
            f1 = f0;
        }
        return Math.min(f1,f2);

    }

    public static void main(String[] args) {
        int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        System.out.println(minCostClimbingStairs(cost));
        System.out.println(minCostStairs(cost));
    }

    // explicit dp solution
    public static int minCostStairs(int[] cost) {
        // reuse the array to save space
        for(int i = cost.length - 3; i >= 0; i--){
            cost[i] = cost[i] + Math.min(cost[i+1],cost[i+2]);
        }
        return Math.min(cost[0],cost[1]);
    }

    public int minCostStairs3(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n+1];
        dp[0]= 0;  // not cost[0]
        dp[1] = 0;
        for (int i = 2; i <=n; i++) {
            dp[i] = Math.min(dp[i-1] + cost[i-1], dp[i-2] + cost[i-2]);
        }
        return dp[n];
    }



    // The "top of the floor" does not refer to the final index of costs. We actually need to "arrive" beyond the array's bounds.

    public int minCostClimbingStairs2(int[] cost) {
        if (cost == null || cost.length == 0) {
            return 0;
        }
        if (cost.length == 1) {
            return cost[0];
        }
        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < cost.length; i++) {
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
        }
        //最后一步，如果是由倒数第二步爬，则最后一步的体力花费可以不用算
        return Math.min(dp[cost.length - 1], dp[cost.length - 2]);
    }
}
