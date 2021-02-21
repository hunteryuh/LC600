package com.alg;

import java.util.Arrays;

/**
 * Created by HAU on 1/28/2018.
 */
/*Given an array of integers sorted in ascending order, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].*/
public class Sol34_SearchForARange {
    // linear scan, O(n)
    public static int[] searchRange(int[] nums, int target) {
        int[] res = {-1, -1};
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == target){
                res[0] = i;
                break;
            }
        }
        if(res[0] == -1) return res;
        for(int j = nums.length - 1; j >=0; j--){
            if(res[j] == target){
                res[1] = j;
                break;
            }
        }
        return res;
    }

    public static int[] searchRange2(int[] nums, int target) {
        int[] targetRange = {-1, -1};
        int left = insertIndex(nums,target,true);
        if( left == nums.length || nums[left] != target){
            return targetRange;
        }
        targetRange[0] = left;
        targetRange[1] = insertIndex(nums, target, false ) - 1;
        return targetRange;
    }

    private static int insertIndex(int[] nums, int target, boolean b) {
        int lo = 0;
        int hi = nums.length - 0;  // -1 does not work for single element array
        while( lo < hi){
            int mid = ( lo + hi) / 2;
            if ( nums[mid] > target || (b && nums[mid] == target)){
                hi = mid;
            }else{
                lo = mid + 1;
            }
        }
        return lo;
    }

    public static int[] searchRange3(int[] nums, int target) {
        int[] res = {-1, -1};
        if (nums == null || nums.length == 0) {
            return res;
        }
        int left = binarySearch(nums, target, true);
        int right = binarySearch(nums, target, false);
        return new int[]{left, right};

    }

    private static int binarySearch(int[] nums, int target, boolean first) {
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < target) {
                start = mid;
            } else if (nums[mid] > target) {
                end = mid;
            } else /*nums[mid] == target*/ if (first) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (first) {
            if (nums[start] == target) {
                return start;
            }
            if (nums[end] == target) {
                return end;
            }
        } else {
            if (nums[end] == target) {
                return end;
            }
            if (nums[start] == target) {
                return start;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,6,6,10};
        int[] res = searchRange2(nums,6);
        System.out.println(Arrays.toString(res));
        int[] nums2 = {1};
        int[] res2 = searchRange2(nums,1);
        System.out.println(Arrays.toString(res2));

        int[] res3 = searchRange3(nums,6);
        int[] res4 = searchRange3(nums2,1);
        System.out.println(Arrays.toString(res3));
        System.out.println(Arrays.toString(res4));
    }
}
