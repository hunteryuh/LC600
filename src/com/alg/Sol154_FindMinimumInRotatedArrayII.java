package com.alg;
/*
Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,4,4,5,6,7] might become:

[4,5,6,7,0,1,4] if it was rotated 4 times.
[0,1,4,4,5,6,7] if it was rotated 7 times.
Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].

Given the sorted rotated array nums that may contain duplicates, return the minimum element of this array.

You must decrease the overall operation steps as much as possible.



Example 1:

Input: nums = [1,3,5]
Output: 1
Example 2:

Input: nums = [2,2,2,0,1]
Output: 0


Constraints:

n == nums.length
1 <= n <= 5000
-5000 <= nums[i] <= 5000
nums is sorted and rotated between 1 and n times.


Follow up: This problem is similar to Find Minimum in Rotated Sorted Array, but nums may contain duplicates. Would this affect the runtime complexity? How and why?
 */
public class Sol154_FindMinimumInRotatedArrayII {
    //  failed for {3,3,1,3}
//    public int findMin(int[] nums) {
//        int start = 0;
//        int end = nums.length - 1;
//        int last = nums[end];
//        while (start + 1 < end) {
//            int mid = start + (end - start)/2;
//            if (nums[mid] == last) {
//                end = mid;  // wrong
//            } else if (nums[mid] < last) {
//                end = mid;
//            } else {
//                start = mid;
//            }
//        }
//        System.out.println("start is " +start);
//        System.out.println("end is " + end);
//        if (nums[start] <= nums[end]) {
//            return nums[start];
//        }
//        return nums[end];
//    }

    public static void main(String[] args) {
        int[] nums = { 3,3,1,3};
        Sol154_FindMinimumInRotatedArrayII ss =new Sol154_FindMinimumInRotatedArrayII();
        System.out.println(ss.findMin2(nums));
    }

    // https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/solution/
    public int findMin2(int[] nums) {
        int start = 0;
        int end = nums.length -1;

        while (start < end) {

            int mid = start + (end-start)/2;
            if (nums[mid] > nums[end]) {
                start = mid + 1;
            } else if (nums[mid] < nums[end]) {
                end = mid;
            } else {
                end--;
            }
        }
        return nums[start];
    }

    // compare to nums[end] where end may change after a loop
    // To summarize, this algorithm differs to the classical binary search algorithm in two parts:
    //
    //    We use the upper bound of search scope as the reference for the comparison with the pivot element, while in the classical binary search the reference would be the desired value.
    //    When the result of comparison is equal (i.e. Case #3), we further move the upper bound, while in the classical binary search normally we would return the value immediately.
    public int findMin0(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start)/2;
            if (nums[mid] == nums[end]) {
                end--;
            } else if (nums[mid] < nums[end]) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[start] <= nums[end]) {
            return nums[start];
        }
        return nums[end];
    }
}
