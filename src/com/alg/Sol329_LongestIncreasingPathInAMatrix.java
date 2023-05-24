package com.alg;

import java.util.PriorityQueue;

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

    // memo +  dfs
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

            int len = 1 +  dfs(matrix,newX, newY, cache);
            longestLength = Math.max(longestLength, len);
        }
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

//    Longest Path With Same Number

//    private int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int longestPathWithSameNumber(int[][] arr) {

        int longestPath = 1;
        int n = arr.length, m = arr[0].length;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                boolean[][] visited = new boolean[arr.length][arr[0].length];
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
                visited[newX][newY] || arr[newX][newY] != target) continue;

            int path = 1 + dfs2(arr, visited, newX, newY, target);
            longestPath = Math.max(longestPath, path);
        }

        // add backtrack?
        visited[row][col] = false;

        return longestPath;
    }
    /*  this returns 6 instead of 7 for
    [
    [1, 1, 2, 1],
    [5, 5, 5, 5],
    [5, 5, 5, 1]
]
     */
}
