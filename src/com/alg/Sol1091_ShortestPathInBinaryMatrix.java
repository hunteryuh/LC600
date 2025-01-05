package com.alg;

import java.sql.SQLOutput;
import java.util.*;

/*
Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.

A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:

All the visited cells of the path are 0.
All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
The length of a clear path is the number of visited cells of this path.



Example 1:


Input: grid = [[0,1],[1,0]]
Output: 2
Example 2:


Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
Output: 4
Example 3:

Input: grid = [[1,0,0],[1,1,0],[1,1,0]]
Output: -1


https://leetcode.com/problems/shortest-path-in-binary-matrix/
 */
public class Sol1091_ShortestPathInBinaryMatrix {
    // 8 directions

    public int shortestPathBinaryMatrix(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        // used to not overwrite the input for possible scenarios like:
        /*


    That the algorithm is running in a * multithreaded* environment, and
    it does not have exclusive access to the grid. Other threads might need to read the grid too,
    and might not expect it to be modified.

    That there is only a single thread or the algorithm has exclusive access to the grid while running,
    but the grid might need to be reused later or by another thread once the lock has been released.

         */
        LinkedList<int[]> queue = new LinkedList<>(); // or new ArrayDeque<>()
        if (grid[0][0] != 0 || grid[grid.length - 1][grid[0].length - 1] != 0) return -1;
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;
        int res = 1;
        int[] dirx = {-1,0,0,1,-1,-1,1,1};
        int[] diry = {0,1,-1,0,1,-1,1,-1};
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] p = queue.poll(); // need to be within the for size loop to pool the element in the outer layer when bfs
                int x = p[0];
                int y = p[1];
                if (x == m - 1 && y == n - 1) {
                    return res;
                }
                for (int j = 0; j < dirx.length; j++) {
                    int nx = x + dirx[j];
                    int ny = y + diry[j];
                    if (nx>=0 && nx < m && ny >=0 && ny< n && grid[nx][ny] == 0 && !visited[nx][ny]) {
                        queue.offer(new int[]{nx,ny});
                        visited[nx][ny] = true;
                    }
                }
            }
            res++;
        }
        return -1;
    }
    // print shortest path
    private static final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    public List<int[]> shortestPathBinaryMatrix2(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
            return Collections.emptyList();
        }
        Queue<int[]> queue = new LinkedList<>();
        Map<String, int[]> parentMap = new HashMap<>();
//        Map<Integer, List<Integer>> parents = new HashMap<>();
        // key: current xy, value: previous xy where the path comes from
        queue.offer(new int[]{0, 0});
        grid[0][0] = 1;
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int row = cell[0], col = cell[1];
            if (row == n - 1 && col == n - 1) {
                return constructPath(parentMap, row, col);
            }
            for (int[] direction : directions) {
                int newRow = row + direction[0], newCol = col + direction[1];
                if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n && grid[newRow][newCol] == 0) {
                    queue.offer(new int[]{newRow, newCol});
                    grid[newRow][newCol] = grid[row][col] + 1; // overriding the input
                    parentMap.put(newRow + "," + newCol, new int[]{row, col});
                }
            }
        }
        /*
        List<List<Integer>> allPaths = new ArrayList<>();
        backtrack(allPaths, new ArrayList<>(), parents, end, start);
        return allPaths;
}
         */
        return Collections.emptyList();
    }

    // if there are multiple shortest paths


    private void backtrack(List<List<Integer>> allPaths, List<Integer> currentPath,
                           Map<Integer, List<Integer>> parents, int current, int start) {
        if (current == start) {
            List<Integer> path = new ArrayList<>(currentPath);
            path.add(start);
            Collections.reverse(path);
            allPaths.add(path);
            return;
        }

        currentPath.add(current);
        for (int parent : parents.get(current)) {
            backtrack(allPaths, currentPath, parents, parent, start);
        }
        currentPath.remove(currentPath.size() - 1);
    }

    private List<int[]> constructPath(Map<String, int[]> parentMap, int row, int col) {
        List<int[]> path = new ArrayList<>();
        int[] cell = new int[]{row, col};
        while (cell != null) {
            path.add(0, cell);
            cell = parentMap.get(cell[0] + "," + cell[1]);
        }
        return path;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {0,0,0},
                {1,1,0},
                {1,1,0},
        };

        Sol1091_ShortestPathInBinaryMatrix ss = new Sol1091_ShortestPathInBinaryMatrix();
        List<int[]> res = ss.shortestPathBinaryMatrix2(grid);
        for (int[] array: res) {
            System.out.println(Arrays.toString(array));
        }
//        res.forEach(a -> System.out.println(Arrays.toString(a)));
    }

    // with override, without visited array
    public int shortestPathBinaryMatrix3(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dir = {
                {-1, -1}, {-1, 0}, {-1, 1}, {0, 1},
                {1, 1}, {1, 0}, {1, -1}, {0, -1}
        };

        Queue<int[]> queue = new LinkedList<>();
        if (grid[0][0] != 0 || grid[m-1][n-1] != 0) return -1;
        queue.offer(new int[]{0, 0});
        grid[0][0] = 1;
        int res = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int x = cur[0];
                int y = cur[1];
                if (x == m - 1 && y == n - 1) {
                    return res;
                }
                for (int j = 0; j < 8; j++) {
                    int nextx = x + dir[j][0];
                    int nexty = y + dir[j][1];
                    if (nextx >= 0 && nextx < m && nexty >= 0 && nexty < n
                            && grid[nextx][nexty] == 0) {
                        grid[nextx][nexty] = grid[x][y] + 1;
                        queue.offer(new int[]{nextx, nexty});
                    }
                }
            }
            res++;
        }
        return -1;
    }

    // find all shortest paths from perplexity 12/2/2024
    public List<List<int[]>> findAllShortestPaths(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] == 1 || grid[n-1][n-1] == 1) return Collections.emptyList();

        Queue<int[]> queue = new LinkedList<>();
        Map<String, List<String>> parents = new HashMap<>();
        Map<String, Integer> distance = new HashMap<>();
        int[][] directions = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};

        String start = "0,0";
        String end = (n-1) + "," + (n-1);

        queue.offer(new int[]{0, 0});
        distance.put(start, 1);
        parents.put(start, new ArrayList<>());

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            String currentKey = row + "," + col;

            if (currentKey.equals(end)) break;

            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];
                String neighborKey = newRow + "," + newCol;

                if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n && grid[newRow][newCol] == 0) {
                    int newDist = distance.get(currentKey) + 1;

                    if (!distance.containsKey(neighborKey) || distance.get(neighborKey) > newDist) {
                        distance.put(neighborKey, newDist);
                        queue.offer(new int[]{newRow, newCol});
                        parents.put(neighborKey, new ArrayList<>(Collections.singletonList(currentKey)));
                    } else if (distance.get(neighborKey) == newDist) {
                        parents.get(neighborKey).add(currentKey);
                    }
                }
            }
        }

        List<List<int[]>> allPaths = new ArrayList<>();
        if (distance.containsKey(end)) {
            backtrack(allPaths, new ArrayList<>(), parents, end);
        }
        return allPaths;
    }

    private void backtrack(List<List<int[]>> allPaths, List<int[]> currentPath,
                           Map<String, List<String>> parents, String current) {
        String[] parts = current.split(",");
        int row = Integer.parseInt(parts[0]);
        int col = Integer.parseInt(parts[1]);

        currentPath.add(new int[]{row, col});

        if (parents.get(current).isEmpty()) { // reaches the start
            Collections.reverse(currentPath);
            allPaths.add(new ArrayList<>(currentPath));
            Collections.reverse(currentPath);
        } else {
            for (String parent : parents.get(current)) {
                backtrack(allPaths, currentPath, parents, parent);
            }
        }

        currentPath.remove(currentPath.size() - 1);
    }

}
