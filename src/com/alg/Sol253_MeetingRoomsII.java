package com.alg;
/*
Given an array of meeting time intervals
where intervals[i] = [starti, endi], return the minimum number of conference rooms required.



Example 1:

Input: intervals = [[0,30],[5,10],[15,20]]
Output: 2

Example 2:

Input: intervals = [[7,10],[2,4]]
Output: 1

 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class Sol253_MeetingRoomsII {

    // priority queue, NlogN time
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> (a[0] - b[0])); // sort by start time
        // minHeap, O(1) time to get minimum
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]); // sort by end time
        pq.offer(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            if (pq.peek()[1] <= intervals[i][0]) {
                pq.poll();
            }
            pq.offer(intervals[i]);
        }
        return pq.size();
    }

    public int minMeetingRooms_pq(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]); // sort by start time
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1] - b[1]); // add to minHeap sorted by endtime
        for (int i = 0; i < intervals.length; i++) {
            if (!pq.isEmpty() && pq.peek()[1] <= intervals[i][0]) {
                pq.poll();
            }
            pq.offer(intervals[i]);
        }
        return pq.size();
    }

    // sweepline
    public int minMeetingRooms2(int[][] intervals) {
        List<int[]> times = new ArrayList<>();
        for (int[] meeting: intervals) {
            times.add(new int[]{meeting[0], 1});
            times.add(new int[]{meeting[1], -1});
        }
        Collections.sort(times, (a, b) -> {
           if (a[0] == b[0]) {
               return a[1] - b[1];// if same timing happens for 2 meetings, put the ending meeting first as it is not treated as a conflict
           }
           return a[0] - b[0];
        });
        int res = 0;
        int count = 0;
        for (int[] time : times) {
            count += time[1];
            res = Math.max(res, count);
        }
        return res;
    }

    // using map to store all start and end timing, ordered by treemap
    public int countOfAirplanes(int[][] airplanes) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int[] airline : airplanes) {
            map.put(airline[0], map.getOrDefault(airline[0], 0) + 1);
            map.put(airline[1], map.getOrDefault(airline[1], 0) - 1);
        }
        int count = 0, now = 0;
        for (Integer key : map.keySet()) {
            now += map.get(key);
            count = Math.max(count, now);
        }
        return count;
    }

    // count of airplanes in the sky
    // Given a list interval, which are taking off and landing time of the flight.
    // How many airplanes are there at most at the same time in the sky?
    //
    //
    //
    // If landing and taking off of different planes happen at the same time,
    // we consider landing should happen at first.
    public int countOfAirplanes_2(int[][] airplanes) {
        // int[]{4, 6},  index 0: start; index 1: land
        List<int[]> list = new ArrayList<>();
        for (int[] interval: airplanes) {
            list.add(new int[]{interval[0], 1});
            list.add(new int[]{interval[1], -1});
        }
        // sort by starting time; if same, end time first
        list.sort( (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];  // landing first, or it is not in the sky
            }
            return a[0] - b[0];
        });
//        list.sort(Comparator.comparing((int[] p) -> p[0]).thenComparing((int[] p) -> p[1]);
        int res = 0;
        int count = 0;
        for (int[] seg: list) {
            count += seg[1];
            res = Math.max(res, count);
        }
        return res;
    }



    // leetcode answer2, sort both start times and end times, use 2 pointers
    public int minMeetingRooms_2(int[][] intervals) {

        // Check for the base case. If there are no intervals, return 0
        if (intervals.length == 0) {
            return 0;
        }

        Integer[] start = new Integer[intervals.length];
        Integer[] end = new Integer[intervals.length];

        for (int i = 0; i < intervals.length; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }

        // Sort the intervals by end time
        Arrays.sort(end);

        // Sort the intervals by start time
        Arrays.sort(start);

        // The two pointers in the algorithm: e_ptr and s_ptr.
        int startPointer = 0, endPointer = 0;

        // Variables to keep track of maximum number of rooms used.
        int usedRooms = 0;

        // Iterate over intervals.
        while (startPointer < intervals.length) {

            // If there is a meeting that has ended by the time the meeting at `start_pointer` starts
            if (start[startPointer] >= end[endPointer]) {
                usedRooms -= 1;
                endPointer += 1;
            }

            // We do this irrespective of whether a room frees up or not.
            // If a room got free, then this used_rooms += 1 wouldn't have any effect. used_rooms would
            // remain the same in that case. If no room was free, then this would increase used_rooms
            usedRooms += 1;
            startPointer += 1;

        }

        return usedRooms;
    }

    // same as approach above with for loop
    public int minMeetingRooms_2_1(int[][] intervals) {
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int rooms = 0, endsItr = 0;
        for (int i = 0; i < starts.length; i++) {
            if (starts[i] < ends[endsItr]) {
                rooms++;
            } else {
                endsItr++;
            }
        }
        return rooms;
    }
}
