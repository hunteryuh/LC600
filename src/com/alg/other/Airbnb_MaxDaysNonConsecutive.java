package com.alg.other;

//https://github.com/kanglicheng/airbnb-1?tab=readme-ov-file
// 19
/*
Given a set of numbers in an array which represent number of consecutive nights of AirBnB reservation requested, as a host, pick the sequence which maximizes the number of days of occupancy, at the same time, leaving at least 1 day gap in between bookings for cleaning.
Problem reduces to finding max-sum of non-consecutive array elements.
 */
public class Airbnb_MaxDaysNonConsecutive {
    public int findMaxDays(int[] days) {
        int n = days.length;
        int[] maxDaysUntilPos = new int[n + 1];
        maxDaysUntilPos[0] = 0;
        maxDaysUntilPos[1] = days[0];

        for (int i = 2; i < maxDaysUntilPos.length; i++) {
            maxDaysUntilPos[i] = Math.max(maxDaysUntilPos[i-1], maxDaysUntilPos[i-2] + days[i-1]);
        }

        return maxDaysUntilPos[n];
    }
    // same as house rob
    // https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0198.%E6%89%93%E5%AE%B6%E5%8A%AB%E8%88%8D.md
    // dp[i]：考虑下标i（包括i）以内的房屋，最多可以偷窃的金额为dp[i]。
}
