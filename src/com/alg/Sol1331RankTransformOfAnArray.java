package com.alg;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
Given an array of integers arr, replace each element with its rank.

The rank represents how large the element is. The rank has the following rules:

Rank is an integer starting from 1.
The larger the element, the larger the rank. If two elements are equal, their rank must be the same.
Rank should be as small as possible.


Example 1:

Input: arr = [40,10,20,30]
Output: [4,1,2,3]
Explanation: 40 is the largest element. 10 is the smallest. 20 is the second smallest. 30 is the third smallest.
Example 2:

Input: arr = [100,100,100]
Output: [1,1,1]
Explanation: Same elements share the same rank.
Example 3:

Input: arr = [37,12,28,9,100,56,80,5,12]
Output: [5,3,4,2,8,6,7,1,3]
 */
public class Sol1331RankTransformOfAnArray {
    public static int[] arrayRankTransform(int[] arr) {
        int n = arr.length;
        int[] temp = Arrays.copyOf(arr,n);
        Arrays.sort(temp);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(temp[i])) {
                map.put(temp[i], map.size() + 1 );  // map.size() to get the number of unique numbers till now. +1 as the rank
            }
        }
        for (int i = 0 ; i < n; i++) {
            arr[i] = map.get(arr[i]);
        }
        return arr;
    }

    public static int[] arrayRankTransform2(int[] arr) {
        int n = arr.length;
        int[] temp = Arrays.copyOf(arr,n);
        Arrays.sort(temp);
        Map<Integer, Integer> map = new HashMap<>();
        int rank = 1;
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(temp[i])) {
                map.put(temp[i], rank++ );  // map.size() to get the number of unique numbers till now. +1 as the rank
            }
        }
        for (int i = 0 ; i < n; i++) {
            arr[i] = map.get(arr[i]);
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] a = { 2,4,5,7,3,4,5};
        int[] x = arrayRankTransform2(a);   // [1, 3, 4, 5, 2, 3, 4]
        System.out.println(Arrays.toString(x));
    }
}
