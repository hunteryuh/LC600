package com.alg;

/**
 * Created by HAU on 7/14/2017.
 */
public class Sol29_DivideTwoInteger {
    public static int divide_slow(int dividend, int divisor) {
        if (divisor == 0) return Integer.MAX_VALUE;
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
        int count = 0;
        int sign = 1;
        if ((dividend < 0 && divisor > 0)) {
            sign = -sign;
            dividend = -dividend;
        }
        if (dividend > 0 && divisor < 0) {
            sign = -sign;
            divisor = -divisor;
        }
        if (dividend < 0 && divisor < 0) {
            dividend = -dividend;
            divisor = -divisor;
        }

        while (dividend >= divisor) {
            dividend -= divisor;
            count++;
        }
        return sign == 1 ? count : -count;
    }

    public static int divide(int dividend, int divisor){
        if(divisor==0) return Integer.MAX_VALUE;
        if(divisor==-1 && dividend == Integer.MIN_VALUE)
            return Integer.MAX_VALUE;

        //get positive values
/*
        int pDividend = Math.abs(dividend);  // time too long
        int pDivisor = Math.abs(divisor); // time too long
*/

        long pDividend = Math.abs((long)dividend);
        long pDivisor = Math.abs((long)divisor);
        int result = 0;
        while(pDividend>=pDivisor){
            //calculate number of left shifts
            int numShift = 0;
            while(pDividend>=(pDivisor<<numShift)){
                numShift++;
            }

            //dividend minus the largest shifted divisor
            result += 1<<(numShift-1);
            pDividend -= (pDivisor<<(numShift-1));
        }

        if((dividend>0 && divisor>0) || (dividend<0 && divisor<0)){
            return result;
        }else{
            return -result;
        }
    }

    public static void main(String[] args) {
        System.out.println(divide(-7,2));
        System.out.println(divide(2,2));
        System.out.println(divide(2,0));
        System.out.println(divide(-6,-12));
        System.out.println(divide(-12,-1));
        System.out.println(divide(2356841,2));
    }
}
