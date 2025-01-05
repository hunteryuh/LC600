package com.alg.other;

import java.util.*;
// interviewed 12/5/2024
public class Aibnb_SkiMaxScore {
    static class Edge {
        int to;
        int travelTime;

        Edge(int to, int travelTime) {
            this.to = to;
            this.travelTime = travelTime;
        }
    }

    static class Result {
        int maxScore;
        List<Integer> path;

        Result(int maxScore, List<Integer> path) {
            this.maxScore = maxScore;
            this.path = new ArrayList<>(path);
        }
    }

    public static void main(String[] args) {
        // Input: List of routes [start_cp, end_cp, time_to_travel]
        List<int[]> routes = Arrays.asList(
                new int[]{0, 1, 5}, new int[]{0, 2, 6}, new int[]{0, 3, 10},
                new int[]{1, 4, 4}, new int[]{2, 4, 5}, new int[]{3, 4, 6},
                new int[]{3, 5, 5}, new int[]{4, 6, 3}, new int[]{5, 6, 1},
                new int[]{6, 7, 5}, new int[]{6, 8, 10}
        );

        // Input: List of checkpoint points [cp, point]
        List<int[]> points = Arrays.asList(
                new int[]{0, 0}, new int[]{1, 24}, new int[]{2, 3}, new int[]{3, 10},
                new int[]{4, 7}, new int[]{5, 24}, new int[]{6, 3},
                new int[]{7, 4}, new int[]{8, 7}
        );

        // Find the maximum score and optimal path
        Result result = findMaxScoreAndPath(routes, points);

        // Print the result
        System.out.println("Maximum Score: " + result.maxScore);
        System.out.println("Optimal Path: " + result.path);
    }

    public static Result findMaxScoreAndPath(List<int[]> routes, List<int[]> points) {
        // Step 1: Build graph and point map
        Map<Integer, List<Edge>> graph = new HashMap<>();
        Map<Integer, Integer> pointMap = new HashMap<>();

        for (int[] route : routes) {
            graph.putIfAbsent(route[0], new ArrayList<>());
            graph.get(route[0]).add(new Edge(route[1], route[2]));
        }

        for (int[] point : points) {
            pointMap.put(point[0], point[1]);
        }

        // Step 2: Initialize variables for DFS
        Result result = new Result(Integer.MIN_VALUE, new ArrayList<>());
        List<Integer> currentPath = new ArrayList<>();

        // Step 3: Start DFS from checkpoint `0`
        dfs(0, graph, pointMap, currentPath,
                result,
                pointMap.get(0), // Initial score from start checkpoint
                0);              // Initial travel time is zero

        return result;
    }

    private static void dfs(int node,
                            Map<Integer, List<Edge>> graph,
                            Map<Integer, Integer> pointMap,
                            List<Integer> currentPath,
                            Result result,
                            int currentScore,
                            int currentTravelTime) {

        // Add current node to path
        currentPath.add(node);

        // If we reach an end checkpoint (no outgoing edges), calculate final score
        if (!graph.containsKey(node)) {
            int finalScore = currentScore - currentTravelTime;
            if (finalScore > result.maxScore) {
                result.maxScore = finalScore;
                result.path = new ArrayList<>(currentPath);
            }
            currentPath.remove(currentPath.size() - 1); // Backtrack why?
            return;
        }

        // Explore all outgoing edges from the current node
        for (Edge edge : graph.get(node)) {
            dfs(edge.to,
                    graph,
                    pointMap,
                    currentPath,
                    result,
                    currentScore + pointMap.get(edge.to),   // Add points of next checkpoint
                    currentTravelTime + edge.travelTime);   // Add travel time to next checkpoint
        }

        // Backtrack: Remove the current node from path
        currentPath.remove(currentPath.size() - 1);
    }
}
