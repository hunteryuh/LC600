package com.alg;

import java.util.Stack;

/*Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.

*/
public class Sol32_LongestValidParentheses {
    public static int longestValidParentheses(String s){
        // using stack, store the index of starting point of a valid string
        // 出现右括号的时候先pop出左括号，再计算长度
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // used to calculate the length
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                stack.push(i);
            }else{
                stack.pop();
                if(stack.isEmpty()){
                    stack.push(i);
                }else{
                    max = Math.max(max, i - stack.peek());
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        String ss = ")(()(())";
        System.out.println(longestValidParentheses(ss));
    }

    // 2 pass
    public int longestValidParen(String s) {
        int left = 0, right = 0;
        int res = 0;
        for (char c: s.toCharArray()) {
            if (c == '(') {
                left++;
            } else right++;
            if (left == right) {
                res = Math.max(res, left * 2);
            } else if (right > left) {
                left = right = 0;
            }
        }
        left = right  =0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == '(') left++;
            else right++;
            if (left == right) {
                res = Math.max(res, left * 2);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return res;
    }
}
