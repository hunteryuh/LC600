package com.alg;

/**
 * Created by HAU on 9/6/2017.
 */
/*
Given a 2D integer matrix M representing the gray scale of an image,
        you need to design a smoother to make the gray scale of each cell
        becomes the average gray scale (rounding down) of all the 8 surrounding cells and itself.
        If a cell has less than 8 surrounding cells, then use as many
        as you can.*/
public class Sol661_ImageSmoother {
    public static int[][] imageSmoother(int[][] M){
        int R = M.length;
        int C = M[0].length;
        int[][] res = new int[R][C];
        for (int i = 0 ; i < R; i++){
            for (int j = 0; j < C; j++){
                int count = 0;
                int sum = 0;
                for (int dr = -1 ; dr <=1; dr++){
                    for (int dc = -1; dc <= 1; dc++){
                        if (isValid(i+dr, j+dc, R, C)){
                            count++;
                            sum += M[i + dr][ j + dc];
                        }
                    }
                }
                res[i][j] = sum/count;
            }
        }
        return res;

    }
    private static boolean isValid(int x, int y, int rn, int cn){
        return x >=0 && x < rn && y >=0 && y < cn;
    }

    public static void main(String[] args) {
        return;
    }
}
