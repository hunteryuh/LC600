package com.alg;

import java.util.*;

/*
A tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.

Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi] indicates that there is an undirected edge between the two nodes ai and bi in the tree, you can choose any node of the tree as the root. When you select a node x as the root, the result tree has height h. Among all possible rooted trees, those with minimum height (i.e. min(h))  are called minimum height trees (MHTs).

Return a list of all MHTs' root labels. You can return the answer in any order.

The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.



Example 1:

Input: n = 4, edges = [[1,0],[1,2],[1,3]]
Output: [1]
Explanation: As shown, the height of the tree is 1 when the root is the node with label 1 which is the only MHT.

Example 2:

Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
Output: [3,4]



Constraints:

    1 <= n <= 2 * 104
    edges.length == n - 1
    0 <= ai, bi < n
    ai != bi
    All the pairs (ai, bi) are distinct.
    The given input is guaranteed to be a tree and there will be no repeated edges.


 */
public class Sol310_MinimumHeightTrees {
    // https://leetcode.com/problems/minimum-height-trees/editorial/
    // time: O(V), V number of nodes in the graph
    // space: O(V)
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n < 2) {
            return Arrays.asList(0); // or Collections.singletonList(0);
        }
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] indegrees = new int[n];
        for (int[] edge : edges) {
            int n1 = edge[0];
            int n2 = edge[1];
            graph.computeIfAbsent(n1, x -> new ArrayList<>()).add(n2);
            graph.computeIfAbsent(n2, x -> new ArrayList<>()).add(n1);
            indegrees[n1]++;
            indegrees[n2]++;
        }
        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (indegrees[i] == 1) {
                leaves.add(i);
            }
        }
        while (n > 2) {
            List<Integer> innerLeaves = new ArrayList<>();
            for (int leave: leaves) {
                for (int parent : graph.get(leave)) {
                    indegrees[parent]--;
                    if (indegrees[parent] == 1) {
                        innerLeaves.add(parent);
                    }
                }
            }
            n = n - leaves.size();
            leaves = innerLeaves;
        }
        return leaves;
    }

    // https://leetcode.com/problems/minimum-height-trees/solutions/5060930/full-explanation-bfs-remove-leaf-nodes/
    public List<Integer> findMinHeightTrees2(int n, int[][] edges) {
        if(n == 1) return Collections.singletonList(0);

        int indegree[] = new int[n];
        Map<Integer, List<Integer>> map = new HashMap();
        for (int[] edge: edges) {
            indegree[edge[0]]++;
            indegree[edge[1]]++;
            map.putIfAbsent(edge[0], new ArrayList());
            map.putIfAbsent(edge[1], new ArrayList());
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }

        Queue<Integer> queue = new LinkedList();
        for(int i=0;i<indegree.length;i++) {
            if(indegree[i] == 1) {
                queue.offer(i);
            }
        }

        int remainingNodes = n;
        while (remainingNodes > 2) {
            int size = queue.size();
            remainingNodes -= size;
            for(int i=0;i < size;i++) {
                int cur = queue.poll();
                for(int adj: map.get(cur)) {
                    if(--indegree[adj] == 1) {
                        queue.add(adj);
                    }
                }
            }
        }

//        List<Integer> list = new ArrayList();
//        list.addAll(queue);
//        return list;
        return new ArrayList<>(queue);
    }
}
