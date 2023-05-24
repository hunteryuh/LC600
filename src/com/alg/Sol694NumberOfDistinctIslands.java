package com.alg;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by HAU on 12/2/2017.
 */
/*Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally
(horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Count the number of distinct islands. An island is considered to be the same as another
if and only if one island can be translated (and not rotated or reflected) to equal the other.*/
public class Sol694NumberOfDistinctIslands {

    static int[][] dirs= {{1,0},{-1,0},{0,1},{0,-1}};
    public  static int numDistinctIslands(int[][] grid) {
        //int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        Set<String> set = new HashSet<>();
        int res = 0;

        for(int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length ; j++){
                if(grid[i][j]  == 1){
                    StringBuilder sb = new StringBuilder();
                    dfshelper(grid,i,j,0,0,sb);
                    String s = sb.toString();
                    if(!set.contains(s)){
                        res++;
                        set.add(s);
                    }
                }
            }
        }
        return res;
    }

    private  static void dfshelper(int[][] grid, int i, int j, int xp, int yp, StringBuilder sb) {

        grid[i][j] = 0;
        sb.append(xp + "" + yp);
        for(int[] dir: dirs){
            int x = i + dir[0];
            int y = j + dir[1]; // four directions
            //if(x<0 || y<0 || x>=grid.length || y>=grid[0].length || grid[x][y]==0) continue;
            if ( x >=0 && y >=0 && x < grid.length && y < grid[0].length && grid[x][y] == 1){
                dfshelper(grid,x,y,xp + dir[0], yp + dir[1],sb);
            }
        }
    }
    public static void main(String[] args) {
        int[][] grid = {
                {0, 1, 0, 1},
                {1, 1, 0, 1},
                {0, 0, 0, 1},
                {0, 1, 0, 0},
                {1, 1, 0, 1}
        };
        //System.out.println(numDistinctIslands(grid)); //2
        //System.out.println(numDistinctIslands2(grid)); //2
        int[][] grid2 = {
                {0, 1, 0, 1},
                {1, 1, 0, 0},
                {0, 0, 0, 1},
                {0, 1, 0, 0},
                {1, 1, 0, 1}
        };
        //System.out.println(numDistinctIslands(grid2));
        //System.out.println(numDistinctIslands2(grid2));
        int[][] grid3 = {
                {1, 1, 0, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 0, 0}
        };
        System.out.println(numDistinctIslands2(grid3));
        // set 0: oRDbRbbb  set 1: oRDRbbbb
        //System.out.println(numDistinctIslands(grid3));
    }
    // method 2 using string directly (not num strings)
    public  static int numDistinctIslands2(int[][] grid) {

        Set<String> set = new HashSet<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();
                    dfs(grid, i, j, "o", sb);
                    //grid[i][j] = 0;
                    set.add(sb.toString());
                }
            }
        }
        return set.size();
    }
    private static void dfs(int[][] grid, int i, int j, String dir, StringBuilder sb) {
        if (i >=0 && j >=0 && i < grid.length && j < grid[0].length && grid[i][j] == 1){
            sb.append(dir);
            grid[i][j] = 0;
            dfs(grid,i+1,j,"D",sb);
            dfs(grid,i-1,j,"U",sb);
            dfs(grid,i,j+1,"R",sb);
            dfs(grid,i,j-1,"L",sb);
            sb.append("b"); //  "back" string
        }
    }
}
