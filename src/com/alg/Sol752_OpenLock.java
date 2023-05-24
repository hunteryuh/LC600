package com.alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/*
You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.

The lock initially starts at '0000', a string representing the state of the 4 wheels.

You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning and you will be unable to open it.

Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to open the lock, or -1 if it is impossible.



Example 1:

Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
Output: 6
Explanation:
A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
because the wheels of the lock become stuck after the display becomes the dead end "0102".
Example 2:

Input: deadends = ["8888"], target = "0009"
Output: 1
Explanation: We can turn the last wheel in reverse to move from "0000" -> "0009".
Example 3:

Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
Output: -1
Explanation: We cannot reach the target without getting stuck.
 */
public class Sol752_OpenLock {
    public int openLock(String[] deadends, String target) {
        Queue<String> q = new LinkedList<>();
        q.offer("0000");
        int res = 0;
        Set<String> blockers = new HashSet<>(Arrays.asList(deadends));
        if (blockers.contains(target) || blockers.contains("0000")) return -1;
        Set<String> visited = new HashSet<>();
        visited.add("0000");
//        int[][] dirs = {{1, -1}}
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String cur = q.poll();
                if (cur.equals(target)) {
                    return res;
                }
                for (String next : getNexts(cur)) {
                    if (blockers.contains(next)) continue;
                    if (visited.contains(next)) continue;
                    visited.add(next);
                    q.offer(next);
                }
            }
            res++;  // only increases after one loop of queue size!
        }
        return -1;
    }
    private List<String> getNexts(String s) {
        //"0000"
        // 2^ 4
        List<String> lists = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            // char to int, add 9 or 11 then mod 10, then + '0' to convert to char
            char next1 = (char)((cur - '0' + 1 + 10) % 10 + '0');
            char next2 = (char)((cur - '0' - 1 + 10) % 10 + '0');
            String pre = s.substring(0, i);
            String post = s.substring(i +1);
            lists.add(pre + next1 + post);
            lists.add(pre + next2 + post);
        }
//        System.out.println(lists);
        return lists;
    }

    public static void main(String[] args) {
        // test
        int x = '1' - '0';
        System.out.println(x);
        char a = '0' + ('1'- '0');
        System.out.println("a is " + a);
        String s = "abc";
        int n = s.length();
        System.out.println(s.substring(n));
        String[] deadends = {"0201","0101","0102","1212","2002"};
        String target = "0202";
        Sol752_OpenLock ss = new Sol752_OpenLock();
        ss.openLock(deadends, target);
    }
}
