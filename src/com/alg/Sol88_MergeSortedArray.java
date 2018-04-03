package com.alg;

/**
 * Created by HAU on 6/6/2017.
 */

/*
Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
*/
public class Sol88_MergeSortedArray {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m-1;
        int j = n-1;
        int k = m+n-1;  // key: assign values from the largest entry to avoid entry reevaluation
        while (i>=0 && j>=0){
            if(nums1[i]>nums2[j]) nums1[k--]=nums1[i--];
            else nums1[k--] = nums2[j--];
        }
        while (k>=0 && j>=0) nums1[k--]=nums2[j--];
    }

    public static void main(String[] args) {
        int[] a1 = new int[20];
        a1[0]=3; a1[1]=5;a1[2]=7;a1[3]=9;

        int[] a2 = {1,2,4,7,8};
        merge(a1,4,a2,5);
        for(int t: a1){
            System.out.print(t+" ");
        }
    }
}
