package com.alg;

import java.util.Stack;

/*
Given an array of n integers nums, a 132 pattern is a subsequence of three integers nums[i], nums[j] and nums[k] such that i < j < k and nums[i] < nums[k] < nums[j].

Return true if there is a 132 pattern in nums, otherwise, return false.

Follow up: The O(n^2) is trivial, could you come up with the O(n logn) or the O(n) solution?



Example 1:

Input: nums = [1,2,3,4]
Output: false
Explanation: There is no 132 pattern in the sequence.
Example 2:

Input: nums = [3,1,4,2]
Output: true
Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
Example 3:

Input: nums = [-1,3,2,0]
Output: true
Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
 */
public class Sol456_132Pattern {
    public boolean find132pattern(int[] nums) {
        // time limit exceeded
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] < nums[k] && nums[k] < nums[j]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // O(n^2), find the min as i
    public boolean find132Pattern2(int[] nums) {
        int min = nums[0];
        for (int i = 0; i < nums.length - 1; i++) {
            min = Math.min(min, nums[i]);
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[i] && min < nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    // O(n) with Stack
    public boolean find132Pattern3(int[] nums) {
        int[] min = new int[nums.length];
        min[0] = nums[0];
        for (int i = 1; i< nums.length; i++) {
            min[i] = Math.min(min[i-1], nums[i]);
        }

        Stack<Integer> stack = new Stack<>();
        for (int j = nums.length - 1; j >= 0; j--) {
            if (nums[j] > min[j]) {
                while (!stack.isEmpty() && stack.peek() <= min[j]) {
                    stack.pop();
                }
                if (!stack.isEmpty() && stack.peek() < nums[j]) {
                    return true;
                }
                stack.push(nums[j]);
            }
        }
        return false;
    }
}

