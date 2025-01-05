package com.alg;

import com.sun.tools.javac.util.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/*
You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.

Return the size of the largest island in grid after applying this operation.

An island is a 4-directionally connected group of 1s.



Example 1:

Input: grid = [[1,0],[0,1]]
Output: 3
Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
Example 2:

Input: grid = [[1,1],[1,0]]
Output: 4
Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
Example 3:

Input: grid = [[1,1],[1,1]]
Output: 4
Explanation: Can't change any 0 to 1, only one island with area = 4.


Constraints:

n == grid.length
n == grid[i].length
1 <= n <= 500
grid[i][j] is either 0 or 1.
 */

//asked tiktok 12/2/2024
public class Sol827_MakingALargeIsland {
    public int largestIsland(int[][] grid) {
        int[][] dirs = {{-1,0}, {0,-1},{0,1}, {1,0}};
        int m = grid.length;
        int n = grid[0].length;
        DSU dsu = new DSU(m * n);
        int res = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    for (int[] dir: dirs) {
                        int x = i + dir[0];
                        int y = j + dir[1];
                        if (x < 0 || x >= grid.length || y < 0 || y >=grid[0].length || grid[x][y] != 1) continue;;
                        dsu.union(i * n + j, x * n + y);
                        res = Math.max(res, dsu.size[dsu.find(i *n + j)]);
                    }
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    Map<Integer, Integer> map = new HashMap<>();
                    for (int[] dir: dirs) {
                        int x = i + dir[0];
                        int y = j + dir[1];
                        if (x < 0 || x >= grid.length || y < 0 || y >=grid[0].length || grid[x][y] != 1) continue;;
                        int parent = dsu.find(x * n + y);
                        map.put(parent, dsu.size[parent]);
                    }
                    res = Math.max(res, map.values().stream().mapToInt(Integer::intValue).sum() + 1);
                }
            }
        }
        return res;
    }

    class DSU {
        int[] parent;
        int[] size;
        public DSU(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n ; i++) {
                parent[i] = i;
            }
            Arrays.fill(size, 1);
        }

        private int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
        private void union(int x, int y) {
            int rx = find(x);
            int ry = find(y);
            if (rx == ry) return;
            if (size[rx] < size[ry]) {
                parent[rx] = ry;
                size[ry] += size[rx];
            } else {
                parent[ry] = rx;
                size[rx] += size[ry];
            }
        }
    }

    // tiktok 11/2024
    // https://leetcode.com/problems/making-a-large-island/solutions/4412500/java-easy-give-unique-labels-to-each-and-every-connected-component-traverse-through/
    public int N = 0;
    int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public int largestIsland2(int[][] grid) {
        N = grid.length;
        //DFS every island and give it a label of island
        int currentLabel = 3;
        int res = 0;
        HashMap<Integer, Integer> area = new HashMap<>();
        for (int x = 0; x < N; ++x) {
            for (int y = 0; y < N; ++y)
                if (grid[x][y] == 1) {
                    int size = dfs(grid, x, y, currentLabel);
                    area.put(currentLabel, size);
                    res = Math.max(res, size);
                    currentLabel++;
                }
        }

        //traverse every 0 cell and count the biggest island it can connect
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j)
                if (grid[i][j] == 0) {
                    HashSet<Integer> neighborLabels = new HashSet<>(); // store existing island labels
                    for (int[] dir : dirs) {
                        int ni = i + dir[0];
                        int nj = j + dir[1];
                        if (ni >= 0 && ni < N && nj >= 0 && nj < N && grid[ni][nj] > 1) {
                            neighborLabels.add(grid[ni][nj]);
                        }
                    }
                    int combinedSize = 1; // count current 0 as 1
                    for (int label: neighborLabels) {
                        combinedSize += area.get(label);
                    }
                    res = Math.max(res, combinedSize);
                }
        }
        return res;
    }

    public int dfs(int[][] grid, int x, int y, int label) {
        int area = 1;
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] != 1) return 0;
        grid[x][y] = label;
        for (int [] dir: dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            area += dfs(grid, nx, ny, label);
        }
        return area;
    }
}
