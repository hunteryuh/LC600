package com.alg.dp;

import sun.awt.image.ImageWatched;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
Given an array of integers arr, you are initially positioned at the first index of the array.

In one step you can jump from index i to index:

i + 1 where: i + 1 < arr.length.
i - 1 where: i - 1 >= 0.
j where: arr[i] == arr[j] and i != j.
Return the minimum number of steps to reach the last index of the array.

Notice that you can not jump outside of the array at any time.



Example 1:

Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
Output: 3
Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9. Note that index 9 is the last index of the array.
Example 2:

Input: arr = [7]
Output: 0
Explanation: Start index is the last index. You do not need to jump.
Example 3:

Input: arr = [7,6,9,6,9,6,9,7]
Output: 1
Explanation: You can jump directly from index 0 to index 7 which is last index of the array.
 */
public class Sol1345_JumpGame4 {
    // https://leetcode.com/problems/jump-game-iv/discuss/502699/JavaC%2B%2B-BFS-Solution-Clean-code-O(N)
    public int minJumps(int[] arr) {
        int len = arr.length;
        Map<Integer, List<Integer>> valueToIndexMap = new HashMap<>();
        for (int i = 0; i < len; i++) { // store index
            valueToIndexMap.computeIfAbsent(arr[i], x -> new LinkedList<>()).add(i);
        }
        boolean[] visited = new boolean[len];
        visited[0] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int value = queue.poll();
                if (value == len - 1) return step; // reached to last index
                List<Integer> neighbor = valueToIndexMap.get(arr[value]);
                neighbor.add(value + 1);
                neighbor.add(value - 1);
                for (int nei: neighbor) {
                    if (nei >= 0 && nei < len && !visited[nei]) {
                        queue.offer(nei);
                        visited[nei] = true;
                    }
                }
                neighbor.clear(); // avoid look up neighbors arr[i]
            }
            step++;
        }
        return step;
    }

    // 双向Bfs  https://leetcode.com/problems/jump-game-iv/discuss/509868/JAVA-BFS
    public int jump4(int[] nums) {
        int n = nums.length;
        Map<Integer, List<Integer>> valueToIndexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            valueToIndexMap.computeIfAbsent(nums[i], x -> new LinkedList<>()).add(i);
        }
        Set<Integer> visited = new HashSet<>();
        Set<Integer> head = new HashSet<>();
        Set<Integer> tail = new HashSet<>();
        head.add(0);
        tail.add(n - 1);
        visited.add(0);
        visited.add(n-1);
        int step = 0;
        while (!head.isEmpty()) {
            // get the smaller set
            if (head.size() > tail.size()) {
                Set<Integer> temp = head;
                head = tail;
                tail = temp;
            }
            Set<Integer> next = new HashSet<>();
            for (int cur: head) {
                // jump to same value
                for (int z: valueToIndexMap.get(nums[cur])) {
                    if (tail.contains(z)) return step + 1;
                    if (!visited.contains(z)) {
                        next.add(z);
                        visited.add(z);
                    }
                }
                // remove the index that has the same value in the list
                // // We remove edges that we go over. We already added all these indices to the queue, there is no need to ever go over them again.
                valueToIndexMap.get(nums[cur]).clear();
                // jump to neighbor
                if (tail.contains(cur + 1) || tail.contains(cur - 1)) return step + 1;
                int x = cur + 1; int y = cur -1 ;
                if (x < n && visited.add(x)) next.add(x); // why not clear?
                if (y >= 0 && visited.add(y)) next.add(y);
            }
            step++;
            head = next;

        }
        return step;
    }
}
