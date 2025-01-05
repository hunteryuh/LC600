package com.alg;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/*
Given an m x n integers matrix, return the length of the longest increasing path in matrix.

From each cell, you can either move in four directions: left, right, up, or down.
 You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).



Example 1:

Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
Output: 4
Explanation: The longest increasing path is [1, 2, 6, 9].

Example 2:

Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
Output: 4
Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.

Example 3:

Input: matrix = [[1]]
Output: 1



Constraints:

    m == matrix.length
    n == matrix[i].length
    1 <= m, n <= 200
    0 <= matrix[i][j] <= 231 - 1


 */
public class Sol329_LongestIncreasingPathInAMatrix {

    private int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    // https://leetcode.com/problems/longest-increasing-path-in-a-matrix/solutions/78308/15ms-concise-java-solution/
    public int longestIncreasingPath(int[][] matrix) {
        int longestPath = 1;
        int m = matrix.length, n = matrix[0].length;
        int[][] cache = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int len = dfs(matrix, i, j, cache);
                longestPath = Math.max(longestPath, len);
            }
        }

        return longestPath;
    }

    // https://leetcode.com/problems/longest-increasing-path-in-a-matrix/solutions/78308/15ms-concise-java-solution/
    // memo +  dfs
    // time: O(V+ E), or O(mn) where V is total number of vertices m*n and E is total number of edges which is 4V = 4mn
    public int dfs(int[][] matrix, int i, int j, int[][] cache) {

        if (cache[i][j] != 0) {
            return cache[i][j];
        }
        int longestLength = 1;
        for(int[] d : dirs) {
            int newX = i + d[0];
            int newY = j + d[1];

            if (newX < 0 || newY < 0 || newX >= matrix.length || newY >= matrix[0].length ||
                matrix[newX][newY] <= matrix[i][j]) continue; // use matrix[x][y] - matrix[i][j] so that we do not need visited array
            // if next point is a valid point, add curLen by 1 and continue DFS traversal
            int len = 1 +  dfs(matrix, newX, newY, cache);
            longestLength = Math.max(longestLength, len);
        }
        // update max increasing path value starting from current point in cache
        cache[i][j] = longestLength;

        return longestLength;
    }

    // maxPQ + DP
    private int maxLen = 0;

    public int longestIncreasingPath2(int[][] matrix) {

        // Algo thinking: reverse thinking
        //      (1) Use a maxPQ
        //      (2) DP
        // time = O(N*M*lg(N*M)), space = O(N*M)

        if (matrix == null || matrix.length == 0) return 0;

        int n = matrix.length;
        int m = matrix[0].length;

        PriorityQueue<int[]> maxPQ = new PriorityQueue<>((a, b) -> b[2] - a[2]);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                maxPQ.offer(new int[]{i, j, matrix[i][j]});
            }
        }

        int[][] dp = new int[n][m];
        while (!maxPQ.isEmpty()) {
            int[] cell = maxPQ.poll();
            int i = cell[0], j = cell[1];
            dp[i][j] = 1;
            for (int[] d: dirs) {
                int newI = i + d[0], newJ = j + d[1];
                if (newI < 0 || newI >= n || newJ < 0 || newJ >= m || matrix[i][j] >= matrix[newI][newJ]) continue;
                dp[i][j] = Math.max(dp[i][j], dp[newI][newJ] + 1);
            }

            maxLen = Math.max(maxLen, dp[i][j]);
        }

        return maxLen;
    }

    // bfs + topological sort
    // https://leetcode.com/problems/longest-increasing-path-in-a-matrix/solutions/6022387/topological-sort/
    public int longestIncreasingPath_bfs(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        // generating the indegree array
        // note that we have M*N nodes
        int[][] indegree = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int[] dir : dirs) {
                    int nx = i + dir[0];
                    int ny = j + dir[1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                        if (matrix[nx][ny] > matrix[i][j]) {
                            // SMALLER       LARGER
                            // node(i,j) --> node(nx,ny)
                            indegree[nx][ny]++;
                        }
                    }
                }
            }
        }


        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (indegree[i][j] == 0) {
                    queue.add(new int[]{i, j});
                }
            }
        }

        int length = 0;

        while (!queue.isEmpty()) {
            // level by level
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int x = cur[0];
                int y = cur[1];

                for (int[] dir: dirs) {
                    int nx = x + dir[0];
                    int ny = y + dir[1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                        // note that indegree[x][y] is zero
                        if (matrix[x][y] < matrix[nx][ny]) {
                            indegree[nx][ny]--;
                            if (indegree[nx][ny] == 0) {
                                queue.offer(new int[]{nx, ny});
                            }
                        }
                    }
                }
            }
            length++;
        }

        return length;
    }

//    Longest Path With Same Number

//    private int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int longestPathWithSameNumber(int[][] arr) {

        int longestPath = 1;
        int n = arr.length, m = arr[0].length;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                boolean[][] visited = new boolean[arr.length][arr[0].length]; // initialize for each starting point
                longestPath = Math.max(longestPath, dfs2(arr, visited, row, col, arr[row][col]));
            }
        }
        return longestPath;
    }

    public int dfs2(int[][] arr, boolean[][] visited, int row, int col, int target) {
        int longestPath = 1;

        visited[row][col] = true;

        for (int[] d : dirs) {
            int newX = row + d[0];
            int newY = col + d[1];

            if (newX < 0 || newY < 0 || newX >= arr.length || newY >= arr[0].length ||
                visited[newX][newY] || arr[newX][newY] != target) continue; // skip visited to not visit twice (see sol695 max area of island)

            int path = 1 + dfs2(arr, visited, newX, newY, target);
            longestPath = Math.max(longestPath, path);
        }
        // adding  backtrack is not needed
//        visited[row][col] = false;

        return longestPath;
    }

    public static void main(String[] args) {
        Sol329_LongestIncreasingPathInAMatrix ss = new Sol329_LongestIncreasingPathInAMatrix();
        int[][] grid = {
                {1,1,2,1},
                {5,5,5,5},
                {5,5,5,1}
        };  //7

        int[][] grid2 = {
                {1, 3, 5, 5, 5},
                {1, 5, 5, 5, 5},
                {1, 1, 5, 5, 5}   // correct longest length 9, although max area is 10
        };
        int res = ss.longestPathWithSameNumber(grid);
        int res2 = ss.longestPathWithSameNumber(grid2);
        System.out.println(res);
        System.out.println(res2);
    }
}
