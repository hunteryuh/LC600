package com.alg;

import sun.awt.image.ImageWatched;

import java.util.LinkedList;
import java.util.Queue;

/*
You are given a 0-indexed binary string s and two integers minJump and maxJump. In the beginning, you are standing at index 0, which is equal to '0'. You can move from index i to index j if the following conditions are fulfilled:

i + minJump <= j <= min(i + maxJump, s.length - 1), and
s[j] == '0'.
Return true if you can reach index s.length - 1 in s, or false otherwise.



Example 1:

Input: s = "011010", minJump = 2, maxJump = 3
Output: true
Explanation:
In the first step, move from index 0 to index 3.
In the second step, move from index 3 to index 5.
Example 2:

Input: s = "01101110", minJump = 2, maxJump = 3
Output: false
 */
public class Sol1871_JumpGameVII {
    public boolean conReach(String s, int minJump, int maxJump) {
        int n = s.length();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        for (int i = 0; i < n && i != -1 ; i= s.indexOf("0", i + 1)) {
            while (!queue.isEmpty() && queue.peek() < i - maxJump) queue.poll();
            if (!queue.isEmpty() && i - queue.peek() >= minJump) {
                queue.offer(i);
                if (i == n - 1 ) return true;
            }
            if (queue.isEmpty()) return false;
        }
        return false;
    }
}
