package com.alg;
/*
Given an array nums which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays.

Write an algorithm to minimize the largest sum among these m subarrays.



Example 1:

Input: nums = [7,2,5,10,8], m = 2
Output: 18
Explanation:
There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8],
where the largest sum among the two subarrays is only 18.
Example 2:

Input: nums = [1,2,3,4,5], m = 2
Output: 9
Example 3:

Input: nums = [1,4,4], m = 3
Output: 4

Constraints:

    1 <= nums.length <= 1000
    0 <= nums[i] <= 106
    1 <= m <= min(50, nums.length)


https://leetcode.com/problems/split-array-largest-sum/
 */
public class Sol410_SplitArrayLargestSum {

    // binary search
    // time: O(n* log(S))  n: length of the array; S: sum of integers in the array
    public int splitArray(int[] nums, int m) {
        int summin = Integer.MIN_VALUE;
        int summax = 0;
        for (int num: nums) {
            summin = Math.max(num, summin);
            summax += num;
        }
        int left = summin;
        int right = summax;
        while (left < right) {
            int mid = left + (right - left)/2;  // trying mid as the max allowed sum for subarray
            if (isValid(nums, mid, m)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean isValid(int[] nums, int mid, int m) {
        int curSum = 0;
        int splits = 1;
        for (int i = 0; i < nums.length; i++) {
            curSum += nums[i];
            if (curSum > mid) {
                curSum = nums[i];
                splits++;
                if (splits > m) return false;
            }
        }
        return true;
    }

    // top down dp
    // https://leetcode.com/problems/split-array-largest-sum/solution/
    // time : O (N^2 * M)
    // space: O (N * M)
    Integer[][] memo = new Integer[1001][51]; // minimum largest sum for a certain range and number of subarrays
    public int SplitArray2(int[] nums, int m) {
        int n = nums.length;
        int[] prefixSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];  // prefixSum[i] sum of all elements before i
        }
        return getMinLargestSplitSum(prefixSum, 0, m);
    }

    private int getMinLargestSplitSum(int[] prefixSum, int currentIndex, int subarrayCount) {
        int n = prefixSum.length - 1;
        if (memo[currentIndex][subarrayCount] != null) {
            return memo[currentIndex][subarrayCount];
        }
        // base case: if there is only one subarry left
        if (subarrayCount == 1) {
            return memo[currentIndex][subarrayCount] = prefixSum[n] - prefixSum[currentIndex];
        }

        int minLargestSplitSum = Integer.MAX_VALUE;
        for (int i = currentIndex; i <= n - subarrayCount; i++) {
            int firstSplitSum = prefixSum[i+1] - prefixSum[currentIndex];
            int largestSplitSum = Math.max(firstSplitSum, getMinLargestSplitSum(prefixSum, i + 1, subarrayCount - 1));
            minLargestSplitSum = Math.min(minLargestSplitSum, largestSplitSum);
            if (firstSplitSum >= minLargestSplitSum) {
                break;
            }

        }
        return memo[currentIndex][subarrayCount] = minLargestSplitSum;
    }

    // bottom up dp
    int[][] memo2 = new int[1001][51];

    public int splitArray2(int[] nums, int m) {
        int n = nums.length;

        // Store the prefix sum of nums array
        int[] prefixSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }

        for (int subarrayCount = 1; subarrayCount <= m; subarrayCount++) {
            for (int currIndex = 0; currIndex < n; currIndex++) {
                // Base Case: If there is only one subarray left, then all of the remaining numbers
                // must go in the current subarray. So return the sum of the remaining numbers.
                if (subarrayCount == 1) {
                    memo[currIndex][subarrayCount] = prefixSum[n] - prefixSum[currIndex];
                    continue;
                }

                // Otherwise, use the recurrence relation to determine the minimum largest subarray
                // sum between currIndex and the end of the array with subarrayCount subarray remaining.
                int minimumLargestSplitSum = Integer.MAX_VALUE;
                for (int i = currIndex; i <= n - subarrayCount; i++) {
                    // Store the sum of the first subarray.
                    int firstSplitSum = prefixSum[i + 1] - prefixSum[currIndex];

                    // Find the maximum subarray sum for the current first split.
                    int largestSplitSum = Math.max(firstSplitSum, memo[i + 1][subarrayCount - 1]);

                    // Find the minimum among all possible combinations.
                    minimumLargestSplitSum = Math.min(minimumLargestSplitSum, largestSplitSum);

                    if (firstSplitSum >= minimumLargestSplitSum) {
                        break;
                    }
                }

                memo[currIndex][subarrayCount] = minimumLargestSplitSum;
            }
        }

        return memo[0][m];
    }
}
