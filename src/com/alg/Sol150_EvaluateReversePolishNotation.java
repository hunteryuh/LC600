package com.alg;

import java.util.Stack;

/*
Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, and /. Each operand may be an integer or another expression.

Note that division between two integers should truncate toward zero.

It is guaranteed that the given RPN expression is always valid. That means the expression would always evaluate to a result, and there will not be any division by zero operation.



Example 1:

Input: tokens = ["2","1","+","3","*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9
Example 2:

Input: tokens = ["4","13","5","/","+"]
Output: 6
Explanation: (4 + (13 / 5)) = 6
Example 3:

Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
Output: 22
Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22
 */
public class Sol150_EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            String c = tokens[i];
            if (!"+-*/".contains(c)) {
                stack.push(Integer.parseInt(c));
            } else if (c.equals("+")) {
                int o1 = stack.pop();
                int o2 = stack.pop();
                stack.push(o1 + o2);
            } else if (c.equals("-")) {
                int o1 = stack.pop();
                int o2 = stack.pop();
                stack.push(o2 - o1);
            } else if (c.equals("*")) {
                int o1 = stack.pop();
                int o2 = stack.pop();
                stack.push(o2 * o1);
            } else {
                int o1 = stack.pop();
                int o2 = stack.pop();
                stack.push(o2/o1);
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        String[] tokens ={"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        Sol150_EvaluateReversePolishNotation pp = new Sol150_EvaluateReversePolishNotation();
        System.out.println(pp.evalRPN(tokens));
    }
}
