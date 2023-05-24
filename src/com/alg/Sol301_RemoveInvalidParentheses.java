package com.alg;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses to make the input string valid.

Return all the possible results. You may return the answer in any order.



Example 1:

Input: s = "()())()"
Output: ["(())()","()()()"]
Example 2:

Input: s = "(a)())()"
Output: ["(a())()","(a)()()"]
Example 3:

Input: s = ")("
Output: [""]


Constraints:

1 <= s.length <= 25
s consists of lowercase English letters and parentheses '(' and ')'.
There will be at most 20 parentheses in s.
 */
public class Sol301_RemoveInvalidParentheses {
    //https://leetcode.com/problems/remove-invalid-parentheses/discuss/75034/Easiest-9ms-Java-Solution
    public List<String> removeInvalidParentheses(String s) {
        int rmL = 0;
        int rmR = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                rmL++;
            } else if (c == ')') {
                if (rmL == 0) {
                    rmR++;
                } else {
                    rmL--;
                }
            }
        }
        Set<String> set = new HashSet<>();
        dfs(s, 0, set, new StringBuilder(), rmL, rmR, 0);  // open get + 1 when it meets an open bracket (
        return new ArrayList<>(set);
    }

    private void dfs(String s, int i, Set<String> set, StringBuilder sb, int rmL, int rmR, int open) {
        if (rmL < 0 || rmR < 0 || open < 0) return;  // if open < 0 that is invalid
        if (i == s.length()) {
            if (rmL == 0 && rmR == 0 && open == 0) {
                set.add(sb.toString());
//                return;  // wrong
            }
            return;  // need to return no matter there is a result or not
        }
        char c = s.charAt(i);
        int len = sb.length();
        if (c == '(') {
            dfs(s, i + 1, set, sb, rmL - 1, rmR, open); // not use (
            dfs(s, i +1, set, sb.append(c), rmL, rmR, open + 1);   // use (
        } else if ( c == ')') {
            dfs(s, i + 1, set, sb, rmL, rmR - 1, open); // not use )
            dfs(s ,i + 1, set, sb.append(c), rmL, rmR, open - 1); // use )
        } else {
            dfs(s, i + 1, set, sb.append(c), rmL, rmR, open);
        }
        sb.setLength(len);  // same as sb.deleteCharAt(sb.length() - 1);
    }


}
