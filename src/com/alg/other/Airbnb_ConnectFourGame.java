package com.alg.other;
/*
Connect Four is a two-player connection board game.
The players choose a color and then take turns dropping colored tokens into a seven-column, six-row vertically suspended grid.
The pieces fall straight down, occupying the lowest available space within the column.
The objective of the game is to be the first to form a horizontal, vertical, or diagonal line of four of one's own tokens. - Wikipedia

The input is a list of columns played successively by the two players.
The output is to display the Connect Four grid associated when the game finishes.

If a winning state is reached or if a wrong move is played, the game should stop even if there are still columns in the list.
In that case, only the tokens that have been played should be displayed in the grid.

The goal is to write code that is well-structured, rather than a solution that is too complex.
Optimisation may be discussed during follow-up questions.

Examples:
Input (column full):
[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2]

Output:
| | |X| | | | |
| | |0| | | | |
| | |X| | | | |
| | |0| | | | |
| | |X| | | | |
| | |0| | | | |

In this example, we start playing with the 0 token.
The 7th item of the input is invalid because the column is already full.
 */
// asked on 11/8 airbnb interview
import java.util.Arrays;
public class Airbnb_ConnectFourGame {
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private char[][] grid;
    private char currentPlayer;

    public Airbnb_ConnectFourGame() {
        grid = new char[ROWS][COLUMNS];
        for (char[] row : grid) {
            Arrays.fill(row, ' ');
        }
        currentPlayer = 'X'; // Player 1 starts with 'X'
    }

    public boolean play(int column) {
        if (column < 0 || column >= COLUMNS) {
            System.out.println("Invalid move: Column out of bounds.");
            return false;
        }

        // Find the lowest available row in the specified column
        for (int row = ROWS - 1; row >= 0; row--) {
            if (grid[row][column] == ' ') {
                grid[row][column] = currentPlayer; // Place the token
                if (checkWin(row, column)) {
                    System.out.println("Player " + currentPlayer + " wins!");
                }
                currentPlayer = (currentPlayer == 'X') ? '0' : 'X'; // Switch player
                return true;
            }
        }

        System.out.println("Invalid move: Column is full.");
        return false;
    }

    private boolean checkWin(int row, int column) {
        return checkDirection(row, column, 1, 0) || // horizontal
                checkDirection(row, column, 0, 1) || // vertical
                checkDirection(row, column, 1, 1) || // diagonal \
                checkDirection(row, column, 1, -1);   // diagonal /
    }

    private boolean checkDirection(int row, int column, int rowIncrement, int colIncrement) {
        char token = grid[row][column];
        int count = 1;
        // the key to find if there is 4 in a row
        // Check in the positive direction
        count += countTokens(row, column, rowIncrement, colIncrement, token);
        // Check in the negative direction
        count += countTokens(row, column, -rowIncrement, -colIncrement, token);

        return count >= 4;
    }

    private int countTokens(int row, int column, int rowIncrement, int colIncrement, char token) {
        int count = 0;
        int r = row + rowIncrement;
        int c = column + colIncrement;

        while (r >= 0 && r < ROWS && c >= 0 && c < COLUMNS && grid[r][c] == token) {
            count++;
            r += rowIncrement;
            c += colIncrement;
        }
        return count;
    }

    public void displayGrid() {
        for (char[] row : grid) {
            System.out.print("|");
            for (char cell : row) {
                System.out.print(cell + "|");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Airbnb_ConnectFourGame game = new Airbnb_ConnectFourGame();
        int[] moves = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}; // Example input

        for (int move : moves) {
            if (!game.play(move)) {
                break; // Stop if an invalid move is made
            }
        }

        game.displayGrid(); // Display the final state of the grid
    }

}
