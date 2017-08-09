package com.alg;

import java.util.Arrays;

/**
 * Created by HAU on 7/16/2017.
 */

/*Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.

        click to show follow up.

        Follow up:
        Did you use extra space?
        A straight forward solution using O(mn) space is probably a bad idea.
        A simple improvement uses O(m + n) space, but still not the best solution.
        Could you devise a constant space solution?*/
public class Sol73_SetMatrixZeroes {
    public static void setZeroes(int[][] matrix){
        boolean row = false, col = false;
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j++){
                if ( matrix[i][j] == 0 ){
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                    if ( i == 0) row = true;
                    if ( j==0) col = true;
                }
            }

        }
        for (int i = 1; i < matrix.length;i++){
            if (matrix[i][0] == 0){
                for (int j = 1;j < matrix[0].length;j++){
                    matrix[i][j] = 0;
                }
            }
        }

        for (int j = 1; j < matrix[0].length;j++){
            if (matrix[0][j] == 0){
                for (int i = 1;i < matrix.length;i++){
                    matrix[i][j] = 0;
                }
            }
        }
        if (row){
            for ( int j = 0; j< matrix[0].length; j++){
                matrix[0][j] = 0;
            }
        }
        if (col){
            for ( int i = 0; i< matrix.length; i++){
                matrix[i][0] = 0;
            }
        }
    }

    public static void main(String[] args) {
        int[][] t= new int[4][5];
        for(int[] row: t){
            Arrays.fill(row,1);
        }
        t[2][0] = 0;
        //t[1][1] = 0;
        setZeroes(t);
        System.out.println();
        for ( int i = 0; i<t.length;i++){
            for (int j = 0; j < t[0].length;j++){
                System.out.print(t[i][j]+ " ");
            }
            System.out.println();
        }
    }
}
