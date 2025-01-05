package com.alg;
/*
We are given a list schedule of employees, which represents the working time for each employee.

Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.

Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.

(Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined).  Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.



Example 1:

Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
Output: [[3,4]]
Explanation: There are a total of three employees, and all common
free time intervals would be [-inf, 1], [3, 4], [10, inf].
We discard any intervals that contain inf as they aren't finite.

Example 2:

Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
Output: [[5,6],[7,9]]



Constraints:

    1 <= schedule.length , schedule[i].length <= 50
    0 <= schedule[i].start < schedule[i].end <= 10^8


 */

import java.util.*;

public class Sol759_EmployeeFreeTime {

    class Interval {
        public int start;
        public int end;

        public Interval() {}

        public Interval(int _start, int _end) {
            start = _start;
            end = _end;
        }
    };

    // using TreeMap
    // https://leetcode.com/problems/employee-free-time/solutions/175081/sweep-line-java-with-explanations/
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> res = new ArrayList<>();
        Map<Integer, Integer> map = new TreeMap<>(); // sorted map, key: time point; value: score
        for (List<Interval> list: schedule) {
            for (Interval interval : list) {
                map.put(interval.start, map.getOrDefault(interval.start, 0) + 1);
                map.put(interval.end, map.getOrDefault(interval.end, 0) - 1);
            }
        }
        int start = -1;  // non-overlapping interval start point.
        int score = 0; // when the sum of the points become 0 then we've found a new non-overlapping start point.
        for (int point : map.keySet()) {
            score += map.get(point);
            if (score == 0 /*&& start == -1*/) {
                start = point;
            } else if (start != -1 /*&& score != 0 */) {
                // means that we've found the next point that follows the starting point of the non-overlapping interval
                res.add(new Interval(start, point));
                start = -1; // reset start
            }

        }
        return res;
    }

    // priority queue
    // https://leetcode.com/problems/employee-free-time/solutions/195308/java-priorityqueue-solution-time-complexity-o-n-log-k/
    // time O(NlogN) N is the total number of intervals in all the lists. K is the number of people.
    // as it adds all intervals in the queue
    public List<Interval> employeeFreeTime2(List<List<Interval>> avails) {
        List<Interval> result = new ArrayList<>();
        PriorityQueue<Interval> pq = new PriorityQueue<>((a, b) -> a.start - b.start); // sort by start

        for (List<Interval> e : avails) {
            pq.addAll(e);
        }

        Interval prev = pq.poll();
        while (!pq.isEmpty()) {
            Interval cur = pq.poll(); // will always be polled after each step in the loop
            //if there is a gap between previous ending and current start then,
            //we have found a free interval
            if (cur.start > prev.end) {
                result.add(new Interval(prev.end, cur.start));
            }
            //if prev has greater ending then it will override the current ending
            if (prev.end < cur.end) {
                prev = cur;
            }
//            if (temp.end < pq.peek().start) { // no intersect
//                result.add(new Interval(temp.end, pq.peek().start));
//                temp = pq.poll(); // becomes the next temp interval
//            } else { // intersect or sub merged
//                temp = temp.end < pq.peek().end ? pq.peek() : temp;
//                pq.poll();
//            }
        }
        return result;
    }

    // nLgK N is the total number of intervals in all the lists. K is the number of people.
    // https://leetcode.com/problems/employee-free-time/solutions/195308/java-priorityqueue-solution-time-complexity-o-n-log-k/
    public List<Interval> employeeFreeTime3(List<List<Interval>> schedule) {
        PriorityQueue<int[]> allEmployees = new PriorityQueue<>((a, b) -> schedule.get(a[0]).get(a[1]).start - schedule.get(b[0]).get(b[1]).start);
        // each element in the priority queue has 2 indexes.
        // 0 points to the List on the List of schedules. 1 points to the index within the list selected by 0

        for (int i = 0; i < schedule.size(); i++) {
            allEmployees.add(new int[] { i, 0 });
        }

        List<Interval> employeesFreeTime = new ArrayList<>();

        int[] prev = allEmployees.peek();

        while (!allEmployees.isEmpty()) {
            int[] current = allEmployees.poll();

            Interval previousInterval = schedule.get(prev[0]).get(prev[1]);
            Interval currentInterval = schedule.get(current[0]).get(current[1]);
            //No overlap, so add the gap in intervals
            if (currentInterval.start > previousInterval.end) {
                employeesFreeTime.add(new Interval(previousInterval.end, currentInterval.start));
            }

            if (currentInterval.end > previousInterval.end) {
                prev = current;
            }
            //Add the next interval into the priority queue, add the next element
            //from the  list whose interval was polled
            if (schedule.get(current[0]).size() > current[1] + 1) {
                allEmployees.add(new int[] { current[0], current[1] + 1 });
            }
        }

        return employeesFreeTime;
    }

    // just arraylist
    public List<Interval> employeeFreeTime4(List<List<Interval>> avails) {
        List<Interval> result = new ArrayList<>();
        List<Interval> timeLine = new ArrayList<>();
        avails.forEach(e -> timeLine.addAll(e));
        Collections.sort(timeLine, ((a, b) -> a.start - b.start));

        Interval temp = timeLine.get(0);
        for(Interval each : timeLine) {
            if(temp.end < each.start) {
                result.add(new Interval(temp.end, each.start));
                temp = each;
            } else {
                temp = temp.end < each.end ? each : temp;
            }
        }
        return result;
    }
}
