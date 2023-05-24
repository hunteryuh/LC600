package com.alg;

import java.util.Stack;

/*
Given two strings s and t, return true if they are equal when both are typed into empty text editors. '#' means a backspace character.

Note that after backspacing an empty text, the text will continue empty.



Example 1:

Input: s = "ab#c", t = "ad#c"
Output: true
Explanation: Both s and t become "ac".
Example 2:

Input: s = "ab##", t = "c#d#"
Output: true
Explanation: Both s and t become "".
Example 3:

Input: s = "a#c", t = "b"
Output: false
Explanation: s becomes "c" while t becomes "b".


Constraints:

1 <= s.length, t.length <= 200
s and t only contain lowercase letters and '#' characters.
 */
public class Sol844_BackspaceStringCompare {
    public boolean backspaceCompare(String s, String t) {
        if (s == null || t == null) return s == t;
        int m = s.length();
        int n = t.length();
        int i = m - 1;
        int j = n - 1;
        int count1 = 0;
        int count2 = 0;
        while (i >= 0 && j >= 0) {
            while (i >= 0) {
                // find position of next possible char in build(s)
                if (s.charAt(i) == '#') {
                    count1++;
                    i--;
                } else if ( count1 > 0) {
                    count1--;
                    i--;
                } else {
                    break;
                }
            }

            while (j >= 0) {
                if (t.charAt(j) == '#') {
                    count2++;
                    j--;
                } else if (count2 > 0) {
                    count2--;
                    j--;
                } else {
                    break;
                }
            }
            if (i >= 0 && j >= 0 && s.charAt(i) != t.charAt(j)) {
                return false;
            }
            if ( (i >= 0) != (j >= 0)) {
                return false;
            }
            i--;
            j--;
        }
        return true;
    }


    // O(M+N) time and space
    public boolean backspaceCompare2(String S, String T) {
        return build(S).equals(build(T));
    }

    public String build(String S) {
        Stack<Character> ans = new Stack();
        for (char c: S.toCharArray()) {
            if (c != '#')
                ans.push(c);
            else if (!ans.empty())
                ans.pop();
        }
        String aa = String.valueOf(ans);
        String ab = ans.toString();
        System.out.println(aa);
        System.out.println(ab);
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        String s= "ab#d";
        String t = "c#ad";
        Sol844_BackspaceStringCompare aa = new Sol844_BackspaceStringCompare();
        aa.backspaceCompare2(s, t);
    }
}
