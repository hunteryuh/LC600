package com.alg;

/**
 * Created by HAU on 7/18/2017.
 */

/*A peak element is an element that is greater than its neighbors.

        Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.

        The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

        You may imagine that num[-1] = num[n] = -∞.

        For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index
        number 2.

        click to show spoilers.

        Note:
        Your solution should be in logarithmic complexity.*/
public class Sol162_FindPeakElement {
    // O(n) https://leetcode.com/problems/find-peak-element/editorial/
    public static int findPeakElement_n(int[] nums) {
        int n = nums.length;
        for (int i = 1; i < n; i++){
            if (nums[i] < nums[i-1]){
                return i - 1;
            }
        }
        return nums.length-1;
    }

    // O(logn) https://leetcode.com/problems/find-peak-element/editorial/
    public static int findPeakElement(int[] nums) {
        int n = nums.length;
        int lo = 0;
        int hi = n - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo)/2;
            if (nums[mid] > nums[mid+1]){
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    public static int findPeak(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > nums[mid + 1]) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[start] > nums[end]) {
            return start;
        }
        return end;
    }

    // recursive binary search
    public int findPeakBinary(int[] nums) {
        return search(nums, 0, nums.length - 1);
    }
    private int search(int[] nums, int left, int right) {
        if (left == right) return left;
        int mid = (left + right) /2;
        if (nums[mid] < nums[mid + 1]) {
            return search(nums, mid + 1, right);
        } else {
            return search(nums, left, mid);
        }
    }

    public static void main(String[] args) {
        int[] a = {1,2,3,1};
        int[] b = {1,2,3};
        System.out.println(findPeakElement(a));
        System.out.println(findPeakElement(b));
        System.out.println(findPeak(a));
        System.out.println(findPeak(b));
    }
}
