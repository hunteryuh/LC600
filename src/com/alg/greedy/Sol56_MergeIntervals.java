package com.alg.greedy;

import java.util.*;

/**
 * Created by HAU on 11/8/2017.
 */
/*Given a collection of intervals, merge all overlapping intervals.

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].*/
public class Sol56_MergeIntervals {
    public static class Interval {
       int start;
       int end;
       Interval() { start = 0; end = 0; }
       Interval(int s, int e) { start = s; end = e; }
   }
    public static List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1) return intervals;
        List<Interval> res = new ArrayList<>();
        Collections.sort(intervals, new IntervalComparator());
        Interval last = intervals.get(0);

        for (int i = 1; i < intervals.size(); i++){
            Interval cur = intervals.get(i);
            if (cur.start <= last.end){
                last.end = Math.max(last.end, cur.end);
            }else{
                res.add(last);
                last = cur;
            }
        }
        res.add(last);
        return res;
    }
    public static class IntervalComparator implements Comparator<Interval> {
        @Override
        public int compare(Interval a, Interval b){
            return a.start - b.start;
        }
    }

    public static void main(String[] args) {
        Interval a1 = new Interval(1,4);
        Interval a2 = new Interval(2,6);
        Interval a3 = new Interval(8,9);
        Interval a4 = new Interval(10,14);
//        List<Interval> list = new ArrayList<>(Arrays.asList(a1,a2));
//        for ( Interval i: list){
//            System.out.println(i.start + ", "+ i.end);
//        }
        List<Interval> list = new ArrayList<>();
        list.add(a1);
        list.add(a2);
        list.add(a3);
        list.add(a4);
        List<Interval> res = mergeAdv(list);
        for ( Interval i: res){
            System.out.println(i.start + ", "+ i.end);
        }


//        int[][] intervals = {{1,3}, {2,6}, {8,10}, {15,18}};
//        int[][] result = mergeAdv(intervals);
//        System.out.println(Arrays.deepToString(res));
//        System.out.println(res.toString());
    }

    //advanced style  https://www.jiuzhang.com/problem/merge-intervals/
    public static List<Interval> mergeAdv(List<Interval> intervals){
        List<Interval> ans = new ArrayList<>();
        intervals.sort(Comparator.comparing(i -> i.start));//lambda 匿名函数：输入i  返回i.start

        Interval last = null;
        for (Interval item: intervals){
            if (last  == null || last.end < item.start){
                ans.add(item);
                last = item;
            }else {
                last.end = Math.max(last.end, item.end); // Modify the element already in list
            }
        }

        return ans;

    }

    // new input

    /*
    Example 1:

Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
Example 2:

Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
     */
    public static int[][] mergeIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparing(i -> i[0]));

        LinkedList<int[]> result = new LinkedList<>();

        for (int[] interval : intervals) {
            if (result.isEmpty() || result.getLast()[1] < interval[0]) {
                result.add(interval);
            } else {
                result.getLast()[1] = Math.max(interval[1], result.getLast()[1]);
            }
        }

//        int[][] output = new int[result.size()][2];
//        int i = 0;
//        for (int[] interval: result) {
//            output[i][0] = interval[0];
//            output[i][1] = interval[1];
//            i++;
//        }

//        return output;
        // this part is to get an availability duration (longer than 1) from the calenders/intervals
        List<int[]> availability = new ArrayList<>();
        for (int i = 0; i < result.size() - 1; i++) {
            if (result.get(i+1)[0] - result.get(i)[1] > 1) {
                availability.add(new int[]{result.get(i)[1], result.get(i+1)[0]});
            }
        }

//        int[][] aval = availability.toArray(new int[availability.size()][]);
//        System.out.println(Arrays.deepToString(aval));

        for(int[] avail: availability) {
            System.out.println(Arrays.toString(avail));
        }
//        int[][] res = new int[list.size()][2];
//        for (int i =0; i < list.size(); i++) {
//            res[i] = list.get(i);
//        }
//        return res;
        return result.toArray(new int[result.size()][]);

    }

    // user List instead of LinkedList variable  (no getLast method, so use .get(xx.size()-1) instead)
    public static int[][] mergeIntervals2(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparing(i -> i[0]));
        List<int[]> res = new LinkedList<>();

        for (int i = 0; i < intervals.length; i++) {
            int[] cur = intervals[i];
            if (res.isEmpty() || res.get(res.size() -1)[1] < cur[0]) {
                res.add(cur);
            } else {
                res.get(res.size() - 1)[1] = Math.max(res.get(res.size() - 1)[1], cur[1]);
            }
        }

        return res.toArray(new int[res.size()][]);
    }

    // https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0056.%E5%90%88%E5%B9%B6%E5%8C%BA%E9%97%B4.md
    public int[][] merge0(int[][] intervals) {
        List<int[]> res = new LinkedList<>();
        Arrays.sort(intervals, (o1, o2) -> Integer.compare(o1[0], o2[0]));

        int start = intervals[0][0];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] > intervals[i - 1][1]) {
                res.add(new int[]{start, intervals[i - 1][1]});
                start = intervals[i][0];
            } else {
                intervals[i][1] = Math.max(intervals[i][1], intervals[i - 1][1]);
            }
        }
        res.add(new int[]{start, intervals[intervals.length - 1][1]});
        return res.toArray(new int[res.size()][]);
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> (o1[0] - o2[0]));
        List<int[]> res = new ArrayList<>();
        int start = intervals[0][0];
        int n = intervals.length;
        for (int i = 1; i < n; i++) {
            if (intervals[i][0] > intervals[i-1][1]) {
                res.add(intervals[i-1]);
                start = intervals[i][0]; // update start
            } else {
                int end = Math.max(intervals[i-1][1], intervals[i][1]);
                intervals[i][0] = start;
                intervals[i][1] = end;
            }
        }
        res.add(intervals[n-1]);

        return res.toArray(new int[res.size()][]);
    }

}
