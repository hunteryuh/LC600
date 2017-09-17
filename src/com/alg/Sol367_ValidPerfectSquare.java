package com.alg;

/**
 * Created by HAU on 9/17/2017.
 */

/*
* Given a positive integer num, write
* a function which returns True if num is a perfect square else False.*/
public class Sol367_ValidPerfectSquare {
    public static boolean isPerfectSquare(int n){
        int i = 1;
        while ( n > 0){
            n -= i;
            i += 2;
        }
        return n == 0;  // 1 + 3 + 5 + 7 +  ...2n -1 = n^2

    }

    public static boolean isSquareNumber(int t){
        if ( t == 1) return true;
        long start = 1;
        long end = t / 2;
        while ( start <= end){
            long mid = (start + end) >>>1 ; // unsigned shift , divided by 2
            if ( mid * mid == t){
                return true;
            }else if( mid * mid < t){
                start = mid + 1;
            }else{
                end = mid - 1;
            }

        }
        return false;
    }

    public static void main(String[] args) {
        int a = 256;
        //System.out.println(isPerfectSquare(a));
        //System.out.println(isSquareNumber(a));
        System.out.println(isSquareNumber(1));
        System.out.println(isSquareNumber(4));

    }

}
