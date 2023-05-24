package com.alg;

import java.util.Stack;

/*
Given a balanced parentheses string s, compute the score of the string based on the following rule:

() has score 1
AB has score A + B, where A and B are balanced parentheses strings.
(A) has score 2 * A, where A is a balanced parentheses string.


Example 1:

Input: s = "()"
Output: 1
Example 2:

Input: s = "(())"
Output: 2
Example 3:

Input: s = "()()"
Output: 2
Example 4:

Input: s = "(()(()))"
Output: 6
 */
public class Sol856_ScoreOfParenthesis {
    // https://leetcode.com/problems/score-of-parentheses/discuss/141763/Java-solution-using-Stack
    public int scoreOfParentheses(String s) {
        int cur = 0;
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(cur);
                cur = 0;
            } else if (c == ')') {
                cur = stack.pop();
                if (s.charAt(i - 1) == '(') {
                    cur += 1;
                } else {
                    cur += res * 2;
                }
                res = cur;
            }
        }
        return res;
    }

    public int scoreOfBrackets(String S) {
        Stack<Integer> stack = new Stack<>();
        int cur = 0;
        for (char c : S.toCharArray()) {
            if (c == '(') {
                stack.push(cur);
                cur = 0;
            } else {
                cur = stack.pop() + Math.max(cur * 2, 1);
            }
        }
        return cur;
    }
}
