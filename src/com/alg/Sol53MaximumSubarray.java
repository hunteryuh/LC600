package com.alg;

/**
 * Created by HAU on 5/24/2017.
 */
/*
Find the contiguous subarray within an array (containing at least one number)
which has the largest sum.

For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
the contiguous subarray [4,-1,2,1] has the largest sum = 6.
*/
public class Sol53MaximumSubarray {
    public static int maxSubArray(int[] nums){
        if (nums.length ==0) return 0;
        int sum = nums[0];
        int result = nums[0];

        int n = nums.length;
        for(int i = 1; i<n; i++){
            sum = Math.max(sum+nums[i],nums[i]);
            result = Math.max(sum,result);
        }
        return result;


    }

    public static void main(String[] args){
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        //int k = 2;
        System.out.println(maxSubArray(nums));
    }
}
