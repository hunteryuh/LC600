package com.alg;

/**
 * Created by HAU on 11/2/2017.
 */
/*You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up
by any combination of the coins, return -1.*/
public class Sol322_CoinChange {
    public static int coinChange(int[] coins, int amount) {
        if ( coins == null || coins.length == 0 || amount < 0){
            return -1;
        }
        int[] counts = new int[amount+1];
        counts[0] = 0;
        for (int i = 1; i <= amount; i++){
            counts[i] = -1;
            for ( int j = 0; j< coins.length; j++){
                if ( i == coins[j]){
                    counts[i] = 1;
                }else if ( i > coins[j] && counts[i - coins[j]] >= 0){
                    if (counts[i] == -1 || counts[i] > counts[i-coins[j]] + 1){
                        counts[i] = counts[i - coins[j]] + 1;
                    }
                }
            }
        }
        return counts[amount];
    }

    public static void main(String[] args) {
        int[] coins = { 1,2,5};
        int amount = 11;
        System.out.println(coinChange(coins,amount));
        int[] c2 = {2};
        System.out.println(coinChange(c2,amount));
    }
}
