package com.alg;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.

Example:

Input: s = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: the subarray [4,3] has the minimal length under the problem constraint.
Follow up:
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
*/
public class Sol209_MinimumSizeSubarraySum {
    public static int minSubArrayLen(int s, int[] nums) {
        int res = nums.length + 1;
        int sum = 0;
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            while (j < nums.length && sum < s) {
                sum += nums[j];
                j++;
            }
            if (sum >= s) {
                res = Math.min(res, j - i);
            }
            // use the precomputed sum and just exclude the left most item in the contiguous subarray
            sum -= nums[i];
        }
        return res == nums.length + 1 ? 0 : res;
    }

    public static void main(String[] args) {
        int[] nums = {2,3,4,3,1,2,1};
        int s = 7;
        System.out.println(minSubArrayLen(s, nums));
    }
}
