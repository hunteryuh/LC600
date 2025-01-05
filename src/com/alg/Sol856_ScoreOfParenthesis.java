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
        Stack<Integer> stack = new Stack<>(); // stack of Integer, not Character
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
            System.out.println(cur + " " + res);
        }
        return res;
    }

    // https://leetcode.com/problems/score-of-parentheses/solutions/141777/c-java-python-o-1-space/
    // time O(N) space O(n)
    public int scoreOfBrackets(String S) {
        Stack<Integer> stack = new Stack<>();
        int cur = 0;
        for (char c : S.toCharArray()) {
            if (c == '(') {
                stack.push(cur);
                cur = 0;
            } else {
                cur = stack.pop() + Math.max(cur * 2, 1); // or  cur += stack.top() + max(cur, 1);
            }
        }
        return cur;
    }

    // space O(1)
    public int scoreOfParentheses2(String S) {
        int res = 0, depth = 0;
        for (int i = 0; i < S.length(); ++i) {
            if (S.charAt(i) == '(') depth++; else depth--;
            if (S.charAt(i) == ')' && S.charAt(i - 1) == '(') {
                res += 1 << depth; // for "(())", 1 left shift 1 bit 1 -> 10 when l == 1 at i == 2
                // Whenever you meet a () pair, you multiply 1 by all the 2 outside of it, and accumulate the result
//                score += Math.pow(2, depth);
                System.out.println("res is " + res);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Sol856_ScoreOfParenthesis ss = new Sol856_ScoreOfParenthesis();
        String s = "(()(()))";
        System.out.println(ss.scoreOfParentheses(s));
    }


}
