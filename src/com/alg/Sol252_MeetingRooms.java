package com.alg;

import java.util.Arrays;
import java.util.Comparator;
/*
Given an array of meeting time intervals where intervals[i] = [starti, endi],
determine if a person could attend all meetings.



Example 1:

Input: intervals = [[0,30],[5,10],[15,20]]
Output: false

Example 2:

Input: intervals = [[7,10],[2,4]]
Output: true



Constraints:

    0 <= intervals.length <= 104
    intervals[i].length == 2
    0 <= starti < endi <= 106


 */
public class Sol252_MeetingRooms {
    //  nLogn
    public boolean canAttendMeetings(int[][] intervals) {
        // sort by start time
        if (intervals.length == 0) return true;

        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);
//        Arrays.sort(intervals, (a,b) -> Integer.compare(a[0], b[0]));
//        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i][1] > intervals[i+1][0]) {
                return false;
            }
        }
        return true;
    }

    // brute force time O (n^2)
    public boolean canAttendMeetings0(int[][] intervals) {
        for (int i = 0; i < intervals.length; i++) {
            for (int j = i + 1; j < intervals.length; j++) {
                if (overlap(intervals[i], intervals[j])) {
                    return false;
                }
            }
        }
        return true;
    }
    private boolean overlap(int[] a, int[] b) {
        return Math.min(a[1], b[1]) > Math.max(a[0], b[0]); // the smaller end is bigger than the larger start
    }

}
