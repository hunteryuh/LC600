package com.alg;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/*
Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]), return the next greater number for every element in nums.

The next greater number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, return -1 for this number.



Example 1:

Input: nums = [1,2,1]
Output: [2,-1,2]
Explanation: The first 1's next greater number is 2;
The number 2 can't find next greater number.
The second 1's next greater number needs to search circularly, which is also 2.

Example 2:

Input: nums = [1,2,3,4,3]
Output: [2,3,4,-1,4]



Constraints:

    1 <= nums.length <= 104
    -109 <= nums[i] <= 109


 */
public class Sol503_NextGreaterElementII {
    public int[] nextGreaterElements(int[] nums) {
        // circular
        // 1 2 3 4 3  1 2 3 4 3
        //   2 3 4 -1 4
        //  value   4 3
        // in stack  3 4
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < 2*n; i++) {
            if (stack.isEmpty()) {
                stack.push(i%n);
                continue;
            }
            if (nums[i%n] <= nums[stack.peek()]) {  // index in the stack, not the value
                stack.push(i%n);
            } else {
            // 模拟遍历两边nums，注意一下都是用i % nums.size()来操作
                while (!stack.isEmpty() && nums[i%n] > nums[stack.peek()]) {
                    res[stack.peek()] = nums[i%n];
                    stack.pop();
                }
                stack.push(i%n);
            }

        }
        return res;
    }
}
