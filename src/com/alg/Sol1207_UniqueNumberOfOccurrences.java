package com.alg;
/*
Given an array of integers arr, return true if the number of occurrences of each value in the array is unique, or false otherwise.



Example 1:

Input: arr = [1,2,2,1,1,3]
Output: true
Explanation: The value 1 has 3 occurrences, 2 has 2 and 3 has 1. No two values have the same number of occurrences.

Example 2:

Input: arr = [1,2]
Output: false

Example 3:

Input: arr = [-3,0,1,-3,1,1,1,-3,10,0]
Output: true

 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Sol1207_UniqueNumberOfOccurrences {
    // method 1
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int n: arr) {
            count.put(n, count.getOrDefault(n, 0) + 1);
        }
        Set<Integer> set = new HashSet<>(count.values());
        return count.size() == set.size();
    }

    // method 2 early exit
    public boolean uniqueOcc(int[] arr) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int n: arr) {
//            count.merge(n, 1, (a,b)-> a + b);
            count.merge(n, 1, Integer::sum);
        }
        Set<Integer> set = new HashSet<>();
        for (int freq: count.values()) {
            if(!set.add(freq)) {
                return false;
            }
        }
        return true;
    }
}
