package com.alg;

import java.util.HashSet;
import java.util.Set;

/*
You are given a 2D integer array ranges and two integers left and right. Each ranges[i] = [starti, endi] represents an inclusive interval between starti and endi.

Return true if each integer in the inclusive range [left, right] is covered by at least one interval in ranges. Return false otherwise.

An integer x is covered by an interval ranges[i] = [starti, endi] if starti <= x <= endi.



Example 1:

Input: ranges = [[1,2],[3,4],[5,6]], left = 2, right = 5
Output: true
Explanation: Every integer between 2 and 5 is covered:
- 2 is covered by the first range.
- 3 and 4 are covered by the second range.
- 5 is covered by the third range.
Example 2:

Input: ranges = [[1,10],[10,20]], left = 21, right = 21
Output: false
Explanation: 21 is not covered by any range.

1 <= ranges.length <= 50
1 <= starti <= endi <= 50
1 <= left <= right <= 50

https://leetcode.com/problems/check-if-all-the-integers-in-a-range-are-covered/
 */
public class Sol1893_CheckIfAllTheIntegersInARangeAreCovered {
    public boolean isCovered(int[][] ranges, int left, int right) {
        Set<Integer> set = new HashSet<>();
        for (int[] range:ranges) {
            int rl = range[0];
            int rr = range[1];
            for (int r = rl; r <= rr; r++) {
                set.add(r);
            }
        }
        for (int i = left; i <= right; i++) {
            if (!set.contains(i)) {
                return false;
            }
        }
        return true;
    }

    // line sweep
    // https://leetcode.com/problems/check-if-all-the-integers-in-a-range-are-covered/discuss/1267923/Line-Sweep-O(n-%2B-m)
    public boolean isCovered2(int[][] ranges, int left, int right) {
        int[] lines = new int[51];
        for (int[] range : ranges) {
            lines[range[0]]++;
            lines[range[1] + 1]--;
        }
        int runningSum = 0;
        for (int i = 1; i<= right; i++) {
            runningSum += lines[i];
            if ( i >= left && runningSum <= 0) {
                return false;
            }
        }
        return true;
    }
}
