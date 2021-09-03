package com.alg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Given two strings s and t, check if s is a subsequence of t.

A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).



Example 1:

Input: s = "abc", t = "ahbgdc"
Output: true
Example 2:

Input: s = "axc", t = "ahbgdc"
Output: false
 */
public class Sol392_IsSubsequence {
    public static boolean isSubsequence(String s, String t) {
        if (s.length() == 0) return true;
        int indexS = 0;
        int indexT = 0;
        while (indexT < t.length()) {
            if (t.charAt(indexT) == s.charAt(indexS)) {
                indexS++;
                if (indexS == s.length()) {
                    return true;
                }
            }
            indexT++;
        }
        return false;
    }

    //Follow up: If there are lots of incoming s, say s1, s2, ..., sk
    // where k >= 109, and you want to check one by one to see if t has its subsequence.
    // In this scenario, how would you change your code?

    public static boolean isSubsequence2(String s, String t) {

        // Eg-2. s="abc", t="bahgdcb"
        // map = [a={1}, b={0,6}, c={5}, h = {xx}]
        //  i=0 ('a'): prev=1
        //  i=1 ('b'): prev=6
        //  i=2 ('c'): prev=? (return false)
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            if (!map.containsKey(t.charAt(i))) {
                map.put(t.charAt(i), new ArrayList<>());
            }
            map.get(t.charAt(i)).add(i);
        }
        int pre = 0;
        for (int i = 0; i < s.length(); i++) {
            if (!map.containsKey(s.charAt(i))) {
                return false;
            }

            // Returns index of key in sorted list
            //If key is not present, the it returns "(-(insertion point) - 1)".
            //The insertion point is defined as the point at which the key
            //would be inserted into the list.
            // https://leetcode.com/problems/is-subsequence/discuss/87302/Binary-search-solution-for-follow-up-with-detailed-comments
            // find the first element larger than a value (the index in t of the previous char in s) in an sorted array (indices of the char in t) (find upper bound)
            int nextIndex = Collections.binarySearch(map.get(s.charAt(i)), pre);
            if (nextIndex < 0) {
                nextIndex = -1 * (nextIndex + 1);
            }
            if (nextIndex == map.get(s.charAt(i)).size()) {
                return false;
            }
            pre = map.get(s.charAt(i)).get(nextIndex) + 1;
        }

        return true;
    }

    public static void main(String[] args) {

        String s = "abc";
        String t = "bahgdcb";

        System.out.println(isSubsequence2(s, t));
    }

}
