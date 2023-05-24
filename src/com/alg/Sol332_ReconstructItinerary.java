package com.alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/*
You are given a list of airline tickets where tickets[i] = [fromi, toi] represent the departure and the arrival airports of one flight. Reconstruct the itinerary in order and return it.

All of the tickets belong to a man who departs from "JFK", thus, the itinerary must begin with "JFK". If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.

For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
You may assume all tickets form at least one valid itinerary. You must use all the tickets once and only once.



Example 1:


Input: tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
Output: ["JFK","MUC","LHR","SFO","SJC"]
Example 2:


Input: tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"] but it is larger in lexical order.


invalid test case 81
 */
public class Sol332_ReconstructItinerary {
    // time limit exceeded
    public List<String> findItinerary(List<List<String>> tickets) {
        List<String> sol = new ArrayList<>();
        List<List<String>> res = new ArrayList<>();
        boolean[] used = new boolean[tickets.size()];
        dfs(tickets, sol, res, "JFK", used);

//        System.out.println(res);
        List<String> min = res.get(0);

        min.add(0, "JFK");
        return min;
    }

    private int compare(List<String> l1, List<String> l2) {
        int i = 0;
        while (i < l1.size()) {
            int comp = l1.get(i).compareTo(l2.get(i));
            if (comp == 0) {
                i++;
            } else {
                return comp;
            }
        }
        return 0;
    }

    private void dfs(List<List<String>> tickets, List<String> sol, List<List<String>> res, String start, boolean[] used) {
        if (sol.size() == tickets.size()) {
            if (res.isEmpty()) {
                res.add(new ArrayList<>(sol));
            } else {
                List<String> pre = res.get(0);
                if (compare(sol, pre) < 0) {
                    res.clear();
                    res.add(new ArrayList<>(sol));
                }
            }
            return;
        }
//        Set<List<String>> set = new HashSet<>();
        for (int i = 0; i < tickets.size(); i++) {
            List<String> ticket = tickets.get(i);
            if (used[i]) {
                continue;
            }
            if (ticket.get(0).equals(start)) {
                sol.add(ticket.get(1));
                used[i] = true;
                dfs(tickets, sol, res, ticket.get(1), used);
                sol.remove(sol.size()-1);
                used[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        List<List<String>> tickets = new ArrayList<>();
        tickets.add(Arrays.asList("JFK", "SFO"));
        tickets.add(Arrays.asList("SFO", "ATL"));
        tickets.add(Arrays.asList("SFO", "BOS"));
        tickets.add(Arrays.asList("SFO", "LHR"));
        tickets.add(Arrays.asList("ATL", "BOS"));
        tickets.add(Arrays.asList("BOS", "LHR"));
        tickets.add(Arrays.asList("LHR", "BOS"));
        tickets.add(Arrays.asList("BOS", "ATL"));
        Sol332_ReconstructItinerary ss = new Sol332_ReconstructItinerary();
        List<String> res = ss.findItinerary2(tickets);
        System.out.println(res);

        List<List<String>> tickets2 = new ArrayList<>();
        tickets2.add(Arrays.asList("JFK", "AAA"));
        tickets2.add(Arrays.asList("JFK", "BBB"));
        tickets2.add(Arrays.asList("BBB", "CCC"));
        tickets2.add(Arrays.asList("CCC", "JFK"));
        List<String> r2 = ss.findItinerary3(tickets2);
        System.out.println(r2);  // [JFK, BBB, CCC, JFK, AAA]
    }

    //https://leetcode.com/problems/reconstruct-itinerary/discuss/138641/Logical-Thinking-with-Clear-Java-Code
    public List<String> findItinerary2(List<List<String>> tickets) {
        List<String> result = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (List<String> ticket :tickets) {
            if (!map.containsKey(ticket.get(0))) {
                map.put(ticket.get(0), new ArrayList<>());
            }
            map.get(ticket.get(0)).add(ticket.get(1));
        }
        for (String key: map.keySet()) {
            Collections.sort(map.get(key));
        }
        System.out.println(map);
        List<String> sol = new ArrayList<>();
        backtrack(map, tickets, result, sol, "JFK");
        result.add(0, "JFK");
        return result;
    }
    private void backtrack(Map<String, List<String>> map, List<List<String>> tickets, List<String> result, List<String> sol, String start) {
        if (sol.size() == tickets.size()) {
            result.addAll(sol);
            return;
        }
        if (!map.containsKey(start) || map.get(start).isEmpty()) {
            return;
        }
        for (int i = 0; i < map.get(start).size(); i++) {
            String dest = map.get(start).get(i);
            sol.add(dest);
            map.get(start).remove(dest);
            backtrack(map, tickets, result, sol, dest);
            if (result.size() > 0) { // the first valid path is the final answer
                return;
            }
            sol.remove(sol.size() - 1);
            map.get(start).add(i, dest);
        }
    }

    // https://leetcode.com/problems/reconstruct-itinerary/discuss/348511/JAVA-DFS
    // The essential step is that starting from the fixed starting vertex (airport 'JFK'), we keep following the ordered and unused edges
    // (flights) until we get stuck at certain vertex where we have no more unvisited outgoing edges.
    // The point that we got stuck would be the last airport that we visit. And then we follow the visited vertex (airport) backwards, we would obtain the final itinerary.
    Map<String, PriorityQueue<String>> map = new HashMap<>();
    List<String> route = new ArrayList<>();

    public List<String> findItinerary3(List<List<String>> tickets) {
        for (List<String> ticket: tickets) {
            if(!map.containsKey(ticket.get(0))) {
                PriorityQueue<String> pq = new PriorityQueue<>();
                pq.add(ticket.get(1));
                map.put(ticket.get(0),pq);
            }
            else {
                map.get(ticket.get(0)).add(ticket.get(1));
            }
        }
//        for (List<String> ticket: tickets) {
//            map.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<String>()).add(ticket.get(1));
//        }
        visit("JFK");
        return route;
    }

    public void visit(String airport) {
        while (map.containsKey(airport) && !map.get(airport).isEmpty()) {
            PriorityQueue<String> dests = map.get(airport);
            String dest = dests.poll();
            visit(dest);
        }
        route.add(0,airport);
    }
}
