package com.alg.other;
/*
Given a sequence of positive/0 integers and a positive/0 integer total target, return whether a continuous sequence of integers sums up to target.

Example
[1, 3, 1, 4, 23], 8 : True (because 3 + 1 + 4 = 8)
[1, 3, 1, 4, 23], 7 : False
[], 0: False
[], 3: False

# element in sequence are >= 0
# target >= 0
 */
public class FB_ContiguousSubarraySumPositive {
    public boolean hasContinuousSum(int[] nums, int target) {
        if (target == 0 && nums.length == 0) {
            return false;
        }

        int left = 0;
        int currentSum = 0;

        for (int right = 0; right < nums.length; right++) {
            currentSum += nums[right];

            while (currentSum > target && left <= right) {
                currentSum -= nums[left];
                left++;
            }

            if (currentSum == target) {
                return true;
            }
        }

        return false;
    }


    // is it right?
    public boolean hasContinuousSum2(int[] nums, int target) {
        if (target == 0 && nums.length == 0) {
            return false;
        }

        int left = 0;
        int currentSum = 0;
        int right = 0;

        while (true) {
            // Add the next element to the current sum
            if (right < nums.length) {
                currentSum += nums[right];
                right++;
            }

            // If the current sum exceeds the target, subtract the leftmost element and move the left pointer
            if (currentSum > target /*&& left < right*/) {
                currentSum -= nums[left];
                left++;
            }

            // If the current sum equals the target, return true
            if (currentSum == target) {
                return true;
            }

            // If the current sum is less than the target and we have exhausted all elements, break the loop
            if (currentSum < target && right == nums.length) {
                break;
            }
        }

        // If the loop completes without finding a matching sum, return false
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 4, 15};
        int[] nums1 = {1, 3, 1, 4, 23}; int target1 = 23;
        int[] nums2 = {1, 3, 1, 4, 23}; int target2 = 8;
        int[] nums3 = {}; int target3 = 0;
        FB_ContiguousSubarraySumPositive ff = new FB_ContiguousSubarraySumPositive();
        System.out.println(ff.hasContinuousSum2(nums1, target1));
        System.out.println(ff.hasContinuousSum2(nums2, target2));
        System.out.println(ff.hasContinuousSum2(nums3, target3));
    }
}
