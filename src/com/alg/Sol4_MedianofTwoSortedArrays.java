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
        //double res; not O(log(min(m,n)) time though
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
        System.out.println(findMedianlogn(num1,num3));
    }

    public static double findMedianlogn(int[] a, int[] b){
        int total = a.length + b.length;
        if ( total % 2 == 0){
            return (findkth(total/2, a,b,0,0) + findkth(total/2 + 1, a,b,0,0) )/2.0;
        }else{
            return findkth(total/2 + 1, a,b,0,0);
        }
    }

    private static double findkth(int k, int[] a, int[] b, int s1, int s2) {
        if ( s1 >= a.length) return b[s2+k-1];
        if ( s2 >= b.length) return a[s1+k-1];
        if ( k == 1){
            return Math.min(a[s1], b[s2]);
        }
        int m1 = s1 + k/2 - 1;
        int m2 = s2 + k/2 - 1;
        int mid1 = m1 < a.length ? a[m1] : Integer.MAX_VALUE;
        int mid2 = m2 < b.length ? b[m2] : Integer.MAX_VALUE;
        if ( mid1 < mid2){
            return findkth(k-k/2,a,b, m1 + 1, s2);
        }else{
            return findkth(k-k/2, a,b,s1, m2 + 1);
        }

    }

}
