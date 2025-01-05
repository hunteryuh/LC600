package com.alg;
/*
You are given an array of events where events[i] = [startDayi, endDayi]. Every event i starts at startDayi and ends at endDayi.

You can attend an event i at any day d where startTimei <= d <= endTimei. You can only attend one event at any time d.

Return the maximum number of events you can attend.



Example 1:


Input: events = [[1,2],[2,3],[3,4]]
Output: 3
Explanation: You can attend all the three events.
One way to attend them all is as shown.
Attend the first event on day 1.
Attend the second event on day 2.
Attend the third event on day 3.
Example 2:

Input: events= [[1,2],[2,3],[3,4],[1,2]]
Output: 4


Constraints:

1 <= events.length <= 105
events[i].length == 2
1 <= startDayi <= endDayi <= 105
 */

import com.alg.dp.Sol377_CombinationSum4;

import java.util.Arrays;
import java.util.PriorityQueue;

// https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/description/
public class Sol1353_MaximumNumberOfEventsCanBeAttended {
    // https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/solutions/1419827/java-sort-priorityqueue-solution/
    public int maxEvents(int[][] events) {
        if (events == null || events.length == 0) return 0;
        final int N = events.length;
        // Sort events by start day.
        Arrays.sort(events, (e1, e2) -> Integer.compare(e1[0], e2[0]));
        // Store end days of in progress events in min heap.
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        // Attend the earliest ending in progress evetns from the earliest start event to the latest start event.
        int i = 0, day = 0, res = 0;
        while (i < N || !pq.isEmpty()) {
            // Get current date.
            if (pq.isEmpty()) {
                day = events[i][0];
            }
            // Add just started events at current day in the min heap.
            while (i < N && day >= events[i][0]) {
                pq.add(events[i][1]);
                i++;
            }
            // Attend the earliest ending event.
            pq.poll();
            res++;
            day++; // go to next day after attending one event (res++)
            // Remove already ended events.
            while (!pq.isEmpty() && day > pq.peek()) {
                pq.poll();
            }
        }
        return res;
    }

    public int maxEvents2(int[][] events) {
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int i = 0;
        int n = events.length;
        int day = events[0][0];
        int res = 0;
        while (i < n || !pq.isEmpty()) { // need to run if pq is not empty
            // if no events in the pq, then move to the next event start day
            if (pq.isEmpty()) {
                day = events[i][0];
            }
            while (i < n && events[i][0] <= day) {
                pq.offer(events[i][1]);
                i++;
            }
            System.out.println("pq size is " + pq.size());
//            System.out.println();
            System.out.println("i is: " + i);
            pq.poll(); // return null if pq is empty, no error catching
            day++;
            System.out.println("day is " + day);
            res++;
            System.out.println("res is " + res);
            while (!pq.isEmpty() && pq.peek() < day) {
                pq.poll();
            }
            System.out.println("pq size is " + pq.size() + " after day " + day);
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] events = new int[][]{{1,2},{2,3},{3,4},{1,2}};
        int[][] events2 = new int[][]{{52,79},{7,34}};

        Sol1353_MaximumNumberOfEventsCanBeAttended ss = new Sol1353_MaximumNumberOfEventsCanBeAttended();
        int res = ss.maxEvents2(events2);
        System.out.println(res);

    }
}
