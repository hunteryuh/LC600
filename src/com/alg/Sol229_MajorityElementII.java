package com.alg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HAU on 9/6/2017.
 */
/*
* Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
* The algorithm should run in linear time and in O(1) space.*/
public class Sol229_MajorityElementII {
    public static List<Integer> majorElement(int[] nums){
        //one counter, hashmap, O(n) space O(n) time
        Map<Integer,Integer> map = new HashMap<>();
        for (int n: nums){
            if(map.containsKey(n)){
                map.put(n, map.get(n) + 1);

            }else{
                map.put(n,1);
            }
        }
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer,Integer> entry : map.entrySet()) {
            if (entry.getValue() > nums.length / 3) {
                list.add(entry.getKey());
            }
        }
        return list;
    }
    public static List<Integer> majorityElement2(int[] nums){
        //摩尔投票法 Moore Voting
        if(nums.length == 0) {
            return new ArrayList<>();
        }
        int m1 = nums[0], m2=nums[0];
        int c1 = 0, c2 = 0;
        for (int i = 0; i<nums.length;i++){
/*            if (nums[i] == m1 || c1 == 0){
                m1 = nums[i];
                c1++;
            } else if (nums[i] == m2 || c2 ==0){
                m2 = nums[i];
                c2++;
            }else{
                c1--;
                c2--;
            }*/
            if (nums[i] == m1){
                c1++;
            }else if (nums[i] == m2){
                c2++;
            }else if (c1 == 0){
                c1++;
                m1 = nums[i];
            }else if (c2 == 0){
                c2++;
                m2 = nums[i];
            }else{
                c1--;c2--;
            }
        }
        c1 = c2 = 0;
        for (int n: nums){
            if ( n == m1) c1++;
            else if (n == m2) c2++;
        }
        List<Integer> list = new ArrayList<>();
        if (c1 > nums.length /3 ) list.add(m1);
        if (c2 > nums.length /3 ) list.add(m2);
        return list;

    }

    public static void main(String[] args) {
       // int[] n = { 2,2,1,0,1};
        //System.out.println(majorElement(n));
        int[] w = {1,2,2,3,2,1,1,3};
        System.out.println(majorityElement2(w));
    }
}
