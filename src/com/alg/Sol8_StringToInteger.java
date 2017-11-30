package com.alg;

/**
 * Created by HAU on 7/3/2017.
 */

/*Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below
        and ask yourself what are the possible input cases.

        Notes: It is intended for this problem to be
        specified vaguely (ie, no given input specs). You are responsible to
        gather all the input requirements up front.*/
/*The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.

If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

If no valid conversion could be performed, a zero value is returned. If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.

*/
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
        System.out.println(myatoi2("-2147483647"));
        System.out.println("---");
        System.out.println("Minint: " + Integer.MAX_VALUE);
        System.out.println("Maxint: " + Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE %10 );
        System.out.println(Integer.MAX_VALUE / 10 );


    }
    public static int myatoi2(String str){
        if(str == null || str.length() == 0) return 0;
        str = str.trim();
        char first = str.charAt(0);
        int sign = 1;
        int start = 0;
        int len = str.length();
        long sum = 0;
        if (first == '+'){
            sign = 1;
            start++;
        }else if (first =='-'){
            sign = -1;
            start++;
        }
        for (int i = start; i < len ; i++){
            if(!Character.isDigit(str.charAt(i))){
                return sign * (int)sum;
            }
            sum = sum * 10 + str.charAt(i)-'0';
            if ( sign == 1 && sum > Integer.MAX_VALUE){
                return Integer.MAX_VALUE;
            }
            if (sign == -1 && -1 * sum < Integer.MIN_VALUE){
                return Integer.MIN_VALUE;
            }
        }
        return (int)sum * sign;
    }
}
