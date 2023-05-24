package com.alg;

import java.util.PriorityQueue;

/*
You are given a m x n matrix grid consisting of non-negative integers where grid[row][col] represents the minimum time required to be able to visit the cell (row, col), which means you can visit the cell (row, col) only when the time you visit it is greater than or equal to grid[row][col].

You are standing in the top-left cell of the matrix in the 0th second, and you must move to any adjacent cell in the four directions: up, down, left, and right. Each move you make takes 1 second.

Return the minimum time required in which you can visit the bottom-right cell of the matrix. If you cannot visit the bottom-right cell, then return -1.



Example 1:

Input: grid = [[0,1,3,2],[5,1,2,5],[4,3,8,6]]
Output: 7
Explanation: One of the paths that we can take is the following:
- at t = 0, we are on the cell (0,0).
- at t = 1, we move to the cell (0,1). It is possible because grid[0][1] <= 1.
- at t = 2, we move to the cell (1,1). It is possible because grid[1][1] <= 2.
- at t = 3, we move to the cell (1,2). It is possible because grid[1][2] <= 3.
- at t = 4, we move to the cell (1,1). It is possible because grid[1][1] <= 4.
- at t = 5, we move to the cell (1,2). It is possible because grid[1][2] <= 5.
- at t = 6, we move to the cell (1,3). It is possible because grid[1][3] <= 6.
- at t = 7, we move to the cell (2,3). It is possible because grid[1][3] <= 7.
The final time is 7. It can be shown that it is the minimum time possible.

Example 2:

Input: grid = [[0,2,4],[3,2,1],[1,0,4]]
Output: -1
Explanation: There is no path from the top left to the bottom-right cell.



Constraints:

    m == grid.length
    n == grid[i].length
    2 <= m, n <= 1000
    4 <= m * n <= 105
    0 <= grid[i][j] <= 105
    grid[0][0] == 0

https://leetcode.com/problems/minimum-time-to-visit-a-cell-in-a-grid/solutions/3230800/c-java-python-ping-pong-dijkstra/
 */
public class Sol2577_MinimumTimeToVisitACellInAGrid {
    private static final int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0, -1}};
    public int minimumTime(int[][] grid) {
        if (grid[0][1] > 1 && grid[1][0] > 1) return -1;
        int m = grid.length;
        int n = grid[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[2] - b[2]);
        pq.offer(new int[]{0, 0, 0}); //{row, col, time}
        boolean[][] visited = new boolean[m][n];
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int row = cur[0];
            int col = cur[1];
            int time = cur[2];
            if (row == m - 1 && col == n - 1) return time;
            if (visited[row][col]) continue;
            visited[row][col] = true;
            for (int[] dir : dirs) {
                int x = row + dir[0];
                int y = col + dir[1];
                if (x < 0 || x >= m || y < 0 || y >= n) continue;
                if (grid[x][y] <= time + 1) {
                    pq.offer(new int[]{x, y, time + 1});
                } else {
                    int diff = grid[x][y] - time;
                    if (diff % 2 == 1) { // need to go back and return to the [row,col], then arrive
                        pq.offer(new int[]{ x, y, grid[x][y]});
                    } else {
                        pq.offer(new int[]{x, y, grid[x][y] + 1});
                    }
                }
            }
        }
        return -1;
    }
}
