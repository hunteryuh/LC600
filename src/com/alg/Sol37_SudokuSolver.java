package com.alg;
/*
Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
The '.' character indicates empty cells.
 */

/*
Constraints:

board.length == 9
board[i].length == 9
board[i][j] is a digit or '.'.
It is guaranteed that the input board has only one solution.

https://leetcode.com/problems/sudoku-solver/
 */
public class Sol37_SudokuSolver {
    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0) return;
        backtrack(board);
    }

    private boolean backtrack(char[][] board) {
        int n = board.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != '.') {
                    continue;
                }
                for (char k = '1'; k <= '9'; k++) { // try 1 through 9
                    if (canTry(k, i, j, board)) {
                        board[i][j] = k; // put k on this cell
                        boolean flag = backtrack(board);
                        if (flag) return true;
                        board[i][j] = '.'; // otherwise go back
                    }
                }
                return false;  // tried 9 numbers and no one works
            }
        }
        return true; // after loop, it did not return false;
    }

//    private boolean isValid(char k, int row, int col, char[][] board) {
    private boolean canTry(char k, int row, int col, char[][] board) {
        // whether we can put k at (row, col)
        for (int i = 0; i < 9; i++) {   // check every row
            if (board[i][col] == k) {
                return false;
            }
        }
        for (int j = 0; j < 9; j++) {  // check every col
            if (board[row][j] == k) {
                return false;
            }
        }

        int boxx = row / 3;
        int boxy = col / 3;
        // 2,4 -> 0, 1
        for (int i = boxx * 3; i < boxx * 3 + 3; i++) {  // check 3 * 3 block
            for (int j = boxy * 3; j < boxy * 3 + 3; j++) {
                if (board[i][j] == k) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(char[][] board, int row, int col, char num) {
        int blkrow = (row / 3) * 3, blkcol = (col / 3) * 3; // Block no. is i/3, first element is i/3*3
        for (int i = 0; i < 9; i++)
            if (board[i][col] == num || board[row][i] == num ||
                board[blkrow + i / 3][blkcol + i % 3] == num)
                return false;
        return true;
    }
    /*
    System design Round:
Implement this feature/ app where for every order the customers can go rate the individual
items they purchased from the restaurant and write a review on it. Basically the feature similar to amazon reviews.
My thoughts: I start with small scope and go big and scale.
Scope: 1-10 people
Did class design, api design.
Scope: 100,000 people
Did high level diagram
Scope: 1M people
Did detailed component design, told why Relational vs non relational, table design etc.
     */
}
