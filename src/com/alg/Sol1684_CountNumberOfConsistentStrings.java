package com.alg;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
You are given a string allowed consisting of distinct characters and an array of strings words. A string is consistent if all characters in the string appear in the string allowed.

Return the number of consistent strings in the array words.



Example 1:

Input: allowed = "ab", words = ["ad","bd","aaab","baa","badab"]
Output: 2
Explanation: Strings "aaab" and "baa" are consistent since they only contain characters 'a' and 'b'.
Example 2:

Input: allowed = "abc", words = ["a","b","c","ab","ac","bc","abc"]
Output: 7
Explanation: All strings are consistent.
Example 3:

Input: allowed = "cad", words = ["cc","acd","b","ba","bac","bad","ac","d"]
Output: 4
Explanation: Strings "cc", "acd", "ac", and "d" are consistent.
 */
public class Sol1684_CountNumberOfConsistentStrings {
    public int countConsistentStrings(String allowed, String[] words) {
        Set<Character> set = new HashSet<>();
        for (char c: allowed.toCharArray()) {
            set.add(c);
        }
        int count = 0;
        for (String word: words) {
            for (char c: word.toCharArray()) {
                if (!set.contains(c)) {
                    count++;
                    break;
                }
            }
        }
        return words.length - count;
    }

    public static void main(String[] args) {
        String allowed = "abc";
        String[] words = {"a","b","c","ab","ac","bc","abc"};
        System.out.println();
        Sol1684_CountNumberOfConsistentStrings s = new Sol1684_CountNumberOfConsistentStrings();
        int ans = s.countConsistentStrings(allowed, words);
        System.out.println(ans);
    }

    public int countConsistentStrings2(String allowed, String[] words) {
        int counter = 0;
        for (String s : words) {
            boolean isValid = true;
            for (char ch : s.toCharArray()) {
                if (!allowed.contains(String.valueOf(ch))) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) counter++;
        }

        return counter;
    }

    public int countConsistentStrings3(String allowed, String[] words) {
        return Arrays.stream(words)
                .mapToInt(w -> w.chars().allMatch(c -> allowed.contains((char)c + "")) ? 1 : 0)
                .sum();
    }
}
