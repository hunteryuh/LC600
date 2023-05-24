package com.alg;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
You are given an array routes representing bus routes where routes[i] is a bus route that the ith bus repeats forever.

    For example, if routes[0] = [1, 5, 7], this means that the 0th bus travels in the sequence 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever.

You will start at the bus stop source (You are not on any bus initially), and you want to go to the bus stop target. You can travel between bus stops by buses only.

Return the least number of buses you must take to travel from source to target. Return -1 if it is not possible.



Example 1:

Input: routes = [[1,2,7],[3,6,7]], source = 1, target = 6
Output: 2
Explanation: The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.

Example 2:

Input: routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
Output: -1

 */
public class Sol815_BusRoutes {
    // bfs
    /*
    The first part loop on routes and record stop to routes mapping in to_route.
The second part is general bfs. Take a stop from queue and find all connected route.
The hashset seen record all visited stops and we won't check a stop for twice.
We can also use a hashset to record all visited routes, or just clear a route after visit.
     */
    public int numBusesToDestination(int[][] routes, int source, int target) {
        int n = routes.length;
        Map<Integer, HashSet<Integer>> toRoutes = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j : routes[i]) {
                if (!toRoutes.containsKey(j)) {
                    toRoutes.put(j, new HashSet<>());
                }
                toRoutes.get(j).add(i); // map of stop to routes id
            }
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{source, 0});  // {source stop, bus count}
        Set<Integer> seen = new HashSet<>();
        seen.add(source);
        boolean[] seen_routes = new boolean[n];
        while (!queue.isEmpty()) {
            int[] busWithCount = queue.poll();
            int stop = busWithCount[0];
            int bus = busWithCount[1];
            if (stop == target) return bus;
            for (int route: toRoutes.get(stop)) {
                if (seen_routes[route])  continue;
                for (int j : routes[route]) {
                    if (!seen.contains(j)) {
                        seen.add(j);
                        queue.offer(new int[]{j, bus + 1});
                    }
                }
                seen_routes[route] = true;
            }
        }
        return -1;
    }
}
