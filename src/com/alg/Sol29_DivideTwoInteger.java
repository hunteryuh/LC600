package com.alg;

/*
Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.

Return the quotient after dividing dividend by divisor.

The integer division should truncate toward zero, which means losing its fractional part. For example, truncate(8.345) = 8 and truncate(-2.7335) = -2.

Note: Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range: [−231, 231 − 1]. For this problem, assume that your function returns 231 − 1 when the division result overflows.
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
        // 1. 最外层 while 表示 只要 被除数 大于等于 除数 这个就还得 继续运算 因为 至少 结果会 + 1
        // 2. 每次 初始化 一个 shift 然后 再用个while 移动shift 直到 被除数 < 除数
        // 3. 被除数 减去 不大于被除数的 移位 shift - 1位的 除数
        // 4. 结果 加上 1 << (shift - 1) 再次进入第一步
        while(pDividend>=pDivisor){
            //calculate number of left shifts
            int numShift = 0;
            while(pDividend >= (pDivisor<<numShift)){
                numShift++;
            }

            //dividend minus the largest shifted divisor
            pDividend -= (pDivisor<<(numShift-1));
            result += 1 << (numShift-1);
        }

        if((dividend>0 && divisor>0) || (dividend<0 && divisor<0)){
            return result;
        } else {
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
