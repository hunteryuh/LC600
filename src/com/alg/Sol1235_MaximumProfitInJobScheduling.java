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
    // top down dp, binary search from editorial https://leetcode.com/problems/maximum-profit-in-job-scheduling/editorial/
    // maximum number of jobs are 50000
    int[] memo = new int[50001];
    private int findNextJob(int[] startTime, int lastEndingTime) {
        int start = 0, end = startTime.length - 1, nextIndex = startTime.length;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (startTime[mid] >= lastEndingTime) {
                nextIndex = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return nextIndex;
    }

    private int findMaxProfit(List<List<Integer>> jobs, int[] startTime, int n, int position) {
        // 0 profit if we have already iterated over all the jobs
        if (position == n) {
            return 0;
        }

        // return result directly if it's calculated
        if (memo[position] != -1) {
            return memo[position];
        }

        // nextIndex is the index of next non-conflicting job
        int nextIndex = findNextJob(startTime, jobs.get(position).get(1));

        // find the maximum profit of our two options: skipping or scheduling the current job
        int maxProfit = Math.max(findMaxProfit(jobs, startTime, n, position + 1),
                jobs.get(position).get(2) + findMaxProfit(jobs, startTime, n, nextIndex));

        // return maximum profit and also store it for future reference (memoization)
        return memo[position] = maxProfit;
    }

    public int jobScheduling_0(int[] startTime, int[] endTime, int[] profit) {
        List<List<Integer>> jobs = new ArrayList<>();

        // marking all values to -1 so that we can differentiate
        // if we have already calculated the answer or not
        Arrays.fill(memo, -1);

        // storing job's details into one list
        // this will help in sorting the jobs while maintaining the other parameters
        int length = profit.length;
        for (int i = 0; i < length; i++) {
            ArrayList<Integer> currJob = new ArrayList<>();
            currJob.add(startTime[i]);
            currJob.add(endTime[i]);
            currJob.add(profit[i]);
            jobs.add(currJob);
        }
        jobs.sort(Comparator.comparingInt(a -> a.get(0)));

        // binary search will be used in startTime so store it as separate list
        for (int i = 0; i < length; i++) {
            startTime[i] = jobs.get(i).get(0);
        }

        return findMaxProfit(jobs, startTime, length, 0);
    }
    // bottom up dp O(n^2)
    public int jobScheduling0(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] jobs = new int[n][3];
        for (int i = 0;i < n; i++) {
            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(jobs, (a, b) -> a[1] - b[1]); // sort by endTime
        int[] dp = new int[n];
        dp[0] = jobs[0][2]; // / dp[i] stores the profit for jobs till jobs[i] including jobs[i]
        for (int i = 1; i < n; i++) {
            int start = jobs[i][0];
            int compatible = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (jobs[j][1] < start) {
                    compatible = dp[j];
                    break;
                }
            }
            dp[i] += jobs[i][2] + ((compatible == -1)? 0: dp[i]);
            dp[i] = Math.max(dp[i], dp[i-1]);
        }
        return dp[n - 1];
    }
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
    // find the maximum profit given maximum number of concurrent jobs allowed k
    // https://leetcode.com/problems/maximum-profit-in-job-scheduling/description/?envType=company&envId=airbnb&favoriteSlug=airbnb-three-months
    public int jobScheduling_k(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] jobs = new int[n][3];
        for (int i = 0; i < n; i++) {
            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(jobs, (a, b) -> a[1] - b[1]); // sort by end time
        // Sorted jobs in ascending order to end times
        int[][] dp = new int[n][4]; // at most number k + 1

        dp[0][1] = profit[0];
        dp[0][2] = dp[0][1];
        dp[0][3] = dp[0][1];

        for(int i = 1; i < n; i++) {
            // Previous Secton Overlaping
            if(endTime[i-1] >= startTime[i]){
                int pi = profit[i];
                // dp[i][1]
                int piPlusBinary = pi;
                int l = search(jobs, i);
                if(l != -1) {
                    piPlusBinary += dp[l][1];
                }
                dp[i][1] = Math.max(dp[i-1][1], piPlusBinary);

                // dp[i][2]
                dp[i][2] = Math.max(dp[i-1][2], dp[i-1][1] + pi);

                // dp[i][3]
                dp[i][3] = Math.max(dp[i-1][3], dp[i-1][2] + pi);

                // Formula:
                // dp[i][x]
                // dp[i][x] = Math.max(dp[i-1][x], dp[i-1][x-1] + pi);
            }
            // Previous Secton NOT Overlaping
            else if(endTime[i-1]< startTime[i]){
                int pi = profit[i];
                // dp[i][1]
                dp[i][1] = dp[i-1][1] + pi;

                // dp[i][2]
                dp[i][2] = dp[i-1][2] + pi;

                // dp[i][3]
                dp[i][3] = dp[i-1][3] + pi;

                // Formula:
                // dp[i][x]
                // dp[i][x] = dp[i-1][x] + pi;
            }
        }

        int last = n - 1;
        return Math.max(Math.max(dp[last][1], dp[last][2]), dp[last][3]);

// Formula:
// return maxOfX(dp[last][1], dp[last][2], dp[last][3], ..., dp[last][x]);
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
    // return the index of the largest element < target in the given list (assume there must exist one element < target)

    private int binarySearchlargestSmaller(List<Integer> list, int target) {
        int left = 0;
        int right = list.size() - 1;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) < target) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        return list.get(right) < target ? right : left;
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
        sortedJobs.put(0, 0); // (endTime, maxProfit)
//        List<Integer> selectedJobs = new ArrayList<>();
//        for (int[] job : jobs) {
        for (int i = 0; i < jobs.length; i++) {
            int[] job = jobs[i];
            // 找最后一个结束早于我们新插入的WORK的PROFIT， 加上目前的
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

    /*
    asked on 12/8/2023
    You're a dasher, and you want to try planning out your schedule. You can view a list of deliveries along with their associated start time, end time, and dollar amount for completing the order. Assuming dashers can only deliver one order at a time, determine the maximum amount of money you can make from the given deliveries.

The inputs are as follows:

int start_time: when you plan to start your schedule
int end_time: when you plan to end your schedule
int d_starts[n]: the start times of each delivery[i]
int d_ends[n]: the end times of each delivery[i]
int d_pays[n]: the pay for each delivery[i]
The output should be an integer representing the maximum amount of money you can make by forming a schedule with the given deliveries.

Example #1
start_time = 0
end_time = 10
d_starts = [2, 3, 5, 7]
d_ends = [6, 5, 10, 11]
d_pays = [5, 2, 4, 1]
Expected output: 6
https://leetcode.com/discuss/interview-question/1320711/Doordash-or-Phone-Screen
FOLLOW UP 1: Get the jobs you selected
FOLLOW UP 2: SCALE FOR N ORDERS

SIMPLE SOLUTION:

run the scheduler once -> remove the jobs that yield max profit -> run the scheduler a second time with the remaining jobs -> remove the jobs -> ... -> repeat until we get k overlaps
     */
    // https://leetcode.com/problems/maximum-profit-in-job-scheduling/solutions/2850788/java-5-solutions/
    // doordash dasher tries to schedule their deliveries
    //5.heap
    //Runtime: 52 ms, faster than 73.86% of Java online submissions for Maximum Profit in Job Scheduling.
    //Memory Usage: 72.3 MB, less than 59.94% of Java online submissions for Maximum Profit in Job Scheduling.
    //Time: O(N + N * log(N) + N * log(N) + N); Space: O(N + logN + N)
    //Time: O(N * log(N)); Space: O(N)
    public int jobScheduling5(int[] startTime, int[] endTime, int[] profit) {
        int[][] tasks = new int[startTime.length][3];
        for (int i = 0; i < startTime.length; i++) {
            tasks[i][0] = startTime[i];
            tasks[i][1] = endTime[i];
            tasks[i][2] = profit[i];
        }
        Arrays.sort(tasks, (a, b) -> a[0] == b[0] ? a[1] - b[1]: a[0] - b[0]); // sort with start time, then end time

        int maxProfit = 0;
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        for (int i = 0; i < tasks.length; i++) {
            while (!minHeap.isEmpty() && minHeap.peek()[0] <= tasks[i][0]) {
                maxProfit = Math.max(maxProfit, minHeap.peek()[1]);
                minHeap.poll();
            }
            minHeap.offer(new int[]{tasks[i][1], tasks[i][2] + maxProfit});
        }

        while (!minHeap.isEmpty()) {
            maxProfit = Math.max(maxProfit, minHeap.peek()[1]);
            minHeap.poll();
            // can be combined with one:
            // maxProfit = Math.max(maxProfit, minHeap.poll()[1]);
        }
        return maxProfit;
    }

    // priority queue
    //Time: O(N + N * log(N) + N * log(N) + N); Space: O(N + logN + N)
    //Time: O(N * log(N)); Space: O(N)
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
        // minHeap having {endTime, profit} as a pair (list)
        PriorityQueue<List<Integer>> pq = new PriorityQueue<>( (a, b) -> a.get(0) - b.get(0));
        for (int i = 0; i < n; i++) {
            int start = jobs.get(i).get(0);
            int end = jobs.get(i).get(1);
            int profit = jobs.get(i).get(2);
            // keep popping while the heap is not empty  and jobs are not conflicting
            // update the value of maxProfit
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

    // similar to above but pq of int[]
    public int jobScheduling6(int[] startTime, int[] endTime, int[] profit) {
        List<List<Integer>> jobs = new ArrayList<>();
        int n = startTime.length;
        for (int i = 0; i < n; i++) {
            List<Integer> job = new ArrayList<>();
            job.add(startTime[i]);
            job.add(endTime[i]);
            job.add(profit[i]);
            jobs.add(job);
        }

        Collections.sort(jobs, (a,b) -> a.get(0) - b.get(0));
        // pq {endTime, profit as of endTime}
        int maxprofit = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>( (a, b) -> (a[0] - b[0]));
        for (int i = 0 ; i < n; i++) {
            while (!pq.isEmpty() && pq.peek()[0] <= jobs.get(i).get(0)) { // the job with smallest endtime ends before the start of the next job
                maxprofit = Math.max(maxprofit, pq.peek()[1]);
                pq.poll();
            }
            int end = jobs.get(i).get(1);
            int currentProfit = jobs.get(i).get(2) + maxprofit;
            pq.offer(new int[]{end, currentProfit});
        }
        while (!pq.isEmpty()) {
            maxprofit = Math.max(maxprofit, pq.peek()[1]);
            pq.poll();
        }
        return maxprofit;
    }
}
