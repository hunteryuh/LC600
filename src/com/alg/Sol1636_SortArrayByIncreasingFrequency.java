package com.alg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Given an array of integers nums, sort the array in increasing order based on the frequency of the values. If multiple values have the same frequency, sort them in decreasing order.

Return the sorted array.

Example 1:

Input: nums = [1,1,2,2,2,3]
Output: [3,1,1,2,2,2]
Explanation: '3' has a frequency of 1, '1' has a frequency of 2, and '2' has a frequency of 3.
Example 2:

Input: nums = [2,3,1,3,2]
Output: [1,3,3,2,2]
Explanation: '2' and '3' both have a frequency of 2, so they are sorted in decreasing order.
Example 3:

Input: nums = [-1,1,-6,4,5,-6,1,4,1]
Output: [5,-1,4,4,-6,-6,1,1,1]
 */
public class Sol1636_SortArrayByIncreasingFrequency {
    public int[] frequencySort(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num:nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet()); // add these entry set to a list; can also be done in the above for loop
        Collections.sort(list, (a, b) -> a.getValue() == b.getValue()? (b.getKey()-a.getKey()) : a.getValue() - b.getValue());

        int[] res = new int[nums.length];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry: list) {
            int freq = entry.getValue();
            while (freq > 0)  {
                res[i] = entry.getKey();
                freq--;
                i++;
            }
        }
        return res;
    }
}
