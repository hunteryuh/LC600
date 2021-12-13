package com.alg;

import java.util.Arrays;

/*
Given an array intervals where intervals[i] = [li, ri] represent the interval [li, ri), remove all intervals that are covered by another interval in the list.

The interval [a, b) is covered by the interval [c, d) if and only if c <= a and b <= d.

Return the number of remaining intervals.



Example 1:

Input: intervals = [[1,4],[3,6],[2,8]]
Output: 2
Explanation: Interval [3,6] is covered by [2,8], therefore it is removed.
Example 2:

Input: intervals = [[1,4],[2,3]]
Output: 1
Example 3:

Input: intervals = [[0,10],[5,12]]
Output: 2
Example 4:

Input: intervals = [[3,10],[4,10],[5,11]]
Output: 2
Example 5:

Input: intervals = [[1,2],[1,4],[3,4]]
Output: 1



 */
public class Sol1288_RemoveCoveredIntervals {

    public int removeCoveredInternals(int[][] intervals) {
        Arrays.sort(intervals, (a,b) -> a[0] == b[0] ? b[1] - a[1] : a[0]- b[0]);
        int n = intervals.length;
        if (n <= 1) {
            return n;
        }
        int countRemoval = 0;
        int[] pre = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            int[] cur = intervals[i];
            if (cur[0] >= pre[0] && cur[1] <= pre[1]) {
                countRemoval++;
            } else {
                pre = cur;
            }
        }
        return intervals.length - countRemoval;
    }

    public static void main(String[] args) {
        Sol1288_RemoveCoveredIntervals ss = new Sol1288_RemoveCoveredIntervals();
        int[][] intervals1 = new int[][]{{1, 4}, {2, 3}};
        int[][] intervals2 = new int[][]{{0, 10}, {5, 13}};
        int[][] intervals3 = new int[][]{{3, 10}, {4,10}, {5, 11}};
        int[][] intervals4 = new int[][]{ {1, 2}, {1,4}, {3, 4}};
        System.out.println(ss.removeCoveredInternals(intervals1));
        System.out.println(ss.removeCoveredInternals(intervals2));
        System.out.println(ss.removeCoveredInternals(intervals3));
        System.out.println(ss.removeCoveredInternals(intervals4));
    }
}
