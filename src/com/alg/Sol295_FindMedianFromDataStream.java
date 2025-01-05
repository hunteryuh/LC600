package com.alg;

import java.util.*;

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

    // time O(5 * logn)
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();  // the min will be polled. stores the larger half of the inputs
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>( (a,b) -> b - a); // the max will be polled; stores the smaller half of the inputs
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

    public static void main(String[] args) {
        MedianFinder obj = new MedianFinder();
        obj.addNum(2);
        obj.addNum(3);
        obj.addNum(4);
        double res = obj.findMedian();
        System.out.println(res);

        MedianFinder2 m2 = new MedianFinder2();
//        m2.addNum(1);
        m2.addNum(2);
        m2.addNum(30);
        m2.addNum(4);
        double r2 = m2.findMedian2();
        System.out.println(r2);
    }

    // follow-up 1 and 2
    // https://github.com/RodneyShag/LeetCode_solutions/blob/master/Solutions/Find%20Median%20from%20Data%20Stream.md
    //  #1 - If all integer numbers from the stream are between 0 and 100, how would you optimize it
    static class MedianFinder2 {

        int[] buckets;
        int totalCount;

        public MedianFinder2() {
            buckets = new int[101];
            totalCount = 0;
        }
        public void addNum(int num) {
            buckets[num]++;
            totalCount++;
        }
        // O(100) = O(1), fail for count == 1
//        public double findMedian() {
//              int medianCount = totalCount % 2 == 0? totalCount / 2: (totalCount + 1)/2;
//            int i = 0;
//            while (count < medianCount ) {
//                count += bucket[i];  // 2,2 ,10
//                i++;
//            }
//            System.out.println(i);
//            if (totalCount % 2 == 1) {
//                return i;
//            }
//            // if n is even  1,2,3,4
//            int j = i;
//            while (count <totalCount/2 + 1) {
//                count += bucket[j];
//                j++;
//            }
//            return (i-1 + j-1) / 2.0;  // i-1 and j-1 are the two medians
//        }

        public double findMedian2() {
//            int middle = totalCount / 2 ; //   count = 1, middle = 1; 3, 2
            int medianCount = totalCount % 2 == 0 ? totalCount / 2 : (totalCount + 1)/2;
//            int prevBucket = -1;
            int currentCount = 0;

            int idx = 0;
            while (idx <= 100) {
                currentCount += buckets[idx];
                if (currentCount >= medianCount) {
                    break;
                }
                idx++;
            }

            if (totalCount % 2 == 0) { // 1 2 2 4 or 1 2 3 4
                if (currentCount > medianCount) {
                    return idx;  // 1 2 2 4
                }
                for (int i = idx + 1; i < 101; i++) {
                    if (buckets[i] != 0) {
                        return (idx + i) / 2.0;  // 1 2 3 4
                    }
                }
            }
            return idx;

//            for (int i = 0; i < buckets.length; i++) {
//                currentCount += buckets[i];
//                System.out.println("i = " + i);
//                System.out.println(currentCount);
//                if (currentCount >= middle) {
//                    if (totalCount % 2 != 0 || currentCount > middle) {
//                        return i; // Odd total count or middle element
//                    } else {
//                        // Even total count, find the next element to calculate the average
//                        for (int j = i + 1; j < buckets.length; j++) {
//                            if (buckets[j] > 0) {
//                                return (i + j) / 2.0;
//                            }
//                        }
//                    }
//                }
//                prevBucket = i;
//            }
//
//            return prevBucket; // This handles the case where count = 1
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
