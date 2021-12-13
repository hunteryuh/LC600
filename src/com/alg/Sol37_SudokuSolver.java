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
        backtrack(board);
    }

    private boolean backtrack(char[][] board) {
        int n = board.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != '.') {
                    continue;
                }
                for (char k = '1'; k <= '9'; k++) {
                    if (canTry(k, i, j, board)) {
                        board[i][j] = k;
                        boolean flag = backtrack(board);
                        if (flag) return true;
                        board[i][j] = '.';
                    }
                }
                return false;  // tried 9 numbers and no one works
            }
        }
        return true; // after loop, it did not return false;
    }

    private boolean canTry(char k, int row, int col, char[][] board) {
        // whether we can put k at (row, col)
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == k) {
                return false;
            }
        }
        for (int j = 0; j < 9; j++) {
            if (board[row][j] == k) {
                return false;
            }
        }

        int boxx = row / 3;
        int boxy = col / 3;
        // 2,4 -> 0, 1
        for (int i = boxx * 3; i < boxx * 3 + 3; i++) {
            for (int j = boxy * 3; j < boxy * 3 + 3; j++) {
                if (board[i][j] == k) {
                    return false;
                }
            }
        }
        return true;
    }
}
