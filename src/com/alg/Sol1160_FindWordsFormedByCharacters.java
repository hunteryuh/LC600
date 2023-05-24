package com.alg;

import java.util.HashMap;
import java.util.Map;

/*
You are given an array of strings words and a string chars.

A string is good if it can be formed by characters from chars (each character can only be used once).

Return the sum of lengths of all good strings in words.



Example 1:

Input: words = ["cat","bt","hat","tree"], chars = "atach"
Output: 6
Explanation: The strings that can be formed are "cat" and "hat" so the answer is 3 + 3 = 6.
Example 2:

Input: words = ["hello","world","leetcode"], chars = "welldonehoneyr"
Output: 10
Explanation: The strings that can be formed are "hello" and "world" so the answer is 5 + 5 = 10.


Constraints:

1 <= words.length <= 1000
1 <= words[i].length, chars.length <= 100
words[i] and chars consist of lowercase English letters.
 */
public class Sol1160_FindWordsFormedByCharacters {
    public int countCharacters(String[] words, String chars) {
        int res = 0;
        Map<Character, Integer> map = new HashMap<>();
        for(char c : chars.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (String word : words) {
            boolean isValid = true;
            Map<Character, Integer> wordMap = new HashMap<>();
            for (char wc: word.toCharArray()) {
                if (!map.containsKey(wc)) {
                    isValid = false;
                    break;
                }
                wordMap.put(wc, wordMap.getOrDefault(wc, 0) + 1);
                if (wordMap.get(wc) > map.get(wc)) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                res += word.length();
            }

        }
        return res;
    }

    // array count
    public int countCharacters2(String[] words, String chars) {
        int[] charsCount = new int[26];
        int sum=0;

        for(char c: chars.toCharArray()) {
            charsCount[c-'a']++;
        }

        for(String s: words) {
            if(isGood(s, charsCount)) {
                sum+=s.length();
            }
        }
        return sum;
    }

    private boolean isGood(String s, int[] charsCount) {
        int[] wordCount = new int[26];

        for(char c: s.toCharArray()) {
            wordCount[c-'a']++;
            if(charsCount[c-'a'] < wordCount[c-'a']) return false;
        }
        return true;
    }

    // array count 2
    public int countCharacters3(String[] words, String chars) {
        int[] characters = new int[26];
        for(char ch : chars.toCharArray()) {
            characters[ch - 'a']++;
        }
        int sum = 0;
        for(String word : words) {
            int[] count = characters.clone();  // clone an int array
            boolean flag = true;
            for(char ch : word.toCharArray()) {
                count[ch - 'a']--;
                if(count[ch - 'a'] < 0) {
                    flag = false;
                    break;
                }
            }
            if(flag) {
                sum += word.length();
            }
        }
        return sum;
    }

}
