package com.alg;
/*
You are given an integer num. You can swap two digits at most once to get the maximum valued number.

Return the maximum valued number you can get.



Example 1:

Input: num = 2736
Output: 7236
Explanation: Swap the number 2 and the number 7.

Example 2:

Input: num = 9973
Output: 9973
Explanation: No swap.



Constraints:

    0 <= num <= 108


 */
public class Sol670_MaximumSwap {
    // https://leetcode.com/problems/maximum-swap/solutions/107068/java-simple-solution-o-n-time/
    /*
    Use buckets to record the last position of digit 0 ~ 9 in this num.

Loop through the num array from left to right. For each position,
we check whether there exists a larger digit in this num (start from 9 to current digit). We also need to make sure the position of this larger digit is behind the current one.
If we find it, simply swap these two digits and return the result.
     */
    // time : O(n)
    // space: O(n)
    public int maximumSwap(int num) {
        char[] digits = Integer.toString(num).toCharArray(); // integer to a string, then to a char array
        int[] buckets = new int[10];
        for (int i = 0; i < digits.length; i++) {
            buckets[digits[i] - '0'] = i; // last[digits[i] - '0']= i
        }
        for (int i = 0; i < digits.length; i++) {
            for (int k = 9; k > digits[i] - '0'; k--) {
                if (buckets[k] > i) {
                    char tmp = digits[i];
                    digits[i] = digits[buckets[k]];
                    digits[buckets[k]] = tmp;
                    return Integer.valueOf(new String(digits));
                }
            }
        }
        return num;
    }
    // 12 99 -> 9291, not 9219

}
