package com.alg;


import java.util.Arrays;

/**
 * Created by HAU on 7/11/2017.
 */
/*
Given an array and a value, remove all instances of that value
in place and return the new length.

        Do not allocate extra space for another array, you must
        do this in place with constant memory.

        The order of elements can be changed. It doesn't matter
        what you leave beyond the new length.

        Example:
        Given input array nums = [3,2,2,3], val = 3

        Your function should return length = 2, with the first
        two elements of nums being 2.*/
public class Sol27_RemoveElement {
    public static int removeElement_slow(int[] nums, int val) {
        int n = nums.length;
        int count = 0;

        for (int i = 0; i < n; i++){
            if (nums[i] != val){
                count++;
            }
        }

        int i = 0;
        int j = n-1;
        while ( i < j){
            if ( nums[i] != val) i++;
            if ( nums[j] == val) j--;
            swap(nums,i,j);
            i++;j--;
        }


        return count;
    }

    public static int removeElement(int[] nums, int val) {
        int m = 0;
        for(int i = 0; i < nums.length; i++){

            if(nums[i] != val){
                nums[m] = nums[i];
                m++;
            }
        }

        return m;
    }
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i]= nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = { 3,1,2,1,3,5,3};
        int val = 3;
        System.out.println(removeElement(nums,val));
        System.out.println(Arrays.toString(nums));

        int[] t = {2};
        int v2 = 2;
        System.out.println(removeElement(t,v2));
        System.out.println(Arrays.toString(t));
    }
}
