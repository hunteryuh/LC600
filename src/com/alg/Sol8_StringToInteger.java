package com.alg;

/**
 * Created by HAU on 7/3/2017.
 */

/*Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below
        and ask yourself what are the possible input cases.

        Notes: It is intended for this problem to be
        specified vaguely (ie, no given input specs). You are responsible to
        gather all the input requirements up front.*/
public class Sol8_StringToInteger {
    public static int myAtoi(String str){
        int sign = 1;
        int i = 0;
        int n = str.length();
        if ( n == 0) return 0;
        while (str.charAt(i) == ' ') {
            i++;
            if ( i == n) return 0;
        }
        if ( str.charAt(i) == '-') {
            sign = -1;
            i++;
        } else if ( str.charAt(i) == '+'){
            sign = 1;
            i++;
        } else if ( str.charAt(i) < '0' || str.charAt(i) > '9') {
            return 0;
        }
        int res = 0;
        int num;

        for ( int k = i; k < n; k++){
            if ( str.charAt(k) < '0' || str.charAt(k) > '9') break;


            num = str.charAt(k) - '0';
            if (sign == 1 && res > Integer.MAX_VALUE / 10 || ( sign ==1 && res == Integer.MAX_VALUE /10 && num > Integer.MAX_VALUE % 10) )
                return Integer.MAX_VALUE;
            if ( sign == -1 && res > Integer.MAX_VALUE / 10 || ( sign == -1 && res == Integer.MAX_VALUE / 10 && num > Integer.MAX_VALUE % 10))
                return Integer.MIN_VALUE;
            res = res * 10 + num;
        }
        return res * sign;

    }

    public static void main(String[] args) {
//        System.out.println(myAtoi("-2090k"));
//        System.out.println(myAtoi("2147483647"));
//        System.out.println(myAtoi("2147483648"));
//        System.out.println(myAtoi("-2147483698"));
//        System.out.println(myAtoi("-2147483648"));
//        System.out.println(myAtoi("-2147483649o"));
//        System.out.println(myAtoi("+1"));
        System.out.println(myAtoi("-2147483647"));
        System.out.println("---");
        System.out.println("Minint: " + Integer.MAX_VALUE);
        System.out.println("Maxint: " + Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE %10 );
        System.out.println(Integer.MAX_VALUE / 10 );


    }
}
