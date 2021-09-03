package com.alg;
/*
Given an integer n, break it into the sum of k positive integers, where k >= 2, and maximize the product of those integers.

Return the maximum product you can get.



Example 1:

Input: n = 2
Output: 1
Explanation: 2 = 1 + 1, 1 × 1 = 1.
Example 2:

Input: n = 10
Output: 36
Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.
 */
public class Sol343_IntegerBreak {
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i<=n ; i++) {
            for (int j = 1; j <= i - j; j++) {
                int f1 = Math.max(j, dp[j]);
                int f2 = Math.max(i-j, dp[i-j]);
                dp[i] = Math.max(dp[i], f1 * f2);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Sol343_IntegerBreak s = new Sol343_IntegerBreak();
        System.out.println(s.integerBreak(10));
    }
}
