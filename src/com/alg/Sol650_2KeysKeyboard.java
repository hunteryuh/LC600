package com.alg;
/*
There is only one character 'A' on the screen of a notepad. You can perform two operations on this notepad for each step:

Copy All: You can copy all the characters present on the screen (a partial copy is not allowed).
Paste: You can paste the characters which are copied last time.
Given an integer n, return the minimum number of operations to get the character 'A' exactly n times on the screen.



Example 1:

Input: n = 3
Output: 3
Explanation: Intitally, we have one character 'A'.
In step 1, we use Copy All operation.
In step 2, we use Paste operation to get 'AA'.
In step 3, we use Paste operation to get 'AAA'.
Example 2:

Input: n = 1
Output: 0
 */
public class Sol650_2KeysKeyboard {
    public static int minSteps(int n) {
        int[] dp = new int[n+1];
        for (int i = 2; i < n + 1; i++) {
            for (int j = i/2; j>0; j--) {
                if (i % j  == 0) {  // the largest factor of i
                    dp[i] = dp[j] + 1 + (i/j) - 1;
                    break;
                }
            }
        }
        return dp[n];
    }

    //https://leetcode.com/problems/2-keys-keyboard/discuss/105899/Java-DP-Solution
    // https://leetcode.com/problems/2-keys-keyboard/discuss/105932/Java-solutions-from-naive-DP-to-optimized-DP-to-non-DP
    public static void main(String[] args) {
        System.out.println(minSteps(5));
        System.out.println(minSteps(2));
        System.out.println(minSteps(15));
    }
    // naive dp
    public static int minStepsNaive(int n) {
        int[] dp = new int[n + 1];

        for (int k = 2; k <= n; k++) {
            dp[k] = Integer.MAX_VALUE;

            for (int i = 1; i < k; i++) {
                if (k % i != 0) continue;
                dp[k] = Math.min(dp[k], dp[i] + k / i);  //T(k) = min(T(i) + k/i) where 1 <= i < k && k % i == 0.
            }
        }

        return dp[n];
    }
}
