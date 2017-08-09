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
    public static int findPeakElement_n(int[] nums) {
        int n = nums.length;
        for (int i = 1; i < n; i++){
            if (nums[i] < nums[i-1]){
                return i - 1;
            }
        }
        return nums.length-1;
    }
    public static int findPeakElement(int[] nums) {
        int n = nums.length;
        int lo = 0;
        int hi = n - 1;
        while ( lo < hi){
            int mid = lo + (hi - lo)/2;
            if (nums[mid] > nums[mid+1]){
                hi = mid;
            }else{
                lo = mid + 1;
            }
        }
        return lo;
    }

    public static void main(String[] args) {
        int[] a = {1,2,3,1};
        int[] b = {1,2,3};
        System.out.println(findPeakElement(a));
        System.out.println(findPeakElement(b));
    }
}
