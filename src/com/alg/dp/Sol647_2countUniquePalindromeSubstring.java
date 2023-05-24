package com.alg.dp;

import java.util.HashSet;

/**
 Given a string s, return the number of palindromic substrings in it.

 A string is a palindrome when it reads the same backward as forward.

 A substring is a contiguous sequence of characters within the string.



 Example 1:

 Input: s = "abc"
 Output: 3
 Explanation: Three palindromic strings: "a", "b", "c".
 Example 2:

 Input: s = "aaa"
 Output: 6
 Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 */
/*“aabaa ” 返回5， 有 [a, b, aba, aa, aabaa], 楼主用的dp，*/
public class Sol647_2countUniquePalindromeSubstring {
    public static int countUniquePalSubstrings(String s) {
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++){
            helper(s,i,i,set);
            helper(s,i,i+1,set);
        }
        return set.size();
    }

    private static void helper(String s, int i, int j, HashSet<String> set) {
        while( i>=0 && j<s.length() && s.charAt(i)== s.charAt(j)){
            set.add(s.substring(i,j+1));
            i--;j++;
        }
    }

    public static void main(String[] args) {
        String s = "aabaa";
        System.out.println(countUniquePalSubstrings(s));
        Sol647_2countUniquePalindromeSubstring ss = new Sol647_2countUniquePalindromeSubstring();
        System.out.println(ss.countSubstrings2("abc"));
    }

    // https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0647.%E5%9B%9E%E6%96%87%E5%AD%90%E4%B8%B2.md
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        boolean[][] dp =new boolean[n][n];

        //dp[i][j] i==j?  i+1,j-1
        // 一定要从下到上，从左到右遍历，这样保证dp[i + 1][j - 1]都是经过计算的。
        for (int i = n-1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                // //当两端字母一样时，才可以两端收缩进一步判断
                if (s.charAt(i) == s.charAt(j)) {
                    // //i++，j--，即两端收缩之后i,j指针指向同一个字符或者i超过j了,必然是一个回文串
                    if (j - i <= 2) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i+1][j-1];
                    }
                } else {
                    dp[i][j] = false;
                }
            }
        }
        int res = 0;
        for (boolean[] rows: dp) {
            for (boolean item: rows) {
                if (item) {
                    res += 1;
                }
            }
        }
        return res;
    }

    // Expand Around Possible Centers
    public int countSubstrings2(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            res += count(s, i, i);
            res += count(s, i, i+1);
        }
        return res;
    }

    private int count(String s, int left, int right) {
        int c = 0;
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) != s.charAt(right)) {
                return c;
            }
            c++;
            left--; right++;
        }
        return c;
    }

}
