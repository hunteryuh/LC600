package com.alg.other;

import java.io.Console;

/*
You are given some number of blocks with letters on their faces
You are also given a target word
Goal is to determine whether you can rearrange the blocks to form the target word
Note: each block can only be used once to try to form the word
 */
public class BlockLettersForWord {

    public boolean wordSearchWithBlocks(char[][] matrix, String word) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[] visited = new boolean[m];
        return dfs(matrix, visited, word, 0);
    }

    private boolean dfs(char[][] matrix, boolean[] visited, String word, int start) {
        if (start == word.length()) {
            return true;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            if (visited[i]) continue;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == word.charAt(start)) {
                    visited[i] = true;
                    if (dfs(matrix, visited, word, start + 1)){
                        System.out.println("char at x " + i + " y " +  j + "is " + matrix[i][j]);
                        return true;
                    }
                    visited[i] = false;
                }
            }
        }
        return false;
    }


    public static void main(String[] args) {
        String word = "leetcode";
        char[][] matrix = {
                {'l','s','p','a','b','c'},
                {'l','t','e','g','h','i'},
                {'a','e','b','c','d','f'},
                {'a','e','b','c','d','t'},
                {'a','e','b','c','d','f'},
                {'a','e','b','o','m','f'},
                {'a','b','b','c','d','f'},
                {'a','c','b','c','d','f'}
        };


        BlockLettersForWord bb = new BlockLettersForWord();
        boolean result = bb.wordSearchWithBlocks(matrix, word);
        System.out.println(result);
    }
}
