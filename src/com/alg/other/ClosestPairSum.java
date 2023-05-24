package com.alg.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*

Given two  arrays and a number x, find the pair whose sum
is closest to x and the pair has an element from each array.
 */
public class ClosestPairSum {

    public List<int[]> getClosetPairSum(int[] A, int[] B, int target) {
        Arrays.sort(A);
//        Arrays.parallelSort(A);
        Arrays.sort(B);
        int n = A.length;
        // matrix sum
        //1 2/3/4
        //1
        //2
        List<int[]> res = new ArrayList<>();
        int minDiff = Integer.MAX_VALUE;
        minDiff = Math.min(Math.abs(A[n-1] + B[0] - target), minDiff);
        int i = 0, j = n-1;
        while (i<n && j >=0) {
            int curDiff = Math.abs(A[j] + B[i] - target);
            if (curDiff < minDiff) {
                res.clear();
                res.add(new int[]{A[j], B[i]});
                minDiff = curDiff;
            } else if (curDiff == minDiff) {
                res.add(new int[]{A[j], B[i]});

            }
            if (A[j] +B[i] >= target) {
                j--;
            } else i++;  // may miss duplicate numbers if there are any
            // // ? handle duplicates
        }
        return res;


    }

    public int[] closestSumPair(int[] a1, int[] a2, int target) {
        int[] a1Sorted = Arrays.copyOf(a1, a1.length);
        Arrays.sort(a1Sorted);
        int[] a2Sorted = Arrays.copyOf(a2, a2.length);
        Arrays.sort(a2Sorted);

        int i = 0;
        int j = a2Sorted.length - 1;
        int smallestDiff = Math.abs(a1Sorted[0] + a2Sorted[0] - target);
        int[] closestPair = {a1Sorted[0], a2Sorted[0]};

        while (i < a1Sorted.length && j >= 0) {
            int v1 = a1Sorted[i];
            int v2 = a2Sorted[j];
            int currentDiff = v1 + v2 - target;
            if (Math.abs(currentDiff) < smallestDiff) {
                smallestDiff = Math.abs(currentDiff);
                closestPair[0] = v1;
                closestPair[1] = v2;
            }

            if (currentDiff == 0) {
                return closestPair;
            } else if (currentDiff < 0) {
                i += 1;
            } else {
                j -= 1;
            }
        }

        return closestPair;
    }

    public static void main(String[] args) {
        // NOTE: You can use the following input values to test this function.
        int[] a1 = {-1, 3, 8, 2, 9, 5};
        int[] a2 = {4, 1, 2, 10, 5, 20};
        int aTarget = 24;
        ClosestPairSum ss = new ClosestPairSum();
        List<int[]> res = ss.getClosetPairSum(a1, a2, aTarget);
//        closestSumPair(a1, a2, aTarget) //should return {5, 20} or {3, 20}
        for (int[] pairs: res) {
            System.out.println(Arrays.toString(pairs));
        }

        int[] d1 = {19, 14, 6, 11, -16, 14, -16, -9, 16, 13};
        int[] d2 = {13, 9, -15, -2, -18, 16, 17, 2, -11, -7};
        int dTarget = -15;
        List<int[]> resd = ss.getClosetPairSum(d1, d2, dTarget);
        resd.stream().map(Arrays::toString).forEach(System.out::println); // does contain duplicates

        int[] e1 = {1, 2, 3, 3};
        int[] e2 = {1, 2, 3, 4};
        int eTarget = 6;
        List<int[]> rese = ss.getClosetPairSum(e1, e2, eTarget);
        rese.stream().map(Arrays::toString).forEach(System.out::println); // does contain duplicates
    }
}
