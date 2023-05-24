package com.alg.lint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*
Description
Given an directed graph, a topological order of the graph nodes is defined as follow:

For each directed edge A -> B in graph, A must before B in the order list.
The first node in the order can be any node in the graph with no nodes direct to it.
Find any topological order for the given graph.

You can assume that there is at least one topological order in the graph.
Learn more about representation of graphs

Example
For graph as follow:
https://www.lintcode.com/help/graph

The topological order can be:

[0, 1, 2, 3, 4, 5]
[0, 2, 3, 1, 5, 4]
...
Challenge
Can you do it in both BFS and DFS?


 */
public class P127_TopologicalSorting {
        static class DirectedGraphNode {
        int label;
        List<DirectedGraphNode> neighbors;
            DirectedGraphNode(int x) {
            label = x; neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    /**
     * @param graph: A list of Directed graph node
     * @return: Any topological order for the given graph.
     */
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        // write your code here
        ArrayList<DirectedGraphNode> result = new ArrayList<>();
        if (graph == null) {
            return result;
        }

        Map<DirectedGraphNode, Integer> indegrees = getIndegrees(graph);

        Queue<DirectedGraphNode> queue = new LinkedList<>();
        for (DirectedGraphNode node : graph) {
            if (indegrees.get(node) == 0) {
                queue.offer(node);
                result.add(node);
            }
        }

        while (!queue.isEmpty()) {
            DirectedGraphNode node = queue.poll();
            for (DirectedGraphNode neighbor : node.neighbors) {
                indegrees.put(neighbor, indegrees.get(neighbor) - 1);
                if (indegrees.get(neighbor) == 0) {
                    queue.offer(neighbor);
                    result.add(neighbor);
                }
            }
        }
        return result;
    }

    private Map<DirectedGraphNode, Integer> getIndegrees(ArrayList<DirectedGraphNode> graph) {
        Map<DirectedGraphNode, Integer> map = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            map.put(node, 0);
        }
        for (DirectedGraphNode node: graph) {
            for (DirectedGraphNode neighbor : node.neighbors) {
                map.put(neighbor, map.get(neighbor) + 1);
            }
        }
        return map;
    }

}
