package com.alg.dp;

import java.util.Arrays;

/*
Given an unsorted array of integers nums, return the length of the longest continuous increasing subsequence (i.e. subarray). The subsequence must be strictly increasing.

A continuous increasing subsequence is defined by two indices l and r (l < r) such that it is [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]]
and for each l <= i < r, nums[i] < nums[i + 1].



Example 1:

Input: nums = [1,3,5,4,7]
Output: 3
Explanation: The longest continuous increasing subsequence is [1,3,5] with length 3.
Even though [1,3,5,7] is an increasing subsequence, it is not continuous as elements 5 and 7 are separated by element
4.
Example 2:

Input: nums = [2,2,2,2,2]
Output: 1
Explanation: The longest continuous increasing subsequence is [2] with length 1. Note that it must be strictly
increasing.
 */
public class Sol674_LongestContinuousIncreasingSubsequence {
    public int findLengthOfLCIS(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i-1]) {
                dp[i] = dp[i-1] + 1;
            } else {
                dp[i] = 1;
            }
        }
        System.out.println(Arrays.toString(dp));
        int res = 1;
        for (int num: dp) {
            res = Math.max(res, num);
        }
        return res;
    }

    public static int findLengthOfLCIS2(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int res = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i + 1] > nums[i]) {
                dp[i + 1] = dp[i] + 1;
            }
            res = Math.max(res, dp[i + 1]);
        }
        return res;
    }

    // greedy
    public int findLengthOfLCIS3(int[] nums) {
        if (nums.length == 0) return 0;
        int result = 1; // 连续子序列最少也是1
        int count = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i + 1] > nums[i]) { // 连续记录
                count++;
            } else { // 不连续，count从头开始
                count = 1;
            }
            if (count > result) result = count;
        }
        return result;
    }

    // sliding window
    public int findLengthOfLCIS4(int[] nums) {
        int ans = 0, left = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (i > 0 && nums[i-1] >= nums[i]) { //if not strictly increasing, reset left
                left = i;
            }
            ans = Math.max(ans, i - left + 1);
        }
        return ans;
    }


    public static void main(String[] args) {
        Sol674_LongestContinuousIncreasingSubsequence ss = new Sol674_LongestContinuousIncreasingSubsequence();
        int[] nums = {1,2,5,2,5};
        System.out.println(ss.findLengthOfLCIS(nums));
    }
}
