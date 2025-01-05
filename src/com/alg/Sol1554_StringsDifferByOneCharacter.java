package com.alg;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
Given a list of strings dict where all the strings are of the same length.

Return true if there are 2 strings that only differ by 1 character in the same index, otherwise return false.



Example 1:

Input: dict = ["abcd","acbd", "aacd"]
Output: true
Explanation: Strings "abcd" and "aacd" differ only by one character in the index 1.

Example 2:

Input: dict = ["ab","cd","yz"]
Output: false

Example 3:

Input: dict = ["abcd","cccc","abyd","abab"]
Output: true



Constraints:

    The number of characters in dict <= 105
    dict[i].length == dict[j].length
    dict[i] should be unique.
    dict[i] contains only lowercase English letters.



Follow up: Could you solve this problem in O(n * m) where n is the length of dict and m is the length of each string.

 */
public class Sol1554_StringsDifferByOneCharacter {
    // memory limit exceeded
    public boolean differByOne(String[] dict) {
        Set<String> set = new HashSet<>();
        for (String str: dict) {
            for (int i = 0; i < str.length(); i++) {
                StringBuilder sb = new StringBuilder(str);
                sb.setCharAt(i, '*');
//                String s = str.substring(0, i) + "*" + str.substring(i+1);
                if (set.contains(sb.toString())) {
                    return true;
                }
                set.add(sb.toString());
            }
        }
        return false;
    }

    public boolean solve2(String[] input) {
        for (int i = 0; i < input.length - 1; i++) {
            for (int j = i + 1; j < input.length; j++) {
                if (differsByOne(input[i], input[j])) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean differsByOne(String str1, String str2) {
        boolean differsByOne = false;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                if (differsByOne) {
                    return false;
                } else {
                    differsByOne = true;
                }
            }
        }
        return true;
    }

    // time complexity good solution, rollin hash
    // https://leetcode.com/problems/strings-differ-by-one-character/solutions/3971276/trie-and-rabin-karp-solutions-explanation-with-images/
    public boolean differByOne_Time(String[] dict) {
        if (dict == null || dict.length <= 1) {
            return false;
        }
        int len = dict.length;
        int wordLen = dict[0].length();
        long[] value = new long[len];
        for (int i = 0; i < len; i++) {
            String word = dict[i];
            for (int j = 0; j < wordLen; j++) {
                value[i] = 26 * value[i] + (word.charAt(j) - 'a');
            }
        }

        Set<Long> visited = new HashSet<>();
        long base = 1;

        for (int j = wordLen - 1; j >= 0; j--) {
            visited.clear();
            for (int i = 0; i < len; i++) {
                String word = dict[i];
                long cur = value[i] - (word.charAt(j) - 'a') * base;
                if (visited.contains(cur)) {
                    return true;
                }
                visited.add(cur);
            }
            base = base * 26;
        }


        return false;
    }

    // trie solution https://leetcode.com/problems/strings-differ-by-one-character/solutions/1994053/java-trie-solution/
    public class Trie{
        TrieNode root;
        public Trie() {
            this.root = new TrieNode();
        }

        public void add(String word) {
            TrieNode current = root;

            for(int i=0; i<word.length(); i++) {
                char c = word.charAt(i);
                if (!current.children.containsKey(c)) {
                    current.children.put(c, new TrieNode());
                }

                current = current.children.get(c);
                current.count++;
            }
            current.isWord = true;
        }

        public boolean containsDiffByOne(String word) {
            TrieNode current = root;
            for(int i=0; i<word.length(); i++) {
                char c = word.charAt(i);
                for(Character key : current.children.keySet()) {
                    if(c == key) {
                        continue;
                    }
                    TrieNode node = current.children.get(key);
                    if(contains(node, word, i+1)) {
                        return true;
                    }
                }

                if(current.children.get(c).count <= 1) {
                    return false;
                }
                current = current.children.get(c);
            }

            return false;
        }

        private boolean contains(TrieNode node, String word, int index) {
            for (int i=index; i<word.length(); i++) {
                char c = word.charAt(i);
                if(!node.children.containsKey(c)) {
                    return false;
                }
                node = node.children.get(c);
            }

            return node.isWord;
        }

        private boolean search(String word, TrieNode curr, int index, int diff) {
            if (diff > 1) return false;

            if(index == word.length()) return diff == 1;

            for(char letter = 'a'; letter <= 'z'; letter++) {
                if(!curr.children.containsKey(letter)) continue;
                int nextDiff = diff + (letter != word.charAt(index) ? 1 : 0);
                if(search(word, curr.children.get(letter), index + 1, nextDiff)) return true;
            }

            return false;
        }
    }

    public class TrieNode {
        int count;
        boolean isWord;
        Map<Character,TrieNode> children;

        public TrieNode() {
            this.count = 0;
            this.isWord = false;
            this.children = new HashMap();
        }
    }
    public boolean differByOne2(String[] dict) {
        Trie trie = new Trie();
        for(String word : dict) {
            trie.add(word);
        }

        for(String word : dict) {
            if (trie.containsDiffByOne(word)) {
                return true;
            }
        }
        return false;
    }
    // trie method 2, dfs
    public boolean differByOne3(String[] dict) {
        Trie trie = new Trie();
        for(String word : dict) {
            trie.add(word);
        }

        for(String word : dict) {
            if (trie.search(word, trie.root, 0, 0)) {
                return true;
            }
        }
        return false;
    }


}
