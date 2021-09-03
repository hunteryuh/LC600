package com.alg;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by HAU on 11/2/2017.
 */
/*You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up
by any combination of the coins, return -1.*/
public class Sol322_CoinChange {
    public static int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount < 0) {
            return -1;
        }
        int[] counts = new int[amount+1];
        counts[0] = 0;
        for (int i = 1; i <= amount; i++){
            counts[i] = -1;
            for (int j = 0; j < coins.length; j++) {
                if (i == coins[j]) {
                    counts[i] = 1;
                } else if (i > coins[j] && counts[i - coins[j]] >= 0) {
                    if (counts[i] == -1 || counts[i] > counts[i-coins[j]] + 1) {
                        counts[i] = counts[i - coins[j]] + 1;
                    }
                }
            }
        }
        return counts[amount];
    }

    public static int coinChange2(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount < 0) {
            return -1;
        }
        // f[x]: the fewest number of coins to make up to amount x
        int[] f = new int[amount+1];  // 0 .. M
        f[0] = 0;  // init
        for (int i = 1; i <= amount; i++) {
            f[i] = Integer.MAX_VALUE;
            // select last coin
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j] && f[i - coins[j]] != Integer.MAX_VALUE && f[i - coins[j]] + 1 < f[i]) {
                    f[i] = f[i - coins[j]] + 1;
                }
            }
        }
        if (f[amount] == Integer.MAX_VALUE) {
            return -1;
        }
        return f[amount];
    }

    // bfs solution
    public int coinChange3(int[] coins, int amount) {
        if (amount == 0) return 0;
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[amount + 1];
        q.offer(amount);
        visited[amount] = true;
        int curLevel = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i< size; i++) {
                int target = q.poll();
                for (int coin: coins) {
                    int rem = target - coin;
                    if (rem == 0) {
                        return curLevel;
                    } else if (rem > 0) {
                        if (!visited[rem]) {
                            q.offer(rem);
                            visited[rem] = true;
                        }
                    }
                }
            }
            curLevel++;
        }
        return -1;
    }


    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.println(coinChange(coins,amount));
        System.out.println(coinChange2(coins,amount));
        int[] c2 = {2};
        System.out.println(coinChange(c2,amount));
        System.out.println(coinChange2(c2,amount));
        int[] c3 = {2, 5, 7};
        System.out.println(coinChange(c3, 27));
        System.out.println(coinChange2(c3, 27));  // 5,5,5,5,7  NOT 7,7,7,2,2,2
    }
}
