package com.alg;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
You are given an array of intervals, where intervals[i] = [starti, endi] and each starti is unique.

The right interval for an interval i is an interval j such that startj >= endi and startj is minimized.

Return an array of right interval indices for each interval i. If no right interval exists for interval i, then put -1 at index i.



Example 1:

Input: intervals = [[1,2]]
Output: [-1]
Explanation: There is only one interval in the collection, so it outputs -1.
Example 2:

Input: intervals = [[3,4],[2,3],[1,2]]
Output: [-1,0,1]
Explanation: There is no right interval for [3,4].
The right interval for [2,3] is [3,4] since start0 = 3 is the smallest start that is >= end1 = 3.
The right interval for [1,2] is [2,3] since start1 = 2 is the smallest start that is >= end2 = 2.
Example 3:

Input: intervals = [[1,4],[2,3],[3,4]]
Output: [-1,2,-1]
Explanation: There is no right interval for [1,4] and [3,4].
The right interval for [2,3] is [3,4] since start2 = 3 is the smallest start that is >= end1 = 3.


Constraints:

1 <= intervals.length <= 2 * 104
intervals[i].length == 2
-106 <= starti <= endi <= 106
The start point of each interval is unique.

 */
public class Sol436_FindRightInterval {

    // https://leetcode.com/problems/find-right-interval/discuss/631133/Java-O(n-logn)-solution-HashMap-%2B-Sort-%2B-Binary-Search
    public int[] findRightInterval(int[][] intervals) {
        int m = intervals.length;
        int[] startArray = new int[m];  // array of starting point of each interval
        Map<Integer, Integer> startIndexMap = new HashMap<>();
        for (int i = 0; i < intervals.length; i++) {
            startIndexMap.put(intervals[i][0], i);   // map of key = start of each interval, value = index of each interval
            startArray[i] = intervals[i][0];
        }

        Arrays.sort(startArray);   // sort the starting point of all intervals, the original intervals does not change

        int[] res = new int[intervals.length];

        for (int i = 0; i < m; i++) {
            int end = intervals[i][1];   // the target we want to search in the sorted array with a greater value
            int left = 0;
            int right = m - 1;
            int resultIntervalStart = -1;
            boolean found = false;   // to see if a valid interval is found
            while (left + 1 < right) {    // binary search
                int mid = left + (right - left ) / 2;
                if (startArray[mid] >= end) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            // now out of the loop, we check left and right value, and store the smaller one, so check "right" first
            if (startArray[right] >= end) {
                found = true;
                resultIntervalStart = startArray[right];
            }

            if (startArray[left] >= end) {
                found = true;
                resultIntervalStart = startArray[left];
            }

            if (found) {
                res[i] = startIndexMap.get(resultIntervalStart);
            } else {
                res[i] = -1;
            }
        }
        return res;

    }
}
