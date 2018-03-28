package com.alg;

/**
 * Created by HAU on 5/25/2017.
 */

/*
Note: This is an extension of House Robber.

After robbing those houses on that street, the thief
has found himself a new place for his thievery so that he will not get too much attention.
This time, all houses at this place are arranged in a circle. That means the first house is the neighbor of the last one.
Meanwhile, the security system for these houses remain the same as for those in the previous street.

Given a list of non-negative integers representing the amount of money of each house,
determine the maximum amount of money you can rob tonight without alerting the police.
*/
public class Sol213_House_RobberII_Circle {
    public static int rob(int[] nums) {
        if(nums == null || nums.length==0) return 0;
        int n = nums.length;
        if (n==1) return nums[0];
        int r1 = subrob(nums,0,n-1);
        int r2 = subrob(nums,1,n);
        return Math.max(r1,r2);

    }
    private static int subrob(int[] nums, int lo, int hi){

        int n = nums.length;
        int sum1=0;
        int sum2=0;
        for(int i=lo;i<hi;i++){
            if (i%2==0)
                sum1 = Math.max(sum1+nums[i],sum2);
            else
                sum2 = Math.max(sum2+nums[i],sum1);
        }

        return Math.max(sum1,sum2);

    }
    private static int subrob2(int[] nums, int lo, int hi){

        int n = nums.length;
        int pre = 0;
        int cur = 0;
        for(int i = lo; i < hi; i++){
            int temp = Math.max(pre+nums[i],cur);
            pre = cur;
            cur = temp;

        }

        return Math.max(pre,cur);

    }

    public static void main(String[] args){
        int[] nums = {1,2,3,4,5,1,2,3,4,5};
        //int k = 2;
        System.out.println(rob(nums));
    }
}
