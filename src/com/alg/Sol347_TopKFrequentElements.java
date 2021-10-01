package com.alg;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.



Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:

Input: nums = [1], k = 1
Output: [1]
 */
public class Sol347_TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i : nums) {
            freq.put(i, freq.getOrDefault(i, 0) + 1);
        }
        int[] res = new int[k];
        // build a min-heap  最小堆， 堆顶要弹出的是Freq 最小的，这样最后留下的是频率最高的
        PriorityQueue<Integer> pq = new PriorityQueue<>(
                (a, b) -> freq.get(a) - freq.get(b)
        );
        for (int i : freq.keySet()) {
            pq.add(i);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        int index = 0;
        while (!pq.isEmpty()) {
            res[index++] = pq.poll();
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3};
        int k = 2;
        Sol347_TopKFrequentElements ss = new Sol347_TopKFrequentElements();
        int[] f = ss.topKFrequent(nums, k);
        System.out.println(Arrays.toString(f));
    }
}
