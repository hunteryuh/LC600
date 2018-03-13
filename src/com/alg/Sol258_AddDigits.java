package com.alg;
/*Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

For example:

Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.

Follow up:
Could you do it without any loop/recursion in O(1) runtime?*/
public class Sol258_AddDigits {
    public static int addDigits(int num) {
        //recursion method
        while(num > 10){
            num = sumDigits(num);
        }
        return num;
    }
    private static int sumDigits(int num){
        if (num ==0) return 0;
        return num%10 + sumDigits(num/10);
    }

    public static void main(String[] args) {
        System.out.println(addDigits(83));
        System.out.println(addDig2(83));
    }

    //In mathematics we have learnt that any number that is divisible by 9, the sum of the digits in the number is also divisible by 9. Also,
    // here we know that the result of the problem is an integer lying in the range [0,9] .
    public static int addDig2(int num){
        if (num < 10) return num;
        else if(num % 9 == 0) return 9;
        else return  num % 9;
    }
}

