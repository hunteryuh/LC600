package com.alg;

import java.util.List;

/*
A row-sorted binary matrix means that all elements are 0 or 1 and each row of the matrix is sorted in non-decreasing order.

Given a row-sorted binary matrix binaryMatrix, return the index (0-indexed) of the leftmost column with a 1 in it. If such an index does not exist, return -1.

You can't access the Binary Matrix directly. You may only access the matrix using a BinaryMatrix interface:

    BinaryMatrix.get(row, col) returns the element of the matrix at index (row, col) (0-indexed).
    BinaryMatrix.dimensions() returns the dimensions of the matrix as a list of 2 elements [rows, cols], which means the matrix is rows x cols.

Submissions making more than 1000 calls to BinaryMatrix.get will be judged Wrong Answer. Also, any solutions that attempt to circumvent the judge will result in disqualification.

For custom testing purposes, the input will be the entire binary matrix mat. You will not have access to the binary matrix directly.



Example 1:

Input: mat = [[0,0],[1,1]]
Output: 0

Example 2:

Input: mat = [[0,0],[0,1]]
Output: 1

Example 3:

Input: mat = [[0,0],[0,0]]
Output: -1



Constraints:

    rows == mat.length
    cols == mat[i].length
    1 <= rows, cols <= 100
    mat[i][j] is either 0 or 1.
    mat[i] is sorted in non-decreasing order.


 */
public class Sol1428_LeftmostColumnWithAtLeastAOne {
    /**
     * // This is the BinaryMatrix's API interface.
     * // You should not implement it, or speculate about its implementation
     * interface BinaryMatrix {
     *     public int get(int row, int col) {}
     *     public List<Integer> dimensions {}
     * };
     */
     interface BinaryMatrix {
          public int get(int row, int col) ;
          public List<Integer> dimensions() ;
     };
    // time too long, linear search
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        int rows = binaryMatrix.dimensions().get(0);
        int cols = binaryMatrix.dimensions().get(1);
        int smallestIndex = cols;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (binaryMatrix.get(row, col) == 1) {
                    smallestIndex = Math.min(smallestIndex, col);
                    break;
                }
            }
        }
        return smallestIndex == cols ? -1 : smallestIndex;
    }

    // binary search in each row, time: O(m log n), m: number of rows, n: number of columns
    public int leftMostColWithOne(BinaryMatrix bm) {
        int m = bm.dimensions().get(0);
        int n = bm.dimensions().get(1);
        int res = n;
        for (int i = 0; i < m; i++) {
            int col = binarySearch(bm, i, 0, n - 1);
            if (col != -1) {
                res = Math.min(res, col);
            }
        }
        if (res == n) {
            return -1;
        }
        return res;
    }
    private int binarySearch(BinaryMatrix bm, int row, int lo, int hi) {

        while (lo + 1 < hi) {
            int mid = lo + (hi - lo)/ 2;
            if (bm.get(row, mid) == 0) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        if (bm.get(row, lo) == 1) return lo;
        if (bm.get(row, hi) == 1) return hi;
        return -1;
    }

    // optimal N + M
    public int leftMostColumnWithOne2(BinaryMatrix binaryMatrix) {
        int rows = binaryMatrix.dimensions().get(0);
        int cols = binaryMatrix.dimensions().get(1);
        int curRow = 0;
        int curCol = cols - 1; // top right
        int ans = -1;
        while (curRow < rows && curCol >= 0) {
            if (binaryMatrix.get(curRow, curCol) == 1) {
                ans = curCol;
                curCol--;
            } else {
                curRow++;
            }
        }
        return ans;
    }


}
