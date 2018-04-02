package com.alg;

/**
 * Created by HAU on 5/26/2017.
 */
/*
Given an array with n objects colored red, white or blue, sort them so that objects of
the same color are adjacent, with the colors in the order red, white and blue.
Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue
respectively.

        Note:
        You are not suppose to use the library's sort function for this problem.
*/
public class Sol75_SortColors {
    public static void sortColors(int[] nums) {


        int lo = 0;
        int hi = nums.length-1;
        int i = 0;
        while(i<=hi){
            if(nums[i]==0) swap(nums,i++,lo++);
            else if (nums[i]==2) swap(nums,i,hi--);
            else if(nums[i]==1) i++;

        }   // one pass, O(n)

    }
    private static void swap(int[] a, int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        int[] colors = {1,2,1,0,1,0,2,0,2,0,1,0,2,0};
        sortColors(colors);
        for(int s: colors){
            System.out.print(s+ " ");
        }
    }
}
