package com.alg;

/**
 * Created by HAU on 7/23/2017.
 */
/*Given a 2D board and a word, find if the word exists in the grid.

        The word can be constructed from letters of sequentially adjacent cell,
        where "adjacent" cells are those horizontally or vertically neighboring.
        The same letter cell may not be used more than once.*/
    /*


Example 1:

Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
Output: true

Example 2:

Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
Output: true

Example 3:

Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
Output: false



Constraints:

    m == board.length
    n = board[i].length
    1 <= m, n <= 6
    1 <= word.length <= 15
    board and word consists of only lowercase and uppercase English letters.



Follow up: Could you use search pruning to make your solution faster with a larger board?
     */
public class Sol79_WordSearch {

    // T/S: O(n * (3 ^ L))/O(n), where n is number of cells and L is word length.
    // To avoid confusion, T: O(r * c * (4 ^ w)), where r x c are the dimensions of the board.
    public static boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0) return false;
        if (word.isEmpty()) return true;
        boolean result = false;
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m ; i ++){
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
            // mark the path before the next exploration
            board[i][j] = '#';
            if (start == word.length() - 1) {
                return true;
            }
            if ( dfs(board,word,i-1,j,start + 1)
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

        Sol79_WordSearch ss = new Sol79_WordSearch();
        char[][] board2 = {
                {'a'},
        };
        String word = "a";
        System.out.println(ss.exist4(board2, word));
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
    int[][] dirs = { {1,0}, {0,1}, {-1, 0}, {0, -1}};
    // time: O(n * 3^L)
    // n: number of cells in the board; L: length of the word to be matched
    public boolean exist3(char[][] board, String word) {

        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (word.charAt(0) == board[i][j]) {
                    if (findWord(board, i, j, word,1, visited)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean findWord(char[][] board, int i, int j, String word, int pos, boolean[][] visited) {
        if (pos == word.length()) return true;
        visited[i][j] = true;
        for (int [] dir: dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            // if inbound && not visited && is the next letter
            if (0 <= x && x < board.length && 0<=y && y < board[0].length
            && !visited[x][y]
            && board[x][y] == word.charAt(pos)) {
                if (findWord(board, x, y, word, pos + 1, visited)) {
                    return true;
                }
            }
        }
        visited[i][j] = false;
        return false;
    }

    // find word in a board
    public boolean exist4(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (findWord4(board, i, j, word,0, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean findWord4(char[][] board, int i, int j, String word, int pos, boolean[][] visited) {
        if (pos == word.length()) return true;

        // Check bounds and whether the current cell matches the word's current character
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length
                || visited[i][j] || board[i][j] != word.charAt(pos)) {
            return false;
        }

        // Mark the cell as visited only if board[i][j] == word.charAt(pos)
        visited[i][j] = true;

        // Explore all possible directions
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (findWord4(board, x, y, word, pos + 1, visited)) {
                return true;
            }
        }

        // Unmark the cell for backtracking
        visited[i][j] = false;
        return false;
    }
}
