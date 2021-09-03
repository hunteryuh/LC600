package com.alg;

/*
Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,2,4,5,6,7] might become:

[4,5,6,7,0,1,2] if it was rotated 4 times.
[0,1,2,4,5,6,7] if it was rotated 7 times.
Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].

Given the sorted rotated array nums, return the minimum element of this array.
 */
public class Sol153_FindMinimumInRotatedSortedArray {
    public static int findMin(int[] nums) {
        // find the first element that is less or equal to the last item
        int start = 0;
        int end = nums.length - 1;
        int last = nums[nums.length - 1];
        while (start + 1 < end) {
            int mid = start + (end - start)/2;
            if (nums[mid] == last) {
                end = mid;
            } else if (nums[mid] < last) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[start] <= last) {
            return nums[start];
        }
        return nums[end];
    }

    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2};
        int[] nums2 = {4,5,6,7};
        int[] nums3 = {4,7,0,1,2,3,4};
        System.out.println(findMin(nums));
        System.out.println(findMin(nums2));
        System.out.println(findMin(nums3));
    }
}
