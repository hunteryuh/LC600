package com.alg;

/**
 * Created by HAU on 6/29/2017.
 */

/*Reverse digits of an integer.

        Example1: x = 123, return 321
        Example2: x = -123, return -321

        click to show spoilers.

        Note:
        The input is assumed to be a 32-bit signed integer.
       The 32-bit int data type can hold integer values in the range of −2,147,483,648 to 2,147,483,647.
        Your function should return 0 when the reversed integer overflows.*/
public class Sol7_ReverseInteger {
    public static int reverse(int x) {
        int sign = 1;
        if (x < 0){
            if ( x < - 2147483647) return 0;
            //Integer.MAX_VALUE
            x = - x;
            sign = -1;
        }
        int res = 0;
        int count = 0;
        int digit = x % 10 ;
        while ( x != 0){


            if ( ++count == 10 && digit > 2){
                return 0;
            }
            if ( res > 214748364) return 0;
            res = res * 10 + x % 10;
            x /= 10;    // works for integer ending in 0:  5190-->915

        }
        return res*sign;

    }

    public static void main(String[] args) {
        int res = reverse(254);
        //System.out.println(res);

        assert res == 4452;
        //System.out.println("done");
/*        int r2 = reverse(5190);
        System.out.println(r2);
        System.out.println(reverse(100));
        System.out.println(reverse(2147447413));
        System.out.println(reverse(2147447412));
        System.out.println(reverse(-2147483648));*/
    }
}
