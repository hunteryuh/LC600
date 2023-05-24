package com.alg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
You are given a string s and an array of strings words of the same length. Return all starting indices of substring(s) in s that is a concatenation of each word in words exactly once, in any order, and without any intervening characters.

You can return the answer in any order.



Example 1:

Input: s = "barfoothefoobarman", words = ["foo","bar"]
Output: [0,9]
Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
The output order does not matter, returning [9,0] is fine too.
Example 2:

Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
Output: []
Example 3:

Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
Output: [6,9,12]
 */
public class Sol30_SubstringwithConcatenationofAllWords {

    private HashMap<String, Integer> wordCount = new HashMap<>();
    private int wordLength;
    private int substringSize;
    private int k;

    // https://leetcode.com/problems/substring-with-concatenation-of-all-words/solution/
    // tune Given nn as the length of s, aa as the length of words, and bb as the length of each word:
    // Time Com: That means each call to check uses O(a + a \cdot (b + 1))O(a+a⋅(b+1)) time,
    // This gives us a time complexity of O((n−a⋅b)⋅a⋅b), which can be expanded to O(n \cdot a \cdot b - (a \cdot b) ^ 2)O(n⋅a⋅b−(a⋅b)
    //2
    // ).
    public List<Integer> findSubstring(String s, String[] words) {

        int n = s.length();
        k = words.length;
        wordLength = words[0].length();
        substringSize = wordLength  * k;

        for (String word: words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n - substringSize + 1; i++) {
            if (isValid(i, s)) {
                res.add(i);
            }
        }
        return res;
    }

    private boolean isValid(int i, String s) {
        // copy the original dictionary to use for the index
        Map<String, Integer> remaining = new HashMap<>(wordCount);
        int wordUsed = 0;
        for (int j = i; j < i + substringSize; j += wordLength) {
            String sub = s.substring(j, j + wordLength);
            if (remaining.getOrDefault(sub, 0) != 0) {
                remaining.put(sub, remaining.get(sub) - 1);
                wordUsed++;
            } else {
                break;
            }
        }
        return k == wordUsed;

    }


    // sliding window
    public List<Integer> findSubstring2(String s, String[] words) {

        int n = s.length();
        k = words.length;
        wordLength = words[0].length();
        substringSize = wordLength  * k;

        for (String word: words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < wordLength; i++) {
            applySlidingWindow(i, s, res);
        }
        return res;
    }

    private void applySlidingWindow(int left, String s, List<Integer> res) {
        HashMap<String, Integer> wordsFound = new HashMap<>();
        int wordsUsed = 0;
        boolean excessiveWord = false;
        for (int right = left; right <= s.length() - wordLength; right += wordLength) {
            String sub = s.substring(right, right + wordLength);
            if (!wordCount.containsKey(sub)) {
                // mismatched word -- reset the window
                wordsFound.clear();
                wordsUsed = 0;
                excessiveWord = false;
                left = right + wordLength;
            } else {
                // if we reach max window size or have an excess word
                while (right - left == substringSize || excessiveWord) {
                    String leftMostWord = s.substring(left, left + wordLength);
                    left += wordLength;
                    wordsFound.put(leftMostWord, wordsFound.get(leftMostWord) - 1);

                    if (wordsFound.get(leftMostWord) >= wordCount.get(leftMostWord)) {
                        excessiveWord = false; // why?
                    } else {
                        wordsUsed--;
                    }
                }
                // keep track of how many times this word occurs in the window
                wordsFound.put(sub, wordsFound.getOrDefault(sub, 0) + 1);
                if (wordsFound.get(sub) <= wordCount.get(sub)) {
                    wordsUsed++;
                } else {
                    excessiveWord = true;
                }
                if (wordsUsed == k && !excessiveWord) {
                    res.add(left);
                }
            }
        }
    }

}
