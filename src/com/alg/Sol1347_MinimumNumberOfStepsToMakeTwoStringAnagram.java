package com.alg;
/*
You are given two strings of the same length s and t. In one step you can choose any character of t and replace it with another character.

Return the minimum number of steps to make t an anagram of s.

An Anagram of a string is a string that contains the same characters with a different (or the same) ordering.



Example 1:

Input: s = "bab", t = "aba"
Output: 1
Explanation: Replace the first 'a' in t with b, t = "bba" which is anagram of s.

Example 2:

Input: s = "leetcode", t = "practice"
Output: 5
Explanation: Replace 'p', 'r', 'a', 'i' and 'c' from t with proper characters to make t anagram of s.

Example 3:

Input: s = "anagram", t = "mangaar"
Output: 0
Explanation: "anagram" and "mangaar" are anagrams.



Constraints:

    1 <= s.length <= 5 * 104
    s.length == t.length
    s and t consist of lowercase English letters only.


 */
public class Sol1347_MinimumNumberOfStepsToMakeTwoStringAnagram {
    public int minSteps(String s, String t) {
        int res = 0;
        int[] freq = new int[26];
        for (char c: s.toCharArray()) {
            freq[c - 'a']++;
        }
        for (char c: t.toCharArray()) {
            if (freq[c - 'a'] > 0) {
                freq[c - 'a']--;
            } else {
                res++;
            }
        }
        return res;
    }

    public int minSteps2(String s, String t) {
        int[] count = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            ++count[s.charAt(i) - 'a']; // count the occurrences of chars in s.
            --count[t.charAt(i) - 'a']; // compute the difference between s and t.
        }
        // return Arrays.stream(count).map(Math::abs).sum() / 2; // sum the absolute of difference and divide it by 2.
        // return Arrays.stream(count).filter(i -> i > 0).sum(); // sum the positive values.
        int steps = 0;
        for (int step : count) {
            if (step > 0) {
                steps += step;
            }
        }
        return steps;
    }
}
