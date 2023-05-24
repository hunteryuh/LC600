package com.alg;
/*
Given a Tic-Tac-Toe board as a string array board, return true if and only if it is possible to reach this board position during the course of a valid tic-tac-toe game.

The board is a 3 x 3 array that consists of characters ' ', 'X', and 'O'. The ' ' character represents an empty square.

Here are the rules of Tic-Tac-Toe:

Players take turns placing characters into empty squares ' '.
The first player always places 'X' characters, while the second player always places 'O' characters.
'X' and 'O' characters are always placed into empty squares, never filled ones.
The game ends when there are three of the same (non-empty) character filling any row, column, or diagonal.
The game also ends if all squares are non-empty.
No more moves can be played if the game is over.


Example 1:


Input: board = ["O  ","   ","   "]
Output: false
Explanation: The first player always plays "X".
Example 2:


Input: board = ["XOX"," X ","   "]
Output: false
Explanation: Players take turns making moves.
Example 3:


Input: board = ["XOX","O O","XOX"]
Output: true


Constraints:

board.length == 3
board[i].length == 3
board[i][j] is either 'X', 'O', or ' '.
 */
public class Sol794_ValidTicTacToe {
    public boolean validTicTacToe(String[] board) {
        int n = 3;
        char[][] grid = new char[n][n];
        int countX = 0;
        int countO = 0;
        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j]= board[i].charAt(j);
                if (grid[i][j] == 'X') {
                    countX++;
                }
                if (grid[i][j] == 'O') {
                    countO++;
                }
            }
        }
        if (countO > countX) return false;
        if (countX > countO + 1) return false;
        if (isWinner('X', grid) && countX != countO + 1) return false;
        if (isWinner('O', grid) && countX != countO) return false;
        return true;

    }
    private boolean isWinner(char mark, char[][] grid) {
        return checkRow(mark, grid) || checkCol(mark, grid) || checkDiag(mark, grid) || checkAntdiag(mark, grid);
    }
    private boolean checkRow(char mark, char[][] grid) {
        for (int i = 0; i < 3; i++) {
            if (grid[i][0] == mark && grid[i][1] == mark && grid[i][2] == mark) {
                return true;
            }
        }
        return false;
    }

    private boolean checkCol(char mark, char[][] grid) {
        for (int i = 0; i < 3; i++) {
            if (grid[0][i] == mark && grid[1][i] == mark && grid[2][i] == mark) {
                return true;
            }
        }
        return false;
    }
    private boolean checkDiag(char mark, char[][] grid) {
        if (grid[0][0] == mark && grid[1][1] == mark && grid[2][2] == mark) {
            return true;
        }
        return false;
    }
    private boolean checkAntdiag(char mark, char[][] grid) {
        if (grid[0][2] == mark && grid[1][1] == mark && grid[2][0] == mark) {
            return true;
        }
        return false;
    }

}
