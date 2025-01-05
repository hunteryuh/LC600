package com.alg;

import java.util.*;

/**
 * Created by HAU on 11/25/2017.
 */
/*Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Therefore, return the max sliding window as [3,3,5,5,6,7]*/
public class Sol239_SlidingWindowMaximum {

    // time O(N)
    // Let's use a deque (double-ended queue), the structure which pops from / pushes to either side with the same O(1)\mathcal{O}(1)O(1) performance.
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int[] res = new int[nums.length - k + 1];
        LinkedList<Integer> deque = new LinkedList<>();
        for (int i = 0; i < nums.length; i++){
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {  // can change while to if as it only removes one item each time i++
                deque.pollFirst();  // remove numbers out of range [i-k+1, i]
                // poll, same as pollFirst, remove the head element
            }
            // compare last elements with nums[i]
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();  // same as removeLast
            }
            deque.offerLast(i);  // add the end of the list, same as offer, addLast
            if (i >= k - 1) {
                // You want to ensure the deque window only has decreasing elements. That way, the leftmost element is always the largest.
                res[i-k+1] = nums[deque.peekFirst()]; // same as peek, check the first element in the queue
            }

        }
        return res;
    }
    // pq O(nlogn)
    public int[] maxSlidingWindowPriorityQueue(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n - k + 1];
        PriorityQueue<int[]> pq = new PriorityQueue<>( (a,b) -> b[1] - a[1]);
        for (int i = 0; i < k; i++) {
            pq.offer(new int[]{i, nums[i]}); // index, value at index i
        }
        res[0] = pq.peek()[1];
        int index = 1;
        for (int i = k; i < n; i++) {
            while (!pq.isEmpty() && i - pq.peek()[0] >= k) {
                pq.poll();
            }
            pq.offer(new int[]{i, nums[i]});
            res[index] = pq.peek()[1];
            index++;
        }
        return res;
    }

    public int[] maxSlidingWindow_2(int[] a, int k) {
        if (a == null || k <= 0) {
            return new int[0];
        }
        int n = a.length;
        int[] res = new int[n-k+1];
        int ri = 0;
        // store index
        Deque<Integer> q = new ArrayDeque<>();
//        Deque<Integer> q = new LinkedList(); // or new LinkedList()
        for (int i = 0; i < a.length; i++) {
            int startWindowIndex = i - k + 1;
            // remove numbers out of range k; 左出q, 保证窗口大小 k-1
            while (!q.isEmpty() && i - q.peekFirst() >= k) {
                q.pollFirst();
            }
            // remove smaller numbers in k range as they are useless; 右出q，保证递减队列
            while (!q.isEmpty() && a[q.peekLast()] < a[i]) {
                q.pollLast();
            }
            // q contains index... r contains content； 进q, 此时q.size == k
            q.offer(i); // q.offerLast(i);
            // 使用q 左边最大值
            if (startWindowIndex >= 0) {
                res[startWindowIndex] = a[q.peekFirst()];
            }
        }
        return res;
    }

    // sliding window
    // monotonic queue
    // decreasing, store the index >=
    // pq O(nlogn)


    public static void main(String[] args) {
        int[] nums = {1,-3,-1,-3,5,3,6,9};
        int k = 3;
        int[] res = maxSlidingWindow(nums,k);
        System.out.println(Arrays.toString(res));
    }

    public int[] maxSlidingWindow2(int[] nums, int k) {
        int l = nums.length;
        int size = l - k + 1;
        int[] res = new int[size];
        CustomQueue q = new CustomQueue();
        for (int i = 0; i < k; i++) {
            q.add(nums[i]);
        }
        int index = 0;
        res[index] = q.peek();
        index++;
        for (int i = k ; i < l ;i++) {
            q.poll(nums[i-k]);
            q.add(nums[i]);
            res[index++] = q.peek();
        }
        return res;
    }

    class CustomQueue {
        private Deque<Integer> queue = new LinkedList<>();
        public CustomQueue(){

        }
        void add(int v) {
            while (!queue.isEmpty() && v > queue.getLast()) { // loop to find the all values from the end that is smaller than v
                queue.pollLast();
            }
            queue.addLast(v);
        }

        int peek() {
            return queue.peek();
        }

        void poll(int v) {
            if (!queue.isEmpty() && v == queue.getFirst()) {
                queue.pollFirst();
            }
        }
    }
}
