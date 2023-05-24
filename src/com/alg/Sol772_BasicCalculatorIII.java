package com.alg;

import java.util.LinkedList;
import java.util.Stack;

/*
Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, '+', '-', '*', '/' operators, and open '(' and closing parentheses ')'. The integer division should truncate toward zero.

You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].

Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().



Example 1:

Input: s = "1+1"
Output: 2

Example 2:

Input: s = "6-4/2"
Output: 4

Example 3:

Input: s = "2*(5+5*2)/3+(6/2+8)"
Output: 21



Constraints:

    1 <= s <= 104
    s consists of digits, '+', '-', '*', '/', '(', and ')'.
    s is a valid expression.


 */
public class Sol772_BasicCalculatorIII {
    // https://leetcode.com/problems/basic-calculator-iii/discuss/113600/Java-and-Python-O(n)-Solution-Using-Two-Stacks
    public int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        Stack<Integer> nums = new Stack<>();
        Stack<Character> ops = new Stack<>();
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                continue;
            }
            if (Character.isDigit(c)) {
                num = c - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                nums.push(num);
                // num = 0;
            } else if ( c == '(') {
                ops.push(c);
            } else if ( c == ')') {
                // do the math between ) and (
                while (ops.peek() != '(') {
                    nums.push(calc(ops.pop(), nums.pop(), nums.pop()));
                }
                ops.pop(); // remove the left ( in the ops stack
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!ops.isEmpty() && precedence(c, ops.peek())) {
                    nums.push(calc(ops.pop(), nums.pop(), nums.pop()));
                }
                ops.push(c);
            }
        }
        while (!ops.isEmpty()) {
            nums.push(calc(ops.pop(), nums.pop(), nums.pop()));
        }
        return nums.pop();
    }

    private int calc(char op, int b, int a) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b; // assume b is not 0;
        }
        return 0;
    }
    private boolean precedence(char op1, char op2) {
        // check if op2 has higher priority than op1
        if (op2 == '(' || op2 == ')') return false;
        if (( op1 == '*' || op1 == '/') && (op2 == '-' || op2 == '+')) {
            return false;
        }
        return true;
    }

    // recursive
    public int calculate2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        LinkedList<Integer> buff = new LinkedList<>();
        int num = 0;
        char sign = '+';

        int n = s.length();
        for (int i = 0; i < n; ++i) {
            char cur = s.charAt(i);

            if (cur >= '0' && cur <= '9') {
                num = 10 * num + (int)(cur - '0');
            } else if (cur == '(') {
                int j = i + 1; int cnt = 1;
                for (; j < n; ++j) {
                    if (s.charAt(j) == '(') ++cnt;
                    if (s.charAt(j) == ')') --cnt;
                    if (cnt == 0) break;
                }

                num = calculate2(s.substring(i + 1, j));
                i = j;
            }

            if (cur == '+' || cur == '-' || cur == '*' || cur == '/' || i == n - 1) {
                switch (sign) {
                    case '+':
                        buff.addFirst(num);
                        break;
                    case '-':
                        buff.addFirst(-num);
                        break;
                    case '*':
                        int tmp = buff.removeFirst() * num;
                        buff.addFirst(tmp);
                        break;
                    case '/':
                        int tmp2 = buff.removeFirst() / num;
                        buff.addFirst(tmp2);
                        break;
                }
                num = 0;
                sign = cur;
            }
        }

        int res = 0;
        for (int i : buff) {
            res += i;
        }

        return res;
    }

    // one stack with recursion
    int i = 0;
    public int calculateRed(String s) {
        Stack<Integer> stack = new Stack<>();
        char operator = '+';

        int num = 0;
        while (i < s.length()) {
            char c = s.charAt(i++);
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }
            if (c == '(') {
                num = calculateRed(s);
            }
            if (i >= s.length() || c == '+' || c == '-' || c == '*' || c == '/' || c == ')') { // include +-*/ and )
                if (operator == '+') stack.push(num);
                else if (operator == '-') stack.push(-num);
                else if (operator == '*') stack.push(stack.pop() * num);
                else if (operator == '/') stack.push(stack.pop() / num);
                operator = c;
                num = 0;

            }
            if (c == ')') break;
        }
        return stack.stream().mapToInt(x -> x).sum();
    }

    int index = 0;
    // no stack,  with recursion O(1) space
    public int calculateSpace1(String s) {
        char operator = '+';

        int res = 0;
        int previous = 0;
        int num = 0;
        while (index < s.length()) {
            char c = s.charAt(index++);
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }
            if (c == '(') {
                num = calculateSpace1(s);
            }
            if (index >= s.length() || c == '+' || c == '-' || c == '*' || c == '/' || c == ')') { // include +-*/ and )
                if (operator == '+') {
                    res = res + previous;
                    previous = num;
                }
                else if (operator == '-') {
                    res = res + previous;
                    previous = -num;
                }
                else if (operator == '*') {
                    previous = previous * num;
                }
                else if (operator == '/') {
                    previous = previous / num;
                }
                operator = c;
                num = 0;

            }
            if (c == ')') break;
        }
        return res + previous;
    }


    public static void main(String[] args) {
        Sol772_BasicCalculatorIII ss = new Sol772_BasicCalculatorIII();
        String s1 = "(6/2+8)";
        System.out.println(ss.calculateSpace1(s1));
    }

}
