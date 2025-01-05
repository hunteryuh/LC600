package com.alg;

import java.util.*;

/**
 * Created by HAU on 7/21/2017.
 */
// https://leetcode.com/problems/contains-duplicate/
public class Sol217_ContainsDuplicate {
    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }

    public static boolean containsDuplicate2(int[] nums) {
        Set<Integer> set = new HashSet<>(nums.length); // with initial capacity
        for (int num : nums) {
            if (!set.add(num)) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsDup2(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1 ; i < nums.length; i++){
            if (nums[i-1] == nums[i]){
                return true;
            }

        }
        return false;

    }
    // java 8
    public boolean containsDup3(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        return nums.length != Arrays.stream(nums).distinct().count();
    }

    public static void main(String[] args) {
        int[] nums = {1,3,5,6,3};
        int[] n2 = { 2,3,4,7,-2,0};
        System.out.println(containsDuplicate(nums));
        System.out.println(containsDup2(nums));

        System.out.println(containsDuplicate(n2));
        System.out.println(containsDup2(n2));
    }
}
