package com.alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by HAU on 7/6/2017.
 */
/*
Given an array S of n integers, find three integers in S such that
the sum is closest to a given number, target. Return the sum of the three integers.
You may assume that each input would have exactly one solution.

        For example, given array S = {-1 2 1 -4}, and target = 1.

        The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
*/

public class Sol16_3SumClosest {

    public static int threeSumClosest_timelimit(int[] nums, int target) {
        int n = nums.length;
        //List<List<Integer>> list = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        int res =Integer.MAX_VALUE;
        for (int i = 0; i < n - 2; i++){

            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                res = Math.abs(res - target) < Math.abs(nums[i] + nums[j]+ nums[k] - target)? res:nums[i] + nums[j]+ nums[k];
                if (res == target) {
                    return target;
                }else if ( nums[i] + nums[j] + nums[k] < target){
                    j++;
                }else if ( nums[i] + nums[j] + nums[k] > target ){
                    k--;
                }
            }

        }
        return res;
    }
    public static int threeSumClosest(int[] nums, int target) {
        int n = nums.length;
        int res = nums[0] + nums[1] + nums[2];
        Arrays.sort(nums);
        for (int i = 0; i < n - 2; i++){

            int j = i + 1;
            int k = n - 1;

            while ( j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if ( Math.abs(sum- target) < Math.abs(res - target) ){
                    res = sum;
                    if (res == target) return res;
                    }

                if (sum < target){
                    j++;
                }else k--;
            }

        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = { -1,-4,1,2};
        int target = 1;
        System.out.println(threeSumClosest(nums,target));
        int[] n2 = { -1,-1,1,1,3};
        int target2 = -1;
        System.out.println(threeSumClosest(n2,target2));
    }
}
