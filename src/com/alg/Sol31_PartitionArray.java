package com.alg;

import java.util.Arrays;

/**
 * Created by HAU on 7/11/2017.
 */

/*
Given an array nums of integers and an int k, partition the array (i.e move the elements in "nums") such that:

All elements < k are moved to the left
All elements >= k are moved to the right
Return the partitioning index, i.e the first index i nums[i] >= k.
*/

public class Sol31_PartitionArray {
    /**
     * @param nums: The integer array you should partition
     * @param k: An integer
     * @return The index after partition
     */
    public static int partitionArray(int[] nums, int k) {
        // write your code here
        if (nums ==  null || nums.length == 0) {
            return 0;
        }

        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            while (left < right && nums[left] < k) {
                // valid  left < right, same validations as the parent loop,
                // this consistency here makes it always no wrong.
                left++;
            }
            while (left < right && nums[right] >= k) {
                right--;
            }

            if (left < right) {
                int temp = nums[right];
                nums[right] = nums[left];
                nums[left] = temp;
                left++; right--; // without this it still passed because the above while loops?
            }
        }
        // if left >= right after the loop
        if (nums[left] < k) return left + 1;
        return left;
    }


    public static void main(String[] args) {
        int[] arr = {3,2,2,1};
        System.out.println(partitionArray(arr, 2));
    }
}
