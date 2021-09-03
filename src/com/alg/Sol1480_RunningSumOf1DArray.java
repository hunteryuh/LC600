package com.alg;
/*
1480. Running Sum of 1d Array
Easy

1489

142

Add to List

Share
Given an array nums. We define a running sum of an array as runningSum[i] = sum(nums[0]…nums[i]).

Return the running sum of nums.



Example 1:

Input: nums = [1,2,3,4]
Output: [1,3,6,10]
Explanation: Running sum is obtained as follows: [1, 1+2, 1+2+3, 1+2+3+4].
Example 2:

Input: nums = [1,1,1,1,1]
Output: [1,2,3,4,5]
Explanation: Running sum is obtained as follows: [1, 1+1, 1+1+1, 1+1+1+1, 1+1+1+1+1].
Example 3:

Input: nums = [3,1,2,10,1]
Output: [3,4,6,16,17]


https://leetcode.com/problems/running-sum-of-1d-array/
 */
public class Sol1480_RunningSumOf1DArray {
    public int[] runningSum(int[] nums) {
        int[] r = new int[nums.length];
        r[0] = nums[0];
        for (int i = 1; i < r.length; i++) {
            r[i] = r[i-1] + nums[i];
        }
        return r;
    }
}
