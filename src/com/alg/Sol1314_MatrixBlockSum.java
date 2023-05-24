package com.alg;
/*
Given a m x n matrix mat and an integer k, return a matrix answer where each answer[i][j] is the sum of all elements mat[r][c] for:

i - k <= r <= i + k,
j - k <= c <= j + k, and
(r, c) is a valid position in the matrix.


Example 1:

Input: mat = [[1,2,3],[4,5,6],[7,8,9]], k = 1
Output: [[12,21,16],[27,45,33],[24,39,28]]
Example 2:

Input: mat = [[1,2,3],[4,5,6],[7,8,9]], k = 2
Output: [[45,45,45],[45,45,45],[45,45,45]]


Constraints:

m == mat.length
n == mat[i].length
1 <= m, n, k <= 100
1 <= mat[i][j] <= 100
 */
public class Sol1314_MatrixBlockSum {
    public int[][] matrixBlockSum(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] sum = new int[m + 1][n+1];
        // sum[i][j] is the sum of all element from rectangle (0,0,i,j)
        // as left, top, right, bottom corresponding
        for (int r = 1; r <=m; r++) {
            for (int c = 1; c <= n; c++) {
                sum[r][c] = sum[r][c-1] + sum[r-1][c] - sum[r-1][c-1] + mat[r-1][c-1];
            }
        }
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int r1 = Math.max(0, i - k);
                int c1 = Math.max(0, j - k);
                int r2 = Math.min(m-1, i + k);
                int c2 = Math.min(n-1, j + k);
                r1++;c1++;r2++;c2++; //  // Since `sum` start with 1 so we need to increase r1, c1, r2, c2 by 1
                ans[i][j] = sum[r2][c2] - sum[r2][c1-1] - sum[r1-1][c2] + sum[r1-1][c1-1];
            }
        }
        return ans;
    }
}
