package com.alg;

import java.util.ArrayList;
import java.util.List;

/*Given two sparse matrices A and B, return the result of AB.

You may assume that A's column number is equal to B's row number.

Example:

A = [
  [ 1, 0, 0],
  [-1, 0, 3]
]

B = [
  [ 7, 0, 0 ],
  [ 0, 0, 0 ],
  [ 0, 0, 1 ]
]


     |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
                  | 0 0 1 |
*/
public class Sol311_SparseMatrixMultiplication {
    public static int[][] multiply(int[][] A, int[][] B) {
/*A sparse matrix can be represented as a sequence of rows,
each of which is a sequence of (column-number, value) pairs of the nonzero values in the row.*/
        int m = A.length;
        int n = A[0].length;
        int nB = B[0].length;
        int[][] res = new int[m][nB];
        List[] indexA = new List[m];
        for(int i = 0; i < m; i++){
            List<Integer> numsA = new ArrayList<>();
            for(int j = 0; j < n; j++){
                if(A[i][j] != 0){
                    numsA.add(j);
                    numsA.add(A[i][j]);
                }
            }
            indexA[i] = numsA;
        }
        for(int i = 0; i < m; i++){
            List<Integer> numsA = indexA[i];
            for(int p = 0; p < numsA.size() - 1; p +=2){
                int col = numsA.get(p);
                int val = numsA.get(p+1);
                for(int j = 0; j < nB; j++){
                    int valB = B[col][j];
                    res[i][j] += val * valB;
                }
            }
        }
        return res;
    }

    // concise solution 2
    public static int[][] SMM(int[][] A, int[][] B){
        int m = A.length;
        int n = A[0].length;
        int nB = B[0].length;
        int[][] res = new int[m][nB];

        for(int i = 0; i < m; i++){
            for(int k = 0; k < n; k++){
                if (A[i][k]!=0){
                    for(int j = 0; j < nB; j++){
                        if(B[k][j] != 0){
                            res[i][j] += A[i][k] * B[k][j];
                        }
                    }
                }
            }
        }
        return res;
    }
    /*if A[ i ] [ k ] == 0 or B[ k ] [ j ] == 0, it just skip the multiplication .
    This is achieved by moving for-loop" for ( k = 0; k < n; k++ ) " from inner-most loop to middle loop,
    so that we can use if-statement to tell whether A[ i ] [ k ] == 0 or B[ k ] [ j ] == 0. T*/
}
