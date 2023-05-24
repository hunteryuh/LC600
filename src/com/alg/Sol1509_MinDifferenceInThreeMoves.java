package com.alg;

import java.util.Arrays;

/*
Given an array nums, you are allowed to choose one element of nums and change it by any value in one move.

Return the minimum difference between the largest and smallest value of nums after perfoming at most 3 moves.



Example 1:

Input: nums = [5,3,2,4]
Output: 0
Explanation: Change the array [5,3,2,4] to [2,2,2,2].
The difference between the maximum and minimum is 2-2 = 0.
Example 2:

Input: nums = [1,5,0,10,14]
Output: 1
Explanation: Change the array [1,5,0,10,14] to [1,1,0,1,1].
The difference between the maximum and minimum is 1-0 = 1.
Example 3:

Input: nums = [6,6,0,1,1,4,6]
Output: 2
Example 4:

Input: nums = [1,5,6,14,15]
Output: 1

https://leetcode.com/problems/minimum-difference-between-largest-and-smallest-value-in-three-moves/
 */
public class Sol1509_MinDifferenceInThreeMoves {

    public int minDifference(int[] nums) {
        int len = nums.length;
        if (len <= 4) {
            return 0;
        }
        Arrays.sort(nums);
        int ans = nums[len-1] - nums[0];
        for (int i = 0; i <= 3 ; i++) {
            // optimal condition is that we replace the three among 3 that are in the prefix or suffix ( 3 smallest/largest)
            ans = Math.min(ans, nums[len - 4 + i] - nums[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] n = {6,6,0,1,1,4,6};
        System.out.println();

    }
}
