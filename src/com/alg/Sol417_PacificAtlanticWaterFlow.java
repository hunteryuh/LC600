package com.alg;

import sun.awt.image.ImageWatched;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/*
You are given an m x n integer matrix heights representing the height of each unit cell in a continent. The Pacific ocean touches the continent's left and top edges, and the Atlantic ocean touches the continent's right and bottom edges.

Water can only flow in four directions: up, down, left, and right. Water flows from a cell to an adjacent one with an equal or lower height.

Return a list of grid coordinates where water can flow to both the Pacific and Atlantic oceans.



Example 1:


Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
Example 2:

Input: heights = [[2,1],[1,2]]
Output: [[0,0],[0,1],[1,0],[1,1]]
 */
public class Sol417_PacificAtlanticWaterFlow {

    // https://leetcode.com/problems/pacific-atlantic-water-flow/discuss/90733/Java-BFS-and-DFS-from-Ocean
    int[] dirx = {-1, 1, 0, 0};
    int[] diry = {0, 0, -1 ,1};
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> res = new ArrayList<>();
        if (heights == null || heights.length == 0 || heights[0].length == 0) return res;
        int m = heights.length;
        int n = heights[0].length;
        boolean[][] pVisited = new boolean[m][n];
        boolean[][] aVisited = new boolean[m][n];
        Queue<List<Integer>> pq = new LinkedList<>();
        Queue<List<Integer>> aq = new LinkedList<>();
        // the key is to start from all boundaries and add them to the queue before bfs
        for (int i = 0; i < m; i++) {
            pVisited[i][0] = true;
            pq.offer(Arrays.asList(i,0));
            aVisited[i][n-1] = true;
            aq.offer(Arrays.asList(i, n-1));
        }
        for (int i = 0; i < n; i++) {
            pVisited[0][i] = true;
            pq.offer(Arrays.asList(0, i));
            aVisited[m-1][i] = true;
            aq.offer(Arrays.asList(m-1, i));
        }

        // put the visited array and queue as bfs parameters and update visited array
        bfs(heights, m, n, pVisited, pq);
        bfs(heights, m, n, aVisited, aq);

        for (int i = 0; i < m; i++) {
            for (int j = 0;j < n ; j++) {
                if (pVisited[i][j] && aVisited[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        return res;
    }

    private void bfs(int[][] heights, int m, int n, boolean[][] pVisited, Queue<List<Integer>> pq) {
        while (!pq.isEmpty()) {
            List<Integer> list = pq.poll();
            int x = list.get(0);
            int y = list.get(1);
            for (int i = 0; i < dirx.length; i++) {
                int newx = x + dirx[i];
                int newy = y + diry[i];
                if (newx >= 0 && newx < m && newy >= 0 && newy <n && !pVisited[newx][newy] && heights[newx][newy] >= heights[x][y]) {
                    pVisited[newx][newy] = true;
                    pq.add(Arrays.asList(newx, newy));
                }
            }
        }
    }

    public List<List<Integer>> pacificAtlanticDFS(int[][] heights) {
        List<List<Integer>> res = new ArrayList<>();
        if (heights == null || heights.length == 0 || heights[0].length == 0) return res;
        int m = heights.length;
        int n = heights[0].length;
        boolean[][] pVisited = new boolean[m][n];
        boolean[][] aVisited = new boolean[m][n];
        for (int i = 0; i<m; i++) {
            dfs(heights, pVisited, i, 0);
            dfs(heights, aVisited, i, n - 1);
        }

        for (int i = 0; i<n; i++) {
            dfs(heights, pVisited, 0, i);
            dfs(heights, aVisited, m-1, i);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0;j < n ; j++) {
                if (pVisited[i][j] && aVisited[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        return res;
    }

    private void dfs(int[][] heights, boolean[][] pVisited, int x, int y) {
        int m = heights.length;
        int n = heights[0].length;
        pVisited[x][y] = true;
        for (int i = 0; i < dirx.length; i++) {
            int newx = x + dirx[i];
            int newy = y + diry[i];
            if (newx >= 0 && newx < m && newy >= 0 && newy <n && !pVisited[newx][newy] && heights[newx][newy] >= heights[x][y]) {
                dfs(heights, pVisited, newx, newy);
            }
        }
    }


    // not working
//    public List<List<Integer>> canFlow(int[][] heights) {
//        List<List<Integer>> res = new ArrayList<>();
//        Set<List<Integer>> setP = new HashSet<>();
//        Set<List<Integer>> setA = new HashSet<>();
//
//        int m = heights.length;
//        int n = heights[0].length;
//        for (int i = 0; i <m; i++) {
//            for (int j = 0; j < n; j++) {
//                bfs2p(heights, i, j, setP);
//                bfs2a(heights, i, j, setA);
//            }
//        }
//        for (List<Integer> list : setP) {
//            if (setA.contains(list)) {
//                res.add(list);
//            }
//        }
//        return res;
//    }
//
//    private void bfs2p(int[][] heights, int x, int y, Set<List<Integer>> set) {
//        Queue<int[]> queue = new LinkedList<>();
//        int m = heights.length;
//        int n = heights[0].length;
//        boolean[][] visited = new boolean[m][n];
//        queue.offer(new int[]{x, y});
//        visited[x][y] = true;
//        int[][] dirs = {{-1, 0}, {0, 1}, { 0,-1}, {1, 0}};
//        while (!queue.isEmpty()) {
//            int[] cur = queue.poll();
//            int curx = cur[0];
//            int cury = cur[1];
//            for (int[] dir: dirs) {
//                int newx = curx + dir[0];
//                int newy = cury + dir[1];
//                if (newx < 0 || newy < 0 || newx >=m || newy >= n) continue;
//                if (visited[newx][newy]) continue;
//                if (newy == 0 || newx == 0) set.add(Arrays.asList(x, y));
//                if (heights[newx][newy] <= heights[curx][cury]) {
//                    visited[newx][newy] = true;
//                    queue.offer(new int[]{newx, newy});
//                }
//            }
//        }
//    }
//
//    private void bfs2a(int[][] heights, int x, int y, Set<List<Integer>> set) {
//        Queue<int[]> queue = new LinkedList<>();
//        int m = heights.length;
//        int n = heights[0].length;
//        boolean[][] visited = new boolean[m][n];
//        queue.offer(new int[]{x, y});
//        visited[x][y] = true;
//        int[][] dirs = {{-1, 0}, {0, 1}, { 0,-1}, {1, 0}};
//        while (!queue.isEmpty()) {
//            int[] cur = queue.poll();
//            int curx = cur[0];
//            int cury = cur[1];
//            for (int[] dir: dirs) {
//                int newx = curx + dir[0];
//                int newy = cury + dir[1];
//                if (newx < 0 || newy < 0 || newx >=m || newy >= n) continue;
//                if (visited[newx][newy]) continue;
//                if (newy == n-1 || newx == m-1) set.add(Arrays.asList(x, y));
//                if (heights[newx][newy] <= heights[curx][cury]) {
//                    visited[newx][newy] = true;
//                    queue.offer(new int[]{newx, newy});
//                }
//            }
//        }
//    }

}
