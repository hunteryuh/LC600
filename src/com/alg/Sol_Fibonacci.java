package com.alg;

/**
 * Created by HAU on 6/11/2017.
 *
 * O(1) space
 */
public class Sol_Fibonacci {
    public static int fib(int n){
        if (n <= 1) return n;
        return fib(n-2) + fib(n-1);
    }


    public static int fib0(int n) {
        int a = 0;
        int b = 1;
        if (n == 0) {
            return 0;
        }
        int c;
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return b;  //c
    }

    public static int fib2(int n){
        int[] f = new int[n+1];
        int i;
        f[0] = 0;
        f[1] = 1;
        for (i = 2; i < n + 1; i++){
            f[i] = f[i-1] + f[i-2];
        }
        return f[n];
    }

    public static void main(String[] args) {
        System.out.println(fib(14));
        System.out.println(fib2(14));
    }
}
