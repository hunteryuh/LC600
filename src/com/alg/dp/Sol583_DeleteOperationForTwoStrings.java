package com.alg.dp;

import java.util.Arrays;

/*
Given two strings word1 and word2, return the
minimum number of steps required to make word1 and word2 the same.

In one step, you can delete exactly one character in either string.



Example 1:

Input: word1 = "sea", word2 = "eat"
Output: 2
Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
Example 2:

Input: word1 = "leetcode", word2 = "etco"
Output: 4


Constraints:

1 <= word1.length, word2.length <= 500
word1 and word2 consist of only lowercase English letters.
 */
public class Sol583_DeleteOperationForTwoStrings {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        // dp[i+1][j+1] : minimum steps to make substrings ending at i in 1 equal substring ending at j in 2
        int[][] dp = new int[m+1][n+1];
        for (int i = 0; i <= m; i++) {  // need to include m
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {   // need to include n
            dp[0][j] = j;
        }
        //0 ,
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i+1][j+1] = dp[i][j];
                } else {
                    dp[i+1][j+1] = Math.min(dp[i+1][j] + 1, dp[i][j+1] + 1);
                }
            }
        }
        System.out.println(Arrays.deepToString(dp));
        return dp[m][n];
    }

    public static void main(String[] args) {
        String s = "leetcode";
        String t = "etco";
        Sol583_DeleteOperationForTwoStrings ss = new Sol583_DeleteOperationForTwoStrings();
//        System.out.println(ss.minDistance(s, t));

        String s2 = "a";
        String t2 = "b";
        System.out.println(ss.minDistance(s2, t2));
    }

    // solution at leetcode
    public int minDistance2(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 || j == 0)
                    dp[i][j] = i + j;
                else if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], dp[i][j - 1]);  // no 3rd option of 2 + dp[i-1][j-1]
            }
        }
        return dp[s1.length()][s2.length()];
    }
}
