package com.alg;
/*
Given a string s, return the number of substrings that have only one distinct letter.



Example 1:

Input: s = "aaaba"
Output: 8
Explanation: The substrings with one distinct letter are "aaa", "aa", "a", "b".
"aaa" occurs 1 time.
"aa" occurs 2 times.
"a" occurs 4 times.
"b" occurs 1 time.
So the answer is 1 + 2 + 4 + 1 = 8.

Example 2:

Input: s = "aaaaaaaaaa"
Output: 55



Constraints:

    1 <= s.length <= 1000
    s[i] consists of only lowercase English letters.

https://leetcode.com/problems/count-substrings-with-only-one-distinct-letter/solution/
 */
public class Sol1180_CountSubstringsWithOnlyOneDistinctLetter {
    // two pointer + arithmetic progression

    public int countLetters(String s) {
        int res = 0;
        for (int left = 0, right = 0; right <= s.length(); right++) {
            if (right == s.length() || s.charAt(left) != s.charAt(right)) {
                int sub = right - left;
                res += sub * (1 + sub) / 2;
                left = right;
            }
        }
        return res;
    }

    // dynamic programming  T: O(n) S: O(n)
    public int countLetters2(String s) {
        int n = s.length();
        int[] subs = new int[n];
        int res = 1;
        subs[0] = 1;
        for (int i = 1; i <n; i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                subs[i] = subs[i-1] + 1;
            } else {
                subs[i] = 1;
            }
            res += subs[i];
        }
        return res;
    }
    // O(1) space
    public int countLetters3(String S) {
        int total = 1, count = 1;
        for (int i = 1; i < S.length(); i++) {
            if (S.charAt(i) == S.charAt(i-1)) {
                count++;
            } else {
                count = 1;
            }
            total += count;
        }
        return total;
    }
}
