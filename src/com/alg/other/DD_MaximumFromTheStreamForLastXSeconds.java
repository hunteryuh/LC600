package com.alg.other;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/*
Given a streaming data of the form (timestamp, value), find the maximum value in the stream in the last X seconds.

Assume time is monotonically increasing.
Assume time is in the order of seconds.
max_value() function finds the max in the last X seconds.

StreamProcessor(5) // last 5 seconds
set_value(0, 5)
set_value(1, 6)
set_value(2, 4)
max_value(3) = 6 -> always the current time

class StreamProcessor:
def init(self, x):
 self.x = x

def set_value(self, t, v):
 pass

def max_value(self, cur_t):
 pass

https://leetcode.com/discuss/interview-question/1302614/DoorDash-Onsite-Interview-(new-question-again!)
 */
public class DD_MaximumFromTheStreamForLastXSeconds {
    class StreamProcessor{
        private int window;
        Deque<int[]> queue = new ArrayDeque<>(); // {timestamp, value}
        // monotonic queue decreasing  (first element is largest, latest/last element is smallest)
//        Map<Integer, Integer> map = new HashMap<>();
//set good state of the queue -> only last x elements needed in queue
        public StreamProcessor(int window) {
            this.window = window;
        }

        public void setValue(int time, int value) {
            int buffer = time - window;
            while (!queue.isEmpty() && queue.peek()[0] < buffer) {
                queue.pollFirst();
            }

            while(!queue.isEmpty() && queue.peekLast()[1] < value) {
                queue.pollLast();
            }
            queue.offerLast(new int[]{time, value});
        }
        public int getMax(int time) {
            int buffer = time - window;
            while (!queue.isEmpty() && queue.peek()[0] < buffer) {
                queue.pollFirst();
            }
            if (queue.isEmpty()) {
                return -1;
            }
            return queue.peekFirst()[1];
        }
    }
}
