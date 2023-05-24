package com.alg.dp;

/**
 * Created by HAU on 5/26/2017.
 */

/*

Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.

Example 1:
Input:

"bbbab"
Output:
4
One possible longest palindromic subsequence is "bbbb".
Example 2:
Input:

"cbbd"
Output:
2
One possible longest palindromic subsequence is "bb".
让我们求最大的回文子序列，子序列和子字符串不同，不需要连续。
*/
public class Sol516Longest_Palindromic_Subsequence {
    public static int longestPalindromeSubseqContinuous(String s) {
        if(s.isEmpty()) return 0;
        if(s.length() ==1)  return 1;
        int length = 0;
        for (int i=0;i<s.length()-1; i++){
            length = Math.max(helperContinuous(s,i,i),length);
            length = Math.max(helperContinuous(s,i,i+1),length);
        }
        return length;

    }
    private static int helperContinuous(String s, int start, int end){

        while( start>=0 && end<=s.length()-1 && s.charAt(start) == s.charAt(end)){
            start--;
            end++;
        }
        return end-start-1;
    }


    public static int longestPalindromeSubseq(String s) {
        if (s == null || s.length()<=0) return 0;
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i= n-1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i+1;j<n; j++) {
                if (s.charAt(i) == s.charAt(j))
                    dp[i][j] = dp[i+1][j-1]  + 2;
                else dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
            }
        }
        return dp[0][n-1];   // dynamic programming approach dp[i][j]表示s[i .. j]的最大回文子串长度
    }
/*
    public static int longestPalindromeSubseq(String s) {
        if(s.isEmpty()) return 0;
        if(s.length() ==1)  return 1;
        int length = 0;
        for (int i=3;i<s.length()-1; i++){
            length = Math.max(helper(s,i,i),length);
            length = Math.max(helper(s,i,i+1),length);
        }
        return length;

    }
    private static int helper(String s, int start, int end){
        int result = 0;
        while( start>=0 && end<=s.length()-1 ){
            if(start+1==end && s.charAt(start)!=s.charAt(end)) return result;
            if(s.charAt(start) == s.charAt(end)) {
                if(start==end) result++;
                else result +=2;
            }else if(start-1>=0 && s.charAt(start-1)==s.charAt(end)){
                start--;//end++;
                result +=2;
            }else if(end+1<=s.length()-1 && s.charAt(start)==s.charAt(end+1)){
                end++;//start--;
                result+=2;
            }
                start--;  // not always true, s[start] may be = s[end+k], here start or end should stay
                end++;
        }
        return result;
    }
*/


    public static void main(String[] args){
        String s= "bbbab";
        String s2 = "cbbd";
        String s3 ="abcabcabcabc";
        String s4 ="abcdabcdabcdabcd";
        String s5 ="baaaaabbab";  // does not work
        //int k = 2;
        //System.out.println(longestPalindromeSubseqContinuous(s));
        //System.out.println(longestPalindromeSubseqContinuous(s2));
        System.out.println(longestPalindromeSubseq(s5));
        System.out.println(longestPalindromeSubseq(s));
    }

    // bottom up dp, O(n^2), easy to implement without errors
    public int getLengthOfLongestPalindromeSubsequence(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        //dp[i][j] length of palindromic subsequence from i , j, if @i==@j  i+1,j-1  + 2 else  i+1,j  i,j-1
        // i n ->0; j 0 > n;
        // init

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    // j == i, 1; j-i == 1, dp
                    if (j - i == 0) {
                        dp[i][j] = 1;
//                    } else if (j - i ==1) {
//                        dp[i][j] = 2;
                    } else {
                        dp[i][j] = 2 + dp[i + 1][j - 1];
                    }
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][n-1];
    }
}
