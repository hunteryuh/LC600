package com.alg;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by HAU on 2/1/2018.
 */
/*Implement a MyCalendar class to store your events. A new event can be added if adding the event will not cause a double booking.

Your class will have the method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end), the range of real numbers x such that start <= x < end.

A double booking happens when two events have some non-empty intersection (ie., there is some time that is common to both events.)

For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully without causing a double booking. Otherwise, return false and do not add the event to the calendar.

Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
Example 1:
MyCalendar();
MyCalendar.book(10, 20); // returns true
MyCalendar.book(15, 25); // returns false
MyCalendar.book(20, 30); // returns true
Explanation:
The first event can be booked.  The second can't because time 15 is already booked by another event.
The third event can be booked, as the first event takes every time less than 20, but not including 20.
Note:*/
public class Sol729_MyCalendarI {
    class MyCalendar {
        List<int[]> calendar;

        public MyCalendar() {
            calendar = new ArrayList<>();
        }

        // O(n^2)
        public boolean book(int start, int end) {
            for(int[] cal: calendar) {
                if (cal[0] < end && cal[1] > start) { // newStart< oldEnd && newEnd > oldStart
                    return false;
                }
            }
            calendar.add(new int[]{start,end});
            return true;
        }

    }

    // TreeMap, sort by the key: start time, value: end time
    // time O(nlogn)
    class MyCalendar2 {
        TreeMap<Integer, Integer> map;

        public MyCalendar2() {
            map = new TreeMap<>();
        }

        // n Log n
        public boolean book(int start, int end) {
            Integer leftStart = map.floorKey(start); // greatest key which is <= start
            Integer rightStart = map.ceilingKey(start); // >= start
            if ((leftStart == null || map.get(leftStart) <= start)  && (
                 rightStart == null || end <= rightStart
                )) {
                map.put(start, end);
                return true;
            }
            return false;
        }
    }

}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */

