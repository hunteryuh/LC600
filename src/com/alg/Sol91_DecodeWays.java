package com.alg;

/**
 * Created by HAU on 9/9/2017.
 */

/*
*
*
* A message containing letters from A-Z is being
* encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits,
determine the total number of ways to decode it.

*/
public class Sol91_DecodeWays {
    public static int numDecodings(String s){
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        int[] dp = new int[n+1];
        dp[0] = 1;  // nothing is decoded; how many ways: 1
        dp[1] = s.charAt(0) != '0'? 1:0;
        for ( int i = 2; i <= n; i++){
            if ( s.charAt(i - 1) != '0'){  // i-1 in the string for ith item in the dp array
                dp[i] = dp[i-1];
            }
            int second = s.charAt(i-1) - '0' + (s.charAt(i-2) - '0')*10;
            if (second >= 10 && second <= 26){
                dp[i] += dp[i-2];
            }
        }
        return dp[n];

    }

    public static void main(String[] args) {
        String s = "7213924";
        System.out.println(numDecodings(s));
    }
}
