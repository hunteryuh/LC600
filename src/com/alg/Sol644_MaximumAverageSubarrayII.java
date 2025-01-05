package com.alg;
/*
You are given an integer array nums consisting of n elements, and an integer k.

Find a contiguous subarray whose length is greater than or equal to k that has the maximum average value and return this value. Any answer with a calculation error less than 10-5 will be accepted.



Example 1:

Input: nums = [1,12,-5,-6,50,3], k = 4
Output: 12.75000
Explanation:
- When the length is 4, averages are [0.5, 12.75, 10.5] and the maximum average is 12.75
- When the length is 5, averages are [10.4, 10.8] and the maximum average is 10.8
- When the length is 6, averages are [9.16667] and the maximum average is 9.16667
The maximum average is when we choose a subarray of length 4 (i.e., the sub array [12, -5, -6, 50]) which has the max average 12.75, so we return 12.75
Note that we do not consider the subarrays of length < 4.

Example 2:

Input: nums = [5], k = 1
Output: 5.00000



Constraints:

    n == nums.length
    1 <= k <= n <= 104
    -104 <= nums[i] <= 104


 */
public class Sol644_MaximumAverageSubarrayII {
    // https://leetcode.com/problems/maximum-average-subarray-ii/editorial/

    // time O(N⋅log2 (max_val−min_val)/0.00001)
    public double findMaxAverage(int[] nums, int k) {
        double left = nums[0];
        double right = nums[0];
        for (int n: nums) {
            left = Math.min(left, n);
            right = Math.max(right, n);
        }
        while (right - left > 0.00001) {
            double mid = (left + right) / 2;
            if (check(nums, mid, k)) { // if there exists a mid (max average) with length >=k
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }
    public boolean check(int[] nums, double mid, int k) {
        double sum = 0, prevSum = 0, minPrevSum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i] - mid;
            // (a1 + a2 + a3)/3 >= mid equivalent to
            // a1 - mid + a2 - mid + a3 - mid >= 0
            if (i >= k - 1) { // length >=k
                if (sum - minPrevSum >= 0) {
                    return true;
                }
                prevSum += nums[i - k + 1] - mid;
                minPrevSum = Math.min(minPrevSum, prevSum);
            }
        }
        return false;
    }
}
