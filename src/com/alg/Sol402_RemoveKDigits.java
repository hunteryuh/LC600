package com.alg;

import java.util.Stack;

/*
Given string num representing a non-negative integer num, and an integer k, return the smallest possible integer after removing k digits from num.



Example 1:

Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
Example 2:

Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
Example 3:

Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 */
public class Sol402_RemoveKDigits {
    public static String removeKdigits(String num, int k) {
        int n = num.length();
        if (k == n) return "0";

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            char c = num.charAt(i);
            //whenever meet a digit which is less than the previous digit, discard the previous one
            while (k > 0 && !stack.isEmpty() && stack.peek() > c) {
                stack.pop();
                k--;
            }
            stack.push(c);
        }
        // if we haven't remove all "k" digits
        /*
        The function of this part of code is :
        1. in case of 11111 (duplicate)
        2. in case of 12345 (ascending order number)
         */
        while (k > 0) {
            stack.pop();
            k--;
        }
        //construct the number from the stack
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        System.out.println("before reverse: " + sb);
        sb.reverse();
        System.out.println("after");
        System.out.println(sb);
        while (sb.length() > 0 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }
        // in case all left digits are 0
        if (sb.length() == 0) return "0";
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(removeKdigits("100", 2));
        System.out.println(removeKdigits("10200", 1));
        System.out.println(removeKdigits("1432219", 3)); // 1219
        System.out.println(removeKdigits("1432219", 4));  // 119
    }
}
