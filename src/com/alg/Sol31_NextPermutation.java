package com.alg;

import java.util.Arrays;

/**
 * Created by HAU on 7/11/2017.
 */

/*
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

        If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

        The replacement must be in-place, do not allocate extra memory.

        Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
        1,2,3 → 1,3,2
        3,2,1 → 1,2,3
        1,1,5 → 1,5,1
*/

public class Sol31_NextPermutation {
    public static void nextPermutation(int[] nums) {
        int n = nums.length;
        if ( n == 1) return;
        int i = n - 1;
        while ( i > 0 && nums[i] <= nums[i-1]){
            i--;
        }
        if (i == 0){
            reverse_sub(nums,0,n-1);
            return;
        }
        if ( i == n - 1){
            swap(nums,i,i-1);
            return;
        }

        reverse_sub(nums,i,n-1);

        int k = i - 1;
        while ( i < n){
            if (nums[i] <= nums[k]){ // including equal
                i++;
            }
			else {
                swap(nums,i,k);
                break;
            }
        }

    }
    private static void swap(int[] nums, int i, int j){
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
    private static void reverse_sub(int[] nums, int i, int j){
        if ( i >= j) return;
        while ( i < j){
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
/*        int[] nums = { 5,2,7,3,1};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));

        int[] t1 = {1,2,3};
        nextPermutation(t1);
        System.out.println(Arrays.toString(t1));

        int[] t2 = {3,2,1};
        nextPermutation(t2);
        System.out.println(Arrays.toString(t2));

        int[] t3 = { 1,1,5};
        nextPermutation(t3);
        System.out.println(Arrays.toString(t3));*/


        int[] t4 = { 1,5,1};
        nextPermutation(t4);
        System.out.println(Arrays.toString(t4));
    }

}
