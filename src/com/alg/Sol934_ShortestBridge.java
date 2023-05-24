package com.alg;

import java.util.LinkedList;
import java.util.Queue;

/*
934. Shortest Bridge
Medium
Share
You are given an n x n binary matrix grid where 1 represents land and 0 represents water.

An island is a 4-directionally connected group of 1's not connected to any other 1's. There are exactly two islands in grid.

You may change 0's to 1's to connect the two islands to form one island.

Return the smallest number of 0's you must flip to connect the two islands.



Example 1:

Input: grid = [[0,1],[1,0]]
Output: 1
Example 2:

Input: grid = [[0,1,0],[0,0,0],[0,0,1]]
Output: 2
Example 3:

Input: grid = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
Output: 1


Constraints:

n == grid.length == grid[i].length
2 <= n <= 100
grid[i][j] is either 0 or 1.
There are exactly two islands in grid.
 */

    /**
     * Use DFS + BFS to solve this WONDERFUL problem!
     * Step 1: use DFS to mark the first island to another number
     * Step 2: start from the first island, doing BFS level order traversal to find number of bridges (levels)
     * until reach the second island
     * */
public class Sol934_ShortestBridge {

    private int[][] dirs = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    private Queue<int[]> queue = new LinkedList<>();	// store visited cells with x and y in an array
    private int row, col;

    public int shortestBridge(int[][] grid) {
        row = grid.length;
        col = grid[0].length;
        boolean[][] visited = new boolean[row][col];
        boolean found = false;

        // 1. dfs to find an island, mark its cells in visited
        for (int i = 0; i < row && !found; i++) {
            // WARNING: MUST use a boolean variable to check if we already marked the first island. Otherwise,
            // we will only break from the inner loop, but will not jump out the outer loop
            if (found) {
                break;
            }
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, visited, i, j);
                    found = true;
                    break;
                }
            }
        }
        // 2. bfs to expand this island to reach another island
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] cell = queue.poll();  // 第一层是first island所有点
                for (int[] d : dirs) {
                    int x = cell[0] + d[0];
                    int y = cell[1] + d[1];
                    if (x >= 0 && y >= 0 && x < row && y < col && !visited[x][y]) {	// Not in current island
                        if (grid[x][y] == 1) {
                            return step;
                        }
                        queue.offer(new int[] { x, y });
                        visited[x][y] = true;
                    }
                }
            }
            step++; //一层结束
        }
        return -1;
    }

    // 把第一岛屿都标记为Visited,并且放进Queue
    private void dfs(int[][] grid, boolean[][] visited, int x, int y) {
        if (x < 0 || y < 0 || x >= row || y >= col || visited[x][y] || grid[x][y] == 0) {
            return;
        }
        visited[x][y] = true;
        queue.offer(new int[] { x, y });
        for (int[] d : dirs) {
            dfs(grid, visited, x + d[0], y + d[1]);
        }
    }
}
