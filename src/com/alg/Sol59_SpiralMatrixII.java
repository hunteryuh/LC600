package com.alg;

import java.util.Arrays;

/**
 * Created by HAU on 2/9/2018.
 */
/*Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

For example,
Given n = 3,

You should return the following matrix:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]*/
public class Sol59_SpiralMatrixII {
    public static int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        if (n <= 0) return matrix;
        int num = 1;
        int rowbegin = 0;
        int rowend = n - 1;
        int colbegin = 0;
        int colend = n - 1;
        while(rowbegin <= rowend && colbegin <= colend){
            for(int i = colbegin; i <= colend; i++){
                matrix[rowbegin][i] = num++;
            }
            rowbegin++;
            for(int i = rowbegin; i<=rowend; i++){
                matrix[i][colend] = num++;
            }
            colend--;
            for(int i = colend; i >= colbegin; i--){
                matrix[rowend][i] = num++;
            }
            rowend--;
            for(int i = rowend; i>= rowbegin; i--){
                matrix[i][colbegin] = num++;
            }
            colbegin++;
        }
        return matrix;
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] m = generateMatrix(n);
        System.out.println(Arrays.deepToString(m));
    }

    public int[][] generateMatrix2(int n) {
        int[][] res = new int[n][n];
        int ri = 0;
        int rf = n - 1;
        int ci = 0;
        int cf = n - 1;
        int v = 1;
        if (n == 1) {
            res[0][0] = 1;
            return res;
        }
        while (ri < rf) {
            for (int j = ci; j < cf; j++) {
                res[ri][j] = v++;
            }
            for (int i = ri; i < rf; i++) {
                res[i][cf] = v++;
            }
            for (int j = cf; j > ci; j--) {
                res[rf][j] = v++;
            }
            for (int i = rf; i > ri; i--) {
                res[i][ci] = v++;
            }
            ri++;
            rf--;
            ci++;
            cf--;
            if (ri == rf) {
                res[n/2][n/2] = n*n;
            }
        }
        return res;
    }
}
