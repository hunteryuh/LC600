package com.alg;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/*Median is the middle value in an ordered integer list.
If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

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
        private Queue<Integer> large = new PriorityQueue<>();  // min heap
        /** initialize your data structure here. */
        public MedianFinder() {

        }
        // add a number into the data structure
        public void addNum(int num) {
            large.add(num);
            small.add(large.poll());
            if (large.size() < small.size()){
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

        MedianFinder2 m2 = new MedianFinder2();
        m2.addNum(1);
        m2.addNum(2);
        m2.addNum(3);
        m2.addNum(4);
        double r2 = m2.findMedian();
        System.out.println(r2);
    }

    // time O(5 * logn)
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();  // the min will be polled
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>( (a,b) -> b - a);
    public void addNum(int num) {
        minHeap.add(num);
        maxHeap.add(minHeap.poll());
        if (minHeap.size() < maxHeap.size()) {
            minHeap.add(maxHeap.poll());
        }

    }
    public double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
        return minHeap.peek();
    }

    // if reverse the size, it works too
    PriorityQueue<Integer> minH;
    PriorityQueue<Integer> maxH;

    /** initialize your data structure here. */
    public Sol295_FindMedianFromDataStream() {
        minH = new PriorityQueue<Integer>();
        /* By default Java provides min heap. For max heap, we need to pass in a appropriate comparator */
        maxH = new PriorityQueue<Integer>(1, new Comparator<Integer>(){
            public int compare(Integer ob1, Integer ob2){
                return ob2.compareTo(ob1);
            }
        });
    }

    public void addNum2(int num) {
        maxH.add(num);
        minH.add(maxH.poll());
        if(minH.size()>maxH.size()){
            maxH.add(minH.poll());
        }
    }

    public double findMedian2() {
        if (minH.size() == maxH.size())
            return (double) (maxH.peek()+minH.peek())*0.5 ;
        else
            return (double) maxH.peek();
    }

    // follow-up 1 and 2
    // https://github.com/RodneyShag/LeetCode_solutions/blob/master/Solutions/Find%20Median%20from%20Data%20Stream.md
    //  #1 - If all integer numbers from the stream are between 0 and 100, how would you optimize it
    static class MedianFinder2 {

        int[] A = new int[101];
        int n = 0;
        public void addNum(int num) {
            A[num]++;
            n++;
        }
        // O(100) = O(1)
        public double findMedian() {
            int count = 0;
            int i = 0;
            while (count < n / 2) {
                count += A[i];
                i++;
            }
            if (n % 2 == 1) {
                return i;
            }
            // if n is even
            int j = i;
            while (count < n/2 + 1) {
                count += A[j];
                j++;
            }
            return (i-1 + j-1) / 2.0;  // i-1 and j-1 are the two medians
        }
    }

    // follow up 2
    static class MedianFinder3 {

        int[] A = new int[101];
        int n = 0;
        int countLessThanZero = 0;
        // int countGreater100 = 0;
        public void addNum(int num) {
            if (num < 0) {
                countLessThanZero++;
            } else {
                A[num]++;
            }
            n++;
        }
        // O(100) = O(1)
        public double findMedian() {
            int count = countLessThanZero;
            int i = 0;
            while (count < n / 2) {
                count += A[i];
                i++;
            }
            if (n % 2 == 1) {
                return i;
            }
            // if n is even
            int j = i;
            while (count < n/2 + 1) {
                count += A[j];
                j++;
            }
            return (i-1 + j-1) / 2.0;  // i-1 and j-1 are the two medians
        }
    }

}
