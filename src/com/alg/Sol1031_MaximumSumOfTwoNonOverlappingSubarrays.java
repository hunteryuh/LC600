package com.alg;
/*
Given an integer array nums and two integers firstLen and secondLen, return the maximum sum of elements in two non-overlapping subarrays with lengths firstLen and secondLen.

The array with length firstLen could occur before or after the array with length secondLen, but they have to be non-overlapping.

A subarray is a contiguous part of an array.



Example 1:

Input: nums = [0,6,5,2,2,5,1,9,4], firstLen = 1, secondLen = 2
Output: 20
Explanation: One choice of subarrays is [9] with length 1, and [6,5] with length 2.
Example 2:

Input: nums = [3,8,1,3,2,1,8,9,0], firstLen = 3, secondLen = 2
Output: 29
Explanation: One choice of subarrays is [3,8,1] with length 3, and [8,9] with length 2.
Example 3:

Input: nums = [2,1,5,6,0,9,5,0,3,8], firstLen = 4, secondLen = 3
Output: 31
Explanation: One choice of subarrays is [5,6,0,9] with length 4, and [0,3,8] with length 3.
 */
public class Sol1031_MaximumSumOfTwoNonOverlappingSubarrays {
    // https://leetcode.com/problems/maximum-sum-of-two-non-overlapping-subarrays/discuss/279221/JavaPython-3-two-easy-DP-codes-w-comment-time-O(n)-NO-change-of-input
    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        int len = nums.length;
        int[] prefixSum = new int[len];
        prefixSum[0] = nums[0];
        for (int i = 1; i < len; i++) {
            prefixSum[i] = nums[i] + prefixSum[i-1];
        }
        int res = 0;
        int Lmax = prefixSum[firstLen-1];
        int Mmax = prefixSum[secondLen-1];
        for (int i = 0; i < len; i++) {
            if (i >= firstLen && i + secondLen - 1 < len) {
                res = Math.max(res, Lmax + prefixSum[i + secondLen - 1] - prefixSum[i - 1]);
            }

            if (i >= secondLen && i + firstLen - 1 < len) {
                res = Math.max(res, Mmax + prefixSum[i + firstLen - 1] - prefixSum[i - 1]);
            }
            if (i >= firstLen) {
                Lmax = Math.max(Lmax, prefixSum[i] - prefixSum[i - firstLen]);// before i, ith excluded
            }

            if (i >= secondLen) {
                Mmax = Math.max(Mmax, prefixSum[i] - prefixSum[i - secondLen]);
            }
        }

        return res;
    }
}
