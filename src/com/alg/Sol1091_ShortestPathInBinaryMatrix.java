package com.alg;

import java.util.LinkedList;

/*
Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.

A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:

All the visited cells of the path are 0.
All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
The length of a clear path is the number of visited cells of this path.



Example 1:


Input: grid = [[0,1],[1,0]]
Output: 2
Example 2:


Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
Output: 4
Example 3:

Input: grid = [[1,0,0],[1,1,0],[1,1,0]]
Output: -1


https://leetcode.com/problems/shortest-path-in-binary-matrix/
 */
public class Sol1091_ShortestPathInBinaryMatrix {
    // 8 directions
    int[] dirx = {-1,0,0,1,-1,-1,1,1};
    int[] diry = {0,1,-1,0,1,-1,1,-1};
    public int shortestPathBinaryMatrix(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        LinkedList<int[]> queue = new LinkedList<>();
        if (grid[0][0] != 0) return -1;
        queue.offer(new int[2]);
        int res = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] p = queue.poll(); // need to be with the for size loop
                int x = p[0];
                int y = p[1];
                if (x == m - 1 && y == n - 1) {
                    return res;
                }
                for (int j = 0; j< dirx.length; j++) {
                    int nx = x + dirx[j];
                    int ny = y + diry[j];
                    if (nx>=0 && nx < m && ny >=0 && ny< n && grid[nx][ny] == 0 && !visited[nx][ny]) {
                        queue.offer(new int[]{nx,ny});
                        visited[nx][ny] = true;
                    }
                }
            }
            res++;
        }
        return -1;
    }
}
