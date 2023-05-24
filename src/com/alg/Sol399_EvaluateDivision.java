package com.alg;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.

You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.

Return the answers to all queries. If a single answer cannot be determined, return -1.0.

Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.



Example 1:

Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
Explanation:
Given: a / b = 2.0, b / c = 3.0
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
Example 2:

Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
Output: [3.75000,0.40000,5.00000,0.20000]
Example 3:

Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
Output: [0.50000,2.00000,-1.00000,-1.00000]


Constraints:

1 <= equations.length <= 20
equations[i].length == 2
1 <= Ai.length, Bi.length <= 5
values.length == equations.length
0.0 < values[i] <= 20.0
1 <= queries.length <= 20
queries[i].length == 2
1 <= Cj.length, Dj.length <= 5
Ai, Bi, Cj, Dj consist of lower case English letters and digits.
 */
public class Sol399_EvaluateDivision {
    // https://leetcode.com/problems/evaluate-division/discuss/171649/1ms-DFS-with-Explanations
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = buildGraph(equations, values);
        int size = queries.size();
        double[] res = new double[size];
        int i = 0;
        for (List<String> eqn: queries) {
            String v1 = eqn.get(0);
            String v2 = eqn.get(1);
            res[i++] = dfs(v1, v2, new HashSet<>(), graph);
        }
        return res;
    }

    // dividend 被除数 divisor 除数

    private Map<String, Map<String, Double>> buildGraph(List<List<String>> equations, double[] values) {
        Map<String, Map<String, Double>> map = new HashMap<>();
        String v1, v2;
        for (int i = 0; i < equations.size(); i++) {
            List<String> eqn = equations.get(i);
            v1 = eqn.get(0);
            v2 = eqn.get(1);
            map.computeIfAbsent(v1, k -> new HashMap<>()).put(v2, values[i]);
            map.computeIfAbsent(v2, k -> new HashMap<>()).put(v1, 1/values[i]);
        }
        return map;
    }

    private double dfs(String v1, String v2, Set<String> visited, Map<String, Map<String, Double>> graph) {
        if (!graph.containsKey(v1) || !graph.containsKey(v2)) return -1;
        if (v1.equals(v2)) return 1;
        if (graph.get(v1).containsKey(v2)) {
            return graph.get(v1).get(v2);
        }
        visited.add(v1);
        for (Map.Entry<String, Double> neigbor: graph.get(v1).entrySet()) {
            if (visited.contains(neigbor.getKey())) continue;
            double result = dfs(neigbor.getKey(), v2, visited, graph);
            if (result != -1) {
                return neigbor.getValue() * result;
            }
        }
        // visited.remove(v1);  // works either way
        return -1.0;
    }

}
