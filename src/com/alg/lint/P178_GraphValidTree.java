package com.alg.lint;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
Description
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

Example
Example 1:

Input: n = 5 edges = [[0, 1], [0, 2], [0, 3], [1, 4]]
Output: true.
Example 2:

Input: n = 5 edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]]
Output: false.
https://www.lintcode.com/problem/178/
https://www.jiuzhang.com/problem/graph-valid-tree/

 */
public class P178_GraphValidTree {
    /**
     * @param n: An integer
     * @param edges: a list of undirected edges
     * @return: true if it's a valid tree, or false
     */
    public static boolean validTree(int n, int[][] edges) {
        // write your code here
        if (n == 0) {
            return false;
        }

        System.out.println(edges.length);
        if (edges.length != n - 1) {
            return false;
        }

        Map<Integer, Set<Integer>> graph = createGraph(n, edges);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        Set<Integer> set = new HashSet<>();
        set.add(0); // need to add the first node
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (Integer neighbor: graph.get(node)) {
                if (!set.contains(neighbor)) {
                    set.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        return set.size() == n;
    }

    public static boolean validTree2(int n, int[][] edges) {
        Map<Integer, Set<Integer>> neighbors = new HashMap<>();
        createGraph(n, edges);
        if (edges.length != n - 1){
            return false;
        }
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        queue.offer(0);
        while (!queue.isEmpty()){
            Integer curt = queue.poll();
            if (visited[curt]){
                return false;  //每次从queue里取出一个，如果这个已经被访问过，立马返回false. 为什么？这种情况只可能出现在环里，你在queue里放进去过这个元素好几次。
            }
            visited[curt] = true;
            for (Integer nei : neighbors.get(curt)){
                if (!visited[nei]){
                    queue.offer(nei);
                }
            }
        }
        for (boolean b : visited){
            if (!b){
                return false;  //保证每个元素都被访问过，如果有没访问过的就返回false.
            }
        }
        return true;
    }

    private static Map<Integer, Set<Integer>> createGraph(int n, int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new HashSet<>());
        }
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        return graph;
    }

    public static void main(String[] args) {
        int n = 1;
        int[][] edges = {};
        System.out.println(validTree(n, edges));
    }
}
