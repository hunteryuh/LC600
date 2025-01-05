package com.alg;
/*
An array is monotonic if it is either monotone increasing or monotone decreasing.

An array nums is monotone increasing if for all i <= j, nums[i] <= nums[j]. An array nums is monotone decreasing if for all i <= j, nums[i] >= nums[j].

Given an integer array nums, return true if the given array is monotonic, or false otherwise.



Example 1:

Input: nums = [1,2,2,3]
Output: true

Example 2:

Input: nums = [6,5,4,4]
Output: true

Example 3:

Input: nums = [1,3,2]
Output: false



Constraints:

    1 <= nums.length <= 105
    -105 <= nums[i] <= 105


 */
public class Sol896_MonotonicArray {
    // O(n) two passes
    public boolean isMonotonic(int[] nums) {
        if (nums.length == 1) return true;
        return isIncreasing(nums) || isDecreasing(nums);
    }

    private boolean isIncreasing(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i + 1] < nums[i]) return false;
        }
        return true;
    }

    private boolean isDecreasing(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i + 1] > nums[i]) return false;
        }
        return true;
    }


    // one pass
    public boolean isMonotonic2(int[] nums) {
        int store = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            int c = Integer.compare(nums[i], nums[i + 1]);
            if (c != 0) {
                if (c != store && store != 0) {
                    return false;
                }
                store = c;
            }
        }
        return true;
    }

    // one pass 2
    public boolean isMonotonic3(int[] nums) {
        boolean increasing = true;
        boolean decreasing = true;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] < nums[i+1]) {
                decreasing = false;
            }
            if (nums[i] > nums[i + 1]) {
                increasing = false;
            }
        }
        return increasing || decreasing;
    }

    public boolean isMonotonic4(int[] A) {
        boolean inc = false;
        boolean dec = false;

        for (int i = 0; i < A.length - 1; i++) {
            if (A[i+1] > A[i]) inc = true;
            if (A[i+1] < A[i]) dec = true;
            if (inc && dec) return false;
        }

        return true;
    }
}
