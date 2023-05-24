package com.alg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Given a list of strings words and a string pattern, return a list of words[i] that match pattern. You may return the answer in any order.

A word matches the pattern if there exists a permutation of letters p so that after replacing every letter x in the pattern with p(x), we get the desired word.

Recall that a permutation of letters is a bijection from letters to letters: every letter maps to another letter, and no two letters map to the same letter.



Example 1:

Input: words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
Output: ["mee","aqq"]
Explanation: "mee" matches the pattern because there is a permutation {a -> m, b -> e, ...}.
"ccc" does not match the pattern because {a -> c, b -> c, ...} is not a permutation, since a and b map to the same letter.
Example 2:

Input: words = ["a","b","c"], pattern = "a"
Output: ["a","b","c"]


Constraints:

1 <= pattern.length <= 20
1 <= words.length <= 50
words[i].length == pattern.length
pattern and words[i] are lowercase English letters.
 */
public class Sol890_FindAndReplacePattern {
    public List<String> findAndReplacePattern(String[] words, String pattern) {

        List<String> res = new ArrayList<>();
        for (String s: words) {
            if (match(s, pattern)) {
                res.add(s);
            }
        }
        return res;
    }

    // based on Sol290, word pattern
    private boolean match(String word, String pattern) {
        if (word.length() != pattern.length()) return false;
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (map.containsKey(c) && !map.get(c).equals(word.charAt(i))) {
                return false;
            }
            if (!map.containsKey(c) && map.containsValue(word.charAt(i))) {
                return false;
            }
            map.put(c, word.charAt(i));
        }
        return true;
    }

    public List<String> findAndReplacePatter2n(String[] words, String pattern) {
        List<String> ans = new ArrayList();
        for (String word: words)
            if (match2(word, pattern))
                ans.add(word);
        return ans;
    }

    public boolean match2(String word, String pattern) {
        Map<Character, Character> m1 = new HashMap();
        Map<Character, Character> m2 = new HashMap();

        for (int i = 0; i < word.length(); ++i) {
            char w = word.charAt(i);
            char p = pattern.charAt(i);
            if (!m1.containsKey(w)) m1.put(w, p);
            if (!m2.containsKey(p)) m2.put(p, w);
            if (m1.get(w) != p || m2.get(p) != w)
                return false;
        }

        return true;
    }
}
