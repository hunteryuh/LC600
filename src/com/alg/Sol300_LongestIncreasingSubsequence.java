package com.alg;

import java.util.Arrays;

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
        System.out.println(lengthOfLIS_bs(nums));
        System.out.println(lengthofLIS(n2));
        System.out.println(lengthofLIS2(n2));
    }

    //Dynamic Programming
    /*dp[i] represents the length of the longest increasing subsequence possible considering the array elements upto the i^{th}
​​  index only ,by necessarily including the i^{th}i
​th ​ element.
dp[i]=max(dp[j])+1,∀0≤j<i

At the end, the maximum out of all the dp[i]'s to determine the final result.
LIS_​length
​​ =max(dp[i]),∀0≤i<n*/
    public static int lengthofLIS2(int[] nums){
        if ( nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int res = 1;
        for (int i = 1; i < dp.length; i++){
            int max = 0;
            for (int j = 0; j < i; j++){
                if (nums[i] > nums[j]){
                    max = Math.max(max,dp[j]);
                }
            }
            dp[i] = max + 1;
            res = Math.max(dp[i], res);

        }
        return res;
    }

    // method 3, dynamic programming with binary search
    /*Note: dpdp array does not result in longest increasing subsequence,
    but length of dpdp array will give you length of LIS.*/
    public static int lengthOfLIS_bs(int[] nums){
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num: nums){
            int i = Arrays.binarySearch(dp,0,len,num);
            if (i < 0){
                i = -(i + 1);
            }
            dp[i] = num;
            if (i == len){
                len++;
            }
        }
        return len;
    }
}
