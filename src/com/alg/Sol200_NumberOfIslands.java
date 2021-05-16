package com.alg;

import sun.awt.image.ImageWatched;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by HAU on 6/11/2017.
 */
/*Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
        An island is surrounded by water and is formed by connecting adjacent lands
        horizontally or vertically.
        You may assume all four edges of the grid are all surrounded by water.*/
public class Sol200_NumberOfIslands {
    public static int numIslands(char[][] grid) {
        int count = 0;
        // iterate over the entire given grid
        for (int i = 0; i < grid.length; i++){
            for ( int j = 0 ; j < grid[0].length; j++){
                if (grid[i][j] == '1'){
                    dfsearch(grid,i,j);
                    count++;
                }
            }
        }
        return count;
    }
    private static void dfsearch(char[][] grid, int i, int j){
        if (i >= 0 && j >= 0 && i < grid.length && j < grid[0].length
                && grid[i][j] == '1'){ //&& grid[i][j] == '1'
            grid[i][j] = '0'; // mark the site as visited
            // check all adjacent sites
            dfsearch(grid, i + 1, j);
            dfsearch(grid, i - 1, j);
            dfsearch(grid, i, j + 1);
            dfsearch(grid, i, j - 1);
        }
    }

    // method2  bfs  https://www.jiuzhang.com/problem/number-of-islands/
    // https://www.lintcode.com/problem/number-of-islands/

    /*
    Description
Given a boolean 2D matrix, 0 is represented as the sea, 1 is represented as the island. If two 1 is adjacent, we consider them in the same island. We only consider up/down/left/right adjacent.

Find the number of islands.

Example
Example 1:

Input:
[
  [1,1,0,0,0],
  [0,1,0,0,1],
  [0,0,0,1,1],
  [0,0,0,0,0],
  [0,0,0,0,1]
]
Output:
3
Example 2:

Input:
[
  [1,1]
]
Output:
1
     */
    static class Position {
        int x;
        int y;
        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static int numOfIslands(boolean[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int result = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j]) {
                    searchBFS(grid, i, j);
                    result++;
                }
            }
        }
        return result;
    }

    private static void searchBFS(boolean[][] grid, int x, int y) {
        int[] dirx = {1, -1, 0, 0};
        int[] diry = {0, 0, 1, -1};

        Queue<Position> queue = new LinkedList<>();
        queue.offer(new Position(x, y));
        grid[x][y] = false;  // mark starting point as visited

        while (!queue.isEmpty()) {
            Position p = queue.poll();
            for (int i = 0; i < dirx.length; i++) {
                int newx = p.x + dirx[i];
                int newy = p.y + diry[i];
                Position neighbor = new Position(newx, newy);
                if (!isInValidRange(neighbor, grid)) {
                    continue;
                }

                if (grid[newx][newy]) {
                    grid[newx][newy] = false;
                    queue.offer(new Position(newx, newy));
                }
            }
        }
    }

    private static boolean isInValidRange(Position neighbor, boolean[][] grid) {
        if (neighbor.x < 0 || neighbor.x >= grid.length) {
            return false;
        }

        if (neighbor.y < 0 || neighbor.y >= grid[0].length) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        char M[][]=  new char[][] {
                {'1', '1', '0', '0', '0'},
                {'0', '1', '0', '0', '1'},
                {'1', '0', '0', '1', '1'},
                {'0', '0', '0', '0', '0'},
                {'1', '0', '1', '0', '1'}
        };
        System.out.println(numIslands(M)); //6
    }
}
