package com.alg;

import java.util.HashSet;
import java.util.Set;

/*
Given a string s, return the number of unique palindromes of length three that are a subsequence of s.

Note that even if there are multiple ways to obtain the same subsequence, it is still only counted once.

A palindrome is a string that reads the same forwards and backwards.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".
 */
public class Sol1930_UniqueLength3PalindromicSubSequence {
    // https://leetcode.com/problems/unique-length-3-palindromic-subsequences/discuss/1330309/Java-17-lines-code-Easy-to-understand-String-built-in-functions
    public int countPalindromicSubsequence(String s) {
        int res = 0;
        for (char c = 'a'; c <= 'z'; c++) {
            int first = s.indexOf(c);
            int last = s.lastIndexOf(c);
            if (first >= 0 && last >= 0 && first != last) {
                Set<Character> set = new HashSet<>();
                for (int i = first + 1; i < last; i++) {
                    if (!set.contains(s.charAt(i))) {
                        set.add(s.charAt(i));
                    }
                }
                res += set.size();
            }
        }
        return res;
    }
}
