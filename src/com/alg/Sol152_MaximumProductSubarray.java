package com.alg;

/**
 * Created by HAU on 9/6/2017.
 */
/*
Find the contiguous subarray within an array (containing at least
        one number) which has the largest product.

        For example, given the array [2,3,-2,4],
        the contiguous subarray [2,3] has the largest product = 6.*/
public class Sol152_MaximumProductSubarray {
    public static int maxProduct(int[] nums){
        int n = nums.length;
        int[] maxp = new int[n];
        int[] minp = new int[n];
        maxp[0] = minp[0] = nums[0];
        int res = nums[0];
        //int[] res = new int[n];
        //res[0] = nums[0];
        for (int i = 1; i < n ; i++){
            maxp[i]=minp[i] = nums[i];
            if (nums[i]  > 0 ){
                maxp[i] = Math.max(maxp[i],nums[i] * maxp[i-1]);
                minp[i] = Math.min(minp[i],nums[i] * minp[i-1]);
            }else{
                maxp[i] = Math.max(maxp[i], nums[i] * minp[i-1]);
                minp[i] = Math.min(minp[i], nums[i] * maxp[i-1]);
            }
            res = Math.max(res, maxp[i]);
            //res[i] = maxp[i];
        }
        //int m = res[0];
        //for (int i = 0 ; i < n ; i++){
        //    if ( m < res[i]) m = res[i];
        //}
        //return m;
        return res;
    }

    public static void main(String[] args) {
        int[] n= {2,3,-2,4};
        System.out.println(maxProduct(n));
    }
}
