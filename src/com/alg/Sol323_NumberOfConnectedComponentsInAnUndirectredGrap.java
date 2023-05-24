package com.alg;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/*
You have a graph of n nodes. You are given an integer n and an array edges where edges[i] = [ai, bi] indicates that there is an edge between ai and bi in the graph.

Return the number of connected components in the graph.



Example 1:

Input: n = 5, edges = [[0,1],[1,2],[3,4]]
Output: 2

Example 2:

Input: n = 5, edges = [[0,1],[1,2],[2,3],[3,4]]
Output: 1



Constraints:

    1 <= n <= 2000
    1 <= edges.length <= 5000
    edges[i].length == 2
    0 <= ai <= bi < n
    ai != bi
    There are no repeated edges.


 */
public class Sol323_NumberOfConnectedComponentsInAnUndirectredGrap {
    // bfs
    public int countComponents(int n, int[][] edges) {
        int count = 0;
        List<List<Integer>> ajlists = new ArrayList<>();
        for (int i = 0; i < n ; i++) {
            ajlists.add(new ArrayList<>());
        }
        for (int[] edge: edges) {
            ajlists.get(edge[0]).add(edge[1]);
            ajlists.get(edge[1]).add(edge[0]);
        }

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < n ; i++) {
            if (visited.contains(i)) continue;
            queue.offer(i);
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                for (int neighbor: ajlists.get(cur)) {
                    if (visited.contains(neighbor)) continue;
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
            count++;
        }
        return count;
    }
    // dfs
    public int countComponents_dfs(int n, int[][] edges) {
        int components = 0;
        int[] visited = new int[n];

        List<List<Integer>> ajlists = new ArrayList<>();
        for (int i = 0; i < n ; i++) {
            ajlists.add(new ArrayList<>());
        }
        for (int[] edge: edges) {
            ajlists.get(edge[0]).add(edge[1]);
            ajlists.get(edge[1]).add(edge[0]);
        }

        for (int i = 0; i < n; i++) {
            if (visited[i] == 0) {
                components++;
                dfs(ajlists, i, visited);
            }
        }
        return components;
    }
    private void dfs(List<List<Integer>> lists, int node, int[] visited) {
        visited[node] = 1;
        for (int neighbor: lists.get(node)) {
            if (visited[neighbor] == 0) {
                dfs(lists, neighbor, visited);
            }
        }
    }

    public static void main(String[] args) {
        Sol323_NumberOfConnectedComponentsInAnUndirectredGrap ss = new Sol323_NumberOfConnectedComponentsInAnUndirectredGrap();
        int n = 5;
        int[][] edges = {{3,4}};
        int x = ss.countComponents_UF(n, edges);
        System.out.println(x);
    }

    // union find  unionfind  dsu disjoint set union
    public int countComponents_UF(int n, int[][] edges) {
        int[] parent = new int[n];
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < n; i++){
            parent[i] = i;
        }

        for(int[] edge : edges){
            union(parent, edge[0],edge[1]);
        }

        for(int i : parent)
            set.add(find(parent,i));

        return set.size();
    }

    // 1. find function
    public int find(int[] parent, int i){
        if(parent[i] == i)
            return i;
        return find(parent,parent[i]);
    }

    // 2. union function
    public void union(int[] parent, int i, int j){
        int x = find(parent,i);
        int y = find(parent,j);
        if(x != y){
            parent[x] = y;
        }
    }
}
