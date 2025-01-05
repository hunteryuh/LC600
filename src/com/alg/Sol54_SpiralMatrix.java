package com.alg;

import java.util.ArrayList;
import java.util.LinkedList;
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


https://leetcode.com/problems/spiral-matrix/
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
            if(r1 < r2 && c1 < c2) {
                for (int c = c2 - 1; c> c1; c--){
                    res.add(matrix[r2][c]);
                }
                for (int r = r2; r>r1; r--){
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

        Sol54_SpiralMatrix ss = new Sol54_SpiralMatrix();
        List<Integer> res = ss.spiralOrder3(m);
        System.out.println(res);
    }

    public List<Integer> spiralOrder2(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int r1 = 0;
        int r2 = matrix.length - 1;
        int c1 = 0;
        int c2 = matrix[0].length - 1;

        while (r1 <= r2 && c1 <= c2) {
            for (int i = c1; i <= c2; i++) {
                res.add(matrix[r1][i]);
            }
            for (int i = r1 + 1; i <= r2; i++) {
                res.add(matrix[i][c2]);
            }
            if (r1 < r2) {  // not the same row reverse
                for (int i = c2 - 1; i >= c1; i--) {
                    res.add(matrix[r2][i]);
                }
            }
            if (c1 < c2) {  // not the same col reverse
                for (int i = r2 - 1; i > r1; i--) {
                    res.add(matrix[i][c1]);
                }
            }

            r1++;
            r2--;
            c1++;
            c2--;
        }
        return res;
    }

    public List<Integer> spiralOrder3(int[][] matrix) {
        List<Integer> res = new LinkedList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        int up = 0;
        int down = m - 1;
        int left = 0;
        int right = n - 1;
        while (res.size() < m * n) {
            for (int j = left; j <= right && res.size() < m * n; j++) { // use res.size() < m * n to stop anytime it finds all numbers
                res.add(matrix[up][j]);
            }
            for (int i = up + 1; i <= down - 1 && res.size() < m * n; i++) {
                res.add(matrix[i][right]);
            }
            for (int j = right; j >= left && res.size() < m * n; j--) {
                res.add(matrix[down][j]);
            }
            for (int i = down - 1; i > up && res.size() < m * n; i--) {
                res.add(matrix[i][left]);
            }
            up++; down--;left++;right--;
        }
        return res;
    }
}
