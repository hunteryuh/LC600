package com.alg;

import java.util.*;

/*
You are given two jugs with capacities jug1Capacity and jug2Capacity liters. There is an infinite amount of water supply available. Determine whether it is possible to measure exactly targetCapacity liters using these two jugs.

If targetCapacity liters of water are measurable, you must have targetCapacity liters of water contained within one or both buckets by the end.

Operations allowed:

    Fill any of the jugs with water.
    Empty any of the jugs.
    Pour water from one jug into another till the other jug is completely full, or the first jug itself is empty.



Example 1:

Input: jug1Capacity = 3, jug2Capacity = 5, targetCapacity = 4
Output: true
Explanation: The famous Die Hard example

Example 2:

Input: jug1Capacity = 2, jug2Capacity = 6, targetCapacity = 5
Output: false

Example 3:

Input: jug1Capacity = 1, jug2Capacity = 2, targetCapacity = 3
Output: true



Constraints:

    1 <= jug1Capacity, jug2Capacity, targetCapacity <= 106


 */
public class Sol365_WaterAndJugProblem {
    // https://leetcode.com/problems/water-and-jug-problem/solutions/83709/breadth-first-search-with-explanation/
    // bfs to simulate all situations in the rules
    public boolean waterJugProblem(int x, int y, int z) {
        if (x + y < z) return false;
        if (z == x || x == y || x == x + y) return true;

        if (x > y) {
            int tmp = x;
            x = y;
            y = tmp;
        }

        // x < y

        Queue<State> states = new ArrayDeque<>();
        Set<State> visited = new HashSet<>();

        // initial state
        State init = new State(0, 0);
        states.offer(init);
        visited.add(init);

        while(!states.isEmpty()) {
            State curr = states.poll();
            if(curr.a + curr.b == z) return true;

            // fill jug1
            Queue<State> queue = new ArrayDeque<>();
            queue.offer(new State(x, curr.b));      // fill jug 1 , x is jug1 capacity
            queue.offer(new State(0, curr.b));      // empty jug1
            queue.offer(new State(curr.a, y));      // fill jug 2
            queue.offer(new State(curr.a, 0));      // empty jug2
            queue.offer(new State(Math.min(curr.a + curr.b, x),
                    curr.a + curr.b < x ? 0 : curr.b - (x - curr.a)));      // pour all water from jug2 to jug1
            queue.offer(new State(curr.a + curr.b < y ? 0 : curr.a - (y - curr.b),
                    Math.min(curr.a + curr.b, y)));                         // pour all water from jug1 to jug2

            for (State tmp: queue) {
                if (visited.contains(tmp)) continue;
                states.offer(tmp);
                visited.add(tmp);
            }
        }
        return false;
    }


    class State {
        public int a, b;

        public State(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public int hashCode() {
            return 31 * a + b;
        }

        public boolean equals(Object o) {
            State other = (State)o;
            return this.a == other.a && this.b == other.b;
        }
    }
    // bfs method 2 https://leetcode.com/problems/water-and-jug-problem/solutions/172968/java-bfs/
    public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        if (targetCapacity < 0 || targetCapacity > jug1Capacity + jug2Capacity) {
            return false;
        }

        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        int[] options = new int[] {jug1Capacity, jug2Capacity, -jug1Capacity, -jug2Capacity};
        while (!queue.isEmpty()) {
            int s = queue.size();
            for (int i = 0; i < s; i++) {
                int cur = queue.poll();
                if (cur == targetCapacity) {
                    return true;
                }
                for (int o : options) {
                    int next = cur + o;
                    if (next < 0 || next > jug1Capacity + jug2Capacity) {
                        continue;
                    }
                    if (!visited.contains(next)) {
                        queue.add(next);
                        visited.add(next);
                    }
                }
            }
        }
        return false;
    }

}
