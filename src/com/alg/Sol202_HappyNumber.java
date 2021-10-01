package com.alg;

import java.util.HashSet;
import java.util.Set;

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

    public static boolean isHappyNumber(int n) {
        for (int i = 0 ; i < 1e5; i++) {  //arbitrary large number to cover most cases
            if (n == 1) {
                return true;
            };
            int sum = 0;
            int m = n;
            while (m > 0) {
                sum += (m % 10) * (m % 10);
                m /= 10;
            }
            n = sum;
        }
        return false;
    }

    public static boolean isHappyNumber2(int n) {
        Set<Integer> set = new HashSet<>();

        while (set.add(n)) {
//            set.add(n);
            int sum = 0;
            while (n > 0) {
                sum += (n%10) * (n%10);
                n /= 10;
            }
            if (sum == 1) {
                return true;
            } else {
                n = sum;
//                if (set.contains(n)) {
//                    return false;
//                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int n = 11;
        Sol202_HappyNumber s = new Sol202_HappyNumber();
        s.isHappyNumber3(n);
        System.out.println(isHappy(n));
    }

    // check 1
    public boolean isHappyNumber3(int n) {
        int sum = n;
        Set<Integer> set = new HashSet<>();
        set.add(n);
        while (sum > 0) {
            if (sum == 1) {
                return true;
            }
            sum = 0;
            while (n > 0) {
                int digit = n % 10;
                sum += digit * digit;
                n = n / 10;
            }
            if (set.contains(sum)) {
                return false;
            } else {
                set.add(sum);
            }
            n = sum;
        }
        return false;
    }

    public boolean isHappy2(int n) {
        Set<Integer> set = new HashSet<>();
        while (n != 1) {
            if (!set.add(n)) {
                return false;
            }
            n = getNext(n);
        }
        return true;

    }

    private int getNext(int n) {
        int sum = 0;
        while (n > 0) {
            sum += (n %10) * (n %10);
            n /= 10;
        }
        return sum;
    }

}
