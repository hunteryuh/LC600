package com.alg;
/*
You are given k identical eggs and you have access to a building with n floors labeled from 1 to n.

You know that there exists a floor f where 0 <= f <= n such that any egg dropped at a floor higher than f will break, and any egg dropped at or below floor f will not break.

Each move, you may take an unbroken egg and drop it from any floor x (where 1 <= x <= n). If the egg breaks, you can no longer use it. However, if the egg does not break, you may reuse it in future moves.

Return the minimum number of moves that you need to determine with certainty what the value of f is.



Example 1:

Input: k = 1, n = 2
Output: 2
Explanation:
Drop the egg from floor 1. If it breaks, we know that f = 0.
Otherwise, drop the egg from floor 2. If it breaks, we know that f = 1.
If it does not break, then we know f = 2.
Hence, we need at minimum 2 moves to determine with certainty what the value of f is.
Example 2:

Input: k = 2, n = 6
Output: 3
 */
public class Sol887_SuperEggDrop {
    //https://leetcode.com/problems/super-egg-drop/discuss/159055/Java-DP-solution-from-O(KN2)-to-O(KNlogN)
    public int superEggDrop(int K, int N) {
        int[][] memo = new int[K+1][N+1];
        return helper(K, N, memo);
    }

    private int helper(int K, int N, int[][] memo) {
        if (N <= 1) {
            return N;
        }
        if (K == 1) {
            return N;
        }
        if (memo[K][N] > 0){
            return memo[K][N];
        }

        int low = 1;
        int high = N;
        int result = N;
        while (low < high) {
            int mid = low + (high - low)/2;
            int poss1 = helper(K-1, mid-1, memo); //breaks at mid
            int poss2 = helper(K, N-mid, memo); // did not break at mid
            result = Math.min(result, 1+ Math.max(poss1, poss2));

            if (poss1 == poss2) {
                break;
            } else if ( poss1 < poss2) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        memo[K][N] = result;
        return result;
    }


    // https://leetcode.com/problems/super-egg-drop/discuss/158974/C%2B%2BJavaPython-2D-and-1D-DP-O(KlogN)
    // dp[M][K]means that, given K eggs and M moves,
    //what is the maximum number of floor that we can check.

    public int superEggDrop2(int K, int N) {
        int[][] dp = new int[N + 1][K + 1];
        int m = 0;
        while (dp[m][K] < N) {
            ++m;
            for (int k = 1; k <= K; ++k)
                dp[m][k] = dp[m - 1][k - 1] + dp[m - 1][k] + 1;
            //Imagine you have budget of m moves and k eggs (drop eggs m times and break no more than k of them) and
            // we like to figure out maximum floor N, you like to pick a floor n to drop the first egg, the value of n can not be too big as otherwise if it broke,
            // you can not finish the n-1 floors below it with (m-1,k-1).
            // In fact, the largest n you can afford is dp[m-1][k-1]+1.
            // If the first egg does not break, you can check dp[m-1][k] floors above it.
            //So overall, the maximum floors is dp[m-1][k-1]+1+dp[m-1][k].
        }
        return m;
    }
}
