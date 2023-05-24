package com.alg;
/*
Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.



Example 1:

Input: n = 13
Output: 6
Example 2:

Input: n = 0
Output: 0


Constraints:

0 <= n <= 109
 */
public class Sol233_NumberOfDigitOne {
    //https://www.geeksforgeeks.org/number-of-occurrences-of-2-as-a-digit-in-numbers-from-0-to-n/
// Basic idea: count number of combination of 1 at each digit in two cases: prefix appears or not
// Eg.3101592:
// 1) one at hundreds:      1 (< 5): [1~3101]1[0~99]. So # = 3101 * 100 + 100 (Safe since 31011XX never be greater than 31015XX)
// 2) one at thousands:     1 (= 1): [1~310]1[0~592]. So # = 310 * 1000 + (592 + 1) (Unsafe if prefix is 0, it must be <= 1592)
// 3) one at ten thousands: 1 (> 0): [1~30]1[0~9999]. So # = 30 * 10000 (If prefix is 0, no 1 digit number exist)
    public int countDigitOne(int n) {
        if (n <= 0) return 0;
        long ones = 0;
        for (long i = 1, q = n; i <= n; i *= 10, q /= 10) {
            long prefix = n / (i * 10), cur = q % 10, suffix = n % i;
            ones += prefix * i;
            ones += (1 < cur ? i : (1 == cur ? suffix + 1: 0));
        }
        return (int) ones;
    }

    // https://leetcode.com/problems/number-of-digit-one/discuss/277541/1
    public int countDigitOne2(int n) {
        int count=0;
        int base=1;
        int numberLower=0;

        while (n>0){//从低位开始
            int curr=n % 10;//当前数字
            if(curr>1)
                count+=base;//如果当前数字大于1 ，加上一个基数即可
            else if(curr==1){//如果当前数字等于1 ，加上低位的数字+1
                count+=numberLower+1;
            }
            n/=10;
            count+=n*base;//左边的高位数字 * 基数
            numberLower+=curr*base;  //更新低边数字
            base*=10;//基数*10
        }
        return count;
    }

    public static void main(String[] args) {
    }
}


/*
Quesiton 1
You are given an integer n. Your task is to calculate how many times the digits 0, 2 and 4 appear in all the non-negative integers up to n (0, 1, ...., n).
Example
• For n = 10, the output should be countOccurrences(n) = 4.
  ○ The digit 0 appears in numbers 0 and 10 once, for a total of 2 occurrences,
  ○ The digit 2 appears in the number 2 once, for a total of 1 occurrence,
  ○ The digit 4 appears in the number 4 once, for a total of 1 occurrence.So the answer is 2 + 1 + 1 = 4.
 */
