package com.alg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
Given an m x n board of characters and a list of strings words, return all words on the board.

Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring.
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
public class Sol21_WordSearchII {
    // https://leetcode.com/problems/word-search-ii/discuss/1643356/Java-Trie%2BDFS
    Set<String> res = new HashSet<>();
    int[][] dirs = { {-1, 0}, {1,0}, {0,1}, {0,-1}};
    public List<String> findWords(char[][] board, String[] words) {

        TrieWS trie = new TrieWS();
        for (String s : words) {
            trie.insert(s);
        }
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0;j < n ; j++) {
                dfs(board, visited, i, j, trie.root);
            }
        }
        return new ArrayList<>(res);
    }

    private void dfs(char[][] board, boolean[][] visited, int x, int y, TrieWSNode root) {
        char cur = board[x][y];
        if (visited[x][y] || !root.children.containsKey(cur)) return;
        TrieWSNode node = root.children.get(cur);
        if (node.value != null) {
            res.add(node.value);
            node.value = null;
        }
        visited[x][y] = true;
        for (int[] dir: dirs) {
            int newx = x + dir[0];
            int newy = y + dir[1];
            if (newx < 0 || newy < 0 || newx >= board.length || newy >= board[0].length) continue;
            dfs(board, visited, newx, newy, node);
        }
        visited[x][y] = false;
    }



    // time limit exceeded
    private void dfs2(char[][] board, boolean[][] visited, String s, int x, int y, TrieWS trie) {
//        if (x < 0 || y < 0 || x >= board.length || y >= board[0].length) return;
//        if (visited[x][y]) return;
        s += board[x][y];
        if (trie.hasWord(s)) {
            res.add(s);
        }
        visited[x][y] = true;
        for (int[] dir: dirs) {
            int newx = x + dir[0];
            int newy = y + dir[1];
            if (newx < 0 || newy < 0 || newx >= board.length || newy >= board[0].length) continue;
            if (visited[newx][newy]) continue;
            dfs2(board, visited, s, newx, newy, trie);
        }
        visited[x][y] = false;
    }


    class TrieWS {
        TrieWSNode root;
        public TrieWS() {
            root = new TrieWSNode();
        }

        public void insert(String word) {
            TrieWSNode node = root;
            for (char c : word.toCharArray()) {
                if (!node.children.containsKey(c)) {
                    node.children.put(c, new TrieWSNode());
                }
                node = node.children.get(c);
            }
            node.value = word;
            node.isWord = true;
        }
        public boolean hasPrefix(String prefix) {
            TrieWSNode node = root;
            for (char c: prefix.toCharArray()) {
                if (!node.children.containsKey(c)) {
                    return false;
                }
                node = node.children.get(c);
            }
            return true;
        }
        public boolean hasWord(String word) {
            TrieWSNode node = root;
            for (char c: word.toCharArray()) {
                if (!node.children.containsKey(c)) {
                    node.children.put(c, new TrieWSNode());
                }
                node = node.children.get(c);
            }
            return node.isWord;
        }
    }

    class TrieWSNode {
        boolean isWord;
        String value;
        Map<Character, TrieWSNode> children;
        public TrieWSNode() {
            children = new HashMap<>();
        }
    }
}
