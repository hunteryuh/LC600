package com.alg;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
Given an array of integers arr, a lucky integer is an integer which has a frequency in the array equal to its value.

Return a lucky integer in the array. If there are multiple lucky integers return the largest of them. If there is no lucky integer return -1.



Example 1:

Input: arr = [2,2,3,4]
Output: 2
Explanation: The only lucky number in the array is 2 because frequency[2] == 2.
Example 2:

Input: arr = [1,2,2,3,3,3]
Output: 3
Explanation: 1, 2 and 3 are all lucky numbers, return the largest of them.
Example 3:

Input: arr = [2,2,2,3,3]
Output: -1
Explanation: There are no lucky numbers in the array.
Example 4:

Input: arr = [5]
Output: -1
 */
public class Sol1394_FindLuckyIntegerInAnArray {

    public static int findLucky(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
//        for (int a: arr) {
//            if (map.containsKey(a)) {
//                map.put(a, map.get(a) + 1);
//            } else {
//                map.put(a, 1);
//            }
//        }

        for (int a: arr) {
            map.put(a, map.getOrDefault(a, 0) + 1);
        }
        int ans = -1;
        for (int n: map.keySet()) {
            if (n == map.get(n)) {
                ans = Math.max(n, ans);
            }
        }
//        for(Map.Entry entry: map.entrySet()) {
//            if (entry.getKey() == entry.getValue()) {
//                ans = Math.max(ans, (Integer) entry.getKey());
//            }
//        }
        return ans;
    }

    public static void main(String[] args) {
        int[] a = {2,2,3,4};
        int[] b = {1,2,2,3,3,3};
        System.out.println(findLucky(a));
        System.out.println(findLucky(b));
    }
}
