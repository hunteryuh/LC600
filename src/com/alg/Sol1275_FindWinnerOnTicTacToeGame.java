package com.alg;
/*
Tic-tac-toe is played by two players A and B on a 3 x 3 grid. The rules of Tic-Tac-Toe are:

Players take turns placing characters into empty squares ' '.
The first player A always places 'X' characters, while the second player B always places 'O' characters.
'X' and 'O' characters are always placed into empty squares, never on filled ones.
The game ends when there are three of the same (non-empty) character filling any row, column, or diagonal.
The game also ends if all squares are non-empty.
No more moves can be played if the game is over.
Given a 2D integer array moves where moves[i] = [rowi, coli] indicates that the ith move will be played on grid[rowi][coli]. return the winner of the game if it exists (A or B). In case the game ends in a draw return "Draw". If there are still movements to play return "Pending".

You can assume that moves is valid (i.e., it follows the rules of Tic-Tac-Toe), the grid is initially empty, and A will play first.



Example 1:


Input: moves = [[0,0],[2,0],[1,1],[2,1],[2,2]]
Output: "A"
Explanation: A wins, they always play first.
Example 2:


Input: moves = [[0,0],[1,1],[0,1],[0,2],[1,0],[2,0]]
Output: "B"
Explanation: B wins.
Example 3:


Input: moves = [[0,0],[1,1],[2,0],[1,0],[1,2],[2,1],[0,1],[0,2],[2,2]]
Output: "Draw"
Explanation: The game ends in a draw since there are no moves to make.


Constraints:

1 <= moves.length <= 9
moves[i].length == 2
0 <= rowi, coli <= 2
There are no repeated elements on moves.
moves follow the rules of tic tac toe.
 */
public class Sol1275_FindWinnerOnTicTacToeGame {

    // time O (m *n ): m the length of moves, n being the size of the grid
    char[][] board = new char[3][3];
    public String tictactoe(int[][] moves) {

        for (int i = 0; i < moves.length; i++) {
            int[] move = moves[i];
            int x = move[0];
            int y = move[1];
            if (i % 2 == 0) {
                board[x][y] = 'X';
                if (hasWinnder(x, y, 'X')) {
                    return "A";
                }
            } else {
                board[x][y] = 'O';
                if (hasWinnder(x, y, 'O')) {
                    return "B";
                }
            }
        }

        if (moves.length == 9) {
            return "Draw";
        }
        return "Pending";
    }

    private boolean hasWinnder(int row, int col, char mark) {
        if (checkRow(row, mark) || checkColumn(col, mark) ||
            checkDiag(mark) || checkAntiDiag(mark)) {
            return true;
        }
        return false;
    }

    private boolean checkRow(int row, char mark) {
        for (int i = 0; i < 3; i++) {
            if (board[row][i] != mark) {
                return false;
            }
        }
        return true;
    }

    private boolean checkColumn(int col, char mark) {
        for (int i = 0; i < 3; i++) {
            if (board[i][col] != mark) {
                return false;
            }
        }
        return true;
    }

    private boolean checkDiag(char mark) {
        for (int i = 0; i < 3; i++) {
            if (board[i][i] != mark) {
                return false;
            }
        }
        return true;
    }

    private boolean checkAntiDiag(char mark) {
        int n = 3;
        for (int i = 0; i < 3; i++) {
            if (board[n-1-i][i] != mark) {
                return false;
            }
        }
        return true;
    }

    // method 2
    // time O(m)
    // space O(n)
    public String tictactoe_op(int[][] moves) {
        int n = 3;
        int[] row = new int[n];
        int[] col = new int[n];
        int diag = 0;
        int antidiag = 0;
        int player = 1;
        for (int[] move: moves) {
            int x = move[0];
            int y = move[1];

            row[x] += player;
            col[y] += player;

            if (x == y) {
                diag += player;
            }
            if (x + y == n - 1) {
                antidiag += player;
            }

            if (Math.abs(row[x]) == n || Math.abs(col[y]) == n ||
                Math.abs(diag) == n || Math.abs(antidiag) == n) {
                return player == 1 ? "A" : "B";
            }
            player *= -1;
        }
        if (moves.length == n * n) {
            return "Draw";
        }
        return "Pending";
    }
}
