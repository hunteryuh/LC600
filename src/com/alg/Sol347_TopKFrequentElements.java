package com.alg;

import java.util.*;

/*
Given an integer array nums and an integer k, return the k most frequent elements.
You may return the answer in any order.



Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:

Input: nums = [1], k = 1
Output: [1]
 */
public class Sol347_TopKFrequentElements {
    // time O(Nlogk)
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i : nums) {
            freq.put(i, freq.getOrDefault(i, 0) + 1);
        }

        // build a min-heap  最小堆， 堆顶要弹出的是Freq 最小的，这样最后留下的是频率最高的, time nLogk, space O(k)
        PriorityQueue<Integer> pq = new PriorityQueue<>(
                (a, b) -> freq.get(a) - freq.get(b)
        );
        for (int i : freq.keySet()) {
            pq.add(i);
            if (pq.size() > k) {
                pq.poll();
            }
        }
//        int[] res = new int[k];
//        int index = 0;
////         klogk
//        while (!pq.isEmpty()) {
//            res[index++] = pq.poll();
//        }
//        return res;
//        return pq.stream().mapToInt(Integer::intValue).toArray();
        return pq.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3};
        int k = 2;
        Sol347_TopKFrequentElements ss = new Sol347_TopKFrequentElements();
        int[] f = ss.topKFrequent(nums, k);
        System.out.println(Arrays.toString(f)); //[ 2, 1]
    }

    // max Heap  最简单也是最直观毫无优化的解法，直接用maxHeap放入所有数字，拿出heap头部最大的k个数字  n Log n
    public int[] topKFrequent2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> map.get(b) - map.get(a));
        pq.addAll(map.keySet());
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = pq.poll();
        }
        return res;
    }

    // java O(n) bucket sort
    public int[] topKFreq(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int n : nums) {
            freqMap.put(n, freqMap.getOrDefault(n, 0) + 1);
        }
        // bucket sort on freq
        List<Integer>[] bucket = new List[nums.length + 1];
        for (int i = 0; i < bucket.length; i++) {
            bucket[i] = new ArrayList<>();
        }
        for (int num: freqMap.keySet()) {
            bucket[freqMap.get(num)].add(num);
        }
        // gather result
        List<Integer> res = new ArrayList<>();
        for (int i = bucket.length - 1; i >= 0; i--) {
            res.addAll(bucket[i]);
            if (res.size() >= k) {
                break;
            }
        }
        return res.stream().mapToInt(x -> x).toArray();

    }
}
