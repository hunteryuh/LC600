package com.alg;
/*
Given an integer array nums of size n, return the minimum number of moves required to make all array elements equal.

In one move, you can increment n - 1 elements of the array by 1.

Example 1:

Input: nums = [1,2,3]
Output: 3
Explanation: Only three moves are needed (remember each move increments two elements):
[1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
Example 2:

Input: nums = [1,1,1]
Output: 0
 */
public class Sol453_MinimumMovesToEqualArrayElements {
    public int minMoves(int[] nums) {
        int sum = 0;
        int min = nums[0];
        for (int i: nums) {
            min = Math.min(min, i);
            sum += i;
        }
        int res = sum - (min * nums.length);
        return res;
    }

    // https://leetcode.com/problems/minimum-moves-to-equal-array-elements/discuss/1567828/Java-oror-Explained-with-Example-oror-Math-Problem
    // convert to decrease 1 number by 1 in each move;
    public static void main(String[] args) {
        int[] nums = {3,2,1};
        Sol453_MinimumMovesToEqualArrayElements ss = new Sol453_MinimumMovesToEqualArrayElements();
        System.out.println(ss.minMoves(nums));
    }
}
