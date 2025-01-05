package com.alg;

import java.util.HashMap;
import java.util.Map;

/*
Given an array of integers and an integer k,
you need to find the total number of continuous subarrays whose sum equals to k.
*/
public class Sol560_SubarraySumEqualsK {
    // time: O(n^2)
    public static int subarraySum0(int[] nums, int k){
        if (nums.length == 0) return 0;
        int sum = 0;
        int result = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            sum = nums[i];
            if(sum == k) result++;
            for (int j = i + 1; j < n; j++){
                sum += nums[j];
                if (sum==k) result++;

            }
        }
        return result;
    }

    // time O(n^2)
    public int subarraySum1(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; start++) {
            int sum = 0;
            for (int end = start; end < nums.length; end++) {
                sum+=nums[end];
                if (sum == k)
                    count++;
            }
        }
        return count;
    }

    public static void main(String[] args){
        int[] nums = {1,1,1};
        int[] nums2 = {1, 2};
        int k = 2;
        int k2 = 2;
        System.out.println(subarraySum(nums2,k2));
    }
    // hashmap, time O(n), space O(n)
    // presum  - presum = target, 要找的是map里是否存在presum - target
    public static int subarraySum(int[] nums, int k) {
        Map<Integer,Integer> preSum = new HashMap<>(); // SumOccurrencesMap
        // key: any particular sum;  value: number of occurrence of the sum in the map
        preSum.put(0, 1);
        int sum = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            /*
            determine the number of times the sum sum−k has occurred already, since it will
            determine the number of times a subarray with sum k has occurred up to the current index.
             sumEnd = sumStart + k
             */
            count += preSum.getOrDefault(sum - k, 0);
            //if (preSum.containsKey(sum - k)
            //{
            //  count += preSum.get(sum - k);
            //}
            //
            preSum.put(sum, preSum.getOrDefault(sum,0) + 1);
        }
        System.out.println(preSum);
        return count;
    }

    // without initialize the map
    public static int subarraySum2(int[] nums, int k) {
        Map<Integer,Integer> preSum = new HashMap<>(); // SumOccurrencesMap
        int sum = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum == k) count++;
            count += preSum.getOrDefault(sum - k, 0);
            preSum.put(sum, preSum.getOrDefault(sum,0) + 1);
        }
        System.out.println(preSum);
        return count;
    }

}
