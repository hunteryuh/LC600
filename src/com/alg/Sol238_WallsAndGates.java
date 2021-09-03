package com.alg;
/*
You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

For example, given the 2D grid:
INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
After running your function, the 2D grid should be:
  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4

 */
public class Sol238_WallsAndGates {
    public void wallsAndGates(int[][] rooms) {
        if (rooms.length == 0 || rooms[0].length == 0) return;
        int m = rooms.length;
        int n = rooms[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    dfs(rooms, i, j, 0);
                }
            }
        }
    }
    private void dfs(int[][] rooms, int x, int y, int dis) {
        if (x < 0 || y < 0 || x >= rooms.length || y >= rooms[0].length ||
                rooms[x][y] == -1) {
            return;
        }
        if (rooms[x][y] > dis || dis == 0) {
            rooms[x][y] = dis;
            dfs(rooms, x + 1, y, dis + 1);
            dfs(rooms, x - 1, y, dis + 1);
            dfs(rooms, x, y + 1, dis + 1);
            dfs(rooms, x, y - 1, dis + 1);
        }
    }
}
