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

    public int minSubArrayLength(int target, int[] nums) {
        int res = nums.length + 1;
        int sum = 0;
        int sublength = 0;
        int start = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while (sum >= target) {
                sublength = i - start + 1;
                if (sublength < res) {
                    res = sublength;
                }
                sum -= nums[start];
                start++;
            }
        }
        if (res == nums.length + 1) {
            return 0;
        }
        return res;
    }
    // two pointers, with while
    public int minSubArrayLen3(int target, int[] nums) {
        int n = nums.length;
        int i = 0, j = 0;
        int currSum = 0;
        int minLen = Integer.MAX_VALUE;

        while (j < n) {
            currSum += nums[j];
            while (currSum >= target) {
                minLen = Math.min(minLen, j - i + 1);
                currSum -= nums[i];
                i++;
            }
            j++;
        }

        return (minLen != Integer.MAX_VALUE) ? minLen : 0;
    }


    // nlogn
    public int minSubArrayLen2(int s, int[] nums) {
        int i = 1, j = nums.length, min = 0;
        while (i <= j) {
            int mid = (i + j) / 2;
            if (windowExist(mid, nums, s)) {
                j = mid - 1;
                min = mid;
            } else i = mid + 1;
        }
        return min;
    }


    private boolean windowExist(int size, int[] nums, int s) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i >= size) sum -= nums[i - size];
            sum += nums[i];
            if (sum >= s) return true;
        }
        return false;
    }
}
