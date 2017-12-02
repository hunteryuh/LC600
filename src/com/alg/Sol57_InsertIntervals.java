package com.alg;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by HAU on 12/1/2017.
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
        for ( Interval i : intervals){
            if( newInterval == null || i.end < newInterval.start){
                res.add(i);
            }else if (i.start > newInterval.end){
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
        if(newInterval != null){
            res.add(newInterval);  // need to avoid again in the end
        }
        //
        return res;
    }

    public static void main(String[] args) {
        Interval e1 = new Interval(1,2);
        Interval e2 = new Interval(3,5);
        Interval e3 = new Interval(6,7);
        Interval e4 = new Interval(8,11);
        Interval e5 = new Interval(18,21);
        Interval enew = new Interval(4,9);

        List<Interval> lists = new ArrayList<>();
        lists.add(e1);lists.add(e2);lists.add(e3);lists.add(e4);lists.add(e5);
        List<Interval> newlists = insert2(lists,enew);

        List<Interval> lists2 = new ArrayList<>();
        List<Interval> newlists2 = insert2(lists2,enew);

        for(Interval e: newlists ){
            System.out.println(e.start+", "+e.end);
        }
    }
}
