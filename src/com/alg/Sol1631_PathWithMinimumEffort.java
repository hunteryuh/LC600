package com.alg;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/*
You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.

A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.

Return the minimum effort required to travel from the top-left cell to the bottom-right cell.



Example 1:



Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
Output: 2
Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.
Example 2:



Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
Output: 1
Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better than route [1,3,5,3,5].
Example 3:


Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
Output: 0
Explanation: This route does not require any effort.

https://leetcode.com/problems/path-with-minimum-effort/
 */
public class Sol1631_PathWithMinimumEffort {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0 , 0, 1, -1};
    public int minimumEffortPath(int[][] heights) {
        int lo = 0;
        int hi = 1000000;
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (dfs(heights, mid)) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        if (dfs(heights, lo)) {
            return lo;
        }
        return hi;
    }

    private boolean dfs(int[][] heights, int effort) {
        int m = heights.length;
        int n = heights[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[2]);  //new int[]{0,0}
        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0];
            int y = curr[1];
            // return if at the bottom right
            if (x == m - 1 && y == n - 1) {
                return true;
            }
            for (int i = 0; i < dx.length; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx >=0 && nx < m && ny >= 0 && ny <n && Math.abs(heights[nx][ny] - heights[x][y]) <= effort  // need to include equal effort along the path
                    && !visited[nx][ny]) {
                    queue.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
        return false;
    }

    // Dijkstra
    // https://leetcode.com/problems/path-with-minimum-effort/discuss/1036518/Java-3-clean-codes%3A-Dijkstra's-algo-Union-Find-Binary-search
    // https://leetcode.com/problems/path-with-minimum-effort/discuss/909002/JavaPython-3-3-codes%3A-Binary-Search-Bellman-Ford-and-Dijkstra-w-brief-explanation-and-analysis.
    private static final int[][] dir = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
    public int minEffortPath(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        int[][] efforts = new int[m][n];
        for (int[] e: efforts) {
            Arrays.fill(e, Integer.MAX_VALUE);
        }
        efforts[0][0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0,0,0});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int effort = cur[0];
            int x = cur[1];
            int y = cur[2];
            if ( x == m - 1 && y == n - 1) {
                return effort;
            }
            for (int k = 0; k < 4; k++) {
                int r = x + dir[k][0];
                int c = y + dir[k][1];
                if (r >= 0 && r < m && c >=0 && c < n ) {
                    int nextEffort = Math.max(Math.abs(heights[r][c] - heights[x][y]), effort); // nextEffort on the same path
                    if (nextEffort < efforts[r][c]) {
                        efforts[r][c] = nextEffort;
                        pq.add(new int[]{nextEffort, r, c});  // this minHeap will poll the smallest effort when (x,y)==(m-1,n-1)
                    }
                }
            }
        }
        return -1;

    }

    // dijkstra 稀疏图，只有4个方向，所有结果都丢进queue, 不做判断
    public int minEffortPath2(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        boolean[][] visited = new boolean[m][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[2] - b[2]));
        pq.offer(new int[]{0, 0, 0});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            visited[cur[0]][cur[1]] = true;
            if (cur[0] == m - 1 && cur[1] == n - 1) {
                return cur[2];
            }
            for (int[] d : dir) {
                int x = cur[0] + d[0];
                int y = cur[1] + d[1];
                if (x < 0 || x >= m || y < 0 || y >= n) continue;
                if (visited[x][y]) continue;
                int diff = Math.abs(heights[x][y] - heights[cur[0]][cur[1]]);
                pq.offer(new int[]{x, y, Math.max(diff, cur[2])});
            }
        }
        return -1;
    }

    //

}
