package com.alg;

import java.util.ArrayList;
import java.util.List;

/*
Given a string s, you can transform every letter individually to be lowercase or uppercase to create another string.

Return a list of all possible strings we could create. Return the output in any order.



Example 1:

Input: s = "a1b2"
Output: ["a1b2","a1B2","A1b2","A1B2"]
Example 2:

Input: s = "3z4"
Output: ["3z4","3Z4"]


Constraints:

1 <= s.length <= 12
s consists of lowercase English letters, uppercase English letters, and digits.
Accepted
185.3K
Submissions

 */
public class Sol784_LetterCasePermutation {
    public List<String> letterCasePermutation(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return res;
        }

        // initialize as the string, not empty
        StringBuilder sb = new StringBuilder(s);
        dfs(s, res, 0, sb);

        return res;
    }

    private void dfs(String s, List<String> res, int start, StringBuilder sb) {
        if (start == s.length()) {
            res.add(sb.toString());
            return;
        }
        char c = s.charAt(start);

        if (Character.isDigit(c)) {
            dfs(s, res, start + 1, sb);
        } else { // is a letter
            sb.setCharAt(start, Character.toUpperCase(c));
            dfs(s, res, start + 1, sb);
            sb.setCharAt(start, Character.toLowerCase(c));
            dfs(s, res, start + 1, sb);
        }
    }


    // https://leetcode.com/problems/letter-case-permutation/discuss/1068361/Java-Backtracking-solution
    // classical with backtracking
    public List<String> letterCasePermutation2(String S) {
        List<String> res = new ArrayList<>();
        if(S == null || S.isEmpty())
            return res;

        dfs2(res, S.toCharArray(), 0);

        return res;
    }

    public void dfs2(List<String> res, char[] arr, int index) {
        res.add(new String(arr));
        for (int i = index; i < arr.length; i++) {
            char ch = arr[i];
            char temp = ch;

            if (ch >= 'a' && ch <= 'z') {
                arr[i] = Character.toUpperCase(ch);
                dfs2(res, arr, i + 1);
            } else if (ch >= 'A' && ch <= 'Z') {
                arr[i] = Character.toLowerCase(ch);
                dfs2(res, arr, i + 1);
            }

            arr[i] = temp;
        }
    }
}
