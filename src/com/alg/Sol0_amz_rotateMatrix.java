package com.alg;

import java.util.Arrays;

/**
 * Created by HAU on 1/1/2018.
 */
/*把一个m*n的矩阵旋转90度，给一个flag规定是向左转还是向右转
*/
public class Sol0_amz_rotateMatrix {
    public static int[][] rotate90Matrix(int[][] matrix, int flag){
        int m = matrix.length;
        int n = matrix[0].length; // columns number
        int[][] res = new int[n][m];
        // first: transpose
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                res[i][j] = matrix[j][i];
            }
        }
        //then, inline inverse (flip)
        flip(res,flag);
        return res;
    }

    private static void flip(int[][] res, int flag) {
        int m = res.length;
        int n = res[0].length;
        if (flag == 1){  // right rotate, clockwise 90 degree
            for(int i = 0; i < m; i++){
                for(int j = 0; j < n/2; j++){
                    int temp = res[i][j];
                    res[i][j] = res[i][n-1-j];
                    res[i][n-1-j] = temp;

                    // way 2 without temp
                    /*
                    matrix[i][j] ^= matrix[i][n-j-1];
                    matrix[i][n-j-1] ^= matrix[i][j];
                    matrix[i][j] ^= matrix[i][n-j-1];*/
                }
            }
        }
        if ( flag == -1){
            for(int i = 0; i < m/2; i++){ // row 0 and last row switch
                for (int j = 0; j < n;j++){
                    res[i][j] = res[i][j]^res[m-1-i][j];
                    res[m-1-i][j] = res[i][j]^res[m-1-i][j];
                    res[i][j] = res[i][j]^res[m-1-i][j];
                }
            }
        }

    }

    public static void main(String[] args) {
        int[][] matrix = {
                {3,2,6},
                {1,9,0}
        };
        int[][] res = rotate90Matrix(matrix,1);
        int[][] resn90 = rotate90Matrix(matrix,-1);
        System.out.println(Arrays.deepToString(res));
        System.out.println(Arrays.deepToString(resn90));

        int[][] matrix2 = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        //int[][] r2_right90 = rotateImage(matrix2,1);
        int[][] r2_left90 = rotateImage(matrix2,0);
        //System.out.println(Arrays.deepToString(r2_right90));
        System.out.println(Arrays.deepToString(r2_left90));


    }
    /*Rotate Left(counter-clockwise):
transpose()
swapRows()
Rotate Right(clockwise):
swapRows()
transpose()*/
    public static void transpose(int[][] matrix){
        if(matrix == null || matrix.length == 0||
                matrix[0] == null || matrix[0].length == 0){
            return;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        for(int i = 0; i < m ; i++){
            for (int j = i + 1; j < n ;j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
    public static void swapRows(int[][] matrix){
        if(matrix == null || matrix.length == 0||
                matrix[0] == null || matrix[0].length == 0){
            return;
        }
        int m = matrix.length;
        int start = 0;
        int end = m - 1;
        while ( start < end){
            int[] tmp = matrix[start];
            matrix[start] = matrix[end];
            matrix[end] = tmp;
            start++;end--;
        }

    }
    // flag == 0, rotate left; flag == 1, rotate right
    public static int[][] rotateImage(int[][] matrix, int flag) {
        if (flag == 0) { // rotate left
            transpose(matrix);
            swapRows(matrix);
        } else { // rotate right
            swapRows(matrix);
            transpose(matrix);
        }
        return matrix;
    }



}
