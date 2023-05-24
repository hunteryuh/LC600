package com.alg;
/*
You are given an m x n integer array grid where grid[i][j] could be:

    1 representing the starting square. There is exactly one starting square.
    2 representing the ending square. There is exactly one ending square.
    0 representing empty squares we can walk over.
    -1 representing obstacles that we cannot walk over.

Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.



Example 1:

Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
Output: 2
Explanation: We have the following two paths:
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)

Example 2:

Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
Output: 4
Explanation: We have the following four paths:
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)

Example 3:

Input: grid = [[0,1],[2,0]]
Output: 0
Explanation: There is no path that walks over every empty square exactly once.
Note that the starting and ending square can be anywhere in the grid.



Constraints:

    m == grid.length
    n == grid[i].length
    1 <= m, n <= 20
    1 <= m * n <= 20
    -1 <= grid[i][j] <= 2
    There is exactly one starting cell and one ending cell.


 */
public class Sol980_UniquePathsIII {
    // https://leetcode.com/problems/unique-paths-iii/discuss/221946/JavaPython-Brute-Force-Backtracking
    int res = 0;
    int startX = 0;
    int startY = 0;
    int zeros = 1;

    public int uniquePathsIII(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    zeros++;
                }
                if (grid[i][j] == 1) {
                    startX = i;
                    startY = j;
                }
            }
        }
        dfs(grid, startX, startY);
        return res;
    }

    private void dfs(int[][] grid, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] < 0) {
            return;
        }
        if (grid[x][y] == 2) {
            if (zeros == 0) {
                res++;
            }
            return;
        }
        grid[x][y] = -2;
        zeros--;
        dfs(grid, x + 1, y);
        dfs(grid, x - 1, y);
        dfs(grid, x, y + 1);
        dfs(grid, x, y - 1);
        grid[x][y] = 0;
        zeros++;
    }

    int total = 0;
    int[][] dirs = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    public int uniquePathsIII2(int[][] grid) {
        int nonObstacles = 0;
        int m = grid.length;
        int n = grid[0].length;
        int sx = 0;
        int sy = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] >= 0) nonObstacles++;
                if (grid[i][j] == 1) {
                    sx = i;
                    sy = j;
                }
            }
        }
        backtrack(grid, sx, sy, nonObstacles);
        return total;
    }
    private void backtrack(int[][] grid, int x, int y, int remain) {
        if (grid[x][y] == 2 && remain == 1) {
            total++;
            return;
        }
        remain--;
        int temp = grid[x][y];  // has to store this temporary for backtrack, otherwise not working..

        grid[x][y] = -2;
        for (int i = 0; i < 4; i++) {
            int newx = x + dirs[i][0];
            int newy = y  + dirs[i][1];
            if (newx < 0 || newy < 0 || newx >= grid.length || newy >= grid[0].length || grid[newx][newy] < 0) {
                continue;
            }
            backtrack(grid, newx, newy, remain);
        }
        grid[x][y] = temp;
        // remain++;  // if without this step, works; if with this step. it works as well.  why?
    }
}
