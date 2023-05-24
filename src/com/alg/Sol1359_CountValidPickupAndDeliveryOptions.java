package com.alg;
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

        return (int)ans;
    }

    // method 3 return factorial(2*n) // 2**n % 1_000_000_007
}
