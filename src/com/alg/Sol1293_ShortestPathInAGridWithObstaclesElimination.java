package com.alg;

import com.alg.other.FB_LinkedList;

import java.util.LinkedList;
import java.util.Queue;

/*
You are given an m x n integer matrix grid where each cell is either 0 (empty) or 1 (obstacle). You can move up, down, left, or right from and to an empty cell in one step.

Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m - 1, n - 1) given that you can eliminate at most k obstacles. If it is not possible to find such walk return -1.



Example 1:


Input: grid = [[0,0,0],[1,1,0],[0,0,0],[0,1,1],[0,0,0]], k = 1
Output: 6
Explanation:
The shortest path without eliminating any obstacle is 10.
The shortest path with one obstacle elimination at position (3,2) is 6. Such path is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).
Example 2:


Input: grid = [[0,1,1],[1,1,1],[1,0,0]], k = 1
Output: -1
Explanation: We need to eliminate at least two obstacles to find such a walk.


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 40
1 <= k <= m * n
grid[i][j] is either 0 or 1.
grid[0][0] == grid[m - 1][n - 1] == 0
 */
public class Sol1293_ShortestPathInAGridWithObstaclesElimination {
// https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/solutions/921400/my-java-solutions-with-comments-using-bfs/
    // we can make use of our own class for the x coordinate, y coordinate, number of obstacles removed, and the distance
    // time: O(N * K): n number of cells in the grid; K: quote to eliminate obstacles
    class Node {
        int x;
        int y;
        int distance;
        int eliminated;
        Node(int x, int y, int distance, int eliminated) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.eliminated = eliminated;
        }
    }
    public int shortestPath(int[][] grid, int k) {
        int[][] dirs = { {-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        int row = grid.length;
        int col = grid[0].length;
        if (k >= row + col - 2) {
            return row + col - 2;
        }
        boolean[][][] visited = new boolean[row][col][row * col]; // i,j, k: number of obstacles removed
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, 0, 0, 0));
        visited[0][0][0] = true;
        // bfs
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int i = cur.x;
            int j = cur.y;
            int dist = cur.distance;
            int eliminated = cur.eliminated;
            if (i == row -1 && j == col - 1) return dist;
            // 4 directions
            for (int d = 0; d < dirs.length; d++) {
                int newx = i + dirs[d][0];
                int newy = j + dirs[d][1];
                // boundary condition true or not
                if (newx >= 0 && newy >= 0 && newx < row && newy < col) {
                    // remove the obstacle
                    int allRemoved = eliminated + grid[newx][newy];
                    if (allRemoved <= k && !visited[newx][newy][allRemoved]) {
                        // update the visited
                        visited[newx][newy][allRemoved] = true;
                        queue.offer(new Node(newx, newy, dist + 1, allRemoved));
                    }
                }
            }
        }
        return -1;
    }

    // method 2, use int[] instead of node class, and count the distance by level
    public int shortestPath2(int[][] grid, int k) {
        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int n = grid.length;
        int m = grid[0].length;
        Queue<int[]> q = new LinkedList();
        boolean[][][] visited = new boolean[n][m][k + 1];
        visited[0][0][0] = true;
        q.offer(new int[]{0, 0, 0});
        int res = 0;
        while (!q.isEmpty()) {
            int size = q.size(); // record the level as distance
            for (int i = 0; i < size; i++) {
                int[] info = q.poll();
                int r = info[0], c = info[1], curK = info[2];
                if (r == n - 1 && c == m - 1) {
                    return res;
                }
                for (int[] dir : dirs) {
                    int nextR = dir[0] + r;
                    int nextC = dir[1] + c;
                    int nextK = curK;
                    if (nextR >= 0 && nextR < n && nextC >= 0 && nextC < m) {
                        if (grid[nextR][nextC] == 1) {
                            nextK++;
                        }
                        if (nextK <= k && !visited[nextR][nextC][nextK]) {
                            visited[nextR][nextC][nextK] = true;
                            q.offer(new int[]{nextR, nextC, nextK});
                        }
                    }
                }
            }
            res++;
        }
        return -1;
    }
}
