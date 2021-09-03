package com.alg;

import java.util.Arrays;

/*
Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n), ans[i] is the number of 1's in the binary representation of i.



Example 1:

Input: n = 2
Output: [0,1,1]
Explanation:
0 --> 0
1 --> 1
2 --> 10
Example 2:

Input: n = 5
Output: [0,1,1,2,1,2]
Explanation:
0 --> 0
1 --> 1
2 --> 10
3 --> 11
4 --> 100
5 --> 101

https://leetcode.com/problems/counting-bits/
 */
public class Sol338_CountingBits {

    public int[] countBits(int n) {
        int[] res = new int[n+1];
        res[0] = 0;
        for (int i = 0 ; i <= n; i++) {
            int mod = i % 2;
            if (mod == 0) {
                res[i] = res[ i / 2];
            } else {
                res[i] = res[i/2 ] + 1;
            }
        }
        return res;
    }


    // An easy recurrence for this problem is f[i] = f[i / 2] + i % 2.  f[i] = f[i >> 1] + i % 2 ;
    public static void main(String[] args) {
        Sol338_CountingBits s = new Sol338_CountingBits();
        int[] res = s.countBits(16);
        System.out.println(Arrays.toString(res));
    }
}
