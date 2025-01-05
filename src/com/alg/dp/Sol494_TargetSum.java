package com.alg.dp;

import java.util.Arrays;

/*
You are given an integer array nums and an integer target.

You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate all the integers.

    For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".

Return the number of different expressions that you can build, which evaluates to target.



Example 1:

Input: nums = [1,1,1,1,1], target = 3
Output: 5
Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
-1 + 1 + 1 + 1 + 1 = 3
+1 - 1 + 1 + 1 + 1 = 3
+1 + 1 - 1 + 1 + 1 = 3
+1 + 1 + 1 - 1 + 1 = 3
+1 + 1 + 1 + 1 - 1 = 3

Example 2:

Input: nums = [1], target = 1
Output: 1

 */
public class Sol494_TargetSum {
    // 01 knapsack
    // https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0494.%E7%9B%AE%E6%A0%87%E5%92%8C.md
    public int findTargetSum(int[] nums, int target) {
        int sum = Arrays.stream(nums).sum();
        // x - (sum - x ) = target
        if ((sum + target) % 2 != 0) return 0;
        if (Math.abs(target) > Math.abs(sum)) return 0;
        int knsize= (sum + target ) >> 1;
        // bag size is knsize, how many ways to fill the bag
        int[] dp = new int[knsize + 1];
        // dp[0] = 1，理论上也很好解释，装满容量为0的背包，有1种方法，就是装0件物品。
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = knsize; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]]; // dp[i] = dp[i] (not take num) + dp[i - num] (take num)
            }
        }
        return dp[knsize];
    }
    // https://leetcode.com/problems/target-sum/discuss/1629253/Java-01-knapsack-with-explanation

    // recursion with memoization
    int total;
    public int findTargetSum2(int[] nums, int s){
        for (int i : nums) {
            total += i;
        }
        int[][] memo = new int[nums.length][2 * total + 1];
        for (int[] row: memo) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }
        return calculate(nums, 0, 0, s, memo);
    }
    private int calculate(int[] nums, int i, int sum, int s, int[][] memo) {
        if (i == nums.length) {
            if (sum == s) {
                return 1;
            } else {
                return 0;
            }
        }
        if (memo[i][sum + total] != Integer.MIN_VALUE) { // use sum + total just ot make a positive integer as index
            return memo[i][sum + total];
        }
        int add = calculate(nums, i + 1, sum + nums[i], s, memo);
        int subtract = calculate(nums, i + 1, sum - nums[i], s, memo);
        memo[i][sum + total] = add + subtract;
        return memo[i][sum + total];
    }
}
