package com.alg;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

/*
You are given an array of events where events[i] = [startDayi, endDayi, valuei]. The ith event starts at startDayi and ends at endDayi, and if you attend this event, you will receive a value of valuei. You are also given an integer k which represents the maximum number of events you can attend.

You can only attend one event at a time. If you choose to attend an event, you must attend the entire event. Note that the end day is inclusive: that is, you cannot attend two events where one of them starts and the other ends on the same day.

Return the maximum sum of values that you can receive by attending events.



Example 1:



Input: events = [[1,2,4],[3,4,3],[2,3,1]], k = 2
Output: 7
Explanation: Choose the green events, 0 and 1 (0-indexed) for a total value of 4 + 3 = 7.
Example 2:



Input: events = [[1,2,4],[3,4,3],[2,3,10]], k = 2
Output: 10
Explanation: Choose event 2 for a total value of 10.
Notice that you cannot attend any other event as they overlap, and that you do not have to attend k events.
Example 3:



Input: events = [[1,1,1],[2,2,2],[3,3,3],[4,4,4]], k = 3
Output: 9
Explanation: Although the events do not overlap, you can only attend 3 events. Pick the highest valued three.


Constraints:

1 <= k <= events.length
1 <= k * events.length <= 106
1 <= startDayi <= endDayi <= 109
1 <= valuei <= 106
 */
public class Sol1751_MaximumNumberOfEventsThatCanBeAttendedII {
    // https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended-ii/solutions/1052581/python-dp/
    public int maxValue(int[][] events, int k) {
        int n = events.length;
        //sort events by endDay
        Arrays.sort(events, (a, b) -> (a[1] - b[1]));
        /*
        # create two dp lists to track maxValues with k-1(dp) and k(dp2) events attended
        # each element in the list means [last_endDay_with_maxValue_so_far, maxValue]

         */
        TreeMap<Integer, Integer> dp1 = new TreeMap<>();
        TreeMap<Integer, Integer> dp2 = new TreeMap<>();
        dp1.put(0, 0);
        dp2.put(0, 0);
        for (int i = 0; i < k; i++) {
            //           # try to get maxValues with k events
            for (int j = 0; j < n; j++) {
                //  # for each event, find the largest endDay in k-1 list before the event startDay
                //
                int cur = dp1.lowerEntry(events[j][0]).getValue();
                //  # only append new [endDay, maxValue] to the k list if maxValue is a new max value
                //  # in this way we can guarantee maxValues only increase in the list, which is the key for bisect above
                if(cur + events[j][2] > dp2.lastEntry().getValue()) {
                    dp2.put(events[j][1], cur + events[j][2]);
                }
            }
            //       # assign dp2 as k-1 list and start a new round if k < K
            dp1 = dp2;
            dp2 = new TreeMap<>();
            dp2.put(0, 0);
        }
        //# return the maxValue of the last element as it's guaranteed to be the max value overall
        return dp1.lastEntry().getValue();
    }

    // dp + binary search
//https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended-ii/solutions/1052442/simple-dp-solution-recursion-memoization-c/
    public int maxValue2(int[][] events, int k) {
        if (k == 1) {
            return Arrays.stream(events).max(Comparator.comparingInt(e -> e[2])).get()[2];
        }
        Arrays.sort(events, Comparator.comparingInt(e -> e[0]));
        int n = events.length;
        int[][] dp = new int[n + 1][k + 1];
        int max = 0;
        for (int i = n - 1; i >= 0; i--) {
            int next = binarySearch(events, events[i][1], i + 1, n);
            for (int j = 1; j <= k; j++) {
                dp[i][j] = Math.max(dp[i + 1][j], dp[next][j - 1] + events[i][2]);
            }
        }
        return dp[0][k];
    }

    int binarySearch(int[][] events, int targetEnd, int lo, int hi) {
        while (lo < hi) {
            int mid = (hi - lo) / 2 + lo;
            if (targetEnd >= events[mid][0]) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }
}
