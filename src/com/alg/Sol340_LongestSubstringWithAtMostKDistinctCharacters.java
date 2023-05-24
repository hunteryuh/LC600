package com.alg;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/*
Given a string s and an intteger k, return the length of the longest substring of s that contains at most k distinct characters.



Example 1:

Input: s = "eceba", k = 2
Output: 3
Explanation: The substring is "ece" with length 3.

Example 2:

Input: s = "aa", k = 1
Output: 2
Explanation: The substring is "aa" with length 2.



Constraints:

    1 <= s.length <= 5 * 104
    0 <= k <= 50


 */
public class Sol340_LongestSubstringWithAtMostKDistinctCharacters {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int res = 1;
        int left = 0;
        if (k == 0) return 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // hashmap stores the distinct char as key, frequency/counts of occurrence of the char as value
            map.put(c, map.getOrDefault(c, 0) + 1);
            while (map.size() > k) {  // if we use "if" it also works, as it adds one char at a time
                char out = s.charAt(left);
                map.put(out, map.get(out) - 1);
                if (map.get(out) == 0) {
                    map.remove(out);
                }
                left++;
            }
            System.out.println("I is " + i);
            System.out.println("map size is " + map.size());
            System.out.println(map);
            res = Math.max(res, i - left + 1);
        }
        System.out.println(res);
        System.out.println(left);
        return res;
    }

    public static void main(String[] args) {

        String s = "eceaaab";
        int k = 2;
        Sol340_LongestSubstringWithAtMostKDistinctCharacters ss = new Sol340_LongestSubstringWithAtMostKDistinctCharacters();
        ss.lengthOfLongestSubstringKDistinct(s, k);
    }

    // gurantee time O(N) with ordered map: linkedhashmap
    public int lengOfLSKDistinct(String s, int k) {
        Map<Character, Integer> map = new LinkedHashMap<>();
        int max = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.remove(ch);
            map.put(ch, i);
            if (map.size() > k) {
                char c1 = map.keySet().iterator().next();
                int idx1 = map.get(c1);
                left = idx1 + 1;
                map.remove(c1);
            }
            max = Math.max(max, i - left + 1);
        }
        return max;

    }
}
