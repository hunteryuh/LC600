package com.alg;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
Given two integer arrays of equal length target and arr.

In one step, you can select any non-empty sub-array of arr and reverse it. You are allowed to make any number of steps.

Return True if you can make arr equal to target, or False otherwise.



Example 1:

Input: target = [1,2,3,4], arr = [2,4,1,3]
Output: true
Explanation: You can follow the next steps to convert arr to target:
1- Reverse sub-array [2,4,1], arr becomes [1,4,2,3]
2- Reverse sub-array [4,2], arr becomes [1,2,4,3]
3- Reverse sub-array [4,3], arr becomes [1,2,3,4]
There are multiple ways to convert arr to target, this is not the only way to do so.
Example 2:

Input: target = [7], arr = [7]
Output: true
Explanation: arr is equal to target without any reverses.
Example 3:

Input: target = [1,12], arr = [12,1]
Output: true
Example 4:

Input: target = [3,7,9], arr = [3,7,11]
Output: false
Explanation: arr doesn't have value 9 and it can never be converted to target.
Example 5:

Input: target = [1,1,1,1,1], arr = [1,1,1,1,1]
Output: true
 */
public class Sol1460_MakeTwoArraysEqualByReversingSubArrays {
    public boolean canBeEqual(int[] target, int[] arr) {
        if (target.length != arr.length) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : target) {
            map.put(a, map.getOrDefault(a,0) + 1);
        }
        for (int b : arr) {
            if (!map.containsKey(b)) {
                return false;
            }
            map.put(b, map.get(b) - 1);
            if (map.get(b) < 0) {
                return false;
            }

        }
        return true;
    }

    public boolean canBeEqual2(int[] target, int[] arr) {
        int m = target.length;
        int n = arr.length;
        if (m != n) return false;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i< m; i++) {
            map.put(target[i], map.getOrDefault(target[i], 0) + 1);
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
        for (int i : map.keySet()) {
            if (map.get(i) != 0) {
                return false;
            }
        }
        return true;
    }
}
