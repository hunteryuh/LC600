package com.alg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive). The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.

You want to determine if there is a valid path that exists from vertex source to vertex destination.

Given edges and the integers n, source, and destination, return true if there is a valid path from source to destination, or false otherwise.
 */
public class Sol1971_FindIfPathExistsInGraph {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n ; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge: edges) {
            int from = edge[0];
            int to = edge[1];
            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        boolean[] visited = new boolean[n];
        return dfs(graph, visited, source, destination);
    }

    private boolean dfs(List<List<Integer>> graph, boolean[] visited, int source, int destination) {
        if (source == destination) return true;
        visited[source] = true;
        for (int neigh: graph.get(source)) {
            if (!visited[neigh] && dfs(graph, visited, neigh, destination)) {
                return true;
            }
        }
//        visited[source] = false;  // if add this, time limit exceeded
        // 注意此处没有必要，如果之前的线路来过发现不通，那么后来的线路也不通，如果加上反而会超时
        return false;
    }

    public boolean hasValidPath(int n, int[][] edges, int start, int end) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] edge: edges) {
            map.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            map.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
        }
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (cur == end) return true;
            for (int i: map.getOrDefault(cur, new ArrayList<>()) ) {
                if (!visited.contains(i)) {
                    visited.add(i);
                    queue.offer(i);
                }
            }
        }
        return false;
    }


}
