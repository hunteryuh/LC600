package com.alg.other;

import java.util.HashMap;
import java.util.Map;

/*
You are given two strings s and t. You can select any substring of string s and rearrange the characters of the selected substring. Determine the minimum length of the substring of s such that string t is a substring of the selected substring.
Signature
int minLengthSubstring(String s, String t)
Input
s and t are non-empty strings that contain less than 1,000,000 characters each
Output
Return the minimum length of the substring of s. If it is not possible, return -1
Example
s = "dcbefebce"
t = "fd"
output = 5
Explanation:
Substring "dcbef" can be rearranged to "cfdeb", "cefdb", and so on. String t is a substring of "cfdeb". Thus, the minimum length required is 5.

 */
public class FB_MinimumLengthSubstrings {

    public int minLengthSubstring(String s, String t) {
        // Write your code here
        if (t.length() > s.length()) {
            return -1;
        }
        Map<Character, Integer> tMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            tMap.put(t.charAt(i), tMap.getOrDefault(t.charAt(i), 0) + 1);
        }
        int left = 0;
        // define a map to store the status of sliding window in S
        int matchCount = 0;
        int res = s.length() + 1;
        Map<Character, Integer> sMap = new HashMap<>();
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (tMap.containsKey(c)) {
                sMap.put(c, sMap.getOrDefault(c, 0) + 1);
                if (sMap.get(c).equals(tMap.get(c))) {
                    matchCount++;
                }
            }
            while (matchCount == tMap.size()) {
                if (right - left + 1 < res) {
                    res = right - left + 1;
                }
                char cl = s.charAt(left);
                if (tMap.containsKey(cl)) {
                    sMap.put(cl, sMap.get(cl) - 1);
                    if (sMap.get(cl) < tMap.get(cl)) {
                        matchCount--;
                    }
                }
                left++;
            }
        }
        return res == s.length() + 1 ? -1 : res;
    }

    public static void main(String[] args) {
        String s = "adobecodebanc";
        String t = "abc";
        FB_MinimumLengthSubstrings f = new FB_MinimumLengthSubstrings();
        System.out.println(f.minLengthSubstring(s, t));  // 4

        String s2 = "dcbefebce";
        String t2 = "fd";
        System.out.println(f.minLengthSubstring(s2, t2));  // 5


        String s_2 = "bfbeadbcbcbfeaaeefcddcccbbbfaaafdbebedddf";
        String t_2 = "cbccfafebccdccebdd";
    }
}
