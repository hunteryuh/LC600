package com.alg;

import com.alg.lint.P90_KSumII;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/*
You are given an integer array heights representing the heights of buildings, some bricks, and some ladders.

You start your journey from building 0 and move to the next building by possibly using bricks or ladders.

While moving from building i to building i+1 (0-indexed),

If the current building's height is greater than or equal to the next building's height, you do not need a ladder or bricks.
If the current building's height is less than the next building's height, you can either use one ladder or (h[i+1] - h[i]) bricks.
Return the furthest building index (0-indexed) you can reach if you use the given ladders and bricks optimally.



Example 1:


Input: heights = [4,2,7,6,9,14,12], bricks = 5, ladders = 1
Output: 4
Explanation: Starting at building 0, you can follow these steps:
- Go to building 1 without using ladders nor bricks since 4 >= 2.
- Go to building 2 using 5 bricks. You must use either bricks or ladders because 2 < 7.
- Go to building 3 without using ladders nor bricks since 7 >= 6.
- Go to building 4 using your only ladder. You must use either bricks or ladders because 6 < 9.
It is impossible to go beyond building 4 because you do not have any more bricks or ladders.
Example 2:

Input: heights = [4,12,2,7,3,18,20,3,19], bricks = 10, ladders = 2
Output: 7
Example 3:

Input: heights = [14,3,19,3], bricks = 17, ladders = 0
Output: 3


Constraints:

1 <= heights.length <= 105
1 <= heights[i] <= 106
0 <= bricks <= 109
0 <= ladders <= heights.length
 */
public class Sol1642_FurthestBuildingYouCanReach {
    // Priority queue, minheap
    /*
    Heap heap store k height difference that we need to use ladders.
Each move, if the height difference d > 0,
we push d into the priority queue pq.
If the size of queue exceed ladders,
it means we have to use bricks for one move.
Then we pop out the smallest difference, and reduce bricks.
If bricks < 0, we can't make this move, then we return current index i.
If we can reach the last building, we return A.length - 1.
     */

    // time O(N LogL) L: number of ladders available
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < heights.length - 1; i++) {
            int d = heights[i+1] - heights[i];
            if (d > 0) {
                pq.add(d);
            }
            if (pq.size() > ladders) {
                bricks -= pq.poll();
            }
            // If this caused bricks to go negative, we can't get to i + 1
            if (bricks < 0) {
                return i;
            }
        }
        return heights.length - 1;
    }

    // binary search
    // time: O(N log^2 N)
    public int furthestBuilding2(int[] heights, int bricks, int ladders) {
        // Do a binary search on the heights array to find the final reachable building.
        int lo = 0;
        int hi = heights.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo + 1) / 2;
            if (isReachable(mid, heights, bricks, ladders)) {
                lo = mid;
            } else {
                hi = mid - 1;
            }
        }
        return hi; // Note that return lo would be equivalent.
    }

    private boolean isReachable(int buildingIndex, int[] heights, int bricks, int ladders) {
        // Make a list of all the climbs we need to do to reach buildingIndex.
        List<Integer> climbs = new ArrayList<>();
        for (int i = 0; i < buildingIndex; i++) {
            int h1 = heights[i];
            int h2 = heights[i + 1];
            if (h2 <= h1) {
                continue;
            }
            climbs.add(h2 - h1);
        }
        Collections.sort(climbs);

        // And now determine whether or not all of these climbs can be covered with the
        // given bricks and ladders.
        for (int climb : climbs) {
            // If there are bricks left, use those.
            if (climb <= bricks) {
                bricks -= climb;
                // Otherwise, you'll have to use a ladder.
            } else if (ladders >= 1) {
                ladders -= 1;
                // And if there are no ladders either, we can't reach buildingIndex.
            } else {
                return false;
            }
        }
        return true;
    }
}
