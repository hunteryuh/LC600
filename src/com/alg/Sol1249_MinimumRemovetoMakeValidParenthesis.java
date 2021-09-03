package com.alg;

import java.util.Stack;

/*
Given a string s of '(' , ')' and lowercase English characters.

Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.

Formally, a parentheses string is valid if and only if:

It is the empty string, contains only lowercase characters, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.


Example 1:

Input: s = "lee(t(c)o)de)"
Output: "lee(t(c)o)de"
Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
Example 2:

Input: s = "a)b(c)d"
Output: "ab(c)d"
Example 3:

Input: s = "))(("
Output: ""
Explanation: An empty string is also valid.
Example 4:

Input: s = "(a(b(c)d)"
Output: "a(b(c)d)"

medium

interviewed by  this one at 7/8/2021
 */
public class Sol1249_MinimumRemovetoMakeValidParenthesis {
    // https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/discuss/419702/Java-O(n)-solution-without-Stack
    public String minRemoveToMakeValid(String s) {
        boolean[] remove = new boolean[s.length()];
        int open = 0;
        for(int i = 0; i< s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                open++;
            } else if( c == ')') {
                if (open > 0) {
                    open--;
                } else {
                    remove[i] = true;
                }
            }
        }
        int close = 0;
        for (int i = s.length() - 1; i >=0; i--) {
            char c = s.charAt(i);
            if (c == ')') {
                close++;
            } else if (c == '(') {
                if (close > 0) {
                    close--;
                } else {
                    remove[i] = true;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < remove.length; i++) {
            if (!remove[i]) {
                sb.append(s.charAt(i));
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String input = "(a(b(c)d)";
        Sol1249_MinimumRemovetoMakeValidParenthesis s = new Sol1249_MinimumRemovetoMakeValidParenthesis();
        System.out.println(s.minRemoveToMakeValid(input));
    }


    // https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/discuss/419443/Java-Clean-StringBuilder-solution
    public String minRemoveToMakeValid2(String s) {
        StringBuilder sb = new StringBuilder();
        int open = 0;
        for (char c : s.toCharArray()) {
            if (c == '(')
                open++;
            else if (c == ')') {
                if (open == 0) continue;
                open--;
            }
            sb.append(c);
        }
            // ))((     ((
        StringBuilder result = new StringBuilder();
        for (int i = sb.length() - 1; i >= 0; i--) {
            if (sb.charAt(i) == '(' && open-- > 0) continue;
            result.append(sb.charAt(i));
        }

        return result.reverse().toString();
    }

    // with Stack
    // https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/discuss/419402/JavaC%2B%2B-Stack
    public String minRemoveToMakeValid3(String s) {
        StringBuilder sb = new StringBuilder(s);
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < sb.length(); ++i) {
            if (sb.charAt(i) == '(') st.add(i);
            if (sb.charAt(i) == ')') {
                if (!st.empty()) st.pop();
                else sb.setCharAt(i, '*');
            }
        }
        while (!st.empty())
            sb.setCharAt(st.pop(), '*');
        return sb.toString().replaceAll("\\*", "");
    }
}
