package com.alg;

import java.util.HashMap;
import java.util.Map;

/*
Given an integer array arr, and an integer target, return the number of tuples i, j, k such that i < j < k and arr[i] + arr[j] + arr[k] == target.

As the answer can be very large, return it modulo 109 + 7.



Example 1:

Input: arr = [1,1,2,2,3,3,4,4,5,5], target = 8
Output: 20
Explanation:
Enumerating by the values (arr[i], arr[j], arr[k]):
(1, 2, 5) occurs 8 times;
(1, 3, 4) occurs 8 times;
(2, 2, 4) occurs 2 times;
(2, 3, 3) occurs 2 times.
Example 2:

Input: arr = [1,1,2,2,2,2], target = 5
Output: 12
Explanation:
arr[i] = 1, arr[j] = arr[k] = 2 occurs 12 times:
We choose one 1 from [1,1] in 2 ways,
and two 2s from [2,2,2,2] in 6 ways.
Example 3:

Input: arr = [2,1,3], target = 6
Output: 1
Explanation: (1, 2, 3) occured one time in the array so we return 1.


Constraints:

3 <= arr.length <= 3000
0 <= arr[i] <= 100
0 <= target <= 300
 */
public class Sol923_3SumWithMultiplicity {
    public int threeSumMulti(int[] arr, int target) {
        long res = 0;
        int mod = 1_000_000_007;
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num: arr) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        for (int i: countMap.keySet()) {
            for (int j : countMap.keySet()) {
                int k = target - i - j;
                if (countMap.containsKey(k)) {
                    long c1 = countMap.get(i);
                    long c2 = countMap.get(j);
                    long c3 = countMap.get(k);
                    if (i == j && j == k) {
                        res += c1 * (c1-1) * (c1 -2)/6;
                    } else if (i == j) {
                        res += c1 * (c1 - 1) /2 * c3;
                    } else if ( i < j && j < k) {
                        res += c1 * c2 * c3;
                    }
                    res %= mod;
                }
            }
        }
        return (int) res;
    }
}
