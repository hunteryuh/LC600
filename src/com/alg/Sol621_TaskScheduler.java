package com.alg;

import java.util.*;

/*
Given a characters array tasks, representing the tasks a CPU needs to do, where each letter represents a different task. Tasks could be done in any order. Each task is done in one unit of time. For each unit of time, the CPU could complete either one task or just be idle.

However, there is a non-negative integer n that represents the cooldown period between two same tasks (the same letter in the array), that is that there must be at least n units of time between any two same tasks.

Return the least number of units of times that the CPU will take to finish all the given tasks.



Example 1:

Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation:
A -> B -> idle -> A -> B -> idle -> A -> B
There is at least 2 units of time between any two same tasks.
Example 2:

Input: tasks = ["A","A","A","B","B","B"], n = 0
Output: 6
Explanation: On this case any permutation of size 6 would work since n = 0.
["A","A","A","B","B","B"]
["A","B","A","B","A","B"]
["B","B","B","A","A","A"]
...
And so on.
Example 3:

Input: tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
Output: 16
Explanation:
One possible solution is
A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> idle -> idle -> A -> idle -> idle -> A
 */
public class Sol621_TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for (char c: tasks) {
            freq[c- 'A']++;
        }
        int maxFreq = 1;
        for (int f: freq) {
            maxFreq = Math.max(f, maxFreq);
        }
        int countMaxFreq = 0;
        for (int f : freq) {
            if (f == maxFreq) {
                countMaxFreq++;  // how many tasks have the most frequency, might be more than 1
            }
        }
//        return (maxFreq - 1) * (n + 1) + countMaxFreq; // failed for n = 0 case
        return Math.max(tasks.length, (maxFreq - 1) * (n + 1) + countMaxFreq);
    }

    public static void main(String[] args) {
        char[] tasks = {'A','A','A','B','B'};
        int n = 1;
        Sol621_TaskScheduler ss = new Sol621_TaskScheduler();
        System.out.println(ss.leastInterval(tasks, n));
    }

    // https://leetcode.com/problems/task-scheduler/solution/ greedy
    public int leastInterval2(char[] tasks, int n) {
        // frequencies of the tasks
        int[] frequencies = new int[26];
        for (int t : tasks) {
            frequencies[t - 'A']++;
        }

        Arrays.sort(frequencies);

        // max frequency
        int f_max = frequencies[25];
        int idle_time = (f_max - 1) * n;

        for (int i = frequencies.length - 2; i >= 0 && idle_time > 0; --i) {
            idle_time -= Math.min(f_max - 1, frequencies[i]);
        }
        idle_time = Math.max(0, idle_time);

        return idle_time + tasks.length;
    }

    // https://leetcode.com/problems/task-scheduler/solutions/104493/c-java-clean-code-priority-queue/
    public int leastInterval3(char[] tasks, int n) {
        Map<Character, Integer> counts = new HashMap<Character, Integer>();
        for (char t : tasks) {
            counts.put(t, counts.getOrDefault(t, 0) + 1);
        }

        // build queue, sort from descending
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a, b) -> b - a);
        pq.addAll(counts.values());

        int alltime = 0;
        int cycle = n + 1;
        // when queue is not empty, there are remaining tasks
        while (!pq.isEmpty()) {
            int worktime = 0;
            List<Integer> tmp = new ArrayList<>();
            for (int i = 0; i < cycle; i++) {
                if (!pq.isEmpty()) {
                    tmp.add(pq.poll());  // most frequent task
                    worktime++;  // executed the task, one slot is taken
                }
            }
            for (int cnt : tmp) {
                if (--cnt > 0) {
                    pq.offer(cnt); // add valid tasks
                }
            }
            alltime += !pq.isEmpty() ? cycle : worktime; // worktime, same as tmp.size()
//            alltime += pq.isEmpty()? worktime : cycle;
        }

        return alltime;
    }

    // https://leetcode.com/problems/task-scheduler/solutions/104500/java-o-n-time-o-1-space-1-pass-no-sorting-solution-with-detailed-explanation/
    public int leastInterval4(char[] tasks, int n) {
        int[] counter = new int[26];
        int maxFreq = 0;
        int numberOfTasksWithMaxFreq = 0;
        for (char task: tasks) {
            counter[task - 'A']++;
            if (counter[task - 'A'] == maxFreq) {
                numberOfTasksWithMaxFreq++;
            } else if (counter[task - 'A'] > maxFreq) {
                maxFreq = counter[task - 'A'];
                numberOfTasksWithMaxFreq = 1;
            }
        }
        int partCountbyMostFreq = maxFreq - 1;
        int partLengthExcludingMostFreqTasks = n - (numberOfTasksWithMaxFreq - 1);
        int emptySlots = partLengthExcludingMostFreqTasks * partCountbyMostFreq;
        int availableTasks = tasks.length - maxFreq * numberOfTasksWithMaxFreq;
        int numberOfIdles = Math.max(0, emptySlots - availableTasks);
        return tasks.length + numberOfIdles;
    }
}
