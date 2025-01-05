package com.alg;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/*
Suppose LeetCode will start its IPO soon. In order to sell a good price of its shares to Venture Capital, LeetCode would like to work on some projects to increase its capital before the IPO. Since it has limited resources, it can only finish at most k distinct projects before the IPO. Help LeetCode design the best way to maximize its total capital after finishing at most k distinct projects.

You are given n projects where the ith project has a pure profit profits[i] and a minimum capital of capital[i] is needed to start it.

Initially, you have w capital. When you finish a project, you will obtain its pure profit and the profit will be added to your total capital.

Pick a list of at most k distinct projects from given projects to maximize your final capital, and return the final maximized capital.

The answer is guaranteed to fit in a 32-bit signed integer.



Example 1:

Input: k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]
Output: 4
Explanation: Since your initial capital is 0, you can only start the project indexed 0.
After finishing it you will obtain profit 1 and your capital becomes 1.
With capital 1, you can either start the project indexed 1 or the project indexed 2.
Since you can choose at most 2 projects, you need to finish the project indexed 2 to get the maximum capital.
Therefore, output the final maximized capital, which is 0 + 1 + 3 = 4.

Example 2:

Input: k = 3, w = 0, profits = [1,2,3], capital = [0,1,2]
Output: 6



Constraints:

    1 <= k <= 105
    0 <= w <= 109
    n == profits.length
    n == capital.length
    1 <= n <= 105
    0 <= profits[i] <= 104
    0 <= capital[i] <= 109

https://leetcode.com/problems/ipo/
 */
public class Sol502_IPO {
    // https://leetcode.com/problems/ipo/solutions/3219987/day-54-c-priority-queue-easiest-beginner-friendly-sol/
    // time : O(Nlogn + klogn) = O(nlogn)
    // space O(n + n) = O (n)
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        int[][] projects = new int[n][2];
        for (int i = 0; i < n; i++) {
            projects[i][0] = profits[i];
            projects[i][1] = profits[i];
        }
        Arrays.sort(projects, (a, b) -> a[0] - b[0]);
        int i = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        // now we are going to run a loop k times( at most), to choose our best possible
        // k(at most) projects to maximise our profits
        while (k-- > 0) {
            while (i < n && projects[i][0] < w) {
                pq.offer(projects[i][1]);
                i++;
            }
            // if max_profit( priority queue) is empty, it means either of 2 things:
            // 1. we have exhausted by processing all our projects
            // 2. the projects left, have capital needs > our current capital
            // so just break
            if (pq.isEmpty()) {
                break;
            }
            w += pq.poll(); // get max profitable job in each iteration as greedy approach
        }
        return w;
    }
}
