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

    // voting algorithm   Moore's Algorithm
    /*
    when we see 2 different numbers, we will remove both of them;
when we see 2 same numbers, we will increase the number of appears of current number.
the majority number will remove all other numbers and finally leaves as a result;
     */
    public int majorityElement3(int[] nums) {
        int major = nums[0];
        int count = 1;
        for(int i = 1; i < nums.length; i++){
            if(count == 0){
                count = 1;
                major = nums[i];
                continue;
            }
            if(nums[i] == major)
                count++;
            else
                count--;
        }
        return major;
    }

    public static void main(String[] args) {
        int[] n = {3,4,2,3,3,3,3};
        assert 3 == majorityElement(n);
    }
}
