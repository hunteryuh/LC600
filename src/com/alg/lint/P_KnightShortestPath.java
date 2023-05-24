package com.alg.lint;

import java.util.LinkedList;
import java.util.Queue;

/*
描述
Given a knight in a chessboard (a binary matrix with 0 as empty and 1 as barrier) with a source position, find the shortest path to a destination position, return the length of the route.
Return -1 if destination cannot be reached.

source and destination must be empty.
Knight can not enter the barrier.
Path length refers to the number of steps the knight takes.

样例
Example 1:

Input:
[[0,0,0],
 [0,0,0],
 [0,0,0]]
source = [2, 0] destination = [2, 2]
Output: 2
Explanation:
[2,0]->[0,1]->[2,2]
Example 2:

Input:
[[0,1,0],
 [0,0,1],
 [0,0,0]]
source = [2, 0] destination = [2, 2]
Output:-1
说明
If the knight is at (x, y), he can get to the following positions in one step:

(x + 1, y + 2)
(x + 1, y - 2)
(x - 1, y + 2)
(x - 1, y - 2)
(x + 2, y + 1)
(x + 2, y - 1)
(x - 2, y + 1)
(x - 2, y - 1)
 */
public class P_KnightShortestPath {
    /**
     * Definition for a point.
     */
    class Point {
        int x;
        int y;

        Point() {
            x = 0;
            y = 0;
        }

        Point(int a, int b) {
            x = a;
            y = b;
        }
    }
    public static final int[] dx = {1, 1, -1, -1, 2, 2, -2, -2};
    public static final int[] dy = {2, -2, 2, -2, 1, -1, 1, -1};
    /**
     * @param grid: a chessboard included 0 (false) and 1 (true)
     * @param source: a point
     * @param destination: a point
     * @return: the shortest path
     */

    public int shortestPath(boolean[][] grid, Point source, Point destination) {
        if (grid == null || grid.length == 0
                || grid[0] == null || grid[0].length == 0) {
            return -1;
        }

        Queue<Point> queue = new LinkedList<>();
        queue.offer(source);

        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point p = queue.poll();
                if (p.x == destination.x && p.y == destination.y) {
                    return steps;
                }

                for (int j = 0; j < dx.length; j++) {
                    Point newp = new Point(p.x + dx[j], p.y + dy[j]);
                    if (isValidNewP(newp, grid)) {
                        queue.offer(newp);
                        grid[newp.x][newp.y] = true;  // mark the new p not accessible any more
                    }
                }
            }
            steps++;  // steps after each layer
        }
        return -1;
    }

    private boolean isValidNewP(Point p, boolean[][] grid) {
        if (p.x < 0 || p.x >= grid.length) {
            return false;
        }

        if (p.y < 0 || p.y >= grid[0].length) {
            return false;
        }

        return !grid[p.x][p.y];
    }
}
