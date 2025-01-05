package com.alg;

import java.util.*;

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
    // bfs https://leetcode.com/problems/bus-routes/solutions/122771/c-java-python-bfs-solution/
    /*
    The first part loop on routes and record stop to routes mapping in to_route.
The second part is general bfs. Take a stop from queue and find all connected route.
The hashset seen record all visited stops and we won't check a stop for twice.
We can also use a hashset to record all visited routes, or just clear a route after visit.
     */
    // https://leetcode.com/problems/bus-routes/solutions/122771/c-java-python-bfs-solution/ Self_Learner

    public int numBusesToDestination(int[][] routes, int source, int target) {
        int n = routes.length;
        Map<Integer, HashSet<Integer>> toRoutes = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j : routes[i]) {
                if (!toRoutes.containsKey(j)) {
                    toRoutes.put(j, new HashSet<>());
                }
                toRoutes.get(j).add(i); // map of bus stop to route id
            }
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{source, 0});  // {source stop, bus count}
        Set<Integer> stopVisited = new HashSet<>();
        stopVisited.add(source);
        Set<Integer> routeVisited = new HashSet<>();
//        boolean[] seen_routes = new boolean[n];
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] busWithCount = queue.poll();
                int stop = busWithCount[0];
                int bus = busWithCount[1];
                if (stop == target) return bus;
                for (int route : toRoutes.get(stop)) {
                    if (routeVisited.contains(route)) continue;
//                if (seen_routes[route])  continue;
                    routeVisited.add(route);
                    for (int nestStop : routes[route]) {
                        if (!stopVisited.contains(nestStop)) {
                            stopVisited.add(nestStop);
                            queue.offer(new int[]{nestStop, bus + 1});
                        }
                    }
//                seen_routes[route] = true;
                }
            }
        }
        return -1;
    }

    // using count variable not in the state
    public int numBusesToDestination11(int[][] routes, int S, int T) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int k = 0; k < routes.length; k++) {
            int[] route = routes[k];
            int n = route.length;
            for (int i = 0; i < n; i++) {
                int stop = route[i];
                if (!graph.containsKey(stop)) {
                    graph.put(stop, new ArrayList<>());
                }
                graph.get(stop).add(k);
            }
        }

        if (!graph.containsKey(S) || !graph.containsKey(T)) return -1;
        if (S == T) return 0;

        Queue<Integer> q = new LinkedList<>();
        Set<Integer> busTaken = new HashSet<>();
        Set<Integer> stopVisited = new HashSet<>();

        q.add(S);

        int cnt = 0;

        while (!q.isEmpty()) {
            cnt++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int curStop = q.poll();
                for (int bus : graph.get(curStop)) {
                    if (busTaken.contains(bus)) continue; //not take the same bus again...
                    busTaken.add(bus);
                    for (int nextStop : routes[bus]) {
                        if (stopVisited.contains(nextStop)) { continue; } //not visited the same stop...
                        if (nextStop == T) { return cnt; }
                        q.add(nextStop);
                        stopVisited.add(nextStop);
                    }
                }
            }
        }
        return -1;
    }

    public int numBusesToDestination2(int[][] routes, int source, int target) {
//        if (source == target) return 0;// no need to take any route/bus at all if at the same location
        int n = routes.length;
        Map<Integer, Set<Integer>> stopToRoutes = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int stop : routes[i]) {
                stopToRoutes.putIfAbsent(stop, new HashSet<>());
                stopToRoutes.get(stop).add(i);
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        Set<Integer> visitedStops = new HashSet<>();
        Set<Integer> visitedRoute = new HashSet<>();
        visitedStops.add(source);
        queue.offer(new int[]{source, 0});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int stop = cur[0];
            int count = cur[1];
            if (stop == target) return count;
            for (int route : stopToRoutes.get(stop)) {
                if (!visitedRoute.contains(route)) {
                    visitedRoute.add(route);
                    for (int j : routes[route]) {
                        if (!visitedStops.contains(j)) {
                            visitedStops.add(j);
                            queue.offer(new int[]{j, count + 1});
                        }
                    }
                }
            }
        }
        return -1;
    }


    // bfs method 2:
    // https://leetcode.com/problems/bus-routes/editorial/
    public int numBusesToDestination3(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }

        HashMap<Integer, ArrayList<Integer>> adjList = new HashMap<>();
        // Create a map from the bus stop to all the routes that include this stop.
        for (int r = 0; r < routes.length; r++) {
            for (int stop : routes[r]) {
                // Add all the routes that have this stop.
                ArrayList<Integer> route = adjList.getOrDefault(stop, new ArrayList<>());
                route.add(r);
                adjList.put(stop, route);
            }
        }

        Queue<Integer> q = new LinkedList<>();
        Set<Integer> vis = new HashSet<Integer>(routes.length);
        // Insert all the routes in the queue that have the source stop.
        for (int route : adjList.get(source)) {
            q.add(route);
            vis.add(route);
        }

        int busCount = 1;
        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                int route = q.remove();
                // Iterate over the stops in the current route.
                for (int stop: routes[route]) {
                    // Return the current count if the target is found.
                    if (stop == target) {
                        return busCount;
                    }

                    // Iterate over the next possible routes from the current stop.
                    for (int nextRoute : adjList.get(stop)) {
                        if (!vis.contains(nextRoute)) {
                            vis.add(nextRoute);
                            q.add(nextRoute);
                        }
                    }
                }
            }
            busCount++;
        }
        return -1;
    }

}
