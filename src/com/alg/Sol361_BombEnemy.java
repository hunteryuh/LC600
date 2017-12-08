package com.alg;

/**
 * Created by HAU on 12/7/2017.
 */
/*Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill using one bomb.
The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.
Note that you can only put the bomb at an empty cell.

Example:

For the given grid

0 E 0 0
E 0 W E
0 E 0 0

return 3. (Placing a bomb at (1,1) kills 3 enemies)
*/
public class Sol361_BombEnemy {
    public static int maxKilledEnemies(char[][] grid) {
        /*Walk through the matrix. At the start of each non-wall-streak (row-wise or column-wise),
        count the number of hits in that streak and remember it. O(mn) time, O(n) space.*/
        if(grid == null || grid.length == 0 ||  grid[0].length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        int rowhits = 0;
        int[] colhits = new int[n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n;j++){
                if( j==0 || grid[i][j-1] == 'W'){
                    rowhits = 0;
                    for(int k = j; k<n && grid[i][k]!= 'W';k++){
                        rowhits += (grid[i][k] == 'E')? 1:0;
                    }
                }
                if(i==0 || grid[i-1][j] == 'W'){
                    colhits[j] = 0;
                    for(int k = i; k<m && grid[k][j] != 'W';k++){
                        colhits[j] += (grid[k][j] == 'E')? 1: 0;
                    }
                }
                if(grid[i][j] == '0'){
                    res = Math.max(res,rowhits + colhits[j]);
                }
            }
        }
        return res;

    }

    public static void main(String[] args) {
        char[][] grid = {
                {'0','E','0','0'},
                {'E','0','W','E'},
                {'0','E','0','0'}
        };
        System.out.println(maxKilledEnemies(grid));
    }
}
