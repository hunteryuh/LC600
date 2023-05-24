package com.alg;

import java.util.LinkedList;
import java.util.Queue;

/*
Given an array of non-negative integers arr, you are initially positioned at start index of the array. When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach to any index with value 0.

Notice that you can not jump outside of the array at any time.



Example 1:

Input: arr = [4,2,3,0,3,1,2], start = 5
Output: true
Explanation:
All possible ways to reach at index 3 with value 0 are:
index 5 -> index 4 -> index 1 -> index 3
index 5 -> index 6 -> index 4 -> index 1 -> index 3

Example 2:

Input: arr = [4,2,3,0,3,1,2], start = 0
Output: true
Explanation:
One possible way to reach at index 3 with value 0 is:
index 0 -> index 4 -> index 1 -> index 3

Example 3:

Input: arr = [3,0,2,1,2], start = 2
Output: false
Explanation: There is no way to reach at index 1 with value 0.



Constraints:

    1 <= arr.length <= 5 * 104
    0 <= arr[i] < arr.length
    0 <= start < arr.length


 */
public class Sol1306_JumpGameIII {
    public boolean canReach(int[] arr, int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        int n = arr.length;
        boolean[] visited = new boolean[n];
        visited[start] = true;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (arr[cur] == 0) {
                return true;
            }
//            visited[cur] = true;
            if (cur + arr[cur] < n && !visited[cur+arr[cur]]) {
                queue.offer(cur + arr[cur]);
                visited[cur + arr[cur]] = true;
            }
            if (cur - arr[cur] >= 0 && !visited[cur - arr[cur]]) {
                queue.offer(cur - arr[cur]);
                visited[cur - arr[cur]] = true;
            }

        }
        return false;
    }

    //dfs
    public boolean canReach2(int[] arr, int start) {
        if (start >= 0 && start < arr.length && arr[start] >= 0) {
            if (arr[start] == 0) {
                return true;
            }
            arr[start] = -arr[start];
            return canReach2(arr, start + arr[start]) || canReach2(arr, start - arr[start]);
        }
        return false;
    }
}
