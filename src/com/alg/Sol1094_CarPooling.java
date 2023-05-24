package com.alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/*
There is a car with capacity empty seats. The vehicle only drives east (i.e., it cannot turn around and drive west).

You are given the integer capacity and an array trips where trips[i] = [numPassengersi, fromi, toi] indicates that the ith trip has numPassengersi passengers and the locations to pick them up and drop them off are fromi and toi respectively. The locations are given as the number of kilometers due east from the car's initial location.

Return true if it is possible to pick up and drop off all passengers for all the given trips, or false otherwise.



Example 1:

Input: trips = [[2,1,5],[3,3,7]], capacity = 4
Output: false
Example 2:

Input: trips = [[2,1,5],[3,3,7]], capacity = 5
Output: true
 */
public class Sol1094_CarPooling {
    // passed sweepline
    public boolean carPooling(int[][] trips, int capacity) {
        // trip: passenger, from, to

        List<int[]> pools = new ArrayList<>();
        for (int[] trip: trips) {
            pools.add(new int[]{trip[1], trip[0]});
            pools.add(new int[]{trip[2], -trip[0]});
        }
        Collections.sort(pools, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });

        int load = 0;
        for (int[] pool : pools) {
            load += pool[1];
            if (load > capacity) {
                return false;
            }
        }

        return true;
    }

    // O(n) bucket sort
    public boolean carPooling2(int[][] trips, int capacity) {
        int[] timestamp = new int[1001];
        for (int[] trip : trips) {
            timestamp[trip[1]] += trip[0];
            timestamp[trip[2]] -= trip[0];
        }
        int usedCapacity = 0;
        for (int number : timestamp) {
            usedCapacity += number;
            if (usedCapacity > capacity) {
                return false;
            }
        }
        return true;
    }

    public boolean carPooling_pq(int[][] trips, int capacity) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(a[2], b[2])); // end time
        if (trips == null || trips.length == 0) return true;
        Arrays.sort(trips, (a, b) -> Integer.compare(a[1], b[1])); // sort based on from location
        if (trips[0][0] > capacity) return false;

        pq.offer(trips[0]);
        int load = trips[0][0];
        for (int i = 1; i < trips.length; i++) {
            while (!pq.isEmpty() && pq.peek()[2] <= trips[i][1]) {
                int[] pre = pq.poll();
                load -= pre[0];
            }
            if (load + trips[i][0] > capacity) {
                return false;
            }
            load += trips[i][0];
            pq.offer(trips[i]);
        }
        return true;

    }
}
