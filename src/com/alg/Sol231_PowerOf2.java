package com.alg;

/**
 * Created by HAU on 2/5/2018.
 */
/*Given an integer, write a function to determine if it is a power of two.*/
public class Sol231_PowerOf2 {
    // bit , O(1)
    public static boolean isPowerOfTwo(int n) {
        if( n < 0) return false;
        return (n^(n-1))== 0;
    }
    // log(n)
    public static boolean isPowerof2(int n){
        if ( n <=0) return false;
        while( n%2 ==0) n /=2;
        return n == 1;
    }

    //One line java solution using bitCount
    public static boolean isP2(int n){
        return n>0 && Integer.bitCount(n) == 1;
    }
}
