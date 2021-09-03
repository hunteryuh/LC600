package com.alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*

Given a m * n matrix of distinct numbers, return all lucky numbers in the matrix in any order.

A lucky number is an element of the matrix such that it is the minimum element in its row and maximum in its column.



Example 1:

Input: matrix = [[3,7,8],[9,11,13],[15,16,17]]
Output: [15]
Explanation: 15 is the only lucky number since it is the minimum in its row and the maximum in its column
Example 2:

Input: matrix = [[1,10,4,2],[9,3,8,7],[15,16,17,12]]
Output: [12]
Explanation: 12 is the only lucky number since it is the minimum in its row and the maximum in its column.
Example 3:

Input: matrix = [[7,8],[1,2]]
Output: [7]
 */
public class Sol1380_LuckNumberInAMatrix {
    public List<Integer> luckyNumbers (int[][] matrix) {
        List<Integer> minRow = new ArrayList<>();
        for(int[] row : matrix) {
            int min = Arrays.stream(row).min().getAsInt();
            minRow.add(min);
        }
        List<Integer> maxCol = new ArrayList<>();
        for (int j = 0; j < matrix[0].length; j++) {
            int max = matrix[0][j];
            for (int i = 0; i < matrix.length; i++) {
                max = Math.max(max, matrix[i][j]);
            }
            maxCol.add(max);
        }
        minRow.removeIf( i -> !maxCol.contains(i));
        return minRow;
    }


    // set intersection, similar as above
    public List<Integer> luckyNumbers1 (int[][] matrix) {
        Set<Integer> minSet = new HashSet<>(), maxSet = new HashSet<>();
        for (int[] row : matrix) {
            int mi = row[0];
            for (int cell : row)
                mi = Math.min(mi, cell);
            minSet.add(mi);
        }
        for (int j = 0; j < matrix[0].length; ++j) {
            int mx = matrix[0][j];
            for (int i = 0; i < matrix.length; ++i)
                mx = Math.max(matrix[i][j], mx);
            if (minSet.contains(mx))
                maxSet.add(mx);
        }
        return new ArrayList<>(maxSet);
    }

    // method 2
    // https://leetcode.com/problems/lucky-numbers-in-a-matrix/discuss/539731/JavaPython-3-Two-23-pass-codes-w-brief-explanation-and-analysis.
    public List<Integer> luckyNumbers2 (int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[] mi = new int[m], mx = new int[n];
        Arrays.fill(mi, Integer.MAX_VALUE);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                mi[i] = Math.min(matrix[i][j], mi[i]);
                mx[j] = Math.max(matrix[i][j], mx[j]);
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (mi[i] == mx[j])  {
                    res.add(mi[i]);
                    break;           // credit to @Ausho_Roup
                }
            }
        }
        return res;
    }

    // intuitive 2
    public List<Integer> luckyN(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        for(int row = 0; row < matrix.length; row++) {
            int colOfMinRow = getColIndexOfMinRow(matrix, row);
            int number = matrix[row][colOfMinRow];
            if (isMaxInCol(number, matrix, colOfMinRow)) {
                res.add(number);
            }
        }
        return res;
    }

    private int getColIndexOfMinRow(int[][] matrix, int row) {
        int col = 0;
        int minRow = matrix[row][col];
        for (int j = 0; j < matrix[row].length; j++) {
            if (matrix[row][j] < minRow) {
                minRow = matrix[row][j];
                col = j;
            }
        }
        return col;
    }
    private boolean isMaxInCol(int val, int[][] matrix, int col) {
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][col] > val) return false;
        }
        return true;
    }


}
