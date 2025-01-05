package com.alg;

import java.util.*;

/*
Given a 1-indexed m x n integer matrix mat, you can select any cell in the matrix as your starting cell.

From the starting cell, you can move to any other cell in the same row or column, but only if the value of the destination cell is strictly greater than the value of the current cell. You can repeat this process as many times as possible, moving from cell to cell until you can no longer make any moves.

Your task is to find the maximum number of cells that you can visit in the matrix by starting from some cell.

Return an integer denoting the maximum number of cells that can be visited.



Example 1:

Input: mat = [[3,1],[3,4]]
Output: 2
Explanation: The image shows how we can visit 2 cells starting from row 1, column 2. It can be shown that we cannot visit more than 2 cells no matter where we start from, so the answer is 2.

Example 2:

Input: mat = [[1,1],[1,1]]
Output: 1
Explanation: Since the cells must be strictly increasing, we can only visit one cell in this example.

Example 3:

Input: mat = [[3,1,6],[-9,5,7]]
Output: 4
Explanation: The image above shows how we can visit 4 cells starting from row 2, column 1. It can be shown that we cannot visit more than 4 cells no matter where we start from, so the answer is 4.



Constraints:

    m == mat.length
    n == mat[i].length
    1 <= m, n <= 105
    1 <= m * n <= 105
    -105 <= mat[i][j] <= 105


 */
public class Sol2713_MaximumStrictlyIncreasingCellsInAMatrix {
    // https://leetcode.com/problems/maximum-strictly-increasing-cells-in-a-matrix/solutions/3570203/longest-path-in-c-java-python/
    /*
    Approach

    Let m and n be the number of rows and columns of the matrix. Create 2 arrays r and c whose lengths are m and n. Initialize them to all 0s. They are the maximum path length of each row or column.
    Sort all the values in the matrix in non increasing order, for each value v,
    save all the positions (x, y) of that value in a list (reverse indexed).
    Loop all the values from largest to smallest, for each v,
    all the occurrence positions (x, y), save temp[x][y] = max(r[x], c[y]) + 1, this is the longest path starting from that value.
    Update r[x] = max(r[x], temp[x][y]) and c[y] = max(c[y], temp[x][y]) for all the above (x, y)s.
    This is to update the longest path for each row and column. Also note this step should be done after step 3 is fully complete.
    The maximum value in arrays r and c is the final answer.

     */
    public int maxIncreasingCells(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        Map<Integer, List<int[]>> map = new TreeMap<>(Collections.reverseOrder()); // greatest is the first key in the keyset
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map.computeIfAbsent(mat[i][j], x -> new ArrayList<>()).add(new int[]{i, j});
            }
        }

        int[][] cellMax = new int[m][n];
        int[] rowMax = new int[m], colMax = new int[n];

        for (int val : map.keySet()) {
            for (int[] cell : map.get(val)) {
                int i = cell[0], j = cell[1];
                cellMax[i][j] = 1 + Math.max(rowMax[i], colMax[j]);
            }

            for (int[] cell : map.get(val)) {
                int i = cell[0], j = cell[1];
                rowMax[i] = Math.max(rowMax[i], cellMax[i][j]);
                colMax[j] = Math.max(colMax[j], cellMax[i][j]);
            }
        }
        int max = 0;
        for (int x: rowMax) {
            max = Math.max(x, max);
        }
        for (int x: colMax) {
            max = Math.max(x, max);
        }

        return max;
    }
}
