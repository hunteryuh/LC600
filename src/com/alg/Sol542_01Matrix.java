package com.alg;

import java.util.LinkedList;
import java.util.Queue;

/*
Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.



Example 1:


Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
Output: [[0,0,0],[0,1,0],[0,0,0]]
Example 2:


Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
Output: [[0,0,0],[0,1,0],[1,2,1]]


Constraints:

m == mat.length
n == mat[i].length
1 <= m, n <= 104
1 <= m * n <= 104
mat[i][j] is either 0 or 1.
There is at least one 0 in mat.
 */
public class Sol542_01Matrix {
    public int[][] updateMatrix(int[][] mat) {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        int m = mat.length;
        int n = mat[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) queue.offer(new int[]{i, j});
                else mat[i][j] = Integer.MAX_VALUE;
            }
        }
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] dir: dirs) {
                int nextx = cur[0] + dir[0];
                int nexty = cur[1] + dir[1];

                if (nextx < 0 || nextx >= m || nexty < 0 || nexty >= n || mat[nextx][nexty] != Integer.MAX_VALUE) {
                    // 如果下一步要到的NEXTX, NEXTY已经有更小的值了，就不需要再跳过去了
//                if (nextx < 0 || nextx >= m || nexty < 0 || nexty >= n || mat[nextx][nexty] < mat[cur[0]][cur[1]] + 1) {
                    continue;
                }
                queue.offer(new int[]{nextx, nexty});
                mat[nextx][nexty] = mat[cur[0]][cur[1]] + 1;
            }
        }
        return mat;
    }
}
