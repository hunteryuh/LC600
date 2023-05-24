package com.alg.dp;
/*
You are given an array of binary strings strs and two integers m and n.

Return the size of the largest subset of strs such that there are at most m 0's and n 1's in the subset.

A set x is a subset of a set y if all elements of x are also elements of y.



Example 1:

Input: strs = ["10","0001","111001","1","0"], m = 5, n = 3
Output: 4
Explanation: The largest subset with at most 5 0's and 3 1's is {"10", "0001", "1", "0"}, so the answer is 4.
Other valid but smaller subsets include {"0001", "1"} and {"10", "1", "0"}.
{"111001"} is an invalid subset because it contains 4 1's, greater than the maximum of 3.
Example 2:

Input: strs = ["10","0","1"], m = 1, n = 1
Output: 2
Explanation: The largest subset is {"0", "1"}, so the answer is 2.
 */
public class Sol474_OnesAndZeroes {
    // https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0474.%E4%B8%80%E5%92%8C%E9%9B%B6.md
    public int findMaxForm(String[] strs, int m, int n) {
        // for nums of items, 0- i
        // for weights of bag, j--, two dimension
        int[][] dp = new int[m+1][n+1];
        for (int i = 0; i < strs.length; i++) {
            String si = strs[i];
            int zeroes = 0;
            int ones = 0;
            for (char c : si.toCharArray()) {
                if (c == '0') zeroes++;
                else ones++;
            }
            for (int j = m; j >= zeroes; j--) {
                for (int k = n; k >= ones; k--) {
                    dp[j][k] = Math.max(dp[j][k] , dp[j- zeroes][k- ones] + 1);
                }
            }
        }
        return dp[m][n];
    }
}
