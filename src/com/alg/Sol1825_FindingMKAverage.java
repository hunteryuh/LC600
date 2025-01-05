package com.alg;

import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;

/*
You are given two integers, m and k, and a stream of integers. You are tasked to implement a data structure that calculates the MKAverage for the stream.

The MKAverage can be calculated using these steps:

    If the number of the elements in the stream is less than m you should consider the MKAverage to be -1. Otherwise, copy the last m elements of the stream to a separate container.
    Remove the smallest k elements and the largest k elements from the container.
    Calculate the average value for the rest of the elements rounded down to the nearest integer.

Implement the MKAverage class:

    MKAverage(int m, int k) Initializes the MKAverage object with an empty stream and the two integers m and k.
    void addElement(int num) Inserts a new element num into the stream.
    int calculateMKAverage() Calculates and returns the MKAverage for the current stream rounded down to the nearest integer.



Example 1:

Input
["MKAverage", "addElement", "addElement", "calculateMKAverage", "addElement", "calculateMKAverage", "addElement", "addElement", "addElement", "calculateMKAverage"]
[[3, 1], [3], [1], [], [10], [], [5], [5], [5], []]
Output
[null, null, null, -1, null, 3, null, null, null, 5]

Explanation
MKAverage obj = new MKAverage(3, 1);
obj.addElement(3);        // current elements are [3]
obj.addElement(1);        // current elements are [3,1]
obj.calculateMKAverage(); // return -1, because m = 3 and only 2 elements exist.
obj.addElement(10);       // current elements are [3,1,10]
obj.calculateMKAverage(); // The last 3 elements are [3,1,10].
                          // After removing smallest and largest 1 element the container will be [3].
                          // The average of [3] equals 3/1 = 3, return 3
obj.addElement(5);        // current elements are [3,1,10,5]
obj.addElement(5);        // current elements are [3,1,10,5,5]
obj.addElement(5);        // current elements are [3,1,10,5,5,5]
obj.calculateMKAverage(); // The last 3 elements are [5,5,5].
                          // After removing smallest and largest 1 element the container will be [5].
                          // The average of [5] equals 5/1 = 5, return 5



Constraints:

    3 <= m <= 105
    1 <= k*2 < m
    1 <= num <= 105
    At most 105 calls will be made to addElement and calculateMKAverage.


 */
public class Sol1825_FindingMKAverage {
    /*
    /**
 * Your MKAverage object will be instantiated and called as such:
 * MKAverage obj = new MKAverage(m, k);
 * obj.addElement(num);
 * int param_2 = obj.calculateMKAverage();
 */

    // https://leetcode.com/problems/finding-mk-average/solutions/1741331/java-easy-solution-treemap-queue/
    class MKAverage {

        int sum;
        int total;
        int m;
        int k;

        TreeMap<Integer, Integer> map = new TreeMap<>();
        Queue<Integer> queue = new LinkedList<>();
        public MKAverage(int m, int k) {
            this.m = m;
            this.k = k;
        }

        public void addElement(int num) {
            total++;
            sum += num;
            queue.offer(num);
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (total > m) {
                total--;
                int first = queue.poll();
                sum -= first;
                if (map.get(first) == 1) {
                    map.remove(first);
                } else {
                    map.put(first, map.get(first) - 1);
                }
            }
        }

        public int calculateMKAverage() {
            if (total < m) {
                return -1;
            }
            int count = k;
            int temp_sum = sum;
            for (int key: map.keySet()) {
                if (count == 0) {
                    break;
                }
                int val = map.get(key);
                int min = Math.min(count, val); // get smallest k
                count -= min;
                temp_sum -= min * key;
            }

            count = k;
            for (int key: map.descendingKeySet()) {
                if (count == 0) {
                    break;
                }
                int val = map.get(key);
                int min = Math.min(count, val);
                count -= min;
                temp_sum -= min * key;
            }
            return temp_sum / (m - 2 * k);
        }
    }
}
