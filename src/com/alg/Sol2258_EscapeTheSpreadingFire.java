package com.alg;

import java.util.*;

/*
You are given a 0-indexed 2D integer array grid of size m x n which represents a field. Each cell has one of three values:

    0 represents grass,
    1 represents fire,
    2 represents a wall that you and fire cannot pass through.

You are situated in the top-left cell, (0, 0), and you want to travel to the safehouse at the bottom-right cell, (m - 1, n - 1). Every minute, you may move to an adjacent grass cell. After your move, every fire cell will spread to all adjacent cells that are not walls.

Return the maximum number of minutes that you can stay in your initial position before moving while still safely reaching the safehouse. If this is impossible, return -1. If you can always reach the safehouse regardless of the minutes stayed, return 109.

Note that even if the fire spreads to the safehouse immediately after you have reached it, it will be counted as safely reaching the safehouse.

A cell is adjacent to another cell if the former is directly north, east, south, or west of the latter (i.e., their sides are touching).



Example 1:

Input: grid = [[0,2,0,0,0,0,0],[0,0,0,2,2,1,0],[0,2,0,0,1,2,0],[0,0,2,2,2,0,2],[0,0,0,0,0,0,0]]
Output: 3
Explanation: The figure above shows the scenario where you stay in the initial position for 3 minutes.
You will still be able to safely reach the safehouse.
Staying for more than 3 minutes will not allow you to safely reach the safehouse.

Example 2:

Input: grid = [[0,0,0,0],[0,1,2,0],[0,2,0,0]]
Output: -1
Explanation: The figure above shows the scenario where you immediately move towards the safehouse.
Fire will spread to any cell you move towards and it is impossible to safely reach the safehouse.
Thus, -1 is returned.

Example 3:

Input: grid = [[0,0,0],[2,2,0],[1,2,0]]
Output: 1000000000
Explanation: The figure above shows the initial grid.
Notice that the fire is contained by walls and you will always be able to safely reach the safehouse.
Thus, 109 is returned.



Constraints:

    m == grid.length
    n == grid[i].length
    2 <= m, n <= 300
    4 <= m * n <= 2 * 104
    grid[i][j] is either 0, 1, or 2.
    grid[0][0] == grid[m - 1][n - 1] == 0


 */
public class Sol2258_EscapeTheSpreadingFire {
    // https://leetcode.com/problems/escape-the-spreading-fire/solutions/1994962/java-binary-search-bfs-with-explanation-beats-100/
    int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public int maximumMinutes(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        List<int[]> fires = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    fires.add(new int[]{i, j});
                }
            }
        }
        int l = -1;
        int r = m * n;
        while (l < r) {
            int mid = l + (r - l) / 2 + 1;
            if (reachable(grid, mid, fires)) { // fire reaches the safehouse first, the player fails
                l = mid;
            } else {
                r = mid - 1;
            }

        }
        return l == m * n ? (int) 1e9 : l; // 1e9 is 10^9
    }

    private boolean reachable(int[][] grid, int move, List<int[]> fires) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] copy = clone(grid);
        Queue<int[]> fireQueue = new LinkedList<>();
        fireQueue.addAll(fires);
        while (!fireQueue.isEmpty() && move-- > 0) {
            if (spread(fireQueue, copy)) {
                return false;
            }
        }
        Queue<int[]> person = new LinkedList<>();
        person.add(new int[]{0, 0});
        while (!person.isEmpty()) {
            boolean onFire = spread(fireQueue, copy);
            if (spread(person, copy)) {
                return true;
            }
            if (onFire) {
                return false;
            }
        }
        return false;
    }

    // return true if it spreads to safehouse
    private boolean spread(Queue<int[]> queue, int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int size = queue.size();
        while (size-- > 0) {
            int[] cell = queue.poll();
            for (int[] dir: dirs) {
                int x = cell[0] + dir[0];
                int y = cell[1] + dir[1];
                if (x == m - 1 && y == n - 1) {
                    return true;
                }
                if (x >= 0 && x < m && y >=0 && y < n && grid[x][y]  == 0) {
                    grid[x][y] = -1;
                    queue.add(new int[]{x, y});
                }
            }
        }
        return false;
    }

    int[][] clone(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] copy = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                copy[i][j] = grid[i][j];
            }
        }
        return copy;
    }

    // sol 2: https://leetcode.com/problems/escape-the-spreading-fire/solutions/1995157/solution-intuition-explanation-with-pictures/

    public boolean ok(int[][] grid, int[][] dist, int wait_time) {
        int n = grid.length;
        int m = grid[0].length;

        Queue<Tuple<Integer, Integer, Integer>> Q = new LinkedList<>();
        Q.add(new Tuple<>(0, 0, wait_time));

        int[][] visited = new int[n][m];
        visited[0][0] = 1;

        while (!Q.isEmpty()) {
            Tuple<Integer, Integer, Integer> at = Q.poll();
            int[][] moves = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

            for (int[] to : moves) {
                int ii = at.first + to[0];
                int jj = at.second + to[1];
                if (!inBounds(ii, jj, n, m) || visited[ii][jj] == 1 || grid[ii][jj] == 2) continue;
                if (ii == n - 1 && jj == m - 1 && dist[ii][jj] >= at.third + 1) return true;
                if (dist[ii][jj] <= at.third + 1) continue;
                Q.add(new Tuple<>(ii, jj, 1 + at.third));
                visited[ii][jj] = 1;
            }
        }
        return false;
    }

    public boolean inBounds(int i, int j, int n, int m) {
        return (0 <= i && i < n && 0 <= j && j < m);
    }

    public int maximumMinutes2(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[][] dist = new int[n][m];

        for (int[] r : dist) Arrays.fill(r, Integer.MAX_VALUE);

        Queue<Tuple<Integer, Integer, Integer>> Q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    Q.add(new Tuple<>(i, j, 0));
                    dist[i][j] = 0;
                }
            }
        }

        while (!Q.isEmpty()) {
            Tuple<Integer, Integer, Integer> at = Q.poll();
            int[][] moves = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            for (int[] to : moves) {
                int ii = at.first + to[0];
                int jj = at.second + to[1];
                if (!inBounds(ii, jj, n, m) || grid[ii][jj] == 2 || dist[ii][jj] <= at.third + 1) continue;
                dist[ii][jj] = 1 + at.third;
                Q.add(new Tuple<>(ii, jj, 1 + at.third));
            }
        }

        int left = 0;
        int right = 1_000_000_000;

        int ans = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (ok(grid, dist, mid)) {
                ans = mid;
                left = mid + 1;
            } else right = mid - 1;
        }

        return ans;
    }

    static class Tuple<T, K, L> {
        T first;
        K second;
        L third;

        public Tuple(T first, K second, L third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }
    }
}
