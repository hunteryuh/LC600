package com.alg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Given a string s, consider all duplicated substrings: (contiguous) substrings of s that occur 2 or more times. The occurrences may overlap.

Return any duplicated substring that has the longest possible length. If s does not have a duplicated substring, the answer is "".



Example 1:

Input: s = "banana"
Output: "ana"
Example 2:

Input: s = "abcd"
Output: ""


Constraints:

2 <= s.length <= 3 * 104
s consists of lowercase English letters.
 */
public class Sol1044_LongestDuplicateSubstring {
    String str;
    public String longestDupSubstring(String s) {
        str = s;
        int n = str.length();
        // convert string to an array of integers to implement constant time slice
        int[] nums = new int[n];
        for (int i =0; i < n; i++) {
            nums[i] = str.charAt(i) - 'a';
        }
        // base value for rolling hash
        int a = 26;
        // modulus value for rolling hash function to avoid overflow
        int modulus = 1_000_000_007;
        // binary search
        int left = 1, right = n;
        while (left <= right) {
            int mid = left + (right - left) /2 ;
            if (search(mid, a, modulus, n, nums) != -1) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        int start = search(left - 1, a, modulus, n, nums);
        return str.substring(start, start + left - 1);
    }

    public int search(int L, int a, long modulus, int n, int[] nums) {
        long h = 0;
        for (int i = 0; i < L; i++) {
            h = (h * a + nums[i] ) % modulus;
        }
        // store the already seen hash values for substring of length L
        Map<Long, List<Integer>> seen = new HashMap<>();
        // initialize the hashmap with the substring starting at index 0
        seen.putIfAbsent(h, new ArrayList<>());
        seen.get(h).add(0);

        // const valu to be used often: a**L % modulus
        long al = 1;
        for (int i = 1; i <=L; i++) {
            al = (al * a)% modulus;
        }
        for (int start = 1; start < n - L + 1; start ++) {
            // compute rolling hash in O(1) time
            h = (h * a - nums[start - 1] * al % modulus + modulus) % modulus; // + moduluas to handle negative number
            h = (h + nums[start + L - 1]) % modulus;
            List<Integer> hits = seen.get(h);

            // check if current substring matches any of previous substring with hash h
            if (hits != null) {
                String cur = str.substring(start, start + L);
                for (Integer i: hits) {
                    String candidate = str.substring(i, i + L);
                    if (candidate.equals(cur)) {
                        return i;
                    }
                }
            }
            seen.putIfAbsent(h, new ArrayList<>());
            seen.get(h).add(start);
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println('b' - 'a');
    }
}
