package com.alg;

import sun.rmi.runtime.Log;

import java.util.*;

/*
Design a logger system that receives a stream of messages along with their timestamps.
Each unique message should only be printed at most every 10 seconds (i.e. a message printed at timestamp t will prevent other identical messages from being printed until timestamp t + 10).

All messages will come in chronological order. Several messages may arrive at the same timestamp.

Implement the Logger class:

    Logger() Initializes the logger object.
    bool shouldPrintMessage(int timestamp, string message) Returns true if the message should be printed in the given timestamp, otherwise returns false.



Example 1:

Input
["Logger", "shouldPrintMessage", "shouldPrintMessage", "shouldPrintMessage", "shouldPrintMessage", "shouldPrintMessage", "shouldPrintMessage"]
[[], [1, "foo"], [2, "bar"], [3, "foo"], [8, "bar"], [10, "foo"], [11, "foo"]]
Output
[null, true, true, false, false, false, true]

Explanation
Logger logger = new Logger();
logger.shouldPrintMessage(1, "foo");  // return true, next allowed timestamp for "foo" is 1 + 10 = 11
logger.shouldPrintMessage(2, "bar");  // return true, next allowed timestamp for "bar" is 2 + 10 = 12
logger.shouldPrintMessage(3, "foo");  // 3 < 11, return false
logger.shouldPrintMessage(8, "bar");  // 8 < 12, return false
logger.shouldPrintMessage(10, "foo"); // 10 < 11, return false
logger.shouldPrintMessage(11, "foo"); // 11 >= 11, return true, next allowed timestamp for "foo" is 11 + 10 = 21



Constraints:

    0 <= timestamp <= 109
    Every timestamp will be passed in non-decreasing order (chronological order).
    1 <= message.length <= 30
    At most 104 calls will be made to shouldPrintMessage.


 */
public class Sol359_LoggerRateLimiter {
    // method 1: map, issue is the space complexity will grow
    public class Logger0 {

        private Map<String, Integer> map = new HashMap<>();
        public boolean shouldPrintMessage(int timestamp, String message) {
            if (timestamp < map.getOrDefault(message, 0))
                return false;
            map.put(message, timestamp + 10);
            return true;
        }
    }

    // queue + set
    // https://leetcode.com/problems/logger-rate-limiter/solutions/349733/Simple-Java-solution-using-Queue-and-Set-for-slow-learners-like-myself/
    public class Logger {

        private final Queue<LogObject> queue;
        private final Set<String> set;
        public Logger() {
            queue = new LinkedList<>();
            set = new HashSet<>();
        }
        public boolean shouldPrintMessage(int time, String message) {
            while (!queue.isEmpty() && time - queue.peek().timestamp >= 10) {
                set.remove(queue.poll().messages);
            }
            if (set.contains(message)) {
                return false;
            }
            queue.offer(new LogObject(time, message));
            set.add(message);
            return true;
        }

        private final class LogObject {
            int timestamp;
            String messages;
            public LogObject(int timestamp, String messages) {
                this.timestamp = timestamp;
                this.messages = messages;
            }
        }
    }

    // queue + map without additional data structure
    class Logger3 {

        Queue<String> q; //holds all the words that came in last 10 seconds
        HashMap<String, Integer> map; //stores (word existing in q, timestamp last printed)

        /** Initialize your data structure here. */
        public Logger3() {
            q = new LinkedList<>();
            map = new HashMap<>();
        }

        /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
         If this method returns false, the message will not be printed.
         The timestamp is in seconds granularity. */
        public boolean shouldPrintMessage(int timestamp, String message) {

            //process q, get rid of words printed 10 seconds ago
            while(!q.isEmpty() && map.get(q.peek()) <= timestamp - 10) {
                map.remove(q.poll());
            }

            //see if word exists because the Queue still contains it, so it is not too old yet
            // this message can be different from the ones that are removed in the queue due to age
            if (map.containsKey(message)) { //word already exists in q
                return false;
            } // removed redundant else
            q.offer(message);
            map.put(message, timestamp);
            return true;
        }
    }
}
