package com.alg;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/*
You are implementing a program to use as your calendar. We can add a new event if adding the event will not cause a triple booking.

A triple booking happens when three events have some non-empty intersection (i.e., some moment is common to all the three events.).

The event can be represented as a pair of integers start and end that represents a booking on the half-open interval [start, end), the range of real numbers x such that start <= x < end.

Implement the MyCalendarTwo class:

    MyCalendarTwo() Initializes the calendar object.
    boolean book(int start, int end) Returns true if the event can be added to the calendar successfully without causing a triple booking. Otherwise, return false and do not add the event to the calendar.



Example 1:

Input
["MyCalendarTwo", "book", "book", "book", "book", "book", "book"]
[[], [10, 20], [50, 60], [10, 40], [5, 15], [5, 10], [25, 55]]
Output
[null, true, true, true, false, true, true]

Explanation
MyCalendarTwo myCalendarTwo = new MyCalendarTwo();
myCalendarTwo.book(10, 20); // return True, The event can be booked.
myCalendarTwo.book(50, 60); // return True, The event can be booked.
myCalendarTwo.book(10, 40); // return True, The event can be double booked.
myCalendarTwo.book(5, 15);  // return False, The event cannot be booked, because it would result in a triple booking.
myCalendarTwo.book(5, 10); // return True, The event can be booked, as it does not use time 10 which is already double booked.
myCalendarTwo.book(25, 55); // return True, The event can be booked, as the time in [25, 40) will be double booked with the third event, the time [40, 50) will be single booked, and the time [50, 55) will be double booked with the second event.



Constraints:

    0 <= start < end <= 109
    At most 1000 calls will be made to book.


 */
// no triple booking is allowed
public class Sol731_MyCalendarII {
    class MyCalendarTwo {

        // count airplane template, sweep line, max 3:
        TreeMap<Integer, Integer> bookings; // key: start
        public MyCalendarTwo() {
            bookings = new TreeMap<>();
        }

        public boolean book(int start, int end) {
            bookings.put(start, bookings.getOrDefault(start, 0) + 1);
            bookings.put(end, bookings.getOrDefault(end, 0) - 1);

            int count = 0;
            for (int value: bookings.values()) {
                count += value;
                 if (count >= 3) {
                     bookings.put(start, bookings.get(start) - 1);
                     bookings.put(end, bookings.get(end) + 1);
                     if (bookings.get(start) == 0) {
                         bookings.remove(start);
                     }
                     return false;
                 }
            }
            return true;
        }
    }

    class MyCalendarTwo2 {

        // store original intervals and 1-time overlap intervals
        List<int[]> calender;
        List<int[]> overlaps;

        public MyCalendarTwo2() {
            calender = new ArrayList<>();
            overlaps = new ArrayList<>();
        }

        public boolean book(int start, int end) {
            for (int[] overlap: overlaps) {
                if (start < overlap[1] && end > overlap[0]) {
                    return false;
                }
            }
            for (int[] cal: calender) {
                if (start < cal[1] && end > cal[0]) {
                    int intervalStart = Math.max(start, cal[0]);
                    int intervalEnd = Math.min(end, cal[1]);
                    overlaps.add(new int[]{intervalStart, intervalEnd});
                }
            }
            calender.add(new int[]{start, end});
            return true;
        }
    }

}
