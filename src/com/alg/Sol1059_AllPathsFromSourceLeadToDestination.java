package com.alg;

import java.util.ArrayList;
import java.util.List;

public class Sol1059_AllPathsFromSourceLeadToDestination {
    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        // build the graph with adjacency list
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge: edges) {
            int from = edge[0];
            int to = edge[1];
            graph.get(from).add(to);
        }
        boolean[] visited = new boolean[n];
        return dfs(graph, visited, source, destination);
//        return dfs2(graph, visited, source, destination);
    }

    private boolean dfs(List<List<Integer>> graph, boolean[] visited, int source, int dest) {
//        if (graph.get(source).isEmpty() && source == dest) {
//            return true;
//        }  // wrong as we need to return false immediately if source is not the target at leaf
        if (graph.get(source).isEmpty()) {
            return source == dest;
        }

        for (int neighbor : graph.get(source)) {
            if (visited[neighbor]) return false;   // this means there is a loop in the path
            visited[source] = true;
            if (!dfs(graph, visited, neighbor, dest)) {
                return false;
            }
            visited[source] = false;
        }

        return true;
    }

    // another way to write dfs
    // https://leetcode.com/problems/all-paths-from-source-lead-to-destination/discuss/1421577/Java-or-DFS-or-Cycle-detection-or-Modified-traversal
    private boolean dfs2(List<List<Integer>> graph, boolean[] visited, int source, int dest) {
        visited[source] = true;
        if (graph.get(source).isEmpty() && source != dest) {
            return false;
        }
        for (int neigh : graph.get(source)) {
            if (visited[neigh]) return false;
            if (!dfs2(graph, visited, neigh, dest)) {
                return false;
            }
        }
        visited[source] = false;
        return true;
    }
}
