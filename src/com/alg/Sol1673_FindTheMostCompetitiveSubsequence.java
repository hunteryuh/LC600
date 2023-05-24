package com.alg;

import java.util.Arrays;
import java.util.Stack;

/*
Given an integer array nums and a positive integer k, return the most competitive subsequence of nums of size k.

An array's subsequence is a resulting sequence obtained by erasing some (possibly zero) elements from the array.

We define that a subsequence a is more competitive than a subsequence b (of the same length) if in the first position where a and b differ, subsequence a has a number less than the corresponding number in b. For example, [1,3,4] is more competitive than [1,3,5] because the first position they differ is at the final number, and 4 is less than 5.



Example 1:

Input: nums = [3,5,2,6], k = 2
Output: [2,6]
Explanation: Among the set of every possible subsequence: {[3,5], [3,2], [3,6], [5,2], [5,6], [2,6]}, [2,6] is the most competitive.
Example 2:

Input: nums = [2,4,3,3,5,4,9,6], k = 4
Output: [2,3,3,4]

https://leetcode.com/problems/find-the-most-competitive-subsequence/
 */
public class Sol1673_FindTheMostCompetitiveSubsequence {
    //  we just need to find the k elements in such a way that they are in increasing order with as smallest combination as possible.
    public static int[] mostCompetitive(int[] nums, int k) {
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i<n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i] && stack.size() - 1 + n - i >= k) {
                stack.pop();
            }
            if (stack.size() < k) {
                stack.push(i);
            }
        }
        int[] res = new int[k];
        for(int i = k - 1; i>=0; i--) {
            res[i] = nums[stack.pop()];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums= {2,4,3,3,5,4,9,5};
        int[] res = mostCompetitive(nums, 4);
        System.out.println(Arrays.toString(res));
        int[] nums2= {3,2,5,6};
        int[] res2 = mostCompetitive2(nums2, 2);
        System.out.println(Arrays.toString(res2));

    }

    public static int[] mostCompetitive2(int[] nums, int k) {
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i<n; i++) {
            while (!stack.isEmpty() && stack.peek() > nums[i] && stack.size() - 1 + n - i >= k) {
                // if after pop we still have at least k items, then we can pop
                stack.pop();
            }
            if (stack.size() < k) {
                stack.push(nums[i]);  //this will end up with a stack with size k
            }
        }
        int[] res = new int[k];
        for(int i = k - 1; i>=0; i--) {
            res[i] = stack.pop();
        }
        return res;
    }
}
