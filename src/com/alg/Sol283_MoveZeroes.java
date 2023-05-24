package com.alg;

import java.util.Arrays;

/**
 * Created by HAU on 7/22/2017.
 */
/*
Given an array nums, write a function to move all 0's
        to the end of it while maintaining the relative order of the non-zero elements.

        For example, given nums = [0, 1, 0, 3, 12],
        after calling your function, nums should be [1, 3, 12, 0, 0].

        Note:
        You must do this in-place without making a copy of the array.
        Minimize the total number of operations.*/
public class Sol283_MoveZeroes {
    public static void moveZeroes(int[] nums){
        int n = nums.length;
        for (int i = 0, j = 0; i < n; i++) {
           if (nums[i] != 0){
               swap(nums, i, j++);
           }
        }
        // space O(1), time O(n)
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    public static void main(String[] args) {
        int[] a = { 0,1,0,3,12};
        moveZeroes(a);
        System.out.println(Arrays.toString(a));
    }

    public static void moveZeroes2(int[] nums){
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != 0){
                int tmp = nums[j];
                nums[j] = nums[i];
                nums[i] = tmp;
                j++;
            }
        }

    }

    // https://leetcode.com/problems/move-zeroes/discuss/72011/Simple-O(N)-Java-Solution-Using-Insert-Index
    public void move0s(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                continue;
            }
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            j++;
        }
    }

}
