package com.alg;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/*Given a stream of integers and a window size,
calculate the moving average of all integers in the sliding window.

Implement the MovingAverage class:

    MovingAverage(int size) Initializes the object with the size of the window size.
    double next(int val) Returns the moving average of the last size values of the stream.

For example,

MovingAverage m = new MovingAverage(3);
m.next(1) = 1
m.next(10) = (1 + 10) / 2
m.next(3) = (1 + 10 + 3) / 3
m.next(5) = (10 + 3 + 5) / 3
*/
public class Sol346_MovingAverageFromDataStream {
    class MovingAverage {
        private  int[] window;
        private int n, insert;
        private int sum;
        /** Initialize your data structure here. */
        public MovingAverage(int size) {
            window = new int[size];
            insert = 0;
            sum = 0;
        }

        public double next(int val) {
            if (n < window.length) n++;
            sum -= window[insert];
            sum += val;
            window[insert] = val;
            insert = (insert + 1)% window.length;// 0th, 1st, 2nd...
            return (double)sum/n;
        }
    }

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
    class MovingAverage2 {
        int n, sum;
        private Queue<Integer> queue;
        public MovingAverage2(int size) {
            queue = new LinkedList<>();
            this.n = size;
        }

        public double next(int val) {
            queue.add(val);
            sum += val;
            if (queue.size() > n) {
                sum -= queue.poll();
            }
            return (double)sum/queue.size();
        }
    }
}
