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
        while (lo <= hi) {
            int mid = lo + (hi-lo)/2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[lo] <= nums[mid]){
                if (nums[lo] <= target && target <= nums[mid]){
                    hi = mid -1;
                }
                else lo = mid + 1;
            }else {
                if (nums[mid] <= target && target <= nums[hi]){
                    lo = mid + 1;
                }else hi = mid - 1;
            }
        }
        return -1;
    }

    public static int searchTarget(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] >= nums[start]) {
                if (nums[mid] >= target && target >= nums[start]) {
                    end = mid;
                } else {
                    start = mid;
                }
            } else {
                if (nums[mid] <= target && target <= nums[end]) {
                    start = mid;
                } else {
                    end = mid;
                }
            }
        }
        if (nums[start] == target) {
            return start;
        }
        if (nums[end] == target) {
            return end;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {3,4,5,6,8,1,2};
        System.out.println(search(nums,2));
        System.out.println(search(nums,1));
        System.out.println(searchTarget(nums,2));
        System.out.println(searchTarget(nums,1));
        System.out.println(searchTarget(nums,0));


    }
}
