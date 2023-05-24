package com.alg;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by HAU on 10/11/2017.
 */
public class Sol_EightQueens {
    public static boolean isConsistent(int[] q, int n){
        for (int i = 0; i < n; i++){
            if(q[i] == q[n]) return false;
            if(q[i] - q[n] == (n - i)) return false;
            if(q[n] - q[i] == (n - i)) return false;

        }
        return true;
    }

    public static void printQueens(int[] q){
        int n = q.length;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n ; j++){
                if(q[i] == j) System.out.print("Q ");
                else System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println("nn");
    }

    //try all permutations using backtracking
    public static void perm(int n){
        int[] a = new int[n];
        perm(a,0);
    }
    public static void perm(int[] q, int k){
        int n = q.length;
        if (k == n) printQueens(q);
        else{
            for (int i = 0; i < n ; i++){
                q[k] = i;
                if(isConsistent(q,k)) perm(q,k+1);
            }
        }
    }

    public static void main(String[] args) {
        //int n = 4;
        //perm(n);
        //solveNQ();
        List<List<String>> sol = solveNQueens(4);
        System.out.println(sol.toString());
    }

    // design 2
    public static void printSol(int[][] board){
        int n = board.length;
        for (int i = 0; i< n; i++){
            for (int j = 0; j < n ; j++){
                System.out.print(" " + board[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static boolean isSafe(int[][] board, int row, int col){
        int i,j;
        //check left rows
        for (i = 0; i < col; i++){
            if (board[row][i] == 1) return false;
        }
        // upper left diagonal
        for ( i = row , j = col; i>=0 && j >=0; i--,j--) {
            if (board[i][j] == 1) return false;
        }
        //lower left diagnonal
        for ( i = row, j= col; i< board.length && j>=0; i++,j--){
            if(board[i][j] == 1) return false;
        }
        return true;
    }
    // recursion
    public static boolean solveNQhelper(int[][] board, int col){
        if (col >= board.length) return true;
        // consider this column and try placing queen in all rows one by one
        for (int i = 0; i < board.length; i++){
            if (isSafe(board,i,col)){
                board[i][col] = 1;
                // recursively place rest of the queens
                if (solveNQhelper(board, col + 1) == true){
                    return true;
                }
                // if placing queen in board[i][col] does not give a solution, remove the queen at board[i][col]
                board[i][col] = 0;
            }
        }
        return false;
    }
    public static boolean solveNQ(){
        int[][] board={
                {0,0,0,0},
                {0,0,0,0},
                {0,0,0,0},
                {0,0,0,0}
        };
        if (solveNQhelper(board,0) == false){
            System.out.println("no solution");
            return false;
        }
        printSol(board);
        return true;
    }

    // for leetcode, easy to understand
    public static List<List<String>> solveNQueens(int n){
        char[][] bd = new char[n][n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j< n; j++){
                bd[i][j] = '.';
            }
        }
        List<List<String>> res = new ArrayList<>();
        dfs(bd,0,res);
        return res;
    }

    private static void dfs(char[][] bd, int col, List<List<String>> res) {
        if (col == bd.length){
            res.add(create(bd));
            return;
        }
        for (int i = 0; i< bd.length; i++){
            if (isValid(bd,i,col)){
                bd[i][col] ='Q';
                dfs(bd,col+1, res);
                bd[i][col] ='.';
            }
        }
    }

    private static boolean isValid(char[][] bd, int row, int col) {
        for ( int i = 0; i < bd.length; i++){
            for (int j = 0; j < bd.length; j++){
                if (bd[i][j] == 'Q' && (i - j == row - col || i == row || i - row == col - j))
                    return false;
            }
        }
        return true;
    }

    private static List<String> create(char[][] bd) {
        List<String> res = new ArrayList<>();
        for ( int i  = 0; i < bd.length; i++){
            String s = new String(bd[i]);
            res.add(s);
        }
        return res;
    }


}
