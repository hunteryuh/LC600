package com.alg;

import java.util.ArrayList;
import java.util.List;

/*
Let's say a positive integer is a super-palindrome if it is a palindrome, and it is also the square of a palindrome.

Given two positive integers left and right represented as strings, return the number of super-palindromes integers in the inclusive range [left, right].



Example 1:

Input: left = "4", right = "1000"
Output: 4
Explanation: 4, 9, 121, and 484 are superpalindromes.
Note that 676 is not a superpalindrome: 26 * 26 = 676, but 26 is not a palindrome.
Example 2:

Input: left = "1", right = "2"
Output: 1


Constraints:

1 <= left.length, right.length <= 18
left and right consist of only digits.
left and right cannot have leading zeros.
left and right represent integers in the range [1, 1018 - 1].
left is less than or equal to right.
 */
public class Sol906_SuperPalindrome {
    public int superpalindromesInRange(String left, String right) {
        List<Long> palins = new ArrayList<>();
        long low = Long.parseLong(left);
        long high = Long.parseLong(right);
        int res = 0;
        for (long i = 1; i <=9; i++) {
            palins.add(i);
        }
        for (long i = 1; i < 10000; i++) {
            String ll = Long.toString(i);
            String rr = new StringBuilder(ll).reverse().toString();
            palins.add(Long.parseLong(ll + rr));
            for (long d = 0; d <= 9; d++) {
                palins.add(Long.parseLong(ll + d + rr));
            }

        }
        for (long palin: palins) {
            long sq2 = palin * palin;
            if (sq2 >= low && sq2 <= high && isPalin(sq2)) {
                res++;
            }
        }
        return res;
    }
    private boolean isPalin(Long s) {
        String ss = Long.toString(s);
        int i = 0;
        int j = ss.length() - 1;
        while (i < j) {
            if (ss.charAt(i) != ss.charAt(j)) {
                return false;
            }
            i++;j--;
        }
        return true;
    }

    // use long to check palin
    private boolean isPalindrome(long s) {
        return s == reverse(s);
    }
    private long reverse(long x) {
        long res = 0;
        while (x > 0) {
            res = res * 10 + x % 10;
            x = x / 10;
        }
        return res;
    }
}
