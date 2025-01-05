package com.alg;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.TreeSet;

/*
Given an array of integers nums and an integer limit, return the size of the longest
non-empty subarray such that the absolute difference between any two elements of this subarray
is less than or equal to limit.

Example 1:

Input: nums = [8,2,4,7], limit = 4
Output: 2
Explanation: All subarrays are:
[8] with maximum absolute diff |8-8| = 0 <= 4.
[8,2] with maximum absolute diff |8-2| = 6 > 4.
[8,2,4] with maximum absolute diff |8-2| = 6 > 4.
[8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
[2] with maximum absolute diff |2-2| = 0 <= 4.
[2,4] with maximum absolute diff |2-4| = 2 <= 4.
[2,4,7] with maximum absolute diff |2-7| = 5 > 4.
[4] with maximum absolute diff |4-4| = 0 <= 4.
[4,7] with maximum absolute diff |4-7| = 3 <= 4.
[7] with maximum absolute diff |7-7| = 0 <= 4.
Therefore, the size of the longest subarray is 2.

Example 2:

Input: nums = [10,1,2,4,7,2], limit = 5
Output: 4
Explanation: The subarray [2,4,7,2] is the longest since the maximum absolute diff is |2-7| = 5 <= 5.

Example 3:

Input: nums = [4,2,2,2,4,4,2,2], limit = 0
Output: 3



Constraints:

    1 <= nums.length <= 105
    1 <= nums[i] <= 109
    0 <= limit <= 109


 */
public class Sol1438_LongestContinuousSubarrayWithDiffLessLimit {
    // https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/solutions/609771/java-c-python-deques-o-n/
    // treemap to get max and min at the same time
    // time O(n logn)
    public int longestSubarray(int[] nums, int limit) {
        int i = 0;
        int j;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (j = 0; j < nums.length; j++) {
            map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);
            if (map.lastKey() - map.firstKey() > limit) {
                map.put(nums[i], map.get(nums[i]) - 1);
                if (map.get(nums[i]) == 0) {
                    map.remove(nums[i]);
                }
                i++;
            }
            System.out.println("step " + j + ": i = " + i); // i and j will move same distance (1) if  > limit is not met
        }
        return j - i;
    }

    // two deque
    // Time O(N), space O(n)
    // https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/solutions/609743/java-detailed-explanation-sliding-window-deque-o-n/
    public int longestSubarray2(int[] A, int limit) {
        Deque<Integer> maxQueue = new ArrayDeque<>();
        Deque<Integer> minQueue = new ArrayDeque<>();
        int i = 0;
        int j;
        for (j = 0; j < A.length; j++) {
            while (!maxQueue.isEmpty() && A[j] > maxQueue.peekLast()) {
                maxQueue.pollLast(); // head of the queue is largest and monotonic decreasing
            }
            while (!minQueue.isEmpty() && A[j] < minQueue.peekLast()) {
                minQueue.pollLast();
            }
            maxQueue.offerLast(A[j]);
            minQueue.offerLast(A[j]);
            if (maxQueue.peekFirst() - minQueue.peekFirst() > limit) {
                // peek is equivalent to peekFirst
                if (maxQueue.peekFirst() == A[i]) {
                    maxQueue.pollFirst();
                }
                if (minQueue.peekFirst() == A[i]) {
                    minQueue.pollFirst();
                }
                i++;
            }
        }
        return j - i;
    }

    // https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/solutions/609705/java-simple-o-n-log-n-sliding-window-treeset/
    // treeset time: O(nlogn)
    public int longestSubarray3(int[] nums, int limit) {
        int left = 0;
        TreeSet<Integer> set = new TreeSet<>((a, b) -> nums[a] == nums[b] ? a - b : nums[a] - nums[b]);
        set.add(0);
        int res = 1;
        for (int right = 1; right < nums.length; right++) {
            set.add(right);
            while (nums[set.last()] - nums[set.first()] > limit) {
                set.remove(left++);
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }

    // priority queue
    public int longestSub(int[] A, int limit) {
        PriorityQueue<int[]> minQ = new PriorityQueue<>( (a,b) -> a[0] - b[0]);
        PriorityQueue<int[]> maxQ = new PriorityQueue<>( (a,b) -> b[0] - a[0]);
        int j = 0;
        int i;
        int res = 0;
        for (i = 0; i < A.length; i++) {
            minQ.add(new int[]{A[i], i});
            maxQ.add(new int[]{A[i], i});
            while (maxQ.peek()[0] - minQ.peek()[0] > limit) {
                j = Math.min(maxQ.peek()[1], minQ.peek()[1]) + 1;
                while (minQ.peek()[1] < j) minQ.poll();
                while (maxQ.peek()[1] < j) maxQ.poll();

            }
            res = Math.max(res, i - j + 1);
        }
        return res;

    }

    public static void main(String[] args) {
        int[] nums = {4,2,2,2,4,4,2,2};
        int limit = 0;
        Sol1438_LongestContinuousSubarrayWithDiffLessLimit ss = new Sol1438_LongestContinuousSubarrayWithDiffLessLimit();
        System.out.println(ss.longestSubarray(nums, limit));
    }
}
