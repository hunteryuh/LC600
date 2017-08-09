package com.alg;

import java.util.Arrays;

/**
 * Created by HAU on 7/21/2017.
 */
public class Sol561_ArrayPartition_I {
    public static int arrayPairSum(int[] nums){
        int sum = 0;
        Arrays.sort(nums);
        for(int i = 0; i < nums.length;i +=2){
            sum += nums[i];
        }
        return sum;

    }

    public static void main(String[] args) {
        int[] nums = { 2,4,1,5,3,8};
        int[] e = { 1,3,4,2};

        System.out.println(arrayPairSum(nums));
        System.out.println(arrayPairSum(e));
    }
}
