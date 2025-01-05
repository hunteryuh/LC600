package com.alg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
In this problem, a tree is an undirected graph that is connected and has no cycles.

You are given a graph that started as a tree with n nodes labeled from 1 to n, with one additional edge added. The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed. The graph is represented as an array edges of length n where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the graph.

Return an edge that can be removed so that the resulting graph is a tree of n nodes.
If there are multiple answers, return the answer that occurs last in the input.

Example 1:


Input: edges = [[1,2],[1,3],[2,3]]
Output: [2,3]
Example 2:


Input: edges = [[1,2],[2,3],[3,4],[1,4],[1,5]]
Output: [1,4]


Constraints:

n == edges.length
3 <= n <= 1000
edges[i].length == 2
1 <= ai < bi <= edges.length
ai != bi
There are no repeated edges.
The given graph is connected.
 */
public class Sol684_RedundantConnection {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length + 1;
        DSU dsu = new DSU(n);
        for (int i = 0; i < n; i++) {
            dsu.parents[i] = i;
        }
        for (int[] edge: edges) {
            if (dsu.isSame(edge[0], edge[1])) {
                return edge;
            } else {
                dsu.union(edge[0], edge[1]);
            }
        }
        return new int[2];

    }

    static class DSU {
        int[] parents;
        public DSU(int n) {
            this.parents = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
        }

        private int find(int u) {
            if (parents[u] != u) {  // if , not while
                parents[u] = find(parents[u]);  // u = parent[u];
            }
            return parents[u];
        }
        private boolean isSame(int u, int v) {
            return find(u) == find(v);
        }

        private void union(int u, int v) {
            parents[find(u)] = find(v);
//            if (find(u) == find(v)) {
//                return;
//            } else {
//                parents[find(u)] = find(v);
//            }
        }

    }


    // method 2: dfs
    // https://leetcode.com/problems/redundant-connection/editorial/
    // similar to https://leetcode.com/problems/redundant-connection/solutions/2052496/java-dfs-solution-with-explain/
    public int[] findRedundantCon(int[][] edges) {
//        Map<Integer, List<Integer>> map = new HashMap<>();
        List<List<Integer>> graph = new ArrayList<>();
        int n = edges.length;
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        boolean[] visited = new boolean[n];
        for (int[] edge: edges) {
            int u = edge[0] - 1;
            int v = edge[1] - 1;
            if (!graph.get(u).isEmpty() && !graph.get(v).isEmpty()) {
                // Actually a redundant edge means after we add this edge, the graph will contain a cycle.
                if (dfs(u, v, graph, visited)) {
                    return edge;
                }
            }
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        return null;
    }

    private boolean dfs(int start, int target, List<List<Integer>> graph, boolean[] visited) {
        if (start == target) return true;
        visited[start] = true;
        for (int index: graph.get(start)) {
            if (!visited[index]) {
                if (dfs(index, target, graph, visited)) {
                    return true;
                }
            }
        }
        visited[start] = false; // need backtracking
        return false;
    }


    //
    boolean[] visited;

    public int[] findRedundantConnection2(int[][] edges) {
        HashMap<Integer, List<Integer>> hashMap = new HashMap<>();
        for(int i = 0; i < edges.length; i++){
            hashMap.put(i + 1, new ArrayList<>());
        }

        int[] res = new int[2];
        for(int i = 0; i < edges.length; i++){
            int[] edge = edges[i];
            visited = new boolean[edges.length + 1];
            if(!hashMap.get(edge[0]).isEmpty() && !hashMap.get(edge[1]).isEmpty()
                    && dfs(edge[0], edge[1], hashMap)){
                return edge;
            }
            hashMap.get(edge[0]).add(edge[1]);
            hashMap.get(edge[1]).add(edge[0]);
        }
        return res;
    }

    public boolean dfs(int src, int target, HashMap<Integer, List<Integer>> hashMap){
        if(src == target){
            return true;
        }
        visited[src] = true;

        for(Integer next: hashMap.get(src)){
            if (!visited[next]) {
                if (dfs(next, target, hashMap)){
                    return true;
                }
            }
        }

        return false;
    }
}
