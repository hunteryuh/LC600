package com.alg;

import java.util.*;

/*
Given an array of strings words (without duplicates), return all the concatenated words in the given list of words.

A concatenated word is defined as a string that is comprised entirely of at least two shorter words (not necessarily distinct) in the given array.



Example 1:

Input: words = ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
"dogcatsdog" can be concatenated by "dog", "cats" and "dog";
"ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".

Example 2:

Input: words = ["cat","dog","catdog"]
Output: ["catdog"]



Constraints:

    1 <= words.length <= 104
    1 <= words[i].length <= 30
    words[i] consists of only lowercase English letters.
    All the strings of words are unique.
    1 <= sum(words[i].length) <= 105


 */
public class Sol472_ConcatenatedWords {
    // https://leetcode.com/problems/concatenated-words/editorial/
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Set<String> wordDic = new HashSet<>(Arrays.asList(words));
        List<String> res = new ArrayList<>();
        for (String word: words) {
            int length = word.length();
            boolean[] visited = new boolean[length];
            if (dfs(word, 0, visited, wordDic)) {
                res.add(word);
            }
        }
        return res;
    }
    private boolean dfs(String word, int index, boolean[] visited, Set<String> set) {
        if (index == word.length()) {
            return true;
        }
        if (visited[index]) {
            return false;
        }
        visited[index] = true;
        for (int i = word.length() - (index == 0 ? 1 : 0); i > index; i--) {
            // if index = 0, we check substring up to length -1, so that it is not the whole word (
            //i.e. at least 2 words)
            if (set.contains(word.substring(index, i)) &&
                    dfs(word, i, visited, set)) {
                return true;
            }
        }
        return false;
    }

    public List<String> findAllConcatenatedWordsInADict2(String[] words) {
        List<String> ans = new ArrayList<>();
        HashSet<String> wordSet = new HashSet<>(Arrays.asList(words));
        HashMap<String, Boolean> cache = new HashMap<>();
        for (String word : words) {
            if (dfs(word, wordSet, cache)) ans.add(word);
        }
        return ans;
    }
    boolean dfs(String word, HashSet<String> wordSet, HashMap<String, Boolean> cache) {
        if (cache.containsKey(word)) return cache.get(word);
        for (int i = 1; i < word.length(); i++) {
            if (wordSet.contains(word.substring(0, i))) {
                String suffix = word.substring(i);
                if (wordSet.contains(suffix) || dfs(suffix, wordSet, cache)) {
                    cache.put(word, true);
                    return true;
                }
            }
        }
        cache.put(word, false);
        return false;
    }

    // trie solution, time limit exceeded for 1 case if adding all words in the trie
    public List<String> findAllConcatenatedWordsInDict(String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode root = new TrieNode();
        //         Arrays.sort(words, (a, b) -> a.length() - b.length()); // put shorter words in front
//        for (String word: words) {
//            addWord(word, root);
//        }
        for (String word: words) {
            if (dfsFound(word, 0, 0, root)) {
                res.add(word);
            } else {
                addWord(word, root); // to avoid time limit, only add word if it cannot be found
            }
        }
        return res;
    }

    private boolean dfsFound(String word, int index, int count, TrieNode root) {
        if (index == word.length() && count >= 2) {
            return true;
        }
        TrieNode node = root;
        for (int i = index; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!node.childrenMap.containsKey(c)) {
                return false;
            }
            node = node.childrenMap.get(c);
            if (node.isWord) { // 找到一个之后又回到起点重新搜索
                if (dfsFound(word, i + 1, count + 1, root)) {
                    return true;
                }
            }
        }
//        memo.put(index, false);
        return false;
    }

    private void addWord(String word, TrieNode root) {
        TrieNode node = root;
        for (char c: word.toCharArray()) {
            if (!node.childrenMap.containsKey(c)) {
                node.childrenMap.put(c, new TrieNode());
            }
            node = node.childrenMap.get(c);
        }
        node.isWord = true;
    }

    class TrieNode {
        Map<Character, TrieNode> childrenMap;
        boolean isWord;
        TrieNode() {
            this.childrenMap = new HashMap<>();
            isWord = false;
        }
    }

}
