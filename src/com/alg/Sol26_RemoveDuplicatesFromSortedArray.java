package com.alg;

/**
 * Created by HAU on 6/7/2017.
 */
/*
Given a sorted array, remove the duplicates in place such that each
element appear only once and return the new length.

        Do not allocate extra space for another array, you must do this
        in place with constant memory.

        For example,
        Given input array nums = [1,1,2],

        Your function should return length = 2, with
        the first two elements of nums being 1 and 2 respectively.
        It doesn't matter what you leave beyond the new length.
*/
public class Sol26_RemoveDuplicatesFromSortedArray {
    public static int removeDuplicates(int[] nums) {
        if (nums.length ==0) return 0;
        int n = nums.length;
        int newlength = 0;
        for (int i=1;i<n;i++){
            if (nums[i]!=nums[newlength]){
                nums[++newlength]= nums[i];
            }
        }


        return newlength+1;

    }

    public static void main(String[] args) {
        int[] nums= {1,1,1,2,2,3,3,3,3};
        System.out.println(removeDuplicates(nums));

        int[] n2 = {1};
        System.out.println(removeDuplicates(n2));
    }
}
