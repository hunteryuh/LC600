package com.alg.other;

import java.util.Arrays;

/*
https://github.com/youngyangyang04/leetcode-master/blob/master/problems/%E8%83%8C%E5%8C%85%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%8001%E8%83%8C%E5%8C%85-1.md
 */
public class Backpack {
    public void testWeightBagProblem(int[] weight, int[] value, int bagSize) {
        int numbers = weight.length; // sorted
        int[][] dp = new int[numbers][bagSize + 1];
        // dp[i][j] 表示从下标为[0-i]的物品里任意取，放进容量为j的背包，价值总和最大是多少。
        for (int i = 0 ; i < numbers; i++) {
            dp[i][0] = 0;
        }
        for (int j = weight[0]; j <= bagSize; j++) {
            dp[0][j] = value[0];
        }
        for (int i = 1; i < numbers; i++) {
            for (int j = 1; j <= bagSize; j++) {
                if (j < weight[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
                }
            }
        }
        for (int i = 0; i < numbers; i++) {
            for (int j = 0; j <= bagSize; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public static void main(String[] args) {
        int[] weight = {1, 3, 4};
        int[] value = {15, 20 , 25};
        int bagsize = 4;
        Backpack bb = new Backpack();
        bb.testWeightBagProblem(weight, value, bagsize);
        bb.testWeightBagProblem2(weight, value, bagsize);
    }

    // https://github.com/youngyangyang04/leetcode-master/blob/master/problems/%E8%83%8C%E5%8C%85%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%8001%E8%83%8C%E5%8C%85-2.md
    public void testWeightBagProblem2(int[] weight, int[] value, int bagSize) {
        int count = weight.length;
        //定义dp数组：dp[j]表示背包容量为j时，能获得的最大价值
        int[] dp = new int[bagSize + 1];
        //遍历顺序：先遍历物品，再遍历背包容量
        for (int i = 0; i < count; i++) {
            for (int j = bagSize; j >=weight[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - weight[i]]+ value[i]);
            }
            System.out.print(Arrays.toString(dp));
        }
        for (int i = 0 ; i <= bagSize; i++) {
            System.out.print(dp[i] + " aa");
        }
    }
}
