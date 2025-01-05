package com.alg;

import java.util.LinkedList;
import java.util.Queue;

/*
You are given an m x n grid where each cell can have one of three values:

    0 representing an empty cell,
    1 representing a fresh orange, or
    2 representing a rotten orange.

Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.



Example 1:

Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
Output: 4

Example 2:

Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.

Example 3:

Input: grid = [[0,2]]
Output: 0
Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.



Constraints:

    m == grid.length
    n == grid[i].length
    1 <= m, n <= 10
    grid[i][j] is 0, 1, or 2.


 */
public class Sol994_RottingOranges {
    //https://leetcode.com/problems/rotting-oranges/solutions/238681/java-clean-bfs-solution-with-comments/
    public int orangeRotting(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int count_fresh = 0;
        // put all rotten ones in the queue
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    count_fresh++;
                }
            }
        }
        //if count of fresh oranges is zero --> return 0
        if(count_fresh == 0) return 0;
        if (queue.size() == 0) return -1; // no rotten ones
        int count = 0;
        int[][] dirs = { { 0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        // bfs start from rotten ones
        while (!queue.isEmpty() /* && count_fresh > 0), same as line 86 check if count_fresh == 0*/) {
            count++;
            int size = queue.size(); // one level is one day
            for (int i = 0; i < size; i++) {
                int[] point = queue.poll();
                for (int[] dir : dirs) {
                    int newx = point[0] + dir[0];
                    int newy = point[1] + dir[1];
                    // donothing (continue) if it is out of bound or 2, or empty
                    if (newx < 0 || newy < 0 || newx >= rows || newy >= cols) continue;
                    if (grid[newx][newy] == 2 || grid[newx][newy] == 0) continue;
//                    if (grid[newx][newy] == 1) {
                    grid[newx][newy] = 2;
                    queue.offer(new int[]{newx, newy});
                    count_fresh--;
//                    }
                }
            }
            if (count_fresh == 0) break;
        }
        if (count_fresh == 0) {
            return count;
        }
        return -1;

    }
}
