package com.alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/*
There are n cities labeled from 1 to n. You are given the integer n and an array connections where connections[i] = [xi, yi, costi] indicates that the cost of connecting city xi and city yi (bidirectional connection) is costi.

Return the minimum cost to connect all the n cities such that there is at least one path between each pair of cities. If it is impossible to connect all the n cities, return -1,

The cost is the sum of the connections' costs used.



Example 1:

Input: n = 3, connections = [[1,2,5],[1,3,6],[2,3,1]]
Output: 6
Explanation: Choosing any 2 edges will connect all cities so we choose the minimum 2.

Example 2:

Input: n = 4, connections = [[1,2,3],[3,4,4]]
Output: -1
Explanation: There is no way to connect all cities even if all edges are used.



Constraints:

    1 <= n <= 104
    1 <= connections.length <= 104
    connections[i].length == 3
    1 <= xi, yi <= n
    xi != yi
    0 <= costi <= 105

Minimum spanning tree

 */
public class Sol1135_ConnectingCitiesWithMinimumCost {
    // 稀疏图 ElogE ~= ElogV  number of edges ~= number of nodes, 稠密图 E= V^2
    // Prim with Priority Queue。 Prim is faster for dense graph
    // https://leetcode.com/problems/connecting-cities-with-minimum-cost/discuss/346137/Java-Prim's-Algorithm-with-Priority-Queue
    public int minimumCost(int n, int[][] connections) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        Set<Integer> visited = new HashSet<>();
        int costs = 0;

        for (int[] conn : connections) {
            int n1 = conn[0], n2 = conn[1], cost = conn[2];

            graph.computeIfAbsent(n1, (k) -> new ArrayList<>()); //.add(new int[] {n2, cost})
            graph.computeIfAbsent(n2, (k) -> new ArrayList<>());
            graph.get(n1).add(new int[] {n2, cost});
            graph.get(n2).add(new int[] {n1, cost});
        }

        pq.add(new int[] {1, 1, 0});
        while (!pq.isEmpty()) {
            int[] conn = pq.poll();
            int n1 = conn[0];
            int n2 = conn[1], cost = conn[2];

            if (!visited.contains(n2)) {
                costs += cost;
                visited.add(n2);
                for (int[] next : graph.get(n2)) {
                    pq.add(new int[] {n2, next[0], next[1]}); // next[1] is the cost
                }
            }
        }

        return visited.size() == n ? costs : -1;
    }

    // DSU for Kruscal, faster in sparse graphs ElogE,  for sparse, ~ ElogV

    class DSU{
        int[] parents;
        DSU(int n) {
            parents = new int[n];
            for (int i = 0; i < n; i++) parents[i] = i;
        }
        public int find(int x) {
            if (parents[x] != x) {
                parents[x] = find(parents[x]);
            }
            return parents[x];
        }
        public void union(int x, int y) {
            parents[find(x)] = find(y);
        }
    }
    public int minCost(int N, int[][] connections) {
        Arrays.sort(connections, (a, b) -> a[2] - b[2]);
        DSU dsu = new DSU(N + 1);
        int res = 0;
        for (int[] con: connections) {
            int x = dsu.find(con[0]);
            int y = dsu.find(con[1]);
            if (x != y) {
                dsu.union(con[0], con[1]);
                res += con[2];
                N--;
            }
        }
        return N == 1? res : -1;   // all N nodes will be connected by n -1 edges
    }

    // Prim naive : O (V^2)
    // Prim PQ, ElogV
    // Kruskal UF ElogE

}
