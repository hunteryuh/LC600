package com.alg;

import java.util.Arrays;

/*
You are given an n x n grid representing a field of cherries, each cell is one of three possible integers.

0 means the cell is empty, so you can pass through,
1 means the cell contains a cherry that you can pick up and pass through, or
-1 means the cell contains a thorn that blocks your way.
Return the maximum number of cherries you can collect by following the rules below:

Starting at the position (0, 0) and reaching (n - 1, n - 1) by moving right or down through valid path cells (cells with value 0 or 1).
After reaching (n - 1, n - 1), returning to (0, 0) by moving left or up through valid path cells.
When passing through a path cell containing a cherry, you pick it up, and the cell becomes an empty cell 0.
If there is no valid path between (0, 0) and (n - 1, n - 1), then no cherries can be collected.
 */
public class Sol741_CherryPickup {
    // https://leetcode.com/problems/cherry-pickup/discuss/165218/Java-O(N3)-DP-solution-w-specific-explanation
    public int cherryPickup(int[][] grid) {
        int m = grid.length;
//        int n = grid[0].length; n == m
        // simulate as two guys start from 0,0 to m-1, n-1 with some constraints
        // x1 + y1 = x2 + y2
        int[][][] dp = new int[m+1][m+1][m+1];
        for (int[][] array: dp) {
            for(int[] row : array) {
                Arrays.fill(row, Integer.MIN_VALUE);
            }
        }
        dp[1][1][1] = grid[0][0];   // use 1 1 1 to avoid x-1 y-1 bound check; represent grid[0][0]
        for (int x1 = 1; x1 <= m; x1++) {
            for (int y1 = 1; y1 <= m; y1++) {
                for (int x2 = 1; x2 <= m; x2++) {
                    int y2 = x1 + y1 - x2;
                    if (dp[x1][y1][x2] <= 0 && y2 >= 1 && y2 <= m &&
                            grid[x1 - 1][y1 - 1] != -1 && grid[x2 - 1][y2 - 1] != -1) {
                        int cur = Math.max(Math.max(dp[x1 - 1][y1][x2], dp[x1 - 1][y1][x2 - 1]), Math.max(dp[x1][y1 - 1][x2], dp[x1][y1 - 1][x2 - 1]));
                        if (cur >= 0) {  // >= 0
                            dp[x1][y1][x2] = cur + grid[x1 - 1][y1 - 1] + grid[x2 - 1][y2 - 1];
                            if (x1 == x2) {  // if same x ,then same y, same location cherry picked can be picked only once
                                dp[x1][y1][x2] -= grid[x2 - 1][y2 - 1];
                            }
                        }
                    }
                }
            }
        }
        return Math.max(dp[m][m][m], 0);
    }
}
