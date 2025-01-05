package com.alg;

import java.util.*;

/*
You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF
as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate.
If it is impossible to reach a gate, it should be filled with INF.

For example, given the 2D grid:
INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
After running your function, the 2D grid should be:
  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4

 */
public class Sol286_WallsAndGates {
    // dfs
    public void wallsAndGates(int[][] rooms) {
        if (rooms.length == 0 || rooms[0].length == 0) return;
        int m = rooms.length;
        int n = rooms[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    dfs(rooms, i, j, 0);
                }
            }
        }
    }

    private void dfs(int[][] rooms, int x, int y, int dis) {
        if (x < 0 || y < 0 || x >= rooms.length || y >= rooms[0].length ||
                rooms[x][y] == -1) {
            return;
        }
        if (rooms[x][y] > dis || dis == 0) {
            rooms[x][y] = dis;
            dfs(rooms, x + 1, y, dis + 1);
            dfs(rooms, x - 1, y, dis + 1);
            dfs(rooms, x, y + 1, dis + 1);
            dfs(rooms, x, y - 1, dis + 1);
        }
    }

    // bfs
    // time: O(m * n)
    public void wallsAndGatesBFS(int[][] rooms) {
        int m = rooms.length;
        int n = rooms[0].length;
        Queue<int[]> q = new LinkedList<>();
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (rooms[row][col] == 0) {
                    q.offer(new int[]{row, col});
                }
            }
        }
        int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!q.isEmpty()) {
            int[] point = q.poll();
            int row = point[0];
            int col = point[1];
            for (int[] dir : directions) {
                int r = row + dir[0];
                int c = col + dir[1];
                if (r < 0 || r >= m || c < 0 || c >= n || rooms[r][c] != Integer.MAX_VALUE) {
                    // as long as it is calculated or not needed to be calculated, like 0 or -1, or a distance
                    // or use a visited_set ( -1, or 0, or visited)
                    continue; // once a room is hits, it has to be from the closest gate.
                }
                rooms[r][c] = rooms[row][col] + 1;
                q.offer(new int[]{r, c});
            }
        }
    }

    // get closest nearest dashmart; doordash
    // tested on 11/4/2022
    // https://leetcode.com/discuss/interview-question/1522955/Doordash-Onsite

    // # A DashMart is a warehouse run by DoorDash that houses items found in
// # convenience stores, grocery stores, and restaurants. We have a city with open
// # roads, blocked-off roads, and DashMarts.
// #
// # City planners want you to identify how far a location is from its closest
// # DashMart.
// #
// # You can only travel over open roads (up, down, left, right).
// #
// # Locations are given in [row, col] format.
// #
// # Example:
// #
// # [
// # # 0 1 2 3 4 5 6 7 8
// # ['X', ' ', ' ', 'D', ' ', ' ', 'X', ' ', 'X'], # 0
// # ['X', ' ', 'X', 'X', ' ', ' ', ' ', ' ', 'X'], # 1
// # [' ', ' ', ' ', 'D', 'X', 'X', ' ', 'X', ' '], # 2
// # [' ', ' ', ' ', 'D', ' ', 'X', ' ', ' ', ' '], # 3
// # [' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ', 'X'], # 4
// # [' ', ' ', ' ', ' ', 'X', ' ', ' ', 'X', 'X'] # 5
// # ]
// #
// # ' ' represents an open road that you can travel over in any direction (up, down, left, or right).
// # 'X' represents a blocked road that you cannot travel through.
// # 'D' represents a DashMart.
// #
// # # list of pairs [row, col]
// # locations = [
// # [2, 2],
// # [4, 0],
// # [0, 4],
// # [2, 6],
// # ]
// #
// # answer = [1, 4, 1, 5]
// #
// # Implement Function:
// # - get_closest_dashmart(city, locations)
// #
// # Provided:
// # - city: List[str]
// # - locations: List[List[int]]
// #
// # Return:
// # - answer: List[int]

    int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public List<Integer> findMinDistance(char[][] cityMap, List<int[]> deliveryLocations) {
        List<Integer> res = new ArrayList<>();
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < cityMap.length; i++) {
            for (int j = 0; j < cityMap[0].length; j++) {
                if (cityMap[i][j] == 'D') queue.offer(new int[]{i, j});
            }
        }
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int row = curr[0];
            int col = curr[1];
            for (int[] dir : dirs) {
                int r = row + dir[0];
                int c = col + dir[1];
                // can reach 'X' but cannot go further from X
                // if cityMap is not empty and not X, it means its already visited and with the shortest distance by the BFS
                // here we need to exclude all those visited, which does not include X because X can be visited,
                // edge case:? visited but distance is at 'X' - 'D'
                if (r < 0 || c < 0 || r >= cityMap.length || c >= cityMap[0].length || (cityMap[r][c] != ' ' && cityMap[r][c] != 'X'))
                    continue;
                // " " is road; if not road, skip
                char temp = cityMap[r][c];
                cityMap[r][c] = (char) (cityMap[row][col] + 1);
                if (temp != 'X') {  // X can reach but cannot go further, so not adding in the queue.
                    queue.offer(new int[]{r, c});
                }
            }
        }
        for (int[] deliveryLocation : deliveryLocations) {
            int i = deliveryLocation[0];
            int j = deliveryLocation[1];
            if (cityMap[i][j] == 'X') {
                res.add(-1);
            } else {
                res.add(cityMap[i][j] - 'D'); // int distance
            }
        }
        // sout Arrays.toString(res)
        return res;
    }

    public static void main(String[] args) {
        char[][] locationBoard = {
                {'X', ' ', ' ', 'D', ' ', ' ', 'X', ' ', 'X'},
                {'X', ' ', 'X', 'X', ' ', ' ', ' ', ' ', 'X'},
                {' ', ' ', ' ', 'D', 'X', 'X', ' ', 'X', ' '},
                {' ', ' ', ' ', 'D', ' ', 'X', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ', 'X'},
                {' ', ' ', ' ', ' ', 'X', ' ', ' ', 'X', 'X'}};

        List<int[]> locations = new ArrayList<>();
        locations.add(new int[]{2, 2});
        locations.add(new int[]{4, 0});
        locations.add(new int[]{0, 4});
        locations.add(new int[]{2, 6});
        locations.add(new int[]{0, 0});
        locations.add(new int[]{5, 7});
        locations.add(new int[]{5, 8});

        Sol286_WallsAndGates ss = new Sol286_WallsAndGates();
        List<Integer> res = ss.findMinDistance(locationBoard, locations);
        System.out.println(Arrays.deepToString(locationBoard));
        System.out.println(res);

    }
}
