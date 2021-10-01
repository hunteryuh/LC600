package com.alg;

/**
 * Created by HAU on 8/21/2017.
 */
/*Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

        You may assume no duplicates in the array.

        Here are few examples.
        [1,3,5,6], 5 → 2
        [1,3,5,6], 2 → 1
        [1,3,5,6], 7 → 4
        [1,3,5,6], 0 → 0*/
public class Sol35_SearchInsertPosition {
    public static int searchInsert(int[] nums, int target){
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi){
            int mid = lo + (hi-lo)/2;
            if(target > nums[mid]) lo = mid + 1;
            else if (target < nums[mid]) hi = mid - 1;
            else return mid;
        }
        return lo;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,5,8};
        int target = 5;
        System.out.println(searchInsert(nums,target));
    }

    public int searchInsert2(int[] nums, int target) {
        // brute force
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {
                return i;
            }
        }
        return nums.length;
    }

    public int searchInsert3(int[] nums, int target){
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > target) {
                end = mid;
            } else if (nums[mid] < target) {
                start = mid;
            } else {
                return mid;
            }
        }
        if (nums[start] >= target) {
            return start;
        }
        if (nums[end] >= target) {
            return end;
        }
        return end + 1;
    }
}
