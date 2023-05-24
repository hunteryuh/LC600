package com.alg;

/**
 * Created by HAU on 11/30/2017.
 */
/*Given an integer n, return the number of trailing(in the end) zeroes in n!.

Note: Your solution should be in logarithmic time complexity.*/
//10 is the product of 2 and 5. In n!, we need to know how many 2 and 5,
// and the number of zeros is the minimum of the number of 2 and the number of 5.
public class Sol172_FactorialTrailingZeroes {
    public static int trailingZeroes(int n) {
        //O(log5_N)
        return ( n == 0 )? 0: n/5 + trailingZeroes(n/5);
    }

    public static void main(String[] args) {
        System.out.println(trailingZeroes(2));
        System.out.println(trailingZeroes(20));
    }
    public static int trailing0(int n){
        int res = 0;
        while ( n > 0){
            n /=5;
            res += n;
        }
        return res;
    }
}
