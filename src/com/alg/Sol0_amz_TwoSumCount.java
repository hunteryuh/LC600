package com.alg;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by HAU on 1/14/2018.
 */
/* count how many pairs of numbers that sum to the target*/
public class Sol0_amz_TwoSumCount {
    public static int countPairs(int[] nums, int target){
        if ( nums == null || nums.length == 0){
            return 0;
        }
        int count = 0;
        Set<Integer> set = new HashSet<>();
        for(int n: nums){
            set.add(n);
        }
        for(int n: nums){
            if(set.contains(target-n)){
                count++;
            }
        }
        return count/2;
    }

    public static void main(String[] args) {
        int[] nums = { 2,4,6,0,1,7,-2,10,3};
        int res = countPairs(nums,8);
        System.out.println(res);
    }
}
