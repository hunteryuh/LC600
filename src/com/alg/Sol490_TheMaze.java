package com.alg;

import sun.awt.image.ImageWatched;

import java.util.LinkedList;
import java.util.Queue;

/*
There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1).
The ball can go through the empty spaces by rolling up, down, left or right,


 **but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.**

Given the m x n maze, the ball's start position and the destination, where start = [startrow, startcol] and destination = [destinationrow, destinationcol], return true if the ball can stop at the destination, otherwise return false.

You may assume that the borders of the maze are all walls (see examples).



Example 1:

Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [4,4]
Output: true
Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.

Example 2:

Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [3,2]
Output: false
Explanation: There is no way for the ball to stop at the destination.
Notice that you can pass through the destination but you cannot stop there.

Example 3:

Input: maze = [[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]], start = [4,3], destination = [0,1]
Output: false

 */
public class Sol490_TheMaze {
    // https://www.jiuzhang.com/problem/the-maze/
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        int m = maze.length;
        int n = maze[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        boolean[][] visited = new boolean[m][n];
        visited[start[0]][start[1]] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == destination[0] && cur[1] == destination[1]) {
                return true;
            }
            for (int[] dir : dirs) {
                int newx = cur[0];
                int newy = cur[1];
//                int x = cur[0];
//                int y = cur[1];

//                if (newx < 0 || newy < 0 || newx >= m || newy >= n || maze[newx][newy] == 1 || visited[newx][newy]) {
//                    continue;
//                }
                // need to continue in one direction rolling until it hits a wall
                while (newx >= 0 && newy >=0 && newx < m && newy < n && maze[newx][newy] != 1) {
                    newx += dir[0];
                    newy += dir[1];
                }
                newx -= dir[0];
                newy -= dir[1];
                /* approach 2, put +dir[0] or +dir[1] in the while condition so that no need to minus it after the while loop */
                /*
                while (newx + dir[0] >= 0 && newy + dir[1] >= 0 && newx + dir[0] < m && newy + dir[1] < n
                        && maze[newx + dir[0]][newy + dir[1]] == 0) {
                    newx += dir[0];
                    newy += dir[1];
                }

                */

                if (!visited[newx][newy]) {
                    visited[newx][newy] = true;
                    queue.offer(new int[]{newx, newy});
                }
            }
        }
        return false;
    }
}