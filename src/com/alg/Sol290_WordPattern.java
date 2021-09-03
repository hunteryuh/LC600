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
}
