package com.alg;
/*
According to Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

The board is made up of an m x n grid of cells, where each cell has an initial state:
live (represented by a 1) or dead (represented by a 0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal)
using the following four rules (taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population.
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously. Given the current state of the m x n grid board, return the next state.
 */
public class Sol289_GameOfLife {
    public void gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        int[][] rep = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n ; j++) {
                int lnc = liveNeighborCount(board, i, j);
                if (board[i][j] == 1) {
                    if (lnc < 2 || lnc > 3) {
                        rep[i][j] = 0;
                    } else {
                        rep[i][j] = 1;
                    }
                } else if (lnc == 3) {
                    rep[i][j] = 1;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = rep[i][j];
            }
        }
    }

    private int liveNeighborCount(int[][] board, int x, int y) {
        int m = board.length;
        int n = board[0].length;
        int[] dirx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] diry = {1, 0, -1, 1, -1, 1, 0, -1};
        int count = 0;
        for (int i = 0; i < 8; i++) {
            int newx = x + dirx[i];
            int newy = y + diry[i];
            if (newx >= 0 && newx < m && newy >=0 && newy < n
              && board[newx][newy] == 1) {
                count++;
            }
        }
        return count;
    }


    // in-place, use different values to mark the change without affecting 1
    private int toLive = 3;
    private int toDie = -1;
    public void gameOfLifeInPlace(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m ; i++) {
            for (int j = 0; j < n ; j++) {
                int lnc = liveNeighborCountInPlace(board, i, j);
                if (board[i][j] == 0) {
                    if (lnc == 3) {
                        board[i][j] = toLive;
                    }
                }
                if (board[i][j] == 1) {
                    if (lnc < 2 || lnc > 3 ) {
                        board[i][j] = toDie;
                    }
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n ; j++) {
                if (board[i][j] == toDie) {
                    board[i][j] = 0;
                }
                if (board[i][j] == toLive) {
                    board[i][j] = 1;
                }
            }
        }
    }
    private int liveNeighborCountInPlace(int[][] board, int x, int y) {
        int m = board.length;
        int n = board[0].length;
        int[] dirx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] diry = {1, 0, -1, 1, -1, 1, 0, -1};
        int count = 0;
        for (int i = 0; i < 8; i++) {
            int newx = x + dirx[i];
            int newy = y + diry[i];
            if (newx >= 0 && newx < m && newy >=0 && newy < n) {
                if (board[newx][newy] == 1 || board[newx][newy] == -1) {
                    // -1 means it was LIVE before
                    count++;
                }
            }
        }
        return count;
    }

}

