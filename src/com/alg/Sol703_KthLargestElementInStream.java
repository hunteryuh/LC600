package com.alg;
/*
Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Implement KthLargest class:

KthLargest(int k, int[] nums) Initializes the object with the integer k and the stream of integers nums.
int add(int val) Appends the integer val to the stream and returns the element representing the kth largest element in the stream.


Example 1:

Input
["KthLargest", "add", "add", "add", "add", "add"]
[[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
Output
[null, 4, 5, 5, 8, 8]

Explanation
KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
kthLargest.add(3);   // return 4
kthLargest.add(5);   // return 5
kthLargest.add(10);  // return 5
kthLargest.add(9);   // return 8
kthLargest.add(4);   // return 8
 */

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */

public class Sol703_KthLargestElementInStream {

    // Time complexity: O(N⋅log(N)+M⋅log(k)), N the length of nums, M the number of calls to add()
    class KthLargest {
        private int k;
        private PriorityQueue<Integer> pq;

        public KthLargest(int k, int[] nums) {
            this.k = k;
            this.pq = new PriorityQueue<>();
            for (int num: nums) {
                pq.offer(num);
            }
            while (pq.size() > k) {
                pq.poll();
            }
        }

        public int add(int val) {
            pq.offer(val);
            if (pq.size() > k) {
                pq.poll();
            }
            return pq.peek();
        }
    }

    class KthLargest2 {

        private int k;
        private Queue<Integer> minHeap = new PriorityQueue<>();

        public KthLargest2(int k, int[] nums) {
            this.k = k;
            for (int num : nums) {
                add(num);
            }
        }

        public int add(int val) {
            minHeap.add(val);
            if (minHeap.size() > k) minHeap.poll();
            return minHeap.peek();
        }
    }
}
