package com.alg;
/*
Given a string s, return the number of different non-empty palindromic subsequences in s. Since the answer may be very large, return it modulo 109 + 7.

A subsequence of a string is obtained by deleting zero or more characters from the string.

A sequence is palindromic if it is equal to the sequence reversed.

Two sequences a1, a2, ... and b1, b2, ... are different if there is some i for which ai != bi.



Example 1:

Input: s = "bccb"
Output: 6
Explanation: The 6 different non-empty palindromic subsequences are 'b', 'c', 'bb', 'cc', 'bcb', 'bccb'.
Note that 'bcb' is counted only once, even though it occurs twice.

Example 2:

Input: s = "abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba"
Output: 104860361
Explanation: There are 3104860382 different non-empty palindromic subsequences, which is 104860361 modulo 109 + 7.

 */
public class Sol730_CountDifferentPalindromicSubsequences {

    // dp , I maintain DP[i][j] to record in substring from i to j(included), the number of palindrome without duplicate. T
    //  two cases of the DP equation:
    //
    // when s.charAt(i) != s.charAt(j):
    //dp[i][j] = dp[i][j] = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1];
    //
    //When s.charAt(i) == s.charAt(j): see code
    public int countPalindromicSubsequences(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        char[] chars = s.toCharArray();
        for (int i = 0; i < len; i++) {
            dp[i][i] = 1;
        }
        for (int distance = 1; distance < len; distance++) {
            for (int i = 0; i < len - distance; i++) {
                int j = i + distance;
                if (chars[i] == chars[j]) {
                    int low = i + 1;
                    int high = j - 1;
                    while (low <= high && chars[low] != chars[i]) {
                        low++;
                    }
                    while (low <= high && chars[high] != chars[j]) {
                        high--;
                    }

                    if (low > high) {
                        // consider the string from i to j is "a...a" "a...a"... where there is no character 'a' inside the leftmost and rightmost 'a'
                       /* eg:  "aba" while i = 0 and j = 2:  dp[1][1] = 1 records the palindrome{"b"},
                         the reason why dp[i + 1][j  - 1] * 2 counted is that we count dp[i + 1][j - 1] one time as {"b"},
                         and additional time as {"aba"}. The reason why 2 counted is that we also count {"a", "aa"}.
                         So totally dp[i][j] record the palindrome: {"a", "b", "aa", "aba"}.
                         */

                        dp[i][j] = dp[i + 1][j - 1] * 2 + 2;
                    } else if (low == high) {
                        // consider the string from i to j is "a...a...a" where there is only one character 'a' inside the leftmost and rightmost 'a'
                       /* eg:  "aaa" while i = 0 and j = 2: the dp[i + 1][j - 1] records the palindrome {"a"}.
                         the reason why dp[i + 1][j  - 1] * 2 counted is that we count dp[i + 1][j - 1] one time as {"a"},
                         and additional time as {"aaa"}. the reason why 1 counted is that
                         we also count {"aa"} that the first 'a' come from index i and the second come from index j. So totally dp[i][j] records {"a", "aa", "aaa"}
                        */
                        dp[i][j] = dp[i + 1][j - 1] * 2 + 1;
                    } else {
                        // consider the string from i to j is "a...a...a... a" where there are at least two character 'a' close to leftmost and rightmost 'a'
                       /* eg: "aacaa" while i = 0 and j = 4: the dp[i + 1][j - 1] records the palindrome {"a",  "c", "aa", "aca"}.
                          the reason why dp[i + 1][j  - 1] * 2 counted is that we count dp[i + 1][j - 1] one time as {"a",  "c", "aa", "aca"},
                          and additional time as {"aaa",  "aca", "aaaa", "aacaa"}.  Now there is duplicate :  {"aca"},
                          which is removed by deduce dp[low + 1][high - 1]. So totally dp[i][j] record {"a",  "c", "aa", "aca", "aaa", "aaaa", "aacaa"}
                          */
                        dp[i][j] = dp[i + 1][j - 1] * 2 - dp[low + 1][high - 1];
                    }
                } else {
                    dp[i][j] = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1];  //s.charAt(i) != s.charAt(j)
                }
                dp[i][j] = dp[i][j] < 0 ? dp[i][j] + 1000000007 : dp[i][j] % 1000000007;
            }
        }

        return dp[0][len - 1];
    }

}
