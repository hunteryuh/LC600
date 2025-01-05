package com.alg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
You are given a 0-indexed integer array nums of length n.

A split at an index i where 0 <= i <= n - 2 is called valid if the product of the first i + 1 elements and the product of the remaining elements are coprime.

    For example, if nums = [2, 3, 3], then a split at the index i = 0 is valid because 2 and 9 are coprime, while a split at the index i = 1 is not valid because 6 and 3 are not coprime. A split at the index i = 2 is not valid because i == n - 1.

Return the smallest index i at which the array can be split validly or -1 if there is no such split.

Two values val1 and val2 are coprime if gcd(val1, val2) == 1 where gcd(val1, val2) is the greatest common divisor of val1 and val2.



Example 1:

Input: nums = [4,7,8,15,3,5]
Output: 2
Explanation: The table above shows the values of the product of the first i + 1 elements, the remaining elements, and their gcd at each index i.
The only valid split is at index 2.

Example 2:

Input: nums = [4,7,15,8,3,5]
Output: -1
Explanation: The table above shows the values of the product of the first i + 1 elements, the remaining elements, and their gcd at each index i.
There is no valid split.



Constraints:

    n == nums.length
    1 <= n <= 104
    1 <= nums[i] <= 106


 */
public class Sol2584_SplittheArrayToMakeCoprimeProducts {
    // https://leetcode.com/problems/split-the-array-to-make-coprime-products/solutions/3258070/prime-intervals-vs-count-primes/
    // Products of prefix and suffix elements are co-prime
    // if prefix elements do not share any prime factors with suffix elements.
    public int findValidSplit(int[] nums) {
        Map<Integer, Integer> left = new HashMap<>();
        Map<Integer, Integer> right = new HashMap<>();
        for (int num : nums) {
            for (int factor: findFactors(num)) {
                right.put(factor, right.getOrDefault(factor, 0) + 1);
            }
        }
        System.out.println(right);
        for (int i = 0; i < nums.length - 1; i++) {
            for (int factor: findFactors(nums[i])) {
                right.put(factor, right.get(factor) - 1);
                left.put(factor, left.getOrDefault(factor, 0) + 1);
                if (right.get(factor) == 0) {
                    left.remove(factor);
                }
            }
            // have to check leftMap after the inner for loop to process all primes for the index
            if (left.isEmpty()) {
                return i;
            }
        }
        return -1;
    }

    private List<Integer> findFactors(int num) {
        List<Integer> list = new ArrayList<>();
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                list.add(i);
                while (num % i == 0) {
                    num /= i;
                }
            }
        }
        if (num > 1) {
            list.add(num);
        }
        return list;
    }

    public static void main(String[] args) {
        int[] nums = {4, 7, 8, 15, 3};
        Sol2584_SplittheArrayToMakeCoprimeProducts ss = new Sol2584_SplittheArrayToMakeCoprimeProducts();
        int x = ss.findValidSplit(nums);
        System.out.println(x);
    }
}
