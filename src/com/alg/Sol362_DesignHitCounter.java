package com.alg;


import com.sun.tools.javac.util.Pair;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/*
Design a hit counter which counts the number of hits received in the past 5 minutes (i.e., the past 300 seconds).

Your system should accept a timestamp parameter (in seconds granularity),
and you may assume that calls are being made to the system in chronological order (i.e., timestamp is monotonically increasing).
Several hits may arrive roughly at the same time.

Implement the HitCounter class:

    HitCounter() Initializes the object of the hit counter system.
    void hit(int timestamp) Records a hit that happened at timestamp (in seconds). Several hits may happen at the same timestamp.
    int getHits(int timestamp) Returns the number of hits in the past 5 minutes from timestamp (i.e., the past 300 seconds).



Example 1:

Input
["HitCounter", "hit", "hit", "hit", "getHits", "hit", "getHits", "getHits"]
[[], [1], [2], [3], [4], [300], [300], [301]]
Output
[null, null, null, null, 3, null, 4, 3]

Explanation
HitCounter hitCounter = new HitCounter();
hitCounter.hit(1);       // hit at timestamp 1.
hitCounter.hit(2);       // hit at timestamp 2.
hitCounter.hit(3);       // hit at timestamp 3.
hitCounter.getHits(4);   // get hits at timestamp 4, return 3.
hitCounter.hit(300);     // hit at timestamp 300.
hitCounter.getHits(300); // get hits at timestamp 300, return 4.
hitCounter.getHits(301); // get hits at timestamp 301, return 3.



Constraints:

    1 <= timestamp <= 2 * 109
    All the calls are being made to the system in chronological order (i.e., timestamp is monotonically increasing).
    At most 300 calls will be made to hit and getHits.


 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);


Follow up: What if the number of hits per second could be huge? Does your design scale?

 */
public class Sol362_DesignHitCounter {
    // https://leetcode.com/problems/design-hit-counter/solution/
    class HitCounter {

        private Queue<Integer> hits;

        public HitCounter() {
            this.hits = new LinkedList<>();
        }

        public void hit(int timestamp) {
            hits.add(timestamp);
        }

        public int getHits(int timestamp) {
            while (!hits.isEmpty()) {
                int diff = timestamp - hits.peek();
                if (diff >= 300) {
                    hits.remove(); // remove head of the queue
                } else {
                    break;
                }
            }
//            while (!hits.isEmpty() && timestamp - hits.peek() >= 300) {
//                hits.poll();
//            }
            return hits.size();
        }
    }


    class HitCounter2 {

        private int total;
        private Deque<Pair<Integer, Integer>> hits;

        public HitCounter2() {
            this.total = 0;
            this.hits = new LinkedList<>();
        }

        // Time O(1)
        public void hit(int timestamp) {
            if (hits.isEmpty() || hits.getLast().fst != timestamp) {
                hits.add(new Pair(timestamp, 1));
            } else {
                int count = hits.getLast().snd;
                hits.removeLast();
                hits.add(new Pair(timestamp, count + 1));
            }
            // Increment total
            total++;
        }

        // the number of hits in the past 300 seconds
        public int getHits(int timestamp) {
            while (!hits.isEmpty()) {
                int diff = timestamp - hits.getFirst().fst;
                if (diff >= 300) {
                    total -= hits.getFirst().snd;
                    hits.removeFirst();
                } else {
                    break;
                }
            }
            return total;
        }
    }
}
