package com.alg;

import java.util.*;

/*
Given a string s and an array of strings words, return the number of words[i] that is a subsequence of s.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

    For example, "ace" is a subsequence of "abcde".



Example 1:

Input: s = "abcde", words = ["a","bb","acd","ace"]
Output: 3
Explanation: There are three strings in words that are a subsequence of s: "a", "acd", "ace".

Example 2:

Input: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
Output: 2



Constraints:

    1 <= s.length <= 5 * 104
    1 <= words.length <= 5000
    1 <= words[i].length <= 50
    s and words[i] consist of only lowercase English letters.


 */
public class Sol792_NumberOfMatchingSubsequences {
    // brute force
    // https://leetcode.com/problems/number-of-matching-subsequences/solutions/2306416/java-easy-solution-97-faster-code/
    public int numOfMatchingSubsequences(String s, String[] words) {
        Map<String, Integer> wordMap = new HashMap<>();
        // length of words
        for (String word: words) {
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }
        int res = 0;
        // number of unique word in words * min(word length, s length)
        for (String word: wordMap.keySet()) {
            int i = 0;
            int j = 0;
            while (i < word.length() && j < s.length()) {
                if (word.charAt(i) == s.charAt(j)) {
                    i++;
                    j++;
                } else {
                    j++;
                }
            }
            if (i == word.length()) {
                res += wordMap.get(word);
            }
        }
        return res;

    }

    // map + queue (linkedlist)
    // https://leetcode.com/problems/number-of-matching-subsequences/solutions/117598/java-solution-using-hashmap-and-queue/
    public int numMatchingSubseq(String s, String[] words) {
        Map<Character, Queue<String>> map = new HashMap<>();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            map.putIfAbsent(s.charAt(i), new LinkedList<>());
        }
        for (String word: words) {
            char c = word.charAt(0);
            if (map.containsKey(c)) {
                map.get(c).offer(word);
            }
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Queue<String> queue = map.get(c);
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                String word = queue.poll();
                if (word.length() == 1) {
                    count++;
                } else {
                    if (map.containsKey(word.charAt(1))) {
                        map.get(word.charAt(1)).offer(word.substring(1));
                    }
                }

            }
        }
        return count;

    }
}
