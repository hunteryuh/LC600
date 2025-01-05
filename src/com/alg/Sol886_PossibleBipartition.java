package com.alg;

import java.util.*;

/*
We want to split a group of n people (labeled from 1 to n) into two groups of any size.
Each person may dislike some other people, and they should not go into the same group.

Given the integer n and the array dislikes where dislikes[i] = [ai, bi] indicates
that the person labeled ai does not like the person labeled bi,
return true if it is possible to split everyone into two groups in this way.



Example 1:

Input: n = 4, dislikes = [[1,2],[1,3],[2,4]]
Output: true
Explanation: The first group has [1,4], and the second group has [2,3].

Example 2:

Input: n = 3, dislikes = [[1,2],[1,3],[2,3]]
Output: false
Explanation: We need at least 3 groups to divide them. We cannot put them in two groups.



Constraints:

    1 <= n <= 2000
    0 <= dislikes.length <= 104
    dislikes[i].length == 2
    1 <= ai < bi <= n
    All the pairs of dislikes are unique.


 */
public class Sol886_PossibleBipartition {
    //https://leetcode.com/problems/possible-bipartition/editorial/
    // time:: O(n + e) , n number of people, e: number of edges
    public boolean possibleBipartition(int n, int[][] dislikes) {
        // adjacent list
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int[] edge: dislikes) {
            int a = edge[0];
            int b = edge[1];
            adj.computeIfAbsent(a, i -> new ArrayList<>()).add(b);
            adj.computeIfAbsent(b, i -> new ArrayList<>()).add(a); // need to put an arrow from b to a as well as both cannot be in the same group
        }

        int[] color = new int[n + 1];
        Arrays.fill(color, -1); // 0 stands for red and 1 for blue; maximum 2 groups
        for (int i = 1; i <= n; i++) {
            if (color[i] == -1) {
                // For each pending component, run BFS.
                if(!bfs(i, adj, color)) {
                    return false;
                }
            }
        }

        return true;

    }

    private boolean bfs(int source, Map<Integer, List<Integer>> adj, int[] color) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);
        color[source] = 0; // starting it with red (or blue, pick any 1)
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (!adj.containsKey(cur)) continue; // avoid null error of map.get(key)
            for (int neighbor: adj.get(cur)) {
                if (color[neighbor] == color[cur]) {
                    return false;
                }
                if (color[neighbor] == -1) {
                    color[neighbor] = 1 - color[cur];
                    queue.offer(neighbor);
                }
            }
        }
        return true;
    }
}
