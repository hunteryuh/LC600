package com.alg;

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
