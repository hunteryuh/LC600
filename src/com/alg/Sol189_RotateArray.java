package com.alg;

import java.util.Arrays;

/**
 * Created by HAU on 7/7/2017.
 */

/*
Rotate an array of n elements to the right by k steps.

        For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is
        rotated to [5,6,7,1,2,3,4].

        Hint:
Could you do it in-place with O(1) extra space?
*/


public class Sol189_RotateArray {
    public static void rotate_slow(int[] nums, int k) {
        int n = nums.length;
//        int[] res = new int[n];
        int i = 0;
        while ( i < k%n){
            int prev = nums[n-1];

            for ( int j = 0; j < n-1 ; j++){
                int cur = nums[j];
                nums[j] = prev;
                prev = cur;
            }
            nums[n-1] = prev;
            i++;

        }

    }
    public static void rotate(int[] nums,int k){
        int n = nums.length;
        k = k %n;
        reverse(nums,0,n-1 -k);
        reverse(nums,n-k, n-1);
        reverse(nums,0,n-1);
    }

    private static void reverse(int[] nums, int i, int j) {
        while ( i < j){
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;j--;
        }
    }

    public static void main(String[] args) {
        int[] nums = { 1,2,3,4,5,6,7};
        int k = 3;
        rotate(nums,k);
        System.out.println(Arrays.toString(nums));

    }
}
