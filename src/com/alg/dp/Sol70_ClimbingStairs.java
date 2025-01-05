package com.alg.dp;

/*You are climbing a stair case. It takes n steps to reach to the top.

        Each time you can either climb 1 or 2 steps.
        In how many distinct ways can you climb to the top?

        Note: Given n will be a positive integer.*/
public class Sol70_ClimbingStairs {
    public static int climbStairs(int n){
        if ( n == 1 || n == 2 || n ==0) return n;
        int[] steps = new int[n];
        steps[0] = 1;
        steps[1] = 2;
        for (int i = 2 ; i < n; i++){
            steps[i] = steps[i-1] + steps[i-2];
        }
        return steps[n-1];
    }

    public static void main(String[] args) {
        System.out.println(climbStairs(4));
    }

    public int climbStairs2(int n) {
        if (n <= 2) return n;
        int[] dp = new int[n+1];
        dp[0] = 1;
        for (int i = 1; i <=n; i++) {
            for (int j = 1; j <= 2; j++) {
                if (i >=j ) {
                    dp[i] += dp[i - j];
                }
            }

        }
        return dp[n];
    }

    // 完全 背包
    // 这是背包里求排列问题，即：1、2 步 和 2、1 步都是上三个台阶，但是这两种方法不一样！
    //
    //所以需将target放在外循环，将nums放在内循环。
    //每一步可以走多次，这是完全背包，内循环需要从前向后遍历。
    public int climeStairs3(int n) {
        // dp[n] number of ways to get n
        int[] dp = new int[n+ 1];
        dp[0] = 1;
        for (int j= 0; j <= n; j++) {
            for (int i = 1; i <= 2; i++) {
                if (j >= i) {
                    dp[j] += dp[j - i];
                }
            }
        }
        return dp[n];
    }
}
