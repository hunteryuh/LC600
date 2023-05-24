package com.alg.other;
/*
Given a string s. In one step you can insert any character at any index of the string.

Return the minimum number of steps to make s palindrome.

A Palindrome String is one that reads the same backward as well as forward.



Example 1:

Input: s = "zzazz"
Output: 0
Explanation: The string "zzazz" is already palindrome we don't need any insertions.

Example 2:

Input: s = "mbadm"
Output: 2
Explanation: String can be "mbdadbm" or "mdbabdm".

Example 3:

Input: s = "leetcode"
Output: 5
Explanation: Inserting 5 characters the string becomes "leetcodocteel".



Constraints:

    1 <= s.length <= 500
    s consists of lowercase English letters.


 */
public class Sol1312_MinInsertionsToMakeAPalindrome {
    // convert to find longest common subsequence of two strings
    public int minInsertions(String s) {
        String rev = new StringBuilder(s).reverse().toString();
        int n = s.length();
        // find longest common subsequence of s and rev
        // https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/discuss/470706/JavaC%2B%2BPython-Longest-Common-Sequence
        // dp[i][j]  length of longest commin subsequence up to i-1 th item in s and j-1 th item in rev
        int[][] dp = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i-1) == rev.charAt(j-1)) {
                    dp[i][j] = Math.max(dp[i][j], 1 + dp[i-1][j-1]);
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return n - dp[n][n];
    }

    // direct dp
    // https://leetcode-cn.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/solution/rang-zi-fu-chuan-cheng-wei-hui-wen-chuan-de-zui--2/
    public int minInsertions2(String s) {
        // dp[i][j]  min inerstions to make substring [i,j] a palindrome
        // need dp[0][n-1]
        int n = s.length();
        int[][] dp = new int[n][n];
        // dp[i][j]  = f(dp[i+1][j-1])
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {

                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1];
                } else {
                    dp[i][j] = Math.min(dp[i+1][j], dp[i][j-1]) + 1;
                }
            }
        }
        return dp[0][n-1];
    }
}
