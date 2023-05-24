package com.alg.lint;
/*
Given n distinct positive integers, integer k (k \leq n)(k≤n) and a number target.

Find k numbers where sum is target. Calculate how many solutions there are?

Example
Example 1:

Input:

A = [1,2,3,4]
k = 2
target = 5
Output:

2
Explanation:

1 + 4 = 2 + 3 = 5
Example 2:

Input:

A = [1,2,3,4,5]
k = 3
target = 6
Output:

1
Explanation:

There is only one method. 1 + 2 + 3 = 6
 */
public class P89_KSum {
    // only asks for the number of solutions; not asking what each solution is
    public int kSum(int[] a, int k, int target) {
        // dp https://www.lintcode.com/problem/89/solution/21763
        // f[i][k][s] 代表，前i个数 (from 0 to i -1 里面取k个数 使得和是s的方案的个数）
        //
        //f[i][k][s] = f[i-1] [k] [ s ] + f[i-1] [ k -1 ] [ s - A[i-1]]  , 其中k >=1, S-A[i-1] >=0;
        int n = a.length;
        int[][][] dp = new int[n+1][k+1][target+1];
        for (int i = 0; i <= n; i++) {
            dp[i][0][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k && j <= i; j++) {
                for (int t = 1; t <= target; t++) {
                    dp[i][j][t] = 0;
                    if ( t >= a[i-1]) {
                        dp[i][j][t] = dp[i-1][j-1][t - a[i-1]];
                    }
                    dp[i][j][t] += dp[i-1][j][t];
                }
            }
        }
        return dp[n][k][target];
    }
}
