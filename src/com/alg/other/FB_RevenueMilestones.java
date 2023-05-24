package com.alg.other;

import java.util.Arrays;

/*
We keep track of the revenue Facebook makes every day, and we want to know on what days Facebook hits certain revenue milestones. Given an array of the revenue on each day, and an array of milestones Facebook wants to reach, return an array containing the days on which Facebook reached every milestone.
Signature
int[] getMilestoneDays(int[] revenues, int[] milestones)
Input
revenues is a length-N array representing how much revenue FB made on each day (from day 1 to day N). milestones is a length-K array of total revenue milestones.
Output
Return a length-K array where K_i is the day on which FB first had milestones[i] total revenue. If the milestone is never met, return -1.
Example
revenues = [10, 20, 30, 40, 50, 60, 70, 80, 90, 100]
milestones = [100, 200, 500]
output = [4, 6, 10]
Explanation
On days 4, 5, and 6, FB has total revenue of $100, $150, and $210 respectively. Day 6 is the first time that FB has >= $200 of total revenue.
 */
public class FB_RevenueMilestones {
    int[] getMilestoneDays(int[] revenues, int[] milestones) {
        int[] prefixSumRevenues = new int[revenues.length];
        for (int i = 0; i < prefixSumRevenues.length; i++) {
            if (i == 0) {
                prefixSumRevenues[i] = revenues[0];
            } else {
                prefixSumRevenues[i] = prefixSumRevenues[i - 1] + revenues[i];
            }
        }

        int[] res = new int[milestones.length];
        for (int i = 0; i< res.length; i++) {
            res[i] = bs(milestones[i], prefixSumRevenues);
        }
        return res;
    }
    private int bs(int target, int[] arr) {
        int left = 0;
        int n = arr.length;
        int right = n - 1;
        while (left + 1 < right) {
            int mid = left + (right - left)/2;
            if (arr[mid] > target) {
                right = mid;  // use mid instead of +1, -1
            } else if (arr[mid] < target) {
                left = mid ;
            } else {
                right = mid ;
            }
        }

        // check left and right after left+1<right loop
        if (arr[left] >= target) {
            return left+1;
        }
        if (arr[right] >= target) {
            return right+1;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] rev = {700, 800, 600, 400, 600, 700};
        int[] ms = {3100, 2200, 800, 2100, 1000};
        FB_RevenueMilestones f = new FB_RevenueMilestones();
        int[] res = f.getMilestoneDays(rev, ms);
        System.out.println(Arrays.toString(res));
    }
}
