package com.alg;

import java.util.Arrays;

/*
You are given an array of n pairs pairs where pairs[i] = [lefti, righti] and lefti < righti.

A pair p2 = [c, d] follows a pair p1 = [a, b] if b < c. A chain of pairs can be formed in this fashion.

Return the length longest chain which can be formed.

You do not need to use up all the given intervals. You can select pairs in any order.



Example 1:

Input: pairs = [[1,2],[2,3],[3,4]]
Output: 2
Explanation: The longest chain is [1,2] -> [3,4].

Example 2:

Input: pairs = [[1,2],[7,8],[4,5]]
Output: 3
Explanation: The longest chain is [1,2] -> [4,5] -> [7,8].



Constraints:

    n == pairs.length
    1 <= n <= 1000
    -1000 <= lefti < righti <= 1000


 */
public class Sol646_MaximumLengofPairChain {
    // greedy

    /*
    The tail of the current chain would be pairA[1] if we choose pairA and would be pairB[1]
    if choose pairB. Since pairA[1] < pairB[1] (due to sorting), it is better to choose pairA first
    because that way we expose a smaller tail which has a better opportunity to append more future pairs.
     */
    public int findLongestChain(int[][] pairs) {
        // time: O(Nlogn)
        Arrays.sort(pairs, (a, b) -> a[1] - b[1]); // sort based on the second element
        int cur = Integer.MIN_VALUE;
        int ans = 0;
        for (int[] pair: pairs) {
            if (pair[0] > cur) {
                ans++;
                cur = pair[1];
            }
        }
        return ans;
    }

    // dp from bottom up https://leetcode.com/problems/maximum-length-of-pair-chain/editorial/
    //dp[i] will store the longest length of the chain starting from the ith
    // pair and including it. We initialize all the elements in dp to 1.
    // time: O(n^2), space: O(n)
    public int findLongestChain2(int[][] pairs) {
        int n = pairs.length;
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int ans = 1;

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (pairs[i][1] < pairs[j][0]) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

}
