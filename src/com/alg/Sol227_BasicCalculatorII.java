package com.alg;

import java.util.Stack;

/*
Given a string s which represents an expression, evaluate this expression and return its value.

The integer division should truncate toward zero.

You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].

Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().



Example 1:

Input: s = "3+2*2"
Output: 7
Example 2:

Input: s = " 3/2 "
Output: 1
Example 3:

Input: s = " 3+5 / 2 "
Output: 5


Constraints:

1 <= s.length <= 3 * 105
s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
s represents a valid expression.
All the integers in the expression are non-negative integers in the range [0, 231 - 1].
The answer is guaranteed to fit in a 32-bit integer.
 */
public class Sol227_BasicCalculatorII {
    public int calculate(String s) {
        // only integer and +-*/
        // 3 + 5 /2;
        // 3 * 5 + 2
        // 3 + 4 + 2
        if (s == null) return 0;
        int n = s.length();
        if (n == 0) return 0;
        Stack<Integer> stack = new Stack<>();
//        s.replaceAll(" ", "");
        int num = 0;
        char opr = '+';
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0'); // add () to avoid overflow for case like  Integer.MAX - 10
            }
//            else if( !Character.isWhitespace(c) || i == n - 1) {
            if (!Character.isDigit(c) && !Character.isWhitespace(c) || i == n - 1) {
                // at i == n - 1, get number in first if and calculate in this if
                if (opr == '+') {
                    stack.push(num);
                } else if (opr == '-') {
                    stack.push(-num);
                } else if (opr == '*') {
                    stack.push(stack.pop() * num);
                } else if (opr == '/') {
                    stack.push(stack.pop() / num);
                }
                opr = c;
                num = 0;
            }
        }
        int res = 0;
        //
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
//        return stack.stream().mapToInt(Integer::intValue).sum();
        return res;

    }

    public static void main(String[] args) {

        String s = "3*4";
        Sol227_BasicCalculatorII ss = new Sol227_BasicCalculatorII();
        System.out.println(ss.calculate(s));
        String t = " 3+3/2 ";
        System.out.println(ss.calculate0(t));
        // stack 3
    }

    public int calculate0(String s) {

        if (s == null || s.isEmpty()) return 0;
        int len = s.length();
        Stack<Integer> stack = new Stack<Integer>();
        int currentNumber = 0;
        char operation = '+';
        for (int i = 0; i < len; i++) {
            char currentChar = s.charAt(i);
            if (Character.isDigit(currentChar)) {
                currentNumber = (currentNumber * 10) + (currentChar - '0');
            }
            if (!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar) || i == len - 1) {
                if (operation == '-') { // read the operation stored in previous step
                    stack.push(-currentNumber);
                }
                else if (operation == '+') {
                    stack.push(currentNumber);
                }
                else if (operation == '*') {
                    stack.push(stack.pop() * currentNumber);
                }
                else if (operation == '/') {
                    stack.push(stack.pop() / currentNumber);
                }
                operation = currentChar;  // save the current char as the new op
                currentNumber = 0;
            }
        }
        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }
}
