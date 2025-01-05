package com.alg;

/**
 * Created by HAU on 11/30/2017.
 */
/*Implement a trie with insert, search, and startsWith methods.*/

    // https://leetcode.com/problems/implement-trie-prefix-tree/solution/
public class Sol208_ImplementTrie_PrefixTree {
    class TrieNode {
        public char val;
        public boolean isWord;
        public TrieNode[] children = new TrieNode[26];
        public TrieNode(){}
        TrieNode(char c) {
            TrieNode node = new TrieNode(c);
            node.val = c;
        }
    }

    // simplified
    class Node {
        public boolean isEnd;
        public Node[] children = new Node[26];  // must define as a new array to avoid null pointer when at ws.children
    }

    class Trie {
        private TrieNode root;
        /** Initialize your data structure here. */
        public Trie() {
            root = new TrieNode();
            root.val = ' ';
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            TrieNode ws = root;
            for (int i = 0; i < word.length(); i++){
                char c = word.charAt(i);
                if(ws.children[c - 'a'] == null) {
                    ws.children[c - 'a'] = new TrieNode(c);
                }
                ws = ws.children[c - 'a'];
            }
            ws.isWord = true;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            TrieNode ws = root;
            for (int i = 0; i < word.length(); i++){
                char c = word.charAt(i);
                if (ws.children[c - 'a'] == null) return false;
                ws = ws.children[c - 'a'];
            }
            return ws.isWord;
        }

        /** Returns if there is any word in the trie that
         * starts with the given prefix. */
        public boolean startsWith(String prefix) {
            TrieNode ws = root;
            for(int i = 0; i < prefix.length(); i++){
                char c = prefix.charAt(i);
                if(ws.children[c - 'a'] == null) return false;
                ws = ws.children[c - 'a'];
            }
            return true;
        }
    }

    class Trie2 {
        private TrieNode2 root;

        public Trie2() {
            root = new TrieNode2();
        }

        public void insert(String word) {
            TrieNode2 node = root;
            for (char c: word.toCharArray()) {
                if (node.children[c-'a'] == null) {
                    node.children[c - 'a'] = new TrieNode2();
                }
                node = node.children[c - 'a'];
            }
            node.isEnd = true;
        }

        public boolean search(String word) {
            TrieNode2 node = root;
            for (char c : word.toCharArray()) {
                if (node.children[c - 'a'] == null) return false;
                node = node.children[c - 'a'];
            }
            return node.isEnd;
        }

        public boolean startsWith(String prefix) {
            TrieNode2 node = root;
            for (char c : prefix.toCharArray()) {
                if (node.children[c - 'a'] == null) return false;
                node = node.children[c - 'a'];
            }
            return true;
        }
    }

    class TrieNode2 {
        TrieNode2[] children;
        boolean isEnd;

        public TrieNode2() {
            children = new TrieNode2[26];
        }
    }
}
