package com.alg;

/**
 * Created by HAU on 11/30/2017.
 */
/*Given a string, your task is to count how many palindromic substrings
in this string.

The substrings with different start indexes or end indexes
are counted as different substrings even they consist of same characters.
Example 1:
Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
Example 2:
Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".*/
public class Sol647_PalindromeSubstrings {
    static int res = 0;
    public  static int countSubstrings(String s) {

        if (s == null || s.length() == 0) return 0;
        for (int i = 0; i < s.length();i++){
            helper(s, i, i);
            helper(s, i,i+1);
        }
        return res;
    }

    private  static void helper(String s, int i, int j) {
        while(i>=0 && j<s.length() && s.charAt(i)== s.charAt(j)){
            res++;i--;j++;
        }
    }

    public static void main(String[] args) {
        String s = "abcba";
        System.out.println(countSubstrings(s)); //7
        String t = "caa";  //4
        System.out.println(countSubPalin(t));
    }
    // version 2  time O(n^2)
    public static int countSubPalin(String s){
        if (s == null || s.length() == 0){
            return 0;
        }
        int count = 0;
        for(int i = 0; i < s.length(); i++){
            count += counthelper(s,i,i);
            count += counthelper(s,i,i+1);
        }
        return count;
    }

    private static int counthelper(String s, int start, int end) {
        int c = 0;
        while (start >=0 && end < s.length() && s.charAt(start) == s.charAt(end)){
            c++;
            start--;
            end++;
        }
        return c;
    }

    //dp https://mp.weixin.qq.com/s/2WetyP6IYQ6VotegepVpEw
    public int countSubPalins(String s) {
        int res = 0;
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        //dp[i][j] - start i -> end j true if s[i] == s[j] && dp[i+1][j-1]
        for (int i = n - 1; i>=0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j == i || j - i  == 1) {
                        dp[i][j] = true;
                        res++;
                    } else { // j - i > 1
                        dp[i][j] = dp[i+1][j-1];
                        if (dp[i][j]) {
                            res++;
                        }
                    }
                }

            }
        }
        // for loop can be simplified as below
//        for (int i = n - 1; i>=0; i--) {
//            for (int j = i; j < n; j++) {
//                dp[i][j] = s.charAt(i) == s.charAt(j) && (j-i <= 2 || dp[i+1][j-1]);
//                if (dp[i][j]) res++;
//            }
//        }
        return res;
    }
}
