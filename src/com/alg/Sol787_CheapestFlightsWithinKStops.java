package com.alg;

import java.util.*;

/*
    There are n cities connected by some number of flights. You are given an array flights where flights[i] = [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.

    You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. If there is no such route, return -1.



    Example 1:

    Input: n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
    Output: 700
    Explanation:
    The graph is shown above.
    The optimal path with at most 1 stop from city 0 to 3 is marked in red and has cost 100 + 600 = 700.
    Note that the path through cities [0,1,2,3] is cheaper but is invalid because it uses 2 stops.

    Example 2:

    Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
    Output: 200
    Explanation:
    The graph is shown above.
    The optimal path with at most 1 stop from city 0 to 2 is marked in red and has cost 100 + 100 = 200.

    Example 3:

    Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0
    Output: 500
    Explanation:
    The graph is shown above.
    The optimal path with no stops from city 0 to 2 is marked in red and has cost 500.



    Constraints:

    1 <= n <= 100
    0 <= flights.length <= (n * (n - 1) / 2)
    flights[i].length == 3
    0 <= fromi, toi < n
    fromi != toi
        1 <= pricei <= 104
        There will not be any multiple flights between two cities.
        0 <= src, dst, k < n
    src != dst

*/
// https://leetcode.com/problems/cheapest-flights-within-k-stops/solution/
public class Sol787_CheapestFlightsWithinKStops {
    // Bellman-Ford
    // T(n) = O(E * K), where E is the length of flights
    final int MAX = 100000000;
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] cost = new int[n];
        Arrays.fill(cost, MAX);
        cost[src] = 0;
        int res = MAX;
        // we need to find the cheapest flight route with at most K stops in between. That translates to K + 1 edges at most.
        for (int i = 0; i <= k; i++) {
            int[] cur = Arrays.copyOf(cost, n);
            for (int[] flight: flights) {
                cur[flight[1]] = Math.min(cur[flight[1]], cost[flight[0]] + flight[2]);
            }
            res = Math.min(res, cur[dst]);
            cost = cur;
        }
        return res == MAX ? -1 : res;
    }

    // https://leetcode.com/problems/cheapest-flights-within-k-stops/discuss/361711/Java-DFSBFSBellman-Ford-Dijkstra's
    // Dijkstra
    // 用pq来找最低路径值，k步之内，谁先到终点谁就是最优解
    // T(n) = O (E + nlogn), where E is the total number of flights
    public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int[] flight : flights) {
            map.putIfAbsent(flight[0], new ArrayList<>());
            map.get(flight[0]).add(new int[]{flight[1], flight[2]}); // src -> dst, price
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0] - b[0]); //[0]: cost
        pq.add(new int[]{0, src, k + 1});  // {cost, source_city, number of stops from the src}
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int price = top[0];
            int city = top[1];
            int stops = top[2];
            if (city == dst) return price;
            if (stops > 0) {
                if (map.containsKey(city)) {
                    for (int[] next: map.get(city)) {
                        pq.add(new int[]{price + next[1], next[0], stops - 1});
                    }
                }
            }
        }
        return -1;
    }


    // dijikstra 2
    //  https://leetcode.com/problems/cheapest-flights-within-k-stops/editorial/
    public int findCheapestPrice3(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<int[]>> adj = new HashMap<>();
        for (int[] i : flights)
            adj.computeIfAbsent(i[0], value -> new ArrayList<>()).add(new int[] { i[1], i[2] });

        int[] stops = new int[n];
        Arrays.fill(stops, Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        // {dist_from_src_node, node, number_of_stops_from_src_node}
        pq.offer(new int[] { 0, src, 0 });

        while (!pq.isEmpty()) {
            int[] temp = pq.poll();
            int dist = temp[0];
            int node = temp[1];
            int steps = temp[2];
            // We have already encountered a path with a lower cost and fewer stops,
            // or the number of stops exceeds the limit.
            if (steps > stops[node] || steps > k + 1)
                continue;
            stops[node] = steps;
            if (node == dst)
                return dist;
            if (!adj.containsKey(node))
                continue;
            for (int[] a : adj.get(node)) {
                pq.offer(new int[] { dist + a[1], a[0], steps + 1 });
            }
        }
        return -1;
    }

    // regular bfs, time limit exceeded
    public int findCheapestPrice4(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer,List<int[]>> map=new HashMap<>();
        for(int[] i:flights) {
            map.putIfAbsent(i[0],new ArrayList<>());
            map.get(i[0]).add(new int[]{i[1],i[2]});
        }
        int step=0;
        Queue<int[]> q=new LinkedList<>();
        q.offer(new int[]{src,0});
        int ans= Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            int size=q.size();
            for(int i=0;i<size;i++) {
                int[] curr=q.poll();
                if(curr[0] == dst)
                    ans=Math.min(ans,curr[1]);
                if(!map.containsKey(curr[0]))
                    continue;
                for(int[] f:map.get(curr[0])) {
                    if (curr[1]+f[1]>ans)      //Pruning
                        continue;
                    q.offer(new int[]{f[0],curr[1]+f[1]});
                }
            }
            if(step++ > K)
                break;
        }
        return ans==Integer.MAX_VALUE?-1:ans;
    }

    // bfs passed, with distance map/array
    // https://leetcode.com/problems/cheapest-flights-within-k-stops/editorial/ similar approach 1
    public int findCheapestPrice_6(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<int[]>> map = new HashMap<>();
        Map<Integer, Integer> distance = new HashMap<>();
        for (int[] flight : flights) {
            map.putIfAbsent(flight[0],new ArrayList<>());
            map.get(flight[0]).add(new int[]{flight[1], flight[2]});
            distance.put(flight[1], Integer.MAX_VALUE);
        }
        // add queue with [city , cost]
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{src, 0});

        distance.put(src, 0);
        int stop = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int city = cur[0];
                int cost = cur[1];
                if (!map.containsKey(city)) continue;
                for (int[] nei: map.get(city)) {
                    if (cost + nei[1] < distance.get(nei[0])) {
                        distance.put(nei[0], cost + nei[1]);
                        queue.offer(new int[]{nei[0], cost + nei[1]});
                    }
                }

            }

            stop++;
            if (stop > k) {
                break;
            }
        }
        if (distance.containsKey(dst) && distance.get(dst) != Integer.MAX_VALUE) {
            return distance.get(dst);
        }
        return -1;
    }

}
