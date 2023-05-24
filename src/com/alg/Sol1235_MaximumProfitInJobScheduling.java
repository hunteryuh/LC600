package com.alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

/*
We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].

You're given the startTime, endTime and profit arrays, return the maximum profit you can take
such that there are no two jobs in the subset with overlapping time range.

If you choose a job that ends at time X you will be able to start another job that starts at time X.



Example 1:

Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
Output: 120
Explanation: The subset chosen is the first and fourth job.
Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.

Example 2:

Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
Output: 150
Explanation: The subset chosen is the first, fourth and fifth job.
Profit obtained 150 = 20 + 70 + 60.

Example 3:

Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
Output: 6



Constraints:

    1 <= startTime.length == endTime.length == profit.length <= 5 * 104
    1 <= startTime[i] < endTime[i] <= 109
    1 <= profit[i] <= 104

https://leetcode.com/discuss/interview-question/1320711/Doordash-or-Phone-Screen
doordash
 */
public class Sol1235_MaximumProfitInJobScheduling {
    // bottom up dp
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] jobs = new int[n][3];
        for (int i = 0; i < n; i++) {
            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(jobs, (a, b) -> a[1] - b[1]); // sort by end time
        int[] dp = new int[n]; // dp[i] stores the profit for jobs till jobs[i] including jobs[i]
        dp[0] = jobs[0][2];
        for (int i = 1; i < n; i++) {
            // profit including the current job
            // Find Profit including current Job:
            //Find the latest job before the current job (in sorted array) that doesn't conflict with current job 'jobs[n-1]'.
            //Once found, we recur for all jobs till that job and add profit of current job to result.
            int curProfit = jobs[i][2];
            int l = search(jobs, i);
            if (l != -1) {
                curProfit += dp[l];
            }
            // store max profit of including and excluding
            dp[i] = Math.max(curProfit, dp[i - 1]);
        }
        return dp[n - 1];

    }
    // return the index of the largest element < target in the given list (assume there must exist one element < target)
    private int search(int[][] jobs, int index) {
        int start = 0;
        int end = index - 1;
        while (start <= end) {
            int mid = (end + start) / 2;
            if (jobs[mid][1] <= jobs[index][0]) {
                if (jobs[mid+1][1] <= jobs[index][0]) {  //  jobs[mid] is the rightmost job that end time is less than the current job (jobs[i]), then we return mid since mid is the index of the previous job we want.
                    start = mid + 1;
                } else {
                    return mid;
                }
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    //https://leetcode.com/problems/maximum-profit-in-job-scheduling/discuss/409009/JavaC%2B%2BPython-DP-Solution
    // Java with TreeMap
    // dp[time] = profit, means that within the first time duration ( from 0 to time), we can make at most profit money.
    //Initial dp[0] = 0, as we make profit = 0 at time = 0.
    // induction rule
// for each job = [s, e, p], where s = start time, e = end time, p = profit,
// case 1: don't do this job  -> nothing changes, dp[end_time] = dp[previous end_time]
// case 2: do this job -> dp[end_time] = dp[previous end_time <= s that gives max profit] + p
//         find the max profit we can make before start time s (using binary search), so we can know the max profit we can make after doing this job
// Therefore,
// dp[end_time] = max( dp[previous end_time],  dp[previous end_time <= s that gives max profit] + p )
    // Time O(NlogN) for sorting
    //Time O(NlogN) for binary search for each job
    //Space O(N)
    public int jobScheduling2(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] jobs = new int[n][3];
//        Map<Integer, Integer>
        for (int i = 0; i < n; i++) {
            jobs[i] = new int[] {startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(jobs, (a, b)->a[1] - b[1]);  // sort by end time
        TreeMap<Integer, Integer> sortedJobs = new TreeMap<>();
        sortedJobs.put(0, 0);
//        List<Integer> selectedJobs = new ArrayList<>();
//        for (int[] job : jobs) {
        for (int i = 0; i < jobs.length; i++) {
            int[] job = jobs[i];
            int cur = sortedJobs.floorEntry(job[0]).getValue() + job[2]; //previous end_time <= cur start_time
            // lastEntry : returns the entry associated with the greatest key
            if (cur > sortedJobs.lastEntry().getValue()) {
                sortedJobs.put(job[1], cur);
//                selectedJobs.add(i);
            }
        }
        System.out.println(sortedJobs);
//        System.out.println(selectedJobs);
        return sortedJobs.lastEntry().getValue();
    }

    public static void main(String[] args) {
        int[] startTime = new int[]{1,2,3,4,6};
        int[] endTime = new int[]{3,5,10,6,9};
        int[] profit = new int[]{20,20,100,70,60};
        Sol1235_MaximumProfitInJobScheduling ss = new Sol1235_MaximumProfitInJobScheduling();
        ss.jobScheduling2(startTime, endTime, profit);
    }

    // priority queue
    public int jobScheduling3(int[] startTime, int[] endTime, int[] profit) {
        List<List<Integer>> jobs = new ArrayList<>();
        int length = profit.length;
        for (int i = 0; i < length; i++) {
            List<Integer> currJob = new ArrayList<>();
            currJob.add(startTime[i]);
            currJob.add(endTime[i]);
            currJob.add(profit[i]);
            jobs.add(currJob);
        }
        Collections.sort(jobs, Comparator.comparingInt(a -> a.get(0)));
        return findMaxProfit(jobs);
    }
    private int findMaxProfit(List<List<Integer>> jobs) {
        int n = jobs.size(), maxProfit = 0;
        // minHeap having {endTime, profit}
        PriorityQueue<List<Integer>> pq = new PriorityQueue<>( (a, b) -> a.get(0) - b.get(0));
        for (int i = 0; i < n; i++) {
            int start = jobs.get(i).get(0);
            int end = jobs.get(i).get(1);
            int profit = jobs.get(i).get(2);
            // keep popping while the heap is not empty
            while (!pq.isEmpty() && start >= pq.peek().get(0)) {
                maxProfit = Math.max(maxProfit, pq.peek().get(1));
                pq.remove();
            }
            List<Integer> combinedJob = new ArrayList<>();
            combinedJob.add(end);
            combinedJob.add(profit + maxProfit);
            pq.add(combinedJob);

        }
        // update the value of maxProfit by comparing with
        // profit of jobs that exits in the heap
        while (!pq.isEmpty()) {
            maxProfit = Math.max(maxProfit, pq.peek().get(1));
            pq.remove();
        }

        return maxProfit;
    }
}
