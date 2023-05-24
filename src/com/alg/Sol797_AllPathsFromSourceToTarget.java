package com.alg;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Sol797_AllPathsFromSourceToTarget {

    // https://www.jiuzhang.com/problem/all-paths-from-source-to-target/
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        path.add(0);
        dfs(graph, res, path, 0);
        return res;
    }
    private void dfs(int[][] graph, List<List<Integer>> res, LinkedList<Integer> path, int start) {
        if (start == graph.length - 1) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int neighbor : graph[start]) {
            path.add(neighbor);
            dfs(graph, res, path, neighbor);
            path.removeLast();
        }
    }


    // using global parameters

    private List<List<Integer>> result = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();
    public List<List<Integer>> allPathsSourceTarget2(int[][] graph) {
        path.add(0);
        dfs(graph, 0);
        return result;
    }

    private void dfs(int[][] graph, int start) {
        if (start == graph.length - 1) {
            result.add(new ArrayList<>(path));
        }
        for (Integer item: graph[start]) {
            path.add(item);
            dfs(graph, item);
            path.remove(path.size() - 1);
        }
    }
}
