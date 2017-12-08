package com.alg;

/**
 * Created by HAU on 12/7/2017.
 */
/*Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

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
            if(n < window.length) n++;
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
}
