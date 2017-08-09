package com.alg;

/**
 * Created by HAU on 5/24/2017.
 */
/*
Given an array of integers and an integer k,
you need to find the total number of continuous subarrays whose sum equals to k.
*/
public class Sol560SubarraySumEqualsK {
    public static int subarraySum(int[] nums, int k){
        if (nums.length ==0) return 0;
        int sum = 0;
        int result = 0;
        int n = nums.length;
        for(int i = 0; i<n; i++){
            sum = nums[i];
            if(sum==k) result++;
            for (int j = i+1;j<n;j++){
                sum+= nums[j];
                if (sum==k) result++;

            }
        }
        return result;


    }

    public static void main(String[] args){
        int[] nums = {1,1,1};
        int k = 2;
        System.out.println(subarraySum(nums,k));
    }
}
