package com.alg;

import java.util.*;

/*
In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].

A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.

Return the minimum number of steps needed to move the knight to the square [x, y]. It is guaranteed the answer exists.



Example 1:

Input: x = 2, y = 1
Output: 1
Explanation: [0, 0] → [2, 1]

Example 2:

Input: x = 5, y = 5
Output: 4
Explanation: [0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]



Constraints:

    -300 <= x, y <= 300
    0 <= |x| + |y| <= 300


 */
public class Sol1197_MinimumKnightMove {
    // time limit exceeded using set
    public int minKnightMoves(int x, int y) {
        int[][] dirs = {
                {2, 1},
                {1, 2},
                {-1, 2},
                {-2, 1},
                {-1, -2},
                {-2, -1},
                {1, -2},
                {2, -1},
        };
        int move = 0;
        Queue<int[]> queue = new LinkedList<>();
//        Set<String> set = new HashSet<>();
        // - Rather than using the inefficient HashSet, we use the bitmap
        //     otherwise we would run out of time for the test cases.
        // - We create a bitmap that is sufficient to cover all the possible
        //     inputs, according to the description of the problem.
        boolean[][] visited = new boolean[607][607];
//        set.add("0,0");
        queue.offer(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int cx = cur[0];
                int cy = cur[1];
                if (cx == x && cy == y) return move;
                for (int j = 0; j < 8; j++) {
                    int nx = cx + dirs[j][0];
                    int ny = cy + dirs[j][1];
                    if (!visited[nx + 302][ny + 302]) {
                        visited[nx+302][ny+302]= true;
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
            move++;
        }
        return -1;
    }

    public int minKnightMoves2(int x, int y) {

        int m = 300;
        if (x == 0 && y == 0) {
            return 0;
        }

        int minimumMoves = 0;
        int[][] directions = {{1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}};
        boolean[][] visited = new boolean[2*m+1][2*m+1];
        Queue<int[]> queue = new LinkedList<> ();

        visited[0][0] = true;
        queue.offer (new int[] {0, 0});

        while (!queue.isEmpty()) {
            ++minimumMoves;
            int size = queue.size ();
            while (size-- != 0) {
                int[] curr = queue.poll();
                if (curr[0] == x && curr[1] == y) {
                    return minimumMoves;
                }
                for (int[] direction : directions) {
                    int row = (curr[0] + direction[0]);
                    int col = (curr[1] + direction[1]);

                    if (row > -m && col > -m && row < m && col <m && !visited[row][col]) {
                        visited[row][col] = true;
                        queue.offer (new int[] {row, col});
                    }
                }
            }
        }

        return -1;
    }

    // bidirectional bfs
    public int minKnightMoves3(int x, int y) {
        // the offsets in the eight directions
        int[][] offsets = {{1, 2}, {2, 1}, {2, -1}, {1, -2},
                {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}};

        // data structures needed to move from the origin point
        Deque<int[]> originQueue = new LinkedList<>();
        originQueue.addLast(new int[]{0, 0, 0});
        Map<String, Integer> originDistance = new HashMap<>();
        originDistance.put("0,0", 0);

        // data structures needed to move from the target point
        Deque<int[]> targetQueue = new LinkedList<>();
        targetQueue.addLast(new int[]{x, y, 0});
        Map<String, Integer> targetDistance = new HashMap<>();
        targetDistance.put(x + "," + y, 0);

        while (true) {
            // check if we reach the circle of target
            int[] origin = originQueue.removeFirst();
            String originXY = origin[0] + "," + origin[1];
            if (targetDistance.containsKey(originXY)) {
                return origin[2] + targetDistance.get(originXY);
            }

            // check if we reach the circle of origin
            int[] target = targetQueue.removeFirst();
            String targetXY = target[0] + "," + target[1];
            if (originDistance.containsKey(targetXY)) {
                return target[2] + originDistance.get(targetXY);
            }

            for (int[] offset : offsets) {
                // expand the circle of origin
                int[] nextOrigin = new int[]{origin[0] + offset[0], origin[1] + offset[1]};
                String nextOriginXY = nextOrigin[0] + "," + nextOrigin[1];
                if (!originDistance.containsKey(nextOriginXY)) {
                    originQueue.addLast(new int[]{nextOrigin[0], nextOrigin[1], origin[2] + 1});
                    originDistance.put(nextOriginXY, origin[2] + 1);
                }

                // expand the circle of target
                int[] nextTarget = new int[]{target[0] + offset[0], target[1] + offset[1]};
                String nextTargetXY = nextTarget[0] + "," + nextTarget[1];
                if (!targetDistance.containsKey(nextTargetXY)) {
                    targetQueue.addLast(new int[]{nextTarget[0], nextTarget[1], target[2] + 1});
                    targetDistance.put(nextTargetXY, target[2] + 1);
                }
            }
        }
    }
}
