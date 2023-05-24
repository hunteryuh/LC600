package com.alg;

import sun.awt.image.ImageWatched;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Created by HAU on 6/11/2017.
 */
/*Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
        An island is surrounded by water and is formed by connecting adjacent lands
        horizontally or vertically.
        You may assume all four edges of the grid are all surrounded by water.*/
public class Sol200_NumberOfIslands {
    /*


    Time complexity : O(M×N) where M is the number of rows and N is the number of columns.

    Space complexity : worst case O(M×N) in case that the grid map is filled with lands where DFS goes by M×NM \times NM×N deep.

     */
    public static int numIslands(char[][] grid) {
        int count = 0;
        // iterate over the entire given grid
        for (int i = 0; i < grid.length; i++){
            for (int j = 0 ; j < grid[0].length; j++){
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
    /*


    Time complexity : O(M×N) where M is the number of rows andNN is the number of columns.

    Space complexity : O(min(M,N)) because in worst case where the grid is filled with lands,
    the size of queue can grow up to min(M,N).  See https://imgur.com/gallery/M58OKvB

     */

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

    // interviewed by tencent 2/14/2022
    public int getNumOfIslands(char[][] grid) {
        int count = 0;
        int m = grid.length;
        int n = grid[0].length;

        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    if (visited[i][j]) continue;
                    dfs(grid, i, j, visited);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int x, int y, boolean[][] visited) {
        if (x >= 0 && x < grid.length && y >=0 && y < grid[0].length && grid[x][y] == '1' && !visited[x][y]) {
            visited[x][y] = true;

            dfs(grid, x + 1, y, visited);
            dfs(grid, x - 1, y, visited);
            dfs(grid, x, y + 1, visited);
            dfs(grid, x, y - 1, visited);

        }

    }
    // bfs
    public int numIslands_bfs(char[][] grid) {
        int res = 0;
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    bfs(grid, i , j);
                    res++;
                }
            }
        }
        return res;
    }

    private void bfs(char[][] grid, int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        int[][] dirs = {{0,1}, {1,0}, {0, -1}, {-1, 0}};
        grid[x][y] = '0';
        q.offer(new int[]{x, y});
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int px = cur[0];
            int py = cur[1];
            for (int i = 0; i < dirs.length; i++) {
                int newx = px + dirs[i][0];
                int newy = py + dirs[i][1];
                if (newx < 0 || newx >= grid.length || newy < 0 || newy >=grid[0].length) continue;
                if (grid[newx][newy] == '0') continue;
                grid[newx][newy] = '0';
                q.offer(new int[]{newx, newy});
            }
        }
    }

    // union find

    class DSU{
        int[] parent;
        DSU(int n ) {
            parent = new int[n];
            for (int i = 0; i < n ; i++) parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
        void union(int u, int v) {
            parent[find(u)] = find(v);
        }
    }

    // https://leetcode.com/problems/number-of-islands/solutions/732832/easy-java-union-find-solution/
    public int numbOfIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        DSU dsu = new DSU(m * n);
        int zeros = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0') {
                    zeros++;
                    continue;
                }
                if (i > 0 && grid[i-1][j] == '1') {
                    dsu.union(n*i + j, n*(i-1) + j);
                }
                if (j > 0 && grid[i][j-1] == '1') {
                    dsu.union(n * i + j, n * i + j  - 1);
                }
            }
        }
        Set<Integer> seen = new HashSet<>();
        for (int i = 0; i < m * n; i++) {
            seen.add(dsu.find(i));
        }
        return seen.size() - zeros;  // every zero has its own union
    }
}
