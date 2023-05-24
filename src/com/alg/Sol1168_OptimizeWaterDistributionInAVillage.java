package com.alg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/*
There are n houses in a village. We want to supply water for all the houses by building wells and laying pipes.

For each house i, we can either build a well inside it directly with cost wells[i - 1] (note the -1 due to 0-indexing), or pipe in water from another well to it. The costs to lay pipes between houses are given by the array pipes where each pipes[j] = [house1j, house2j, costj] represents the cost to connect house1j and house2j together using a pipe. Connections are bidirectional, and there could be multiple valid connections between the same two houses with different costs.

Return the minimum total cost to supply water to all houses.



Example 1:

Input: n = 3, wells = [1,2,2], pipes = [[1,2,1],[2,3,1]]
Output: 3
Explanation: The image shows the costs of connecting houses using pipes.
The best strategy is to build a well in the first house with cost 1 and connect the other houses to it with cost 2 so the total cost is 3.

Example 2:

Input: n = 2, wells = [1,1], pipes = [[1,2,1],[1,2,2]]
Output: 2
Explanation: We can supply water with cost two using one of the three options:
Option 1:
  - Build a well inside house 1 with cost 1.
  - Build a well inside house 2 with cost 1.
The total cost will be 2.
Option 2:
  - Build a well inside house 1 with cost 1.
  - Connect house 2 with house 1 with cost 1.
The total cost will be 2.
Option 3:
  - Build a well inside house 2 with cost 1.
  - Connect house 1 with house 2 with cost 1.
The total cost will be 2.
Note that we can connect houses 1 and 2 with cost 1 or with cost 2 but we will always choose the cheapest option.



Constraints:

    2 <= n <= 104
    wells.length == n
    0 <= wells[i] <= 105
    1 <= pipes.length <= 104
    pipes[j].length == 3
    1 <= house1j, house2j <= n
    0 <= costj <= 105
    house1j != house2j


 */
public class Sol1168_OptimizeWaterDistributionInAVillage {
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        // add a virtual vertex to the graph as the cost of each vertex, in addition to the cost of each edge
        // Prim + priority queue
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            graph.computeIfAbsent(0, v -> new HashMap<>()).put(i, wells[i-1]); // connect to 0th virtual vertex
        }

        for (int i = 0; i < pipes.length; i++) {
            int[] edge = pipes[i];
            int house1 = edge[0];
            int house2 = edge[1];
            int cost = edge[2];
            // to avoid duplicated edges in the input between 2 nodes [1,2,1] [1,2,5] 只取[1,2,1]
            int minFrom0To1 = graph.computeIfAbsent(house1, v -> new HashMap<>()).getOrDefault(house2, Integer.MAX_VALUE);
            int minFrom1To0 = graph.computeIfAbsent(house2, v -> new HashMap<>()).getOrDefault(house1, Integer.MAX_VALUE);

            graph.get(house1).put(house2, Math.min(cost, minFrom0To1));
            graph.get(house2).put(house1, Math.min(cost, minFrom1To0));
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]); // order by cost
        Set<Integer> visited = new HashSet<>();
        pq.offer(new int[]{0, 0}); // {current node, current cost}
        int res = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int currentNode = cur[0];
            int currentCost = cur[1];
            if (!visited.contains(currentNode)) {
                res += currentCost;
                visited.add(currentNode);
                for (int neighbor : graph.getOrDefault(currentNode, new HashMap<>()).keySet()) {  // to avoid null
                    if (!visited.contains(neighbor)) {
                        pq.offer(new int[]{neighbor, graph.get(currentNode).get(neighbor)});  // no isolated nodes,
                    }
                }
            }
        }
        return res;
    }

    //Kruscal, by rank is better than by weight union find
    // time ElogE,  E: number of edges
    class DSU{
        int[] parents;
        public DSU(int n) {
            parents = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
        }
        public int find(int x) {
            if (parents[x] != x) parents[x] = find(parents[x]);
            return parents[x];
        }
        public void union(int x, int y) {
            parents[find(x)] = find(y);
        }
    }

    public int minCostToSupplyWate2r(int n, int[] wells, int[][] pipes) {
        DSU dsu = new DSU(n + 1);
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < n ;i++) {
            edges.add(new int[]{0, i + 1, wells[i]});
        }
        for (int[] pipe : pipes) edges.add(pipe);
        Collections.sort(edges, Comparator.comparingInt(a -> a[2]));
        int res = 0;
        for (int[] edge: edges) {
            int h1 = edge[0];
            int h2 = edge[1];
            if (dsu.find(h1) != dsu.find(h2)) {
                dsu.union(h1, h2);
                res += edge[2];
            }
        }
        return res;
    }
}
