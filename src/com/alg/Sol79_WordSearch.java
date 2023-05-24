package com.alg;

/**
 * Created by HAU on 7/23/2017.
 */
/*Given a 2D board and a word, find if the word exists in the grid.

        The word can be constructed from letters of sequentially adjacent cell,
        where "adjacent" cells are those horizontally or vertically neighboring.
        The same letter cell may not be used more than once.*/
public class Sol79_WordSearch {
    public static boolean exist(char[][] board, String word) {
        if ( board == null || board.length == 0) return false;
        if ( word.length() == 0) return true;
        boolean result = false;
        int m = board.length;
        int n = board[0].length;
        for ( int i = 0; i < m ; i ++){
            for (int j = 0; j < n; j++){
                if (dfs2(board,word,i,j,0)){
                    result = true;
                }
            }
        }
        return result;
    }

    private static boolean dfs(char[][] board, String word, int i, int j, int start) {
        int m = board.length;
        int n = board[0].length;

        if ( i < 0 || j < 0 || i>=m || j>=n) return false;  // out of the board
        if ( board[i][j] == word.charAt(start)){
            char tmp = board[i][j];
            board[i][j] = '#';
            if (start == word.length() - 1) {
                return true;
            }else if ( dfs(board,word,i-1,j,start + 1)
                    || dfs(board,word,i+1,j,start + 1)
                    || dfs(board,word,i,j-1,start + 1)
                    || dfs(board,word,i,j+1, start +1)){
                return true;
            }
            board[i][j] = tmp;   // depth first search

        }
        return false;
    }
    private static boolean dfs2(char[][] board, String word, int i, int j, int start) {
        if (start == word.length()) return true;
        int m = board.length;
        int n = board[0].length;

        if ( i < 0 || j < 0 || i>=m || j>=n) return false;  // out of the board
        if ( board[i][j] == word.charAt(start)){
            char c = board[i][j];
            board[i][j] = '-';
            boolean res = dfs2(board,word,i+1,j,start+1) ||
                    dfs2(board,word,i-1,j,start+1)||dfs2(board,word,i,j+1,start+1)
                    ||dfs2(board,word,i,j-1,start+1);

            board[i][j] = c;   // depth first search ( backtracking)
            return res;

        }
        return false;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'a','b','c','e'},
                {'s','f','c','s'},
                {'a','d','e','e'},
        };
        //String word = "abcced";
        //String w2 = "see";
        String w3 = "abfcc";
        //System.out.println(exist(board,word));
        //System.out.println(exist(board,w2));
        System.out.println(exist(board,w3));
    }

    public boolean exist2(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        if (word == null || word.length() == 0) return true;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n ; j++) {
                if (dfs(word, board, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;

    }

    private boolean dfs(String word, char[][] board, int x, int y, int start) {
        int m = board.length;
        int n = board[0].length;
        if (start == word.length()) return true;
        if (x < 0 || x >= m || y < 0 || y >=n) return false;
        if (word.charAt(start) == board[x][y]) {
            start++;
            char c = board[x][y];
            board[x][y] = '*';
            if (
                    dfs(word, board, x + 1, y, start) ||
                            dfs(word, board, x - 1, y, start) ||
                            dfs(word, board, x, y+1, start) ||
                            dfs(word, board, x, y -1 , start) ) {
                return true; // side effect: the last matched char would be overwritten as '*'
            }

            // back track the start position and the char at x,y
            start--;
            board[x][y] = c;

        }
        return false;
    }
}
