package com.alg.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

/*
Given an integer array nums and an integer k, modify the array in the following way:

choose an index i and replace nums[i] with -nums[i].
You should apply this process exactly k times. You may choose the same index i multiple times.

Return the largest possible sum of the array after modifying it in this way.



Example 1:

Input: nums = [4,2,3], k = 1
Output: 5
Explanation: Choose index 1 and nums becomes [4,-2,3].
Example 2:

Input: nums = [3,-1,0,2], k = 3
Output: 6
Explanation: Choose indices (1, 2, 2) and nums becomes [3,1,0,2].
Example 3:

Input: nums = [2,-3,-1,5,-4], k = 2
Output: 13
Explanation: Choose indices (1, 4) and nums becomes [2,3,-1,5,4].
https://leetcode.com/problems/maximize-sum-of-array-after-k-negations/

 */
public class Sol1005_MaximizeSumOfArrayAfterKNegations {
    public int largestSumAfterKNegations(int[] nums, int k) {
        nums = IntStream.of(nums).boxed().sorted((o1, o2) -> Math.abs(o2) - Math.abs(o1)).mapToInt(Integer::intValue).toArray();
        int n = nums.length;

        for (int i = 0; i < n ; i++) {
            if (nums[i] < 0 && k > 0) {
                nums[i] = -nums[i];
                k--;
            }

        }
        if (k > 0 && k % 2 == 1) {
            nums[n-1] = -nums[n-1];
        }
        return Arrays.stream(nums).sum();
    }

    public int largestSumWithKNeg(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        Arrays.stream(nums).forEach(i -> queue.offer(i));
        while (k > 0) {
            queue.offer(-queue.poll());
            k--;
        }
        return queue.stream().mapToInt(Integer::intValue).sum();
    }

    // same as above, minHeap
    public int largestSumAfterKNegations2(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i : nums) {
            queue.offer(i);
        }
        while (k > 0) {
            int min = queue.poll();
            queue.offer(-min);
            k--;
        }
        int sum = 0;
        while (!queue.isEmpty()) {
            sum += queue.poll();
        }
        return sum;
    }

    // sort twice
    public int largestSumAfterKNegations3(int[] A, int K) {
        Arrays.sort(A);

        for(int i = 0; i < A.length && K > 0 && A[i] < 0; i++, K--) A[i] = -A[i];
        Arrays.sort(A);
        if(K%2 == 1) A[0] = -A[0];
        return IntStream.of(A).sum();
    }
}
