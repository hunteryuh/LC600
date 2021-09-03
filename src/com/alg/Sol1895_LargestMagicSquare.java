package com.alg;
/*
A k x k magic square is a k x k grid filled with integers such that every row sum, every column sum, and both diagonal sums are all equal. The integers in the magic square do not have to be distinct. Every 1 x 1 grid is trivially a magic square.

Given an m x n integer grid, return the size (i.e., the side length k) of the largest magic square that can be found within this grid.



Example 1:


Input: grid = [[7,1,4,5,6],[2,5,1,6,4],[1,5,4,3,2],[1,2,7,3,4]]
Output: 3
Explanation: The largest magic square has a size of 3.
Every row sum, column sum, and diagonal sum of this magic square is equal to 12.
- Row sums: 5+1+6 = 5+4+3 = 2+7+3 = 12
- Column sums: 5+5+2 = 1+4+7 = 6+3+3 = 12
- Diagonal sums: 5+4+3 = 6+4+2 = 12
Example 2:


Input: grid = [[5,1,3,1],[9,3,3,1],[1,3,3,8]]
Output: 2
https://leetcode.com/problems/largest-magic-square/
 */
public class Sol1895_LargestMagicSquare {
    public int largestMagicSquare(int[][] grid) {
        int H = grid.length;
        int W = grid[0].length;
        int[][] rowsums = new int[H][W + 1];
        int[][] colsums = new int[H + 1][W];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                rowsums[i][j + 1] = rowsums[i][j] + grid[i][j];   // to allow j = 0
                colsums[i + 1][j] = colsums[i][j] + grid[i][j];   // to allow i = 0
            }
        }
        int max = Math.min(H, W);
        for (int size = max; size > 1; size--) {
            for (int i = 0; i <= H - size; i++) {
                for (int j = 0; j <= W - size; j++) {
//                    if (isMagic(grid, rowsums, colsums, i, j, size))//checks if a square with top left [i, j] and side length is magic
//                        return size;
                    int rsum = rowsums[i][j+size] - rowsums[i][j];
                    int csum = colsums[i + size][j] - colsums[i][j];
                    if (csum != rsum) {
                        continue;
                    }
                    int dsum = 0;
                    int adsum = 0;
                    for (int k = 0; k < size; k++) {
                        dsum += grid[i+k][j+k];
                        adsum += grid[i + size - 1 - k ][j + k];
                        if (rowsums[i + k][j + size] - rowsums[i + k][j] != rsum
                            || colsums[i + size][j + k] - colsums[i][j + k] != rsum) {
                            break;
                        }

                    }
                    if (dsum == rsum && adsum == rsum) {
                        return size;
                    }
                }
            }
        }
        return 1;
    }

    private boolean isMagic(int[][] g, int[][] rowsums, int[][] colsums, int r, int c, int size) {
        int sum = rowsums[r][c + size] - rowsums[r][c], d1 = 0, d2 = 0;
        for (int k = 0; k < size; k++) {
            d1 += g[r + k][c + k];
            d2 += g[r + size - 1 - k][c + k];
            if (rowsums[r + k][c + size] - rowsums[r + k][c] != sum || colsums[r + size][c + k] - colsums[r][c + k] != sum)//check each row and column
                return false;
        }
        return d1 == sum && d2 == sum;  //checks both diagonals
    }

    // https://leetcode.com/problems/largest-magic-square/discuss/1267298/Easy-O(N5)-Brute-to-O(N4)-Optimal-Solution-Intuition-Explanation-with-Images
    // O(N^5) + prefix sum -> O(N^4)
    public int largestMagicSquareBruteForce(int[][] grid) {
        int H = grid.length;
        int W = grid[0].length;
        int res = 1;

        int[][] rowsums = new int[H][W ];
        int[][] colsums = new int[H][W];
//        int[][] d1sums = new int[H][W];
//        int[][] d2sums = new int[H][W];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                rowsums[i][j] = (j>0 ? rowsums[i][j-1] : 0 ) + grid[i][j];
                colsums[i][j] = (i>0 ? colsums[i-1][j]: 0) + grid[i][j];
//                d1sums[i][j] = (i>0 && j > 0 ? d1sums[i-1][j-1] : 0) + grid[i][j];
//                d2sums[i][j] =  (i > 0 && j < W - 1 ? d2sums[i-1][j+1]: 0) + grid[i][j];
            }
        }

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                for (int size = 2; size <= Math.min(H,W); size++) {
                    int rowlast = i + size - 1;
                    int collast = j + size - 1;
                    if ( rowlast >= H || collast >= W) {
                        break;
                    }
                    int diag1 = 0;
                    int diag2 = 0;
                    for (int k = 0; k < size; k++) {
                        diag1 += grid[i+k][j+k];
                        diag2 += grid[i+k][collast-k];
                    }
                    if (diag1 != diag2) {
                        continue;
                    }
                    boolean isMagic = true;
                    for (int row = i; row <= rowlast; row++) {
//                        int sum = 0;
//                        for (int c = j; c<= collast; c++) {
//                            sum += grid[row][c];
//                        }
                        int sum = rowsums[row][collast] - (j > 0 ? rowsums[row][j-1] : 0);
                        if (sum != diag1) {
                            isMagic = false;
                            break;
                        }
                    }

                    for (int col = j; col <= collast; col++) {
//                        int sum  =0;
//                        for (int row = i; row <= rowlast; row ++) {
//                            sum += grid[row][col];
//                        }
                        int sum = colsums[rowlast][col] - (i > 0 ? colsums[i-1][col] : 0);
                        if (sum != diag1) {
                            isMagic = false;
                            break;
                        }
                    }
                    if (isMagic) {
                        res = Math.max(size, res);
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {

    }
}
