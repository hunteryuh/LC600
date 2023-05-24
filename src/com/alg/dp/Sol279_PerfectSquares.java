package com.alg.dp;

import java.util.Arrays;

/**
 * Created by HAU on 11/2/2017.
 */
/*Given a positive integer n,
find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.*/
public class Sol279_PerfectSquares {
    public static int numSquares(int n){
        if( n<= 0) return -1;
        int[] counts = new int[n+1];
        counts[0] = 0;
        for (int i = 1; i <= n ; i++){
            counts[i] = Integer.MAX_VALUE;
            for (int j = 1; j *j <= i; j++){
                if (counts[i] > counts[i - j *j] + 1){
                    counts[i] = counts[i - j* j] + 1;
                }
            }
        }
        return counts[n];
    }

    public static void main(String[] args) {
        int t = 22;
        System.out.println(numSquares(t));
    }

    public int numSquares2(int n) {
        // dp[j] min number of squares that reach target j
        // unbounded knapsack, combinations
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n);
        dp[0] = 0;  // 0 is not counted as positive squares, so make it 0
        for (int i = 1; i < Math.sqrt(n) + 1 ; i++) {
            for (int j = i * i; j <=n; j++) {
                if (dp[j-i*i] < n) {
                    dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
                }
            }
        }
        return dp[n];
    }
}
