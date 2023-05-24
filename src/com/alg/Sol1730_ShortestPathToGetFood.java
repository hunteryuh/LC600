package com.alg;

import sun.awt.image.ImageWatched;

import java.util.LinkedList;
import java.util.Queue;

/*
You are starving and you want to eat food as quickly as possible. You want to find the shortest path to arrive at any food cell.

You are given an m x n character matrix, grid, of these different types of cells:

    '*' is your location. There is exactly one '*' cell.
    '#' is a food cell. There may be multiple food cells.
    'O' is free space, and you can travel through these cells.
    'X' is an obstacle, and you cannot travel through these cells.

You can travel to any adjacent cell north, east, south, or west of your current location if there is not an obstacle.

Return the length of the shortest path for you to reach any food cell. If there is no path for you to reach food, return -1.



Example 1:

Input: grid = [["X","X","X","X","X","X"],["X","*","O","O","O","X"],["X","O","O","#","O","X"],["X","X","X","X","X","X"]]
Output: 3
Explanation: It takes 3 steps to reach the food.

Example 2:

Input: grid = [["X","X","X","X","X"],["X","*","X","O","X"],["X","O","X","#","X"],["X","X","X","X","X"]]
Output: -1
Explanation: It is not possible to reach the food.

Example 3:

Input: grid = [["X","X","X","X","X","X","X","X"],["X","*","O","X","O","#","O","X"],["X","O","O","X","O","O","X","X"],["X","O","O","O","O","#","O","X"],["X","X","X","X","X","X","X","X"]]
Output: 6
Explanation: There can be multiple food cells. It only takes 6 steps to reach the bottom food.



Constraints:

    m == grid.length
    n == grid[i].length
    1 <= m, n <= 200
    grid[row][col] is '*', 'X', 'O', or '#'.
    The grid contains exactly one '*'.


 */
public class Sol1730_ShortestPathToGetFood {
    public int getFood(char[][] grid) {
        int[][] dirs={{0,1},{1,0},{0,-1},{-1,0}};
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        int[] start = getStart(grid);
        q.offer(start);
        visited[start[0]][start[1]] = true;
        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                int x = cur[0];
                int y = cur[1];
                if (grid[x][y] == '#') {
                    return step;
                }
                for (int[] dir : dirs) {
                    int newx = x + dir[0];
                    int newy = y + dir[1];
                    if (newx < 0 || newy < 0 || newx >= m || newy >= n || grid[newx][newy] == 'X') continue;
                    if (visited[newx][newy]) continue;
                    visited[newx][newy] = true;
                    q.offer(new int[]{newx, newy});
                }
            }
            step++;
        }
        return -1;
    }

    private int[] getStart(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '*') {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
}
