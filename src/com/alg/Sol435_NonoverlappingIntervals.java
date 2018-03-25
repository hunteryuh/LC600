package com.alg;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

/**
 * Created by HAU on 11/21/2017.
 */
/*Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

Note:
You may assume the interval's end point is always bigger than its start point.
Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.
Example 1:
Input: [ [1,2], [2,3], [3,4], [1,3] ]

Output: 1

Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.*/
public class Sol435_NonoverlappingIntervals {
    public static class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }
    public static int eraseOverlapIntervals(Interval[] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, new myComparator());
        int end = intervals[0].end;
        int count = 1;
        for (int i = 1; i < intervals.length; i++){
            if(intervals[i].start >= end){
                count++;
                end = intervals[i].end;
            }
        }
        return intervals.length - count;
        /*

    Time complexity : O(nlog(n)). Sorting takes  time.

    Space complexity : O(1) No extra space is used.
*/
    }

    public static class myComparator implements Comparator<Interval>{
        public int compare(Interval a, Interval b){
            return a.end - b.end;
        }
    }

    public static void main(String[] args) {
        Interval a1 = new Interval(1,3);
        Interval a2 = new Interval(3,5);
        Interval a3 = new Interval(6,13);
        Interval a4 = new Interval(3,8);
        Interval[] ia = {a1,a2,a3,a4};
        System.out.println(eraseOverlapIntervals(ia));
        System.out.println(eraseOverlap(ia));

    }
    // method 2, sort by start
    public static int eraseOverlap(Interval[] intervals){
        if (intervals.length == 0) return 0;
        //Arrays.sort(intervals, new startComparator());
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
        int prev = 0, count = 0;
        for ( int i = 1; i < intervals.length; i++){
            if (intervals[prev].end > intervals[i].start){
                count++;
                if (intervals[prev].end > intervals[i].end) prev = i;
            }else{
                prev = i;
            }
        }
        return count;

    }

    private static class startComparator implements Comparator<Interval>{
        public int compare(Interval a, Interval b){
            return a.start - b.start;
        }
    }
}
