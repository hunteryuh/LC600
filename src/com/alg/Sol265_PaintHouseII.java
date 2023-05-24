package com.alg;

import java.util.HashMap;
import java.util.Map;

/*
There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by an n x k cost matrix costs.

    For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on...

Return the minimum cost to paint all houses.



Example 1:

Input: costs = [[1,5,3],[2,9,4]]
Output: 5
Explanation:
Paint house 0 into color 0, paint house 1 into color 2. Minimum cost: 1 + 4 = 5;
Or paint house 0 into color 2, paint house 1 into color 0. Minimum cost: 3 + 2 = 5.

Example 2:

Input: costs = [[1,3],[2,4]]
Output: 5

 */
public class Sol265_PaintHouseII {
    // bottom up
    public int minCostII(int[][] costs) {
        if (costs.length == 0) return 0;
        int n = costs.length;
        int k = costs[0].length;

        for (int house = 1; house < n; house++) {
            for (int color = 0; color < k; color++) {
                int min = Integer.MAX_VALUE;
                for (int previousColor = 0; previousColor < k; previousColor++) {
                    if (color == previousColor) continue;
                    min = Math.min(min, costs[house - 1][previousColor]);
                }
                costs[house][color] += min;
            }
        }
        int min = Integer.MAX_VALUE;
        for (int c : costs[n-1]) { // last row
            min = Math.min(min, c);
        }
        return min;
    }

    // top down, dfs
    private int n;
    private int k;
    private int[][] costs;
    private Map<String, Integer> memo;
    public int minCostII_TopDown(int[][] costs) {
        if (costs.length == 0) return 0;
        n = costs.length;
        k = costs[0].length;
        costs = costs;
        memo = new HashMap<>();
        int minCost = Integer.MAX_VALUE;
        for (int color = 0; color < k; color++) {
            minCost = Math.min(minCost, memoSolve(0, color));
        }
        return minCost;

    }

    private int memoSolve(int house, int color) {
        // last house/row
        if (house == n - 1) {
            return costs[house][color];
        }
        // memo lookup
        if (memo.containsKey(getKey(house, color))) {
            return memo.get(getKey(house, color));
        }
        // recursive case: determine the min cost for the remainder
        int minRemainingCost = Integer.MAX_VALUE;
        for (int nextColor = 0; nextColor < k; nextColor++) {
            if (nextColor == color) continue;
            int currentRemainingCost = memoSolve(house + 1, nextColor);
            minRemainingCost = Math.min(minRemainingCost, currentRemainingCost);
        }
        int totalCost = costs[house][color] + minRemainingCost;
        memo.put(getKey(house, color), totalCost);
        return totalCost;
    }
    private String getKey(int house, int color) {
        return house + " " + color;
    }

}
