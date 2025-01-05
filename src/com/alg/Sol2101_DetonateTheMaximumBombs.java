package com.alg;

import java.util.*;

/*
You are given a list of bombs. The range of a bomb is defined as the area where its effect can be felt. This area is in the shape of a circle with the center as the location of the bomb.

The bombs are represented by a 0-indexed 2D integer array bombs where bombs[i] = [xi, yi, ri]. xi and yi denote the X-coordinate and Y-coordinate of the location of the ith bomb, whereas ri denotes the radius of its range.

You may choose to detonate a single bomb. When a bomb is detonated, it will detonate all bombs that lie in its range. These bombs will further detonate the bombs that lie in their ranges.

Given the list of bombs, return the maximum number of bombs that can be detonated if you are allowed to detonate only one bomb.



Example 1:

Input: bombs = [[2,1,3],[6,1,4]]
Output: 2
Explanation:
The above figure shows the positions and ranges of the 2 bombs.
If we detonate the left bomb, the right bomb will not be affected.
But if we detonate the right bomb, both bombs will be detonated.
So the maximum bombs that can be detonated is max(1, 2) = 2.

Example 2:

Input: bombs = [[1,1,5],[10,10,5]]
Output: 1
Explanation:
Detonating either bomb will not detonate the other bomb, so the maximum number of bombs that can be detonated is 1.

Example 3:

Input: bombs = [[1,2,3],[2,3,1],[3,4,2],[4,5,3],[5,6,4]]
Output: 5
Explanation:
The best bomb to detonate is bomb 0 because:
- Bomb 0 detonates bombs 1 and 2. The red circle denotes the range of bomb 0.
- Bomb 2 detonates bomb 3. The blue circle denotes the range of bomb 2.
- Bomb 3 detonates bomb 4. The green circle denotes the range of bomb 3.
Thus all 5 bombs are detonated.



Constraints:

    1 <= bombs.length <= 100
    bombs[i].length == 3
    1 <= xi, yi, ri <= 105

https://leetcode.com/problems/detonate-the-maximum-bombs/
 */
public class Sol2101_DetonateTheMaximumBombs {
    // https://leetcode.com/problems/detonate-the-maximum-bombs/solutions/1623988/neat-code-java-dfs/
    public int maximumDetonation(int[][] bombs) {
        int n = bombs.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            boolean[] visited = new boolean[n];
            res = Math.max(res, dfs(i, visited, bombs));
        }
        return res;
    }

    // DFS to get the number of nodes reachable from a given node i
    private int dfs(int i, boolean[] visited, int[][] bombs) {
        int count = 1;
        visited[i] = true;
        for (int j = 0; j < bombs.length; j++) {
            if (!visited[j] && isInRange(bombs[i], bombs[j])) {
                count += dfs(j, visited, bombs);
            }
        }
        return count;
    }
    private boolean isInRange(int[] a, int[] b) {  // need to cast to long for large int
        long dx = a[0] - b[0];
        long dy = a[1] - b[1];
        long distant_square = dx * dx + dy * dy;
        long r = a[2];
        return distant_square <= r * r;
    }


    // https://leetcode.com/problems/detonate-the-maximum-bombs/editorial/
    // build the graph first, the bfs to count the size of the visited set
    public int maximumDetonation2(int[][] bombs) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < bombs.length; i++) {
            for (int j = 0; j < bombs.length; j++) {
                if (i != j) {
                    int xi = bombs[i][0];
                    int xj = bombs[i][1];
                    int yi = bombs[j][0];
                    int yj = bombs[j][1];
                    long r2 = (long) bombs[i][2] * bombs[i][2]; // need to cast to long before assignment
                    long distance2= (long) (xi - yi) * (xi - yi) + (long) (xj - yj) * (xj - yj);
                    if (r2 >= distance2) {
                        map.computeIfAbsent(i, x -> new ArrayList<>()).add(j);
                    }
                }
            }
        }

        int res = 0;
        for (int i = 0; i < bombs.length; i++) {
            res = Math.max(res, bfs(i, map));
        }
        return res;

    }

    private int bfs(int i, Map<Integer, List<Integer>> graph) {
        Queue<Integer> deque = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        deque.offer(i);
        visited.add(i);
        while (!deque.isEmpty()) {
            Integer cur = deque.poll();
            for (int neighbor : graph.getOrDefault(cur, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    deque.offer(neighbor);
                    visited.add(neighbor);
                }
            }
        }
        return visited.size();
    }
}
