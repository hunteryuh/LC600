package com.alg;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
Given an array of unique integers, arr, where each integer arr[i] is strictly greater than 1.

We make a binary tree using these integers, and each number may be used for any number of times. Each non-leaf node's value should be equal to the product of the values of its children.

Return the number of binary trees we can make. The answer may be too large so return the answer modulo 109 + 7.



Example 1:

Input: arr = [2,4]
Output: 3
Explanation: We can make these trees: [2], [4], [4, 2, 2]

Example 2:

Input: arr = [2,4,5,10]
Output: 7
Explanation: We can make these trees: [2], [4], [5], [10], [4, 2, 2], [10, 2, 5], [10, 5, 2].



Constraints:

    1 <= arr.length <= 1000
    2 <= arr[i] <= 109
    All the values of arr are unique.
https://leetcode.com/problems/binary-trees-with-factors/description/

 */
public class Sol823_BinaryTreeWithFactors {
    public int numFactoredBinaryTrees(int[] A) {
        long res = 0L, mod = (long)1e9 + 7;
        Arrays.sort(A);
        HashMap<Integer, Long> dp = new HashMap<>();
        for (int i = 0; i < A.length; ++i) {
            dp.put(A[i], 1L);
            for (int j = 0; j < i; ++j)
                if (A[i] % A[j] == 0)
                    dp.put(A[i], (dp.get(A[i]) + dp.get(A[j]) * dp.getOrDefault(A[i] / A[j], 0L)) % mod);
            res = (res + dp.get(A[i])) % mod;
        }
        return (int) res;
    }

    // https://leetcode.com/problems/binary-trees-with-factors/solutions/2400159/java-easy-solution-using-hashmap/
    public int numFactoredBinaryTree(int[] A) {
        Arrays.sort(A);
        long res = 0;
        Map<Integer, Long> map = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            map.put(A[i], 1L);
            for (int j = 0; j < i; j++) {
                if (A[i] % A[j] == 0 && map.containsKey(A[i]/A[j])) {
                    map.put(A[i], map.get(A[i]) + map.get(A[j]) * map.get(A[i]/A[j]));
                }
            }
            res += map.get(A[i]);
        }
        return (int) (res % 1000000007);
    }
}
