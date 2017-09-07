package com.alg;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by HAU on 9/6/2017.
 */

/*
* Given an array of size n, find the majority element.
* The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element
always exist in the array.*/
public class Sol169_MajorityElement {
    public static int majorityElementsort(int[] nums){
        //sort
        Arrays.sort(nums);
        return nums[nums.length/2];
    }

    public static int majorityElement(int[] nums){
        //hashtable
        Map<Integer,Integer> map = new HashMap<>();
        int res = 0;
        for (int n: nums){
            if(!map.containsKey(n)){
                map.put(n,1);
            }else{
                map.put(n, map.get(n) + 1);
            }
            if(map.get(n) > nums.length / 2) {
                res = n;
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] n = {3,4,2,3,3,3,3};
        assert 3 == majorityElement(n);
    }
}
