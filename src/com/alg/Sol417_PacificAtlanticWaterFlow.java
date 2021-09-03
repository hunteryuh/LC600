package com.alg;

import sun.awt.image.ImageWatched;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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


}
