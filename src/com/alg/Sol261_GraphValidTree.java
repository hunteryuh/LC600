package com.alg;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/*
You have a graph of n nodes labeled from 0 to n - 1. You are given an integer n and a list of edges where edges[i] = [ai, bi] indicates that there is an undirected edge between nodes ai and bi in the graph.

Return true if the edges of the given graph make up a valid tree, and false otherwise.



Example 1:

Input: n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
Output: true

Example 2:

Input: n = 5, edges = [[0,1],[1,2],[2,3],[1,3],[1,4]]
Output: false



Constraints:

    1 <= n <= 2000
    0 <= edges.length <= 5000
    edges[i].length == 2
    0 <= ai, bi < n
    ai != bi
    There are no self-loops or repeated edges.


 */
// 无向图 找环
public class Sol261_GraphValidTree {
    public boolean validTree(int n, int[][] edges) {
        // For the graph to be a valid tree, it must have exactly n - 1 edges. Any less, and it can't possibly be fully connected. Any more, and it has to contain cycles. Additionally, if the graph is fully connected and contains exactly n - 1 edges, it can't possibly contain a cycle, and therefore must be a tree!

        // bfs using queue, iterative
        if (edges.length != n - 1) return false;
        // build adjacency  list
        List<List<Integer>> adjacencyList = new ArrayList<>();
        // node labels are 0..n-1
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> seen = new HashSet<>();
        queue.offer(0);
        seen.add(0);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbor : adjacencyList.get(node)) {
                if (seen.contains(neighbor)) continue;
                seen.add(neighbor);
                queue.offer(neighbor);
            }
        }
        return seen.size() == n;
    }

    // Iterative Depth-First Search. Using stack.
    public boolean validTree_dfs(int n, int[][] edges) {
        if (edges.length != n - 1) return false;
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }
        Stack<Integer> stack = new Stack<>();
        Set<Integer> seen = new HashSet<>();
        stack.push(0);
        seen.add(0);

        while (!stack.isEmpty()) {
            int node = stack.pop();
            for (int neighbour : adjacencyList.get(node)) {
                if (seen.contains(neighbour)) continue;
                seen.add(neighbour);
                stack.push(neighbour);   // the node's neighbor will be popped next along the same depth
            }
        }

        return seen.size() == n;
    }

    // Recursive Depth-First Search.
    private List<List<Integer>> adjacencyList = new ArrayList<>();
    private Set<Integer> seen = new HashSet<>();

    public boolean validTree3(int n, int[][] edges) {

        if (edges.length != n - 1) return false;

        // Make the adjacency list.
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }

        // Carry out depth first search.
        dfs(0);
        // Inspect result and return the verdict.
        return seen.size() == n;
    }
    public void dfs(int node) {
        if (seen.contains(node)) return;
        seen.add(node);
        for (int neighbour : adjacencyList.get(node)) {
            dfs(neighbour);
        }
    }

    // union find
    class DSU {
        int[] parents;
        public DSU(int n) {
            parents = new int[n];
            for (int i = 0; i < n; i++) parents[i] = i;
        }
        public int find(int x) {
            if (parents[x] != x) parents[x] = find(parents[x]);
            return parents[x];
        }
        public void union(int x, int y) {
            parents[find(x)] = find(y);
        }
    }

    // Time is about O(E), E: number of edges.  operations of find and union is amortized O(1)
    public boolean isValidTree(int n, int[][] edges) {
        DSU dsu = new DSU(n);
        for (int [] edge: edges) {
            if (dsu.find(edge[0]) == dsu.find(edge[1])) return true;
            dsu.union(edge[0], edge[1]);
        }
        return n - 1 == edges.length;
    }
}
