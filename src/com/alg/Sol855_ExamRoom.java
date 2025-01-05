package com.alg;
/*
There is an exam room with n seats in a single row labeled from 0 to n - 1.

When a student enters the room, they must sit in the seat that maximizes the distance to the closest person. If there are multiple such seats, they sit in the seat with the lowest number. If no one is in the room, then the student sits at seat number 0.

Design a class that simulates the mentioned exam room.

Implement the ExamRoom class:

ExamRoom(int n) Initializes the object of the exam room with the number of the seats n.
int seat() Returns the label of the seat at which the next student will set.
void leave(int p) Indicates that the student sitting at seat p will leave the room. It is guaranteed that there will be a student sitting at seat p.


Example 1:

Input
["ExamRoom", "seat", "seat", "seat", "seat", "leave", "seat"]
[[10], [], [], [], [], [4], []]
Output
[null, 0, 9, 4, 2, null, 5]

Explanation
ExamRoom examRoom = new ExamRoom(10);
examRoom.seat(); // return 0, no one is in the room, then the student sits at seat number 0.
examRoom.seat(); // return 9, the student sits at the last seat number 9.
examRoom.seat(); // return 4, the student sits at the last seat number 4.
examRoom.seat(); // return 2, the student sits at the last seat number 2.
examRoom.leave(4);
examRoom.seat(); // return 5, the student sits at the last seat number 5.



Constraints:

1 <= n <= 109
It is guaranteed that there is a student sitting at seat p.
At most 104 calls will be made to seat and leave.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

/**
 * Your ExamRoom object will be instantiated and called as such:
 * ExamRoom obj = new ExamRoom(n);
 * int param_1 = obj.seat();
 * obj.leave(p);
 */
public class Sol855_ExamRoom {
    class ExamRoom {

        int capacity;
        TreeSet<Integer> seats;
        public ExamRoom(int n) {
            capacity = n;
            seats = new TreeSet<>();
        }

        /*
  Return 0 for first attempt ( as mentioned in question)
  Otherwise, we need to calculate the max distance by checking the whole treeset : O(n) time.
  Note that "distance" variable is initialized to first appearing seat,
  why because the distance calculation is based on current seat and the seat before that.
  Find the maximum distance and update the seat number accordingly.
  distance calculation -> (current seat - previous seat )/ 2
  Update the max distance at each step.
  New seat number will be ->  previous seat number + max distance

  Now, before returning the end result,  check for one more edge case:
  That is, if the max distance calculated is less than ->  capacity-1-seats.last()

  Why because -> if last seat number in treeset is far from last position,
  then  the largest distance possible is the last position.

*/
        // https://leetcode.com/problems/exam-room/solutions/1331530/easy-to-understand-java-solution-with-explanation-treeset/
        public int seat() {
            if (seats.isEmpty()) {
                seats.add(0);
                return 0;
            }
            int seatNumber = 0;
            Integer prev = null;
            int distance = seats.first();
            for (int seat : seats) {
                if (prev != null) {
                    int d = (seat - prev) / 2;
                    if (d > distance) {
                        distance = d;
                        seatNumber = prev + distance;
                    }
                }
                prev = seat;
            }
            if (distance < capacity - 1 - seats.last()) {
                seatNumber = capacity - 1;
            }
            seats.add(seatNumber);
            return seatNumber;
        }

        public void leave(int p) {
            // simply remove the seat number from treeset
            seats.remove(p);
        }
    }

    class ExamRoom2 {
        int N;
        ArrayList<Integer> L = new ArrayList<>();
        public ExamRoom2(int n) {
            N = n;
        }

        public int seat() {
            if (L.size() == 0) {
                L.add(0);
                return 0;
            }
            int d = Math.max(L.get(0), N - 1 - L.get(L.size() - 1));
            for (int i = 0; i < L.size() - 1; ++i) d = Math.max(d, (L.get(i + 1) - L.get(i)) / 2);
            if (L.get(0) == d) {
                L.add(0, 0);
                return 0;
            }
            for (int i = 0; i < L.size() - 1; ++i)
                if ((L.get(i + 1) - L.get(i)) / 2 == d) {
                    L.add(i + 1, (L.get(i + 1) + L.get(i)) / 2);
                    return L.get(i + 1);
                }
            L.add(N - 1);
            return N - 1;
        }

        public void leave(int p) {
            for (int i = 0; i < L.size(); ++i) if (L.get(i) == p) L.remove(i);
        }
    }

    // https://leetcode.com/problems/exam-room/solutions/193295/my-elegant-java-solution-beats-99-84/?orderBy=most_votes

    class ExamRoom3 {
        class Interval {
            int start;
            int end;
            int length;
            public Interval(int start, int end) {
                this.start = start;
                this.end = end;
                if (start == 0 || end == N - 1) {
                    this.length = end - start;
                } else {
                    this.length = (end - start) / 2;
                }
            }
        }
        private PriorityQueue<Interval> pq; // The pq only save Intervals that are available for later use.
        private int N;

        public ExamRoom3(int N) {
            this.pq = new PriorityQueue<>((a, b) -> a.length != b.length ? b.length - a.length : a.start - b.start);
            this.N = N;
            pq.offer(new Interval(0, N - 1));
        }

        //O(log n)
        public int seat() {
            Interval in = pq.poll();
            int result;
            if (in.start == 0) {
                result = 0;
            } else if (in.end == N - 1) {
                result = N - 1;
            } else {
                result = in.start + in.length;
            }

            if (result > in.start) {
                pq.offer(new Interval(in.start, result - 1));
            }
            if (in.end > result) {
                pq.offer(new Interval(result + 1, in.end));
            }
            return result;
        }

        // O(n)
        public void leave(int p) {
            List<Interval> list = new ArrayList(pq);
            Interval prev = null;
            Interval next = null;
            for (Interval in: list) {
                if (in.end + 1 == p) {
                    prev = in;
                }
                if (in.start - 1 == p) {
                    next = in;
                }
            }
            pq.remove(prev);
            pq.remove(next);
            pq.offer(new Interval(prev == null ? p : prev.start, next == null ? p : next.end));

        }
    }
}
