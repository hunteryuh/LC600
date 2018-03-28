package com.alg;

/**
 * Created by HAU on 5/25/2017.
 */

/*

You are a professional robber planning to rob houses along a street.
Each house has a certain amount of money stashed,
the only constraint stopping you from robbing each of them is that
 adjacent houses have security system connected and it will automatically
 contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house,
determine the maximum amount of money you can rob tonight without alerting the police.
*/
public class Sol198_House_Robber {
    public static int rob198(int[] nums) {
        if(nums.length==0) return 0;
        int n = nums.length;
        int sum1=0;
        int sum2=0;
        for(int i=0;i<n;i++){
            if (i%2==0)
                sum1 = Math.max(sum1+nums[i],sum2);
            else
                sum2 = Math.max(sum2+nums[i],sum1);
        }

        return Math.max(sum1,sum2);

    }

    public static void main(String[] args){
        int[] nums = {1,2,3,4,5,1,2,3,4,5};
        //int k = 2;
        System.out.println(rob198(nums));
    }
}