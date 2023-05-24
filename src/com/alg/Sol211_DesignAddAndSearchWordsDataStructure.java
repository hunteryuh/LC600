package com.alg;

import java.util.HashMap;
import java.util.Map;

/*
Design a data structure that supports adding new words and finding if a string matches any previously added string.

Implement the WordDictionary class:

WordDictionary() Initializes the object.
void addWord(word) Adds word to the data structure, it can be matched later.
bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.


Example:

Input
["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
Output
[null,null,null,null,false,true,true,true]

Explanation
WordDictionary wordDictionary = new WordDictionary();
wordDictionary.addWord("bad");
wordDictionary.addWord("dad");
wordDictionary.addWord("mad");
wordDictionary.search("pad"); // return False
wordDictionary.search("bad"); // return True
wordDictionary.search(".ad"); // return True
wordDictionary.search("b.."); // return True


Constraints:

1 <= word.length <= 500
word in addWord consists lower-case English letters.
word in search consist of  '.' or lower-case English letters.
At most 50000 calls will be made to addWord and search.
 */
public class Sol211_DesignAddAndSearchWordsDataStructure {
    class WordDictionary {
        WordNode root;
        public WordDictionary() {
            root = new WordNode();
        }

        public void addWord(String word) {
            WordNode node = root;
            for (char c : word.toCharArray()) {
                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new WordNode();
                }
                node = node.children[c - 'a'];
            }
            node.isWord = true;
        }

        public boolean search(String word) {
            return helper(word, 0, root);
        }

        private boolean helper(String word, int start, WordNode node) {
            if (node == null) return false;
            if (start == word.length()) return node.isWord;
            char c = word.charAt(start);
            if (c == '.') {
                for (int i = 0; i < 26; i++) {
                    if (helper(word, start + 1, node.children[i])) {
                        return true;
                    }
                }
            } else {
                return helper(word, start + 1, node.children[c - 'a']);
            }
            return false;
        }
    }

    class WordNode {
        WordNode[] children;
        boolean isWord;
        public WordNode() {
            children = new WordNode[26];
        }
    }

    // implementation 2


    class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean word = false;
        public TrieNode() {}
    }

    class WordDictionary2 {
        TrieNode trie;

        /** Initialize your data structure here. */
        public WordDictionary2() {
            trie = new TrieNode();
        }

        /** Adds a word into the data structure. */
        public void addWord(String word) {
            TrieNode node = trie;

            for (char ch : word.toCharArray()) {
                if (!node.children.containsKey(ch)) {
                    node.children.put(ch, new TrieNode());
                }
                node = node.children.get(ch);
            }
            node.word = true;
        }
    }

    public boolean searchInNode(String word, TrieNode node) {
        for (int i = 0; i < word.length(); ++i) {
            char ch = word.charAt(i);
            if (!node.children.containsKey(ch)) {
                // if the current character is '.'
                // check all possible nodes at this level
                if (ch == '.') {
                    for (char x : node.children.keySet()) {
                        TrieNode child = node.children.get(x);
                        if (searchInNode(word.substring(i + 1), child)) {
                            return true;
                        }
                    }
                }
                // if no nodes lead to answer
                // or the current character != '.'
                return false;
            } else {
                // if the character is found
                // go down to the next level in trie
                node = node.children.get(ch);
            }
        }
        return node.word;
    }
}
