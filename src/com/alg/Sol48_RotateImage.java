package com.alg;

import java.util.Arrays;

/**
 * Created by HAU on 7/7/2017.
 */

/*You are given an n x n 2D matrix representing an image.

        Rotate the image by 90 degrees (clockwise).

        Follow up:
        Could you do this in-place?*/
public class Sol48_RotateImage {
    public static void rotate_notworking(int[][] matrix) {
        if (matrix.length != matrix[0].length) return;
        int n = matrix.length;
        int m = n;
        for ( int i = 0; i < m/2; i++,n--){
            for (int j = i; j < n-1; j++){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][n-1];
                matrix[j][n-1] = matrix[n-1][n-1-j];
                matrix[n-1][n-1-j] = matrix[n-1-j][i];
                matrix[n-1-j][i] = tmp;

//                int temp = matrix[0][0];
//                matrix[0][0] = matrix[0][3];
//                matrix[0][3] = matrix[3][3];
//                matrix[3][3] = matrix[3][0];
//                matrix[3][0] = temp;
//
//                int t2 = matrix[1][1];  //test for 5 x 5, at this step n = 4
//                matrix[1][1] = matrix[1][3];
//                matrix[1][3] = matrix[3][3]; /////
//                matrix[3][3] = matrix[3][1];
//                matrix[3][1] = tmp;
//
                int t4 = matrix[1][2];
                matrix[1][2]= matrix[2][3];
                matrix[2][3]= matrix[3][2];  ///// how to generalize?!
                matrix[3][2]= matrix[2][1];
                matrix[2][1]= t4;

//                int t3 = matrix[0][2];
//                matrix[0][2] = matrix[2][3];
//                matrix[2][3] = matrix[3][1];
//                matrix[3][1] = matrix[1][0];
//                matrix[1][0] = t3;
            }
        }
    }

    public static void rotate(int[][] matrix) {
        if (matrix.length != matrix[0].length) return;
        int n = matrix.length;
        for ( int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
        for ( int i = 0; i < n; i++) {
            for (int j = 0; j < n/2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n-1-j];
                matrix[i][n-1-j] = tmp;
            }
        }

    }

    public static void main(String[] args) {
        int[][] matrix={
                {0,11,21,31,42},
                {1,2,4,8,16},
                {2,6,12,18,24},
                {3,31,32,33,34},
                {4,41,42,43,44}
        };
        for ( int i = 0; i< matrix.length;i++){
            for (int j = 0; j < matrix[0].length;j++){
                System.out.print(matrix[i][j]+ " ");
            }
            System.out.println("");
        }
        System.out.println("---");
        rotate(matrix);
        for ( int i = 0; i< matrix.length;i++){
            for (int j = 0; j < matrix[0].length;j++){
                System.out.print(matrix[i][j]+ " ");
            }
            System.out.println();
        }
        //System.out.println(Arrays.deepToString(matrix));
    }
}
