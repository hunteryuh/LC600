package com.alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

//
// LC 121, best time to buy and sell stocks
//
public class Main {

    public static void main(String[] args) {

        String paths = "/abc/def";
        String[] res = paths.split("/");
        System.out.println(res.length);
        System.out.println(Arrays.toString(res));


        int[] src = new int[]{1, 2, 3, 4, 5, 1, 5};
        int[] dest = new int[]{2, 3, 4, 5, 1, 3, 3};
        int[] weights = new int[]{1, 1, 1, 1, 3, 2, 1};

        getAllShortestPath(5, 1, 5, src, dest, weights);
    }


    private static void getAllShortestPath( int numNodes, int src, int dstn, int[] startNodes,
                                            int[] destNodes, int[] weights) {

        int N = numNodes+1;

        Map<Integer, Map<Integer, Integer>> adjMap = new HashMap<>();
        for(int i =1; i<N; i++){
            adjMap.put(i, new HashMap<>());
        }

        for(int i = 0 ; i<weights.length; i++){
            int u = startNodes[i];
            int v = destNodes[i];
            adjMap.get(u).put(v, weights[i]);
            adjMap.get(v).put(u, weights[i]);
        }
        List<List<Integer>> parents = new ArrayList<>();
        for(int i = 0; i<N; i++){
            parents.add(new ArrayList<>());
        }

        // based on weight
        PriorityQueue<int[]> pq = new PriorityQueue<>( (a, b) -> {
            return a[1] - b[1];
        });


        int[] distance = new int[N];
//        Arrays.fill(visited, -1);
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[src] = 0;
        //parents.get(src).add();
        pq.add(new int[]{src, 0});

        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int currDist = top[1];
            int currNode = top[0];
            for(int neigh : adjMap.get(currNode).keySet()) {
                int extraDist = adjMap.get(currNode).get(neigh);
                if (currDist + extraDist  < distance[neigh]) {
                    distance[neigh] = currDist + extraDist;
                    pq.offer(new int[]{neigh, extraDist+currDist});

                    List<Integer> li = new ArrayList<>();
                    li.add(currNode);
                    parents.set(neigh, li);

                } else if (currDist + extraDist  == distance[neigh]){
                    parents.get(neigh).add(currNode); // {Key: (to) next stap, value: source (from)}
                }
            }
        }
        for(int i = 0; i<N; i++) {
             System.out.println(">>"+distance[i]);
             System.out.println(" Parent of  "+i +" is"+parents.get(i));
        }

        Set<String> roads = new HashSet<>();
        backtrack(parents, dstn, roads);

        // Constructing the result array
        String[] result = new String[startNodes.length];
        for(int i=0; i<startNodes.length; i++) {
            String r = startNodes[i] + "-" + destNodes[i];
            if(roads.contains(r)){
                result[i] = "YES";
            } else {
                result[i] = "NO";
            }
        }

        System.out.println(Arrays.toString(result));

    }

    // parents map stores key as current/to location, value is from location
    private static void backtrack(List<List<Integer>> parents, int curr, Set<String> roads) {
        if (parents.get(curr) == null || parents.get(curr).size() == 0){
            return;
        }
        for(int parent : parents.get(curr)){
            roads.add(parent + "-" + curr);
            roads.add(curr + "-" + parent);
            backtrack(parents, parent, roads);
        }
    }
}
