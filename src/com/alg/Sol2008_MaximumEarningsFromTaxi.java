package com.alg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
There are n points on a road you are driving your taxi on. The n points on the road are labeled from 1 to n in the direction you are going, and you want to drive from point 1 to point n to make money by picking up passengers. You cannot change the direction of the taxi.

The passengers are represented by a 0-indexed 2D integer array rides, where rides[i] = [starti, endi, tipi] denotes the ith passenger requesting a ride from point starti to point endi who is willing to give a tipi dollar tip.

For each passenger i you pick up, you earn endi - starti + tipi dollars. You may only drive at most one passenger at a time.

Given n and rides, return the maximum number of dollars you can earn by picking up the passengers optimally.

Note: You may drop off a passenger and pick up a different passenger at the same point.



Example 1:

Input: n = 5, rides = [[2,5,4],[1,5,1]]
Output: 7
Explanation: We can pick up passenger 0 to earn 5 - 2 + 4 = 7 dollars.

Example 2:

Input: n = 20, rides = [[1,6,1],[3,10,2],[10,12,3],[11,12,2],[12,15,2],[13,18,1]]
Output: 20
Explanation: We will pick up the following passengers:
- Drive passenger 1 from point 3 to point 10 for a profit of 10 - 3 + 2 = 9 dollars.
- Drive passenger 2 from point 10 to point 12 for a profit of 12 - 10 + 3 = 5 dollars.
- Drive passenger 5 from point 13 to point 18 for a profit of 18 - 13 + 1 = 6 dollars.
We earn 9 + 5 + 6 = 20 dollars in total.



Constraints:

    1 <= n <= 105
    1 <= rides.length <= 3 * 104
    rides[i].length == 3
    1 <= starti < endi <= n
    1 <= tipi <= 105


 */
public class Sol2008_MaximumEarningsFromTaxi {
    public long maxTaxiEarnings(int n, int[][] rides) {
        // Time O(n + klogk), k = A.length
        //hashmap + dp https://leetcode.com/problems/maximum-earnings-from-taxi/solutions/1592231/java-dp/
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int i = 0; i < rides.length; i++) {
            //key: end, value: array [0]: start, [1]: tip
            map.computeIfAbsent(rides[i][1], x -> new ArrayList<>()).add(new int[]{rides[i][0], rides[i][2]}); //
        }
        long[] dp = new long[n+1];
        for (int i = 1; i <= n; i++) {
            long curMax = dp[i-1];
            if (map.containsKey(i)) {
                for (int[] ride : map.get(i)) {
                    curMax = Math.max(curMax, dp[ride[0]] + i - ride[0] + ride[1]);
                }
            }
            dp[i] = curMax;
        }
        return dp[n];
    }
}
