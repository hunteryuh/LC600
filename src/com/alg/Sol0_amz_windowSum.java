package com.alg;

/**
 * Created by HAU on 12/27/2017.
 */
/*Given an array of n integer, and a moving window(size k), move the window at each iteration from the start of the array,
find the sum of the element inside the window at each moving.*/
public class Sol0_amz_windowSum {
    public static int[] windowSum(int[] nums, int k){
        if ( nums == null || nums.length == 0 || k < 0)
            return new int[0];
        // may need to check what to do if nums.length < k
        int[] res = new int[nums.length - k + 1];
        for (int i = 0 ; i < k; i++){
            res[0] += nums[i];
        }
        for (int i = 1; i < res.length; i++){
            res[i] = res[i - 1] - nums[i-1]+nums[i-1+k];
        }
        return res;
    }
}
