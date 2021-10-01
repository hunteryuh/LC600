package com.alg;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by HAU on 11/27/2017.
 */
/*Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
* A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.*/
public class Sol36_ValidSudoku {
    public static boolean isValidSudoku(char[][] board) {
        Set set = new HashSet();
        assert board.length == 9;
        for ( int i = 0; i< 9; i++){
            for (int j = 0; j < 9; j++){
                if (board[i][j] != '.'){
                    if (!set.add(board[i][j] +" in row "+ i)||
                            !set.add(board[i][j] + " in column " + j)||
                            !set.add(board[i][j] + " in block" + i/3 +"-" + j/3)
                            ){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isValidSudoku2(char[][] board) {
        int n = board.length;
        Set<Character>[] rows = new HashSet[n];
        Set<Character>[] cols = new HashSet[n];
        Set<Character>[] boxes = new HashSet[n];
        for (int i = 0; i < n; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != '.') {
                    char c = board[i][j];
                    if (rows[i].contains(c)) {
                        return false;
                    }
                    rows[i].add(c);
                    if (cols[j].contains(c)) {
                        return false;
                    }
                    cols[j].add(c);
                    int boxid = i/3 * 3 + j/3;
                    //  0 1  2 3 4 5 6 7 8 9
                    //0
                    //1
                    //2
                    if (boxes[boxid].contains(c)) {
                        return false;
                    }
                    boxes[boxid].add(c);
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {

    }
}
