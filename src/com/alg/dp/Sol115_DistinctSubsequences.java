package com.alg.dp;
/*
Given two strings s and t, return the number of distinct subsequences of s which equals t.

A string's subsequence is a new string formed from the original string
by deleting some (can be none) of the characters without disturbing the remaining characters' relative positions. (i.e., "ACE" is a subsequence of "ABCDE" while "AEC" is not).

The test cases are generated so that the answer fits on a 32-bit signed integer.



Example 1:

Input: s = "rabbbit", t = "rabbit"
Output: 3
Explanation:
As shown below, there are 3 ways you can generate "rabbit" from S.
rabbbit
rabbbit
rabbbit
Example 2:

Input: s = "babgbag", t = "bag"
Output: 5
Explanation:
As shown below, there are 5 ways you can generate "bag" from S.
babgbag
babgbag
babgbag
babgbag
babgbag
 */
public class Sol115_DistinctSubsequences {

    //https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0115.%E4%B8%8D%E5%90%8C%E7%9A%84%E5%AD%90%E5%BA%8F%E5%88%97.md

    public int numDistinctSequences(String s, String t) {
        if (s.length() < t.length()) return 0;
        int sl = s.length();
        int tl = t.length();
        int[][] dp = new int[sl+1][tl+1];
        // dp[i+1][j+1]：以i为结尾的s子序列中出现以j为结尾的t的个数。
        // delicate initialization
        for (int i = 0; i <= sl; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < sl; i++) {
            for (int j = 0; j < tl; j++) {
                if (s.charAt(i) == t.charAt(j)) {
                    dp[i+1][j+1] = dp[i][j] + dp[i][j+1];  // use i+1 to match j+1 in dp or not use i+1
                } else {
                    dp[i+1][j+1] = dp[i][j+1];
                }
            }
        }
        return dp[sl][tl];
    }

    public static void main(String[] args) {
        String s = "babgbag";
        String t = "bag";
        Sol115_DistinctSubsequences ss = new Sol115_DistinctSubsequences();
        System.out.println(ss.numDistinctSequences(s, t));
    }
}
