package com.alg.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/*
This was the question asked:
doordash
A dasher sometimes travels between cities. To avoid delays, the dasher first checks for the shortest routes. Given a map of the cities and their bidirectional roads represented by a graph of nodes and edges, determine which given roads are along any shortest path. Return an array of strings, one for each road in order, where the value is YES if the road is along a shortest path or NO if it is not.The roads or edges are named using their 1-based index within the input arrays.

Example
given a map of g_nodes = 5 nodes, the starting nodes, ending nodes and road lengths are:

Road from/to/weight
1 (1, 2, 1)
2 (2, 3, 1)
3 (3, 4, 1)
4 (4, 5, 1)
5 (5, 1, 3)
6 (1, 3, 2)
7 (5, 3, 1)

Example Input: (5, [1, 2, 3, 4, 5, 1, 5], [
2, 3, 4, 5, 1, 3, 3], [1, 1, 1, 1, 3, 2, 1])
The traveller must travel from city 1 to city g_nodes, so from node 1 to node 5 in this case.
The shortest path is 3 units long and there are three paths of that length: 1 → 5, 1 → 2 → 3 → 5, and 1 → 3 → 5.
Return an array of strings, one for each road in order, where the value is YES if a road is along a shortest path or NO if it is not.
In this case, the resulting array is ['YES', 'YES', 'NO', 'NO', 'YES', 'YES', 'YES'].

The third and fourth roads connect nodes (3, 4) and (4, 5) respectively. They are not on a shortest path, i.e. one with a length of 3 in this case.
 */
public class DD_IsRoadOnShortestPath {
// https://leetcode.com/discuss/interview-question/1353434/Doordash-Phone-Screen-or-Senior-Software-Engineer-or-July-2021
// The approach is similar to Dijkstra's algorithm, with a slight modification.
    public static void main(String[] args) {

        int[] src = new int[]{1, 2, 3, 4, 5, 1, 5};
        int[] dest = new int[]{2, 3, 4, 5, 1, 3, 3};
        int[] weights = new int[]{1, 1, 1, 1, 3, 2, 1};

        DD_IsRoadOnShortestPath dd = new DD_IsRoadOnShortestPath();

        int[][] routes = new int[][] {{1, 2, 1}, {2, 3, 1}, {3, 4, 1}, {4, 5, 1}, {5, 1, 3}, {1, 3, 2}, {5, 3, 1}};
//        boolean[] res = dd.getAllShortestPaths(routes, 1, 5, 5);
        dd.getAllShortestPath(5, 1, 5, src, dest, weights);
    }

    private String[] getAllShortestPath(int numNodes, int source, int des, int[] startNodes, int[] destNodes, int[] weights) {
        int n = numNodes + 1;
        // build the map
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        for (int i = 1; i < n; i++) {
            graph.put(i, new HashMap<>());
        }
        for (int i = 0; i < weights.length; i++) {
            int u = startNodes[i];
            int v = destNodes[i];
            graph.get(u).put(v, weights[i]);
            graph.get(v).put(u, weights[i]);
        }

        // The parents array maintains a list of parents who are part of the shortest way to reach the current node from the S
        // For ex: (1,2,3), (2,3,1), (1,3,4), (4,3,5) and S = 1. The parents[3] = {1,2} as we can reach node 3 with minDistance of 4 from S
        List<List<Integer>> parents = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            parents.add(new ArrayList<>());
        }
        // sort by weight/ cost
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        int[] distance = new int[n];
//        int[] visited = new int[n];
//        Arrays.fill(visited, -1);
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;  // initialize source distance as 0
        pq.add(new int[]{source, 0}); // { start, cost}, dijikstra
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int currDist = top[1];
            int currNode = top[0];
            for (int neigh : graph.get(currNode).keySet()) {
                int extraDist = graph.get(currNode).get(neigh);
                if (currDist + extraDist  < distance[neigh]) {
                    distance[neigh] = currDist + extraDist;
                    pq.offer(new int[]{neigh, extraDist+currDist});

                    List<Integer> li = new ArrayList<>();
                    li.add(currNode);
                    parents.set(neigh, li);

                } else if (currDist + extraDist  == distance[neigh]){
                    parents.get(neigh).add(currNode);
                }
            }
        }
        for (int i = 0; i< n; i++) {
             System.out.println(">>" + distance[i]);
             System.out.println(" Parent of  "+ i +" is "+parents.get(i));
        }

        Set<String> roads = new HashSet<>();
        backtrack(parents, des, roads);
        // Constructing the result array
        System.out.println(roads);
        String[] result = new String[startNodes.length];
        for (int i = 0; i < startNodes.length; i++) {
            String r = startNodes[i] + "-" + destNodes[i];
            if (roads.contains(r) /*|| roads.contains(new StringBuilder(r).reverse().toString())*/) {
                result[i] = "YES";
            } else {
                result[i] = "NO";
            }
        }
        System.out.println(Arrays.toString(result));
        return result;

    }

    // find all shortest roads to destination: curr
    private static void backtrack(List<List<Integer>> parents, int curr, Set<String> roads) {

        if (parents.get(curr) == null || parents.get(curr).size() == 0){
            return;
        }

        for(int parent : parents.get(curr)){
            roads.add(parent + "-" + curr);
            roads.add(curr + "-" + parent);  // reverse direction of the above road
            backtrack(parents, parent, roads);
        }
    }

    public boolean[] getAllShortestPaths(int[][] routes, int start, int end, int n) {
        // dijastra get all shortest in the map from start
        // (src, [dst, cost])
        n = n+1;
        Map<Integer, List<int[]>> routeMap = new HashMap<>();
        int[] minDist = new int[n];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        PriorityQueue<CityNode> q = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        q.add(new CityNode(0, start));
        // store parents for cur node when min cost, for future backtrack getting all routes
        List<Integer>[] parents = new List[n];
        Map<String, Integer> roadMap = new HashMap<>();
        boolean[] res = new boolean[routes.length];
        for (int i = 0; i < routes.length; i++) {
            routeMap.putIfAbsent(routes[i][0], new ArrayList<>());
            routeMap.putIfAbsent(routes[i][1], new ArrayList<>());
            routeMap.get(routes[i][0]).add(new int[]{routes[i][1], routes[i][2]});
            routeMap.get(routes[i][1]).add(new int[]{routes[i][0], routes[i][2]});
            roadMap.put(routes[i][0]+"-"+routes[i][1], i);
            roadMap.put(routes[i][1]+"-"+routes[i][0], i);
        }
        System.out.println(roadMap);
        while (!q.isEmpty()) {
            CityNode node = q.poll();
            if (routeMap.containsKey(node.id)) {
                for (int[] dst: routeMap.get(node.id)) {
                    int newCost = node.cost + dst[1];
                    if (newCost < minDist[dst[0]]) {
                        minDist[dst[0]] = newCost;
                        q.add(new CityNode(newCost, dst[0]));
                        parents[dst[0]] = new ArrayList<>(Arrays.asList(node.id));
                    } else if (newCost == minDist[dst[0]]) {
                        parents[dst[0]].add(node.id);
                    }
                }
            }
        }
        traversalBack(parents, end, res, roadMap);
        System.out.println(Arrays.toString(res));
        return res;
    }

    private void traversalBack(List<Integer>[] parents, int end, boolean[] res, Map<String, Integer> roadMap) {
        if (parents[end] == null || parents[end].size() == 0) return;
        for (int parent: parents[end]) {
            if (roadMap.containsKey(parent+"-"+end) && !res[roadMap.get(parent+"-"+end)]) {
                res[roadMap.get(parent+"-"+end)] = true;
                traversalBack(parents, parent, res, roadMap);
            }
        }
    }

    class CityNode {
        int cost;
        int id;
        CityNode (int cost, int id) {
            this.cost = cost;
            this.id = id;
        }
    }
}
