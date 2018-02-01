package com.alg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HAU on 2/1/2018.
 */
/*Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].

Note:
Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order.
Follow up:
What if the given array is already sorted? How would you optimize your algorithm?
What if nums1's size is small compared to nums2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?*/
public class Sol350_IntersectionOfTwoArraysII {
    // hashmap
    public static int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer,Integer> map = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        for(int i: nums1){
            if(map.containsKey(i)){
                map.put(i,map.get(i)+1);
            }else{
                map.put(i,1);
            }
        }
        for(int j: nums2){
            if(map.containsKey(j) && map.get(j) > 0){
                res.add(j);
                map.put(j,map.get(j) - 1);
            }
        }
        int[] ar = new int[res.size()];
        for(int i = 0; i < res.size(); i++){
            ar[i] = res.get(i);
        }
        return ar;
    }
}
