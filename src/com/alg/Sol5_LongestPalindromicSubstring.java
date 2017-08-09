package com.alg;

/**
 * Created by HAU on 7/25/2017.
 */
/*Given a string s, find the longest palindromic substring in s.
        You may assume that the maximum length of s is 1000.*/
public class Sol5_LongestPalindromicSubstring {
    public static String longestPalindrome(String s){
        int n = s.length();
        if ( n==0) return "";
        int start = 0, end = 0;
        int length = 0;
        for (int i = 0; i < n - 1; i++){
            length = Math.max(helper(s,i,i),length);
            length = Math.max(helper(s,i,i+1),length);
            if ( length > end - start +1) {  // if found longer length, update start and end
                start = i - (length - 1) / 2;
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
        return j-i-1;
    }

    public static void main(String[] args) {
        String s = "ababcc";
        System.out.println(longestPalindrome(s));
    }
}
