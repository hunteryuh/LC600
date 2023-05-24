package com.alg.greedy;

import java.util.Arrays;
import java.util.Comparator;

/*
There are some spherical balloons taped onto a flat wall that represents the XY-plane. The balloons are represented as a 2D integer array points where points[i] = [xstart, xend] denotes a balloon whose horizontal diameter stretches between xstart and xend. You do not know the exact y-coordinates of the balloons.

Arrows can be shot up directly vertically (in the positive y-direction) from different points along the x-axis. A balloon with xstart and xend is burst by an arrow shot at x if xstart <= x <= xend. There is no limit to the number of arrows that can be shot. A shot arrow keeps traveling up infinitely, bursting any balloons in its path.

Given the array points, return the minimum number of arrows that must be shot to burst all balloons.



Example 1:

Input: points = [[10,16],[2,8],[1,6],[7,12]]
Output: 2
Explanation: The balloons can be burst by 2 arrows:
- Shoot an arrow at x = 6, bursting the balloons [2,8] and [1,6].
- Shoot an arrow at x = 11, bursting the balloons [10,16] and [7,12].
Example 2:

Input: points = [[1,2],[3,4],[5,6],[7,8]]
Output: 4
Explanation: One arrow needs to be shot for each balloon for a total of 4 arrows.
Example 3:

Input: points = [[1,2],[2,3],[3,4],[4,5]]
Output: 2
Explanation: The balloons can be burst by 2 arrows:
- Shoot an arrow at x = 2, bursting the balloons [1,2] and [2,3].
- Shoot an arrow at x = 4, bursting the balloons [3,4] and [4,5].
 */
public class Sol452_MinimumNumberArrowsBurstBalloons {
    public int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
//        Arrays.sort(points, (o1, o2) -> {
//            if (o1[0] < 0 && o2[0] > 0) {
//                return -1;
//            };
//            if (o1[0] > 0 && o2[0] < 0) {
//                return 1;
//            }
//            return o1[0] - o2[0];
//        });
        Arrays.sort(points, Comparator.comparingInt(o -> o[0]));
        int res = 1;
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] <= points[i-1][1]) {
                points[i][1] = Math.min(points[i-1][1], points[i][1]);
            } else {
                res++;
            }
        }
        return res;
        // failed for case [[-2147483646,-2147483645],[2147483646,2147483647]]
        // due to overflow
    }

    public static void main(String[] args) {
        int[][] points = {{1,2}, {2,3}, {4,5}, {5,6}};
        Sol452_MinimumNumberArrowsBurstBalloons ss = new Sol452_MinimumNumberArrowsBurstBalloons();
        System.out.println(ss.findMinArrowShots(points));
    }
}
