package com.alg;

/**
 * Created by HAU on 6/11/2017.
 */

/*
A median is the middle number of the array after it is sorted.
        If there are even numbers in the array, return the N/2-th number after sorted.
Example
Given [4, 5, 1, 2, 3], return 3.

Given [7, 9, 4, 5], return 5.
*/
public class Sol_MedianOfUnsortedArray {
    public static int median(int[] nums){
        if (nums == null) return -1;
        return helper(nums, 0, nums.length - 1, (nums.length+1)/2);
    }

    private static int helper(int[] nums, int lo, int hi, int size) {
        //int i = lo;
        int pivot = lo;
        //int right = hi;
        int v = nums[lo];
        for ( int i = lo + 1; i <= hi; i++){
            if( nums[i] < v)
                swap(nums,i,++pivot);
            //else if(nums[i] > v)
                //swap(nums,i--,right--);

        }
        swap(nums,pivot,lo);            // swap between nums[pivot] and nums[lo]
        if (pivot - lo + 1 == size){
            return nums[pivot];
        }else if (pivot - lo + 1 > size){
            return helper(nums, lo, pivot - 1, size);
        } else
            return helper(nums, pivot + 1, hi, size - ( pivot - lo + 1));
    }
    private static void swap(int[] a, int i, int j){
        int temp = a[j];
        a[j]= a[i];
        a[i]= temp;
    }

    public static void main(String[] args) {
        int[] nums = {14,3,2,11,5};
        System.out.println(median(nums));
        int[] a = {4,3,1,2};
        System.out.println(median(a));
    }
}
