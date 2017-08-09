package com.alg;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by HAU on 6/26/2017.
 */
/*Given a stream of integers and a window size, calculate the moving average of
all integers in the sliding window.
        For example,
        MovingAverage m = new MovingAverage(3);
        m.next(1) = 1
        m.next(10) = (1 + 10) / 2
        m.next(3) = (1 + 10 + 3) / 3
        m.next(5) = (10 + 3 + 5) / 3*/

public class Sol_MovingAverageFromDataStream {
    public static class MovingAverage{
        int n;
        int sum;
        Queue<Integer> queue;

        public MovingAverage(int size){
            n = size;
            sum = 0;
            queue = new LinkedList<>();
        }
        public double next(int val){
            queue.add(val);
            sum += val;
            if (queue.size() > n){
                int front = queue.poll();
                sum -= front;
            }

            return (double)sum/queue.size();
        }
    }


    public static void main(String[] args) {
        MovingAverage ma = new MovingAverage(3);
        double res = ma.next(4);
        System.out.println(res);
        res = ma.next(1);
        System.out.println(res);

        res = ma.next(7);
        System.out.println(res);
        res = ma.next(7);
        System.out.println(res);
        res = ma.next(8);
        System.out.println(res);
    }
}
