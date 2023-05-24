package com.alg;
/*
You are given a string s consisting only of lowercase English letters.

In one move, you can select any two adjacent characters of s and swap them.

Return the minimum number of moves needed to make s a palindrome.

Note that the input will be generated such that s can always be converted to a palindrome.



Example 1:

Input: s = "aabb"
Output: 2
Explanation:
We can obtain two palindromes from s, "abba" and "baab".
- We can obtain "abba" from s in 2 moves: "aabb" -> "abab" -> "abba".
- We can obtain "baab" from s in 2 moves: "aabb" -> "abab" -> "baab".
Thus, the minimum number of moves needed to make s a palindrome is 2.

Example 2:

Input: s = "letelt"
Output: 2
Explanation:
One of the palindromes we can obtain from s in 2 moves is "lettel".
One of the ways we can obtain it is "letelt" -> "letetl" -> "lettel".
Other palindromes such as "tleelt" can also be obtained in 2 moves.
It can be shown that it is not possible to obtain a palindrome in less than 2 moves.



Constraints:

    1 <= s.length <= 2000
    s consists only of lowercase English letters.
    s can be converted to a palindrome using a finite number of moves.


 */
public class Sol2193_MinimumNumberOfMovesToMakePalindrome {
    // https://leetcode.com/problems/minimum-number-of-moves-to-make-palindrome/solutions/1822174/c-python-short-greedy-solution/
    // greedy  time : O(n^2)
    public int minMovesToMakePalindrome(String s) {
        int res = 0;
        while (s.length() > 0) {
            int i = s.indexOf(s.charAt(s.length() - 1));
            if (i == s.length() - 1) res += i / 2;  // bbcca
            else {
                res += i;
                s = s.substring(0, i) + s.substring(i + 1); // remove ith and the last char
            }
            s = s.substring(0, s.length() - 1);
        }
        return res;
    }

    // greedy, using stringbuilder
    // https://leetcode.com/problems/minimum-number-of-moves-to-make-palindrome/solutions/1821950/java-solution-using-greedy/
    public int minMovesToMakePalindrome2(String s) {
        int count = 0;
        StringBuilder sb = new StringBuilder(s);
        while (sb.length() > 2) {
            char ch1 = sb.charAt(0);
            int len = sb.length();
            char ch2 = sb.charAt(len - 1);

            if (ch1 == ch2) {
                sb.deleteCharAt(len - 1);
                sb.deleteCharAt(0);
            } else {
                int id1 = sb.lastIndexOf(Character.toString(ch1));
                int id2 = sb.indexOf(Character.toString(ch2));

                int steps1 = len - id1 - 1;
                int steps2 = id2;

                if (steps1 > steps2) { // keep last char, move char at id2
                    count += steps2;
                    sb.deleteCharAt(id2);
                    sb.deleteCharAt(sb.length() - 1);
                } else {// keep first char, move char at id1
                    count += steps1;
                    sb.deleteCharAt(id1);
                    sb.deleteCharAt(0);
                }

            }
        }

        return count;
    }
}
