package com.alg;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
A string s is called good if there are no two different characters in s that have the same frequency.

Given a string s, return the minimum number of characters you need to delete to make s good.

The frequency of a character in a string is the number of times it appears in the string. For example, in the string "aab", the frequency of 'a' is 2, while the frequency of 'b' is 1.



Example 1:

Input: s = "aab"
Output: 0
Explanation: s is already good.
Example 2:

Input: s = "aaabbbcc"
Output: 2
Explanation: You can delete two 'b's resulting in the good string "aaabcc".
Another way it to delete one 'b' and one 'c' resulting in the good string "aaabbc".
Example 3:

Input: s = "ceabaacb"
Output: 2
Explanation: You can delete both 'c's resulting in the good string "eabaab".
Note that we only care about characters that are still in the string at the end (i.e. frequency of 0 is ignored).
 */
public class Sol1647_MinDeletionsToMakeCharFreqUnique {
    // https://leetcode.com/problems/minimum-deletions-to-make-character-frequencies-unique/discuss/1205548/Java-Using-HashSet
    public int minDeletions(String s) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        Set<Integer> set = new HashSet<>();
        int res = 0;
        for (int count : freq) {
            while (count > 0 && set.contains(count)) {
                count--;
                res++;
            }
            set.add(count);
        }
        return res;
    }

    public int minDeletions2(String s) {
        int[] freq = new int[26];
        for (char c: s.toCharArray()) {
            freq[c - 'a']++;
        }
        Arrays.sort(freq);
        int expected = freq[25];
        int res = 0;
        for (int i = 25; i >= 0; i--) {
            if (freq[i] > expected) {
                res += freq[i] - expected;
            } else {
                expected = freq[i];
            }
            if (expected > 0) expected--;
        }
        return res;
    }
}
