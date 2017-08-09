package com.alg;

/**
 * Created by HAU on 7/14/2017.
 */

/*Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

        (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

        You are given a target value to search. If found in the array return its index, otherwise return -1.

        You may assume no duplicate exists in the array.*/

public class Sol33_SearchInRoatedSortedArray {
    public static int search(int[] nums, int target){
        int lo = 0;
        int hi = nums.length - 1;
        while ( lo <= hi){
            int mid = lo + (hi-lo)/2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[lo] <= nums[mid]){
                if (nums[lo] <= target && target <= nums[mid]){
                    hi = mid -1;
                }
                else lo = mid + 1;
            }else{
                if ( nums[mid] <= target && target <= nums[hi]){
                    lo = mid + 1;
                }else hi = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = { 3,4,5,6,8,1};
        System.out.println(search(nums,2));
        System.out.println(search(nums,1));


    }
}
