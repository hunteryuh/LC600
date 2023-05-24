package com.alg.other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
You work in an automated doll factory. Once dolls are assembled, they are sent to the shipping center via a series of autonomous delivery carts, each of which moves packages on a one-way route.

Given input that provides the (directed) steps that each cart takes as pairs, write a function that identifies all the start locations, and a collection of all of the possible ending locations for each start location.

In this diagram, starting locations are at the top and destinations are at the bottom - i.e. the graph is directed exclusively downward.

  A         E      J       Key:  [Origins]
 / \       / \      \             \
B   C     F   L      M            [Destinations]
 \ /  \  /
  K     G
       / \
      H   I
paths = [
["A", "B"],
["A", "C"],
["B", "K"],
["C", "K"],
["E", "L"],
["F", "G"],
["J", "M"],
["E", "F"],
["G", "H"],
["G", "I"],
["C", "G"]
]
 */

// https://leetcode.com/discuss/interview-question/1159164/Compass-or-OA-or-All-possible-starting-and-ending-locations
public class FindAllStartAndEndLocations {
    public Map<String, Set<String>> printAllPossiblePathsFromStartToPossibleEnds(String[][] paths) {
        Map<String, Set<String>> res = new HashMap<>();
        Set<String> allNodes = new HashSet<>();
        Set<String> allchilds = new HashSet<>();
        Map<String, List<String>> allPathsForNode = new HashMap<>();
        for (String[] path: paths) {
            String from = path[0];
            String to = path[1];
            allNodes.add(from);
            allNodes.add(to);
            allchilds.add(to);
            if (!allPathsForNode.containsKey(from)) {
                allPathsForNode.put(from, new ArrayList<>());
            }
            allPathsForNode.get(from).add(to);
        }
        List<String> starts = new ArrayList<>();
        for (String node : allNodes) {
            if (!allchilds.contains(node)) {
                starts.add(node);
            }
        }

        for (String start : starts) {
            Set<String> allEnds = getAllEnds(start, allPathsForNode);
            res.put(start, allEnds);
        }
        return res;
    }

    private Set<String> getAllEnds(String start, Map<String, List<String>> allPaths) {
        Set<String> ends = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            if (allPaths.containsKey(cur)) {
                for (String location : allPaths.get(cur)) {
                    queue.offer(location);
                }
            } else {
                ends.add(cur);
            }
        }
        return ends;
    }

    public static void main(String[] args) {
        String[][] paths = {
            {"A", "B"},
            {"A", "C"},
            {"B", "K"},
            {"C", "K"},
            {"E", "L"},
            {"F", "G"},
            {"J", "M"},
            {"E", "F"},
            {"G", "H"},
            {"G", "I"},
            {"C", "G"}
        };
        FindAllStartAndEndLocations ff = new FindAllStartAndEndLocations();

        Map<String, Set<String>> result = ff.printAllPossiblePathsFromStartToPossibleEnds(paths);
        System.out.println(result);  //{A=[H, I, K], E=[H, I, L], J=[M]}
    }
}
