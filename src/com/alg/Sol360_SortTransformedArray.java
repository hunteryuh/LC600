package com.alg;

import java.util.Arrays;

/*
Given a sorted integer array nums and three integers a, b and c, apply a quadratic function of the form
f(x) = ax2 + bx + c to each element nums[i] in the array, and return the array in a sorted order.



Example 1:

Input: nums = [-4,-2,2,4], a = 1, b = 3, c = 5
Output: [3,9,15,33]

Example 2:

Input: nums = [-4,-2,2,4], a = -1, b = 3, c = 5
Output: [-23,-5,1,7]



Constraints:

    1 <= nums.length <= 200
    -100 <= nums[i], a, b, c <= 100
    nums is sorted in ascending order.



Follow up: Could you solve it in O(n) time?

https://leetcode.com/problems/sort-transformed-array/
 */
public class Sol360_SortTransformedArray {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int n = nums.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = a*nums[i]*nums[i] + b * nums[i] + c;
        }
        Arrays.sort(res);
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {-4, -2, 2, 4};
        int a = -1;
        int b = 3;
        int c = 5;

        Sol360_SortTransformedArray ss = new Sol360_SortTransformedArray();
        int[] res = ss.sortTransformedArray(nums, a, b, c);
        System.out.println(Arrays.toString(res));

    }

    public int[] transformedArray2(int[] nums, int a, int b, int c) {
        if (nums == null) return null;
        int n = nums.length;
        int[] res = new int[n];
        int left = 0;
        int right = n - 1;
        int k = n - 1;
        while (left <= right) {
            int y1 = f(nums[left], a, b, c);
            int y2 = f(nums[right], a, b, c);
            if (a >= 0) {
                if (y1 >= y2) {
                    res[k] = y1;
                    left++;
                } else {
                    res[k] = y2;
                    right--;
                }
            } else { // a < 0
                if (y1 >= y2) {
                    res[n-1-k] = y2;
                    right--;
                } else {
                    res[n-1-k] = y1;
                    left++;
                }
            }
            k--;
        }
        return res;
    }
    private int f(int x, int a, int b, int c) {
        return a * x * x + b * x + c;
    }
}

