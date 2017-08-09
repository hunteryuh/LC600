package com.alg;

import java.util.*;

/**
 * Created by HAU on 7/21/2017.
 */
public class Sol217_ContainsDuplicate {
    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0 ; i < nums.length; i++){
            if (set.contains(nums[i])){
                return true;
            }
            set.add(nums[i]);
        }
        return false;

    }

    public static boolean containsDup2(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1 ; i < nums.length; i++){
            if (nums[i-1]==nums[i]){
                return true;
            }

        }
        return false;

    }

    public static void main(String[] args) {
        int[] nums = { 1,3,5,6,3};
        int[] n2 = { 2,3,4,7,-2,0};
        System.out.println(containsDuplicate(nums));
        System.out.println(containsDup2(nums));

        System.out.println(containsDuplicate(n2));
        System.out.println(containsDup2(n2));
    }
}
