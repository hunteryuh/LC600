package com.alg;

import java.util.HashMap;
import java.util.Map;

/*
There is a row of n houses, where each house can be painted one of three colors: red, blue, or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by an n x 3 cost matrix costs.

    For example, costs[0][0] is the cost of painting house 0 with the color red; costs[1][2] is the cost of painting house 1 with color green, and so on...

Return the minimum cost to paint all houses.



Example 1:

Input: costs = [[17,2,17],[16,16,5],[14,3,19]]
Output: 10
Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue.
Minimum cost: 2 + 5 + 3 = 10.

Example 2:

Input: costs = [[7,6,2]]
Output: 2



Constraints:

    costs.length == n
    costs[i].length == 3
    1 <= n <= 100
    1 <= costs[i][j] <= 20

https://leetcode.com/problems/paint-house/editorial/
 */
public class Sol256_PaintHouse {
    // dp
    // Time O(n), space O(1)
    public int minCost(int[][] costs) {
        for (int i = 1; i < costs.length; i++) {
            costs[i][0] += Math.min(costs[i-1][1], costs[i-1][2]);
            costs[i][1] += Math.min(costs[i-1][0], costs[i-1][2]);
            costs[i][2] += Math.min(costs[i-1][0], costs[i-1][1]);
        }
        int last = costs.length - 1;
        return Math.min(costs[last][0], Math.min(costs[last][1], costs[last][2]));
    }

    // dfs + memoization
    private int[][] costs;
    private Map<String, Integer> memo;
    public int minCost2(int[][] costs) {
        if (costs.length == 0) return 0;
        this.costs = costs;
        this.memo = new HashMap<>();
        return Math.min(paintCost(0, 0), Math.min(paintCost(0,1), paintCost(0, 2)));

    }
    private int paintCost(int n, int color) {
        if (memo.containsKey(getKey(n, color))) {
            return memo.get(getKey(n, color));
        }
        int totalCost = costs[n][color];
        if (n != costs.length - 1) {
            if (color == 0) { // red
                totalCost += Math.min(paintCost(n+1, 1), paintCost(n+1, 2));
            } else if (color == 1) { // green
                totalCost += Math.min(paintCost(n+1, 0), paintCost(n+1, 2));
            } else if (color == 2) {
                totalCost += Math.min(paintCost(n+1, 0), paintCost(n+1, 1));
            }
        }
        memo.put(getKey(n, color), totalCost);
        return totalCost;
    }
    private String getKey(int n , int color) {
        return n + " " + color;
    }

}
