package com.alg;

/**
 * Created by HAU on 9/2/2017.
 */

/*Given an unsorted array of integers, find the length of longest increasing
        subsequence.

        For example,
        Given [10, 9, 2, 5, 3, 7, 101, 18],
        The longest increasing subsequence is [2, 3, 7, 101],
        therefore the length is 4. Note that there may be more than
        one LIS combination,
        it is only necessary for you to return the length.*/
public class Sol300_LongestIncreasingSubsequence {
    public static int lengthofLIS(int[] nums){
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] L = new int[n];
        for (int i = 0; i < n ; i++){
            L[i] = 1;
            for (int j = 0 ; j < i; j++){
                if (nums[j] < nums[i] && L[i] < 1 + L[j]){
                    L[i] = 1 + L[j];
                }
            }
        }
        int res = 1;
        for ( int i = 0; i < n; i++){
            if (res < L[i]){
                res = L[i];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {10,9,0,5,3,7,111,17};
        //assert 4 == lengthofLIS(nums);
        int[] n2 = { -3, 0};
        System.out.println(lengthofLIS(n2));
    }
}
