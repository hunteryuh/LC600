package com.alg;

/**
 * Created by HAU on 2/11/2018.
 */
/*Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.

Example:
Given a = 1 and b = 2, return 3.*/
public class Sol371_SumOfTwoIntegers {
    public static int getSum(int a, int b) {
        if ( b == 0) return a;
        while (b != 0){
            int carry = a & b; // get carry
            a = a ^ b; // get sum without carry
            b = carry << 1;
        }
        return a;
    }

    public static void main(String[] args) {
        int a = 4;
        int b = 6;
        System.out.println(getSum(a,b));
        System.out.println(getSum2(a,b));
    }
    // recursive
    public static int getSum2(int a, int b){
        if (b == 0) return a;
        int carry = (a & b) << 1;
        int sum = a ^ b;
        return getSum(sum,carry);
    }
    // one liner
    public static int getSum1(int a, int b){
        return b == 0? a : getSum(a^b, (a&b)<< 1);
    }

    public int getSum3(int a , int b) {
        while ( b!= 0) {
            int sumNoCarry = a ^ b;
            int carry = (a & b) << 1;
            a = sumNoCarry;
            b = carry;
        }
        return a;
    }
}
