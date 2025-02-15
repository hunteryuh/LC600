package com.alg;
/*
A parentheses string is valid if and only if:

It is the empty string,
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.
You are given a parentheses string s. In one move, you can insert a parenthesis at any position of the string.

For example, if s = "()))", you can insert an opening parenthesis to be "(()))" or a closing parenthesis to be "())))".
Return the minimum number of moves required to make s valid.



Example 1:

Input: s = "())"
Output: 1
Example 2:

Input: s = "((("
Output: 3


Constraints:

1 <= s.length <= 1000
s[i] is either '(' or ')'.
 */
public class Sol921_MinimumAddToMakeParenthesesValid {
    // https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/description/
    // https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/solutions/181132/c-java-python-straight-forward-one-pass/
    public int minAddtoMakeValid(String s) {
        int left = 0;
        int right = 0;  // left right 代表要加的左bracket和右bracket
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                right++; // 有( 需要右Bracket来补
            }
            if (c == ')') {
                if (right > 0) right--;
                else left++;
            }
        }
        return left + right;
    }

    public int minAddToMakeValid(String S) {
        int left = 0, right = 0;
        for (int i = 0; i < S.length(); ++i) {
            if (S.charAt(i) == '(') {
                right++;
            } else if (right > 0) {
                right--;
            } else {
                left++;
            }
        }
        return left + right;
    }

    public int minAddToMakeValid2(String s) {
        int open = 0, close = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(') {
                open++;
            } else {
                if (open > 0) {
                    open--;
                } else {
                    close++;
                }
            }
        }
        return open + close;
    }
}
