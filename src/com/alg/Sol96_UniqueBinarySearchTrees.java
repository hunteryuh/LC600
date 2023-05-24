package com.alg;
/*
Given an integer n, return the number of structurally unique BST's (binary search trees)
which has exactly n nodes of unique values from 1 to n.

Example 1:


Input: n = 3
Output: 5
Example 2:

Input: n = 1
Output: 1
 */
public class Sol96_UniqueBinarySearchTrees {
    public int numTrees(int n) {
        // n == 1 , 1
        // n == 2 , 2
        // time O(n^2) space O(n)
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        // dp[3] = dp[2] * dp[0] + dp[1] * dp[1] + dp[0] * dp[2];
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i-j-1];
            }
        }
        return dp[n];
    }
// dp  https://leetcode.com/problems/unique-binary-search-trees/discuss/31666/DP-Solution-in-6-lines-with-explanation.-F(i-n)-G(i-1)-*-G(n-i)
    public int numOfBSTs(int n ) {
        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1;
        for (int i = 2; i <=n ;i++) {
            for (int j = 1; j <= i; j++) {
                G[i] += G[j-1] * G[i - j];
            }
        }
        return G[n];
    }

    public int numOfTrees(int n) {
        Integer[] memo = new Integer[n + 1];
        return dfs(n, memo);
    }
    private int dfs(int n, Integer[] memo) {
        if (n == 0 || n == 1) return 1;
        if (memo[n] != null) return memo[n];
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += dfs(i - 1, memo) + dfs(n - i, memo);
        }
        // memo[n] = sum;
        return memo[n] = sum;
    }
}
