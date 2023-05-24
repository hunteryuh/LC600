package com.alg;
/*
Given two strings s and t, return true if they are both one edit distance apart, otherwise return false.

A string s is said to be one distance apart from a string t if you can:

    Insert exactly one character into s to get t.
    Delete exactly one character from s to get t.
    Replace exactly one character of s with a different character to get t.



Example 1:

Input: s = "ab", t = "acb"
Output: true
Explanation: We can insert 'c' into s to get t.

Example 2:

Input: s = "", t = ""
Output: false
Explanation: We cannot get t from s by only one step.



Constraints:

    0 <= s.length, t.length <= 104
    s and t consist of lowercase letters, uppercase letters, and digits.


 */
public class Sol161_OneEditDistance {
    // https://leetcode.com/problems/one-edit-distance/discuss/50098/My-CLEAR-JAVA-solution-with-explanation
    public boolean isOneEditDistance(String s, String t) {
        int m = s.length();
        int n = t.length();
        if (m > n) {
            return isOneEditDistance(t, s);
        }
        boolean isSamelength = m == n ;
        for (int i = 0; i < m; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (isSamelength) {
                    return s.substring(i+1).equals(t.substring(i+1));
                } else {
                    return s.substring(i).equals(t.substring(i+1));
                }
            }
        }
        return m + 1 == n;
    }
}
