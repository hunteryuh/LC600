package com.alg.dp;
/*
You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

Return the number of combinations that make up that amount. If that amount of money cannot be made up by any combination of the coins, return 0.

You may assume that you have an infinite number of each kind of coin.

The answer is guaranteed to fit into a signed 32-bit integer.



Example 1:

Input: amount = 5, coins = [1,2,5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
Example 2:

Input: amount = 3, coins = [2]
Output: 0
Explanation: the amount of 3 cannot be made up just with coins of 2.
Example 3:

Input: amount = 10, coins = [10]
Output: 1

 */
public class Sol518_CoinChange2 {
    // 完全背包， 物品是可以添加多次的，所以遍历背包容量要从小到大去遍历，即：
    // 完全背包中，两个for循环的先后循序，都不影响计算dp[j]所需要的值（这个值就是下标j之前所对应的dp[j]）
    // https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0518.%E9%9B%B6%E9%92%B1%E5%85%91%E6%8D%A2II.md
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        // first coins, then bag: 这种遍历顺序中dp[j]里计算的是组合数！
        // 如果把两个for交换顺序 时dp[j]里算出来的就是排列数！
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                // 求装满背包有几种方法，一般公式都是：dp[j] += dp[j - nums[i]];
                dp[j] += dp[j - coins[i]];
            }
        }
        return dp[amount];
    }
    // Time complexity: O(N×amount)\mathcal{O}(N \times \textrm{amount})O(N×amount), where N is a length of coins array.

    // 如果求组合数就是外层for循环遍历物品，内层for遍历背包。
    // 如果求排列数就是外层for遍历背包，内层for循环遍历物品。

    // similar question
    // given sum and number k (0<k<=sum). YOu can use number 1 to k any number of times
    // return the number of unique combination to get sum
    // sum = 8, k= 2, ans = 5
    // sum = 8, k = 3, ans = 5  ？！
    // A[i][j] all combinations to get I using 1 to j
    // A[i][j] = A[i][j-1] + A[i-1][j]
    public int getways(int sum, int k) {
        // knapsack, unbounded, combination
        int[] dp = new int[sum + 1];
        dp[0] = 1;
        for (int i = 1; i <=k ; i++) {
            for (int j = i; j <= sum; j++) {
                dp[j] += dp[j-i];
            }
        }
        return dp[sum];
    }

    public static void main(String[] args) {
        Sol518_CoinChange2 ss = new Sol518_CoinChange2();
        System.out.println(ss.getways(8, 2));
        System.out.println(ss.getways(8, 3));
    }
}
