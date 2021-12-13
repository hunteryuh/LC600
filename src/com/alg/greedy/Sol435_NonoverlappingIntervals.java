package com.alg.greedy;

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

        int[][] intervals = { {1,100}, {11,22}, {1,11}, {2,12}};
        Sol435_NonoverlappingIntervals ss = new Sol435_NonoverlappingIntervals();
        System.out.println(ss.eraseOverlapIntervals3(intervals));

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

    public int eraseOverlapIntervals(int[][] intervals) {
        // Arrays.sort(intervals, (i1, i2) -> i1[1] - i2[1]); // sort by right end; works as well, preserve order for equal case
        Arrays.sort(intervals, (i1, i2) ->
                i1[1] == i2[1] ?  i1[0] - i2[0] : i1[1] - i2[1]); // sort by right end, if equal, sort by left end; does not change the result
        int nonOver = 1;  // initiate as 1
        int currentEnd = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= currentEnd) {
                nonOver++;
                currentEnd = intervals[i][1];
            }
        }
        return intervals.length - nonOver;

    }

    //按左边排序，不管右边顺序。相交的时候取最小的右边。
    public int eraseOverlapIntervals3(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        int n = intervals.length;
//        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));
        Arrays.sort(intervals, ((o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        }));
        int remove = 0;
        int end = intervals[0][1];
        for (int i = 1; i < n; i++) {
            if (intervals[i][0] < end) {
                remove++;
                end = Math.min(end, intervals[i][1]);
            } else {
                end = intervals[i][1];
            }
        }
        return remove;
    }
}
