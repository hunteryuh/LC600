package com.alg.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
You are given an array of non-overlapping intervals intervals
where intervals[i] = [starti, endi] represent the start and the end of the ith interval and
intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval.

Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).

Return intervals after the insertion.



Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 */
public class Sol57_InsertIntervals {
    public static class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new LinkedList<>();
        int i = 0;
        while ( i < intervals.size() && intervals.get(i).end < newInterval.start){
            res.add(intervals.get(i++));
        }
        //merger overlapping
        while(i < intervals.size() && intervals.get(i).start <= newInterval.end){
            newInterval = new Interval(
                    Math.min(newInterval.start,intervals.get(i).start),
                            Math.max(newInterval.end, intervals.get(i).end));

            i++;
        }
        res.add(newInterval);
        while ( i < intervals.size()) {
            res.add(intervals.get(i++));
        }
        return res;
    }

    // method 2
    public static List<Interval> insert2(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<>();
        for (Interval i : intervals){
            if (newInterval == null || i.end < newInterval.start){
                res.add(i);
            } else if (i.start > newInterval.end) {
                // i.end >= newInterval.start
                res.add(newInterval);
                res.add(i);
                newInterval = null;
            }else {
                // overlap
                newInterval.start = Math.min(newInterval.start, i.start);
                newInterval.end = Math.max(newInterval.end, i.end);
            }
        }
        if (newInterval != null) {
            res.add(newInterval);  // need to avoid again in the end
        }
        return res;
    }

    // method 2 with int arrays
    // https://leetcode.com/problems/insert-interval/discuss/21636/Java-solution-with-explanation-of-algorithm
    public int[][] insert2(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        for(int[] interval: intervals) {
            if (interval[1] < newInterval[0]) {
                res.add(interval);
            } else if (newInterval[1] < interval[0]) {
                res.add(newInterval);
                // we could have inserted cur as well but that will make
                // merge complex. Just carry this as newInterval for further
                //  iteration to help merge.
                newInterval = interval;
//                res.add(interval);
            } else {
                int start = Math.min(interval[0], newInterval[0]);
                int end = Math.max(interval[1], newInterval[1]);
                newInterval[0] = start;
                newInterval[1] = end;
//                System.out.println(Arrays.toString(newInterval));
            }
        }
        //add the new interval that may be missing.
        res.add(newInterval);
        return res.toArray(new int[res.size()][]);
    }

    // method 2.1 from gucheng
    public int[][] insert3(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        for (int[] cur : intervals) {
            if (newInterval == null || cur[1] < newInterval[0]) {
                res.add(cur);
            } else if (newInterval[1] < cur[0]) {
//                res.addAll(List.of(newInterval, cur));
                res.addAll(Arrays.asList(newInterval, cur));
                newInterval = null;
            } else {
                newInterval[0] = Math.min(cur[0], newInterval[0]);
                newInterval[1] = Math.max(cur[1], newInterval[1]);
            }
        }
        if (newInterval != null) {
            res.add(newInterval);
        }
        return res.toArray(new int[0][]); // 0 will be updated as the actual list size in the logic
    }

    public static void main(String[] args) {
        int[][] intervals = {
                {1,2}, {3,5}, {6,7}, {8,10}, {12,16}
        };
        int[] newInterval = {4,8};
        Sol57_InsertIntervals ss = new Sol57_InsertIntervals();
        int[][] res = ss.insert2(intervals, newInterval);
//        int[][] res = ss.insert(intervals, newInterval);
        System.out.println(Arrays.deepToString(res));
//        Interval e1 = new Interval(1,2);
//        Interval e2 = new Interval(3,5);
//        Interval e3 = new Interval(6,7);
//        Interval e4 = new Interval(8,11);
//        Interval e5 = new Interval(18,21);
//        Interval enew = new Interval(4,9);
//
//        List<Interval> lists = new ArrayList<>();
//        lists.add(e1);lists.add(e2);lists.add(e3);lists.add(e4);lists.add(e5);
//        List<Interval> newlists = insert2(lists,enew);
//
//        List<Interval> lists2 = new ArrayList<>();
//        List<Interval> newlists2 = insert2(lists2,enew);
//
//        for(Interval e: newlists) {
//            System.out.println(e.start+", "+e.end);
//        }

    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        int i = 0;
        int n = intervals.length;
        while (i < n && intervals[i][1] < newInterval[0]) {
            res.add(intervals[i]); // add to res before increase i
            i++;
        }
        for(int[] aa: res) {
            System.out.println(Arrays.toString(aa));
        }

        //  1 2   6 9  9 11    20  24
        //      5     10
//        int[] toadd = newInterval;
        // start of the new interval is already smaller than end of previous interval in intervals
        // after the while loop above,
        // so compare its (the new interval's end to the start of interval[i]
        while (i < n && newInterval[1] >= intervals[i][0]) {
            int start = Math.min(intervals[i][0], newInterval[0]);
            int end = Math.max(intervals[i][1], newInterval[1] );
            newInterval[0] = start;
            newInterval[1] = end;
            i++;
        }
        res.add(newInterval);
        while (i < n) {
            res.add(intervals[i++]);
        }
        return res.toArray(new int[res.size()][]);
    }
}
