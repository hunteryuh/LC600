package com.alg;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

The area of an island is the number of cells with a value 1 in the island.

Return the maximum area of an island in grid. If there is no island, return 0.



Example 1:

Input: grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
Output: 6
Explanation: The answer is not 11, because the island must be connected 4-directionally.

Example 2:

Input: grid = [[0,0,0,0,0,0,0,0]]
Output: 0



Constraints:

    m == grid.length
    n == grid[i].length
    1 <= m, n <= 50
    grid[i][j] is either 0 or 1.

doordash

https://leetcode.com/problems/max-area-of-island/
 */
public class Sol695_MaxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        int m = grid.length;
        int n = grid[0].length;
        int[] area = new int[]{0};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    area[0] = 0;
                    dfs(grid, i, j, area);
                    max = Math.max(max, area[0]);
                }
            }
        }
        return max;
    }
    private void dfs(int[][] grid, int x, int y, int[] area) {
        if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1) {
            area[0]++;
            grid[x][y] = 0;
            dfs(grid, x + 1, y, area);
            dfs(grid, x - 1, y, area);
            dfs(grid, x, y + 1, area);
            dfs(grid, x, y - 1, area);
        }
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{1}};
        int m = grid.length;
        int n = grid[0].length;
//        System.out.println(m);
//        System.out.println(n);
        Sol695_MaxAreaOfIsland ss = new Sol695_MaxAreaOfIsland();
//        System.out.println(ss.maxAreaOfIsland(grid));

        int[][] grid2 = {
                {1, 3, 5, 5, 5},
                {1, 5, 5, 1, 5},
                {1, 1, 5, 5, 5}
        };
        int[][] grid3 = {
                {0, 3, 1, 1, 1},
                {0, 1, 1, 1, 1},
                {0, 0, 1, 1, 1}
        };

//        System.out.println(ss.maxAreaOfIsland_dfs2(grid3));
//        System.out.println(ss.maxAreaOfIsland2(grid2));
//        System.out.println(ss.maxAreaWithSameNumber(grid3));
        System.out.println(ss.longestPath(grid3));
    }

    public int maxAreaWithSameNumber(int[][] arr) {
        int maxArea = 1;
        int n = arr.length, m = arr[0].length;
        boolean[][] visited = new boolean[arr.length][arr[0].length];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                if (!visited[row][col]) {
//                    System.out.println("time ");
                    int x = dfs_samenumber(arr, visited, row, col, arr[row][col]);
//                    System.out.println(x);
                    maxArea = Math.max(maxArea, x);
                }
            }
        }
        System.out.println(Arrays.deepToString(visited));

        return maxArea;
    }

    public int dfs_samenumber(int[][] arr, boolean[][] visited, int row, int col, int target) {

        int area = 1;

        visited[row][col] = true;

        for(int[] d : dirs) {
            int newX = row + d[0];
            int newY = col + d[1];

            if (newX < 0 || newY < 0 || newX >= arr.length || newY >= arr[0].length ||
                      /*wrong visited[newX][newY]  ||*/
                arr[newX][newY] != target) continue;  // only skip the non-target one (not the non-visited)
            area += dfs(arr, visited, newX, newY, target);
        }

        return area;
    }

    // dfs 2

    public int maxAreaOfIsland2(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;

        int maxArea = 0;

        // iterate through every grid in the current array
        for (int r = 0; r < rows; ++r) {
            for (int c = 0; c < cols; ++c) {
                // in case the current grid is a land and is not visited yet
                if (grid[r][c] == 1) {
                    maxArea = Math.max(maxArea, dfs2(grid, r, c));
//                    maxArea = Math.max(maxArea, bfs(grid, r, c));
                }
            }
        }

        return maxArea;
    }
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private int dfs2(int[][] grid, int r, int c) {
        grid[r][c] = 2;
        int area = 1;

        for (int[] dir: dirs) {
            int x = r + dir[0];
            int y = c + dir[1];
            if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] != 1) {
                continue;
            }
            area += dfs2(grid, x, y);
        }
        return area;
    }

    public int maxAreaOfIsland_dfs2(int[][] grid) {
        int max_area = 0;
        for(int i = 0; i < grid.length; i++)
            for(int j = 0; j < grid[0].length; j++)
                if(grid[i][j] == 1) max_area = Math.max(max_area, AreaOfIsland(grid, i, j));
        return max_area;
    }

    public int AreaOfIsland(int[][] grid, int i, int j){
        if( i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == 1){
            grid[i][j] = 0;
            return 1 + AreaOfIsland(grid, i+1, j) + AreaOfIsland(grid, i-1, j) + AreaOfIsland(grid, i, j-1) + AreaOfIsland(grid, i, j+1);
        }
        return 0;
    }

    // bfs
    // Do with BFS which does not have stack overflow issue for infinite size.
    // I did with DFS and called for second screen as I explained the limitation with DFS.
    private int bfs(int[][] grid, int i, int j) {
        int m = grid.length, n = grid[0].length;
        if (grid[i][j] == 0) return 0;
        grid[i][j] = 0; // need to update to 0 as visited

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{i, j});
        int res = 1;
        while (!q.isEmpty()) {
            int[] pos = q.poll();
            for (int[] dir : dirs) {
                int x = dir[0] + pos[0];
                int y = dir[1] + pos[1];
                if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 0) {
                    continue;
                }
                grid[x][y] = 0;
                res++;
                q.offer(new int[]{x, y});
            }
        }
        return res;
    }

    // longest path dfs
    public int longestPath(int[][] arr) {
        int longestPath = 1;
        int n = arr.length, m = arr[0].length;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                boolean[][] visited = new boolean[arr.length][arr[0].length];
                longestPath = Math.max(longestPath, dfs(arr, visited, row, col, arr[row][col]));
            }
        }
        return longestPath;
    }

    public int dfs(int[][] arr, boolean[][] visited, int row, int col, int target) {
        int longestPath = 1;

        visited[row][col] = true;

        for(int[] d : dirs) {
            int newX = row + d[0];
            int newY = col + d[1];

            if (newX < 0 || newY < 0 || newX >= arr.length || newY >= arr[0].length ||
                    visited[newX][newY] || arr[newX][newY] != target ) continue;

            // need to calculate the length at each direction, so inside the for loop
            int path = 1 + dfs(arr, visited, newX, newY, target);
            longestPath = Math.max(longestPath, path);
        }

        return longestPath;

    }
}
