package com.alg;
/*
You are given a string s. You can convert s to a palindrome by adding characters in front of it.

Return the shortest palindrome you can find by performing this transformation.



Example 1:

Input: s = "aacecaaa"
Output: "aaacecaaa"

Example 2:

Input: s = "abcd"
Output: "dcbabcd"



Constraints:

    0 <= s.length <= 5 * 104
    s consists of lowercase English letters only.


 */
public class Sol214_ShortestPalindrome {
    // https://leetcode.wang/leetcode-214-Shortest-Palindrome.html
    public String shortestPalindrome0(String s) {
        String rev = new StringBuilder(s).reverse().toString();
        int n = s.length();
        int i = 0;
        for (i = 0; i < s.length(); i++) {
            if (s.substring(0, n - i).equals(rev.substring(i))) {
                break;
            }
        }
        // i = 3 end of prefix palindrome
        String res = rev.substring(0, i) + s;
        return res;
    }

    // https://leetcode.com/problems/shortest-palindrome/solution/
    // 2 pointer and recursion
    public String shortestPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        char[] c = s.toCharArray();
        while (j >= 0) {
            if (c[i] == c[j]) {
                i++;
            }
            j--;
        }
        //此时代表整个字符串是回文串
        if (i == s.length()) {
            return s;
        }
        //后缀
        String suffix = s.substring(i);
        //后缀倒置
        String reverse = new StringBuilder(suffix).reverse().toString();
        //递归 s[0,i),寻找开头开始的最长回文串，将其余部分加到开头和结尾
        return reverse + shortestPalindrome(s.substring(0, i)) + suffix;
    }
}
