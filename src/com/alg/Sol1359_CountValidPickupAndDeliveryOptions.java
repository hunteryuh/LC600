package com.alg;

import java.util.ArrayList;
import java.util.List;

/*
Given n orders, each order consist in pickup and delivery services.

Count all valid pickup/delivery possible sequences such that delivery(i) is always after of pickup(i).

Since the answer may be too large, return it modulo 10^9 + 7.



Example 1:

Input: n = 1
Output: 1
Explanation: Unique order (P1, D1), Delivery 1 always is after of Pickup 1.
Example 2:

Input: n = 2
Output: 6
Explanation: All possible orders:
(P1,P2,D1,D2), (P1,P2,D2,D1), (P1,D1,P2,D2), (P2,P1,D1,D2), (P2,P1,D2,D1) and (P2,D2,P1,D1).
This is an invalid order (P1,D2,P2,D1) because Pickup 2 is after of Delivery 2.
Example 3:

Input: n = 3
Output: 90
 */
public class Sol1359_CountValidPickupAndDeliveryOptions {
    // https://leetcode.com/problems/count-all-valid-pickup-and-delivery-options/solution/
    public int countOrders(int n) {
        // bottom-up dp
        // define a state (unpicked, undelivered), where dp[unpicked][undelivered] denotes, the number of ways
        // to arrange the unpicked unpicked and undelivered undelivered orders.
        long[][] dp = new long[n+1][n+1];
        int MOD = 1_000_000_007;
        for (int unpicked = 0; unpicked <= n; unpicked++) {
            for (int undelivered = unpicked; undelivered <= n; undelivered++) {
                if (undelivered == 0 && unpicked == 0) {
                    dp[unpicked][undelivered] =1;
                    continue;
                }
                // There are some unpicked elements left.
                // We have choice to pick any one of those orders.
                if (unpicked > 0) {
                    dp[unpicked][undelivered] += unpicked * dp[unpicked-1][undelivered];
                    dp[unpicked][undelivered] %= MOD;
                }
                // Number of deliveries done is less than picked orders.
                // We have choice to deliver any one of (undelivered - unpicked) orders.
                if (undelivered > unpicked) {
                    dp[unpicked][undelivered] += (undelivered - unpicked) * dp[unpicked][undelivered - 1];
                    dp[unpicked][undelivered] %= MOD;
                }
            }
        }
        return (int)dp[n][n];
    }

    public int countOrders2(int n) {
        long ans = 1;
        int MOD = 1_000_000_007;

        for (int i = 1; i <= n; ++i) {
            // Ways to arrange all pickups, 1*2*3*4*5*...*n
            ans = ans * i;
            // Ways to arrange all deliveries, 1*3*5*...*(2n-1)
            ans = ans * (2 * i - 1);
            ans %= MOD;
        }

        return (int) ans;
    }

    // https://leetcode.com/problems/count-all-valid-pickup-and-delivery-options/solutions/516940/Simple-Java-DP/
    private int mod = (int) Math.pow(10,9) + 7;
    long[] dp = new long[501];
    public int countOrders3(int n) {
        dp[1]=1L;
        dp[2]=6L;
        for (int i=3;i<=n;i++) {
            int spaceCount = (i-1)*2 + 1;
            long val = (spaceCount)*(spaceCount+1)/2;
            dp[i] = (dp[i-1]*val)%mod;
        }
        return (int) dp[n];
    }

    // method 3 return factorial(2*n) // 2**n % 1_000_000_007

    public int countOrders4(int n) {
        List<List<String>> res = new ArrayList<>();
        int[] visited = new int[n + 1];
        findAllPossibleOrders(n, res, new ArrayList<String>(), visited);
        return res.size();
    }

    // if pick up order can be descending or ascending
    private void findAllPossibleOrders(int n, List<List<String>> res, ArrayList<String> path, int[] visited) {
        if (path.size() == n * 2) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (visited[i] == 0) {
                visited[i]++;
                path.add("P" + i);
                findAllPossibleOrders(n, res, path, visited);
                visited[i]--;
                path.remove(path.size() - 1);
            }
            if (visited[i] == 1) {
                visited[i]++;
                path.add("D" + i);
                findAllPossibleOrders(n, res, path, visited);
                visited[i]--;
                path.remove(path.size() - 1);
            }
        }
    }
}
