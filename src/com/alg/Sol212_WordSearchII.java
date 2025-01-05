package com.alg;

import java.util.*;

/*
Given an m x n board of characters and a list of strings words,
return all words on the board.

Each word must be constructed from letters of sequentially adjacent cells,
where adjacent cells are horizontally or vertically neighboring.
The same letter cell may not be used more than once in a word.



Example 1:

Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
Output: ["eat","oath"]

Example 2:

Input: board = [["a","b"],["c","d"]], words = ["abcb"]
Output: []



Constraints:

    m == board.length
    n == board[i].length
    1 <= m, n <= 12
    board[i][j] is a lowercase English letter.
    1 <= words.length <= 3 * 104
    1 <= words[i].length <= 10
    words[i] consists of lowercase English letters.
    All the strings of words are unique.


 */
public class Sol212_WordSearchII {

    // Interview Friendly Version
    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = buildTrie(words);
        Set<String> res = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, trie, res, i, j);
            }
        }
        return new ArrayList<>(res);
    }

    public void dfs(char[][] board, Trie node, Set<String> res, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length ||
                board[i][j] == '#' || node.next[board[i][j] - 'a'] == null) {
            return;
        }
        if (node.next[board[i][j] - 'a'].word != null) {
            res.add(node.next[board[i][j] - 'a'].word);
        }

        // Go to next char
        node = node.next[board[i][j] - 'a'];
        char c = board[i][j];
        board[i][j] = '#';
        dfs(board, node, res, i - 1, j);
        dfs(board, node, res, i + 1, j);
        dfs(board, node, res, i, j - 1);
        dfs(board, node, res, i, j + 1);
        board[i][j] = c;
    }

    public Trie buildTrie(String[] words) {
        Trie root = new Trie();
        for (String w : words) {
            Trie p = root;
            for (char c : w.toCharArray()) {
                if (p.next[c - 'a'] == null) {
                    p.next[c - 'a'] = new Trie();
                }
                p = p.next[c - 'a'];  // will point to curr char
            }
            p.word = w;
        }
        return root;
    }

    private class Trie {
        Trie[] next = new Trie[26];
        String word = null;
    }

    // with well thought optimization , time O (M * 4 * 3^L)
    // M: total number of cells in grid, L: word length
    public List<String> findWords2(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode root = buildTrie2(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, root, res);
            }
        }
        return res;
    }

    private TrieNode buildTrie2(String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode p = root;
            for (char c: w.toCharArray()) {
                int i = c - 'a';
                if (p.next[i] == null) {
                    p.next[i] = new TrieNode();
                    p = p.next[i];
                }
            }
            p.word = w;
        }
        return root;
    }

    private void dfs(char[][] board, int i, int j, TrieNode p, List<String> res) {
        char c = board[i][j];
        if (c == '#' || p.next[c - 'a'] == null) {
            return;
        }
        p = p.next[c - 'a'];
        if (p.word != null) { // found one word
            res.add(p.word);
            p.word = null; // de-duplicate, avoid revisiting the same word.
        }

        board[i][j] = '#';
        if (i > 0) {
            dfs(board, i - 1, j, p, res);
        }
        if (j > 0) {
            dfs(board, i, j - 1, p, res);
        }
        if (i < board.length - 1) {
            dfs(board, i + 1, j, p, res);
        }
        if (j < board[0].length - 1) {
            dfs(board, i, j + 1, p , res);
        }
        board[i][j] = c;
    }
    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String word;
    }

    // redo 11/24/2024, works after moving node.word after node = node.map.get(c)
    public List<String> findWords3(char[][] board, String[] words) {
        Set<String> set = new HashSet<>();
        int m = board.length;
        int n = board[0].length;
        TrieItem trie = buildTrie3(words);
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, i, j, set, trie, visited);
            }
        }
        return new ArrayList<>(set);
    }

    private void dfs(char[][] board, int i, int j, Set<String> set, TrieItem node, boolean[][] visited) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return;
        if (visited[i][j]) return;
        if (!node.map.containsKey(board[i][j])) return;

        node = node.map.get(board[i][j]); // needs to be before checking node.word

        if (node.word != null) {
            set.add(node.word);
        }
        visited[i][j] = true;
        dfs(board, i + 1, j, set, node, visited);
        dfs(board, i - 1, j, set, node, visited);
        dfs(board, i, j + 1, set, node, visited);
        dfs(board, i, j - 1, set, node, visited);
        visited[i][j] = false;
    }

    private TrieItem buildTrie3(String[] words) {
        TrieItem root = new TrieItem();
        for (String word : words) {
            addWord(word, root);
        }
        return root;
    }
    private void addWord(String word, TrieItem root) {
        TrieItem node = root;
        for (char c : word.toCharArray()) {
            if (!node.map.containsKey(c)) {
                node.map.put(c, new TrieItem());
            }
            node = node.map.get(c);
        }
        node.word = word;
    }

    class TrieItem {
        Map<Character, TrieItem> map;
        String word;
        TrieItem() {
            this.map = new HashMap();
            this.word = null;
        }
    }
}
