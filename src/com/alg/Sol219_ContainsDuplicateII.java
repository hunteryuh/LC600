package com.alg;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by HAU on 2/9/2018.
 */
/*
Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j]
and the absolute difference between i and j is at most k.*/
public class Sol219_ContainsDuplicateII {
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for(int i = 0 ; i < nums.length ; i++){
            if ( i > k) set.remove(nums[i-k-1]);
            if(!set.add(nums[i])) return true;
        }
        return false;
    }
    // map
    public static boolean containsDupk(int[] nums,int k){
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(nums[i])){
                if(i - map.get(nums[i]) <= k) return true;
            }
            map.put(nums[i],i);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {2,3,4,1,6,3,5,2};
        int k = 4;
        int s = 3;
        System.out.println(containsNearbyDuplicate(nums,k));
        System.out.println(containsNearbyDuplicate(nums,s));
        System.out.println(containsDupk(nums,k));
        System.out.println(containsDupk(nums,s));


    }
}
