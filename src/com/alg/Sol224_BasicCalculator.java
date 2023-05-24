package com.alg;

import java.util.Stack;

/*
Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation.

Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().



Example 1:

Input: s = "1 + 1"
Output: 2

Example 2:

Input: s = " 2-1 + 2 "
Output: 3

Example 3:

Input: s = "(1+(4+5+2)-3)+(6+8)"
Output: 23



Constraints:

    1 <= s.length <= 3 * 105
    s consists of digits, '+', '-', '(', ')', and ' '.
    s represents a valid expression.
    '+' is not used as a unary operation (i.e., "+1" and "+(2 + 3)" is invalid).
    '-' could be used as a unary operation (i.e., "-1" and "-(2 + 3)" is valid).
    There will be no two consecutive operators in the input.
    Every number and running calculation will fit in a signed 32-bit integer.


 */
public class Sol224_BasicCalculator {
    // https://leetcode.com/problems/basic-calculator/discuss/62362/JAVA-Easy-Version-To-Understand!!!!!
    public int calcuate(String s) {
        // 3 + ( 5  - 2)
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        int sign = 1;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int sum = c - '0';
                while (i + 1 < n && Character.isDigit(s.charAt(i + 1))) {
                    sum = sum * 10 + s.charAt(i+1) - '0';
                    i++;
                    System.out.println(sum);
                }
                result += sum * sign;
            } else if (c == '(') {
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            } else if (c == ')') {
                int op2 = result;
                int operator = stack.pop();
                int op1 = stack.pop();
                result = op2 * operator + op1;
            } else if (c == '+') {
                sign = 1;
            } else if ( c == '-') {
                sign = -1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "2147483647";
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        Sol224_BasicCalculator ss = new Sol224_BasicCalculator();
        System.out.println("answer is " + ss.calcuate(s));
    }
}
