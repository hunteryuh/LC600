package com.alg;

import java.util.Arrays;
import java.util.Comparator;
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

    // max heap/max priority queue, capped at k elements  time: O(NlogK)
    // https://leetcode.com/problems/k-closest-points-to-origin/solution/
    public static int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (p1, p2) -> p2[0] * p2[0] + p2[1] * p2[1] - p1[0] * p1[0] - p1[1] * p1[1]
        );

        for (int[] point: points) {
            // Adding to/removing from the heap (or priority queue) only takes O(logk) time when the size of the heap is capped at kk elements.
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

    public int[][] kClosest2(int[][] points, int K) {
        Arrays.sort(points, Comparator.comparing(p -> p[0] * p[0] + p[1] * p[1]));
        return Arrays.copyOfRange(points, 0, K); // from inclusive, to exclusive
    }

    // quick select  time: O(n)
    // https://leetcode.com/problems/k-closest-points-to-origin/solutions/220235/java-three-solutions-to-this-classical-k-th-problem/
    public int[][] kClosestPoints(int[][] points, int k) {
        int len = points.length;
        int l = 0;
        int r = len - 1;
        while (l < r) { // l <= r also works
            int mid = partition(points, l, r);
//            int mid = helper(points, l, r);
            if (mid == k) {
                break;
            }
            if (mid  < k) {
                l = mid + 1;
            } else {
                r = mid  - 1;
            }
        }
        return Arrays.copyOfRange(points, 0, k);
    }

    private int partition(int[][] points, int start, int end) {
        int[] pivot = points[end];
        int swapIndex = start - 1;
        for (int i = start; i < end; i++) {
            if (value(points[i]) < value(pivot)) {
                swapIndex++;
                swap(points, swapIndex, i);
            }
        }
        swap(points, ++swapIndex, end);
        return swapIndex;
    }

    private int value(int[] a) {
        return a[0] * a[0] + a[1] * a[1];
    }

    private void swap(int[][] points, int i, int j) {
        int[] temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }

    private int helper(int[][] nums, int l, int r) {
        int[] pivot = nums[l];
        while (l < r) {
            while (l < r &&  compare(nums[r], pivot) >= 0) {
                r--;
            }
            nums[l] = nums[r];
            while (l < r && compare(nums[l], pivot) <= 0) {
                l++;
            }
            nums[r] = nums[l];
        }
        nums[l] = pivot;
        return l;
    }

    private int compare(int[] p1, int[] p2) {
        return p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1];
    }

}
