package com.alg;
/*
Given an array consisting of n integers,
find the contiguous subarray of given length k
that has the maximum average value.
And you need to output the maximum average value.

Example 1:

Input: [1,12,-5,-6,50,3], k = 4
Output: 12.75
Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75
 */
public class Sol643_MaximumAverageSubarrayI {
    public static double findMaxAverage(int[] nums, int k) {
        double sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }

        double cursum = sum;
        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - nums[i-k];
            cursum = Math.max(cursum, sum);
        }
        return cursum/k;
    }

    public static void main(String[] args) {
        int[] nums = {1,12,-5,-6,50,3};
        int k = 4;
        System.out.println(findMaxAverage(nums,k));
    }
}
