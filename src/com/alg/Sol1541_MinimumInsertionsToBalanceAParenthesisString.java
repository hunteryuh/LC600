package com.alg;

import java.util.Stack;

/*
Given a parentheses string s containing only the characters '(' and ')'. A parentheses string is balanced if:

Any left parenthesis '(' must have a corresponding two consecutive right parenthesis '))'.
Left parenthesis '(' must go before the corresponding two consecutive right parenthesis '))'.
In other words, we treat '(' as openning parenthesis and '))' as closing parenthesis.

For example, "())", "())(())))" and "(())())))" are balanced, ")()", "()))" and "(()))" are not balanced.

You can insert the characters '(' and ')' at any position of the string to balance it if needed.

Return the minimum number of insertions needed to make s balanced.



Example 1:

Input: s = "(()))"
Output: 1
Explanation: The second '(' has two matching '))', but the first '(' has only ')' matching. We need to to add one more ')' at the end of the string to be "(())))" which is balanced.
Example 2:

Input: s = "())"
Output: 0
Explanation: The string is already balanced.
Example 3:

Input: s = "))())("
Output: 3
Explanation: Add '(' to match the first '))', Add '))' to match the last '('.
Example 4:

Input: s = "(((((("
Output: 12
Explanation: Add 12 ')' to balance the string.
Example 5:

Input: s = ")))))))"
Output: 5
Explanation: Add 4 '(' at the beginning of the string and one ')' at the end. The string becomes "(((())))))))".
 */
public class Sol1541_MinimumInsertionsToBalanceAParenthesisString {
    // self debugged and test
    public int minInsertions(String s) {
        int res = 0;
        Stack<Character> stack = new Stack<>();
        int close = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(c);
            } else if (c == ')' && !stack.isEmpty()) {
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    stack.pop();
                    i++;
                } else {
                    stack.pop();
                    res++;
                }
            } else {
                close++;
                if (close == 2) {
                    res++;
                    close = 0;
                } else if (close == 1) {
                    if (i + 1 >= s.length() || s.charAt(i + 1) == '(') {
                        res += 2;
                        close = 0;
                    }
                }
            }
        }
        while (!stack.isEmpty()) {
            stack.pop();
            res += 2;
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "))(()()))()))))))()())()(())()))))()())(()())))()(";
        String f = ")()))";
        Sol1541_MinimumInsertionsToBalanceAParenthesisString a = new Sol1541_MinimumInsertionsToBalanceAParenthesisString();
        System.out.println(a.minInsertions(f));
        System.out.println(a.minInsertions(s));
    }

    // https://leetcode.com/problems/minimum-insertions-to-balance-a-parentheses-string/discuss/780199/JavaC%2B%2BPython-Straight-Forward-One-Pass
    public int minInsertions2(String s) {
        int res = 0, right = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(') {
                if (right % 2 > 0) {
                    right--;
                    res++;
                }
                right += 2;
            } else {
                right--;
                if (right < 0) {
                    right += 2;
                    res++;
                }
            }
        }
        return right + res;
    }

    public int minInsertions3(String s) {
        int ans = 0;
        int cnt = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                cnt += 2;
                if (cnt % 2 != 0) {
                    ans++;
                    cnt--;
                }
            } else {
                cnt -= 1;
                if (cnt < 0) {
                    ans++;
                    cnt += 2;
                }
            }
        }

        return ans + cnt;

    }
}
