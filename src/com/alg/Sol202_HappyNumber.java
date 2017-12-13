package com.alg;

/**
 * Created by HAU on 12/12/2017.
 */
/*A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

Example: 19 is a happy number

12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
Credits:*/
public class Sol202_HappyNumber {
    public static boolean isHappy(int n) {
        int slow = n, fast = n;
        while ( slow > 1){
            slow = sqsum(slow);
            if( slow == 1) return true;
            fast = sqsum(sqsum(fast));
            if ( fast == 1) return true;
            if( slow == fast) return false;

        }
        return true;
    }
    private static int sqsum(int n){
        int s = 0;
        while (n > 0) {
            s += (n % 10) * (n % 10);
            n /= 10;
        }
        return s;
    }

    public static void main(String[] args) {
        int n = 11;
        System.out.println(isHappy(n));
    }
}
