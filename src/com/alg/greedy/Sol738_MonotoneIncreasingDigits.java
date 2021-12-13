package com.alg.greedy;

import java.util.concurrent.TimeUnit;

/*
An integer has monotone increasing digits if and only if each pair of adjacent digits x and y satisfy x <= y.

Given an integer n, return the largest number that is less than or equal to n with monotone increasing digits.



Example 1:

Input: n = 10
Output: 9
Example 2:

Input: n = 1234
Output: 1234
Example 3:

Input: n = 332
Output: 299
 */
public class Sol738_MonotoneIncreasingDigits {
    public int monotoneIncreasingDigits(int n) {
        char[] digits = Integer.toString(n).toCharArray();
        int size = digits.length;
        int start = size;
        for (int i = size - 1; i > 0; i--) {
            if (digits[i-1] > digits[i]) {
                digits[i-1]--;
                start = i;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (i < start) {
                sb.append(digits[i]);
            } else {
                sb.append("9");
            }
        }
        return Integer.parseInt(sb.toString());  // can take care of the 0 at first of "01234"
    }

    public int monotoneIncreasingDigits2(int n) {
        if (n < 10) return n;

        int scale = 1, v = n;
        while (v >= 10) {
            int current = v % 10;
            v /= 10;
            scale *= 10;
            int prev = v % 10;
            if (prev > current) {
                return monotoneIncreasingDigits2(v - 1) * scale + scale - 1;
            }
        }

        return n;
    }

    public static void main(String[] args) {
        int n = 214;

        Sol738_MonotoneIncreasingDigits ss = new Sol738_MonotoneIncreasingDigits();
        long start = System.nanoTime();
        System.out.println(ss.monotoneIncreasingDigits2(n));
        long end = System.nanoTime();
        long durationinNs = TimeUnit.NANOSECONDS.toNanos(end - start);
        long durationinMicros = TimeUnit.NANOSECONDS.toMicros(end - start);
        System.out.println(durationinNs + " nano seconds");
        System.out.println(durationinMicros + " micro seconds");

    }

    /*
    python
    class Solution:
    def monotoneIncreasingDigits(self, n: int) -> int:
        a = list(str(n))
        for i in range(len(a)-1,0,-1):
            if int(a[i]) < int(a[i-1]):
                a[i-1] = str(int(a[i-1]) - 1)
                a[i:] = '9' * (len(a) - i)  #python不需要设置flag值，直接按长度给9就好了
        return int("".join(a))
     */
}
