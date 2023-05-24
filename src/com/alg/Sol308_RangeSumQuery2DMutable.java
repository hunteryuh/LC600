package com.alg;

/**
 * Created by HAU on 12/6/2017.
 */
/*Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by
its upper left corner (row1, col1) and lower right corner (row2, col2).
Example:

Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
update(3, 2, 2)   // (
sumRegion(2, 1, 4, 3) -> 10
*/
/*
/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * obj.update(row,col,val);
 * int param_2 = obj.sumRegion(row1,col1,row2,col2);
 */
public class Sol308_RangeSumQuery2DMutable {
    class NumMatrix {
        private int[][] colSum;
        //We use colSums[i][j] = the sum of ( matrix[0][j], matrix[1][j], matrix[2][j],......,matrix[i - 1][j] ).
        private int[][] matrix;
        public NumMatrix(int[][] matrix) {
            if ( matrix == null || matrix.length == 0 || matrix[0].length == 0) return ;
            this.matrix = matrix;
            int m = matrix.length;
            int n = matrix[0].length;
            colSum = new int[m+1][n];

            for ( int i = 1; i <= m; i++){
                for (int j = 0; j < n; j++){
                    colSum[i][j] = colSum[i-1][j] + matrix[i-1][j];
                }
            }
        }

        public void update(int row, int col, int val) {
            for ( int i = row + 1; i < colSum.length; i++){
                colSum[i][col] = colSum[i][col] - matrix[row][col] + val;
            }
            matrix[row][col] = val;
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int res = 0;
            for(int j = col1; j<= col2; j++){
                res += colSum[row2+1][j] - colSum[row1][j]; // excluding row1 + 1
            }
            return res;
        }
    }
}
