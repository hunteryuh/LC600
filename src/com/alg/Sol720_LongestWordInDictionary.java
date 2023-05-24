package com.alg;
/*
Given an array of strings words representing an English Dictionary, return the longest word in words that can be built one character at a time by other words in words.

If there is more than one possible answer, return the longest word with the smallest lexicographical order. If there is no answer, return the empty string.



Example 1:

Input: words = ["w","wo","wor","worl","world"]
Output: "world"
Explanation: The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".
Example 2:

Input: words = ["a","banana","app","appl","ap","apply","apple"]
Output: "apple"
Explanation: Both "apply" and "apple" can be built from other words in the dictionary. However, "apple" is lexicographically smaller than "apply".


Constraints:

1 <= words.length <= 1000
1 <= words[i].length <= 30
words[i] consists of lowercase English letters.

 */

import java.util.HashSet;
import java.util.Set;

//https://leetcode.com/problems/longest-word-in-dictionary/
public class Sol720_LongestWordInDictionary {
    public String longestWord(String[] words) {
        String res = "";
        Set<String> set = new HashSet<>();
        for(String word : words) {
            set.add(word);
        }
        for (String word : words) {
            if (word.length() > res.length() || word.length() == res.length() && word.compareTo(res) < 0) {
                if (canBuild(word, set)) {
                    res = word;
                }
            }
        }
        return res;

    }
    private boolean canBuild(String word, Set<String> set) {
        int n = word.length();
        for (int i = 1; i< n; i++) {
            if(!set.contains(word.substring(0,i))) {  // need to start from 1 as set does not contain empty string ""
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String t = "abc";
        String res = t.substring(0,0);
        System.out.println(res.equals("")); // alwasy true;  so
        System.out.println("zz"+ res + "00");
        Set<String> set = new HashSet<>();  // set does not contain the empty string ""
        set.add(t);
        System.out.println(set.contains(res));
    }

    // trie solution
    // https://leetcode.com/problems/longest-word-in-dictionary/discuss/634793/Java-The-easiest-to-understand-trie-solution.-100-Explanation
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word;
        // boolean isTerminal;
//        TrieNode() {}        }
    }

    String result = "";
    public String longestWord2(String[] words) {
        if (words.length == 0) {
            return "";
        }
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode cur = root;
            for (char c : word.toCharArray()) {
                if (cur.children[c-'a'] == null) {
                    cur.children[c-'a'] = new TrieNode();
                }
                cur = cur.children[c-'a'];
            }
            cur.word = word;
        }

        dfs(root);
        return result;
    }
    public void dfs(TrieNode node) {
        if (node == null) { return;
        }
        if (node.word != null) {
            if (node.word.length() > result.length()) {
                result = node.word;
            } else if (node.word.length() == result.length() && node.word.compareTo(result) < 0) {
                result = node.word;
            }
        }
        for (TrieNode child : node.children) {
            if (child != null && child.word != null) {
                dfs(child);
            }
        }
    }


}
