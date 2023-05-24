package com.alg;

import java.util.LinkedList;
import java.util.Queue;

/*
You are given an m x n matrix maze (0-indexed) with empty cells (represented as '.') and walls (represented as '+'). You are also given the entrance of the maze, where entrance = [entrancerow, entrancecol] denotes the row and column of the cell you are initially standing at.

In one step, you can move one cell up, down, left, or right. You cannot step into a cell with a wall, and you cannot step outside the maze. Your goal is to find the nearest exit from the entrance. An exit is defined as an empty cell that is at the border of the maze. The entrance does not count as an exit.

Return the number of steps in the shortest path from the entrance to the nearest exit, or -1 if no such path exists.



Example 1:


Input: maze = [["+","+",".","+"],[".",".",".","+"],["+","+","+","."]], entrance = [1,2]
Output: 1
Explanation: There are 3 exits in this maze at [1,0], [0,2], and [2,3].
Initially, you are at the entrance cell [1,2].
- You can reach [1,0] by moving 2 steps left.
- You can reach [0,2] by moving 1 step up.
It is impossible to reach [2,3] from the entrance.
Thus, the nearest exit is [0,2], which is 1 step away.
Example 2:


Input: maze = [["+","+","+"],[".",".","."],["+","+","+"]], entrance = [1,0]
Output: 2
Explanation: There is 1 exit in this maze at [1,2].
[1,0] does not count as an exit since it is the entrance cell.
Initially, you are at the entrance cell [1,0].
- You can reach [1,2] by moving 2 steps right.
Thus, the nearest exit is [1,2], which is 2 steps away.
Example 3:


Input: maze = [[".","+"]], entrance = [0,0]
Output: -1
Explanation: There are no exits in this maze.


Constraints:

maze.length == m
maze[i].length == n
1 <= m, n <= 100
maze[i][j] is either '.' or '+'.
entrance.length == 2
0 <= entrancerow < m
0 <= entrancecol < n
entrance will always be an empty cell.
 */
public class Sol1926_NearestExitFromEntranceInMaze {
    public int nearestExit(char[][] maze, int[] entrance) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(entrance);
        int m = maze.length;
        int n = maze[0].length;
        // boolean[][] visited = new boolean[m][n];
        int[][] dirs = { {-1,0}, {1,0}, {0,1}, {0,-1}};
        // visited[entrance[0]][entrance[1]] = true;
        maze[entrance[0]][entrance[1]] = '+';
        int dis = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            dis++;
            for (int level = 0 ; level < size ; level++) {

                int[] cur = queue.poll();
                int x = cur[0];
                int y = cur[1];
                for (int i = 0; i < dirs.length; i++) {
                    int newx = x + dirs[i][0];
                    int newy = y + dirs[i][1];
                    if (newx >= 0 && newx < m && newy >= 0 && newy < n
                            && maze[newx][newy] == '.') {
                        if (isExit(newx, newy, maze)) {
                            return dis;
                        }
                        // visited[x][y] = true;
                        maze[newx][newy] = '+';  // to avoid using another visited array
                        queue.offer(new int[]{newx, newy});
                    }

                }
            }
        }
        return -1;
    }
    private boolean isExit(int x, int y, char[][] maze) {
        if (x == 0 || y ==0 || x == maze.length - 1 ||y == maze[0].length - 1) {
            return true;
        }
        return false;
    }
}
