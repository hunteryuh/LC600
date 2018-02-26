package com.alg;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/*Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

Examples:
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.
For example:

addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3)
findMedian() -> 2*/
public class Sol295_FindMedianFromDataStream {
    static class MedianFinder {
        private Queue<Integer> small = new PriorityQueue<>(1, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        //private Queue<Integer> small = new PriorityQueue<>(Collections.reverseOrder());
        // lamda
        //private Queue<Integer> sm = new PriorityQueue<>(((o1, o2) -> o2 - o1));
        private Queue<Integer> large = new PriorityQueue<>();
        /** initialize your data structure here. */
        public MedianFinder() {

        }
        // add a number into the data structure
        public void addNum(int num) {
            large.add(num);
            small.add(large.poll());
            if(large.size() < small.size()){
                large.add(small.poll());
            }
        }

        public double findMedian() {
            if (large.size() > small.size()){
                return large.peek();
            }
            return (large.peek() + small.peek())/2.0;
        }
    }

    public static void main(String[] args) {
        MedianFinder obj = new MedianFinder();
        obj.addNum(2);
        obj.addNum(3);
        obj.addNum(4);
        double res = obj.findMedian();
        System.out.println(res);
    }
}
