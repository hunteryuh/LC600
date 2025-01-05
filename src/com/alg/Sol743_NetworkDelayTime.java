package com.alg;

import java.util.*;

/*
You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node, and wi is the time it takes for a signal to travel from source to target.

We will send a signal from a given node k. Return the minimum time it takes for all the n nodes to receive the signal. If it is impossible for all the n nodes to receive the signal, return -1.



Example 1:

Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
Output: 2

Example 2:

Input: times = [[1,2,1]], n = 2, k = 1
Output: 1

Example 3:

Input: times = [[1,2,1]], n = 2, k = 2
Output: -1



Constraints:

    1 <= k <= n <= 100
    1 <= times.length <= 6000
    times[i].length == 3
    1 <= ui, vi <= n
    ui != vi
    0 <= wi <= 100
    All the pairs (ui, vi) are unique. (i.e., no multiple edges.)


 */
public class Sol743_NetworkDelayTime {
    //https://leetcode.com/problems/network-delay-time/editorial/
    // dijikstra

    // Here N is the number of nodes and E is the number of total edges in the given network.
    //    Time complexity: O(N + E log N), E is at most N*(N-1) for dense graph
    Map<Integer, List<int[]>> adj = new HashMap<>();
    public int networkDelayTime(int[][] times, int n, int k) {
        // build adjacent list map
        for (int[] edge: times) {
            adj.computeIfAbsent(edge[0], x-> new ArrayList<>()).add(new int[]{edge[1], edge[2]});
        }
        Map<Integer, Integer> distanceMap = new HashMap<>(); // distance map or array, here used as visited set
        //Use PriorityQueue to get the node with the shortest absolute distance
        //and calculate the absolute distance of its neighbor nodes.
        // pq of int[]{source, distance}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{k, 0});
        int res = 0;
        // time ELogv:
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int node = cur[0];
            int distance = cur[1];
            // ignore processed node
            if (distanceMap.containsKey(node) /*&& distanceMap.get(node) < distance*/) {
                // first time when enqueue, it has the shorteset path due to order in pq
                continue;
            }
            distanceMap.put(node, distance);
            res = Math.max(res, distance);
            for (int[] edge: adj.getOrDefault(node, new ArrayList<>())) {
                int neighbor = edge[0];
                int neighbor_distance = edge[1];
//                if (distance + neighbor_distance < distanceMap.get(neighbor)) {
                if (!distanceMap.containsKey(neighbor)) { // if contains, it means visited and with shortest path
                    pq.offer(new int[]{neighbor, distance + neighbor_distance});
                }
            }
        }
        if (distanceMap.size() != n) return -1;
        return res;
    }
}
