package com.alg;

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
        if ( intervals == null || intervals.size() <= 1) return intervals;
        List<Interval> res = new ArrayList<>();
        Collections.sort(intervals, new IntervalComparator());
        Interval last = intervals.get(0);

        for ( int i = 1; i < intervals.size(); i++){
            Interval cur = intervals.get(i);
            if ( cur.start <= last.end){
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
        List<Interval> res = merge(list);
        for ( Interval i: res){
            System.out.println(i.start + ", "+ i.end);
        }
        //System.out.println(res);
    }

    //advanced style
    public static List<Interval> mergeAdv(List<Interval> intervals){
        List<Interval> ans = new ArrayList<>();
        intervals.sort(Comparator.comparing(i -> i.start));//lambda 匿名函数：输入i  返回i.start

        Interval last = null;
        for (Interval item: intervals){
            if( last  == null || last.end < item.start){
                ans.add(last);
                last = item;
            }else {
                last.end = Math.max(last.end, item.end);
            }
        }
        return ans;

    }

}
