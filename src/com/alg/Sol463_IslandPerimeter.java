package com.alg;

/**
 * Created by HAU on 8/31/2017.
 */

/*
You are given a map in form of a two-dimensional integer grid where 1
        represents land and 0 represents water. Grid cells are connected
        horizontally/vertically (not diagonally). The grid is completely
        surrounded by water, and there is exactly one island
        (i.e., one or more connected land cells).
        The island doesn't have "lakes" (water inside that isn't
        connected to the water around the island).
        One cell is a square with side length 1.
        The grid is rectangular, width and height don't exceed 100.
        Determine the perimeter of the island.
*/
public class Sol463_IslandPerimeter {
    public static int islandPerimeter(int[][] grid){
        if (grid == null ||grid.length == 0 || grid[0].length == 0 )
            return 0;
        int res = 0;
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                if (grid[i][j] == 1){
                    res += 4;  // count border
                    if (i > 0 && grid[i-1][j] == 1) res -=2;  // count up
                    if (j > 0 && grid[i][j-1] == 1) res -=2; // count left
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {0,1,0,0},
                {1,1,1,0},
                {0,1,0,0},
                {1,1,0,0}
        };
        System.out.println(islandPerimeter(grid));
    }

    public int islandPerimeter2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n ; j++) {
                if (grid[i][j] == 1) {
                    return dfs(grid, i, j);
                }
            }
        }
        return 0;
    }

    private int dfs(int[][] grid, int x, int y) {
        if ( x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == 0) return 1; // find water at the neighbor, return 1
        if (grid[x][y] == -1) return 0;
        grid[x][y] = -1;
        int res = dfs(grid, x + 1, y) + dfs(grid, x - 1, y) + dfs(grid, x, y+1) + dfs(grid, x, y - 1);
        return res;
    }
}
