package com.alg.dp;

import java.util.Arrays;

/*
Given a non-empty array nums containing only positive integers,
find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.



Example 1:

Input: nums = [1,5,11,5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].
Example 2:

Input: nums = [1,2,3,5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.
 */
public class Sol416_PartitionEqualSubsetSum {
    // https://leetcode.com/problems/partition-equal-subset-sum/discuss/90592/01-knapsack-detailed-explanation
    // Let us assume dp[i][j] means whether the specific sum j can be gotten from the first i numbers. If we can pick such a series of numbers from 0-i whose sum is j, dp[i][j] is true, otherwise it is false.
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num: nums) {
            sum += num;
        }
        if (sum % 2 == 1) {
            return false;
        }
        sum /= 2;
        int n = nums.length;
        boolean[][] dp = new boolean[n+1][sum+1];
        // assume dp[i][j] means whether the specific sum j can be gotten from the first i numbers.
        // If we can pick such a series of numbers from 0-i whose sum is j, dp[i][j] is true, otherwise it is false.
        dp[0][0] = true;
        for (int i = 1; i < n+1; i++) {
            dp[i][0] = true;
        }
        for (int j = 1; j < sum + 1; j++) {
            dp[0][j] = false;
        }
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < sum + 1; j++) {
                dp[i][j] = dp[i-1][j];
                if (j >= nums[i-1]) {
                    dp[i][j] = dp[i-1][j] || dp[i-1][j - nums[i-1]];
                }
            }
        }
        return dp[n][sum];
    }

    public boolean canPartition2(int[] nums) {
        int sum = 0;
        for (int num: nums) {
            sum += num;
        }
//        sum = Arrays.stream(nums).sum();
        if (sum % 2 == 1) return false;
        sum /= 2;
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        for (int num: nums) {
            for (int j = sum; j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];
            }
        }
        return dp[sum];
    }

    public boolean canPartition3(int[] nums) {
        int target = 0;
        target = Arrays.stream(nums).sum();
        if (target % 2 != 0) return false;
        target /= 2;
        int length = nums.length;
        int[][] dp = new int[length][target + 1];
        for (int i = 0; i < length; i++) {
            dp[i][0] = 0;
        }
        for (int j = nums[0]; j <= target; j++) {
            dp[0][j] = nums[0];
        }
        for (int i = 1; i < length; i++) {
            for (int j = 1; j <= target; j++) {
                if ( j< nums[i]) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    //物品 i 的重量是 nums[i]，其价值也是 nums[i]
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j - nums[i]] + nums[i]);
                }
            }
        }
        for (int i = 0; i < length; i++) {
            for (int j = 0; j <= target; j++) {
                System.out.print(dp[i][j]+ " ");
            }
            System.out.println("");
            // 0 1 1 1 1 1 1 1 1 1 1 1
            //0 1 1 1 1 5 6 6 6 6 6 6
            //0 1 1 1 1 5 6 6 6 6 6 11
            //0 1 1 1 1 5 6 6 6 6 10 11
        }
        return dp[length-1][target] == target;
    }

    public static void main(String[] args) {
        int[] nums = {1,5,11,5};
        Sol416_PartitionEqualSubsetSum ss = new Sol416_PartitionEqualSubsetSum();
        System.out.println(ss.canPartition3(nums));
    }

    public boolean canPartition4(int[] nums) {
        // target = sum / 2;
        // 01 knapsack
        int target = Arrays.stream(nums).sum();
        if (target % 2 != 0) return false;
        target /= 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int i = 0; i < nums.length; i++) {
            for (int j = target; j >= nums[i]; j--) {
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[target];
    }

    // TLE, with memoization
    Boolean[][] memo;
    public boolean canPartition5(int[] nums) {
        int target = 0;
        for (int num: nums) {
            target += num;
        }
        if (target % 2 != 0) return false;
        target = target /2;
        int n = nums.length;
        memo = new Boolean[n + 1][target + 1]; // first n items to reach sum of target
        return isTargetSubSetSum(nums, target, n);
    }
    private boolean isTargetSubSetSum(int[] nums, int target, int n) {
        if (target == 0) {
            return true;
        }
        if (n <= 0 || target < 0) return false; // all items are positive
        if (memo[n][target] != null) {
            return memo[n][target];
        }
        memo[n][target] =  isTargetSubSetSum(nums, target - nums[n - 1], n - 1)
                || isTargetSubSetSum(nums, target, n - 1);
        return memo[n][target];
    }
}
