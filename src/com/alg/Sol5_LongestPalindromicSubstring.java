package com.alg;

/**
 * Created by HAU on 7/25/2017.
 */
/*Given a string s, find the longest palindromic substring in s.
        You may assume that the maximum length of s is 1000.*/
public class Sol5_LongestPalindromicSubstring {
    //(Expand Around Center)  O(n^2) time, O(1) space
    public static String longestPalindrome(String s){
        int n = s.length();
        if ( n==0) return "";
        int start = 0, end = 0;
        int length = 0;
        for (int i = 0; i < n - 1; i++) {
            length = Math.max(helper(s,i,i),length);
            length = Math.max(helper(s,i,i+1),length);
            if ( length > end - start +1) {  // if found longer length, update start and end
                start = i - (length - 1) / 2;  //
                end = i + length / 2;
            }
        }
        return s.substring(start,end+1);
    }

    private static int helper(String s, int i, int j) {
        while ( i>=0 && j<= s.length() - 1 && s.charAt(i) == s.charAt(j)){
            i--;
            j++;
        }
        return j-i-1;  // i and j are out of palindrome range, so minus 1
    }

    // 中心扩散法 时间 O(n^2) space: O(1)
    public String longestPalin2(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            helpersb(s, i, 0, sb);
            helpersb(s, i, 1, sb);
        }
        return sb.toString();
    }

    private void helpersb(String s, int start, int offset, StringBuilder sb) {
        int left = start;
        int right = start + offset;
        while (left >= 0 && right <s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        String cur = s.substring(left + 1, right);
        if (cur.length() > sb.length()) {
            sb.setLength(0);
            sb.append(cur);
        }
    }

    public static void main(String[] args) {
        String s = "ababcc";
        System.out.println(longestPalindrome(s));
        String t = "abcdr";
        System.out.println(longestPalindrome(t));
        System.out.println(longestPal(t));
    }

    // dp approach O(n^2) space, O(n^2) time
    public static String longestPal(String s){
        if ( s == null || s.length() <= 1) return s;
        int len = s.length();
        int maxLen = 0;
        boolean[][] dp = new boolean[len][len];
        String longest = null;
        for (int k = 0; k < len; k++){
            for ( int i = 0; i < len - k; i++){
                int j = i + k;
                if (s.charAt(i) == s.charAt(j) && ( j - i <= 2 || dp[i+1][j-1] )){
                    dp[i][j] = true;
                    if ( j-i+1 > maxLen) {
                        maxLen = j - i + 1;
                        longest = s.substring(i, j + 1);
                    }

                }
            }
        }
        return longest;
    }

    // dp https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0005.%E6%9C%80%E9%95%BF%E5%9B%9E%E6%96%87%E5%AD%90%E4%B8%B2.md
    // 布尔类型的dp[i][j]：表示区间范围[i,j] （注意是左闭右闭）的子串是否是回文子串，如果是dp[i][j]为true，否则为false。
    public String longestPalin(String s) {
        int maxL = 0;
        int start = 0;
        int end = 0;
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {  // i <= j
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i <= 1) {
                        dp[i][j] = true;
                    } else {
                        if (dp[i+1][j-1]) {
                            dp[i][j] = true;
                        }
                    }
                    if (dp[i][j]) {
                        if (j - i + 1 > maxL) {
                            start = i;
                            end = j;
                            maxL = j - i + 1;
                        }
                    }
                }
            }
        }
        return s.substring(start, end + 1);
    }
}
