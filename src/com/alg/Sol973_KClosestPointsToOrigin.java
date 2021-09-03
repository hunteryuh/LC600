package com.alg;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return the k closest points to the origin (0, 0).

The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).

You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).

Input: points = [[1,3],[-2,2]], k = 1
Output: [[-2,2]]
Explanation:
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].
 */
public class Sol973_KClosestPointsToOrigin {

    public static int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (p1, p2) -> p2[0] * p2[0] + p2[1] * p2[1] - p1[0] * p1[0] - p1[1] * p1[1]
        );

        for (int[] point: points) {
            pq.add(point);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        int[][] result = new int[k][2];
//        for (int i = 0; i < k; i++) {
        for (int i = k - 1; i >=0; i--) {
            result[i] = pq.poll();  // maximum out first
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] inputs = {{1,0}, {-2,2}, {1,7}};
        int k = 2;
        System.out.println(Arrays.deepToString(kClosest(inputs, k)));
    }
}
