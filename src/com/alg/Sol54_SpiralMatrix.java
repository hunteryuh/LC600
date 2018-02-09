package com.alg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HAU on 2/9/2018.
 */
/*Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

For example,
Given the following matrix:

[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
You should return [1,2,3,6,9,8,7,4,5].*/
public class Sol54_SpiralMatrix {
    public static List<Integer> spiralOrder(int[][] matrix){
        // layer by layer
        List<Integer> res = new ArrayList<>();
        if(matrix.length == 0) return res;
        int r1 = 0;
        int r2 = matrix.length - 1;
        int c1 = 0;
        int c2 = matrix[0].length - 1;

        while( r1<= r2 && c1 <= c2){
            for(int c = c1; c <= c2; c++){
                res.add(matrix[r1][c]);
            }
            for(int r = r1 +1; r <= r2; r++){
                res.add(matrix[r][c2]);
            }
            if( r1 < r2 && c1 < c2){
                for(int c = c2 - 1; c> c1;c--){
                    res.add(matrix[r2][c]);
                }
                for(int r = r1; r>r1; r--){
                    res.add(matrix[r][c1]);
                }
            }
            r1++;r2--;
            c1++;c2--;
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] m = {
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12}
        };
        System.out.println(spiralOrder(m));
    }
}
