package com.alg;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
The next greater element of some element x in an array is the first greater element that is to the right of x in the same array.

You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.

For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and determine the next greater element of nums2[j] in nums2. If there is no next greater element, then the answer for this query is -1.

Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.



Example 1:

Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
Output: [-1,3,-1]
Explanation: The next greater element for each value of nums1 is as follows:
- 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
- 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
- 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
Example 2:

Input: nums1 = [2,4], nums2 = [1,2,3,4]
Output: [3,-1]
Explanation: The next greater element for each value of nums1 is as follows:
- 2 is underlined in nums2 = [1,2,3,4]. The next greater element is 3.
- 4 is underlined in nums2 = [1,2,3,4]. There is no next greater element, so the answer is -1.


Constraints:

1 <= nums1.length <= nums2.length <= 1000
0 <= nums1[i], nums2[i] <= 104
All integers in nums1 and nums2 are unique.
All the integers of nums1 also appear in nums2.


Follow up: Could you find an O(nums1.length + nums2.length) solution?
 */
public class Sol496_NextGreaterElementI {
    // from left to right
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int size = nums1.length;
        int[] res = new int[size];
        Map<Integer, Integer> map = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < nums2.length; i++) {
            if (stack.isEmpty()) {
                stack.push(nums2[i]);
                continue;
            }
            if (nums2[i] < stack.peek()) {
                stack.push(nums2[i]);
            } else {
                while (!stack.isEmpty() && nums2[i] > stack.peek()) {
                    map.put(stack.pop(), nums2[i]);
                }
                stack.push(nums2[i]);
            }
        }
        for (int i = 0; i < size; i++) {
            res[i] = map.getOrDefault(nums1[i], -1);
        }
        return res;
    }

    // from left to right shorter version, monotonic （decreasing stack, top element is the smallest and popped
    // bottom element is largest
    public int[] nextG(int[] nums1, int nums2[]) {
        int size = nums1.length;
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = nums2.length-1; i>0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums2[i]) stack.pop(); // 把stack里比当前数字小的都pop掉，因为他们发挥不出优势；剩下的是比我们大的
            map.put(nums2[i], stack.isEmpty()? -1: stack.peek());
            stack.push(nums2[i]);
        }

        int[] res = new int[size];
        for (int i = 0; i < size; i ++) {
            res[i] = map.get(nums1[i]);
        }
        return res;
    }

    // from right to left
    public int[] nextGreater(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int num: nums2) {
            while (!stack.isEmpty() && stack.peek() < num) {
                map.put(stack.pop(), num);
            }
            stack.push(num);
        }
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.getOrDefault(nums1[i], -1);
        }
        return res;
    }
}
