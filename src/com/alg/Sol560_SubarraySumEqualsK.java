package com.alg;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HAU on 5/24/2017.
 */
/*
Given an array of integers and an integer k,
you need to find the total number of continuous subarrays whose sum equals to k.
*/
public class Sol560_SubarraySumEqualsK {
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
    // hashmap, time O(n)
    // presum  - presum = target, 要找的是map里是否存在presum - target
    public static int subArraySum(int[] nums, int k){
        Map<Integer,Integer> preSum = new HashMap<>();
        // key: sum;  value: occurence of the sum
        preSum.put(0,1);
        int sum = 0;
        int count = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            count += preSum.getOrDefault(sum - k, 0);
            //if ( preSum.containsKey(sum - k)
            //{
            //  count += preSum.get(sum - k);
            //}
            //
            preSum.put(sum, preSum.getOrDefault(sum,0) + 1);
        }
        return count;
    }
}
