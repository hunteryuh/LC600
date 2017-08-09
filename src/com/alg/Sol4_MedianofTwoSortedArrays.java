package com.alg;

/**
 * Created by HAU on 6/29/2017.
 */

/*
There are two sorted arrays nums1 and nums2 of size m and n respectively.

        Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

        Example 1:
        nums1 = [1, 3]
        nums2 = [2]

        The median is 2.0
        Example 2:
        nums1 = [1, 2]
        nums2 = [3, 4]

        The median is (2 + 3)/2 = 2.5
*/

public class Sol4_MedianofTwoSortedArrays {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //double res;
        int m = nums1.length;
        int n = nums2.length;
        int[] newnum = new int[m+n];
        int i = 0, j = 0;
        int k;
        for ( k = 0; k <= (m+n)/2 ; k++) {
            if (i == m ) newnum[k] = nums2[j++];
            else if (j == n ) newnum[k] = nums1[i++];
            else if (nums1[i] < nums2[j]) newnum[k] = nums1[i++];
            else newnum[k] = nums2[j++];
        }

        return (m+n)%2 == 1 ? newnum[k-1] : (newnum[k-2]+newnum[k-1])/2.0;
    }

    public static void main(String[] args) {
        int[] num1 = { 1,2};
        int[] num2 = { 3,4};
        int[] num3 = {5};
        System.out.println(findMedianSortedArrays(num1,num2));
        System.out.println(findMedianSortedArrays(num2,num3));
    }
}
