package com.alg;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
Let's define a function countUniqueChars(s) that returns the number of unique characters on s.

For example if s = "LEETCODE" then "L", "T", "C", "O", "D" are the unique characters since they appear only once in s, therefore countUniqueChars(s) = 5.
Given a string s, return the sum of countUniqueChars(t) where t is a substring of s.

Notice that some substrings can be repeated so in this case you have to count the repeated ones too.



Example 1:

Input: s = "ABC"
Output: 10
Explanation: All possible substrings are: "A","B","C","AB","BC" and "ABC".
Evey substring is composed with only unique letters.
Sum of lengths of all substring is 1 + 1 + 1 + 2 + 2 + 3 = 10
Example 2:

Input: s = "ABA"
Output: 8
Explanation: The same as example 1, except countUniqueChars("ABA") = 1.
Example 3:

Input: s = "LEETCODE"
Output: 92
 */
public class Sol828_CountUniqueCharsInSubstrings {
    // https://leetcode.com/problems/count-unique-characters-of-all-substrings-of-a-given-string/discuss/1195587/Java-Clean-O(n)-Solution-oror-detailed-explanation-of-why-it-works
    public int uniqueLetterString(String s) {
        int[] left = new int[s.length()];
        int[] right = new int[s.length()];
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < left.length; i++) left[i] = i + 1;
        for (int i = 0; i < right.length; i++) right[i] = s.length() - i;
        int sum = 0;
        int modulo = 1000000007;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (map.containsKey(ch)) {
                int distance = i - map.get(ch);
                left[i] = distance;
            }
            map.put(ch, i);
        }
        map.clear();
        for (int i = s.length() - 1;i >= 0;i--) {
            char ch = s.charAt(i);
            if (map.containsKey(ch)) {
                int distance = map.get(ch) - i;
                right[i] = distance;
            }
            map.put(ch, i);
        }
        for (int i = 0;i < s.length();i++) {
            sum = (sum + (left[i] * right[i])) % modulo;
        }
        return sum;
    }

    public static void main(String[] args) {
        String s = "abc";
        Sol828_CountUniqueCharsInSubstrings ss = new Sol828_CountUniqueCharsInSubstrings();
        System.out.println(ss.uniqueLetterString(s));
    }

    public int countUnique(String s) {
        int n = s.length();
        int[] leftPos = new int[n]; // store the position of previous appearance of s[i], if no appearance, set as -1
        Arrays.fill(leftPos, -1);
        int[] rightPos = new int[n];
        Arrays.fill(rightPos, -1);
        int[] count = new int[26];
        Arrays.fill(count, -1);
        // ....a.....a.....
        for (int i = 0; i < n ; i++) {
            char c = s.charAt(i);
            if (count[c-'A'] != -1) {
                leftPos[i] = count[c-'A'];
            }
            count[c-'A'] = i;
        }
        Arrays.fill(count, -1);
        for (int i = n - 1;i>=0; i--) {
            char c = s.charAt(i);
            if (count[c - 'A'] != -1) {
                rightPos[i] = count[c - 'A'];
            }
            count[c - 'A'] = i;
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            int leftBound = leftPos[i] == -1 ? 0 : leftPos[i] + 1;
            int rightBound = rightPos[i] == -1 ? n - 1 : rightPos[i] - 1;
            res += (i - leftBound + 1) * ( rightBound - i + 1);  // pick one from left range and one from right range, then multiply
        }
        return res;
    }
}
