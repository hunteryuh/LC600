package com.alg;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by HAU on 6/26/2017.
 */
/*
Given an array of integers, return indices of the two numbers such that they add up to a specific target.

        You may assume that each input would have exactly one solution, and you may not use the same element twice.

        Example:
        Given nums = [2, 7, 11, 15], target = 9,

        Because nums[0] + nums[1] = 2 + 7 = 9,
        return [0, 1].*/
public class Sol1_TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2)
            return null;
        int[] res = new int[2];
        int n = nums.length;
        //Arrays.sort(nums); // cannot sort, otherwise indices will be changed

        // to get O(n) time, need to use Hashmap
        HashMap<Integer,Integer> map = new HashMap<>();
        for ( int i = 0; i < n; i++){
            map.put(nums[i], i);
        }
        for (int i = 0; i < n; i++){
            if (map.containsKey(target - nums[i]) && map.get(target - nums[i]) != i) {
                res[0] = i;
                res[1] = map.get(target - nums[i]);
                break;
            }
        }
        return res;
    }

    // O(n^2)
    public static int[] twoSum_brute(int[] nums, int target){
        for (int i = 0; i < nums.length; i ++){
            for (int j = i + 1; j < nums.length;j++)
                if (nums[j] + nums[i] == target) {
                    return new int[] {i,j};
                }
        }
        throw new IllegalArgumentException("no solution");
    }

    // O(nlogn)
    public int[] twoSum3(int[] nums, int target) {
        int[] ctl = nums.clone();
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        while (nums[left] + nums[right] != target) {
            if (nums[left] + nums[right] > target) {
                right--;
            } else {
                left++;
            }
        }
        int[] res = new int[2];
        for (int i = 0; i < ctl.length; i++) {
            if (ctl[i] == nums[left]) {
                res[0]= i;
                break;
            }
        }
        for (int i = ctl.length - 1; i >= 0; i--) {
            if (ctl[i] == nums[right]) {
                res[1] = i;
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int nums[] = {3,2,4};
        int target = 6;
        int res[] = twoSum2(nums,target);

        System.out.println(Arrays.toString(res));
        //System.out.println(Arrays.toString(twoSum_brute(nums,target)));
    }

    // hashmap + 1 loop
    public static int[] twoSum2(int[] nums, int target){
        int[] res = new int[2];
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            if (map.containsKey(target - nums[i])){
                res[0] = map.get(target - nums[i]);
                res[1] = i ;
                return res;
            }
            map.put(nums[i], i);
        }
        return res;
    }
}
