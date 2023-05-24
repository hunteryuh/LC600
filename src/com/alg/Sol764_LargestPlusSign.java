package com.alg;

import java.util.Arrays;

/*
You are given an integer n. You have an n x n binary grid grid with all values initially 1's except for some indices given in the array mines. The ith element of the array mines is defined as mines[i] = [xi, yi] where grid[xi][yi] == 0.

Return the order of the largest axis-aligned plus sign of 1's contained in grid. If there is none, return 0.

An axis-aligned plus sign of 1's of order k has some center grid[r][c] == 1 along with four arms of length k - 1 going up, down, left, and right, and made of 1's. Note that there could be 0's or 1's beyond the arms of the plus sign, only the relevant area of the plus sign is checked for 1's.



Example 1:


Input: n = 5, mines = [[4,2]]
Output: 2
Explanation: In the above grid, the largest plus sign can only be of order 2. One of them is shown.
Example 2:


Input: n = 1, mines = [[0,0]]
Output: 0
Explanation: There is no plus sign, so return 0.


Constraints:

1 <= n <= 500
1 <= mines.length <= 5000
0 <= xi, yi < n
All the pairs (xi, yi) are unique.
 */
public class Sol764_LargestPlusSign {
    // https://leetcode.com/problems/largest-plus-sign/discuss/113314/JavaC%2B%2BPython-O(N2)-solution-using-only-one-grid-matrix
    public int orderOflargestPlusSign(int n, int[][] mines) {
        int[][] grid = new int[n][n];
        for (int[] row : grid) {
            Arrays.fill(row, n);
        }
        for (int[] mine: mines) {
            grid[mine[0]][mine[1]] = 0;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0, k = n - 1, left = 0, right = 0, up = 0, down = 0; j < n; j++ , k--) {
//                int left = 0, right = 0, up = 0, down = 0;
                grid[i][j] = Math.min(grid[i][j], left = (grid[i][j] == 0 ? 0 : left + 1));
                grid[i][k] = Math.min(grid[i][k], right = (grid[i][k] == 0 ? 0 : right + 1));
                grid[j][i] = Math.min(grid[j][i], up = (grid[j][i] == 0 ? 0 : up + 1));
                grid[k][i] = Math.min(grid[k][i], down = (grid[k][i] == 0 ? 0 : down + 1));

            }
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j ++) {
                res = Math.max(res, grid[i][j]);
            }
        }
        return res;
    }

    // easier in java
    public int orderOfLargestPlusSign(int N, int[][] mines) {
        int[][] M = new int[N][N];
        for (int[] arr : M) {
            Arrays.fill(arr, 1);
        }
        for (int[] arr : mines) {
            M[arr[0]][arr[1]] = 0;
        }
        for (int i = 0; i < N; i++) {
            int count = 0;   //left
            for (int j = 0; j < N; j++) {
                if (M[i][j] != 0) {
                    count++;
                } else {
                    count = 0;
                }
                M[i][j] = count;
            }
            count = 0; //right
            for (int j = N - 1; j >= 0; j--) {
                if (M[i][j] != 0) {
                    count++;
                } else {
                    count = 0;
                }
                M[i][j] = Math.min(M[i][j], count);
            }
        }
        int result = 0;
        for (int j = 0; j < N; j++) {
            int count = 0;
            for (int i = 0; i < N; i++) { //up
                if (M[i][j] != 0) {
                    count++;
                } else {
                    count = 0;
                }
                M[i][j] = Math.min(M[i][j], count);
            }
            count = 0; //down
            for (int i = N - 1; i >= 0; i--) {
                if (M[i][j] != 0) {
                    count++;
                } else {
                    count = 0;
                }
                M[i][j] = Math.min(M[i][j], count);
                result = Math.max(result, M[i][j]);
            }
        }
        return result;
    }
}
