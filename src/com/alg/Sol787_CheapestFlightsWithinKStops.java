package com.alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

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
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0] - b[0]);
        pq.add(new int[]{0, src, k + 1});  // {cost, city, step}
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

}
