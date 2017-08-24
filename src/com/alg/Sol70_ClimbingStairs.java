package com.alg;

/**
 * Created by HAU on 8/23/2017.
 */
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
}
