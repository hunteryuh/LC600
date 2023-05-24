package com.alg;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by HAU on 1/14/2018.
 */
/* count how many pairs of numbers that sum to the target*/
public class Sol0_amz_TwoSumCount {
    public int countPairs(int[] nums, int target){
        if ( nums == null || nums.length == 0){
            return 0;
        }
        int count = 0;
        Set<Integer> set = new HashSet<>();
        for (int n: nums) {
            set.add(n);
        }
        for(int n: nums) {
            if(set.contains(target-n)){
                count++;
            }
        }
        return count/2;
    }

    public static void main(String[] args) {
        int[] nums = { 2,4,6,0,1,7,-2,10,3};
        Sol0_amz_TwoSumCount s = new Sol0_amz_TwoSumCount();

        int res = s.countPairs2(nums,8);
        System.out.println(res);
        int[] nums2 = {1,7,4,4,4,1};
        System.out.println(s.countPairs2(nums2, 8));

    }
    
    public int countPairs2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap();
        for(int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                if (nums[i] != target - nums[i]) {
                    res += map.get(target - nums[i]);
                } else {
                    res += map.get(nums[i]) - 1;  // for each item, count the occurance except for itself in the "double" count
                }
            }
        }
        return res/2;  // every one was counted twice
    }
}
