package com.alg;

import java.util.HashMap;
import java.util.Map;

/*
Given a pattern and a string s, find if s follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s.



Example 1:

Input: pattern = "abba", s = "dog cat cat dog"
Output: true
Example 2:

Input: pattern = "abba", s = "dog cat cat fish"
Output: false
Example 3:

Input: pattern = "aaaa", s = "dog cat cat dog"
Output: false
Example 4:

Input: pattern = "abba", s = "dog dog dog dog"
Output: false
 */
public class Sol290_WordPattern {
    public boolean wordPattern(String pattern, String s) {
        String[] strings = s.split("\\s+");
        if (strings.length != pattern.length()) {
            return false;
        }
        Map<Character, String> map = new HashMap<>();
        for(int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (map.containsKey(c) && !map.get(c).equals(strings[i])) {
                return false;
            }
            if (!map.containsKey(c) && map.containsValue(strings[i])) {
                return false;
            }
            map.put(c, strings[i]);
        }
        return true;

     }


     // https://leetcode.com/problems/word-pattern/editorial/
    public boolean wordPattern2(String pattern, String s) {
        HashMap map_index = new HashMap();
        String[] words = s.split(" ");

        if (words.length != pattern.length())
            return false;

        for (Integer i = 0; i < words.length; i++) {
            char c = pattern.charAt(i);
            String w = words[i];

            if (!map_index.containsKey(c))
                map_index.put(c, i);

            if (!map_index.containsKey(w))
                map_index.put(w, i);

            if (map_index.get(c) != map_index.get(w))
                return false;
        }

        return true;
    }
}
