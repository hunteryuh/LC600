package com.alg;

/**
 * Created by HAU on 8/21/2017.
 */
public class Sol50_Pow {
    public static double mypow(double x, int n){
        long N = (long) n;
        if ( N >= 0 ) return helper(x,N);
        else return 1/helper(x,-N);
    }

    private static double helper(double x,long i) {
        if ( i == 0) return 1;
        double res = helper(x, i/2);
        res *= res;
        if (i % 2 == 1) res *= x;
        return res;
    }

    public static void main(String[] args) {
        double x = 3;
        int n = 5;
        int i = Integer.MIN_VALUE;
        System.out.println(mypow(x,i));
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);
    }
}
