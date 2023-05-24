package com.alg;

import java.util.Arrays;

/**
 You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).


 You have to rotate the image in-place, which means you have to modify the
 input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

        Follow up:
        Could you do this in-place?*/
public class Sol48_RotateImage {
    // https://leetcode.com/problems/rotate-image/solution/
    public void rotate2(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < (n + 1) / 2; i ++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - j - 1];
                matrix[n - 1 - i][n - j - 1] = matrix[j][n - 1 -i];
                matrix[j][n - 1 - i] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }
    }

    public static void rotate(int[][] matrix) {
        if (matrix.length != matrix[0].length) return;
        int n = matrix.length;
        // mirror along y = x
        for ( int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
        // mirror along y = n/2
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
