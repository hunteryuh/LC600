package com.alg;

import java.util.Arrays;

/*
Given the array houses where houses[i] is the location of the ith house along a street and an integer k, allocate k mailboxes in the street.

Return the minimum total distance between each house and its nearest mailbox.

The test cases are generated so that the answer fits in a 32-bit integer.



Example 1:


Input: houses = [1,4,8,10,20], k = 3
Output: 5
Explanation: Allocate mailboxes in position 3, 9 and 20.
Minimum total distance from each houses to nearest mailboxes is |3-1| + |4-3| + |9-8| + |10-9| + |20-20| = 5
Example 2:


Input: houses = [2,3,5,12,18], k = 2
Output: 9
Explanation: Allocate mailboxes in position 3 and 14.
Minimum total distance from each houses to nearest mailboxes is |2-3| + |3-3| + |5-3| + |12-14| + |18-14| = 9.


Constraints:

1 <= k <= houses.length <= 100
1 <= houses[i] <= 104
All the integers of houses are unique.
 */
public class Sol1478_AllocateMailboxes {
    // https://leetcode.com/problems/allocate-mailboxes/discuss/685620/JavaC%2B%2BPython-Top-down-DP-Prove-median-mailbox-O(n3)
    // https://leetcode.com/problems/allocate-mailboxes/discuss/685321/Java-or-Heavily-Commented-or-Proof-on-why-median-work
    int MAX = 100 * 1000;
    public int minDistance(int[] houses, int k) {
        int n = houses.length;
        int[][] cost = new int[n][n];
        int[][] dp = new int[n][k];
        Arrays.sort(houses);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // // Best way to place a mailbox between [i, pos] houses is to place at the median house
                int median = houses[(i + j) /2];
                for (int m = i; m <= j; m++) {
                    cost[i][j] += Math.abs(median - houses[m]);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return dfs(houses, cost, dp, k, 0, 0);
    }

    // top down dp
    private int dfs(int[] houses, int[][] cost, int[][] dp, int k, int curK, int pos) {
        if (pos == houses.length) {
            if (curK == k) {
                return 0;
            }
            return MAX; // covered all houses with less than k mailboxes
        }
        if (curK == k) return MAX;  // used all mailboxes (k) but did not cover all houses
        if (dp[pos][curK] != -1) return dp[pos][curK];
        // Step 2: Recursively, calculate cost of placing rest of the mailboxes at i+1 pos
        int res = MAX;
        for (int i = pos; i < houses.length; i++) {
            res = Math.min(res, cost[pos][i] + dfs(houses, cost, dp, k, curK + 1, i + 1));
        }
        dp[pos][curK] = res;
        return res;
    }

    ///bottom up
    // https://leetcode.com/problems/allocate-mailboxes/discuss/687502/Java-Simple-DP
    // https://leetcode.com/problems/allocate-mailboxes/discuss/1695859/Java-or-DP-or-O(KN2)-or-Explaination

    // worked
    public int minDistance2(int[] houses, int k) {

        // dp[i][j]   min cost to allocate j mailboxes to cover 0..i-1 (first i) houses
        int n = houses.length;
        Arrays.sort(houses);
        int[][] dp = new int[n+1][k+1];
        int[][] costs = new int[n][n+1];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n ; j++) {
                int median = houses[ (i + j - 1 )/2];
                for (int x = i; x < j; x++) {
                    costs[i][j] += Math.abs(median - houses[x]);
                }
            }
        }
        for (int i = 1; i <= n; i++) {
//            dp[i][1] = getCost(houses, 0, i - 1);
            dp[i][1] = costs[0][i];
        }

        for (int i = 1; i <= n; i++) {
            for (int m = 2; m <= k; m++) {
                dp[i][m] = Integer.MAX_VALUE / 2;
                for (int j = 1; j+1 <= i; j++) {
//                    dp[i][m]= Math.min(dp[i][m], dp[j][m-1] + getCost(houses, j, i-1));
                    dp[i][m]= Math.min(dp[i][m], dp[j][m-1] + costs[j][i]);
                }
            }
        }
        return dp[n][k];

    }

    private int getCost(int[] houses, int left, int right) {
        int res = 0;
        while (left < right) {
            res += houses[right] - houses[left];
            right--;
            left++;
        }
        return res;
    }
}
