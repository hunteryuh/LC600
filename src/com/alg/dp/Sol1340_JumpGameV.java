package com.alg.dp;
/*
Given an array of integers arr and an integer d. In one step you can jump from index i to index:

i + x where: i + x < arr.length and 0 < x <= d.
i - x where: i - x >= 0 and 0 < x <= d.
In addition, you can only jump from index i to index j if arr[i] > arr[j] and arr[i] > arr[k] for all indices k between i and j (More formally min(i, j) < k < max(i, j)).

You can choose any index of the array and start jumping. Return the maximum number of indices you can visit.

Notice that you can not jump outside of the array at any time.


 */
public class Sol1340_JumpGameV {
    // dfs + memo
    // time O(n^2)
    Integer[] memo;
    int[] arr;
    int n;
    int d;
    public int maxJumps(int[] arr, int d) {
        int res = 1;
        this.n = arr.length;
        this.memo = new Integer[n];
        this.arr = arr;

        this.d = d;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dfs(i));
        }
        return res;
    }

    private int dfs(int cur) {
        if (memo[cur] != null) return memo[cur];
        int res = 1;
        for (int i = cur + 1; i <= Math.min(cur + d, n -1) && arr[cur] > arr[i]; i++) {
            res = Math.max(res, 1  + dfs(i)); //  max jumps we can do start from cur
        }
        for (int i = cur -1; i >= Math.max(0, cur - d) && arr[cur] > arr[i]; i--) {
            res  = Math.max(res, 1 + dfs(i));
        }
        return memo[cur] = res;
    }

}
